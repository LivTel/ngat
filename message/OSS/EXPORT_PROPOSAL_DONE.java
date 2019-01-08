package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: EXPORT_PROPOSAL_DONE.<br>
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
public class EXPORT_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a EXPORT_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this EXPORT_PROPOSAL_DONE.
	 */
	public EXPORT_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [EXPORT_PROPOSAL_DONE].

