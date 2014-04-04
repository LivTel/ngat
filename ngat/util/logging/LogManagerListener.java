package ngat.util.logging;

import java.util.*;
/** Interface to be implemented by any classes which wish to be notified
 * when a LogManager's list of Loggers and other properties are changed.
 *
 * <dl>
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/logging/LogManagerListener.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public interface LogManagerListener extends EventListener {

    /** Called when a LogManager has a Logger added.*/
    public void loggerAdded(LogManagerEvent ev);

    /** Called when a LogManager's Logger is changed in some way.
     * The specific Logger is obtained via LogManagerEvent.getLogger().*/
    public void loggerChanged(LogManagerEvent ev);

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2001/02/23 18:49:51  snf
/** Initial revision
/** */
