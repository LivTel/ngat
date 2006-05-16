package ngat.net.transaction;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.util.logging.*;

/**
 * Concrete class for implementing the Distributed Transaction Protocol
 * DTP from the client perspective.<br>
 *   
 * <br><br>
 * $Id: DistributedTransactionProtocolClientImpl.java,v 1.1 2006-05-16 18:14:14 cjm Exp $
 */
public class DistributedTransactionProtocolClientImpl implements ProtocolImpl, Logging {
    
    public static int count = 0;
    
    public final String CLASS = "DTPClientImpl";

    public String id;

    /** The transaction control message to be sent.*/
    protected DistributedTransactionMessage request;
  
    /** The response message received from  the server.*/
    protected ResponseTransactionMessage response;

    /** The client which will handle the response. */
    protected DTPClient client;

    /** The connection used.*/
    protected IConnection connection;

    /** Socket timeout period (millis).*/
    protected long timeout;

    /** A Logger for TRACE information.*/
    Logger logger;
    
    /** Create a DTProtocolClientImpl from the supplied parameters.
     * @param client     Handler for exceptions and protocol specific events.
     * @param connection Abstract connection to use.
     * @param request    The message to be sent.
     * @param timeout    Socket timeout period (millis).
     */
    public DistributedTransactionProtocolClientImpl(DTPClient                     client, 
						    IConnection                   connection,
						    DistributedTransactionMessage request,
						    long                          timeout) {
	this.connection = connection;
	this.client     = client;
	this.request    = request;
	this.timeout    = timeout;
	count++;
	id = ""+count;
	logger = LogManager.getLogger("DTP");
    }
    
    /** The client implementation. If an unexpected error occurs
     * it must be caught and may be passed back to the client via
     * the failed() mechanism. The client implementation
     * opens the attached connection, sends the command and waits
     * for the response which is passed back to the client.
     * Any errors are also passed back via the client's failure
     * handling API i.e. the failed() method - these provide 
     * information to the client as to where the error occurred.
     * NOTE: The command should have been set before an
     * attempt is made to call this method or it will receive a
     * failed() signal.
     */  
    public void implement() {

	logger.log(5, CLASS, id, "impl",
		   "Using request: "+request);
	// This should not happen !
	if (request == null) {
	    client.failed(new IllegalStateException("No request specified"));
	    return;
	}

	try {
	    logger.log(5, CLASS, id, "impl", 
		       "Opening client connection");
	    
	    // Fail if the connection is not set.
	    if (connection == null) {
		client.failed(new ConnectException("No connection specified"));
		cancel();
		return;
	    }
	    
	    // Open the connection if NOT already open!
	    if ( ! connection.isOpen()) {
		try {
		    connection.open();
		    logger.log(5, CLASS, id, 
			       "impl","Connection is open");
		} catch (ConnectException cx) {
		    client.failed(cx);
		    cancel();
		    return;
		}
	    }
	    	   	    
	    // Send the Request
	    try {
		connection.send(request);
		logger.log(5, CLASS, id, "impl",
			   "Despatched request: "+request);		
	    } catch (IOException iox) {
		client.failed(iox); 
		cancel();
		return;
	    }
	    
	    // Receive the response
	    // ## There is no way to timeout generically. ##	 
	    Object  obj = null;
	    boolean completed = false;
	   
	    try {
		//obj = connection.receive(timeout);
		obj = connection.receive();
		logger.log(5, CLASS, id, "impl",
			   "Data received type= "+obj.getClass().getName());
		if (obj instanceof ResponseTransactionMessage) {
		    response = (ResponseTransactionMessage)obj;
		    logger.log(5, CLASS, id, 
			       "impl","About to forward response to client for handling");
		    client.handleResponse(response);		   
		} else {
		    client.failed(new Exception("Unknown response type: "+
						obj.getClass().getName()));
		}
	    } catch (IOException iox) {
		logger.log(EXCEPTION, 3, CLASS, id, "impl",
			   "No response received within timeout", null, iox);
		client.failed(iox); 
		cancel();
		return;			
	    }
	    
	} catch (Exception ex) {
	    // Deal with any other exceptions here.
	    logger.log(EXCEPTION, 3, CLASS, id, "impl",
		       "Passing exception to client: "+ex);
	    client.failed(ex);
	}
	
	// Close the connection.
	connection.close();
	
	logger.log(TRACE, 5, CLASS, id, "impl", "Leaving implementation.");
	
    }
    
    /** Forces the CCT-Thread to unblock by any suitable method.*/
    public void cancel() {
	logger.log(5, CLASS, id, "cancel", "Closing connection.");
	if (connection != null)
	    connection.close();
	connection = null;
    }

    /** Returns the ID for this implementor.*/
    public String getId() { return id;}
    
   
}

/** $Log: not supported by cvs2svn $ */
