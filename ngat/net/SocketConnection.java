package ngat.net;

import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;

import ngat.util.logging.*;

/** Implements an abstraction of a TCP socket connection. The Socket
 * used is not 'live' until the open() method is called - this state
 * can always be checked by calling isOpen(). The overridden constructor
 * SocketConnection(java.net.Socket) allows a pre-built Socket to be
 * used and opens the connection automatically - this means that the
 * SocketConnection is live immediately without having to call open(). 
 * It is intended for use at the server end.
 *
 * $Id: SocketConnection.java,v 1.2 2013-07-01 10:10:46 eng Exp $
 *
 */
public class SocketConnection implements IConnection {
    
    /** The input stream for receiving data. */ 
    protected ObjectInputStream is;

    /** The output stream for sending data to.*/
    protected ObjectOutputStream os;
    
    /** The socket created by this connection.*/
    protected Socket socket;
    
    /** The host name.*/
    protected String host;
    
    /** The remote port.*/
    protected int port;
   
    /** Optional SocketFactory for creating Sockets.*/
    protected SocketFactory sf;

    /** Indicates whether the socket has been opened.*/
    protected boolean open;

    /** Logging.*/
    protected Logger logger;

    protected String cid;
    
    /** Create a SocketConnection object for specified address.
     * @param host The host name.
     * @param port The port to connect to at the named host.
     */
    public SocketConnection(String host, int port) {
	this.host = host;
	this.port = port;
	open = false;
	logger = LogManager.getLogger("JMS");
	logger.log(3, "SockConn::Created for "+host+":"+port);
	cid = host+":"+port
    }

    /** Create a SocketConnection for the specified address.
     * @param port The port to connect to at the named host.
     * @param address A pre-built InetAddress for the host.*/
    public SocketConnection(InetAddress address, int port) {
	this(address.getHostName(), port);	
    }
    
    /** Create a SocketConnection object for specified address.
     * @param host The host name.
     * @param port The port to connect to at the named host.
     * @param sf   An optional SocketFactory.
     */
    public SocketConnection(String host, int port, SocketFactory sf) {
	this.host = host;
	this.port = port;
	this.sf   = sf;
	open = false;	
	logger = LogManager.getLogger("JMS");
    }

    /** Creates a SocketConnection on an already built Socket and
     * opens it as the server end. <b>Do not</b> call open() on
     * the connection after using this constructor.
     * @param socket The pre-built Socket to use.
     * @exception IOException If any problem occurs opening the
     * I/O streams or the Socket is null.*/
    public SocketConnection(Socket socket) throws IOException {
	if (socket == null) throw new IOException("Socket not defined");
	this.socket = socket;

	logger = LogManager.getLogger("JMS");

	//socket.setTcpNoDelay(true);
	try {
	    //is = new ObjectInputStream(socket.getInputStream());
	    is = getInputStream();
	} catch (IOException e) {
	    throw new ConnectException("Error opening input stream:"+e);
	}
	
	try {
	    //os = new ObjectOutputStream(socket.getOutputStream());
	    os = getOutputStream();
	    os.flush();
	} catch (IOException e) {
	    throw new ConnectException("Error opening output stream:"+e);
	}
	open = true;


	logger.log(1, "SockConn::"+socket.getInetAddress()+":"+socket.getPort()+":Socket streams are now open and flushed");

    }

    /** Set the id for this connection.*/
    public void setConnectionName(String cid) {
	this.cid = cid;
    }


    
    /** Establishes the Socket connection to the server.
     * @exception ConnectException If the connection is already open or any
     * error occurs while opening the I/O streams.*/
    public void open() throws ConnectException {
	if (open) throw new ConnectException("Connection already open");
	logger.log(3, "SockConn:"+cid+":opening connection to: "+host+":"+port);

	if (sf != null) {
	    try {
		socket = sf.createSocket(host, port);	
		String [] suites = ((SSLSocket)socket).getSupportedCipherSuites();		
		((SSLSocket)socket).setEnabledCipherSuites(suites);
		socket.setTcpNoDelay(true);
	    } catch (IOException e) {
		throw new ConnectException("Unable to connect to "+host+":"+port+" :"+e);
	    } 
	} else {
	    try {
		socket = new Socket(host, port);
		//socket.setTcpNoDelay(true);
		

	    } catch (UnknownHostException ue) {
		throw new ConnectException("Unable to connect to "+host+":"+port+" :"+ue);
	    } catch (IOException e) {
		throw new ConnectException("Unable to connect to "+host+":"+port+" :"+e);
	    } 
	}
	
	try {
	    os = getOutputStream();
	    os.flush();
	} catch (IOException e) {
	    throw new ConnectException("SockConn:: "+cid+" Error opening output stream:"+e);
	}
	
	try {
	    is = getInputStream();
	} catch (IOException e) {
	    throw new ConnectException(""+cid+"Error opening input stream:"+e);
	}
	open = true;
	logger.log(1, "SockConn:: "+cid+" Socket streams are now open and flushed");

    }
    
    /** Writes an object to the socket's output stream.
     * @param data The object to send.
     * @exception IOException If any error occurs while writing to
     * the output stream.*/
    public void send(Object data) throws IOException {
	os.writeObject(data);
	os.flush();
	logger.log(1, "SockConn:: "+cid+" Sent object and flushed OS: "+
		   (data != null ? data.getClass().getName() : "NULL"));
    }
    
    /** Writes an object to the socket's output stream but timeout
     * if not accepted within the specified period. The encapsulated
     * Socket has its SO timeout set to the specified parameter, after
     * reading from the Socket this is reset to the original value if any. 
     * @param timeout The timeout interval (msecs).  
     * @param data    The object to send.
     * @exception IOException If any error occurs while writing to
     * the output stream.*/
    public void send(Object data, long timeout) throws IOException {
	int original = socket.getSoTimeout();
	Object obj = null;

	if ((long)original != timeout) {
	    try {
		socket.setSoTimeout((int)timeout);
	    } catch (SocketException s) {
		throw new IOException("SockConn:: "+cid+" Failed to set socket timeout: "+s);
	    }
	}
	os.writeObject(data);
	os.flush();
	logger.log(1, "SockConn:: "+cid+" Sent objert and flushed OS: "+
		   (data != null ? data.getClass().getName() : "NULL"));
	try {
	    socket.setSoTimeout(original);
	} catch (SocketException s) {
	    throw new IOException("Failed to reset socket timeout: "+s);
	}
    }
    
    /** @return The object retrieved from the socket's input stream.
     * @exception IOException If any error occurs while reading the 
     * input stream.*/
    public Object receive() throws IOException {
	Object data = null;
	try {
	    logger.log(1, "SockConn:: "+cid+" Calling readObj on IS..");
	    data = is.readObject();
	    logger.log(1, "SockConn:: "+cid+" received: "+(data != null ? data.getClass().getName() : "NULL"));
	} catch (ClassNotFoundException c) {
	    throw new IOException("Error resolving serialized object: "+c);
	} catch (Exception e) {
	    logger.log(1, "SockConn:: "+cid+" An exception occurred reading Object: "+e);	  
	}
	return data;
    }
    
    /** Receive data from other end of connection but timeout
     * if not received within the specified period. The encapsulated
     * Socket has its SO timeout set to the specified parameter, after
     * reading from the Socket this is reset to the original value if any. 
     * @param timeout The timeout interval (msecs).  
     * @exception IOException If any IO related problems occur.
     * @exception InterruptedIOException If the connection times out.*/
    public Object receive(long timeout) throws IOException {
	int original = socket.getSoTimeout();
	Object data = null;

	if ((long)original != timeout) {
	    try {
		socket.setSoTimeout((int)timeout);
	    } catch (SocketException s) {
		throw new IOException("SockConn:: "+cid+" Failed to set socket timeout: "+s);
	    }
	}
	try {	   
	    logger.log(1, "SockConn:: "+cid+" Calling readObj on IS.."); 
	    data = is.readObject();  
	    logger.log(1, "SockConn:: "+cid+" received: "+(data != null ? data.getClass().getName() : "NULL"));
	
	} catch (ClassNotFoundException c) {
	    throw new IOException("SockConn:: "+cid+" Error resolving serialized object: "+c);
	}
	try {
	    socket.setSoTimeout(original);
	} catch (SocketException s) {
	    throw new IOException("SockConn:: "+cid+" Failed to reset socket timeout: "+s);
	}
	return data;
    }



    /** Close down the connection. Closes the socket and the
     * associated input and output streams if not already done.*/
    public void close() {
	logger.log(1,"SockConn:: "+cid+" Connection called close()");
	if ( ! isOpen() ) return;
	open = false;

	try {
	    os.close();
	} catch (IOException e) {}
	os = null;
	try {
	    is.close();
	} catch (IOException e) {}
	is = null;
	try {
	    socket.close();
	} catch (IOException e) {}
	socket = null;
    }
    
    /** @return True if the connection has been opened.*/
    public boolean isOpen() { return open; }
    
    /** Subclasses can override this method to return special subclasses 
     * of java.io.ObjectOutputStream. 
     * @return This default implementation returns a standard
     * java.io.ObjectOutputStream wrapping the socket's outputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected ObjectOutputStream getOutputStream() throws IOException {
	return new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    /** Subclasses can override this method to return special subclasses 
     * of java.io.ObjectInputStream. 
     * @return This default implementation returns a standard
     * java.io.ObjectInputStream wrapping the socket's inputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected ObjectInputStream getInputStream() throws IOException {
	return new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public String toString() {
	return (socket != null ? "ngat.net.SocketConnection: "+socket : host+":"+port);
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2008/07/23 12:41:17  eng
/** Initial revision
/**
/** Revision 1.10  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.9  2004/01/15 16:04:53  snf
/** updated
/**
/** Revision 1.8  2001/07/11 10:50:28  snf
/** backup.
/**
/** Revision 1.7  2001/05/14 15:35:43  snf
/** Modified close to fail siently if already closed.
/**
/** Revision 1.6  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.5  2000/12/20 09:47:34  snf
/** Corrected socketexception handling.
/**
/** Revision 1.4  2000/12/20 09:44:15  snf
/** Added timed receive.
/**
/** Revision 1.3  2000/12/06 10:47:59  snf
/** *** empty log message ***
/**
/** Revision 1.2  2000/12/06 10:46:31  snf
/** Added new constructor.
/**
/** Revision 1.1  2000/12/06 10:43:25  snf
/** Initial revision
/**
/** Revision 1.1  2000/12/04 15:12:53  snf
/** Initial revision
/**
/** Revision 1.1  2000/11/06 13:20:37  snf
/** Initial revision
/** */
 
