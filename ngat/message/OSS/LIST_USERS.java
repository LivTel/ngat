package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: LIST_USERS.<br>
 * Command: LIST_USERS<br>
 * Returns a list of Users owned by the TAG.<br>
 * Module code: 704200  <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagPath - The path used to identify the TAG in the P2DB</dd>
 * <dd>&nbsp;values: A valid TAG</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>userList - A java.util.List containing the ngat.phase2.UserDescriptors of the Users belonging to this TAG.</dd>
 * </dl>
 * <br>
 */
public class LIST_USERS extends TRANSACTION {

	// Variables.

	/** The The path used to identify the TAG in the P2DB*/
	protected Path tagPath;

	/** Create a LIST_USERS with specified id.
	 * @param id The unique id of this LIST_USERS.
	 */
	public LIST_USERS(String id) { super(id); }

	/** Create a LIST_USERS with specified id and parameters.
	 * @param id The unique id of this LIST_USERS.
	 * @param tagPath The The path used to identify the TAG in the P2DB
	 */
	public LIST_USERS(
	         String id,
	         Path tagPath) {
	         super(id);
	           this.tagPath = tagPath;
	         }

	/** Set the The path used to identify the TAG in the P2DB
	 * @param tagPath The The path used to identify the TAG in the P2DB
	 */
	public void setTagPath(Path tagPath) { this.tagPath = tagPath; }

	/** Get the The path used to identify the TAG in the P2DB
	 * @return The The path used to identify the TAG in the P2DB
	 */
	public Path getTagPath() { return tagPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tagPath="+tagPath+"]";
	}
	// Hand generated code.

} // class def. [LIST_USERS].

