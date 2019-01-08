package ngat.net;

import java.io.*;

/** 
 * Temporary class to implement JMS protocol SCT (server side).
 * <br><br>
 * $Id$
 */
public class PipedServerConnectionThread extends ServerConnectionThread {

    /** Reference to the server which created this connection.*/
    protected PipedServer server;

    /** The incoming pipe.*/
    protected PipedInputStream pipeis;
    
    /** The outgoing pipe.*/
    protected PipedOutputStream pipeos;
    
    /** The output stream for sending data to.*/
    protected ObjectOutputStream os;
    
    /** The input stream for receiving data. */ 
    protected ObjectInputStream is;

    /** Creates the server's end of the communication pipes.*/
    public PipedServerConnectionThread(PipedServer server, ProtocolImpl implementor) {
	super(implementor);
	this.server = server;
	pipeis = new PipedInputStream();
	pipeos = new PipedOutputStream();
    }
    
    /** Returns the PipedOutputStream in use.*/
    public PipedOutputStream getPipedOutputStream() { return pipeos;}
    
    /** Returns the PipedInputStream in use.*/
    public PipedInputStream  getPipedInputStream()  { return pipeis;}
    
    /** Creates an ObjectInputStream on the incoming pipe.*/
    public void createObjectInputStream() throws IOException {
	is = new ObjectInputStream(pipeis);
    }
   
    /** Creates an ObjectOutputStream on the outgoing pipe and
     * flushes the stream header.*/
    public void createObjectOutputStream() throws IOException {
	os = new ObjectOutputStream(pipeos);
	os.flush();
    }
 
}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2000/12/12 10:50:07  snf
/** Removed accessors for ProtocolImpl.
/**
/** Revision 1.2  2000/12/04 17:23:54  snf
/** Added connected() method.
/** */
