package ngat.net;

import java.io.*;
import java.net.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.base.*;

/** Implementation of the JMS (Multiple Acknowledge) protocol at
 * the server end.
 * <br><br>
 * $Id$
 */
public class JMSMA_ProtocolServerImpl implements ProtocolImpl, Logging {

    /** ERROR_BASE for this class.*/
    public static final int ERROR_BASE = 200;

    public static final int BASIC_TIMEOUT = 10000;

    public static final int SERVER_TIMEOUT_ERROR        = 000201;

    public static final int SERVER_INITIALIZATION_ERROR = 000202;

    public static int count = 0;
    
    public final String CLASS = "JMSMA_ServerImpl";
    
    public String id;

    Logger logger;

    /** Handler factory - generates the appropriate RequestHandler for
     * a received command.*/
    RequestHandlerFactory hFactory;

    /** Stores the command received.*/
    COMMAND command;

    /** The abstract connection to use.*/
    IConnection connection;
    
    /** Handles the COMMAND recieved from the client.*/
    protected RequestHandler handler;

    /** Indicates that the command has completed or failed. i.e. the TCS
     * has sent either a <i>completed</i> or an <i>error</i> message.*/
    protected volatile boolean completed;
    
    /** Object to synch on.*/
    protected Object csynch;
    
    
    /** Flag to indicate whether this handler should carry on waiting for
     * ACKs from the Proxy server.*/
    protected volatile boolean acked;
    
    /** Object to synch on.*/
    protected Object asynch;
    
    
    /** Create a DTProtocolServerImpl using the supplied parameters.   
     * @param connection The abstract connection to use.*/
    public JMSMA_ProtocolServerImpl(IConnection connection, RequestHandlerFactory hFactory ) {
	this.connection = connection;
	this.hFactory = hFactory;	
	asynch = new Object();
	csynch = new Object();
	count++;
	id = ""+count;
	logger = LogManager.getLogger("JMS");
    }
    
    
    /** The implementation. <br>
     * Notes: <ul>
     * <li> Returns silently if the connection dies
     * or the handler cannot be created.
     * <li> The handler's handleRequest() method should return either
     * immediately if it starts a ClientConnectionThread or before its
     * initially calculated tiomeout if it does processing.
     * </ul>
     * The handler (via a proxy) will have to keep sending ACKs back
     * to the client using the sendAck() method on this ServerImpl.
     *
     * <ol>
     *    <li>Get the command from the client.
     *    <li>Using RequestHandlerFactory, instantiate an appropriate handler.
     *    <li>Send an ACK using handler's initial completion time estimate.
     *    <li>Call handler's handleRequest().
     *    <li>Enter loop waiting for handler to complete. The execution thread
     *        is made to sleep for the timeout period. If the handler has spawned
     *        a client thread, then this may recieve ACKs and pass them on to
     *        here. The acked flag is reset on receipt of an ACK and another
     *        timeout period can be entered.
     * </ol>
     */
    public void implement() {

	// 1. Get the command from the client. If this fails then we 
	// try to send an error message to the client can't do much else.
	logger.log(1, CLASS, id, "impl",
		   "About to read command");
	try { 
	    command = (COMMAND)connection.receive();
	    logger.log(1,CLASS, id, "impl",
		       "Received command: "+command.getClass().getName());
	} catch (IOException e) {
	    initError("I/O error while reading command:["+e+"]",command);
	    return;
	} catch (ClassCastException ce) {
	    initError("Garbled command:["+ce+"]", command);
	    return;
	} catch (Exception e) {
	    initError("Fatal: "+e, command);
	    return;
	}
	
	// 2. Create a handler.
	if (command == null) {
	    initError("No command set", null);
	    return;
	}
	handler = hFactory.createHandler(this, command);
	if (handler == null) {
	    initError("Unable to process command ["+command.getClass().getName()+"]: No known handler", command);
	    return;
	}

	// 3. Send an ACK straight to the client.
	ACK ack = new ACK(command.getId()+":handling request");
	ack.setTimeToComplete(BASIC_TIMEOUT+(int)(1.1*handler.getHandlingTime()));
	sendAck(ack);

	// 4. Handle the request.
	handler.handleRequest();
	
	// 5. Loop waiting for acks to clear or completion.
	// runner  = Thread.currentThread(); // This the the caller !
	resetAck();
	while ( ! completed() && waitingAck() ) {
	    // Clear the ACK flag. A Client thread spawned by
	    // the handler can reset it to indicate that the
	    // server thread (invoker) should not timeout yet.
	    logger.log(1,CLASS, id, "impl",
		       "Waiting ACK");

	    clearAck(); 
	    try {
		Thread.sleep(handler.getHandlingTime());
	    } catch (InterruptedException e) {
	    }	
	    logger.log(1,CLASS, id, "impl", 
		       "Timed out with ACK flag: "+
		       (waitingAck() ? "SET" : "CLEAR")+
		       " and "+(completed ? "DONE":"UNCOMPLETE"));
	}
	// If not completed then  we've timed out. Send an error to ICS.
	if (! completed() ) {
	    logger.log(1, CLASS, id, "impl", 
		       "Timed out uncompleted");
	    System.err.println("JMSPS::"+command.getId()+":Handler timed out w/o reply, sending DONE w t/o message");
	    COMMAND_DONE done = new COMMAND_DONE(command.getId());
	    done.setSuccessful(false);
	    done.setErrorNum(SERVER_TIMEOUT_ERROR);
	    done.setErrorString("JMS::Command:["+command.getId()+
				"]:Timed out after ("+handler.getHandlingTime()+
				" millis) waiting for handler to complete");
	    sendDone(done, handler.getHandlingTime());
	}
	
	// Leave the implementation here.
	logger.log(3, CLASS, id, "impl", "Leaving implement");
    }
    
    /** Send an ACK to the client.*/
    public void sendAck(ACK ack) {
	synchronized (this) {
	    resetAck();
	    try { 
		logger.log(1, CLASS, id, "impl", 
			   "Sending ACK with ttc: "+ack.getTimeToComplete());
		//System.err.println("JMSMA_PSI:: sendAck: connect: "+connection+
		//	   "\nTTC: "+ack.getTimeToComplete());
		//System.err.println("JMSPS::"+command.getId()+"SendACK:: Conn="+connection);

		if (connection != null)
		    connection.send(ack);
	    } catch (IOException e) {
		System.err.println("JMSPS::"+command.getId()+"SendACK:: Exception: "+e);
		handler.exceptionOccurred(this, e);
	    }
	}
    }

    public void sendDone(COMMAND_DONE done) {
	sendDone(done, 0L);
    }


    /** Send a DONE to the client and finish execution thread.*/
    public void sendDone(COMMAND_DONE done, long timeout) {
	synchronized (this) {
	    try { 
		logger.log(1, CLASS, id, "impl", 
			   "Sending DONE");
		//System.err.println("JMSMA_PSI:: sendDone: connect: "+connection+
		//		   "\ndone:  "+done+
		//	   "\nclass: "+done.getClass().getName()+
		//	   "\nid:    "+done.getId()+
		//	   "\nOK:    "+done.getSuccessful()+
		//	   "\nErr:   "+done.getErrorNum()+
		//	   "\nMsg:   "+done.getErrorString());	
		//		System.err.println("JMSPS::"+command.getId()+" Conn="+connection);
		if (connection != null) {
		    if (timeout > 0L)
			connection.send(done, timeout);
		    else
			connection.send(done);
		}
	    } catch (IOException e) {
		handler.exceptionOccurred(this,e);
	    }
	    setCompleted();
	    if (handler != null)
		handler.dispose();
	    if (connection != null)
		connection.close();
	    connection = null;
	    command    = null;
	    hFactory   = null;
	}
	// runner.interrupt(); // use this to break the wait-for-ACK in implement().
    }
    
    /** Set the flag to indicate that an ACK has been received.*/
    private void resetAck() {
	synchronized(asynch) {
	    acked = true;
	}
    }
    
    /** Clear the flag to indicate that an ACK is not expected.*/
    private void clearAck() {
	synchronized(asynch) {
	    acked = false;
	}
    }
    
    /** @return True if the flag to indicate an ACK was received is set.*/
    private boolean waitingAck() {
	synchronized(asynch) {
	    return acked;
	}
    }
    
    /** Set the completion status to indicate that handler has finished.*/
    private void setCompleted() {
	synchronized(csynch) {
	    completed = true;
	}
    }
    
    /** @return True if the handler has completed processing.*/
    private boolean completed() {
	synchronized(csynch) {
	    return completed;

	}
    }
    
    /** Send a message to the client to indicate an error occurred
     * while reading the command. Sends an ACK and a DONE.
     * @param message A message to send to the client.
     * @param command The command which initiated this message.*/
    protected void initError(String message, COMMAND command) {	
	System.err.println("Error: "+message);
	String id = (command != null ? command.getId() : "no-command");	    
	ACK ack = new ACK(id+":handling error");
	ack.setTimeToComplete(BASIC_TIMEOUT);
	sendAck(ack);
	COMMAND_DONE done = new COMMAND_DONE(id);
	done.setSuccessful(false);
	done.setErrorNum(SERVER_INITIALIZATION_ERROR);
	done.setErrorString("Command: ["+id+"]: JMS::"+message);
	sendDone(done);
    }
  
    /** Forces the CCT-Thread to unblock by any suitable method.*/
    public void cancel() {
	if (connection != null)
	    connection.close();
    }

    /** Returns the ID for this implementor.*/
    public String getId() { return id;}
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2008/07/23 12:41:17  eng
/** Initial revision
/**
/** Revision 1.14  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.13  2004/01/15 16:04:53  snf
/** uodated
/**
/** Revision 1.12  2001/07/11 10:50:28  snf
/** backup.
/**
/** Revision 1.11  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.10  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.9  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.8  2000/12/08 17:33:10  snf
/** *** empty log message ***
/**
/** Revision 1.7  2000/12/08 10:39:38  snf
/** Modified timeout mechanism.
/**
/** Revision 1.6  2000/12/07 09:34:25  snf
/** Modified body of implement().
/**
/** Revision 1.5  2000/12/06 11:27:09  snf
/** Removed ref to handler before it is assigned.
/**
/** Revision 1.4  2000/12/04 17:23:54  snf
/** Added connected() method.
/**
/** Revision 1.3  2000/12/04 13:38:42  snf
/** Changed connection from ServerConnection to basic IConnection.
/**
/** Revision 1.2  2000/12/04 13:04:08  snf
/** Implementation of the JMS(MA) protocol from server end.
/** */
