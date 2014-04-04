package ngat.net;

/** Classes which wish to acts as handlers for requests in the
 * context of a server should implement this interface.
 * <br><br>
 * $Id$
 */
public interface RequestHandler extends ExceptionCallbackHandler {

    /** Implementors should handle their request appropriately.*/
    public void handleRequest();

    /** Implementors should provide an indication of the expected
     * execution time of the handleRequest() method in msecs.*/
    public long getHandlingTime();

    /** Called to allow the handler to release resources before disposal.*/
    public void dispose();

}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2001/05/14 15:35:43  snf
/** Added cancel method implementation.
/**
/** Revision 1.3  2001/02/23 18:50:23  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/02/02 08:51:13  snf
/** Added constructor using ConnectionFactory.
/**
/** Revision 1.1  2000/12/04 13:03:43  snf
/** Initial revision
/** */
