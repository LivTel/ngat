package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: RENAME_PROPOSAL.<br>
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
public class RENAME_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The  The new proposal name*/
	protected String newName;

	/** The Path to new location*/
	protected Path newPath;

	/** Create a RENAME_PROPOSAL with specified id.
	 * @param id The unique id of this RENAME_PROPOSAL.
	 */
	public RENAME_PROPOSAL(String id) { super(id); }

	/** Create a RENAME_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this RENAME_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param newName The  The new proposal name
	 * @param newPath The Path to new location
	 */
	public RENAME_PROPOSAL(
	         String id,
	         Path proposalPath,
	         String newName,
	         Path newPath) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.newName = newName;
	           this.newPath = newPath;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the  The new proposal name
	 * @param newName The  The new proposal name
	 */
	public void setNewName(String newName) { this.newName = newName; }

	/** Get the  The new proposal name
	 * @return The  The new proposal name
	 */
	public String getNewName() { return newName; }

	/** Set the Path to new location
	 * @param newPath The Path to new location
	 */
	public void setNewPath(Path newPath) { this.newPath = newPath; }

	/** Get the Path to new location
	 * @return The Path to new location
	 */
	public Path getNewPath() { return newPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", newName="+newName+
			", newPath="+newPath+"]";
	}
	// Hand generated code.

} // class def. [RENAME_PROPOSAL].

