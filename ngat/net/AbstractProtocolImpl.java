package ngat.net;

/**
 * Abstract class for implementation of the Client or Server end of
 * a connection based Protocol.
 * <br><br>
 * $Id: AbstractProtocolImpl.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 *
 */
public abstract class AbstractProtocolImpl implements ProtocolImpl {

    /** The connection to use.*/
    IConnection connection;

    /** ExceptionHandler for any IOExceptions thrown by the implement() method.*/
    ExceptionCallbackHandler handler;
    
    /** Create an AbstractProtocolImpl using the specified IConnection.*/
    public AbstractProtocolImpl(ExceptionCallbackHandler handler, IConnection connection) {	
	this.handler    = handler;
	this.connection = connection;
    }

    /** Returns the connection in use.*/
    public IConnection getConnection() { return connection; }

    /** Returns the ExceptionHandler in use.*/
    public ExceptionCallbackHandler getHandler() { return handler; }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2001/03/27 09:55:38  cjm
/** Bugfix, replaced ExceptionCallbackhandler (sp) with ExceptionCallbackHandler.
/**
/** Revision 1.1  2000/11/28 11:37:58  snf
/** Initial revision
/** */
