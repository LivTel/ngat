package ngat.net;

import java.net.*;
import ngat.util.*;

/** Basic and very crude JMS Server base class.*/
public class JMSServer extends ControlThread {

    int port;

    JMSServerProtocolRequestHandlerFactory factory;

    ServerSocket server;

    public JMSServer(String name, int port, JMSServerProtocolRequestHandlerFactory factory) throws Exception {
	super(name, true);
	this.port = port;
	this.factory =factory;
	server = new ServerSocket(port);
    }

    public void initialise() {}

    public void mainTask() {
		
	try {
	    Socket socket = server.accept();
	    
	    if (socket != null) {		    
		SocketConnection connection = new SocketConnection(socket);
		
		JMSServerProtocolImplementor impl = 
		    new JMSServerProtocolImplementor(connection, factory);
		
		(new Thread(new Executor(impl))).start();
		
	    }	

	}  catch (Exception e) {
	    e.printStackTrace();
	} 
    }

    public void shutdown() {
	try {
	    server.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static class Executor implements Runnable {

	JMSServerProtocolImplementor impl;

	Executor(JMSServerProtocolImplementor impl) {
	    this.impl = impl;
	}

	public void run() {
	    try {
		impl.implementProtocol();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

    }

}
