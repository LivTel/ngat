package ngat.net;

import ngat.util.*;

/**
 * Base class for implementations of a client-server Protocol
 * over a generic IConnection. This class is used to provide an
 * execution thread for a ProtocolImpl acting as client.
 * <br>
 * $Id: ClientConnectionThread.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public class ClientConnectionThread extends ControlThread {

    /** The ProtocolImplementor which will carry out the operations
     * specified by the Protocol.*/
    protected ProtocolImpl implementor;

  
    /** Create a ClientConnectionThread using the specified ProtocolImpl.
     * @param protocolImplementor The implementor for the client.*/
    public ClientConnectionThread(ProtocolImpl implementor) {
	this.implementor = implementor;
    }
    
    /** Carry out any initialization.*/
    public void initialise() {}

    /** The mainTask() method is made final to force subclasses to follow the
     * specified execution template. Calls implement() on the attached
     * ProtocolImpl. The implementor <b>must</b> block to stop this thread
     * from returning from its run method and dieing prematurely.*/
    public final void mainTask() {
	implementor.implement();
    }
    
    /** Force the implementor to give up any live connections
     * and release this thread from a blocked I/O state.*/
    public void abort() {
	System.err.println("CCT: called abort()");
	implementor.cancel();
    }

    /** Carry out any shutdown procedures.*/
    public void shutdown() {}

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/05/14 15:35:43  snf
/** Added the abort method.
/**
/** Revision 1.3  2000/12/04 17:23:54  snf
/** Added connected() method.
/**
/** Revision 1.2  2000/11/23 10:54:51  snf
/** Changed exception from Protocol to general IO.
/**
/** Revision 1.1  2000/11/23 10:21:29  snf
/** Initial revision
/** */

