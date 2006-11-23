package ngat.util;

/** Implementors should provide a hook to an object which can return status-entry information.
 *
 * <dl>	
 * <dt><b>RCS:</b>
 * <dd>$Id: StatusCategoryGrabber.java,v 1.2 2006-11-23 10:17:09 snf Exp $
 * <dt><b>Source:</b>
 * <dd>$Source: /space/home/eng/cjm/cvs/ngat/util/StatusCategoryGrabber.java,v $
 * </dl>
 * @author $Author: snf $
 * @version $Revision: 1.2 $
 */
public interface StatusCategoryGrabber {
    
    /** Returns a StatusEntryGrabber for the specified category.*/
    public StatusCategory getStatusCategory(String cat) throws IllegalArgumentException;
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2004/01/15 16:01:57  snf
/** Initial revision
/** */
