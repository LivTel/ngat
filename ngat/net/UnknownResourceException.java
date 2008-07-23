package ngat.net;


/** This Exception is thrown when a Factory is unable to obtain
 * a nominated resource - the resource is <b>anything</b> the factory
 * needs to carry out its designated construction task. 
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: UnknownResourceException.java,v 1.1 2008-07-23 12:41:17 eng Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/net/UnknownResourceException.java,v $
 * </dl>
 * @author $Author: eng $
 * @version $Revision: 1.1 $
 */
public class UnknownResourceException extends Exception {
    
    /** Create an UnknownResourceException with no message.*/
    public UnknownResourceException() {
	super();
    }
    
    /** Create an UnknownResourceException with the given message.
     * @param message The message for this Exception.*/
    public UnknownResourceException(String message) {
	super(message);
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2004/01/15 16:04:53  snf
/** Initial revision
/** */
