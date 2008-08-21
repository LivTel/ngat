package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: DELETE_COI_USER.<br>
 * Command: DELETE_COI_USER<br>
 * Removes a user as co-investigator of proposal.<br>
 * Module code: 701000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>userId - The uid for the user who is being deleted as CoI</dd>
 * <dd>&nbsp;values: UserId of a registered user already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the user was removed as co-investigator. False if they were not co-investigator anyway</dd>
 * </dl>
 * <br>
 */
public class DELETE_COI_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The The uid for the user who is being deleted as CoI*/
	protected String userId;

	/** Create a DELETE_COI_USER with specified id.
	 * @param id The unique id of this DELETE_COI_USER.
	 */
	public DELETE_COI_USER(String id) { super(id); }

	/** Create a DELETE_COI_USER with specified id and parameters.
	 * @param id The unique id of this DELETE_COI_USER.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param userId The The uid for the user who is being deleted as CoI
	 */
	public DELETE_COI_USER(
	         String id,
	         Path proposalPath,
	         String userId) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.userId = userId;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The uid for the user who is being deleted as CoI
	 * @param userId The The uid for the user who is being deleted as CoI
	 */
	public void setUserId(String userId) { this.userId = userId; }

	/** Get the The uid for the user who is being deleted as CoI
	 * @return The The uid for the user who is being deleted as CoI
	 */
	public String getUserId() { return userId; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", userId="+userId+"]";
	}
	// Hand generated code.

} // class def. [DELETE_COI_USER].

