package ngat.util.logging;

import java.io.*;
import java.net.*;

public class MulticastLogHandler extends LogHandler {

    byte[] msg;

    DatagramPacket packet;
    
    DatagramSocket socket;

    InetAddress groupAddress;

    int port;

    public MulticastLogHandler(String groupName,  int port, LogFormatter formatter) throws IOException {
	super(formatter);
	this.port = port;	
	socket = new DatagramSocket(0);
	groupAddress = InetAddress.getByName(groupName);	
	send(formatter.getHead());
    }
    
    public void publish(LogRecord record) {
	send(formatter.format(record));
    }

    public void publish(ExtendedLogRecord record) {
	send(formatter.format(record));
    }
    public void close() {
	send(formatter.getTail());
	send("BYE");
	socket.close();
    }
    
    protected void send(String message) {
	msg = message.getBytes();
	packet = new DatagramPacket(msg, msg.length, groupAddress, port);
	try {
	    socket.send(packet);	
	} catch (IOException e ) {}
    }
    
}
