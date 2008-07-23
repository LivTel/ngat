package ngat.net;

/** Factory for generating appropriate ProtocolImpls for a given protocol.
 * <br><br>
 * $Id: ProtocolImplFactory.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public interface ProtocolImplFactory {

    /** Implementors should return an appropriate client end implementation of the
     * protocol they represent.
     * @param client A generic client - implementors may make more specific.
     * @param connection The connection used for communication with a server.*/
    public ProtocolImpl createClientImpl(Client client, IConnection connection);

    /** Implementors should return an appropriate server end implementation of the
     * protocol they represent.
     * @param connection The connection used for communication with a client.
     * @param factory An appropriate factory for producing RequestHandlers once a
     * specific command/request has been received.*/
    public ProtocolImpl createServerImpl(IConnection connection, RequestHandlerFactory factory);

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
