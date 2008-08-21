package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: ISS_SET_HEADERS.<br>
 *  Set some FITS headers when observing in manual mode.<br>
 * <br>
 *  Headers are: TAG, USER, PROP, GRP and OBS IDs.<br>
 * <br>
 *  Module code: 681400<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagId - TAG Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>userId - User Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>proposalId - Proposal Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>groupId - Group Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>obsId - Observation Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>manual - true if this is to set manual headers, false to reset to automatic</dd>
 * <dd>&nbsp;values: {T|F}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ISS_SET_HEADERS extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS is not in manual mode at present*/
	public static final int NOT_MANUAL_MODE = 681401;

	// Variables.

	/** The TAG Id*/
	protected String tagId;

	/** The User Id*/
	protected String userId;

	/** The Proposal Id*/
	protected String proposalId;

	/** The Group Id*/
	protected String groupId;

	/** The Observation Id*/
	protected String obsId;

	/** The true if this is to set manual headers, false to reset to automatic*/
	protected boolean manual;

	/** Create a ISS_SET_HEADERS with specified id.
	 * @param id The unique id of this ISS_SET_HEADERS.
	 */
	public ISS_SET_HEADERS(String id) { super(id); }

	/** Create a ISS_SET_HEADERS with specified id and parameters.
	 * @param id The unique id of this ISS_SET_HEADERS.
	 * @param tagId The TAG Id
	 * @param userId The User Id
	 * @param proposalId The Proposal Id
	 * @param groupId The Group Id
	 * @param obsId The Observation Id
	 * @param manual The true if this is to set manual headers, false to reset to automatic
	 */
	public ISS_SET_HEADERS(
	         String id,
	         String tagId,
	         String userId,
	         String proposalId,
	         String groupId,
	         String obsId,
	         boolean manual) {
	         super(id);
	           this.tagId = tagId;
	           this.userId = userId;
	           this.proposalId = proposalId;
	           this.groupId = groupId;
	           this.obsId = obsId;
	           this.manual = manual;
	         }

	/** Set the TAG Id
	 * @param tagId The TAG Id
	 */
	public void setTagId(String tagId) { this.tagId = tagId; }

	/** Get the TAG Id
	 * @return The TAG Id
	 */
	public String getTagId() { return tagId; }

	/** Set the User Id
	 * @param userId The User Id
	 */
	public void setUserId(String userId) { this.userId = userId; }

	/** Get the User Id
	 * @return The User Id
	 */
	public String getUserId() { return userId; }

	/** Set the Proposal Id
	 * @param proposalId The Proposal Id
	 */
	public void setProposalId(String proposalId) { this.proposalId = proposalId; }

	/** Get the Proposal Id
	 * @return The Proposal Id
	 */
	public String getProposalId() { return proposalId; }

	/** Set the Group Id
	 * @param groupId The Group Id
	 */
	public void setGroupId(String groupId) { this.groupId = groupId; }

	/** Get the Group Id
	 * @return The Group Id
	 */
	public String getGroupId() { return groupId; }

	/** Set the Observation Id
	 * @param obsId The Observation Id
	 */
	public void setObsId(String obsId) { this.obsId = obsId; }

	/** Get the Observation Id
	 * @return The Observation Id
	 */
	public String getObsId() { return obsId; }

	/** Set the true if this is to set manual headers, false to reset to automatic
	 * @param manual The true if this is to set manual headers, false to reset to automatic
	 */
	public void setManual(boolean manual) { this.manual = manual; }

	/** Get the true if this is to set manual headers, false to reset to automatic
	 * @return The true if this is to set manual headers, false to reset to automatic
	 */
	public boolean getManual() { return manual; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tagId="+tagId+
			", userId="+userId+
			", proposalId="+proposalId+
			", groupId="+groupId+
			", obsId="+obsId+
			", manual="+manual+"]";
	}
	// Hand generated code.

} // class def. [ISS_SET_HEADERS].

