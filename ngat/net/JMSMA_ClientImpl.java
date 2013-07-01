package ngat.net;

import ngat.message.base.*;
import ngat.util.logging.*;
/**
 * Abstract base class implementing JMS_Client, can be extended
 * to provide the appropriate functionality for specific commands. 
 * Uses an IConnection to send and receive data via a 
 * JMS_ClientConnectionThread to which the concrete Client object 
 * is passed as a handler (typically as <i>this</i>).
 * <br>
 * $Id: JMSMA_ClientImpl.java,v 1.2 2013-07-01 13:26:18 eng Exp $
 *
 */
public abstract class JMSMA_ClientImpl implements JMSMA_Client { 

    /** The connection to use.*/
    protected IConnection connection;

    /** The command to be sent.*/
    protected COMMAND command;

    /** A Thread to execute the ProtocolImpl for this client.*/
    protected ClientConnectionThread cct;

    /** A factory to generate ProtocolImpl for this client.*/
    protected ProtocolImplFactory piFactory;

    /** The ConnectionFactory to use for generating the connection to
     * be used by the ConnectionThread.*/
    ConnectionFactory connFactory;

    /** The timeout period for reading from the connection.*/
    protected volatile long timeout;

    /** Object to synchronize on for access to timeout.*/
    protected transient Object tsynch;

    /** Name/id for the connection if available.*/
    protected String connectionId;

    protected Logger logger;

    /** Create a basic JMS_CLientImpl.*/
    public JMSMA_ClientImpl() {
	tsynch = new Object();
	connectionId = "";
	logger = LogManager.getLogger("JMS");
    }

    /** Create a JMS_CLientImpl on the specified connection.
     * @param c The IConnection used for communication.*/
    public JMSMA_ClientImpl(IConnection c) {
	this();
	this.connection = c;
    }
    
    /** Create a JMSMA_ClientImpl using the specified ConnectionFactory.
     * Before calling exec() the connection must have been created by
     * calling createConnection(String) passing the name/id of the resource
     * to the ConnectionFactory.
     * @param connFactory The ConnectionFactory to use for generating the
     * connection to be used by the ConnectionThread.*/
    public JMSMA_ClientImpl(ConnectionFactory connFactory) {
	this();
	this.connFactory = connFactory;
    }

    /** Create the Connection to be used by the ConnectionThread.
     * @param name The name/id of the resource to connect to.
     * @exception UnknownResourceException If the named resource does not exist.
     */
    public void createConnection(String name) throws UnknownResourceException {
	if (connFactory == null)
	    throw new UnknownResourceException("No ConnectionFactory set");
	logger.log(3, "JMSMA_Client::Create connection for: "+name);
	connection   = connFactory.createConnection(name);
	connectionId = name;
    }
	    
    /** Creates a ClientConnectionThread using a JMSMA_ProtocolImplFactory with
     * this as client and using the current connection. This class is nominated 
     * as the handler for ACK and DONE messages from the server as well as any
     * exception callback messages from the ConnectionThread created.*/
    public void exec() { 
	cct = createClientConnectionThread(piFactory, connection);
	cct.start();
    }
    
    /** Generates a ClientConnectionThread to handle the protocol implementation.
     * @param piFactory A factory to generate a client-end ProtocolImpl.
     * @param connection The connection to the client.
     * @return A thread to handle the client connection protocol.*/
    public ClientConnectionThread createClientConnectionThread(ProtocolImplFactory piFactory,
							       IConnection connection) {
	return new ClientConnectionThread(piFactory.createClientImpl(this,connection));
    }

    /** Sets the value of the COMMAND implemented by this instance of JMSMA_ClientImpl.*/
    public void setCommand(COMMAND command) { this.command = command; }

    /** @return The COMMAND being implemented by this ClientImpl.*/
    public COMMAND getCommand() { return command; }


    /** Sends the specified command to a server via the IConnection 
     * specified by connection.*/
    public void despatchRequest() { sendCommand((COMMAND)command); }
    
    /** Waits for the ClientConnectionThread to complete - forever!*/
    public void waitFor() throws InterruptedException {
	if (cct != null)
	    cct.join();
    }
    

    /** Waits for the ClientConnectionThread to complete but only for
     * the specified period.
     * @param timeout The timeout period (millis).*/
     public void waitFor(long timeout) throws InterruptedException {
	 if (cct != null)
	     cct.join(timeout);
     }
    
    /** Set the timeout period.
     * @param timeout The timeout period (msecs).*/
    public void setTimeout(long timeout) {
	synchronized(tsynch) {
	    this.timeout = timeout;
	}
    }
    
    /** @return The current timeout period (msecs).*/
    public long getTimeout() {
	synchronized(tsynch) {
	    return timeout;
	}
    }
    
    /** Returns the ConnectionId if set.*/
    public String getConnectionId() { return connectionId; }


}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2008/07/23 12:41:17  eng
/** Initial revision
/**
/** Revision 1.6  2007/01/11 10:08:47  snf
/** checking
/**
/** Revision 1.5  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.4  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.3  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.2  2000/12/06 09:41:10  snf
/** Added methods to set/get command.
/**
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
