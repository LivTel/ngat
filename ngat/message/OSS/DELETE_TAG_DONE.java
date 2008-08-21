package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: DELETE_TAG_DONE.<br>
 * Command: DELETE_COI_USER<br>
 * Removes a user as co-investigator of proposal.<br>
 * Module code: 704900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagPath - The path used to locate the TAG in the P2DB</dd>
 * <dd>&nbsp;values: Path to a TAG already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the TAG was removed</dd>
 * </dl>
 * <br>
 */
public class DELETE_TAG_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The True if the TAG was removed*/
	protected boolean removed;

	/** Create a DELETE_TAG_DONE with specified id.
	 * @param id The unique id of this DELETE_TAG_DONE.
	 */
	public DELETE_TAG_DONE (String id) { super(id); }

	/** Set the True if the TAG was removed
	 * @param removed The True if the TAG was removed.
	 */
	public void setRemoved(boolean removed) { this.removed = removed; }

	/** Get the True if the TAG was removed
	 * @return The True if the TAG was removed
	 */
	public boolean getRemoved() { return removed; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", removed="+removed+"]";
	}
	// Hand generated code.

} // class def. [DELETE_TAG_DONE].

