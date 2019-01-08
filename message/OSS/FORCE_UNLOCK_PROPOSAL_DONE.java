package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: FORCE_UNLOCK_PROPOSAL_DONE.<br>
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
public class FORCE_UNLOCK_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a FORCE_UNLOCK_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this FORCE_UNLOCK_PROPOSAL_DONE.
	 */
	public FORCE_UNLOCK_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [FORCE_UNLOCK_PROPOSAL_DONE].

