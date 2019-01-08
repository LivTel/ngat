package ngat.util.logging;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

import ngat.util.*;

public class LogClient {
       
    String host;

    int    port;

    Socket socket = null;

    DataOutputStream out = null;
 
    public static void main(String args[]) {

	if (args.length == 0) {
	    usage();
	    return;
	}

	String logger = null;
	String clazz  = null;
	String name   = null;
	String method = null;
	int    level  = 1;
	String msg    = null;

	CommandParser parser = new CommandParser();
	try {
	    parser.parse(args);
	} catch (ParseException px) {
	    System.err.println("Error parsing command arguments: "+px);
	    usage();
	    return;
	}
	
	ConfigurationProperties map = parser.getMap();
	
	try {
	    logger = map.getProperty("L", "ANON");
	    clazz  = map.getProperty("C", "X");	   
	    name   = map.getProperty("n", "X");
	    method = map.getProperty("m", "X");
	    msg    = map.getProperty("T", "X");
	    level  = map.getIntValue("l", 1);
	} catch (Exception e) {}
	
	String host = map.getProperty("h", "ltccd1");
	int    port = 7600;
	try {
	    port = map.getIntValue("p", 8010);
	} catch (Exception e) {}
	
	
	LogClient client = new LogClient(host, port);
	try {
	    client.connect();
	} catch (IOException e) {
	    System.err.println("Error opening connection: "+e);     
	    return;
	}

	try {
	    client.send(" -logger "+ logger+
			" -level "+  level+
			" -class "+  clazz+
			" -name "+   name+
			" -method "+ method+
			" -msg "+    msg);
	} catch (IOException e) {
	    System.err.println("Error sending text: "+e);     
	    return;
	}

	client.close();

    }

    public static void usage() {
	System.err.println("USAGE: sendlog -[LlCnmThp]"+
			   "\n L <logger>    : Name of Logger to use."+
			   "\n l <level>     : Logging level [1-10]."+
			   "\n C <class>     : Name of script/utility."+
			   "\n n <id>        : Identity of caller."+
			   "\n m <method/fn> : Function name of calling module."+
			   "\n T <Text>      : Message text."+
			   "\n h <host>      : Name / address of LogServer host."+
			   "\n p <port>      : Port number on LogServer."+
			   "\n\n E.g. sendlog -L ERROR -l 2 -C Admin -n snf -p 8560 -T $status");			   
    }
    
    public LogClient(String host, int port) {
	this.host = host;
	this.port = port;
    }
    
    protected void connect() throws IOException {			  
	socket = new Socket(host, port);
	//System.err.println("Opened connection to: "+host+" : "+port);
	out = new DataOutputStream(socket.getOutputStream()); 
	//System.err.println("Opened output stream");	
    }
    
    
    protected void send(String text) throws IOException {	
	out.writeBytes(text);
    }
    
    protected void close() {
	try {
	    socket.close();	     
	} catch (IOException e) {
	    //System.err.println("Error closing socket: "+e);
	    socket = null;
	    return;
	}				
    }

}

