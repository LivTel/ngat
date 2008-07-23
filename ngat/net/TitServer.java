package ngat.net;

import ngat.util.*;
import ngat.util.logging.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;

/** Telescope Image Transfer (TIT) server - used primarily for transferring images
 * between Telescope subsystems (e.g. ICSes to RCS (to POS) ).
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: TitServer.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/TitServer.java,v $
 * </dl>
 * @author $Author: eng $
 * @version $Revision: 1.1 $
 */
public class TitServer extends ControlThread implements Logging{

    static long FILE_NOT_FOUND = -12L;
    
    static int BUFFER_LENGTH = 4096;
    
    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss.SSS");

    static final String CLASS = "RCS_CommandServer";
    
    CommandParser parser;
    
    ServerSocket serverSocket;
    
    Socket clientSocket;
    
    String command;
    
    int port;

    String id;
    
    int connectCount;
    
    /** Create a TitServer using specified settings.
     * @param id The name/id of this TitServer (for logging purposes).
     * @param port The port on which the server listens.*/
    public TitServer(String id, int port) {
	super(id, true);
	this.port = port;
	logger = LogManager.getLogger(this);
    }

    /** Run a standalone instance of a TitServer.<br><br>
     *
     * Usage:- <i>java TitServer <ident> <port> </i>
     * <ul>
     *  <li>id   - A name/id for the instance (for logging).
     *  <li>port - The port to listen on for clients.
     * </ul>
     *
     */
    public static void main(String args[]) {
	if (args.length < 1) {
	    usage();
	    return;
	}
	
	String id = args[0];
	int port = 8463;
	try {
	    port = Integer.parseInt(args[1]);
	} catch (NumberFormatException nx) {
	    usage();
	    return;
	}
	
	LogHandler console = new ConsoleLogHandler(new SimpleLogFormatter());
	console.setLogLevel(3);

	Logger logger = LogManager.getLogger(TitServer.class);
	
	logger.setLogLevel(3);
	logger.addHandler(console);

	new TitServer(id, port).start();

    }

    private static void usage() {
	System.err.println("Usage: java TitServer <id> <port>");
    }

    /** Setup the server.*/
    public void initialise() {
	try {
	    serverSocket = new ServerSocket(port);
	    logger.log(INFO, 1, CLASS, id, "init", "Opened server socket on port: "+port);
	} catch (IOException iox) {
	    logger.log(ERROR, 1, CLASS, id, "init", "Starting server: "+iox); 
	}
    }

    /** Run the server.*/
    public void mainTask() {
	Socket clientSocket = null;
	
	// Listen for Client connections. Timeout regularly to check for termination signal.
	while (canRun() && !isInterrupted()) {  
	    try {
		clientSocket = serverSocket.accept();
		logger.log(INFO, 1, CLASS, id, "mainTask",
			     "Client attached from: " + clientSocket.getInetAddress()+
			     " port: " + clientSocket.getPort());
	    } catch (InterruptedIOException iie1) {
		// Socket timed-out so try again
	    } catch (IOException iox) {
		if (canRun()) 
		    logger.log(ERROR, 1, CLASS, id, "mainTask", "Error connecting to client", null, iox);
		clientSocket = null;
	    }
	    if (clientSocket != null) break; // got a connection.
	}
	// 
	if (clientSocket != null) {
	    logger.log(INFO, 2, CLASS, id, "mainTask",
			 "Creating Connection Thread for Client at: ["+
			 clientSocket.getInetAddress() + "] on local port: " + 
			 clientSocket.getLocalPort());
	    TitServerConnectionThread connectionThread = new TitServerConnectionThread(clientSocket);
	    
	    if (connectionThread == null) {
		logger.log(ERROR, 1, CLASS, id, "mainTask", 
			     "Error generating CommandConnection Thread for client: ");
	    } else {
		logger.log(INFO, 2, CLASS, id, "mainTask", 
			     "Starting CommandConnection Thread for client connection: ");
		connectionThread.start();
	    }
	}
	
    }
    
    /** Release resources.*/
    public void shutdown() {
	
    }

    /** Override terminator.*/
    public void close() {
	synchronized (runLock) {
	    terminate();
	    try {
		serverSocket.close();
	    } catch (IOException iox) {
		logger.log(WARNING, 1, CLASS, id, "terminate", "Error closing ServerSocket.");
	    }
	}
    }

    private class TitServerConnectionThread extends ControlThread {
	
	/** Socket associated with current client connection. */
	protected Socket clientSocket;

	/** Input stream from client.*/
	DataInputStream cin;
	
	/** Output stream to client.*/
	DataOutputStream cout;
	
	String CLASS;
	
	long sessionStart;

	Date date;

	/** Create a TitServerConnction.*/
	TitServerConnectionThread(Socket clientSocket) {	    
	    super("COMMAND_CONNECT#", false);
	    setName("COMMAND_CONNECT#"+(connectCount++));
	    this.clientSocket = clientSocket;	 
	    CLASS = TitServer.this.CLASS+".ConnectionThread";
	    sessionStart = System.currentTimeMillis();
	} // (Constructor).
	
	/** Set up I/O streams between here and client. */
	protected void initialise() {
	    
	    // Make connections to Client.
	    try {
		//clientSocket.setSoTimeout(20000);
		//clientSocket.setTcpNoDelay(true);   // send small packets immediately.
		//clientSocket.setSoLinger(true, 600); // give up and close after 5mins.
		cin = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		logger.log(INFO, 2, CLASS, getName(), "init",
			     "Opened INPUT stream from Client: " + clientSocket.getInetAddress()+":"+
			     clientSocket.getPort()+" timeout: "+clientSocket.getSoTimeout()+" secs.");	   
	    } catch (IOException ie1) {
		logger.log(ERROR, 1, CLASS, getName(), "init",
			     "Error opening input stream from Client: " + 
			     clientSocket.getInetAddress()+
			     " : "+clientSocket.getPort());
		logger.dumpStack(2, ie1);
		terminate(); // cant send Error message as connect failed. Client will see IOException.
	    }
	    
	    try {
		cout = new DataOutputStream(clientSocket.getOutputStream());
		logger.log(INFO, 1, CLASS, getName(), "init",
			     "Opened OUTPUT stream to Client: " + clientSocket.getInetAddress()+" : "+
			     clientSocket.getPort()+" and flushed header:");
	    } catch (IOException ie2) {
		logger.log(ERROR, 1, CLASS, getName(), "init",
			     "Error opening output stream to Client: " + clientSocket.getInetAddress() +
			     ":" + clientSocket.getPort());
		logger.dumpStack(2, ie2);
		terminate(); // cant send Error message as connect failed. Client will see IOException.
	    }		    
	    
	} // (initialise).
	
	protected void mainTask() {
	    
	    command = null;
	    String reply   = null;
	    
	    // 1. Read the data from the Client.
	    try {
		command = cin.readLine();
				
		logger.log(INFO, 1, CLASS, getName(), "init", 
			     "Read Command from Client: "+command);	
		
		if (command == null) return;	
		
	    } catch (IOException ie3) {
		logger.log(ERROR, 1, CLASS, getName(), "init", 
			     "Error reading request from Client - IOError: "+ie3);
		logger.dumpStack(2, ie3);
		terminate();
	    }
	    
	    date = new Date();
	    
	    // 2. Process.
	    if (command.startsWith("get")) {
		processGet();
	    } 
	    
	    try {
		cout.close();
		cin.close();
		clientSocket.close();
	    } catch (IOException iox) {
		
	    }
	} //  main().
	
	
	protected void shutdown() {
	    logger.log(INFO, 1, CLASS, getName(), "mainTask", "Shutting down now.");
	} // shutdown().
	
	private void processGet() {
	    String filename = command.substring(4).trim();
	    DataInputStream fin = null; 
	    
	    byte[] bb = new byte[BUFFER_LENGTH];
	    int len = 0;
	    
	    try {
		fin = new DataInputStream(new FileInputStream(filename));
		File file = new File(filename);
		long size = file.length();
		cout.writeLong(size);
	    } catch (IOException fx) {
		logger.log(ERROR, 1, CLASS, getName(), "init", 
			   "Opening file: ", null, fx);
	 		
		try {
		    cout.writeLong(FILE_NOT_FOUND);
		    cout.close();
		} catch (IOException iox) {
		    logger.log(ERROR, 1, CLASS, getName(), "init", 
			       "Closing output file: ", null, iox);
		}	
		return;
	    }
	    
	    try {
		while ( (len = fin.read(bb) ) != -1 ) {			
		    cout.write(bb, 0, len);
		}
		
	    } catch (IOException iox) {
		logger.log(ERROR, 1, CLASS, getName(), "init", 
			   "Transferring file: ", null, iox);
		return;
	    } finally {
		try {
		    cout.close();
		} catch (IOException iox) {
		    logger.log(ERROR, 1, CLASS, getName(), "init", 
			       "Closing output file: ", null, iox);
		}
	    }
	    
	}
	
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2001/07/11 10:50:28  snf
/** Initial revision
/** */
