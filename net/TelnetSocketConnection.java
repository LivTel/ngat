package ngat.net;

import java.io.*;
import java.net.*;
import java.text.*;

import ngat.util.*;

/** Implements an abstraction of a TCP socket TELNET connection. The Socket
 * used is not 'live' until the open() method is called - this state
 * can always be checked by calling isOpen(). The overridden constructor
 * TelnetSocketConnection(java.net.Socket) allows a pre-built Socket to be
 * used and opens the connection automatically - this means that the
 * TelnetSocketConnection is live immediately without having to call open(). 
 * It is intended for use at the server end.
 *
 * $Id$
 *
 */
public class TelnetSocketConnection implements IConnection {
    
    /** The input stream for receiving data. */ 
    protected BufferedReader is;

    /** The output stream for sending data to.*/
    protected PrintWriter os;
    
    /** The socket created by this connection.*/
    protected Socket socket;
    
    /** The host name.*/
    protected String host;
    
    /** The remote port.*/
    protected int port;
    
    /** Indicates whether the socket has been opened.*/
    protected boolean open;

      protected String cid;

    /** Create a TelnetSocketConnection object for specified address.
     * @param host The host name.
     * @param port The port to connect to at the named host.
     */
    public TelnetSocketConnection(String host, int port) {
	this.host = host;
	this.port = port;
	open = false;
    }

    /** Create a TelnetSocketConnection for the specified address.
     * @param port The port to connect to at the named host.
     * @param address A pre-built InetAddress for the host.*/
    public TelnetSocketConnection(InetAddress address, int port) {
	this(address.getHostName(), port);	
    }
    
    /** Creates a TelnetSocketConnection on an already built Socket and
     * opens it as the server end. <b>Do not</b> call open() on
     * the connection after using this constructor.
     * @param socket The pre-built Socket to use.
     * @exception IOException If any problem occurs opening the
     * I/O streams or the Socket is null.*/
    public TelnetSocketConnection(Socket socket) throws IOException {
	if (socket == null) throw new IOException("Socket not defined");
	this.socket = socket;
	try {
	    //is = new ObjectInputStream(socket.getInputStream());
	    is = getInputReader();
	} catch (IOException e) {
	    throw new ConnectException("Error opening input stream:"+e);
	}
	
	try {
	    //os = new ObjectOutputStream(socket.getOutputStream());
	    os = getOutputWriter();
	    os.flush();
	} catch (IOException e) {
	    throw new ConnectException("Error opening output stream:"+e);
	}
	open = true;
    }
    

    /** Set the id for this connection.*/
    public void setConnectionName(String cid) {
	this.cid = cid;
    }



    /** Establishes the TelnetSocket connection to the server.
     * @exception ConnectException If the connection is already open or any
     * error occurs while opening the I/O streams.*/
    public void open() throws ConnectException {
	if (open) throw new ConnectException("Connection already open");
	try {
	    socket = new Socket(host, port);
	} catch (UnknownHostException ue) {
	    throw new ConnectException("Unable to connect to "+host+":"+port+" :"+ue);
	} catch (IOException e) {
	    throw new ConnectException("Unable to connect to "+host+":"+port+" :"+e);
	}
	
	try {
	    os = getOutputWriter();
	    os.flush();
	} catch (IOException e) {
	    throw new ConnectException("Error opening output stream:"+e);
	}
	
	try {
	    is = getInputReader();
	} catch (IOException e) {
	    throw new ConnectException("Error opening input stream:"+e);
	}
	open = true;
	
    }
    
    /** Writes an object to the socket's output stream.
     * @param data The String to send.
     * @exception IOException If any error occurs while writing to
     * the output stream.*/
    public void send(Object data) throws IOException {
	if (! (data instanceof String))
	    throw new IOException("Not a String: "+(data != null ? data.getClass().getName() : "NULL DATA"));
	
	os.println((String)data);
	os.flush();
    }
    

    /** Writes an object to the socket's output stream.
     * @param data The String to send.
     * @param timeout The time to wait on send.
     * @exception IOException If any error occurs while writing to
     * the output stream.*/
    public void send(Object data, long timeout) throws IOException {
	if (! (data instanceof String))
	    throw new IOException("Not a String: "+(data != null ? data.getClass().getName() : "NULL DATA"));

	int original = socket.getSoTimeout();
	Object obj = null;

	if ((long)original != timeout) {
	    try {
		socket.setSoTimeout((int)timeout);
	    } catch (SocketException s) {
		throw new IOException("Failed to set socket timeout: "+s);
	    }
	}

	os.println((String)data);
	os.flush();

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
	return (String)is.readLine();
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
	String str = null;

	if ((long)original != timeout) {
	    try {
		socket.setSoTimeout((int)timeout);
	    } catch (SocketException s) {
		throw new IOException("Failed to set socket timeout: "+s);
	    }
	}
	
	str = is.readLine();
	
	try {
	    socket.setSoTimeout(original);
	} catch (SocketException s) {
	    throw new IOException("Failed to reset socket timeout: "+s);
	}
	return str;
    }



    /** Close down the connection. Closes the socket and the
     * associated input and output streams if not already done.*/
    public void close() {
	System.err.println("SockConn called close()");
	if ( ! isOpen() ) return;
	open = false;

	os.close();	
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
     * of java.io.DataOutputStream. 
     * @return This default implementation returns a standard
     * java.io.PrintWriter wrapping the socket's outputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected PrintWriter getOutputWriter() throws IOException {
	return new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
    }
    
    /** Subclasses can override this method to return special subclasses 
     * of java.io.DataInputStream. 
     * @return This default implementation returns a standard
     * java.io.BufferedReader wrapping the socket's inputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected BufferedReader getInputReader() throws IOException {
	return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    /** Callable program. Sends the message specified in the command string to the 
     * host and port specified.
     */
    public static void main(String args[]) {

	CommandParser parser = new CommandParser();

	try {
	    parser.parse(args);	    

	    ConfigurationProperties map = parser.getMap();

	    String host = map.getProperty("host");
	    
	    int    port = map.getIntValue("port");

	    TelnetSocketConnection telnet = new TelnetSocketConnection(host, port);

	    try {
		telnet.open();
	    } catch (IOException iox) {
		System.err.println("TelnetSocketConnection::Opening connection:"+
				   " Host: "+host+" Port: "+port+" Error: "+iox); 
		return;
	    }
	    
	    String mesg = map.getProperty("mesg");
	    
	    try {
		telnet.send(mesg, 0L);
	    } catch (IOException iox) {
		System.err.println("TelnetSocketConnection::Sending Message:"+
				   mesg+" Error: "+iox); 
		return;
	    }

	    telnet.close();

	    telnet = null;

	} catch (ParseException px) {
	    System.err.println("TelnetSocketConnection::Parsing line: "+px);
	    return;
	}	
    
    }


}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2006/11/23 10:34:13  snf
/** test
/**
/** Revision 1.2  2004/01/15 16:04:53  snf
/** updated
/**
/** Revision 1.1  2001/09/03 11:35:08  snf
/** Initial revision
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
 
