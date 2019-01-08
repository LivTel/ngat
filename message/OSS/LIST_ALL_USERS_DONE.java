package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: LIST_ALL_USERS_DONE.<br>
 * Command: LIST_USERS<br>
 * Returns a list of all Users<br>
 * Module code: 704000  <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>userList - A java.util.List containing the ngat.phase2.UserDescriptors of the Users</dd>
 * </dl>
 * <br>
 */
public class LIST_ALL_USERS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List containing the ngat.phase2.UserDescriptors of the Users*/
	protected List userList;

	/** Create a LIST_ALL_USERS_DONE with specified id.
	 * @param id The unique id of this LIST_ALL_USERS_DONE.
	 */
	public LIST_ALL_USERS_DONE (String id) { super(id); }

	/** Set the A java.util.List containing the ngat.phase2.UserDescriptors of the Users
	 * @param userList The A java.util.List containing the ngat.phase2.UserDescriptors of the Users.
	 */
	public void setUserList(List userList) { this.userList = userList; }

	/** Get the A java.util.List containing the ngat.phase2.UserDescriptors of the Users
	 * @return The A java.util.List containing the ngat.phase2.UserDescriptors of the Users
	 */
	public List getUserList() { return userList; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", userList="+userList+"]";
	}
	// Hand generated code.

} // class def. [LIST_ALL_USERS_DONE].

