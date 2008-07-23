package ngat.net;

/** Abstract base class for SlotServers.
 * <br><br>
 * $Id: SlotServer.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 */
public class SlotServer implements Server {

    /** A factory for generating ProtocolImpls.*/
    protected ProtocolImplFactory piFactory;

    /** A factory for generating RequestHandlers.*/
    protected RequestHandlerFactory rhFactory;

    /** Create a SlotServer using the specified factories.
     * @param piFactory A factory for generating ProtocolImpls.
     * @param rhFactory A factory for generating RequestHandlers.
     */
    public SlotServer(ProtocolImplFactory piFactory, RequestHandlerFactory rhFactory) {
	this.piFactory = piFactory;
	this.rhFactory = rhFactory;
    }

    /** The server does not have to do anything as the client has
     * direct access to its object reference ! This method does
     * nothing at all now. It may be used to switch the server on/off later ?*/
    public void listen() {}

    /** Create a SlotServerConnectionThread using the server's
     * pre-set ProtocolImplFactory and RequestHandlerFactory to create a ProtocolImpl
     * on the supplied connection and returns a reference to the thread.
     * @param connection The connection for communication.
     * @return The connection thread.
     */
    public SlotServerConnectionThread createServerConnectionThread(SlotBuffer inSlot, SlotBuffer outSlot) {
	IConnection connection = new SlotConnection(inSlot, outSlot);
	return (SlotServerConnectionThread)createServerConnectionThread
	    (piFactory.createServerImpl(connection, rhFactory));
    }

    /** Creates a ServerConnectionThread using the specified ProtocolImpl.
     * @param implementor The ProtocolImpl to use for the connection.*/ 
    public ServerConnectionThread createServerConnectionThread(ProtocolImpl implementor) {
	return new SlotServerConnectionThread(this, implementor);
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/12/12 12:33:49  snf
/** Changed createSCT to use 2 buffers in/out.
/**
/** Revision 1.1  2000/12/12 10:48:45  snf
/** Initial revision
/** */
