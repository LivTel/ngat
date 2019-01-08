package ngat.util.logging;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;
/** This LogHandler broadcasts LogRecords directly to a Multicast channel.
 * It should be invoked using a format similar to the following:-<br><br>
 * relay = new MulticastLogRelay("228.1.1.30", 5566);<br><br>
 * The above statement will create a Relay which passes any received records
 * directly out onto the specified channel as serialized java Objects.
 * The client may write any approporiate LogReader to recieve these and
 * process/display/filter these as required. Any number of clients may
 * simultaneously listen to and read these messages. This Handler does
 * not require a LogFormatter to be specified.
 */
public class MulticastLogRelay extends LogHandler {

    MulticastSocket socket;

    InetAddress groupAddress;
 
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

    int port;
    
    /** This LogHandler broadcasts LogRecords directly to a Multicast channel.
     * @param groupName The IP Multicast address for the Multicast channel.
     * @param port The port on which to broadcast.
     * @exception IOException If an error occurs opening the channel.
     */
    public MulticastLogRelay(String groupName,  int port) throws IOException {
	super(null);
	this.port = port;	
	socket = new MulticastSocket();
	groupAddress = InetAddress.getByName(groupName);	
	socket.joinGroup(groupAddress);
    }
    
    /** Publish (i.e. broadcast) the LogRecord to the attached Multicast channel.
     * @param record The LogRecord to broadcast.*/
    public void publish(LogRecord record) { 
	Date time = new Date();

	DatagramPacket packet;
	
	ByteArrayOutputStream baos;
	
	ObjectOutputStream oos;
    
	byte[] buffer;
	 
	try {	   
	    baos = new ByteArrayOutputStream(10000);	    
	    oos  = new ObjectOutputStream(baos);
	    //System.err.println(sdf.format(time)+ " Built OOS = "+oos);
	    oos.flush();
	    //System.err.println(sdf.format(time)+ " OOS flushed (1) - stream header OK");
	    oos.writeObject(record);
	    //System.err.println(sdf.format(time)+ " OOS wrote object: "+record.toString());
	    oos.flush();
	    oos.close();
	    //System.err.println(sdf.format(time)+ " OOS flushed (2) - record OK");
	    buffer = baos.toByteArray();
	    packet = new DatagramPacket(buffer, buffer.length, groupAddress, port);	    
	    socket.send(packet);	
	    //System.err.println(sdf.format(time)+ " Sent the packet OK was "+packet.getLength()+" data bytes");
	    baos = null;
	    oos  = null;
	} catch (IOException e ) {
	    System.err.println(sdf.format(time)+ "Error relaying record: "+e);
	    e.printStackTrace();
	} 
    }
    
    public void close() {
	try {
	    socket.leaveGroup(groupAddress);
	} catch (IOException e ) {
	    System.err.println("Error leaving MCast group: "+e); 
	}
	socket.close();
    }
    
}
