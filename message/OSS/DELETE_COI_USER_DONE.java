package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: DELETE_COI_USER_DONE.<br>
 * Command: DELETE_COI_USER<br>
 * Removes a user as co-investigator of proposal.<br>
 * Module code: 701000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>userId - The uid for the user who is being deleted as CoI</dd>
 * <dd>&nbsp;values: UserId of a registered user already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the user was removed as co-investigator. False if they were not co-investigator anyway</dd>
 * </dl>
 * <br>
 */
public class DELETE_COI_USER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The True if the user was removed as co-investigator. False if they were not co-investigator anyway*/
	protected boolean removed;

	/** Create a DELETE_COI_USER_DONE with specified id.
	 * @param id The unique id of this DELETE_COI_USER_DONE.
	 */
	public DELETE_COI_USER_DONE (String id) { super(id); }

	/** Set the True if the user was removed as co-investigator. False if they were not co-investigator anyway
	 * @param removed The True if the user was removed as co-investigator. False if they were not co-investigator anyway.
	 */
	public void setRemoved(boolean removed) { this.removed = removed; }

	/** Get the True if the user was removed as co-investigator. False if they were not co-investigator anyway
	 * @return The True if the user was removed as co-investigator. False if they were not co-investigator anyway
	 */
	public boolean getRemoved() { return removed; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", removed="+removed+"]";
	}
	// Hand generated code.

} // class def. [DELETE_COI_USER_DONE].

