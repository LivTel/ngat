package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: DELETE_USER_DONE.<br>
 * Command: DELETE_USER<br>
 * Removes a user as principle-investigator of proposal.<br>
 * Module code: 705100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the User in the P2DB</dd>
 * <dd>&nbsp;values: Path to a User already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the User was removed</dd>
 * </dl>
 * <br>
 */
public class DELETE_USER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The True if the User was removed*/
	protected boolean removed;

	/** Create a DELETE_USER_DONE with specified id.
	 * @param id The unique id of this DELETE_USER_DONE.
	 */
	public DELETE_USER_DONE (String id) { super(id); }

	/** Set the True if the User was removed
	 * @param removed The True if the User was removed.
	 */
	public void setRemoved(boolean removed) { this.removed = removed; }

	/** Get the True if the User was removed
	 * @return The True if the User was removed
	 */
	public boolean getRemoved() { return removed; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", removed="+removed+"]";
	}
	// Hand generated code.

} // class def. [DELETE_USER_DONE].

