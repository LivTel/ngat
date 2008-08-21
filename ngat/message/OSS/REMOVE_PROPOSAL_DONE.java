package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REMOVE_PROPOSAL_DONE.<br>
 * Command: REMOVE_PROPOSAL<br>
 * Remove the Proposal at the specified path.<br>
 * <br>
 * Module code: 704200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal's owner in the P2DB</dd>
 * <dd>&nbsp;values: Path to a Proposal already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class REMOVE_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a REMOVE_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this REMOVE_PROPOSAL_DONE.
	 */
	public REMOVE_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [REMOVE_PROPOSAL_DONE].

