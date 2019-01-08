package ngat.net.camp;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.util.*;
import ngat.message.base.*;
import ngat.util.logging.*;

public class CAMPServer extends ControlThread {

    public static final int SERVER_INITIALIZATION_ERROR = 000202;
    
    /** A Socket for client communication.*/
    protected Socket socket;

    /** The ServerSocket.*/
    protected ServerSocket serverSocket;

    /** Request handler.*/
    protected CAMPRequestHandlerFactory handlerFactory;

    protected Logger logger;

    /** Create a CAMP server.*/
    public CAMPServer(String name) {
	super(name, true);
	logger = LogManager.getLogger("CAMP");	
    }
    public void setRequestHandlerFactory(CAMPRequestHandlerFactory handlerFactory) {
	this.handlerFactory = handlerFactory;
    }

    public void bind(int port) throws IOException {	
	serverSocket = new ServerSocket(port);
    }

    public void unbind() throws IOException {	
	serverSocket.close();
    }

    protected void initialise() {}

    /** Keep running and create new scts.*/
    protected void mainTask() {
	try {
	    socket = serverSocket.accept();	
	    spawnConnectionThread(socket);
	} catch (IOException e) {
	    return;
	}
    }

    protected void shutdown() {handlerFactory   = null;}

    /** Start a connection thread.*/
    protected void spawnConnectionThread(Socket socket) {
	try {
	    CAMPServerConnectionThread sct = new CAMPServerConnectionThread(socket);
	    sct.start();
	} catch (IOException iox) {
	    System.err.println("Error creating sct: "+iox);
	}
    }


    class CAMPServerConnectionThread extends ControlThread {

	String CLASS = "CAMPServerConnectionThread";

	IConnection connection;

	CAMPRequestHandler handler;

	COMMAND command;

	private volatile boolean completed  = false;

	CAMPServerConnectionThread(Socket socket) throws IOException {
	    connection = new SocketConnection(socket);	    
	}

	protected void initialise() {}

	protected void mainTask() {
	    
	    // 1. Get the command from the client. If this fails then we 
	    // try to send an error message to the client can't do much else.
	    logger.log(1, CLASS, "-", "impl",
		       "About to read command");
	    try { 
		command = (COMMAND)connection.receive();
		logger.log(1,CLASS, "-", "impl",
			   "Received command: "+(command == null ? "no-command" : command.getClass().getName()));
	    } catch (IOException e) {
		initError("I/O error while reading command:["+e+"]",command);
		return;
	    } catch (ClassCastException ce) {
		initError("Garbled command:["+ce+"]", command);
		return;
	    }  
	    
	    // 2. Create a handler.
	    if (command == null) {
		initError("No command set", null);
		return;
	    }

	    if (handlerFactory != null) {
		handler = handlerFactory.createHandler(connection, command);
		if (handler == null) {
		    initError("Unable to process command ["+command.getClass().getName()+"]: No known handler", command);
		    return;
		}
	    
		// 3. Handle the request - handler may send multiple updates to client over long period
		handler.handleRequest();
	    }	   
	}
	
	protected void shutdown() {}

	/** Send a message to the client to indicate an error occurred
	 * while reading the command. Sends a DONE.
	 * @param message A message to send to the client.
	 * @param command The command which initiated this message.*/
	protected void initError(String message, COMMAND command) {	
	    String id = (command != null ? command.getId() : "no-command");	    
	   
	    COMMAND_DONE done = new COMMAND_DONE(id);
	    done.setSuccessful(false);
	    done.setErrorNum(SERVER_INITIALIZATION_ERROR);
	    done.setErrorString("Command: ["+id+"]: CAMP::"+message);
	  
	    try { 	
		if (connection != null)
		    connection.send(done);  
	    } catch (IOException e) {
		logger.log(1,CLASS, "-", "impl",
			   "Failed to send error: "+e);
	    }
	    
	    if (handler != null)
		handler.dispose();
	    if (connection != null)
		connection.close();
	    connection = null;
	    command    = null;
	    
	}

    }
    
}
