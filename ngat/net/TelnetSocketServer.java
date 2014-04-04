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
public class TelnetSocketServer extends SocketServer {
    
    /** Create a TelnetSocketServer on the specified port.
     * @param port The port to bind to.
     * @exception IOException If an error occurs while opening the socket.*/
    public TelnetSocketServer(int port) throws IOException {
	super(port);
    }
    
    /** Create a TelnetSocketServer using a pre-built ServerSocket.
     * @param serverSocket A pre-built (and already bound) ServerSocket.*/
    public TelnetSocketServer(ServerSocket serverSocket) {
	super(serverSocket);
    }

    /** Creates a ServerConnectionThread to handle a client connection 
     * and starts it.
     * @param socket The socket to be used for this client.*/
    protected void spawnConnectionThread(Socket socket) throws IOException {
	connection = new TelnetSocketConnection(socket);
	sct = createServerConnectionThread(piFactory.createServerImpl(connection, rhFactory));
	sct.start();
    }
    
}


