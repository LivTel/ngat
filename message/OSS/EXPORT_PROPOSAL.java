package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: EXPORT_PROPOSAL.<br>
 * Command: EXPORT_PROPOSAL<br>
 * Replaces a Proposal specification after editing. The proposal will<br>
 * not be scheduled as some of its fields may not be set at this point.<br>
 * This command is used to allow sharing of proposal editing task between<br>
 * various co-investigators.<br>
 * Module code: 701100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>replacementProposal -  The replacement (updated) Proposal to replace the one at 'proposalPath'</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete Proposal</dd>
 * <dd>key - The key to unlock the Proposal record. </dd>
 * <dd>&nbsp;values: Valid (>0) key</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class EXPORT_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The  The replacement (updated) Proposal to replace the one at 'proposalPath'*/
	protected Proposal replacementProposal;

	/** The The key to unlock the Proposal record. */
	protected int key;

	/** Create a EXPORT_PROPOSAL with specified id.
	 * @param id The unique id of this EXPORT_PROPOSAL.
	 */
	public EXPORT_PROPOSAL(String id) { super(id); }

	/** Create a EXPORT_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this EXPORT_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param replacementProposal The  The replacement (updated) Proposal to replace the one at 'proposalPath'
	 * @param key The The key to unlock the Proposal record. 
	 */
	public EXPORT_PROPOSAL(
	         String id,
	         Path proposalPath,
	         Proposal replacementProposal,
	         int key) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.replacementProposal = replacementProposal;
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

	/** Set the  The replacement (updated) Proposal to replace the one at 'proposalPath'
	 * @param replacementProposal The  The replacement (updated) Proposal to replace the one at 'proposalPath'
	 */
	public void setReplacementProposal(Proposal replacementProposal) { this.replacementProposal = replacementProposal; }

	/** Get the  The replacement (updated) Proposal to replace the one at 'proposalPath'
	 * @return The  The replacement (updated) Proposal to replace the one at 'proposalPath'
	 */
	public Proposal getReplacementProposal() { return replacementProposal; }

	/** Set the The key to unlock the Proposal record. 
	 * @param key The The key to unlock the Proposal record. 
	 */
	public void setKey(int key) { this.key = key; }

	/** Get the The key to unlock the Proposal record. 
	 * @return The The key to unlock the Proposal record. 
	 */
	public int getKey() { return key; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", replacementProposal="+replacementProposal+
			", key="+key+"]";
	}
	// Hand generated code.

} // class def. [EXPORT_PROPOSAL].

