package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_USER.<br>
 * Command: GET_USER<br>
 * Retreives a User from the P2DB. <br>
 * Module code: 705300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the User in the P2DB</dd>
 * <dd>&nbsp;values: Path to a User already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>user - The ngat.phase2.User requested</dd>
 * </dl>
 * <br>
 */
public class GET_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the User in the P2DB*/
	protected Path userPath;

	/** Create a GET_USER with specified id.
	 * @param id The unique id of this GET_USER.
	 */
	public GET_USER(String id) { super(id); }

	/** Create a GET_USER with specified id and parameters.
	 * @param id The unique id of this GET_USER.
	 * @param userPath The The path used to locate the User in the P2DB
	 */
	public GET_USER(
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

} // class def. [GET_USER].

