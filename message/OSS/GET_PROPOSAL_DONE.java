package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_PROPOSAL_DONE.<br>
 * Command: GET_PROPOSAL<br>
 * Retreives a Proposal from the P2DB. The Proposal will be locked <br>
 * with the specified key which will be required to unlock it when<br>
 * a subsequent EXPORT_PROPOSAL or REPLACE_PROPOSAL command is sent.<br>
 * The userId of the editor (i.e. the sender of this command) is saved<br>
 * in the Proposal record as lastEditor so others can see who has currently<br>
 * got the lock. If the Proposal is already locked this command fails and<br>
 * the uid and lock time of the current locker are returned.<br>
 * Module code: 701500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>editorPath - The path used to locate the editor (us) in the P2DB</dd>
 * <dd>&nbsp;values: Path to a user already in the P2DB</dd>
 * <dd>key - The key to be used to lock the Proposal in the Database</dd>
 * <dd>&nbsp;values: Valid (>0) key</dd>
 * <dd>regId - The registrationID of the client</dd>
 * <dd>&nbsp;values: Valid client registrationID</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>proposal - The ngat.phase2.Proposal requested</dd>
 * </dl>
 * <br>
 */
public class GET_PROPOSAL_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The ngat.phase2.Proposal requested*/
	protected Proposal proposal;

	/** Create a GET_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this GET_PROPOSAL_DONE.
	 */
	public GET_PROPOSAL_DONE (String id) { super(id); }

	/** Set the The ngat.phase2.Proposal requested
	 * @param proposal The The ngat.phase2.Proposal requested.
	 */
	public void setProposal(Proposal proposal) { this.proposal = proposal; }

	/** Get the The ngat.phase2.Proposal requested
	 * @return The The ngat.phase2.Proposal requested
	 */
	public Proposal getProposal() { return proposal; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposal="+proposal+"]";
	}
	// Hand generated code.

} // class def. [GET_PROPOSAL_DONE].

