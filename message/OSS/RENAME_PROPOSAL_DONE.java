package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: RENAME_PROPOSAL_DONE.<br>
 * Command: RENAME_PROPOSAL<br>
 *  Rename a proposal<br>
 * Module code: 704800<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>newName -  The new proposal name</dd>
 * <dd>&nbsp;values:  A valid name</dd>
 * <dd>newPath - Path to new location</dd>
 * <dd>&nbsp;values: A valid TAG/User path</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class RENAME_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a RENAME_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this RENAME_PROPOSAL_DONE.
	 */
	public RENAME_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [RENAME_PROPOSAL_DONE].

