package ngat.util;


/** Exception thrown by any implementation of Queue when an attempt is made to remove
 * an object when none is available.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id: QueueEmptyException.java,v 1.1 2001-07-11 10:24:23 snf Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/QueueEmptyException.java,v $
 * </dl>
 * @author $Author: snf $
 * @version $Revision: 1.1 $
 */
public class QueueEmptyException extends Exception {

    /** Create a QueueEmptyException.*/
    public QueueEmptyException() { super(); }
    
    /** Create a QueueEmptyException with the specified message.
     * @param message The message to set.*/
    public QueueEmptyException(String message) { super(message); }

}

/** $Log: not supported by cvs2svn $ */
