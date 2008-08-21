package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: FIND_COI_USERS_DONE.<br>
 * Command: FIND_COI_USERS<br>
 * Find the various co-investigators of a proposal.<br>
 * Module code: 701200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>coiUsers - A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email></dd>
 * </dl>
 * <br>
 */
public class FIND_COI_USERS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email>*/
	protected List coiUsers;

	/** Create a FIND_COI_USERS_DONE with specified id.
	 * @param id The unique id of this FIND_COI_USERS_DONE.
	 */
	public FIND_COI_USERS_DONE (String id) { super(id); }

	/** Set the A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email>
	 * @param coiUsers The A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email>.
	 */
	public void setCoiUsers(List coiUsers) { this.coiUsers = coiUsers; }

	/** Get the A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email>
	 * @return The A java.util.List of Strings describing the co-investigators of a proposal. These are formatted as: <TagID> (<UserId>) [<First Name> <Second Name>] email: <Email>
	 */
	public List getCoiUsers() { return coiUsers; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", coiUsers="+coiUsers+"]";
	}
	// Hand generated code.

} // class def. [FIND_COI_USERS_DONE].

