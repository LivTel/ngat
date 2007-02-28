package ngat.util.logging;


import java.io.*;
import java.net.*;
import java.util.*;


public class MulticastObjectReader extends Thread {

    byte[] msg;

    MulticastSocket socket;

    /** The InetAddress of the Multicast channel to listen to.*/
    InetAddress groupAddress;

   
    /** The port to attach to.*/
    int port;

    /** Holds the set of MulticastUpdateListeners for this Reader.*/
    protected Set listeners;

   /** This Reader reads Serializable Objects directly from a Multicast channel.
     * @param groupName The IP Multicast address for the Multicast channel.
     * @param port The port on which to listen.
     * @exception IOException If an error occurs joining the channel.
     */
    public MulticastObjectReader(InetAddress groupAddress,  int port) throws IOException {
	super("MCastObjectReader");
	listeners = Collections.synchronizedSet(new HashSet());
	try {
	    socket = new MulticastSocket(port);
	    socket.joinGroup(groupAddress);
	} catch (IOException e) {
	    System.err.println("Error opening socket: "+e);
	}	
    }
    
    /** Loop round forever reading Serializable Objects off the MC channel and forwarding
     * them to any listeners.*/
    public void run() {
	while (true) {
	    try {
		byte[] buffer = new byte[10000];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		System.err.println("1. Got packet");
		buffer = packet.getData();	
		System.err.println("2. Got data: buffer size: "+buffer.length);
		ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
		System.err.println("3. Opened BAIS");
		ObjectInputStream ois  = new ObjectInputStream(bais);	
		System.err.println("4. Opened OIS");
		Object object = ois.readObject();
		System.err.println("5. Read Object");
		MulticastUpdateListener listener = null;
		// Iterate over handlers.
		Iterator it = listeners.iterator();
		while (it.hasNext()) {
		    listener = (MulticastUpdateListener)it.next();
		    // The Listeners may have filters.
		    //if (listener.isReadable(record))
		    System.err.println("6. Forwarding to listener");
		    listener.objectReceived((Serializable)object);
		}		
	    } catch (Exception x) {
		System.err.println("Error processing object record: "+x);
	    }
	}	
    }
    
    /** Add the specifed handler to the set of handlers which
     * are notified when this Reader receives a LogRecord.
     * @param handler The handler to add.
     */  
    public void addUpdateListener(MulticastUpdateListener listener) {
	listeners.add(listener);
    }
    
    /** Remove a handler from the set of handlers.
     * @param handler The handler to remove.
     */  
    public void removeUpdateListener(MulticastUpdateListener listener) {
	if (listeners.contains(listener))
	    listeners.remove(listener);
    }
    
    private static void usage() {
	System.err.println("java MulticastObjectReader <multicast_address> <port>\n"+
			   "multicast_address: A multicast address e.g. 228.0.0.2\n"+
			   "port: A port number 1024 - 65536.");
    }
    
    /** Create a MulticastObjectReader, logging to System.err.*/
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
	MulticastObjectReader reader = null;
	try {
	    reader = new MulticastObjectReader(address, port);
	} catch (IOException e) {
	    System.err.println("Error creating MultiCastObjectReader: "+e);
	    return;
	}
	reader.addUpdateListener(new UpdateListener());
	reader.start();
	
    }
    
    /** Local UpdateListener to use during standalone execution.*/
    static class UpdateListener implements MulticastUpdateListener {
	
	public void objectReceived(Serializable object) {
	    System.err.println("Object: "+object);	    
	}

    }

}


