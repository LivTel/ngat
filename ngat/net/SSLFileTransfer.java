package ngat.net;

import ngat.util.*;
import ngat.util.logging.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.security.*;
import javax.net.ssl.*;
import javax.security.cert.*;

// THIS MAY BE NEEDED TO COMPILE UNDER JAVA1.3
//import com.sun.net.ssl.*;

/**File Transfer Server/Client - with SSL. 
 * Primarily intended for image transfer between Telescope and
 * data-archives / web servers.
 *
 * The inner classes Server and Client can be instantiated as part of an 
 * application by calling the respective factory constructors then calling 
 * bind() and start() (Server) or any of the transfer methods (Client).
 *
 * The SSLFileTransfer Server can be stopped by calling terminate().
 *
 * Files are transferred by calling the (Client) methods:
 * 
 * <ul>
 *   <li> get <src-file>
 *   <li> put <dest-file>
 *   <li> fwd <host> <port> <dest-file>
 *   <li> nab <host> <port> <src-file> ## TBD ##
 * </ul>
 *
 * Command line usage: 
 *
 * <dl>
 *  <dt>(Server)
 *     <dd><i>java SSLFileTransfer [-bind] -id&lt;id&gt; -port&lt;port&gt; [&lt;-secure&gt;]</i>
 *
 *  <dt>(Client)
 *     <dd><i>java SSLFileTransfer [-client] -put &lt;dest-file&gt;
 *     <dd><i>java SSLFileTransfer [-client] -get &lt;src-file&gt; 
 *     <dd><i>java SSLFileTransfer [-client] -fwd -host&lt;host&gt; -port&lt;port&gt; -file&lt;dest-file&gt;
 *     <dd><i>java SSLFileTransfer [-client] -nab -host&lt;host&gt; -port&lt;port&gt; -file&lt;src-file&gt;
 * </dl>
 *
 * The Logger is initially setup as: <i>ngat.util.SSLFileTransfer</i> but can be
 * changed by public method setLogger(String name). 
 *
 * <dl>
 *  <dt><b>RCS:</b>
 *   <dd>$Id$
 *  <dt><b>Source:</b>
 *   <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/SSLFileTransfer.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public class SSLFileTransfer implements Logging {

    public static final String CLASS = "SSLFileTransfer";


    /** Exitcode indicating successful operation / completion of service.*/
    public static final int STATUS_SUCCESS = 0;

    /** Error exitcode indicating a problem parsing command line arguments.*/
    public static final int PARSE_ERROR = 1;
       
    /** Error exitcode indicating one or more missing command line arguments.*/
    public static final int MISSING_ARG_ERROR = 2;
    
    /** Error exitcode indicating a missing certificate.*/
    public static final int MISSING_CERT_ERROR = 3;
    
    /** Error exitcode indicating a missing or unwritable base directory.*/
    public static final int BASE_DIR_ERROR = 4;
    
    /** Error exitcode indicating a bind problem with server.*/
    public static final int SERVER_BIND_ERROR = 5;
    
    /** Error exitcode indicating a problem initializing a client.*/
    public static final int CLIENT_INIT_ERROR = 6;
    
    /** Error exitcode indicating a problem during transfer .*/
    public static final int TRANSFER_ERROR = 7;
    
    /** Error exitcode indicating an unknown class of request.*/
    public static final int UNKNOWN_REQUEST_ERROR = 8;


    // CODES.

    public static final long READY_TO_START_CODE = 555L;

    public static final int EOT_CODE = -555;


    // Error return codes from server.

    /** A file could not be found.*/
    public static final long FILE_NOT_FOUND      = -1L;

    /** Server not able to create a file.*/
    public static final long FILE_UNABLE_CREATE  = -2L;

    /** SOme of the call parameters were incorrect/missing.*/
    public static final long ILLEGAL_PARAMETERS  = -3L;

    /** Server is not capable of acting as a relay.*/
    public static final long RELAY_NOT_AVAILABLE = -4L;
    
    /** A problem with the ongoing relay connection.*/
    public static final long RELAY_CONNECTION    = -5L;

    /** Server is not capable of accepting data via put or fwd.*/
    public static final long WRITE_NOT_AVAILABLE = -6L;
    

    /** This is the expected available (max) transfer rate over the internet (KBytes/sec).*/
    public static int           KBPS_RATE        = 5;
    
    public static int           SERVER_TIMEOUT   = 7200000; // 2 hours

    public static int           BACKLOG          = 5;

    public static int           DEFAULT_BUFFER_LENGTH    = 1024;

    public static final int     DEFAULT_TIMEOUT  = 20000;
    
    public static final String  DEFAULT_LOGGER   = "SSLFileTransfer";

    public static final String  DEFAULT_HOST     = "localhost";
    
    public static final int     DEFAULT_PORT     = 8473;


    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm z");

    public static final SimpleDateFormat rdf = new SimpleDateFormat("HHmmss");

    /** Keeps track of connections active.*/
    protected static volatile int activeCount = 0;

    /** All class logging.*/
    static Logger classLogger;
  
    /** Parser for command line usage.*/
    static CommandParser parser; 

    /** Instantiation counter.*/
    private static int counter;
    
    /** Increment and return the instantiation counter.*/
    public static int incCounter() {
	synchronized (SSLFileTransfer.class) {
	    counter++;
	    return counter;
	}
    }

    /** Check the value of the counter.*/
    public static int getCounter() {
	synchronized (SSLFileTransfer.class) {
	    return counter;
	}
    }
    
    /** Increment/decrement and return the active counter.*/
    public static int changeActiveCounter(int updown) {
	synchronized (SSLFileTransfer.class) {
	    activeCount += updown;
	    return activeCount;
	}
    }

    /** Check the value of the active counter.*/
    public static int getActiveCounter() {
	synchronized (SSLFileTransfer.class) {
	    return activeCount;
	}
    }

    /** Set the Logger to be used. 
     * @param name The name of the Logger. This should have been setup in advance.
     * In order to catch all relevant Log messages, this method should be called
     * immediately after calling the Constructor.
     */
    public static void setLogger(String name) {
	classLogger = LogManager.getLogger(name);
    }
    
    /** Sets up the default class logger.*/
    static {
	classLogger =  LogManager.getLogger(CLASS);
    }

    /** Create a (Secure) Server instance.
     * @param id The name/id of this Server (for logging purposes).  
     */
    public static Server createServer(String id) {
	Server server = new Server(id);
	return server;
    }


    // -----------------------------------------------------------------------
    //            SERVER
    // -----------------------------------------------------------------------

    /** SSLFileTransfer Server.*/
    public static class Server extends ControlThread implements Logging { 

	/** Class ID for logging.*/
	final String CLASS = "SSLFileTransfer.Server";

	/** The ServerSocket.*/
	ServerSocket serverSocket;

	/** Count number of connections.*/
	int connectCount;

	/** True if single threaded server.*/
	boolean singleThreaded;
	
	/** Port to bind to.*/
	int port;

	String command;

	/** Directory where received files will be placed if not absolute paths.*/
	File baseDirectory;

	/** File containing server's KeyStore.*/
	File keyFile;

	/** File containing server's TrustStore.*/
	File trustFile;

	/** Keystore password unencrypted.*/
	String keyPass;

	/** True if the server can be a relay.*/
	boolean relay;

	/** Temporary directory to store relay images in before forwarding.*/
	File tempDir;

	/** Root of directory tree which can be accessed by client.*/
	File rootDir;

	/** True if client can only get rather than put or fwd.*/
	boolean getOnly;

	/** True if incoming connections should be secure.*/
	boolean secureIncoming;

	/** True if outgoing relayed connections should be secure.*/
	boolean secureOutgoing;

	/** True if we use the old version of the protocol for incoming (fwds). always use new for outgoing.*/
	boolean oldProtocol;

	/** Size of buffer to use.*/
	int bufferLength = DEFAULT_BUFFER_LENGTH;

	/** Create a Server using specified settings. File base-directory is set initially
	 * to the current working directory.
	 * @param id The name/id of this Server (for logging purposes).
	 * @param secure true if a secure connection is required.*/
	public Server(String id) {
	    super(id, true);	  	
	    baseDirectory = new File(System.getProperty("user.dir"));	
	    // Defaults to multi-threaded
	    // singleThreaded = (System.getProperty("single.threaded") != null); // defaults to multi-threaded
	    // secureIncoming = (System.getProperty("secure.incoming") != null); // defaults to non-secure
	    // secureOutgoing = (System.getProperty("secure.outgoing") != null); // defaults to non-secure
	    // oldProtocol    = (System.getProperty("old.protocol")    != null); // defaults to new protocol	   
	}
	
	/** Bind the server to the specified port.
	 * @param port The port on which the server listens.
	 * @param auth Set true if Client Authentication is required.
	 * @exception IOException if the bind operation fails for any reason.
	 */
	public void bind(int port, boolean auth, boolean relay) throws Exception {
	    this.port  = port;
	    this.relay = relay;

	    if (secureIncoming) {
		KeyManager []   kms = getKeyManagers();
		TrustManager [] tms = getTrustManagers();
	    
		SSLContext context = SSLContext.getInstance("TLS");
		classLogger.log(INFO, 1, CLASS, getName(), "init", "Got SSL Context");
		context.init(kms, tms, null);
		classLogger.log(INFO, 1, CLASS, getName(), "init", "Context inited");

		SSLServerSocketFactory sf = context.getServerSocketFactory();
		//SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
		classLogger.log(INFO, 1, CLASS, getName(), "init", "Got a SSLSocketFty");

		serverSocket = sf.createServerSocket(port, BACKLOG);

		//set this when client auth is required.
		if (auth) {
		    ((SSLServerSocket)serverSocket).setNeedClientAuth(true);
		}
		String [] suites = ((SSLServerSocket)serverSocket).getSupportedCipherSuites();
		
		((SSLServerSocket)serverSocket).setEnabledCipherSuites(suites);

	    } else {
		serverSocket = new ServerSocket(port, BACKLOG);
	    }
	    	   
	    serverSocket.setSoTimeout(SERVER_TIMEOUT);
	    
	    // Check if we can see or make a temp dir under 'user.home'
	    // If neither then fail.
	    if (relay) {
		File userDir = new File(System.getProperty("user.home"));
		tempDir = new File(userDir, "tmp_relay");
		if ((! tempDir.exists())) {		   
		    tempDir.mkdir();
		} else {
		    if (! tempDir.isDirectory())
			throw new FileNotFoundException("Temp directory: "+tempDir.getPath()+" is not a directory.");
		}
	    }
	    
	    classLogger.log(INFO, 1, CLASS, getName(), "init", 
			    "Started SSLFileTransfer Server: "+ getName()+
			    "\n\tIncoming is:    "+(secureIncoming ? "SECURE, Client Authentication by: "+
						    (auth ? "*Certificate*" : "*None*") : "NON_SECURE")+
			    "\n\tOngoing is:     "+(secureOutgoing ? "SECURE" : "NON_SECURE")+
			    "\n\tBound to port:  "+port+ 
			    "\n\tMode:           "+(singleThreaded ? "SINGLE_THREADED" :"MULTI-THREADED")+
			    "\n\tBase-directory: "+baseDirectory.getPath()+			    
			    "\n\t"+(getOnly ? "ReadOnly from:  "+(rootDir != null ? rootDir.getPath() : "nowhere") : "Full access"));
	}
	
	/** Retrieve the list of KeyManagers for the SERVER.*/
	private KeyManager [] getKeyManagers() throws Exception {
	    String alg = KeyManagerFactory.getDefaultAlgorithm();
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gK: Got algorithm: "+alg);
	    KeyManagerFactory kmf = KeyManagerFactory.getInstance(alg);
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gK: Got KMF");
	    // Uses "server.private" or equivalant.
	    FileInputStream fis = new FileInputStream(keyFile);
	    KeyStore ks = KeyStore.getInstance("jks");
	    ks.load(fis, keyPass.toCharArray());
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gK: Loaded KS");
	    fis.close();
	    kmf.init(ks, keyPass.toCharArray());
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gK: KMF inited");
	    KeyManager [] kms = kmf.getKeyManagers();
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gK: Got KMs");
	    return kms;
	}

	/** Retrieve the list of trustManagers for the SERVER.*/
	private TrustManager [] getTrustManagers() throws Exception {
	    String alg = TrustManagerFactory.getDefaultAlgorithm();
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gT: Got algorithm: "+alg);
	    TrustManagerFactory tmf = TrustManagerFactory.getInstance(alg);
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gT: Got TMF");
	    // Uses "client.public" or equivalant.
	    FileInputStream fis = new FileInputStream(trustFile);
	    KeyStore ks = KeyStore.getInstance("jks");
	    ks.load(fis, "public".toCharArray());
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gT: Loaded TS");
	    fis.close();
	    tmf.init(ks);
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gT: TMF inited");
	    TrustManager [] tms = tmf.getTrustManagers();
	    classLogger.log(INFO, 3, CLASS, getName(), "init","gT: Got TMs");
	    return tms;
	}

	/** True if single threaded server.*/
        public void setSingleThreaded(boolean s) { this.singleThreaded = s; }

	/** Set True if incoming connections should be secure.*/
        public void setSecureIncoming(boolean s) { this.secureIncoming = s;}

        /** Set True if outgoing relayed connections should be secure.*/
        public void setSecureOutgoing(boolean s) { this.secureOutgoing =s; }

        /** Set True if we use the old version of the protocol for incoming (fwds). always use new for outgoing.*/
	public void setOldProtocol(boolean s) { this.oldProtocol =s; }

	
	/** Setup the server.*/
	public void initialise() {

	}

	/** Run the server.*/
	public void mainTask() {

	    Socket clientSocket = null;
	    
	    // Listen for Client connections. Timeout regularly to check for termination signal.
	    while (canRun() && !isInterrupted()) {  
		try {
		    classLogger.log(INFO, 2, CLASS, getName(), "mainTask",
				    "Server is ready for incoming with "+getActiveCounter()+" active connections");
		    clientSocket = serverSocket.accept();
		   
		    classLogger.log(INFO, 1, CLASS, getName(), "mainTask",
				    "Client attached from: " + clientSocket.getInetAddress()+
				    " : " + clientSocket.getPort());
		} catch (SocketTimeoutException stx) {
		    // Socket timed-out so try again
		    classLogger.log(ERROR, 2, CLASS, getName(), "mainTask",
				    "ServerSocket timed out on accept() after "+stx.bytesTransferred+" bytes : "+stx);	 
		    clientSocket = null;
		} catch (InterruptedIOException iix) {
		    // Socket timed-out so try again
		    classLogger.log(ERROR, 2, CLASS, getName(), "mainTask",
				    "ServerSocket interrupted during accept() after "+iix.bytesTransferred+" bytes : "+iix);	 
		    clientSocket = null;
		} catch (IOException iox) {
		    if (canRun()) 
			classLogger.log(ERROR, 1, CLASS, getName(), "mainTask", 
					"Error connecting to client", null, iox);
		    clientSocket = null;
		} catch (Exception e) {
		    classLogger.log(ERROR, 2, CLASS, getName(), "mainTask",
				    "Bad error on accept: "+e);
		    e.printStackTrace();
		    clientSocket = null;
		}
		if (clientSocket != null) break; // got a connection.
	    }
	    // 
	    if (clientSocket != null) {
		try {
		    classLogger.log(INFO, 1, CLASS, getName(), "mainTask",
				    "Creating Connection Thread for Client at: ["+clientSocket.getInetAddress() + "]"+
				    " on local port: " +clientSocket.getLocalPort()+
				    " SendBuffer: "+clientSocket.getSendBufferSize()+
				    " RcvBuff: "+clientSocket.getReceiveBufferSize());
		} catch (Exception e) {
		    e.printStackTrace();
		    classLogger.log(INFO, 1, CLASS, getName(), "mainTask", "WARNING-error reading buff sizes: "+e);
		}

		ServerConnectionHandler handler = 
		    new ServerConnectionHandler("["+getName()+":"+(connectCount++)+"]", clientSocket);
		
		//ServerConnectionThread connectionThread = new ServerConnectionThread(handler);
		
		if (handler == null) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "mainTask", 
			       "Unable to generate ConnectionHandler for client: ");
		} else {

		    if (singleThreaded) {
			classLogger.log(INFO, 2, CLASS, getName(), "mainTask", 
					"SingleThreadedServer:: Running ConnectionHandler for client connection:: ");
			handler.initialise();
			handler.mainTask();
			handler.shutdown();

		    } else {
			classLogger.log(INFO, 2, CLASS, getName(), "mainTask", 
					"MultiThreadedServer:: Starting Connection Thread for client connection: ");
			ServerConnectionThread connectionThread = new ServerConnectionThread(handler);			
			connectionThread.start();
		    }

		}
	    } else {
		classLogger.log(ERROR, 1, CLASS, getName(), "mainTask",
				"ClientSocket is NULL !");
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
		    classLogger.log(INFO, 2, CLASS, getName(), "mainTask",
				    "Closed server socket.");
		} catch (IOException iox) {
		    classLogger.log(WARNING, 1, CLASS, getName(), "terminate", "Error closing ServerSocket.");
		}
	    }
	}

	public void setGetOnly(boolean go) { this.getOnly = go; }

	public void setRootDir(File rd) {
	    this.rootDir = rd; 
	     classLogger.log(INFO, 1, CLASS, getName(), "setRootDir", 
			     "Reset Root-Directory: "+rootDir.getPath());
	}
	

	/** Sets the current base-directory. Files specified in put/get operations
	 * with relative paths are relative to this directory.
	 * @param baseDir The base directory for all relative file names.
	 */
	public void setBaseDirectory(File baseDir) {
	    this.baseDirectory = baseDir; 
	     classLogger.log(INFO, 1, CLASS, getName(), "setBaseDir", 
			     "Reset Base-Directory: "+baseDirectory.getPath());
	}
	
	/** Sets the file to use as KeyStore for the SERVER.
	 * @param keyFile The KeyStore.
	 */
	public void setKeyFile(File keyFile) {
	    this.keyFile = keyFile;
	}
	
	/** Sets the file to use as TrustStore for the SERVER.
	 * @param trustFile The TrustStore.
	 */
	public void setTrustFile(File trustFile) {
	    this.trustFile = trustFile;
	}

	/** Sets the keystore password.*/
	public void setKeyPass(String keyPass) {
	    this.keyPass = keyPass;
	}

	/** Sets the buffer length.*/
	public void setBufferLength(int b) { this.bufferLength = b;}

	public class ServerConnectionThread extends Thread {

	    ServerConnectionHandler handler;

	    public ServerConnectionThread(ServerConnectionHandler handler) {
		this.handler = handler;
	    }

	    public void run() {
		classLogger.log(INFO, 2, CLASS, getName(), "mainTask", 
				"SCT:: "+getName()+" Running ConnectionHandler for client connection:: ");
		handler.initialise();
		handler.mainTask();
		handler.shutdown();
	    }

	}



	// -----------------------------------------------------------------------
	//            SERVER_CONNECTION_THREAD
	// -----------------------------------------------------------------------
	
	/** Create a handler to handle a client connection.*/
	public class ServerConnectionHandler {
	    
	    /** Socket associated with current client connection. */
	    protected Socket clientSocket;
	    
	    /** Input stream from client.*/
	    DataInputStream cin;
	    
	    /** Output stream to client.*/
	    DataOutputStream cout;	

	    final String CLASS = "SSLFileTransfer.Server.ConnectionHandler";

	    String name;
	    
	    long sessionStart;
	    
	    Date date;

	    boolean canRun = true;
	    
	    /** Create a ServerConnctionThread.*/
	    public ServerConnectionHandler(String name, Socket clientSocket) {	    
		this.name = name;
		this.clientSocket = clientSocket;	 	
		sessionStart = System.currentTimeMillis();
			
		if (Server.this.secureIncoming) {	
		    String [] suites = ((SSLSocket)clientSocket).getSupportedCipherSuites();		    
		    ((SSLSocket)clientSocket).setEnabledCipherSuites(suites);		
		}

	    } // (Constructor).
	    
	    /** Set up I/O streams between here and client. */
	    protected void initialise() {
	
		classLogger.log(INFO, 1, CLASS, getName(), "init",
				"Starting connection handler initialisation...");
		
		changeActiveCounter(1);
		classLogger.log(INFO, 1, CLASS, getName(), "init",
				"Active counter set: "+getActiveCounter());
		
		// Make connections to Client.
		try {
		   
		    clientSocket.setTcpNoDelay(true);   // send small packets immediately.
		    clientSocket.setSoTimeout(300000);   // no waits on read for longer than 30 sec
		  
		    cin = new DataInputStream(clientSocket.getInputStream());
		    classLogger.log(INFO, 2, CLASS, getName(), "init",
				    getName()+"Opened INPUT stream from Client: " + clientSocket.getInetAddress()+":"+
				    clientSocket.getPort()+" timeout: "+clientSocket.getSoTimeout()+" ms.");	   
		} catch (Exception ie1) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "init",
				    getName()+"Error opening input stream from Client: " + 
				    clientSocket.getInetAddress()+
				    " : "+clientSocket.getPort());
		    classLogger.dumpStack(2, ie1);
		    terminate(); // cant send Error message as connect failed. Client will see IOException.
		    return;
		}
		
		try {
		    cout = new DataOutputStream(clientSocket.getOutputStream());
		    cout.flush();
		    classLogger.log(INFO, 2, CLASS, getName(), "init",
				    getName()+"Opened OUTPUT stream to Client: " + clientSocket.getInetAddress()+" : "+
				    clientSocket.getPort()+" and flushed header:");
		} catch (Exception ie2) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "init",
				    getName()+"Error opening output stream to Client: " + clientSocket.getInetAddress() +
				    ":" + clientSocket.getPort());
		    classLogger.dumpStack(2, ie2);
		    terminate(); // cant send Error message as connect failed. Client will see IOException.
		}		    
		
	    } // (initialise).
	    
	    protected void mainTask() {
		
		if (!canRun)
		    return;

		String command = null;
		String reply   = null;
		
		classLogger.log(INFO, 2, CLASS, getName(), "main", 
				getName()+"Ready to read command");
		
		// 1. Read the data from the Client.
		try {
		    StringBuffer buff = new StringBuffer();
		    byte b = cin.readByte();
		    while (b != 10 && b != 13) {
			buff.append((char)b);
			b = cin.readByte();		  
		    }
		    
		    command = buff.toString();
		    
		    classLogger.log(INFO, 1, CLASS, getName(), "main", 
				    getName()+"Read Command from Client: "+command);	
		    
		    if (command == null) {
			terminate();
			return;
		    }	
		    
		} catch (Exception ie3) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "main", 
				    getName()+"Error reading request from Client - IOError: "+ie3);
		    classLogger.dumpStack(2, ie3);
		    terminate(); 
		    return;
		}
		
		date = new Date();
		
		// 2. Process.
		if (command.startsWith("get")) {
		    processGet(command);
		} else if
		    (command.startsWith("put")) {
		    processPut(command);
		} else if
		    (command.startsWith("fwd")) {
		    processFwd(command);
		} else {
		    classLogger.log(ERROR, 1, CLASS, getName(), "main",
				    "Unrecognized command: "+command);
		}
		
		
	    } //  main().
	    
	    protected void shutdown() {	
		
		classLogger.log(INFO, 2, CLASS, getName(), "shutdown", getName()+"Shutting down now."); 
		changeActiveCounter(-1);
		try {
		    cout.close();
		    cin.close();
		    clientSocket.close();
		    clientSocket = null;
		    cin          = null;
		    cout         = null;
		} catch (Exception ex) {
		    classLogger.log(INFO, 1, CLASS, getName(), "shutdown", 
				    "Error shutting down: "+ex);
		}
	    } // shutdown().
	    
	    /** Set a flag so we dont try to run.*/
	    private void terminate() {
		classLogger.log(INFO, 1, CLASS, getName(), "terminate",
				"Setting handler termination flag on error");
		canRun = false;
	    }

	    public String getName() { return name;}

	    /** Return a file from named location over the I/O stream.
	     * Usage: get <srcfilename>.*/
	    private void processGet(String command) {
		String  filename    = command.substring(4).trim();
		
		DataInputStream fin = null; 
		
		byte[] bb     = new byte[bufferLength];
		int    len    = 0;
		long   size   = 0;
		int    blocks = 0;
		
		try {
		    File file = new File(filename);
		    if (! file.isAbsolute()) {
			file = new File(baseDirectory, filename);
			classLogger.log(INFO, 2, CLASS, getName(), "processGet",
					getName()+"Opening Absolute file: "+file.getPath());
		    } else {
			classLogger.log(INFO, 1, CLASS, getName(), "processGet",
					getName()+"Opening Relative file: "+file.getPath());
		    }	
	   
		    fin = new DataInputStream(new FileInputStream(file));		    
		    size      = file.length();
		    blocks    = (int)(size/bufferLength) + 1;
		    cout.writeLong(size);
		} catch (Exception fx) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processGet", 
			       getName()+"Error Opening requested file: "+fx);		    
		    try {
			cout.writeLong(FILE_NOT_FOUND);			
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processGet", 
				   getName()+"Closing output file: "+iox);
		    }	
		    return;
		}
		
		long bytes = 0L;
		long start = System.currentTimeMillis();
		
		try {
		    for (int ib = 0; ib < blocks; ib++) {
			len = fin.read(bb);		   
			cout.write(bb, 0, len);		    
		    }
		    
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processGet", 
			       getName()+"Transferring file: "+iox);		  
		} 
	    }	
	    
	    /** Save the data on the I/O stream to the named location.
	     * Usage: put <destfilename>.*/
	    private void processPut(String command) {
		classLogger.log(INFO, 2, CLASS, getName(), "processPut", getName()+"Starting Execution of PUT");

		if (getOnly) {
		    try {	
			cout.writeLong(WRITE_NOT_AVAILABLE);
			cout.flush();
		    }  catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
					getName()+"Error: While sending ready-to-start code to client: "+iox);
		    }
		}

		try {		   
		    cout.writeLong(READY_TO_START_CODE);	
		    cout.flush(); 
		    classLogger.log(ERROR, 2, CLASS, getName(), "processPut", 
				    getName()+"Sent ready-to-start code [555]");
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
				    getName()+"Error: While sending ready-to-start code to client: "+iox);
		}

		DataOutputStream fout = null; 

		String destFilename =  command.substring(4).trim();

		File   file = null;
		byte[] bb = new byte[bufferLength];
		int    len = 0;
		
		try { 
		    file = new File(destFilename);
		    if (! file.isAbsolute())
			file = new File(baseDirectory, destFilename);			 
		    fout = new DataOutputStream(new FileOutputStream(file));	
		    classLogger.log(INFO, 2, CLASS, getName(), "processPut", 
				    getName()+"Opened output stream to: "+file.getPath());
		} catch (Exception fx) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
				    getName()+"Error: While Opening destination file: "+fx);
		    
		    try {
			cout.writeLong(FILE_UNABLE_CREATE);
			cout.flush();
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
					getName()+"Error: While opening output file: "+iox);
		    }	
		    return;
		}
		
		classLogger.log(INFO, 2, CLASS, getName(), "processPut", 
				getName()+"Ready to read incoming data");
		int total = 0;


		try {		    

		    boolean data = true;
		    while (data) {
// 			int code = cin.readInt();
// 			//System.err.println("Code: "+code);
// 			if (code != EOT_CODE) {
// 			    len = cin.read(bb);
// 			    //System.err.println("Read: "+len+" bytes"); 
// 			    System.err.print("#");			   
// 			    if (len != -1) {				
// 				fout.write(bb, 0, len); 
// 				fout.flush();
// 				total += len;
// 			    }
// 			} else {
// 			    data = false;  
			int code = cin.readInt();
			//System.err.print("<"+code+">"); // no of bytes we expect to receive.		
			if (code != EOT_CODE) {	
			    bb  = new byte[code];
			    cin.readFully(bb);			   
			    System.err.print("#");			  					
			    fout.write(bb, 0, code);
			    fout.flush();
			    total += code;
			} else {
			    data = false;
			    classLogger.log(INFO, 1, CLASS, getName(), "processPut", 
					    getName()+"Read EOT code: [-555]");
			}
		    }
		    
		    // Write the no of bytes received/saved for the client to confirm.
		    long size = file.length();
		    classLogger.log(INFO, 2, CLASS, getName(), "processPut",
				    "Ready to send file size "+size+" as confirmation...");
		    cout.writeLong(size);
		    cout.flush();
		    classLogger.log(INFO, 2, CLASS, getName(), "processPut", 
				    getName()+"Sent  bytes length confirmation to client: ["+size+"] and very definitely flushed");
		
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
			   getName()+"Error: While transferring file: "+iox);
		    classLogger.dumpStack(1, iox);
		    return;
		} finally {		   
		    try {
			fout.close();
			classLogger.log(INFO, 2, CLASS, getName(), "processPut", 
					getName()+"Closed destination file");
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processPut", 
				   getName()+"Closing destination file: "+iox);
		    }				
		}  
	    }		    
	    
	    /** Handles the FWD operation.*/
	    private void processFwd(String command) {
		classLogger.log(INFO, 1, CLASS, getName(), "processFwd", "Start Exec FWD");
		
	
		// Fail if the server has not been setup for relay duties.
		if (! relay) {
		    try {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Server is not  relay-capable");
			cout.writeLong(RELAY_NOT_AVAILABLE);	
			cout.flush();
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Sending report to client: "+iox);
		    }	
		    return;
		}
		
		DataOutputStream fout = null;
		
		String args = command.substring(4).trim();
		
		StringTokenizer tok = new  StringTokenizer(args, " ");
		
		// Check params: FWD <rhost> <rport> <destFileName>
		classLogger.log(INFO, 2, CLASS, getName(), "processFwd", "checking FWD params");
		if (tok.countTokens() != 3) {
		    try {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error wrong number params: ");
			cout.writeLong(ILLEGAL_PARAMETERS);	
			cout.flush();
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error Closing (client) output stream: "+iox);
			classLogger.dumpStack(1,iox);
		    }	
		    return;		
		}
		
		String rhost = tok.nextToken();
		int    rport = 0;
		
		// check remote server port.
		try {
		    rport = Integer.parseInt(tok.nextToken());
		} catch (NumberFormatException nx) {	
		    classLogger.dumpStack(1,nx);
		    try {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd",
					getName()+"Illegal rport number: ");
			cout.writeLong(ILLEGAL_PARAMETERS);	
			cout.flush();	   
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error Closing (client) output stream: "+iox);
			classLogger.dumpStack(1,iox);
		    }	
		    return;
		}
		
		String destFileName = tok.nextToken();

		long startTime = System.currentTimeMillis();
		
		byte[] bb    = new byte[bufferLength];
		int    len   = 0;
		int    bytes = 0;
		
		// Create a temporary file - append the destfile's final name part.
		File destFile = new File(destFileName);
		File tempFile = new File(tempDir, "file_"+rdf.format(new Date())+"_"+incCounter());
		
		// Attempt to open for output.
		try { 		   	 
		    fout = new DataOutputStream(new FileOutputStream(tempFile));		
		} catch (Exception fx) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
				    getName()+"Opening temporary file: "+fx);
		    classLogger.dumpStack(1,fx);
		    try {
			cout.writeLong(FILE_UNABLE_CREATE);	
			cout.flush();
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error: Opening temporary file: "+iox);
			classLogger.dumpStack(1,iox);
		    }	
		    return;
		}

		// Confirm ready to accept file
		try {
		    classLogger.log(INFO, 2, CLASS, getName(), "processFwd", 
				    getName()+"Confirming ready to accept file: Sending 666");
		    cout.writeLong(666L);	
		    cout.flush();
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
				    getName()+"Error: Sending report to client: "+iox);	
		    classLogger.dumpStack(1,iox);
		}

		
		// Read bytes from client and store to temp file.
		classLogger.log(INFO, 2, CLASS, getName(), "processFwd", 
				getName()+"About to read bytes from client.");
		try {
		    boolean data = true;
		    while (data) {
			
			if (oldProtocol) {
			    // ------------
			    // OLD PROTOCOL
			    // ------------
			    int code = cin.readInt();			 
			    if (code != EOT_CODE) {
				len = cin.read(bb);			
				System.err.print("#");			   
				if (len != -1) {				
				    fout.write(bb, 0, len); 
				    fout.flush();				  
				}
			    } else {
				data = false;  
			    }
			    
			} else {
			    // ------------
			    // NEW PROTOCOL
			    // ------------
			    int code = cin.readInt();
			    //System.err.print("<"+code+">"); // no of bytes we expect to receive.		
			    if (code != EOT_CODE) {	
				bb  = new byte[code];
				cin.readFully(bb);			   
				System.err.print("#");			  					
				fout.write(bb, 0, code);
				fout.flush();
			    } else 
				data = false;
			    
			}

		    }

		    // XXXXXX DO NOT YET Write the no of bytes received/saved for the client to confirm.
		    bytes = (int)tempFile.length();
		    classLogger.log(INFO, 2, CLASS, getName(), "processFwd", 
				   getName()+"Stored "+bytes+" bytes in temp file: "+tempFile.getPath());
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
			   getName()+"Error: Storing file: "+iox);
		    classLogger.dumpStack(1,iox);
		    try {
			cout.writeLong(FILE_UNABLE_CREATE);	
			cout.flush();
		    } catch (Exception iox2) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					"Error: Sending report to client: "+iox2);
		    }
		    return;
		} finally {		   
		    try {
			fout.close();
			classLogger.log(INFO, 2, CLASS, getName(), "processFwd", 
					getName()+"Closed temp file");
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
				   "getName()+Error: Closing temp file: "+iox);
		    }				
		}  
		
		classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
				getName()+"Creating ongoing relay client...");
		
//  		try {
//  		    Thread.sleep(5000L);
//  		}catch (InterruptedException ix) {
//  		    System.err.println("Intx: While snoozing");
//  		}

		Client relayClient = null;

		// Create a client to handle the ongoing send.
		if (Server.this.secureOutgoing) {
		    relayClient = new Client(getName()+":RC", rhost, rport, true);
		
		    // Create the SSL connection.
		    try {
			relayClient.initialize(keyFile, keyPass, trustFile);
			classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
					getName()+"Initialized relay client.");
		    } catch (Exception ex) {	
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error: Initializing relay client: "+ex);		 
			try {
			    cout.writeLong(RELAY_CONNECTION);	
			    cout.flush();
			} catch (Exception iox) {
			    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					    getName()+"Error: Sending error report to client"+ex);
			}
			return;
		    }
		} else {
		    
		    relayClient = new Client(getName()+":RC", rhost, rport, false);
		    
		}
		
		// use the server's buffer size.
		relayClient.setBufferLength(bufferLength);
		
		// Send the tempfile.
		try { 
		    classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
				    getName()+"Preparing to send onwards");
		    relayClient.send(tempFile.getPath(), destFileName); 
		    classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
				    getName()+"Sent file onwards.");
		} catch (Exception iox) {
		    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
				    getName()+"Error: During ongoing file transfer  : "+iox);

		    // ## This is where we should now get the trace		    
		    classLogger.dumpStack(1, iox);

		    try {
			classLogger.log(INFO, 1, CLASS, getName(), "processFwd",
					getName()+"Sending error code: "+RELAY_CONNECTION+" to client");
			cout.writeLong(RELAY_CONNECTION);	
			cout.flush();
		    } catch (Exception iox2) { 			
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"Error: Sending error report to client: "+iox2);
		    } 
		    return;
		} finally {
		    try {
			tempFile.delete();
			classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
					getName()+"Deleted temporary file: "+tempFile.getPath());	
		    } catch (SecurityException sx) { 
			classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
					getName()+"** WARNING: Unable to delete temporary file: "+sx);
		    }	
		}

		// How many bytes sent onward.
		try {
		    classLogger.log(INFO, 2, CLASS, getName(), "processFwd",
				    getName()+"Sending bytes report: "+bytes+" to client");
		    cout.writeLong((long)bytes);	
		    cout.flush();
		} catch (Exception iox2) { 			
		    classLogger.log(ERROR, 1, CLASS, getName(), "processFwd", 
				    getName()+"Error: Sending report to client: "+iox2);
		} 

		double time = ((double)(System.currentTimeMillis() - startTime))/1000.0;
		double mbytes = (((double)bytes)/(1024*1024));	
		classLogger.log(INFO, 1, CLASS, getName(), "processFwd",
				getName()+"Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
				(mbytes/time)+" MBytes/sec].");
	    	
	    }
	    
	}

    }

    /** Convenience method to create a create a Client instance.
     * @param id The name/id of this Client (for logging purposes).
     * @param host The server host name/address.
     * @param port The server port.
     */
    public static Client createClient(String id, String host, int port) {	
	return new Client(id, host, port);
    }

    // -----------------------------------------------------------------------
    //            CLIENT
    // -----------------------------------------------------------------------

    /** SSLFileTransfer Client.*/
    public static class Client {
	
	final String CLASS = "SSLFileTransfer.Client";
	
	String id;
	String host;
	int    port;

	/** File containing client's KeyStore.*/
	File keyFile;
	
	/** File containing client's TrustStore.*/
	File trustFile;

	/** Keystore password - this is not very secure.*/
	String keyPass;

	/** SSL Version.*/
	String sslVersion = "TLS";

	/** An SSLSocketFactory for making socket connections.*/
	SSLSocketFactory socketFactory;

	boolean secure;

	/** The expected available bandwidth (KBytes/sec).
	 * This should be for the slowest link in the chain.*/
	int bandWidth = KBPS_RATE;

	/** Size of buffer to use.*/
	int bufferLength = DEFAULT_BUFFER_LENGTH;

	/** Create a client.*/
	public Client(String id, String host, int port, boolean secure) {
	    this.id     = id;
	    this.host   = host;
	    this.port   = port;	   
	    this.secure = secure;
	}

	/** Create a client - defaults to secure.*/
	public Client(String id, String host, int port) {
	    this(id, host, port, true);
	}


	/** Sets the available bandwidth (KBytes/sec).*/
	public void setBandWidth(int bandWidth) {
	    this.bandWidth = bandWidth;
	}
		
	/** Sets the file to use as KeyStore.
	 * @param keyFile The KeyStore.
	 */
	public void setKeyFile(File keyFile) {
	    this.keyFile = keyFile;
	}
	
	/** Sets the file to use as TrustStore.
	 * @param keyFile The TrustStore.
	 */
	public void setTrustFile(File trustFile) {
	    this.trustFile = trustFile;
	}

	/** Sets the Keystore password.
	 * @param keyPass The password to use.
	 */
	public void setKeyPass(String keyPass) {
	    this.keyPass = keyPass;
	}

	/** Set the SSL Version.
	 * @param sslVersion The SSL version to use.
	 */
	public void setSslVersion(String sslVersion) { this.sslVersion = sslVersion; }

	/** Sets the buffer length.*/
	public void setBufferLength(int b) { this.bufferLength = b;}

	public String getId() { return id; }

	/** Loads the clients certificates and creates a SocketFactory.
	 */
	public void initialize(File keyFile, String keyPass, File trustFile) throws Exception {
	    setKeyFile(keyFile);
	    setKeyPass(keyPass);
	    setTrustFile(trustFile);

	    KeyManager []   kms = getKeyManagers();
	    TrustManager [] tms = getTrustManagers();
	    
	    SSLContext context = SSLContext.getInstance(sslVersion);
	    classLogger.log(INFO, 2, CLASS, "", "init", "Created SSL Context: "+sslVersion);
	    context.init(kms, tms, null);
	    classLogger.log(INFO, 2, CLASS, "", "init", "Context initialized");
	    
	    socketFactory = (SSLSocketFactory)context.getSocketFactory(); 
	    classLogger.log(INFO, 2, CLASS, "", "init", "Got a SSLSocketFty");
	}
    
	
	/** Retrieve the list of KeyManagers for ONGOING-CLIENT.*/
	private KeyManager [] getKeyManagers() throws Exception {
	    String alg = KeyManagerFactory.getDefaultAlgorithm();
	    classLogger.log(INFO, 3, CLASS, "", "init","gK: Got algorithm: "+alg);
	    KeyManagerFactory kmf = KeyManagerFactory.getInstance(alg);
	    classLogger.log(INFO, 3, CLASS, "", "init","gK: Got KMF");
	    // Uses "server.private" or equivalant.
	    FileInputStream fis = new FileInputStream(keyFile);
	    KeyStore ks = KeyStore.getInstance("jks");
	    ks.load(fis, keyPass.toCharArray());
	    classLogger.log(INFO, 3, CLASS, "", "init","gK: Loaded KS");
	    fis.close();
	    kmf.init(ks, keyPass.toCharArray());
	    classLogger.log(INFO, 3, CLASS, "", "init","gK: KMF inited");
	    KeyManager [] kms = kmf.getKeyManagers();
	    classLogger.log(INFO, 3, CLASS, "", "init","gK: Got KMs");
	    return kms;
	}
	
	/** Retrieve the list of trustManagers for ONGOING-CLIENT.*/
	private TrustManager [] getTrustManagers() throws Exception {
	    String alg = TrustManagerFactory.getDefaultAlgorithm();
	    classLogger.log(INFO, 3, CLASS, "", "init","gT: Got algorithm: "+alg);
	    TrustManagerFactory tmf = TrustManagerFactory.getInstance(alg);
	    classLogger.log(INFO, 3, CLASS, "", "init","gT: Got TMF");
	    // Uses "client.public" or equivalant.
	    FileInputStream fis = new FileInputStream(trustFile);
	    KeyStore ks = KeyStore.getInstance("jks");
	    ks.load(fis, "public".toCharArray());
	    classLogger.log(INFO, 3, CLASS, "", "init","gT: Loaded TS");
	    fis.close();
	    tmf.init(ks);
	    classLogger.log(INFO, 3, CLASS, "", "init","gT: TMF inited");
	    TrustManager [] tms = tmf.getTrustManagers();
	    classLogger.log(INFO, 3, CLASS, "", "init","gT: Got TMs");
	    return tms;
	}
	    
	/** Synchronous request operation.
	 */
	public void request(String srcFilename, String destFilename)  
	    throws Exception {
	    new Request(srcFilename, destFilename).perform();
	}

	/** Synchronous send operation.
	 */
	public void send(String srcFilename, String destFilename)  
	    throws Exception {
	    new Send(srcFilename, destFilename).perform();  
	}

	/** Synchronous forward operation.
	 */
	public void forward(String rhost, int rport, String srcFilename, String destFilename) 
	    throws Exception {
	    new Forward(rhost, rport, srcFilename, destFilename).perform(); 
	}
	
	/** Asynchronous request operation.
	 */
	public Operation requestAsynchronous(String srcFilename, String destFilename)  
	{
	    Request request = new Request(srcFilename, destFilename);
	    request.go();
	    return request;
	}

	/** Asynchronous send operation.
	 */
	public Operation sendAsynchronous(String srcFilename, String destFilename)
	{
	    Send send = new Send(srcFilename, destFilename);
	    send.go();
	    return send;
	}
	
	/** Asynchronous forward operation.
	 */
	public Operation forwardAsynchronous(String rhost, int rport, String srcFilename, String destFilename) 
	{
	    Forward forward = new Forward(rhost, rport, srcFilename, destFilename);
	    forward.go();
	    return forward;
	}
	
	// -----------------------------------------------------------------------
	//            CLIENT$OPERATION
	// -----------------------------------------------------------------------

	/** Base class - returned by all Asynchronous calls on Client as a hook.*/
	public abstract class Operation implements Runnable {
	    
	    /** A Thread to perform this Operation.*/
	    Thread runner;
	    
	    /** An AsynchronousHandler to receive completion callback from the Operation.*/
	    AsynchronousHandler handler;

	    /**A timeout for Socket connection .*/
	    int socketTimeout;

	    /** The connection socket.*/
	    Socket socket;
	
	    /** Generic transfer operation with callback handler.*/
	    public Operation(AsynchronousHandler handler) {
		this.handler = handler;
	    }
	    
	    /** Generic transfer operation.*/
	    public Operation() {}
	    
	    /** Set the Socket timeout (millis).*/
	    public void setSoTimeout(int to) {
		this.socketTimeout = to;
	    }

	    /** Sets the AsynchHandler.*/
	    public void setAsynchronousHandler(AsynchronousHandler handler) {
		this.handler = handler;
	    }
	    
	    /** Called to assign this Operation to an execution Thread and start it.
	     * @exception IllegalThreadStateException If the Thread is already assigned whether
	     * started or not.*/
	    protected void go() throws IllegalThreadStateException {
		if (runner != null)
		    throw new IllegalThreadStateException("Thread has already been assigned.");
		runner = new Thread(this);
		runner.start();
	    }
	    
	    /** Just calls perform() to carry out the subclass specific Operation.
	     * If an AsynchHandler is specified it is notified of completion.
	     * The handler is de-referenced to avoid possibility of circularity leak.
	     */
	    public final void run() {		
		try {
		    perform();
		} catch (Exception iox) {
		    if (handler == null) {
			classLogger.log(1, CLASS, "-", "connect",
					getId()+" Exception during operation: ");
			iox.printStackTrace();
		    } else {
			handler.asynchOperationFailed(this, iox);
		    }
		    handler = null;
		    return;
		}
		if (handler != null)
		    handler.asynchOperationCompleted(this);
		handler = null;
 	    }

	    /** Overridden by concrete classes to perform the specific operation.*/
	    protected abstract void perform() throws Exception ;
	    
	    /** Wait for the Operation to complete but timeout after the specified time.
	     * @param timeout After this time (millis) the method returns whatever.
	     * A value of 0 causes this method to wait 'for ever'.
	     */
	    public void waitfor(long timeout) throws InterruptedException {
		if (runner != null)
		    runner.join(timeout);
	    }

	    /** Make the connection.
	     * @exception IOException if the connect fails for any reason.
	     */
	    protected Socket connect(String host, int port) throws Exception {
	
		Socket socket = null;

		if (Client.this.secure) {	
		    
		    socket = socketFactory.createSocket(host, port);
       		 
		    classLogger.log(1, CLASS, "-", "connect", 
				    getId()+"SSL Socket connected");
		    
		    String [] suites = ((SSLSocket)socket).getSupportedCipherSuites();
		    
		    ((SSLSocket)socket).setEnabledCipherSuites(suites);	
		    
		    String [] protocols = ((SSLSocket)socket).getEnabledProtocols();
		    
		    ((SSLSocket)socket).setEnabledProtocols(protocols);
		    
		    classLogger.log(3, CLASS, "-", "connect", 
				    getId()+"Enabled protocols are: "+protocols);
		    
		    classLogger.log(2, CLASS, "-", "connect", 
				    getId()+"Opened SSL connection to: "+host+" : "+port);

		} else {
		    socket = new Socket(host,port); 
		    classLogger.log(1, CLASS, "-", "connect", 
				    getId()+"Opened std connection to: "+host+" : "+port);
		}

		socket.setTcpNoDelay(true);

		return socket;
		
	    }

	   
	   

	} // [Operation].

	// -----------------------------------------------------------------------
	//            CLIENT$REQUEST
	// -----------------------------------------------------------------------

	/** Class to perform a Request operation.*/
	public class Request extends Operation {
	    
	    DataInputStream  ccin;
	    DataOutputStream ccout;
	    DataOutputStream fout;
	    DataInputStream  fin;
	    Socket socket;
	    
	    String srcFilename;
	    String destFilename;
	    
	    public Request(String srcFilename, String destFilename) {
		super();
		this.srcFilename  = srcFilename;
		this.destFilename = destFilename;
	    }
	    
	    protected void perform() throws Exception {   
		
		// Open a SocketConnection.
		socket = connect(host, port);	   
		socket.setSoTimeout(300000); 

// 		if (Client.this.secure) {
// 		    classLogger.log(1, CLASS, "-", "request",
// 				    getId()+"Opened socket, starting handshake: ");
		    
// 		    ((SSLSocket)socket).startHandshake();
// 		}

		ccout = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); 				
		ccout.flush();
		classLogger.log(2, CLASS, "-", "request", getId()+"Opened output stream and flushed");
		
		ccin = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		classLogger.log(2, CLASS, "-", "request", getId()+"Opened input:");
		
		String request = "";
		
		request = "get "+srcFilename+"\n";
		
		ccout.writeBytes(request);
		ccout.flush();
	    
		classLogger.log(1, CLASS, "-", "request", getId()+"Sent request "+ request);
			    
		fout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(destFilename)));
		classLogger.log(1, CLASS, "-", "request", getId()+"Opened destfile");
		
		byte[] bb = new byte[bufferLength];
		int len = 0;
		int bytes = 0;
		
		long size = ccin.readLong();
		if (size < 0L) 		 
		    throw new FileTransferException("SSLFileTransfer: error during get", (int)size);

		long startTime = System.currentTimeMillis();
		
		while ( ( len = ccin.read(bb) ) != -1 ) {		
		    fout.write(bb, 0, len);
		    bytes += len;
		}  
		
		fout.close();
		
		double time   = ((double)(System.currentTimeMillis() - startTime))/1000.0;
		double mbytes = (((double)bytes)/(1024*1024));
		classLogger.log(1, CLASS, "-", "request", getId()+
				"Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
				(mbytes/time)+" MBytes/sec].");
		
	    }
	    
	    /** Return a String representation of this Request.*/
	    public String toString() {
		return "Request: "+srcFilename+" "+destFilename;
	    }

	}

	// -----------------------------------------------------------------------
	//            CLIENT$SEND
	// -----------------------------------------------------------------------

	/** Class to perform a Send operation.*/
	public class Send extends Operation {
	    DataInputStream  ccin;
	    DataOutputStream ccout;
	    DataOutputStream fout;
	    DataInputStream  fin;
	    Socket socket;
	    
	    String srcFilename;
	    String destFilename;
	    
	    public Send(String srcFilename, String destFilename) {	
		super(); 
		this.srcFilename  = srcFilename;
		this.destFilename = destFilename;
	    }
	    
	    protected void perform() throws Exception {   
		
		// Open a SocketConnection.
		classLogger.log(1, CLASS, "-", "send", getId()+"Opening socket for: "+host+" : "+port);
		
		socket = connect(host, port);

		// ### Add a timeout on read for the returned confirm messages.
		socket.setSoTimeout(300000); 

		classLogger.log(1, CLASS, "-", "forward", getId()+"Opened socket: On local port: "+socket.getLocalPort()+
				" SendBuff: "+socket.getSendBufferSize()+
				" RcvBuff: "+socket.getReceiveBufferSize());

	// 	classLogger.log(1, CLASS, "-", "send", getId()+"Starting implicit SSL handshake..");		
// 		((SSLSocket)socket).startHandshake();
		
		ccout = new DataOutputStream(socket.getOutputStream()); 
		ccout.flush();
		classLogger.log(2, CLASS, "-", "send", getId()+"Opened output and flushed");
		
		ccin = new DataInputStream(socket.getInputStream());
		classLogger.log(2, CLASS, "-", "send", getId()+"Opened input:");
		
		// ## this is where something is occurring (or not).
		ccout.writeBytes("put "+destFilename+"\n");	
		ccout.flush();
		classLogger.log(1, CLASS, "-", "send", getId()+"Sent request and very definitely flushed: ");

		//##
		classLogger.log(2, CLASS, "-", "send", getId()+"Waiting to read confirm code from server....");				
		long code = ccin.readLong();
		classLogger.log(1, CLASS, "-", "send", getId()+"Read confirm code: "+code);
		if (code < 0L) 
		    throw new IOException("SSLFileTransfer: request error: Code: ["+code+"]");
		//##

		long startTime = System.currentTimeMillis();
		
		fin = new DataInputStream(new BufferedInputStream(new FileInputStream(srcFilename)));
		classLogger.log(2, CLASS, "-", "send", getId()+"Opened inputfile ready to read and send, buff len = "+bufferLength);
				
		byte[] bb = new byte[bufferLength];
		int len = 0;
		int bytes = 0;
		
// 		// Read the file into buffer and forward.
// 		while ( ( len = fin.read(bb) ) != -1 ) {	
// 		    ccout.writeInt(1);
// 		    ccout.write(bb, 0, len);
// 		    bytes += len;
// 		}  

		// Read the file into buffer and send.
		while ( ( len = fin.read(bb) ) != -1 ) {
		    //System.err.print("@"+len);
		    ccout.writeInt(len);			   
		    ccout.write(bb, 0, len);
		    ccout.flush();
		    //System.err.print("["+len+"]");
		    bytes += len;
		}  
	
		classLogger.log(1, CLASS, "-", "send", getId()+"Sent file, total: "+bytes+" bytes");
		
		ccout.writeInt(EOT_CODE);
		ccout.flush();
		classLogger.log(2, CLASS, "-", "send", getId()+"Sent EOT code [-555]");

		//## placeholder for size returned..
		classLogger.log(2, CLASS, "-", "send", getId()+"Waiting for transferred bytes confirmation....");
		long size = ccin.readLong();
		classLogger.log(2, CLASS, "-", "send", getId()+"Read file size: "+size);


		if (size < 0L) 
		    throw new FileTransferException("SSLFileTransfer: error during send", (int)size);
				
		double time = ((double)(System.currentTimeMillis() - startTime))/1000.0;
		double mbytes = (((double)bytes)/(1024*1024));
		classLogger.log(1, CLASS, "-", "send", 
				getId()+"Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
				(mbytes/time)+" MBytes/sec].");

		
		// Tidy up.

		try {
		    ccout.close();
		} catch (Exception iox) {
		   classLogger.log(1, CLASS, "-", "send", 
				   getId()+"Error closing socket outputstream: "+iox);
		}

		try {
		    ccin.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				   getId()+"Error closing socket inputstream: "+iox);
		}
		
		try {
		    fin.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				    getId()+"Error closing file inputstream: "+iox);
		}
		
		try {
		    socket.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				    getId()+"Error closing socket: "+iox);
		}
	    } 

	    /** Return a String representation of this Send.*/
	    public String toString() {
		return "Send: "+srcFilename+" "+destFilename;
	    }

	}

	// -----------------------------------------------------------------------
	//            CLIENT$FORWARD
	// -----------------------------------------------------------------------

	/** Class to perform a Forward operation.*/
	public class Forward extends Operation {
	    
	    DataInputStream  ccin;
	    DataOutputStream ccout;
	    DataOutputStream fout;
	    DataInputStream  fin;
	    Socket socket;

	    String rhost;
	    int    rport;
	    String srcFilename;
	    String destFilename;
	    
	    public Forward(String rhost, int rport, String srcFilename, String destFilename) {
		super();
		this.rhost = rhost;
		this.rport = rport;
		this.srcFilename  = srcFilename;
		this.destFilename = destFilename;
	    }
	    
	    protected void perform() throws Exception  {	   
		
		// Open a SocketConnection.	   
		socket = connect(host, port);

		// ### Add a timeout on read for the returned confirm messages.		
		socket.setSoTimeout(300000); 
		
		classLogger.log(1, CLASS, "-", "forward", getId()+"Opened socket: On local port: "+socket.getLocalPort()+
				" SendBuff: "+socket.getSendBufferSize()+
				" RcvBuff: "+socket.getReceiveBufferSize());
		
// 		classLogger.log(1, CLASS, "-", "send", getId()+"Starting implicit SSL handshake..");		
// 		((SSLSocket)socket).startHandshake();
		
		ccout = new DataOutputStream(socket.getOutputStream()); 
		ccout.flush();
		classLogger.log(2, CLASS, "-", "forward", getId()+"Opened output");
		
		ccin = new DataInputStream(socket.getInputStream());
		classLogger.log(2, CLASS, "-", "forward", getId()+"Opened input: "+ccin);
		
		String req = "fwd "+rhost+" "+rport+" "+destFilename+"\n";
		ccout.writeBytes(req);
		ccout.flush();
		classLogger.log(1, CLASS, "-", "forward", getId()+"Sent and flushed request:"+req);
		
		//## CUT-START		
		classLogger.log(2, CLASS, "-", "forward", "Waiting to read confirm code from server....");		
		
		long code = ccin.readLong();
		classLogger.log(2, CLASS, "-", "forward", "Read confirm code: "+code);
		if (code < 0L) 
		    throw new FileTransferException("SSLFileTransfer: Request error", (int)code);
		//## CUT-END
		
		fin = new DataInputStream(new BufferedInputStream(new FileInputStream(srcFilename)));
		classLogger.log(2, CLASS, "-", "forward", getId()+"Opened input file");
		
		byte[] bb = new byte[bufferLength];
		int len = 0;
		int bytes = 0;
		
		long startTime = System.currentTimeMillis();
		
		// Read the file into buffer and send.
		while ( ( len = fin.read(bb) ) != -1 ) {	
		    ccout.writeInt(len);			   
		    ccout.write(bb, 0, len);
		    ccout.flush();
		    //System.err.print("["+len+"]");
		    bytes += len;
		}  
		//System.err.println();
		//System.err.println("Sent total of "+bytes);

		ccout.writeInt(EOT_CODE);
		//ccout.close();
		ccout.flush();

		classLogger.log(1, CLASS, "-", "forward",
				getId()+"Wrote file content to relay and flushed output stream,"+
				" Expected transfer delay will be "+(bytes/(bandWidth*1000))+" secs.");
		
		socket.setSoTimeout(DEFAULT_TIMEOUT+(int)(3*bytes/bandWidth)); 
		classLogger.log(2, CLASS, "-", "forward", 
				"Resetting socket timeout: "+socket.getSoTimeout());
		
		//## placeholder for size returned..
		classLogger.log(2, CLASS, "-", "forward", getId()+"Waiting for xferred bytes confirmation....");
		long size = ccin.readLong();
		classLogger.log(2, CLASS, "-", "forward", getId()+"Read file size: "+size);

		if (size < 0L) 		   
		    throw new FileTransferException("SSLFileTransfer: error during forward", (int)size);
		
		double time = ((double)(System.currentTimeMillis() - startTime))/1000.0;
		double mbytes = (((double)bytes)/(1024*1024));
		classLogger.log(1, CLASS, "-", "forward",
				getId()+"Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
				(mbytes/time)+" MBytes/sec].");
		
		// Tidy up.
		
		try {
		    ccout.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				   getId()+"Error closing socket outputstream: "+iox);
		}
		
		try {
		    ccin.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				    getId()+"Error closing socket inputstream: "+iox);
		}
		
		try {
		    fin.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				    getId()+"Error closing file inputstream: "+iox);
		}
		
		try {
		    socket.close();
		} catch (Exception iox) {
		    classLogger.log(1, CLASS, "-", "send", 
				    getId()+"Error closing socket: "+iox);
		}
		
	    }
	    
	    /** Return a String representation of this Forward.*/
	    public String toString() {
		return "Forward: "+rhost+" : "+rport+" "+srcFilename+" "+destFilename;
	    }

	}

	// -----------------------------------------------------------------------
	//            ASYNCHRONOUS_HANDLER
	// -----------------------------------------------------------------------

	/** Interface to be implemented by any class which wishes to receive notification
	 * that an asynchronous operation has completed.
	 * May be extended to provide information about the progress of the operation.
	 */
	public interface AsynchronousHandler {
	    
	    /** Notify implementor that operation op is complete.*/
	    public void asynchOperationCompleted(Operation op);

	    /** Notify implementor that operation op has failed due to ex.*/
	    public void asynchOperationFailed(Operation op, Exception ex);
	    
	}	
	
    }
  
    /** Run a standalone instance of a SSLFileTransfer.<br><br>
     *
     * <pre>
     *   java ngat.net.SSLFileTransfer <options>
     * </pre>
     * 
     * @see #request
     */
    public static void main(String args[]) {
	
	if (args.length == 0) {
	    usage();
	    System.exit(MISSING_ARG_ERROR);
	}
	    
	int status = execute(args);
	
	System.exit(status);

    }


    /** Execute as client/server/relay.
     * @return Status to use as system-exit code.
     */    
    private static int execute(String args[]) {

	String delim = System.getProperty("cpdelim", "@");

	CommandParser parser = new CommandParser(delim);
	
	//System.err.println("Using: ["+delim+"]");

	try {
	    parser.parse(args);	    
	} catch (ParseException px) {
	    System.err.println("Error parsing args:");	    
	    usage();
	    return PARSE_ERROR;
	}
	
	ConfigurationProperties map = parser.getMap();

	boolean interactive = (map.getProperty("interactive") != null);
    
	// Setup logging to console and generally.
	
	String     logName      = map.getProperty("logger",        DEFAULT_LOGGER);
	int        logLevel     = map.getIntValue("log-level",     1);
	int        conLogLevel  = map.getIntValue("con-log-level", 1);

	Logger     classLogger  = LogManager.getLogger(logName);

	LogHandler console = new ConsoleLogHandler(new BasicLogFormatter(150));
	console.setLogLevel(conLogLevel);
	classLogger.setLogLevel(logLevel);
	classLogger.addHandler(console);
	SSLFileTransfer.setLogger(logName);

	classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main",
			"Creating secure random number generator..");
		
	boolean doserver = map.getBooleanValue("server", false);
	if (doserver) {

	    String  id    = map.getProperty("id",   "SSLSERVER");
	    int     port  = map.getIntValue("port", DEFAULT_PORT);
	    boolean auth  = map.getBooleanValue("auth",  false);
	    boolean relay = map.getBooleanValue("relay", false);
	    int     bl    = map.getIntValue("buffer", DEFAULT_BUFFER_LENGTH);

	    // Key-store.
	    File   keyFile = null;
	    String key     = map.getProperty("key");
	    if (key != null) {
		keyFile = new File(key);
		if (keyFile.isFile()) {		   
		    classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main",
				    "Using key-store: "+keyFile.getPath());
		} else {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Could not locate key-store: "+keyFile.getPath());
		    return MISSING_CERT_ERROR;
		}
	    } else {
		return MISSING_ARG_ERROR;
	    }

	    String pass = map.getProperty("pass");
	    if (pass == null) {
		classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main",
				"No password supplied");
		
		return MISSING_ARG_ERROR;
	    }

	    // Trust-store.
	    File   trustFile = null;
	    String trust     = map.getProperty("trust");	   
	    if (trust != null) {
		trustFile = new File(trust);
		if (trustFile.isFile()) {		   
		    classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main",
				    "Using trust-store: "+trustFile.getPath());
		} else {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Could not locate trust-store: "+trustFile.getPath());
		    return MISSING_CERT_ERROR;
		}
	    } else {
		return MISSING_ARG_ERROR;
	    }

	    // Base-dir.
	    File   baseDir = null; 
	    String base    = map.getProperty("base");
	    //System.err.println("Looking for basedir ["+base+"]");
	    if (base != null) {
		baseDir = new File(base);
		if (! baseDir.isDirectory()) {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Error creating server: Base Directory: ["+
				    baseDir.getPath()+"] is NOT a proper directory");	
		    return BASE_DIR_ERROR;
		}
	    } else {
		return MISSING_ARG_ERROR;
	    }

	    // Create and start the Server.  
	    try {
		
		Server server = createServer(id);
		
		if (baseDir != null)
		    server.setBaseDirectory(baseDir);

		if (keyFile != null && trustFile != null) {
		    server.setKeyFile(keyFile);
		    server.setTrustFile(trustFile);
		    server.setKeyPass(pass);
		}

		server.setBufferLength(bl);

		server.setSingleThreaded(System.getProperty("single.threaded") != null); // defaults to multi-threaded
		server.setSecureIncoming(System.getProperty("secure.incoming") != null); // defaults to non-secure
		server.setSecureOutgoing(System.getProperty("secure.outgoing") != null); // defaults to non-secure
		server.setOldProtocol(System.getProperty("old.protocol")    != null); // defaults to new protocol
		
		server.bind(port, auth, relay);
		
		classLogger.log(INFO, 1, "SSLFileTransfer", "-", "main",
				"Starting server: "+id+" using buffer: "+bl+" on port: "+port);
		
		server.start();
		
		try {
		    server.join();
		} catch (InterruptedException ix) {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main",
				    "Server thread was interrupted");
		}

		return STATUS_SUCCESS;
		
	    } catch (Exception x) {
		classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				"Error starting up server: "+x);		
	    }
	    return SERVER_BIND_ERROR;
	} // doserver
	
	boolean doclient = map.getBooleanValue("client", false);
	if (doclient) {
	    
	    String id   = map.getProperty("id",   "SSLCLIENT");
	    String host = map.getProperty("host", DEFAULT_HOST);    
	    int    port = map.getIntValue("port", DEFAULT_PORT);

	    int    band = map.getIntValue("band", KBPS_RATE); 
	    int    bl   = map.getIntValue("buffer", DEFAULT_BUFFER_LENGTH);

	    Client client = null;

	    // default to non-secure comms.
	    boolean clientSecure = (map.getProperty("secure") != null);

	    if (clientSecure) {
		String sslv = map.getProperty("ssl", "TLS");
		
		// Key-store.
		File   keyFile = null;
		String key     = map.getProperty("key");
		if (key != null) {
		    keyFile = new File(key);
		    if (keyFile.isFile()) {		   
			classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main",
					"Using key-store: "+keyFile.getPath());
		    } else {
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Could not locate key-store: "+keyFile.getPath());
			return MISSING_CERT_ERROR;
		    }
		} else {
		    return MISSING_ARG_ERROR;
		}
		
		// Trust-store.
		File   trustFile = null;
		String trust     = map.getProperty("trust");	   
		if (trust != null) {
		    trustFile = new File(trust);
		    if (trustFile.isFile()) {		   
			classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main",
					"Using trust-store: "+trustFile.getPath());
		    } else {
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Could not locate trust-store: "+trustFile.getPath());
			return MISSING_CERT_ERROR;
		    }
		} else {
		    return MISSING_ARG_ERROR;
		}
		
		// KeyPass.
		String keyPass = map.getProperty("pass");
		if (keyPass == null) {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main",
				    "No password supplied for the private KeyStore.");
		    return MISSING_ARG_ERROR;
		}
		
		classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main", 
				"Creating Secure Client for: "+host+" : "+port);
				
		try {
		    // default to secure client for now.
		    client = new Client(id, host, port, true);
		    
		    client.setSslVersion(sslv);
		    
		    if (keyFile != null && trustFile != null) {		   
			// creates the SSLSocketFactory ready for connect().
			client.initialize(keyFile, keyPass, trustFile);
		    }    	
		    
		    classLogger.log(INFO, 1, "SSLFileTransfer", "-", "main",
				    "Started client: "+id+" For host: "+host+" port: "+port);
		} catch (Exception x) {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Error initializing client: ", null, x);
		    x.printStackTrace(System.err);
		    return CLIENT_INIT_ERROR;	
		}
	  

	    } else {
	
		classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main", 
				"Creating Non-Secure Client for: "+host+" : "+port);
		
		client = new Client(id, host, port, false);

	    }
	    
	    client.setBandWidth(band);
	    client.setBufferLength(bl);

	    classLogger.log(INFO, 1, "SSLFileTransfer", "-", "main",
			    "Client using buffer: "+bl+", assumes bandwidth: < "+band+" KB/sec");
	    
	    // Do a PUT operation.
	    String fput = map.getProperty("put");
	    if (fput != null) {
		classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main", 
				"Attempting PUT");
		String remoteFilename = map.getProperty("remote");
		if (remoteFilename != null) {
		    try {
			client.send(fput, remoteFilename);
		    } catch (FileTransferException fx) {	
			fx.printStackTrace();
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Error sending file: "+fx);
			return 30+fx.getErrorCode(); 
		    } catch (Exception iox) {
			iox.printStackTrace();
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Error sending file: "+iox);
			return TRANSFER_ERROR; 
		    } 
		} else {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "put::No remote file was specified");
		    if (interactive) {
			usage();
			return MISSING_ARG_ERROR;
		    }
		}
		return  STATUS_SUCCESS;
	    }
	    
	    // Do a GET operation.
	    String fget = map.getProperty("get");
	    if (fget != null) {
		classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main", 
				"Attempting GET");
		String localFilename = map.getProperty("local");
		if (localFilename != null) {
		    try {
			client.request(fget, localFilename); 
		    } catch (FileTransferException fx) {	
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Error retreiving file: "+fx);
			return 30+fx.getErrorCode(); 
		    } catch (Exception iox) {
			classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
					"Error retreiving file: "+iox);
			return TRANSFER_ERROR;
		    }
		} else {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "get::No local file was specified");
		    if (interactive) {
			usage();
			return MISSING_ARG_ERROR;
		    }
		}
		return STATUS_SUCCESS;
	    }

	    // Do a FWD operation.
	    String ffwd = map.getProperty("fwd");
	    if (ffwd != null) { 
		classLogger.log(INFO, 2, "SSLFileTransfer", "-", "main", 
				"Attempting FWD");
		String destFilename = map.getProperty("dest");
		if (destFilename == null) { 
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Fwd:: dest file was not specified.");
		    if (interactive)
			usage();
		    return MISSING_ARG_ERROR;
		}
		
		int rport = map.getIntValue("rport", DEFAULT_PORT);
		
		String rhost = map.getProperty("rhost");
		if (rhost == null) {
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Fwd:: rhost was not specified.");
		     if (interactive)
			 usage();
		    return MISSING_ARG_ERROR;
		}
		
		try {
		    client.forward(rhost, rport, ffwd, destFilename);
		} catch (FileTransferException fx) {	
		    fx.printStackTrace();
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Error forwarding file: "+fx);
		    return 30+fx.getErrorCode(); 
		} catch (Exception iox) {
		    iox.printStackTrace();
		    classLogger.log(ERROR, 1, "SSLFileTransfer", "-", "main", 
				    "Error forwarding file: "+iox);
		    return TRANSFER_ERROR;
		}
		return STATUS_SUCCESS;
	    }
	    
	    // None of the request options.
	    if (interactive)
		usage();	    
	    
	    return UNKNOWN_REQUEST_ERROR;
	} // doclient
	
	return STATUS_SUCCESS;
	
    }
    
    /** Print usage information.*/
    private static void usage() {
	System.err.println("Usage: java [-Dcpdelim=<delim-char>] SSLFileTransfer (args)"+
			   "\n"+
			   "\n    delim-char  : Is the charcater used for delimiting any"+
			   "\n                  additional command line arguments."+
			   "\n"+
			   "\n Possible command line arguments are:"+  
			   "\n"+
			   "\n"+
			   "\n   &help       : Print this help information."+
			   "\n"+
			   "\n Server options:"+ 
			   "\n ---------------"+
			   "\n"+
			   "\n   &server [&relay] &id <id> &port <port> [&auth] &key <keyfile> &trust <trust-file> [&base <base-directory>"+
			   "\n"+
			   "\n   Creates a SSLFileTransfer Server with:"+
			   "\n     id         : The ID of this server."+
			   "\n     port       : The port to bind to."+
			   "\n     base-dir   : Path to the base directory for file references."+
			   "\n     key-file   : File containing Key-store."+
			   "\n     trust-file : File containig Trust-store."+
			   "\n     auth       : If the Client should authenticate."+
			   "\n     relay      : Indicates that this server may relay to a remote server."+
			   "\n     logger     : Name of logger."+
			   "\n     log-level  : Logging level (1)."+
			   "\n"+
			   "\n Client options:"+
			   "\n ---------------"+ 
			   "\n"+
			   "\n   &client &id <id> &host <host> &port <port> &key <keyfile> &kpass <pass> &trust <trust-file> [&band <band-width>] (request options)"+ 
			   "\n"+
			   "\n   Creates a SSLFileTransfer Client with:"+
			   "\n     id         : The ID of this client instantiation."+
			   "\n     host       : The remote (Server) host address."+
			   "\n     port       : The remote (Server) port."+ 
			   "\n     key-file   : File containing Key-store."+
			   "\n     pass       : Keystore password ***later this will change to something more secure***."+
			   "\n     trust-file : File containing Trust-store."+
			   "\n     band-width : The expected available bandwidth in (KBytes/sec)."+ 
			   "\n     logger     : Name of logger."+
			   "\n     log-level  : Logging level (1)."+
			   "\n"+
			   "\n   Request options:"+ 
			   "\n   ----------------"+ 
			   "\n"+			 
			   "\n     &put <local-file> -remote <remote-file>"+
			   "\n              : Transfer file to destination file on remote system"+
			   "\n     &get <remote-file> &local <local-file>"+
			   "\n              : Retreive source file from remote system."+
			   "\n     &fwd <local-file> &rhost <rhost> &rport <rport> &dest <dest-file>"+
			   "\n              : Relay file to another host"); 
    }
   

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.1  2004/01/15 16:04:53  snf
/** Initial revision
/** */
