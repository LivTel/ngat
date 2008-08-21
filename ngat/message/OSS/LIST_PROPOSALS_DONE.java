package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: LIST_PROPOSALS_DONE.<br>
 * Command: LIST_PROPOSALS<br>
 * Returns a list of Proposals owned (or co-owned) by the user.<br>
 * Module code: 703700  <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to identify the User in the P2DB</dd>
 * <dd>&nbsp;values: A valid user</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>proposalList - A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.</dd>
 * <dd>userList - A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.</dd>
 * </dl>
 * <br>
 */
public class LIST_PROPOSALS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.*/
	protected List proposalList;

	/** The A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.*/
	protected List userList;

	/** Create a LIST_PROPOSALS_DONE with specified id.
	 * @param id The unique id of this LIST_PROPOSALS_DONE.
	 */
	public LIST_PROPOSALS_DONE (String id) { super(id); }

	/** Set the A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.
	 * @param proposalList The A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user..
	 */
	public void setProposalList(List proposalList) { this.proposalList = proposalList; }

	/** Get the A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.
	 * @return The A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.
	 */
	public List getProposalList() { return proposalList; }

	/** Set the A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.
	 * @param userList The A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList..
	 */
	public void setUserList(List userList) { this.userList = userList; }

	/** Get the A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.
	 * @return The A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.
	 */
	public List getUserList() { return userList; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalList="+proposalList+
			", userList="+userList+"]";
	}
	// Hand generated code.

} // class def. [LIST_PROPOSALS_DONE].

