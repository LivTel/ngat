package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_PROPOSAL_STATUS_DONE.<br>
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
public class GET_PROPOSAL_STATUS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The An ngat.phase2.ProposalDescriptor containing details of the requested Proposal*/
	protected ProposalDescriptor descriptor;

	/** Create a GET_PROPOSAL_STATUS_DONE with specified id.
	 * @param id The unique id of this GET_PROPOSAL_STATUS_DONE.
	 */
	public GET_PROPOSAL_STATUS_DONE (String id) { super(id); }

	/** Set the An ngat.phase2.ProposalDescriptor containing details of the requested Proposal
	 * @param descriptor The An ngat.phase2.ProposalDescriptor containing details of the requested Proposal.
	 */
	public void setDescriptor(ProposalDescriptor descriptor) { this.descriptor = descriptor; }

	/** Get the An ngat.phase2.ProposalDescriptor containing details of the requested Proposal
	 * @return The An ngat.phase2.ProposalDescriptor containing details of the requested Proposal
	 */
	public ProposalDescriptor getDescriptor() { return descriptor; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", descriptor="+descriptor+"]";
	}
	// Hand generated code.

} // class def. [GET_PROPOSAL_STATUS_DONE].

