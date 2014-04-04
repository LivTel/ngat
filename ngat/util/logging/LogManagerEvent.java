package ngat.util.logging;

import java.util.*;
/** An Event generated by a LogManager or subclass when the set of
 * Loggers it manages are changed in some way.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/logging/LogManagerEvent.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public class LogManagerEvent extends EventObject {
    
    /** Reference to a Logger which precipatated the event.*/
    protected Logger logger;
    
    /** Create a LogManagerEvent originating from the specified 'source'
     * @param source The originator of the Event.*/
    public LogManagerEvent(Object source) {
	super(source);
    }
    
    /** Set the Logger that was changed.
     * @param logger The Logger that was changed.*/
    public void setLogger(Logger logger) {
	this.logger = logger;
    }
    
    /** Returns the Logger that was changed.
     * @return The Logger that was changed.*/
    public Logger getLogger() { return logger; }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2001/02/23 18:49:51  snf
/** Initial revision
/** */
