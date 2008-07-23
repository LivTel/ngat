package ngat.net;

import ngat.message.base.*;

/**
 * Generic interface for all classes which wish to act as clients
 * using the JMS Protocol. Concrete classes should typically be
 * written for each COMMAND type to be sent (and handled). An
 * abstract base class JMS_ClientImpl can be extended to provide
 * the appropriate functionality. The base class uses an IConnection
 * to send and receive data via a ClientConnectionThread to which the
 * concrete Client object is passed as a handler.
 * <br><br>
 * $Id: JMSMA_Client.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public interface JMSMA_Client extends Client {

    /** Override to send the specified command to a server.*/
    public void sendCommand(COMMAND command);

    /** Override to handle the Acknowledgement from the server.*/
    public void handleAck  (ACK ack);

    /** Override to handle the results (Done) from the server.*/
    public void handleDone (COMMAND_DONE done);

    // These method signatures are used to allow the ProtocolImpl
    // to pass back information as to where and how any I/O error
    // occurred. If a concrete class does not care about the details
    // it can just have these delegate to exceptionOccurred or similar.

    /** Override to handle a failed attempt at connection.*/
    public void failedConnect  (Exception e);
    
    /** Override to handle a failed COMMAND despatch.*/
    public void failedDespatch (Exception e);

    /** Override to handle a failed-to-receive ACK or DONE message.*/
    public void failedResponse  (Exception e);

    /** @return Override to return the current timeout period (msecs).*/
    public long getTimeout();
    
    /** @return Override to return the COMMAND being implemented by this ClientImpl.*/
    public COMMAND getCommand();


}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/12/04 13:52:27  snf
/** Typos fixed.
/**
/** Revision 1.1  2000/12/04 13:21:40  snf
/** Initial revision
/**
/** Revision 1.1  2000/12/04 12:04:03  snf
/** Initial revision
/**
/** Revision 1.2  2000/11/28 11:41:33  snf
/** Modified docs.
/**
/** Revision 1.1  2000/11/23 16:17:37  snf
/** Initial revision
/** */
