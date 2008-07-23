package ngat.net;

import java.io.*;
import java.net.*;

/** 
 * Implements an abstraction of a SlotBuffer connection between 2 threads.
 * <br><br>
 * $Id: SlotConnection.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class SlotConnection implements IConnection {

    /** Object which handles the server end of the Slot. */
    protected SlotServer server;
    
    /** Reference to the connection thread spawned by the server.*/
    protected SlotServerConnectionThread ssct;

    /** The SlotBuffer used for this connection to read from.*/
    protected SlotBuffer inSlot;
    
    /** The SlotBuffer used for this connection to send to.*/
    protected SlotBuffer outSlot;
    
    /** Indicates that the connection is alive.*/
    protected boolean open;
    
    protected String cid;
    
    /** Create a SlotConnection on the specified Server. This
     * constructor should be used by a client to attach to a server.
     * @param server The SlotServer to connect to.
     * @param capacity The size of buffers to use.
     */
    public SlotConnection(SlotServer server, int capacity) {
	this.server = server;
	outSlot = new SlotBuffer(capacity);
	inSlot  = new SlotBuffer(capacity);
	open = false;
    }

    /** Create a SlotConnection using the specified SlotBuffers. This
     * constructor is intended to be used at the server end of a connection.
     * @param inSlot The SlotBuffer to read from - created by a client.
     * @param outSlot The SlotBuffer to send to - created by a client.*/
    public SlotConnection(SlotBuffer inSlot, SlotBuffer outSlot) {
	this.inSlot  = inSlot;
	this.outSlot = outSlot;
	open = true;
    }
    

    /** Set the id for this connection.*/
    public void setConnectionName(String cid) {
	this.cid = cid;
    }



    /** Establishes the SlotConnection to the server and forces the
     * Server to spawn a ConnectionThread. 
     * @exception ConnectException If used at a server end on an already
     * open connection.*/
    public void open() throws ConnectException {
	if (open) throw new ConnectException("Connection already open:");
	
	// Make the server spawn a ConnectionThread.
	// Note. The specific SlotServer subclass should have set the
	// ProtocolImpl that this ServerConnectionThread will use.
	ssct = server.createServerConnectionThread(outSlot, inSlot);
	ssct.start();
	open = true;

    }

    public void send(Object data, long timeout) throws IOException {
	try {
	    outSlot.add(data, timeout);
	} catch (InterruptedException e) {
	    throw new IOException("Interrupted while writing to slot with timeout: "+e);
	}    
    }


    public void send(Object data) throws IOException {
	try {
	    outSlot.add(data);
	} catch (InterruptedException e) {
	    throw new IOException("Interrupted while writing to slot: "+e);
	}    
    }

    public Object receive() throws IOException {
	try {
	    return inSlot.remove();
	} catch (InterruptedException e) {
	    throw new IOException("Interrupted while reading from slot: "+e);
	}
    }    

    /** Receive data from other end of connection but timeout
     * if not received within the specified period. 
     * @param timeout The timeout interval (msecs).  
     * @exception IOException If any IO related problems occur or the
     * thread is Interrupted while reading from the buffer..
     * @exception InterruptedIOException If the connection times out.*/
    public Object receive(long timeout) throws IOException {
	try {
	    return inSlot.remove(timeout);
	} catch (InterruptedException e) {
	    throw new IOException("Interrupted while reading from slot: "+e);
	}


    }

    /** Try to close all streams.*/
    public void close() {
	open = false;
	// Dont really care if it does.
	if (ssct != null)
	    ssct.terminate();
	ssct = null;
    }
    
    /** Returns true if the connection is live.*/
    public boolean isOpen() { return open;}
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.8  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.7  2004/01/15 16:04:53  snf
/** updated
/**
/** Revision 1.6  2001/02/05 10:40:32  snf
/** Added SlotBuffer timeout to remove(long).
/**
/** Revision 1.5  2000/12/20 09:43:55  snf
/** Added timed receive.
/**
/** Revision 1.4  2000/12/12 12:54:16  snf
/** Set the SCT running.
/**
/** Revision 1.3  2000/12/12 12:35:21  snf
/** *** empty log message ***
/**
/** Revision 1.2  2000/12/12 12:34:12  snf
/** Added extra buffer in/out.
/**
/** Revision 1.1  2000/12/12 10:50:16  snf
/** Initial revision
/** */
