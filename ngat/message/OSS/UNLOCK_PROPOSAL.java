package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: UNLOCK_PROPOSAL.<br>
 * Command: UNLOCK_PROPOSAL<br>
 * Unlocks a Proposal which has been locked accidentally <br>
 * e.g. when a network error has occurred after locking for edit.<br>
 * Module code: 703300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>key - The key to unlock the Proposal</dd>
 * <dd>&nbsp;values: Valid (>0) key</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class UNLOCK_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The The key to unlock the Proposal*/
	protected int key;

	/** Create a UNLOCK_PROPOSAL with specified id.
	 * @param id The unique id of this UNLOCK_PROPOSAL.
	 */
	public UNLOCK_PROPOSAL(String id) { super(id); }

	/** Create a UNLOCK_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this UNLOCK_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param key The The key to unlock the Proposal
	 */
	public UNLOCK_PROPOSAL(
	         String id,
	         Path proposalPath,
	         int key) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.key = key;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The key to unlock the Proposal
	 * @param key The The key to unlock the Proposal
	 */
	public void setKey(int key) { this.key = key; }

	/** Get the The key to unlock the Proposal
	 * @return The The key to unlock the Proposal
	 */
	public int getKey() { return key; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", key="+key+"]";
	}
	// Hand generated code.

} // class def. [UNLOCK_PROPOSAL].

