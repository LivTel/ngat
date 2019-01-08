package ngat.net.camp;

import java.util.*;
import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.base.*;

/** Implements the CAMP protocol as client and response handler.*/
public abstract class CAMPClient implements Runnable, CAMPResponseHandler {

    public static final long DEFAULT_TIMEOUT = 20000L;

    /** Timeout to use (msec).*/
    protected long timeout;

    /** Connection factory.*/
    protected ConnectionFactory connFactory;

    /** Connection ID.*/
    protected String connId;

    /** Connection from factory.*/
    protected IConnection conn = null;

    /** Command to end.*/
    protected COMMAND command;

    public CAMPClient(ConnectionFactory connFactory, String connId, COMMAND command) {
	this.connFactory = connFactory;
	this.connId      = connId;
	this.command     = command;
	timeout = DEFAULT_TIMEOUT;
    }

    public CAMPClient(IConnection conn, COMMAND command) {
	this.conn     = conn;
	this.command  = command;
	timeout = DEFAULT_TIMEOUT;
    }

    public void setConnection(IConnection conn) { this.conn = conn; }

    public void setTimeout(long timeout) { this.timeout = timeout; }

    public void run() {

	if (conn == null) {

	    try {
		conn = connFactory.createConnection(connId);
	    } catch (UnknownResourceException rx) {
		conn = null;
	    }
	}

	if (conn == null) {
	    failed(new IOException("No connection resource: "+connId), conn);
	    return;
	}

	try {
	    conn.open();
	} catch (ConnectException cx) {
	    failed(cx, conn);
	    return;
	}
	
	try {
	    conn.send(command);
	} catch (IOException iox) {
	    failed(iox, conn);
	    return;
	}
	
	try {
	    Object obj = conn.receive(timeout);
	    System.err.println("Object recvd: "+(obj != null ? obj.getClass().getName() : "NULL"));
	    COMMAND_DONE update = (COMMAND_DONE)obj;	   
	    handleUpdate(update, conn);
	} catch (ClassCastException cx) {
	    failed(cx, conn);
	    return;
	} catch (IOException iox) {
	    failed(iox, conn);
	    return;
	} finally {
	    if (conn != null)
		conn.close();
	    conn = null;
	}

    }

}
