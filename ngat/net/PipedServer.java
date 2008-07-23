package ngat.net;

/** Abstract base class for PipedServers.
 * <br><br>
 * $Id: PipedServer.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class PipedServer implements Server {

    /** A factory for generating ProtocolImpls.*/
    protected ProtocolImplFactory piFactory;

    /** A factory for generating RequestHandlers.*/
    protected RequestHandlerFactory rhFactory;

    /** Create a PipedServer using the specified factories.
     * @param piFactory A factory for generating ProtocolImpls.
     * @param rhFactory A factory for generating RequestHandlers.
     */
    public PipedServer(ProtocolImplFactory piFactory, RequestHandlerFactory rhFactory) {
	this.piFactory = piFactory;
	this.rhFactory = rhFactory;
    }

    /** The server does not have to do anything as the client has
     * direct access to its object reference ! This method does
     * nothing at all.*/
    public void listen() {}

    /** Create a PipedServerConnectionThread using the server's
     * pre-set ProtocolImplFactory and RequestHandlerFactory to create a ProtocolImpl
     * on the supplied connection and returns a reference to the thread.
     * @param connection The connection for communication.
     * @return The connection thread.
     */
    public PipedServerConnectionThread createServerConnectionThread(IConnection connection) {
	return (PipedServerConnectionThread)createServerConnectionThread
	    (piFactory.createServerImpl(connection, rhFactory));
    }

    /** Creates a ServerConnectionThread using the specified ProtocolImpl.
     * @param implementor The ProtocolImpl to use for the connection.*/ 
    public ServerConnectionThread createServerConnectionThread(ProtocolImpl implementor) {
	return new PipedServerConnectionThread(this, implementor);
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.2  2000/12/04 17:23:54  snf
/** Added connected() method.
/** */
