package ngat.net.camp;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.util.*;
import ngat.message.base.*;

/** Simple repeating query is sent and replies passed to handler.*/
public class QueryThread extends ControlThread {

    protected COMMAND command;

    protected CAMPResponseHandler handler;

    protected IConnection connection;

    protected String connectionId;

    protected long timeout;

    protected long pollingInterval;

    protected ConnectionFactory connectionFactory;

    public QueryThread(String name) {
	super(name, true);
    }

    public void setPollingInterval(long pollingInterval) { this.pollingInterval = pollingInterval; }

    public void setTimeout(long timeout) { this.timeout = timeout; }

    public void setCommand(COMMAND command) { this.command = command; }

    public void setResponseHandler(CAMPResponseHandler handler) { this.handler = handler; }

    public void setConnectionFactory(ConnectionFactory connectionFactory) { this.connectionFactory = connectionFactory; }

    public void setConnectionId(String connectionId) { this.connectionId =  connectionId; }

    protected void initialise() {


    }
    
    protected void mainTask() {

	try { Thread.sleep(pollingInterval);} catch (InterruptedException ix) {}

	try {
	    connection = connectionFactory.createConnection(connectionId);
	} catch (UnknownResourceException ux) {
	    handler.failed(ux, connection);
	    return;
	}

	try {
	    connection.open();
	} catch (ConnectException cx) {
	    handler.failed(cx, connection);
	    return;
	}
	    
	try {
	    connection.send(command);
	} catch (IOException iox) {
	    handler.failed(iox, connection);
	    return;
	}

	boolean waitingReply = true;
	while (waitingReply) {
	    try {
		Object obj = connection.receive(timeout);
		System.err.println("QT:"+getName()+"::Object recvd: "+obj);
		if (obj instanceof COMMAND_DONE) {
		    try {
			COMMAND_DONE update = (COMMAND_DONE)obj;	   
			handler.handleUpdate(update, connection);
			return;
		    } catch (Exception x) {
			handler.failed(x, connection);
			return;
		    }
		}
	    } catch (Exception x) {
		handler.failed(x, connection);
		return;
	    }

	}

    }

    protected void shutdown() {}

}
