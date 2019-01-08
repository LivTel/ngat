package ngat.net.datalogger;

import ngat.net.datalogger.*;
import ngat.message.RCS_TCS.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class DataForwarder implements DataLoggerUpdateListener {

    DatagramSocket socket;
    String host;
    int port;
    InetAddress address;
    Vector wants;

    /** Create a DataForwarder for the specified host and port.*/
    public DataForwarder(Vector wants, String host, int port) throws IOException {
	this.wants = wants;
	this.host  = host;
	this.port  = port;

	socket = new DatagramSocket();
	address = InetAddress.getByName(host);

	//System.err.println("DGSocket send buffer size: "+socket.getSendBufferSize());
	
    }

    /** We forward as follows:-
     *
     * Data is null..                     (Ignore)
     *
     * Wants not set..                    (Nothing to send).
     * Wants is set but empty..           (Send everything).
     * Wants has a list and data on list..(Send).
     *
     */
    public void dataUpdate(Object data) {

	// Noone wants null data !
	if (data == null)
	    return;

	// If we want (anything and this is on our list) or we want everything..
	if (wants != null && 
	    (wants.contains(data.getClass()) ||
	     wants.isEmpty())) {
	    
	    try {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);	    
		ObjectOutputStream    oos  = new ObjectOutputStream(baos);
		//System.err.println(sdf.format(time)+ " Built OOS = "+oos);
		oos.flush();
		//System.err.println(sdf.format(time)+ " OOS flushed (1) - stream header OK");
		oos.writeObject(data);
		
		//System.err.println("DFwd::Wrote object: "+(data != null ? data.getClass().getName() : "NULL"));
	
		oos.flush();
		oos.close();
		//System.err.println(sdf.format(time)+ " OOS flushed (2) - record OK");
		byte[] buffer = baos.toByteArray();
		
		//System.err.println("DFwd::Sending buffer: size: "+buffer.length);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);	    
		socket.send(packet);
		
		System.err.println("DFwd::Forwarded packet to: "+address+":"+port);
		
	    } catch (Exception e) {
		e.printStackTrace();	
	    }
	}
    }

}
