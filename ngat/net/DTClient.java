package ngat.net;

import ngat.util.*;
import ngat.util.logging.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.net.ssl.*;

/** 
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: DTClient.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/DTClient.java,v $
 * </dl>
 * @author $Author: eng $
 * @version $Revision: 1.1 $
 */
public class DTClient {

    static final int DEFAULT_PORT = 8473;

    static final String DEFAULT_LOGGER = "DT";

    static final String DEFAULT_HOST = "ltccd1";

    static int BUFFER_LENGTH = 4096;

    String id;
    String host;
    int port;
    Socket socket;

    DataInputStream  cin;
    DataOutputStream cout;
    DataOutputStream fout;
    DataInputStream  fin;

    Logger logger;

    boolean secure = false;

    public DTClient(String id, String host, int port, boolean secure) {
	this.id     = id;
	this.host   = host;
	this.port   = port;
	this.secure = secure;
	logger      = LogManager.getLogger(DEFAULT_LOGGER);
    }

    /** Creates an insecure DTClient.*/
    public DTClient(String id, String host, int port) {
	this(id, host, port, false);
    }
    
    public void request(String srcFilename, String destFilename) 
	throws IOException {   
	
	// Open a SocketConnection.
	
	if (secure) {
	    SSLSocketFactory sslFact =
		(SSLSocketFactory)SSLSocketFactory.getDefault();
	    socket =
		sslFact.createSocket(host, port);
	    String [] suites = ((SSLSocket)socket).getSupportedCipherSuites();
	    for (int  i = 0; i < suites.length; i++) {
		System.err.println("Cipher Suite: "+i+" : "+suites[i]);
	    }
	    ((SSLSocket)socket).setEnabledCipherSuites(suites);
	} else {
	    socket = new Socket(host, port); 
	}
	logger.log(1,"Opened socket: ");
	
	
	cout = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); 
	cout.flush();
	logger.log(1,"Opened output stream");
	
	cin = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	logger.log(1,"Opened input:");
	
	String request = "";
	
	request = "get "+srcFilename+"\n";

	cout.writeBytes(request);
	cout.flush();

	logger.log(1,"Sent request "+ request);
	
	long startTime = System.currentTimeMillis();
	
	fout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(destFilename)));
	logger.log(1,"Opened destfile");
		
	byte[] bb = new byte[BUFFER_LENGTH];
	int len = 0;
	int bytes = 0;
	
	long size = cin.readLong();
	if (size < 0L) 
	    throw new IOException("DT request error: DTServer Code: "+size);
	
	while ( ( len = cin.read(bb) ) != -1 ) {		
	    fout.write(bb, 0, len);
	    bytes += len;
	}  
	
	fout.close();
	
	double time = ((double)(System.currentTimeMillis() - startTime))/1000.0;
	double mbytes = (((double)bytes)/(1024*1024));
	System.err.println("Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
			   (mbytes/time)+" MBytes/sec].");
	
    }

    public void send(String srcFilename, String destFilename) 
	throws IOException {   
	
	// Open a SocketConnection.
	
	if (secure) {
	    SSLSocketFactory sslFact =
		(SSLSocketFactory)SSLSocketFactory.getDefault();
	    socket =
		sslFact.createSocket(host, port);
	    String [] suites = ((SSLSocket)socket).getSupportedCipherSuites();
	    for (int  i = 0; i < suites.length; i++) {
		System.err.println("Cipher Suite: "+i+" : "+suites[i]);
	    }
	    ((SSLSocket)socket).setEnabledCipherSuites(suites);
	} else {
	    socket = new Socket(host, port); 
	}
	logger.log(1,"Opened socket: ");
		
	cout = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); 
	logger.log(1,"Opened output");
	
	cin = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	logger.log(1,"Opened input:");
	
	cout.writeBytes("put "+destFilename+"\n");
	cout.flush();
	logger.log(1,"Sent request");
	
	long startTime = System.currentTimeMillis();
	
	fin = new DataInputStream(new BufferedInputStream(new FileInputStream(srcFilename)));
	logger.log(1,"Opened outfile");
		
	byte[] bb = new byte[BUFFER_LENGTH];
	int len = 0;
	int bytes = 0;
	
	// Read the file into buffer and forward.
	while ( ( len = fin.read(bb) ) != -1 ) {		
	    cout.write(bb, 0, len);
	    bytes += len;
	}  
	
	cout.close();

	double time = ((double)(System.currentTimeMillis() - startTime))/1000.0;
	double mbytes = (((double)bytes)/(1024*1024));
	System.err.println("Transferred "+mbytes+" MBytes in "+(time)+" secs - ["+
			   (mbytes/time)+" MBytes/sec].");
	
    }

    public void setLogger(String logName) {
	logger = LogManager.getLogger(logName);
    }
    
    /**
     * Example main program. Call as follows:
     * <pre>
     * java ngat.net.DTClient <options>
     * </pre>
     * This downloads the source filename from the host machine and stores it in the destination filename.
     * It is assumed the server is running on port 8473.
     * @see #request
     */
    public static void main(String args[] ) {
	
	if (args.length == 0) {
	    usage();
	    return;
	}
	   
	CommandParser parser = new CommandParser();
	
	try {
	    parser.parse(args);	    
	} catch (ParseException px) {
	    System.err.println("Error parsing args:");
	    usage();
	    return;
	}

	ConfigurationProperties map = parser.getMap();

	String name = map.getProperty("name", "DTCLIENT");

	String host = map.getProperty("host", DEFAULT_HOST);
	
	String src  = map.getProperty("src");
	if (src == null) {
	    System.err.println("No source file supplied");
	    usage();
	    return;
	}
	
	String dest = map.getProperty("dest");
	if (dest == null) {
	    System.err.println("No destination supplied");
	    usage();
	    return;
	}
	
	int port   = map.getIntValue("port", DEFAULT_PORT);
	
	String logName = map.getProperty("logger", DEFAULT_LOGGER);
	Logger logger = LogManager.getLogger(logName);
	LogHandler console = new ConsoleLogHandler(new SimpleLogFormatter());
	console.setLogLevel(Logging.ALL);
	logger.setLogLevel(Logging.ALL);
	logger.addHandler(console);

	boolean secure = false;
	if (map.getProperty("mode", "STANDARD").equals("SECURE"))
	    secure = true;
	
	DTClient client = new DTClient(name, host, port, secure);
	client.setLogger(logName);

	String op = map.getProperty("op", "NOOP");
	if (op.equals("GET")) {
	    try {
		client.request(src, dest);
	    } catch (IOException iox) {
		System.err.println("Error: GETTING file from remote host: "+iox);
		System.exit(0);	
	    }
	} else if
	    (op.equals("PUT")) {
	    try {
		client.send(src, dest);
	    } catch (IOException iox) {
		System.err.println("Error: SENDING file to remote host: "+iox);
		System.exit(0);	
	    }
	} else {
	    System.err.println("NOOP was specified - did nothing.");
	}
    }

    public static void usage() {
	System.err.println("java ngat.net.DTClient <options>"+
			   "\noptions:-"+
			   "\n  -name <name>             : a name/id for this instantatiation."+
			   "\n  -host <host-name>        : the remote host name (default ltccd1)."+
			   "\n  -src  <source-file-name> : (remote) source file path."+
			   "\n  -dest <dest-file-name>   : (local) destination file path."+
			   "\n  -port <port>             : remote port (default 8473)."+
			   "\n  -log  <logger-name>      : send logging to the named logger (default DT)."+
			   "\n  -mode <security>         : use SECURE for SSL connection."+
			   "\n  -op   <operation>        : PUT or GET (later FWD forward, RGET remoteget" +
			   "\n  -rate <xfer rate>        : Xfer rate KBytes/sec for GET");
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2004/01/15 16:04:53  snf
/** Initial revision
/** */
