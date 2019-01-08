package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: UPDATE_GROUP_DONE.<br>
 * Command: UPDATE_GROUP<br>
 * Updates a Group's scheduling/execution history. Sent when a Group has<br>
 * been scheduled and either completes or fails for some reason. For a<br>
 * FlexibleGroup there may be many scheduled records but only one done record.<br>
 * For Monitor and Ephem Groups there can be many of each. For FixedGroups<br>
 * there should only be one record which may be done or failed.<br>
 * The owning Proposal and Tag have their respective block allocation times<br>
 * ,total time and fixed time updated to reflect the time used only if the<br>
 * Group executed successfully. Proposals which have a freeTwilight allocation<br>
 * do not have these updated if it is currently twilight.<br>
 * Module code: 703400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>groupPath - The path used to locate the Group in the P2DB</dd>
 * <dd>&nbsp;values: Path to a group already in the P2DB</dd>
 * <dd>doneTime - The time/date at which the Group was completed if at all</dd>
 * <dd>&nbsp;values: A date/time </dd>
 * <dd>key - The key to unlock the Group's record</dd>
 * <dd>&nbsp;values: Valid (>0) key</dd>
 * <dd>success - True if the Group was completed successfully</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>details - Information about the completion or reason for failure of the referred Group</dd>
 * <dd>&nbsp;values:  </dd>
 * <dd>code - optional code to indicate the reason for failure</dd>
 * <dd>&nbsp;values: A valid error-code or 0</dd>
 * <dd>usedTime - amount of time the Group took to execute or fail</dd>
 * <dd>&nbsp;values: Time >= 0</dd>
 * <dd>tabuTime - amount of time this group should be considered Taboo due to systemic failure, but only if the group fails</dd>
 * <dd>&nbsp;values:  Time >= 0</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class UPDATE_GROUP_DONE extends TRANSACTION_DONE {

	/** Create a UPDATE_GROUP_DONE with specified id.
	 * @param id The unique id of this UPDATE_GROUP_DONE.
	 */
	public UPDATE_GROUP_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [UPDATE_GROUP_DONE].

