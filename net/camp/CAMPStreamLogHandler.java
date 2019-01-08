package ngat.net.camp;


import ngat.net.*;
import ngat.util.logging.*;
import ngat.message.GUI_RCS.*;

import java.io.*;

public class CAMPStreamLogHandler extends LogHandler {

    /** ID of the requesting command.*/
    String id;

    /** Connection to publish messages to.*/
    IConnection connection;

    /** Create a CAMPStreamLogHandler.*/
    public CAMPStreamLogHandler(String id, IConnection connection) {
	super(null);
	this.id = id;
	this.connection = connection;
    }

    /** Publish a LogRecord to System.err .*/
    public void publish(LogRecord record) {
	
	GRAB_LOG_DONE done = new GRAB_LOG_DONE(id);
	done.setRecord(record);
	done.setSuccessful(true);
	if (connection != null) {
	    try {
		connection.send(done);
	    } catch (IOException iox) {	 
		connection.close();
		connection = null;
	    }
	}
    }
    
    /** Write the tail.*/
    public void close() {
	if (connection != null) {	   
	    connection.close();
	    
	    connection = null;
	}
    }

}
