package ngat.util.logging;

import java.io.*;
import java.util.*;
import java.text.*;
import java.net.*;

public class SyslogHandler extends LogHandler {

    public static int SYSLOG_PORT = 514;

    public SimpleDateFormat sysf = new SimpleDateFormat("MMM dd HH:mm:ss");

    private DatagramSocket socket;

    private String logHost;

    private int logPort = SYSLOG_PORT;

    private String host;

    // counts errors.
    private int errc = 0;

    /** Create a new SyslogHandler.
     * @throws Exception if there is a problem binding.
     */
    public SyslogHandler(String logHost, int logPort) throws Exception{
	
	super(new SyslogFormatter());
	// bind anywhere available.
	socket = new DatagramSocket();

	this.logHost = logHost;
	this.logPort = logPort;

	host = InetAddress.getLocalHost().getHostName();

    }

    /** Overridden to write a formatted LogRecord to syslog via UDP.*/
    public void publish(LogRecord record) {

	// interpret the pri and tag fields from record content..
	int    pri = 14; // assume local0.level messages for now
	String tag = record.getSource();

	String message = "<"+pri+">"+sysf.format(new Date())+" "+host+" "+tag+": "+formatter.format(record);
	
	System.err.println("Syslog Message: ["+message+"]");
	
	byte[] buffer = new byte[message.length()];
	
	try {

	    ByteArrayOutputStream baos = new ByteArrayOutputStream(buffer.length);
	    
	    DataOutputStream dos = new DataOutputStream(baos);
	    
	    dos.writeBytes(message);
	    buffer = baos.toByteArray();
	    
	    InetAddress address = InetAddress.getByName(logHost);
	    
	    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, logPort);
	    
	    socket.send(packet);
	} catch (Exception e) {
	    errc++;
	    if (errc < 10)
		e.printStackTrace();
	}


    }

    public void close() {

	try {
	    socket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}

