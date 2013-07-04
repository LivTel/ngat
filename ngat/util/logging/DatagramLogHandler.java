package ngat.util.logging;

import java.io.*;
import java.net.*;

public class DatagramLogHandler extends LogHandler implements ExtendedLogHandler {

    public static final int C_PACKET_TYPE   = 1010;
    public static final int JSO_PACKET_TYPE = 2020;
    
    DatagramSocket socket;

    InetAddress address;

    int port;


    public DatagramLogHandler(String host,  int port) throws IOException {
	super(null);
	this.port = port;	
	socket = new DatagramSocket(0);
	address = InetAddress.getByName(host);	
    }
    
    //    public void setLogLevel(int level) { this.logLevel = logLevel;}

    public void publish(LogRecord record) {

	try {
	    send(record);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /** Override to write a formatted LogRecord to the output stream.*/
    public void publish(ExtendedLogRecord record) {
	try {
	    send(record);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public boolean isLoggable(ExtendedLogRecord record) {
	
	return (logLevel == Logging.ALL || record.getLevel() <= logLevel);
    }
    
    /** Send a packet.*/
    private void send(Object data) throws Exception {
	ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
	DataOutputStream dos = new DataOutputStream(baos);

	dos.writeInt(JSO_PACKET_TYPE);

	ObjectOutputStream oos = new ObjectOutputStream(dos);
	oos.flush();
	oos.writeObject(data);
	oos.flush();
	oos.close();
	
	byte[] buffer = baos.toByteArray();
	
	DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
	socket.send(packet);
	
    }
    
    public void close() {
        socket.close();
    }
    
}
