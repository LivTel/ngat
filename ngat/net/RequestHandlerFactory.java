package ngat.net;

/** Factory for generating handlers for specific requests.
 * <br><br>
 * $Id$
 */
public interface RequestHandlerFactory {
 
    /** Implementors should return an appropriate request handler for the specified
     * protocol implementor and generic request. This method should return null
     * if the ProtocolImpl is null or of the wrong type or the request is null
     * or of the wrong type. <br>
     * ## May throw a RequestHandlerException in future ##.
     * @param implementor The protocol implementor which uses this handler.
     * @param request The generic request / command object to handle.*/
    public RequestHandler createHandler(ProtocolImpl implementor, Object request);

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/12/04 17:23:54  snf
/** Initial revision
/** */
