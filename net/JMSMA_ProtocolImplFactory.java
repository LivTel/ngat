package ngat.net;

/** Factory for generating ProtocolImplementors for the JMS (MA) 
 * multiple acknowledge protocol.
 * <br><br>
 * $Id$
 */
public class JMSMA_ProtocolImplFactory implements ProtocolImplFactory {

    /** Singleton instance of this class.*/
    private static JMSMA_ProtocolImplFactory instance = null;

    /** @return The singleton instance of JMSMA_ProtocolImplFactory.*/
    public static JMSMA_ProtocolImplFactory getInstance() {
	if (instance == null)
	    instance = new JMSMA_ProtocolImplFactory();
	return instance;
    }

    /** Private constructor to generate singleton instance.*/
    private JMSMA_ProtocolImplFactory() {}


    /** @return A JMS(MA) client-end protocol implementor.
     * @param client A generic client - implementors may make more specific.
     * @param connection The connection used for communication with a server.*/
    public ProtocolImpl createClientImpl(Client client, IConnection connection) {
	return new JMSMA_ProtocolClientImpl((JMSMA_Client)client, connection);
    }

    /** @return A JMS(MA) server-end protocol implementor.
     * @param connection The connection used for communication with a client.
     * @param factory An appropriate factory for producing RequestHandlers once a
     * specific command/request has been received.*/
    public ProtocolImpl createServerImpl(IConnection connection, RequestHandlerFactory factory) {
	return new JMSMA_ProtocolServerImpl(connection, factory);
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.3  2006/11/23 11:42:41  snf
/** test.
/**
/** Revision 1.2  2000/12/06 09:35:55  snf
/** Made singleton methods.
/**
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
