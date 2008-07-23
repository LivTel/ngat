package ngat.net;

/**
 * Generic interface for all classes which wish to act as clients
 * using any Protocol. Concrete classes should typically be
 * written for each Command/request type to be sent (and handled). An
 * abstract base class ClientImpl can be extended to provide
 * the appropriate functionality. The base class uses an IConnection
 * to send and receive data via a ClientConnectionThread to which the
 * concrete Client object should be passed as a handler.
 * <br>
 * $Id: Client.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public interface Client extends ExceptionCallbackHandler {

    /** Concrete classes must implement the behaviour of the client
     * via this method.*/
    public void exec();
    
    /** Called (usually by a ProtocolImpl) to cause the Client to
     * send its reequest / command to a server.*/
    public void despatchRequest();

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/12/04 13:22:28  snf
/** Upgraded.
/**
/** Revision 1.1  2000/11/23 10:20:58  snf
/** Initial revision
/** */
