package ngat.net;

import ngat.util.*;
import ngat.util.logging.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
/** 
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: TitClient.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/TitClient.java,v $
 * </dl>
 * @author $Author: eng $
 * @version $Revision: 1.1 $
 */
public class TitClient {

    static final int DEFAULT_PORT = 8473;

    static final String DEFAULT_LOGGER = "TIT";

    static final String DEFAULT_HOST = "ltccd1";

    static int BUFFER_LENGTH = 4096;

    String id;
    String host;
    int port;
    Socket socket;

    DataInputStream cin;
    PrintStream cout;
    DataOutputStream fout;

    Logger logger;

    public TitClient(String id, String host, int port) {
	this.id = id;
	this.host = host;
	this.port = port;
	logger = LogManager.getLogger(DEFAULT_LOGGER);
    }

    public void request(String srcFilename, String destFilename) 
	throws IOException {   
	
	// Open a SocketConnection.
	
	socket = new Socket(host, port); 
	logger.log(1,"Opened socket: ");
	
	
	cout = new PrintStream(socket.getOutputStream()); 
	logger.log(1,"Opened output");
		
	cin = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	logger.log(1,"Opened input:");
	
	cout.println("get "+srcFilename);
	logger.log(1,"Sent request");
	
	long startTime = System.currentTimeMillis();
	
	fout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(destFilename)));
	logger.log(1,"Opened outfile");
		
	byte[] bb = new byte[BUFFER_LENGTH];
	int len = 0;
	int bytes = 0;
	
	long size = cin.readLong();
	if (size < 0L) 
	    throw new IOException("TIT request error: "+size);
	
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

    public void setLogger(String logName) {
	logger = LogManager.getLogger(logName);
    }
    
    /**
     * Example main program. Call as follows:
     * <pre>
     * java ngat.net.TitClient <options>
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

	String name = map.getProperty("name", "TITCLIENT");

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
	
	String logger = map.getProperty("logger", DEFAULT_LOGGER);
	
	TitClient client = new TitClient(name, host, port);
	client.setLogger(logger);
	try {
	    client.request(src, dest);
	} catch (IOException iox) {
	    System.err.println("Error: getting remote file: "+iox);
	    System.exit(0);	
	}
    }

    public static void usage() {
	System.err.println("java ngat.net.TitClient <options>"+
			   "\noptions:-"+
			   "\n  -name <name>             : a name/id for this instantatiation."+
			   "\n  -host <host-name>        : the remote host name (default ltccd1)."+
			   "\n  -src  <source-file-name> : (remote) source file path."+
			   "\n  -dest <dest-file-name>   : (local) destination file path."+
			   "\n  -port <port>             : remote port (default 8473)."+
			   "\n  -log  <logger-name>      : send logging to the named logger (default TIT).");
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2004/01/15 16:04:53  snf
/** updated
/**
/** Revision 1.1  2001/07/11 10:50:28  snf
/** Initial revision
/** */
