package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: UNLOCK_PROPOSAL_DONE.<br>
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
public class UNLOCK_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a UNLOCK_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this UNLOCK_PROPOSAL_DONE.
	 */
	public UNLOCK_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [UNLOCK_PROPOSAL_DONE].

