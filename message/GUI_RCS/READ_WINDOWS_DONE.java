package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: READ_WINDOWS_DONE.<br>
 *  Request the current operations scheduled windows<br>
 *  from the RCS. The windows are returned as a<br>
 *  java.util.TreeSet containing the TimeWindows.<br>
 * <br>
 *  Module code: 681100<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>windows - window information</dd>
 * </dl>
 * <br>
 */
public class READ_WINDOWS_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The window information*/
	protected TreeSet windows;

	/** Create a READ_WINDOWS_DONE with specified id.
	 * @param id The unique id of this READ_WINDOWS_DONE.
	 */
	public READ_WINDOWS_DONE (String id) { super(id); }

	/** Set the window information
	 * @param windows The window information.
	 */
	public void setWindows(TreeSet windows) { this.windows = windows; }

	/** Get the window information
	 * @return The window information
	 */
	public TreeSet getWindows() { return windows; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", windows="+windows+"]";
	}
	// Hand generated code.

} // class def. [READ_WINDOWS_DONE].

