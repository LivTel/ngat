package ngat.net.datalogger;

import ngat.net.*;
import ngat.net.camp.*;
import ngat.message.base.*;
import ngat.message.RCS_TCS.*;
import ngat.message.GUI_RCS.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class DataCampSender implements DataLoggerUpdateListener {

    String host;
    int port;
    long timeout;
    Vector wants;

    public static int count = 0;

    /** Create a DataSender for the specified host and port.*/
    public DataCampSender(Vector wants, String host, int port, long timeout) throws IOException {
	this.wants = wants;
	this.host    = host;
	this.port    = port;
	this.timeout = timeout;
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

	count++;

	// Noone wants null data !
	if (data == null)
	    return;

	// If we want (anything and this is on our list) or we want everything..
	if (wants != null && 
	    (wants.contains(data.getClass()) ||
	     wants.isEmpty())) {
	    
	    IConnection conn = null;
	    
	    try {
		conn = new SocketConnection(host, port);
	    } catch (Exception e) {
		e.printStackTrace();
		return;
	    }
	    
	    TELEMETRY_UPDATE telem = new TELEMETRY_UPDATE("TestUpdateClient");
	    telem.setData((TelemetryInfo)data);

	    long oct = System.currentTimeMillis();
	    try {
		conn.open();
	    } catch (Exception cx) {
		System.err.println("DLogCamp::["+count+"] Error opening TCP/camp connection to: "+host+":"+port+" after "+(System.currentTimeMillis()-oct)+"ms");
		cx.printStackTrace();
		return;
	    }
	  
	    try {
		long sst = System.currentTimeMillis();
		try {
		    System.err.println("DLogCamp::["+count+"] Sending TCP/camp packet to: "+host+":"+port+", to="+timeout+" ms");
		    conn.send(telem);		    
		} catch (Exception iox) {
		    System.err.println("DLogCamp::["+count+"] Error sending TCP/camp packet to: "+host+":"+port+" after "+(System.currentTimeMillis()-sst)+"ms");
		    iox.printStackTrace();
		    return;
		}
		
		long srt = System.currentTimeMillis();
		try {
		    System.err.println("DLogCamp::["+count+"] Waiting TCP/camp reply from: "+host+":"+port+", to="+timeout+" ms");
		    Object obj = conn.receive(timeout);
		    System.err.println("DLogCamp::Obj recvd: "+(obj != null ? obj.getClass().getName() : "NULL"));
		    COMMAND_DONE update = (COMMAND_DONE)obj;	   
		    
		} catch (Exception cx) {
		    System.err.println("DLogCamp::["+count+"] Error getting TCP/camp reply from: "+host+":"+port+" after "+(System.currentTimeMillis()-srt)+"ms");
		    cx.printStackTrace();
		    return;
		}
	    } finally {
		try {
		    conn.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	    
	}
    }

}
