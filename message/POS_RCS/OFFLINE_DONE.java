package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: OFFLINE_DONE.<br>
 *  Requests the RCS to pass control over to the Offline mode<br>
 *  via the Science Control Agent until a specified time or<br>
 *  the end of the current RTOC window.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>until - time to switch to Offline mode until.</dd>
 * <dd>&nbsp;values: a valid (ISO 8601 Formatted date/time)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OFFLINE_DONE extends POS_TO_RCS_DONE {

	/** Create a OFFLINE_DONE with specified id.
	 * @param id The unique id of this OFFLINE_DONE.
	 */
	public OFFLINE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [OFFLINE_DONE].

