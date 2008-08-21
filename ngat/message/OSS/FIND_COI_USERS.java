package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: FIND_COI_USERS.<br>
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
public class FIND_COI_USERS extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** Create a FIND_COI_USERS with specified id.
	 * @param id The unique id of this FIND_COI_USERS.
	 */
	public FIND_COI_USERS(String id) { super(id); }

	/** Create a FIND_COI_USERS with specified id and parameters.
	 * @param id The unique id of this FIND_COI_USERS.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public FIND_COI_USERS(
	         String id,
	         Path proposalPath) {
	         super(id);
	           this.proposalPath = proposalPath;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+"]";
	}
	// Hand generated code.

} // class def. [FIND_COI_USERS].

