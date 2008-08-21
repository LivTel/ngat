package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_PROPOSAL_STATUS.<br>
 * Command: GET_PROPOSAL_STATUS<br>
 * Retrieves a descriptor for a Proposal. This is used to check the current<br>
 * status of a Proposal prior to issuing a subsequent GET_PROPOSAL command.<br>
 * Module code: 701600<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>descriptor - An ngat.phase2.ProposalDescriptor containing details of the requested Proposal</dd>
 * </dl>
 * <br>
 */
public class GET_PROPOSAL_STATUS extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** Create a GET_PROPOSAL_STATUS with specified id.
	 * @param id The unique id of this GET_PROPOSAL_STATUS.
	 */
	public GET_PROPOSAL_STATUS(String id) { super(id); }

	/** Create a GET_PROPOSAL_STATUS with specified id and parameters.
	 * @param id The unique id of this GET_PROPOSAL_STATUS.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public GET_PROPOSAL_STATUS(
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

} // class def. [GET_PROPOSAL_STATUS].

