package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_PROPOSAL.<br>
 * Command: ADD_PROPOSAL<br>
 * Add the Proposal at the specified path.<br>
 * <br>
 * Module code: 704100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the Proposal's owner in the P2DB</dd>
 * <dd>&nbsp;values: Path to a user already in the P2DB</dd>
 * <dd>proposal - The Proposal to add</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete Proposal</dd>
 * <dd>password - The password to allow access to the Proposal record. </dd>
 * <dd>&nbsp;values: Valid password</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_PROPOSAL extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Proposal's owner in the P2DB*/
	protected Path userPath;

	/** The The Proposal to add*/
	protected Proposal proposal;

	/** The The password to allow access to the Proposal record. */
	protected String password;

	/** Create a ADD_PROPOSAL with specified id.
	 * @param id The unique id of this ADD_PROPOSAL.
	 */
	public ADD_PROPOSAL(String id) { super(id); }

	/** Create a ADD_PROPOSAL with specified id and parameters.
	 * @param id The unique id of this ADD_PROPOSAL.
	 * @param userPath The The path used to locate the Proposal's owner in the P2DB
	 * @param proposal The The Proposal to add
	 * @param password The The password to allow access to the Proposal record. 
	 */
	public ADD_PROPOSAL(
	         String id,
	         Path userPath,
	         Proposal proposal,
	         String password) {
	         super(id);
	           this.userPath = userPath;
	           this.proposal = proposal;
	           this.password = password;
	         }

	/** Set the The path used to locate the Proposal's owner in the P2DB
	 * @param userPath The The path used to locate the Proposal's owner in the P2DB
	 */
	public void setUserPath(Path userPath) { this.userPath = userPath; }

	/** Get the The path used to locate the Proposal's owner in the P2DB
	 * @return The The path used to locate the Proposal's owner in the P2DB
	 */
	public Path getUserPath() { return userPath; }

	/** Set the The Proposal to add
	 * @param proposal The The Proposal to add
	 */
	public void setProposal(Proposal proposal) { this.proposal = proposal; }

	/** Get the The Proposal to add
	 * @return The The Proposal to add
	 */
	public Proposal getProposal() { return proposal; }

	/** Set the The password to allow access to the Proposal record. 
	 * @param password The The password to allow access to the Proposal record. 
	 */
	public void setPassword(String password) { this.password = password; }

	/** Get the The password to allow access to the Proposal record. 
	 * @return The The password to allow access to the Proposal record. 
	 */
	public String getPassword() { return password; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", userPath="+userPath+
			", proposal="+proposal+
			", password="+password+"]";
	}
	// Hand generated code.

} // class def. [ADD_PROPOSAL].

