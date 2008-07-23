package ngat.net;

import java.net.*;

import ngat.message.base.*;
import ngat.net.*;

public class JMSClientProtocolImplementor {

    long timeout = 10000L;

    public void setTimeout(long timeout) {this.timeout = timeout;}

    /** Perform JMS protocol using supplied command and components.
     * @param handler A handler to accept response.
     * @param connection The connection to send command via.
     * @param command The command to send.
     */
    public void implementProtocol(JMSClientProtocolResponseHandler handler,
				  IConnection connection,
				  COMMAND command) throws Exception {
	
	if (connection == null) 
	    throw new ConnectException("JMSClient::No connection set");
	
	// Open the connection if NOT already open!
	if ( ! connection.isOpen()) 
	    connection.open();
	
	try {
	    
	    if (command == null) 
		throw new IllegalArgumentException("JMSClient::No command set");
	    
	    // Send the Command
	    connection.send(command);
	    
	    Object  obj       = null;
	    boolean completed = false;
	    ACK          ack  = null;
	    COMMAND_DONE done = null;

	    while (!completed) {
		System.err.println("JMSClient::Ready receive (to="+timeout);
		obj = connection.receive(timeout);
		System.err.println("JMSClient::Data received type= "+
				   (obj != null ? obj.getClass().getName() : "NULL"));
		if
		    (obj instanceof ACK) {
		    ack = (ACK)obj;
		    timeout = ack.getTimeToComplete();
		    System.err.println("JMSClient::Reset timeout ["+ack.getId()+"] to: "+timeout);
		    handler.handleAck(ack);
		    
		} else if
		    (obj instanceof COMMAND_DONE) {
		    done = (COMMAND_DONE)obj;		    
		    completed = true;
		    
		    handler.handleDone(done);
		    
		} else
		    throw new Exception("JMSClient::Unknown reply class: "+
					(done != null ? done.getClass().getName() : "NULL"));
	    }
	    if (!completed)
		System.err.println("JMSClient::No response received within timeout");
	    
	} finally {
	    
	    // Close the connection.
	    connection.close();
	}
	
    }
    
}
