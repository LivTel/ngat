package ngat.net;

import java.io.*;
import java.net.*;

/** 
 * Provides a general Connection based interface. Implementations
 * should provide mechanisms for establishing and tearing down
 * a connection and for sending and receiving serialized or other
 * forms of data e.g. passing via a channel. The send and receive
 * methods should support blocking i/o.
 * <br>
 * $Id: IConnection.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public interface IConnection {
    
    /** Set the id for this connection.*/
    public void setConnectionName(String cid);

    /** Establish a connection.   
     * @exception ConnectException If the connection fails.*/
    public void   open() throws ConnectException;
    
    /** Send data to other end of connection.
     * @exception IOException If any IO related problems occur.*/
    public void   send(Object data) throws IOException;
    
    /** Send data to other end of connection but timeout
     * if not accepted within the specified period. Concrete
     * implementions may choose not to support this operation
     * if it is not feasable to do so - they should simply delegate
     * to the send() method.
     * @param timeout The timeout interval (msecs).  
     * @exception IOException If any IO related problems occur.
     * @exception InterruptedIOException If the connection times out.
     */
    public void   send(Object data, long timeout) throws IOException;
    
    /** Receive data from other end of connection.  
     * @exception IOException If any IO related problems occur.*/
    public Object receive()  throws IOException;

    /** Receive data from other end of connection but timeout
     * if not received within the specified period. Concrete
     * implementions may choose not to support this operation
     * if it is not feasable to do so - they should simply delegate
     * to the receive() method.
     * @param timeout The timeout interval (msecs).  
     * @exception IOException If any IO related problems occur.
     * @exception InterruptedIOException If the connection times out.*/
    public Object receive(long timeout) throws IOException;
    
    /** Tear down connection and release resources.*/
    public void   close();
    
    /** Should return true if the connection has been established.*/
    public boolean isOpen();
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2000/12/20 09:44:53  snf
/** Added timed receive.
/**
/** Revision 1.3  2000/12/04 17:23:54  snf
/** Added connected() method.
/**
/** Revision 1.2  2000/11/23 10:23:28  snf
/** Changed connect /discoonnect to open/close.
/**
/** Revision 1.1  2000/11/23 10:22:10  snf
/** Initial revision
/** */
