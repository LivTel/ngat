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
	    
	    try {
		conn.open();
	    } catch (ConnectException cx) {
		cx.printStackTrace();
		return;
	    }
	    
	    try {
		conn.send(telem);
	    } catch (IOException iox) {
		iox.printStackTrace();
		return;
	    }
	    
	    try {
		Object obj = conn.receive(timeout);
		System.err.println("DLog:TelemSender::Obj recvd: "+(obj != null ? obj.getClass().getName() : "NULL"));
		COMMAND_DONE update = (COMMAND_DONE)obj;	   
		
	    } catch (ClassCastException cx) {
		cx.printStackTrace();
		return;
	    } catch (IOException iox) {
		iox.printStackTrace();
		return;
	    }
	    
	}
    }

}
