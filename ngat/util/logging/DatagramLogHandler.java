package ngat.util.logging;

import java.io.*;
import java.net.*;

public class DatagramLogHandler extends LogHandler {
    
    DatagramSocket socket;

    InetAddress address;

    int port;

    public DatagramLogHandler(String host,  int port) throws IOException {
	super(null);
	this.port = port;	
	socket = new DatagramSocket(0);
	address = InetAddress.getByName(host);	
    }
    
    public void publish(LogRecord record) {

	try {
	    send(record);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

   
    private void send(Object data) throws Exception {
	ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
	ObjectOutputStream    oos  = new ObjectOutputStream(baos);
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
