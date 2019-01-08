package ngat.util.logging;


import java.io.*;
import java.net.*;
import java.util.*;


public class MulticastLogReader extends Thread {

    byte[] msg;

    DatagramPacket packet;
    
    MulticastSocket socket;

    /** The InetAddress of the Multicast channel to listen to.*/
    InetAddress groupAddress;

    ByteArrayInputStream bais;
    
    ObjectInputStream ois;
    
    /** The port to attach to.*/
    int port;

    /** Holds the set of LogHandlers for this Logger.*/
    protected Set handlers;

   /** This LogHandler reads LogRecords directly from a Multicast channel.
     * @param groupName The IP Multicast address for the Multicast channel.
     * @param port The port on which to listen.
     * @exception IOException If an error occurs joining the channel.
     */
    public MulticastLogReader(InetAddress groupAddress,  int port) throws IOException {
	super("MCastLogReader");
	handlers = Collections.synchronizedSet(new HashSet());
	try {
	    socket = new MulticastSocket(port);
	    socket.joinGroup(groupAddress);
	} catch (IOException e) {
	    System.err.println("Error opening socket: "+e);
	}	
    }
    
    /** Loop round forever reading LogRecords off the MC channel and forwarding
     * them to any handlers.*/
    public void run() {
	while (true) {
	    try {
		byte[] buffer = new byte[2048];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		buffer = packet.getData();	
		bais = new ByteArrayInputStream(buffer);		
		ois  = new ObjectInputStream(bais);		
		LogRecord record = (LogRecord)ois.readObject();
		LogHandler handler = null;
		// Iterate over handlers.
		Iterator it = handlers.iterator();
		while (it.hasNext()) {
		    handler = (LogHandler)it.next();
		    // The handlers may have filters also.
		    if (handler.isLoggable(record))
			handler.publish(record);
		}		
	    } catch (Exception x) {
		System.err.println("Error processing log record: "+x);
	    }
	}	
    }
    
    /** Add the specifed handler to the set of handlers which
     * are notified when this Reader receives a LogRecord.
     * @param handler The handler to add.
     */  
    public void addHandler(LogHandler handler) {
	handlers.add(handler);
    }
    
    /** Remove a handler from the set of handlers.
     * @param handler The handler to remove.
     */  
    public void removeHandler(LogHandler handler) {
	if (handlers.contains(handler))
	    handlers.remove(handler);
    }

    private static void usage() {
	System.err.println("java MulticastLogReader <multicast_address> <port>\n"+
			   "multicast_address: A multicast address e.g. 228.0.0.2\n"+
			   "port: A port number 1024 - 65536.");
    }
    
    /** Create a MulticastLogReader with a Console handler and SimpleFormatter.*/
    public static void main(String args[] ) {
	
	if (args == null || args.length == 0) {
	    usage();
	    return;
	}
	
	InetAddress address;	
	try {
	    address = InetAddress.getByName(args[0]);
	} catch (IOException e) {
	    usage();
	    return;
	}
	
	int port = 5656;
	try {
	    port = Integer.parseInt(args[1]);
	} catch (NumberFormatException e) {
	    usage();
	    return;
	}
	MulticastLogReader reader = null;
	try {
	    reader = new MulticastLogReader(address, port);
	} catch (IOException e) {
	    System.err.println("Error creating MCastLogReader: "+e);
	    return;
	}
	
	LogHandler console = new ConsoleLogHandler(new SimpleLogFormatter());
	console.setLogLevel(Logging.ALL);
	reader.addHandler(console);
	reader.start();
	
    }



}


