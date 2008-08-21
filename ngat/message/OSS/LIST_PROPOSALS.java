package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: LIST_PROPOSALS.<br>
 * Command: LIST_PROPOSALS<br>
 * Returns a list of Proposals owned (or co-owned) by the user.<br>
 * Module code: 703700  <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to identify the User in the P2DB</dd>
 * <dd>&nbsp;values: A valid user</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>proposalList - A java.util.List containing the ngat.phase2.ProposalDescriptors of the Proposals belonging (PI or CoI) to this user.</dd>
 * <dd>userList - A java.util.List containing the ngat.phase2.UserDescriptors of the owners of the Proposals in proposalList.</dd>
 * </dl>
 * <br>
 */
public class LIST_PROPOSALS extends TRANSACTION {

	// Variables.

	/** The The path used to identify the User in the P2DB*/
	protected Path userPath;

	/** Create a LIST_PROPOSALS with specified id.
	 * @param id The unique id of this LIST_PROPOSALS.
	 */
	public LIST_PROPOSALS(String id) { super(id); }

	/** Create a LIST_PROPOSALS with specified id and parameters.
	 * @param id The unique id of this LIST_PROPOSALS.
	 * @param userPath The The path used to identify the User in the P2DB
	 */
	public LIST_PROPOSALS(
	         String id,
	         Path userPath) {
	         super(id);
	           this.userPath = userPath;
	         }

	/** Set the The path used to identify the User in the P2DB
	 * @param userPath The The path used to identify the User in the P2DB
	 */
	public void setUserPath(Path userPath) { this.userPath = userPath; }

	/** Get the The path used to identify the User in the P2DB
	 * @return The The path used to identify the User in the P2DB
	 */
	public Path getUserPath() { return userPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", userPath="+userPath+"]";
	}
	// Hand generated code.

} // class def. [LIST_PROPOSALS].

