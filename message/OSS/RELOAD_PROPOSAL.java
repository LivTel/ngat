package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: RELOAD_PROPOSAL.<br>
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
public class RELOAD_PROPOSAL extends TRANSACTION {

	// Constants.

	/** Constant: Indicates The Proposal is locked at a lower level e.g. due to a Group being scheduled*/
	public static final int LOW_LEVEL_LOCKOUT = 701501;

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The The path used to locate the editor (us) in the P2DB*/
	protected Path editorPath;

	/** The The key to be used to lock the Proposal in the Database*/
	protected int key;

	/** Create a RELOAD_PROPOSAL with specified id.
	 * @param id The unique id of this RELOAD_PROPOSAL.
	 */
	public RELOAD_PROPOSAL(String id) { super(id); }

	/** Create a RELOAD_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this RELOAD_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param editorPath The The path used to locate the editor (us) in the P2DB
	 * @param key The The key to be used to lock the Proposal in the Database
	 */
	public RELOAD_PROPOSAL(
	         String id,
	         Path proposalPath,
	         Path editorPath,
	         int key) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.editorPath = editorPath;
	           this.key = key;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The path used to locate the editor (us) in the P2DB
	 * @param editorPath The The path used to locate the editor (us) in the P2DB
	 */
	public void setEditorPath(Path editorPath) { this.editorPath = editorPath; }

	/** Get the The path used to locate the editor (us) in the P2DB
	 * @return The The path used to locate the editor (us) in the P2DB
	 */
	public Path getEditorPath() { return editorPath; }

	/** Set the The key to be used to lock the Proposal in the Database
	 * @param key The The key to be used to lock the Proposal in the Database
	 */
	public void setKey(int key) { this.key = key; }

	/** Get the The key to be used to lock the Proposal in the Database
	 * @return The The key to be used to lock the Proposal in the Database
	 */
	public int getKey() { return key; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", editorPath="+editorPath+
			", key="+key+"]";
	}
	// Hand generated code.

} // class def. [RELOAD_PROPOSAL].

