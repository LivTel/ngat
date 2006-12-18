package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: USERID_DONE.<br>
 *  Sets the current USerID for FITS headers Used at the start<br>
 *  of a time slot in a window to indicate that a new User is<br>
 *  now in control of the telescope.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userId - Id of a school or other controlling user.</dd>
 * <dd>&nbsp;values: a unique (per School) ID</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class USERID_DONE extends POS_TO_RCS_DONE {

	/** Create a USERID_DONE with specified id.
	 * @param id The unique id of this USERID_DONE.
	 */
	public USERID_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [USERID_DONE].

