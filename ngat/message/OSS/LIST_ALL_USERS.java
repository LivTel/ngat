package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: LIST_ALL_USERS.<br>
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
public class LIST_ALL_USERS extends TRANSACTION {

	/** Create a LIST_ALL_USERS with specified id.
	 * @param id The unique id of this LIST_ALL_USERS.
	 */
	public LIST_ALL_USERS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [LIST_ALL_USERS].

