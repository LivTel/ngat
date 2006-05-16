package ngat.net.transaction;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.util.logging.*;

/**
 * Concrete class for implementing the Distributed Transaction Protocol
 * DTP from the client perspective.
 *   
 * <br><br>
 * $Id: DistributedTransactionProtocolServerImpl.java,v 1.1 2006-05-16 18:14:14 cjm Exp $
 */
public class DistributedTransactionProtocolServerImpl implements ProtocolImpl, Logging {
    
    public static int count = 0;
    
    public final String CLASS = "DTPServerImpl";

    public String id;

    /** The transaction control message to be sent.*/
    protected DistributedTransactionMessage request;
  
    /** The response message received from  the server.*/
    protected ResponseTransactionMessage response;

    /** The client which will handle the response. */
    protected DTPServer server;

    /** The connection used.*/
    protected IConnection connection;

    /** Socket timeout period (millis).*/
    protected long timeout;

    /** A Logger for TRACE information.*/
    Logger logger;


    /** Create a JMSMA_ProtocolServerImpl using the supplied parameters. 
     * @param server     Handler for the received request.
     * @param connection The abstract connection to use. This will already have
     *                   been opened by the client and i/o streams setup.    
     */
    public DistributedTransactionProtocolServerImpl(DTPServer   server,
						    IConnection connection) {
	this.server = server;
	this.connection = connection;
	count++;
	id = ""+count;
	logger = LogManager.getLogger("DTP");
    }
    

    /** The implementation.   
     */
    public void implement() {

	// Get the command from the client. 
	logger.log(1, CLASS, id, "impl",
		   "About to read command");

	try { 
	   request = (DistributedTransactionMessage)connection.receive();
	   logger.log(1, CLASS, id, "impl",
		      "Received request: "+request);
	} catch (IOException e) {
	    logger.log(1, CLASS, id, 
		       "I/O error while reading request: "+request+" : "+e);
	    e.printStackTrace();
	    cancel();
	    return;
	} catch (ClassCastException ce) {
	    logger.log(1, CLASS, id, 
		       "Garbled request: "+request+" : "+ce);
	    cancel();
	    return;
	}  
	
	//  Check the request.
	if (request == null) {
	    logger.log(1, CLASS, id, "No request set");
	     cancel();
	     return;
	}

	if (server == null) {
	    logger.log(1, CLASS, id, 
		       "Unable to process request: "+request+" : No known service-handler.");
	     cancel();
	     return;
	}

	//  Handle the request.
	response = server.handleRequest(request);
	
	if (response != null) {

	    try { 
		logger.log(1, CLASS, id, "impl", 
			   "Sending response: "+response);		
		if (connection != null)
		    connection.send(response);  
	    } catch (IOException e) {
		logger.log(1, CLASS, id, 
			   "Failed to send response: "+e);
	    }
	}

	//if (server != null)
	//  server.dispose();
	//server = null;

	if (connection != null)
	    connection.close();
	connection = null;

	request    = null;

	// Leave the implementation here.
	logger.log(3, CLASS, id, "impl", "Leaving implement");
    }
    
    /** Cancel the implementation of the protocol immediately - if possible.*/
    public void cancel() {
	if (connection != null)
	    connection.close();
	connection = null;
    }

}
