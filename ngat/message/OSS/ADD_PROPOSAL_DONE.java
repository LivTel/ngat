package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_PROPOSAL_DONE.<br>
 * Command: ADD_PROPOSAL<br>
 * Add the Proposal at the specified path.<br>
 * <br>
 * Module code: 704100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the Proposal's owner in the P2DB</dd>
 * <dd>&nbsp;values: Path to a user already in the P2DB</dd>
 * <dd>proposal - The Proposal to add</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete Proposal</dd>
 * <dd>password - The password to allow access to the Proposal record. </dd>
 * <dd>&nbsp;values: Valid password</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_PROPOSAL_DONE extends TRANSACTION_DONE {

	/** Create a ADD_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this ADD_PROPOSAL_DONE.
	 */
	public ADD_PROPOSAL_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_PROPOSAL_DONE].

