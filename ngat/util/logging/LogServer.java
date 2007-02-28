package ngat.util.logging;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
import java.applet.*;


import ngat.astrometry.*;
import ngat.util.*;

public class LogServer extends Thread {
    
    int port;
    
    DataOutputStream out = null;
    
    BufferedReader in = null;
    
    public LogServer(int port) {
	this.port = port;
    }
    
    public static void main(String args[]) {
	Logger deflog = LogManager.getLogger("DEFAULT");
	deflog.setLogLevel(3);
	LogHandler console = new ConsoleLogHandler(new SimpleLogFormatter());
	console.setLogLevel(3);
	deflog.addHandler(console);
	try {
	    new LogServer(Integer.parseInt(args[0])).start();
	}  catch (Exception e) {
	    System.err.println("USAGE: java LogServer <port> : "+e);
	    System.exit(1);
	}
	
    }
    
    public void run() {
	ServerSocket server = null;
	Socket       socket = null;
	
	try {
	    server= new ServerSocket(port);
	    //System.err.println("Started LogServer Bound to port: "+port);
	} catch (IOException e) {
	    System.err.println("Error starting LogServer: "+e);
	    System.exit(1);
	}
	
	while (true) {
	    
	    try {
		socket = server.accept(); 
		//System.err.println("Opened connection to: "+socket.getInetAddress().getHostName()+
		//	   " : "+socket.getPort());
	    } catch (IOException e) { 
		System.err.println("SS.accept::Error opening connection: "+e);
		continue;
	    }
	    
	    try {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//System.err.println("Opened input stream");
		
		out = new DataOutputStream(socket.getOutputStream()); 
		//System.err.println("Opened output stream");		
	    } catch (IOException e) {
		System.err.println("Streams::Error opening connection: "+e);
		continue;
	    }

	    // Readin 1 line.	
	    CommandParser parser = null;
	    try {
		String line = in.readLine();
		//System.err.println("Received: ["+line+"]");

		// Parse and process the log message.
		parser = new CommandParser();
		try {
		    parser.parse(line);		    
		} catch (ParseException px) {
		    System.err.println("Parsing line: "+px);
		    break;
		}		
	    } catch (IOException e) {
		System.err.println("Read::Error opening connection: "+e);
		break;
	    }
	   
	    ConfigurationProperties map = parser.getMap();

	    String strLog   = map.getProperty("logger", "anon");
	    String strClass = map.getProperty("class", "X");
	    String strMeth  = map.getProperty("method", "X");
	    String strName  = map.getProperty("name", "X");
	    int    iLevel   = map.getIntValue("level", 1);
	    String strMsg   = map.getProperty("msg", "X");

	    Logger logger = LogManager.getLogger(strLog);
	    logger.log(iLevel, strClass, strName, strMeth, strMsg);

	    // Close the Socket.
	    try {
		socket.close();
		//System.err.println("Closed socket:");
	    } catch (IOException e) {
		System.err.println("Error closing connection: "+e);
		break;
	    }
	}
	
    }
       
}
