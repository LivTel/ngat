package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_USER_DONE.<br>
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
public class GET_USER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The ngat.phase2.User requested*/
	protected User user;

	/** Create a GET_USER_DONE with specified id.
	 * @param id The unique id of this GET_USER_DONE.
	 */
	public GET_USER_DONE (String id) { super(id); }

	/** Set the The ngat.phase2.User requested
	 * @param user The The ngat.phase2.User requested.
	 */
	public void setUser(User user) { this.user = user; }

	/** Get the The ngat.phase2.User requested
	 * @return The The ngat.phase2.User requested
	 */
	public User getUser() { return user; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", user="+user+"]";
	}
	// Hand generated code.

} // class def. [GET_USER_DONE].

