package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_GROUP_DONE.<br>
 * Command: ADD_GROUP.<br>
 * Adds a Group of observations to the named Proposal.<br>
 * Module code: 700200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>group - Group to add to Proposal</dd>
 * <dd>&nbsp;values: A valid Group</dd>
 * <dd>proposalPath - path of the Proposal to add Group to</dd>
 * <dd>&nbsp;values: A valid Proposal path</dd>
 * <dd>srcMap - mapping between Observations (in Group) and Source IDs in existing Proposal</dd>
 * <dd>&nbsp;values: A mapping</dd>
 * <dd>icMap - mapping between Observations (in Group) and InstConfig IDs in existing Proposal</dd>
 * <dd>&nbsp;values: A mapping</dd>
 * <dd>tcMap - mapping between Observations (in Group) and TeleConfig IDs in existing Proposal</dd>
 * <dd>&nbsp;values: A mapping</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_GROUP_DONE extends TRANSACTION_DONE {

	/** Create a ADD_GROUP_DONE with specified id.
	 * @param id The unique id of this ADD_GROUP_DONE.
	 */
	public ADD_GROUP_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_GROUP_DONE].

