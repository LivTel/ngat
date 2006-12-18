package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: SET_WINDOWS_DONE.<br>
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
public class SET_WINDOWS_DONE extends POS_TO_RCS_DONE {

	/** Create a SET_WINDOWS_DONE with specified id.
	 * @param id The unique id of this SET_WINDOWS_DONE.
	 */
	public SET_WINDOWS_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SET_WINDOWS_DONE].

