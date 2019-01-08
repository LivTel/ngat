package ngat.net.datalogger;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.util.*;

public class DataReceiverThread extends ControlThread {

    /** The DataLogger to service.*/
    protected DataLogger logger;
    
    /** Port to bind to.*/
    protected int port;
 
    /** Incoming UDP socket.*/
    protected DatagramSocket inSocket;
    
    /** Create a DataReceiverThread to service the speicified DataLogger.
     * @param logger The DataLogger to service.
     */
    public DataReceiverThread(DataLogger logger, int port) throws IOException {
	super("Receiver", true);
	this.logger = logger;	

	inSocket = new  DatagramSocket(port);

	System.err.println("Opened receiver socket on port: "+port);

    }

    /** */
    protected void initialise() {

    }
    
    /** Called repeatedly to check for new data from socket - places in cache. */
    protected void mainTask() {

	try {

	    byte[] buffer = new byte[20000];
	    DatagramPacket packet = new DatagramPacket(buffer, 20000);
	
	    System.err.println("0. Waiting for packet.");
	    inSocket.receive(packet);
	    System.err.println("1. Got packet");
	    buffer = packet.getData();	
	    System.err.println("2. Got data: buffer size: "+buffer.length);
	    ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
	    System.err.println("3. Opened BAIS");
	    ObjectInputStream ois  = new ObjectInputStream(bais);	
	    System.err.println("4. Opened OIS");
	    Object data = ois.readObject();
	    
	    System.err.println("Received object: "+
			       (data != null ? data.getClass().getName() : "NULL")+" : "+data );
	    try {
		logger.push(data);
	    } catch (InterruptedException ix) {}
	    
	} catch (Exception ex) {
	    
	    System.err.println("Exception: "+ex);
	    
	}	    
    }
    
    /** Close down. */
    protected void shutdown() {

	inSocket.close();

    }

}
