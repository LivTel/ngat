package ngat.net;

import ngat.util.*;

/**
 * Base class for implementations of a client-server Protocol
 * over a generic IConnection. This class is used to provide an
 * execution thread for a ProtocolImpl acting as server.
 * <br><br>
 * $Id$
 */public class ServerConnectionThread extends ControlThread {

     /** The ProtocolImplementor which will carry out the operations
      * specified by the Protocol.*/
     protected ProtocolImpl implementor;

     /** Create a ClientConnectionThread using the specified ProtocolImpl.
      * This implementation of ControlThread is TRANSIENT i.e. runs once.
      * @param protocolImplementor The implementor for the client.*/
     public ServerConnectionThread(ProtocolImpl implementor) {
	 super("ServerConnectionThread", TRANSIENT);
	 this.implementor = implementor;
     }
     
     /** Carry out any initialization.*/
     public void initialise(){}
          
     /** The run method is made final to force subclasses to follow the
      * specified execution template. Calls implement() on the attached
      * ProtocolImpl. The implementor <b>must</b> block to stop this thread
      * from returning from its run method and dieing prematurely.*/
     public final void mainTask() {
	 implementor.implement();
     }

     /** Carry out any shutdown procedures.*/
     public void shutdown() {
	 implementor = null;
	 //System.err.println("SCT-Shutdown-OK");
     }  

     /** Sets the protocol implementor.*/
     public synchronized void setProtocolImpl(ProtocolImpl implementor) { this.implementor = implementor;}
     
     /** Returns the implementor in use.*/
     public synchronized ProtocolImpl getProtocolImpl() {  return implementor; }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2004/01/15 16:04:53  snf
/** updated
/**
/** Revision 1.3  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.2  2000/12/12 10:49:43  snf
/** Added accessors for ProtocolImpl.
/**
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
