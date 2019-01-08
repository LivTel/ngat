package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_COI_USER.<br>
 * Command: ADD_COI_USER<br>
 * Adds a user as co-investigator to existing proposal.<br>
 * Module code: 700100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB. </dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>userId - The uid for the user who is being added as CoI.   </dd>
 * <dd>&nbsp;values: UserId of a registered user already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>added - True if the user was added. False if the user was already co-i.</dd>
 * </dl>
 * <br>
 */
public class ADD_COI_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB. */
	protected Path proposalPath;

	/** The The uid for the user who is being added as CoI.   */
	protected String userId;

	/** Create a ADD_COI_USER with specified id.
	 * @param id The unique id of this ADD_COI_USER.
	 */
	public ADD_COI_USER(String id) { super(id); }

	/** Create a ADD_COI_USER with specified id and parameters.
	 * @param id The unique id of this ADD_COI_USER.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB. 
	 * @param userId The The uid for the user who is being added as CoI.   
	 */
	public ADD_COI_USER(
	         String id,
	         Path proposalPath,
	         String userId) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.userId = userId;
	         }

	/** Set the The path used to locate the Proposal in the P2DB. 
	 * @param proposalPath The The path used to locate the Proposal in the P2DB. 
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB. 
	 * @return The The path used to locate the Proposal in the P2DB. 
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The uid for the user who is being added as CoI.   
	 * @param userId The The uid for the user who is being added as CoI.   
	 */
	public void setUserId(String userId) { this.userId = userId; }

	/** Get the The uid for the user who is being added as CoI.   
	 * @return The The uid for the user who is being added as CoI.   
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

} // class def. [ADD_COI_USER].

