package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REPLACE_GROUP_DONE.<br>
 * Command: REPLACE_GROUP.<br>
 * Replaces a Group of observations in the named Proposal.<br>
 * Module code: 703600<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>group - Group to add to Proposal</dd>
 * <dd>&nbsp;values: A valid Group</dd>
 * <dd>groupPath - path of the Group to replace</dd>
 * <dd>&nbsp;values: A valid Group path</dd>
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
public class REPLACE_GROUP_DONE extends TRANSACTION_DONE {

	/** Create a REPLACE_GROUP_DONE with specified id.
	 * @param id The unique id of this REPLACE_GROUP_DONE.
	 */
	public REPLACE_GROUP_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [REPLACE_GROUP_DONE].

