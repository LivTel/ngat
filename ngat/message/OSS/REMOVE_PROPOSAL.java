package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REMOVE_PROPOSAL.<br>
 * Command: REMOVE_PROPOSAL<br>
 * Remove the Proposal at the specified path.<br>
 * <br>
 * Module code: 704200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal's owner in the P2DB</dd>
 * <dd>&nbsp;values: Path to a Proposal already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class REMOVE_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal's owner in the P2DB*/
	protected Path proposalPath;

	/** Create a REMOVE_PROPOSAL with specified id.
	 * @param id The unique id of this REMOVE_PROPOSAL.
	 */
	public REMOVE_PROPOSAL(String id) { super(id); }

	/** Create a REMOVE_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this REMOVE_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal's owner in the P2DB
	 */
	public REMOVE_PROPOSAL(
	         String id,
	         Path proposalPath) {
	         super(id);
	           this.proposalPath = proposalPath;
	         }

	/** Set the The path used to locate the Proposal's owner in the P2DB
	 * @param proposalPath The The path used to locate the Proposal's owner in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal's owner in the P2DB
	 * @return The The path used to locate the Proposal's owner in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+"]";
	}
	// Hand generated code.

} // class def. [REMOVE_PROPOSAL].

