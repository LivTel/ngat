package ngat.net.camp;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.message.base.*;

public class TestServer implements CAMPRequestHandlerFactory {

    public TestServer(int port) throws IOException  {

	CAMPServer server = new CAMPServer("TEST");
	
	server.bind(port);

	server.setRequestHandlerFactory(this);

	server.start();

    }


    public CAMPRequestHandler createHandler(IConnection connection, COMMAND command) {
	return new Handler(connection, command);	
    }

    class Handler implements CAMPRequestHandler {

	IConnection connection;

	Handler(IConnection connection, COMMAND command) {
	    this.connection = connection;	  
	}

	public void handleRequest() {
	    
	    for (int i = 0; i < 10; i++) {

		try {Thread.sleep(5000L); } catch (InterruptedException ix) {}

		COMMAND_DONE done = new COMMAND_DONE("test"+i);
		done.setSuccessful(true);
		try {
		    connection.send(done);	 
		} catch (Exception e) {
		    System.err.println("Error: "+e);
		}
	    }
	    
	}
	
	public long getHandlingTime() {return 0L;}
	
	public void dispose() {}

    }

    public static void main(String args[]) {

	try {
	    new TestServer(8787);
	} catch (Exception e) {
	    System.err.println("Error: "+e);
	}
    }

}
