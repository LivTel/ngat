package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: POS_TO_RCS_DONE.<br>
 *  Base class for messaging between POS and RCS.<br>
 *  Module code: 690000.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>requestNumber - unique ID for this request.</dd>
 * <dd>&nbsp;values: a unique value</dd>
 * <dd>controllerAddress - Address of the client (RTOC).</dd>
 * <dd>&nbsp;values: a valid IP Address</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class POS_TO_RCS_DONE extends POS_TO_RCS_DONE {

	/** Create a POS_TO_RCS_DONE with specified id.
	 * @param id The unique id of this POS_TO_RCS_DONE.
	 */
	public POS_TO_RCS_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [POS_TO_RCS_DONE].

