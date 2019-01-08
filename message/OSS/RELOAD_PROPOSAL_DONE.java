package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: RELOAD_PROPOSAL_DONE.<br>
 * Command: RELOAD_PROPOSAL<br>
 * Attempts to retrieve a Proposal which appears to be locked but which<br>
 * should not be - generally due to a synchronization problem. This<br>
 * command should only be sent during automatic recovery by the PEST.<br>
 * Module code: 702500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>editorPath - The path used to locate the editor (us) in the P2DB</dd>
 * <dd>&nbsp;values: Path to a user already in the P2DB</dd>
 * <dd>key - The key to be used to lock the Proposal in the Database</dd>
 * <dd>&nbsp;values: Valid (>0) key</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>proposal - The ngat.phase2.Proposal requested</dd>
 * </dl>
 * <br>
 */
public class RELOAD_PROPOSAL_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The ngat.phase2.Proposal requested*/
	protected Proposal proposal;

	/** Create a RELOAD_PROPOSAL_DONE with specified id.
	 * @param id The unique id of this RELOAD_PROPOSAL_DONE.
	 */
	public RELOAD_PROPOSAL_DONE (String id) { super(id); }

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

} // class def. [RELOAD_PROPOSAL_DONE].

