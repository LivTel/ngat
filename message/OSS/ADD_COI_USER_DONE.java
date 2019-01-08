package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_COI_USER_DONE.<br>
 * Command: ADD_COI_USER<br>
 * Adds a user as co-investigator to existing proposal.<br>
 * Module code: 700100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB. </dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>userId - The uid for the user who is being added as CoI.   </dd>
 * <dd>&nbsp;values: UserId of a registered user already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>added - True if the user was added. False if the user was already co-i.</dd>
 * </dl>
 * <br>
 */
public class ADD_COI_USER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The True if the user was added. False if the user was already co-i.*/
	protected boolean added;

	/** Create a ADD_COI_USER_DONE with specified id.
	 * @param id The unique id of this ADD_COI_USER_DONE.
	 */
	public ADD_COI_USER_DONE (String id) { super(id); }

	/** Set the True if the user was added. False if the user was already co-i.
	 * @param added The True if the user was added. False if the user was already co-i..
	 */
	public void setAdded(boolean added) { this.added = added; }

	/** Get the True if the user was added. False if the user was already co-i.
	 * @return The True if the user was added. False if the user was already co-i.
	 */
	public boolean getAdded() { return added; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", added="+added+"]";
	}
	// Hand generated code.

} // class def. [ADD_COI_USER_DONE].

