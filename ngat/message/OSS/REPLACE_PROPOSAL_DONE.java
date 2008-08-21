package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REPLACE_PROPOSAL_DONE.<br>
 * Command: REPLACE_PROPOSAL<br>
 * Replace the Proposal at the specified path with the new supplied one.<br>
 * This command is sent by the PEST when a user has completed editing.<br>
 * The proposal will have been checked as far as possible for consistency<br>
 * and should therefore be immediately schedulable. Any groups in the<br>
 * Proposal will have their various execution time estimates updated <br>
 * before storing.<br>
 * Module code: 702800<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>replacementProposal - The replacement (updated) Proposal to replace the one at 'proposalPath'</dd>
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
public class REPLACE_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a REPLACE_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this REPLACE_PROPOSAL_DONE.
	 */
	public REPLACE_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [REPLACE_PROPOSAL_DONE].

