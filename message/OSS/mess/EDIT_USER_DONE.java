package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: EDIT_USER_DONE.<br>
 * Command: EDIT_USER<br>
 * Modify a stored User's accounting info<br>
 * <br>
 * Module code: 705400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>user - The User to edit</dd>
 * <dd>&nbsp;values: A valid and complete User</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class EDIT_USER_DONE extends TRANSACTION_DONE {

	/** Create a EDIT_USER_DONE with specified id.
	 * @param id The unique id of this EDIT_USER_DONE.
	 */
	public EDIT_USER_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [EDIT_USER_DONE].

