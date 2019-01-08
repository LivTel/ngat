package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REPLACE_GROUP.<br>
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
public class REPLACE_GROUP extends TRANSACTION {

	// Constants.

	/** Constant: Indicates One or more items in the Source,InstConfig,TeleConfig maps could not be found*/
	public static final int CONTENT_MAPPING_ERROR = 703601;

	// Variables.

	/** The Group to add to Proposal*/
	protected Group group;

	/** The path of the Group to replace*/
	protected Path groupPath;

	/** The mapping between Observations (in Group) and Source IDs in existing Proposal*/
	protected Map srcMap;

	/** The mapping between Observations (in Group) and InstConfig IDs in existing Proposal*/
	protected Map icMap;

	/** The mapping between Observations (in Group) and TeleConfig IDs in existing Proposal*/
	protected Map tcMap;

	/** Create a REPLACE_GROUP with specified id.
	 * @param id The unique id of this REPLACE_GROUP.
	 */
	public REPLACE_GROUP(String id) { super(id); }

	/** Create a REPLACE_GROUP with specified id and parameters.
	 * @param id The unique id of this REPLACE_GROUP.
	 * @param group The Group to add to Proposal
	 * @param groupPath The path of the Group to replace
	 * @param srcMap The mapping between Observations (in Group) and Source IDs in existing Proposal
	 * @param icMap The mapping between Observations (in Group) and InstConfig IDs in existing Proposal
	 * @param tcMap The mapping between Observations (in Group) and TeleConfig IDs in existing Proposal
	 */
	public REPLACE_GROUP(
	         String id,
	         Group group,
	         Path groupPath,
	         Map srcMap,
	         Map icMap,
	         Map tcMap) {
	         super(id);
	           this.group = group;
	           this.groupPath = groupPath;
	           this.srcMap = srcMap;
	           this.icMap = icMap;
	           this.tcMap = tcMap;
	         }

	/** Set the Group to add to Proposal
	 * @param group The Group to add to Proposal
	 */
	public void setGroup(Group group) { this.group = group; }

	/** Get the Group to add to Proposal
	 * @return The Group to add to Proposal
	 */
	public Group getGroup() { return group; }

	/** Set the path of the Group to replace
	 * @param groupPath The path of the Group to replace
	 */
	public void setGroupPath(Path groupPath) { this.groupPath = groupPath; }

	/** Get the path of the Group to replace
	 * @return The path of the Group to replace
	 */
	public Path getGroupPath() { return groupPath; }

	/** Set the mapping between Observations (in Group) and Source IDs in existing Proposal
	 * @param srcMap The mapping between Observations (in Group) and Source IDs in existing Proposal
	 */
	public void setSrcMap(Map srcMap) { this.srcMap = srcMap; }

	/** Get the mapping between Observations (in Group) and Source IDs in existing Proposal
	 * @return The mapping between Observations (in Group) and Source IDs in existing Proposal
	 */
	public Map getSrcMap() { return srcMap; }

	/** Set the mapping between Observations (in Group) and InstConfig IDs in existing Proposal
	 * @param icMap The mapping between Observations (in Group) and InstConfig IDs in existing Proposal
	 */
	public void setIcMap(Map icMap) { this.icMap = icMap; }

	/** Get the mapping between Observations (in Group) and InstConfig IDs in existing Proposal
	 * @return The mapping between Observations (in Group) and InstConfig IDs in existing Proposal
	 */
	public Map getIcMap() { return icMap; }

	/** Set the mapping between Observations (in Group) and TeleConfig IDs in existing Proposal
	 * @param tcMap The mapping between Observations (in Group) and TeleConfig IDs in existing Proposal
	 */
	public void setTcMap(Map tcMap) { this.tcMap = tcMap; }

	/** Get the mapping between Observations (in Group) and TeleConfig IDs in existing Proposal
	 * @return The mapping between Observations (in Group) and TeleConfig IDs in existing Proposal
	 */
	public Map getTcMap() { return tcMap; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", group="+group+
			", groupPath="+groupPath+
			", srcMap="+srcMap+
			", icMap="+icMap+
			", tcMap="+tcMap+"]";
	}
	// Hand generated code.

} // class def. [REPLACE_GROUP].

