package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: SET_WINDOWS.<br>
 *  Replaces the current operations scheduled windows<br>
 *  at the RCS.<br>
 * <br>
 *  Module code: 692700<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>windows - set of schedule windows</dd>
 * <dd>&nbsp;values: sorted set of windows information</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_WINDOWS extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates that there is an error in the windows*/
	public static final int ILLEGAL_WINDOWS = 692701;

	// Variables.

	/** The set of schedule windows*/
	protected TreeSet windows;

	/** Create a SET_WINDOWS with specified id.
	 * @param id The unique id of this SET_WINDOWS.
	 */
	public SET_WINDOWS(String id) { super(id); }

	/** Create a SET_WINDOWS with specified id and parameters.
	 * @param id The unique id of this SET_WINDOWS.
	 * @param windows The set of schedule windows
	 */
	public SET_WINDOWS(
	         String id,
	         TreeSet windows) {
	         super(id);
	           this.windows = windows;
	         }

	/** Set the set of schedule windows
	 * @param windows The set of schedule windows
	 */
	public void setWindows(TreeSet windows) { this.windows = windows; }

	/** Get the set of schedule windows
	 * @return The set of schedule windows
	 */
	public TreeSet getWindows() { return windows; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", windows="+windows+"]";
	}
	// Hand generated code.

} // class def. [SET_WINDOWS].

