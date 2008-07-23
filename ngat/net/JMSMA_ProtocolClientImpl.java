package ngat.net;

import java.io.*;
import java.net.*;
import ngat.net.*;
import ngat.util.logging.*;
import ngat.message.base.*;

/**
 * Concrete class for implementing the JMS multi acknowledge
 * Protocol JMS(MA) from the client perspective.<br>
 *    ## There is no way yet to timeout generically. ##<br>
 *    ## If the server is sending repeated Acks within ##<br>
 *    ## the timeout period then its Ok , otherwise we ##<br>
 *    ## do not rebroadcast our Acks in time and our ##<br>
 *    ## client will probably die. ##
 * <br><br>
 * $Id: JMSMA_ProtocolClientImpl.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class JMSMA_ProtocolClientImpl implements ProtocolImpl, Logging {
    
    public static int count = 0;
    
    public final String CLASS = "JMSMA_Client";

    public String id;

    /** The Command to be sent.*/
    protected COMMAND command;

    /** The Ack received from the server.*/
    protected ACK ack;

    /** The Done message received from  the server.*/
    protected COMMAND_DONE done;

    /** The client which will handle the response. */
    protected JMSMA_Client client;

    /** The connection used.*/
    protected IConnection connection;

    /** A Logger for TRACE information.*/
    Logger logger;
    
    /** Create a JMS_ProtocolClientImpl from the supplied parameters.
     * @param client The handler for exceptions and protocol specific
     * events.
     * @param connection The abstract connection to use.*/
    public JMSMA_ProtocolClientImpl(JMSMA_Client client, 
				    IConnection connection) {
	this.connection = connection;
	this.client = client;
	count++;
	id = ""+count;
	logger = LogManager.getLogger("JMS");
	connection.setConnectionName(id);
    }
    
    /** The client implementation. If an unexpected error occurs
     * it must be caught and may be passed back to the client via
     * the ExceptionCallback mechanism. The client implementation
     * opens the attached connection, sends the command and waits
     * for the ACK and DONE which are passed back to the client.
     * Any errors are also passed back via the client's failure
     * handling API i.e. the failedXXX() methods - these provide 
     * information to the client as to where the error occurred.
     * NOTE: The client's command should have been set before an
     * attempt is made to call this method or it will receive a
     * failedDespatch() signal.*/  
    public void implement() {
	try {
	    logger.log(5, CLASS, id, "impl", 
		       "Opening client connection");

	    // Fail if the connection is not set.
	    if (connection == null) {
		client.failedConnect(new ConnectException("No connection set"));
		return;
	    }

	    // Open the connection if NOT already open!
	    if ( ! connection.isOpen()) {
		try {
		    connection.open();
		    logger.log(5, CLASS, id, 
			       "impl","Connection is open");
		} catch (ConnectException cx) {
		    client.failedConnect(cx);
		    return;
		}
	    }

	    // Retrieve the COMMAND.
	    command = client.getCommand();
	    logger.log(5, CLASS, id, "impl",
		       "Retreived command: "+command.getClass().getName());
	    // This should not happen !
	    if (command == null) {
		client.exceptionOccurred(this, new IllegalStateException("No command set"));
		return;
	    }
	    
	    // Send the Command
	    try {
		connection.send(command);
		logger.log(5, CLASS, id, "impl",
			   "Despatched command: "+command.getId());
		
	    } catch (IOException iox) {
		client.failedDespatch(iox); 
		return;
	    }
	    
	    // Receive the Ack(s) or Done.
	    // ## There is no way to timeout generically. ##
	    // ## If the server is sending repeated Acks within ##
	    // ## the timeout period then its Ok , otherwise we ##
	    // ## do not rebroadcast our Acks in time and our ##
	    // ## client will probably die. ## 
	    Object obj = null;
	    boolean completed = false;
	    while (!completed) {		
		logger.log(5, CLASS, id, "impl",
			   "Waiting ACK with timeout: "+client.getTimeout());
		try {
		    obj = connection.receive(client.getTimeout());
		    logger.log(5, CLASS, id, "impl",
			       "Data received type= "+obj.getClass().getName());
		    if 
			(obj instanceof ACK) {	
			ack = (ACK)obj;
			logger.log(5, CLASS, id, "impl",
				   "About to forward ACK to client for handling");
			client.handleAck(ack);
		    } else if
			(obj instanceof COMMAND_DONE) {
			done = (COMMAND_DONE)obj;

			completed = true;

			logger.log(5, CLASS, id, 
				   "impl","About to forward DONE to client for handling");

			client.handleDone(done);
			// Break out of the loop unless client resets the completion flag.
		
			//continue;
		    } else {
			client.failedResponse(new Exception("Unknown response type: "+
							    obj.getClass().getName()));
			return;
		    }
		} catch (IOException iox) {
		    logger.log(EXCEPTION, 3, CLASS, id, "impl",
			       "No response received within timeout", null, iox);
		    client.failedResponse(iox); 
		    return;			
		}
	    }

	   
	    
	} catch (Exception ex) {
	    // Deal with any other exceptions here.
	    logger.log(EXCEPTION, 3, CLASS, id, "impl",
		       "Passing exception to client: "+ex);
	    client.exceptionOccurred(this, ex);

	} finally {

	    // Close the connection.
	    
	    connection.close();
	
	    logger.log(TRACE, 5, CLASS, id, "impl", "Leaving implementation.");
	
	}

    }
  
    /** Forces the CCT-Thread to unblock by any suitable method.*/
    public void cancel() {
	logger.log(5, CLASS, id, "cancel", "Closing connection.");
	connection.close();
    }

    /** Returns the ID for this implementor.*/
    public String getId() { return id;}
    
   
}

/** $Log: not supported by cvs2svn $
/** Revision 1.13  2008/07/23 12:01:52  eng
/** nothing
/**
/** Revision 1.12  2007/04/11 08:50:12  snf
/** added finally to allow close to always occur.
/**
/** Revision 1.11  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.10  2004/01/15 16:04:53  snf
/** uodated
/**
/** Revision 1.9  2001/07/11 10:50:28  snf
/** backup.
/**
/** Revision 1.8  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.7  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.6  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.5  2000/12/08 17:34:12  snf
/** *** empty log message ***
/**
/** Revision 1.4  2000/12/08 17:32:58  snf
/** *** empty log message ***
/**
/** Revision 1.3  2000/12/06 10:06:05  snf
/** Added check for connection already open and retrieve command from client.
/**
/** Revision 1.2  2000/12/04 17:23:54  snf
/** Added connected() method.
/**
/** Revision 1.1  2000/12/04 13:18:54  snf
/** Initial revision
/** */

