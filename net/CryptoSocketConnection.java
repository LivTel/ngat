package ngat.net;

import java.io.*;
import java.net.*;
import javax.net.*;

/** Implements an abstraction of a TCP socket connection using 
 * CryptoOutput and CryptoInput streams.
 *
 * $Id$
 *
 */
public class CryptoSocketConnection extends SocketConnection {
    
    /** Create a SocketConnection object for specified address.
     * @param host The host name.
     * @param port The port to connect to at the named host.
     */
    public CryptoSocketConnection(String host, int port) {
	super(host, port);
    }

    /** Create a CryptoSocketConnection for the specified address.
     * @param port The port to connect to at the named host.
     * @param address A pre-built InetAddress for the host.*/
    public CryptoSocketConnection(InetAddress address, int port) {
	super(address, port);	
    }    
    
    /** Create a SocketConnection object for specified address.
     * @param host The host name.
     * @param port The port to connect to at the named host.
     * @param sf   An optional SocketFactory.
     */
    public CryptoSocketConnection(String host, int port, SocketFactory sf) {
	super(host, port, sf);
	open = false;
    }

    /** Creates a CryptoSocketConnection on an already built Socket and
     * opens it as the server end. <b>Do not</b> call open() on
     * the connection after using this constructor.
     * @param socket The pre-built Socket to use.
     * @exception IOException If any problem occurs opening the
     * I/O streams or the Socket is null.*/
    public CryptoSocketConnection(Socket socket) throws IOException {
	super(socket);
    }
    
    /** Overridden to return a CryptoOutputStream
     * @return A CryptoOutputStream wrapping the socket's outputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected ObjectOutputStream getOutputStream() throws IOException {
	return new CryptoOutputStream(socket.getOutputStream());
    }

    /** Overridden to return a CryptoInputStream.
     * @return A CryptoInputStream wrapping the socket's inputstream.
     * @exception IOException if an error occurs while opening the stream.
     */
    protected ObjectInputStream getInputStream() throws IOException {
	return new CryptoInputStream(socket.getInputStream());
    }


}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/07/11 10:50:28  snf
/** backup.
/**
/** Revision 1.1  2001/02/23 18:50:23  snf
/** Initial revision
/** */
 
