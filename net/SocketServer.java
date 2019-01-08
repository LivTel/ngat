package ngat.net;

import ngat.util.*;

import java.net.*;
import java.io.*;

/** Generic socket based server. Subclasses can override the initialize()
 * method with any specific setup - note this is called just before the server
 * is started. Any setup which can be done well in advance of starting can
 * be done in the constructor.<br>
 * The mainTask() method should not be overridden.<br>
 * It may be neccessary to provide a mechanism in (ControlThread) to allow
 * a Controlling thread to register any threads it has started so it can
 * tidy them up on its own shutdown.
 * <br><br>
 * $Id$ 
 */
public class SocketServer extends ControlThread implements Server {

    /** A factory for generating ProtocolImpls.*/
    protected ProtocolImplFactory piFactory;

    /** A factory for generating RequestHandlers.*/
    protected RequestHandlerFactory rhFactory;

    /** A Socket for client communication.*/
    protected Socket socket;

    /** The ServerSocket.*/
    protected ServerSocket serverSocket;

    /** A Server end connection for client communication.*/
    protected IConnection connection;

    /** A ServerConnectionThread to execute a ProtocolImpl for a client.*/
    protected ServerConnectionThread sct;

    /** Create a SocketServer initially unbound. USe bind(port) to bind it.
     * @param name The name of this server.
     */
    public SocketServer(String name) {
	super(name, PERMANENT);
    }

    /** Create a SocketServer on the specified port.
     * @param port The port to bind to.
     * @exception IOException If an error occurs while opening the socket.
     */
    public SocketServer(int port) throws IOException {	
	this(new ServerSocket(port));
    }
    
    /** Create a SocketServer using a pre-built ServerSocket.
     * @param serverSocket A pre-built (and already bound) ServerSocket.
     */
    public SocketServer(ServerSocket serverSocket) {
	super("SocketServer:"+serverSocket.getLocalPort(), PERMANENT);
	this.serverSocket = serverSocket;
    }

    /** Bind an initially unbound SocketServer onto port.
     * @param port The port to bind to.
     * @exception IOException If an error occurs while opening the socket.
     */
    public void bind(int port) throws IOException {	
	serverSocket = new ServerSocket(port);
    }

    /** This method is called each time round the mainTask loop.
     * If the ServerSocket has a timeout specified then the
     * encapsulated serverSocket.accept() will timeout and cause this 
     * method to return. The mainTask() will also exit and the control
     * test can check to see if the stop flag has been set to tell
     * this thread to die off. It then calls shutdown().*/
    public void listen() {
	System.err.println("ngat.net.SocketServer::Listening on "+serverSocket.getLocalPort());
	try {
	    socket = serverSocket.accept();
	    System.err.println("ngat.net.SocketServer::Spawn connection");
	    spawnConnectionThread(socket);
	} catch (IOException e) {
	    return;
	}
    }
    
    /** Carry out any initializing.*/
    public void initialise() {}

    /** This is called once each time round the main loop. Where the
     * control flag is tested to see if the thread should die.*/
    public void mainTask() {listen();}
    
    /** Creates a ServerConnectionThread to handle a client connection 
     * and starts it.
     * @param socket The socket to be used for this client.*/
    protected void spawnConnectionThread(Socket socket) throws IOException {
	connection = new SocketConnection(socket);
	System.err.println("ngat.net.SocketServer::Creating connection: "+connection);
	sct = createServerConnectionThread(piFactory.createServerImpl(connection, rhFactory));
	sct.start();
    }

    /** Create a ServerConnectionThread to communicate with a client.
     * @param connection The server end connection. 
     * @param piFactory A factory to create a ProtocolImplementor.
     * @param rhFactory A factory to create RequestHandlers.*/
    public ServerConnectionThread createServerConnectionThread(ProtocolImpl pi) {
	return new ServerConnectionThread(pi);
    }

    /** Called prior to exiting the run() method, before the thread dies.
     * Closes down the server-socket, current connection and current thread.*/
    public void shutdown() {
	if (connection != null)
	    connection.close();
	connection = null;
      	try { 
	    serverSocket.close();
	    serverSocket = null;
	} catch (IOException e) {}
	if (sct != null)
	    sct.terminate();
	sct = null;
    }

    /** @return The ServerSocket in use by this server.*/
    public ServerSocket getServerSocket() { return serverSocket;}
    
    /** Set the RequestHandlerFactory to use.
     * @param factory The RequestHandlerFactory to use.*/
    public void setRequestHandlerFactory(RequestHandlerFactory factory) {
	this.rhFactory = factory;
    }

    /** @return The RequestHandlerFactory in use.*/
    public RequestHandlerFactory getRequestHandlerFactory() { return rhFactory; }
    
    /** Set the ProtocolImplFactory to use.
     * @param factory The ProtocolImplFactory to use.*/
    public void setProtocolImplFactory(ProtocolImplFactory factory) {
	this.piFactory = factory;
    }

    /** @return The ProtocolImplFactory in use.*/
    public ProtocolImplFactory getProtocolImplFactory() { return piFactory; }

    /** Returns the ServerSocket's port (if bound) else -1.*/
    protected int getPort() {
	if (serverSocket != null)
	    return serverSocket.getLocalPort();
	return -1;
    }

    /** Returns a text description of this SocketServer.*/
    public String toString() {
	return "SocketServer: "+getName()+" : "+
	    desc+" : "+ 
	    (getPort() != -1 ? "on port: "+getPort() : "not bound")+" : "+
	    (paused ? "paused" : "running");

	// e.g. SocketServer: ABSRVR : AB Data Server: on port: 6665 : paused

    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2000/12/06 13:01:25  snf
/** Added accessors for RHFactory and PIFactory.
/**
/** Revision 1.2  2000/12/06 10:44:32  snf
/** Changed createSCT to use new SocketConnection constructor.
/**
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
