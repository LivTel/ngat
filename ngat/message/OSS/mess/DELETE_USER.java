package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: DELETE_USER.<br>
 * Command: DELETE_USER<br>
 * Removes a user as principle-investigator of proposal.<br>
 * Module code: 705100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the User in the P2DB</dd>
 * <dd>&nbsp;values: Path to a User already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the User was removed</dd>
 * </dl>
 * <br>
 */
public class DELETE_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the User in the P2DB*/
	protected Path userPath;

	/** Create a DELETE_USER with specified id.
	 * @param id The unique id of this DELETE_USER.
	 */
	public DELETE_USER(String id) { super(id); }

	/** Create a DELETE_USER with specified id and parameters.
	 * @param id The unique id of this DELETE_USER.
	 * @param userPath The The path used to locate the User in the P2DB
	 */
	public DELETE_USER(
	         String id,
	         Path userPath) {
	         super(id);
	           this.userPath = userPath;
	         }

	/** Set the The path used to locate the User in the P2DB
	 * @param userPath The The path used to locate the User in the P2DB
	 */
	public void setUserPath(Path userPath) { this.userPath = userPath; }

	/** Get the The path used to locate the User in the P2DB
	 * @return The The path used to locate the User in the P2DB
	 */
	public Path getUserPath() { return userPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", userPath="+userPath+"]";
	}
	// Hand generated code.

} // class def. [DELETE_USER].

