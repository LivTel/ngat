package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_PROPOSAL.<br>
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
 * <dd>dolock - true if the proposal is to be locked.</dd>
 * <dd>&nbsp;values: { true | false }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>proposal - The ngat.phase2.Proposal requested</dd>
 * </dl>
 * <br>
 */
public class GET_PROPOSAL extends TRANSACTION {

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

	/** The The registrationID of the client*/
	protected long regId;

	/** The true if the proposal is to be locked.*/
	protected boolean dolock;

	/** Create a GET_PROPOSAL with specified id.
	 * @param id The unique id of this GET_PROPOSAL.
	 */
	public GET_PROPOSAL(String id) { super(id); }

	/** Create a GET_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this GET_PROPOSAL.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param editorPath The The path used to locate the editor (us) in the P2DB
	 * @param key The The key to be used to lock the Proposal in the Database
	 * @param regId The The registrationID of the client
	 * @param dolock The true if the proposal is to be locked.
	 */
	public GET_PROPOSAL(
	         String id,
	         Path proposalPath,
	         Path editorPath,
	         int key,
	         long regId,
	         boolean dolock) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.editorPath = editorPath;
	           this.key = key;
	           this.regId = regId;
	           this.dolock = dolock;
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

	/** Set the The registrationID of the client
	 * @param regId The The registrationID of the client
	 */
	public void setRegId(long regId) { this.regId = regId; }

	/** Get the The registrationID of the client
	 * @return The The registrationID of the client
	 */
	public long getRegId() { return regId; }

	/** Set the true if the proposal is to be locked.
	 * @param dolock The true if the proposal is to be locked.
	 */
	public void setDolock(boolean dolock) { this.dolock = dolock; }

	/** Get the true if the proposal is to be locked.
	 * @return The true if the proposal is to be locked.
	 */
	public boolean getDolock() { return dolock; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", editorPath="+editorPath+
			", key="+key+
			", regId="+regId+
			", dolock="+dolock+"]";
	}
	// Hand generated code.

} // class def. [GET_PROPOSAL].

