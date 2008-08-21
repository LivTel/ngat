package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: UPDATE_GROUP.<br>
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
public class UPDATE_GROUP extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Group in the P2DB*/
	protected Path groupPath;

	/** The The time/date at which the Group was completed if at all*/
	protected long doneTime;

	/** The The key to unlock the Group's record*/
	protected int key;

	/** The True if the Group was completed successfully*/
	protected boolean success;

	/** The Information about the completion or reason for failure of the referred Group*/
	protected String details;

	/** The optional code to indicate the reason for failure*/
	protected int code;

	/** The amount of time the Group took to execute or fail*/
	protected long usedTime;

	/** The amount of time this group should be considered Taboo due to systemic failure, but only if the group fails*/
	protected long tabuTime;

	/** Create a UPDATE_GROUP with specified id.
	 * @param id The unique id of this UPDATE_GROUP.
	 */
	public UPDATE_GROUP(String id) { super(id); }

	/** Create a UPDATE_GROUP with specified id and parameters.
	 * @param id The unique id of this UPDATE_GROUP.
	 * @param groupPath The The path used to locate the Group in the P2DB
	 * @param doneTime The The time/date at which the Group was completed if at all
	 * @param key The The key to unlock the Group's record
	 * @param success The True if the Group was completed successfully
	 * @param details The Information about the completion or reason for failure of the referred Group
	 * @param code The optional code to indicate the reason for failure
	 * @param usedTime The amount of time the Group took to execute or fail
	 * @param tabuTime The amount of time this group should be considered Taboo due to systemic failure, but only if the group fails
	 */
	public UPDATE_GROUP(
	         String id,
	         Path groupPath,
	         long doneTime,
	         int key,
	         boolean success,
	         String details,
	         int code,
	         long usedTime,
	         long tabuTime) {
	         super(id);
	           this.groupPath = groupPath;
	           this.doneTime = doneTime;
	           this.key = key;
	           this.success = success;
	           this.details = details;
	           this.code = code;
	           this.usedTime = usedTime;
	           this.tabuTime = tabuTime;
	         }

	/** Set the The path used to locate the Group in the P2DB
	 * @param groupPath The The path used to locate the Group in the P2DB
	 */
	public void setGroupPath(Path groupPath) { this.groupPath = groupPath; }

	/** Get the The path used to locate the Group in the P2DB
	 * @return The The path used to locate the Group in the P2DB
	 */
	public Path getGroupPath() { return groupPath; }

	/** Set the The time/date at which the Group was completed if at all
	 * @param doneTime The The time/date at which the Group was completed if at all
	 */
	public void setDoneTime(long doneTime) { this.doneTime = doneTime; }

	/** Get the The time/date at which the Group was completed if at all
	 * @return The The time/date at which the Group was completed if at all
	 */
	public long getDoneTime() { return doneTime; }

	/** Set the The key to unlock the Group's record
	 * @param key The The key to unlock the Group's record
	 */
	public void setKey(int key) { this.key = key; }

	/** Get the The key to unlock the Group's record
	 * @return The The key to unlock the Group's record
	 */
	public int getKey() { return key; }

	/** Set the True if the Group was completed successfully
	 * @param success The True if the Group was completed successfully
	 */
	public void setSuccess(boolean success) { this.success = success; }

	/** Get the True if the Group was completed successfully
	 * @return The True if the Group was completed successfully
	 */
	public boolean getSuccess() { return success; }

	/** Set the Information about the completion or reason for failure of the referred Group
	 * @param details The Information about the completion or reason for failure of the referred Group
	 */
	public void setDetails(String details) { this.details = details; }

	/** Get the Information about the completion or reason for failure of the referred Group
	 * @return The Information about the completion or reason for failure of the referred Group
	 */
	public String getDetails() { return details; }

	/** Set the optional code to indicate the reason for failure
	 * @param code The optional code to indicate the reason for failure
	 */
	public void setCode(int code) { this.code = code; }

	/** Get the optional code to indicate the reason for failure
	 * @return The optional code to indicate the reason for failure
	 */
	public int getCode() { return code; }

	/** Set the amount of time the Group took to execute or fail
	 * @param usedTime The amount of time the Group took to execute or fail
	 */
	public void setUsedTime(long usedTime) { this.usedTime = usedTime; }

	/** Get the amount of time the Group took to execute or fail
	 * @return The amount of time the Group took to execute or fail
	 */
	public long getUsedTime() { return usedTime; }

	/** Set the amount of time this group should be considered Taboo due to systemic failure, but only if the group fails
	 * @param tabuTime The amount of time this group should be considered Taboo due to systemic failure, but only if the group fails
	 */
	public void setTabuTime(long tabuTime) { this.tabuTime = tabuTime; }

	/** Get the amount of time this group should be considered Taboo due to systemic failure, but only if the group fails
	 * @return The amount of time this group should be considered Taboo due to systemic failure, but only if the group fails
	 */
	public long getTabuTime() { return tabuTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", groupPath="+groupPath+
			", doneTime="+doneTime+
			", key="+key+
			", success="+success+
			", details="+details+
			", code="+code+
			", usedTime="+usedTime+
			", tabuTime="+tabuTime+"]";
	}
	// Hand generated code.

} // class def. [UPDATE_GROUP].

