package ngat.util;

/** Implementors should provide a hook to an object which can return status-entry information.
 *
 * <dl>	
 * <dt><b>RCS:</b>
 * <dd>$Id$
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StatusCategoryGrabber.java,v $
 * </dl>
 * @author $Author$
 * @version $Revision$
 */
public interface StatusCategoryGrabber {
    
    /** Returns a StatusEntryGrabber for the specified category.*/
    public StatusCategory getStatusCategory(String cat) throws IllegalArgumentException;
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2004/01/15 16:01:57  snf
/** Initial revision
/** */
