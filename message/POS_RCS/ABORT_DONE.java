package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: ABORT_DONE.<br>
 *  Terminates a specified, currently executing or pending request.<br>
 *  Pending requests are just removed from the queue. Executing requests<br>
 *  are stopped in mid action where this is possible.<br>
 *  Module code: 690100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>requestCode - reference code of request to abort.</dd>
 * <dd>&nbsp;values: a valid acknowledgement reference number returned by any accepted request</dd>
 * <dd>all - True if ALL pending and executing requests should be aborted.</dd>
 * <dd>&nbsp;values: { True | False }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ABORT_DONE extends POS_TO_RCS_DONE {

	/** Create a ABORT_DONE with specified id.
	 * @param id The unique id of this ABORT_DONE.
	 */
	public ABORT_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ABORT_DONE].

