package ngat.net;

import java.io.*;
import java.net.*;

/** 
 * Implements an abstraction of a Pipeline connection between 2 threads.
 *
 * $Id: PipeConnection.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public class PipeConnection implements IConnection {

    /** Object which handles the server end of the pipe. */
    protected PipedServer server;
    
    /** Reference to the connection thread spawned by the server.*/
    protected PipedServerConnectionThread psct;
    
    /** The output stream for sending data to.*/
    protected ObjectOutputStream os;
    
    /** The input stream for receiving data. */ 
    protected ObjectInputStream is;

    /** Indicates that the connection is alive.*/
    protected boolean open;
    
    protected String cid;

    public PipeConnection(PipedServer server) {
	this.server = server;
	open = false;
    }
    
    /** Set the id for this connection.*/
    public void setConnectionName(String cid) {
	this.cid = cid;
    }

    /** Establishes the PipeConnection to the server and forces the
     * Server to spawn a ConnectionThread.
     * --THERE IS NO ERROR HANDLING HERE YET */
    public void open() throws ConnectException {
	// create the Stream at each end and link them.
	
	PipedInputStream  pipeis = new PipedInputStream();
	PipedOutputStream pipeos = new PipedOutputStream();

	// Make the server spawn a ConnectionThread.
	// Note. The specific PipeServer subclass should have set the
	// ProtocolImpl that this ServerConnectionThread will use.
	psct = server.createServerConnectionThread(this);
	
	try {
	    pipeis.connect(psct.getPipedOutputStream());
	    pipeos.connect(psct.getPipedInputStream());
	} catch (IOException e) {
	    throw new ConnectException("Unable to connect to server: "+e);
	}
	
	try {
	    os = new ObjectOutputStream(pipeos);
	    os.flush();
	} catch (IOException e) {
	    throw new ConnectException("Error opening output stream to server: "+e);
	}

	// Tell SCT to create its input stream before we flush header.
	try {
	    psct.createObjectInputStream();
	} catch (IOException e) {
	    throw new ConnectException("Error opening input stream at server: "+e);
	}

	// Tell the SCT to create its output stream and flush header.
	try {	
	    psct.createObjectOutputStream();
	} catch (IOException e) {
	    throw new ConnectException("Error opening output stream at server: "+e);
	}

	try {	    
	    is = new ObjectInputStream(pipeis);
	    // Tell the SCT to create its output stream and flush header.
	} catch (IOException e) {
	    throw new ConnectException("Error opening input stream from server: "+e);
	}
	

	// Make the server connection thread start -  the connection is live.
	psct.start();
	
	open = true;

    }

    public void send(Object data) throws IOException {
	os.writeObject(data);
	os.flush();
    }

    public void send(Object data, long timeout) throws IOException {
	os.writeObject(data);
	os.flush();
    }

    public Object receive() throws IOException {
	try {
	    return is.readObject();
	} catch (ClassNotFoundException c) {
	    throw new IOException("Error reading object: "+c);
	}
    }
    
    public Object receive(long timeout) throws IOException {
	try {
	    return is.readObject();
	} catch (ClassNotFoundException c) {
	    throw new IOException("Error reading object: "+c);
	}
    }



    /** Try to close all streams.*/
    public void close() {
	open = false;
	try {
	    os.close();
	} catch (IOException e) {}
	os = null;
	try {
	    is.close();
	} catch (IOException e) {}
	is = null;
	// Dont really care if it does.
	psct.terminate();
	psct = null;
    }

    /** Returns true if the connection is live.*/
    public boolean isOpen() { return open;}

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2006/11/23 11:43:17  snf
/** test
/**
/** Revision 1.3  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.2  2000/12/06 14:32:17  snf
/** changed connect to open.
/**
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
