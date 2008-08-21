package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: FORCE_UNLOCK_PROPOSAL.<br>
 * Command: FORCE_UNLOCK_PROPOSAL<br>
 * Forces a proposal to unlock after a synchronization problem (To be used with CAUTION).<br>
 * Module code: 701300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class FORCE_UNLOCK_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** Create a FORCE_UNLOCK_PROPOSAL with specified id.
	 * @param id The unique id of this FORCE_UNLOCK_PROPOSAL.
	 */
	public FORCE_UNLOCK_PROPOSAL(String id) { super(id); }

	/** Create a FORCE_UNLOCK_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this FORCE_UNLOCK_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public FORCE_UNLOCK_PROPOSAL(
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

} // class def. [FORCE_UNLOCK_PROPOSAL].

