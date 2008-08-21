package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_USER_DONE.<br>
 * Command: ADD_USER<br>
 * Add the User at the specified path.<br>
 * <br>
 * Module code: 704300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagPath - The path used to locate the User's owner in the P2DB</dd>
 * <dd>&nbsp;values: Path to a TAG already in the P2DB</dd>
 * <dd>user - The User to add</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete User</dd>
 * <dd>password - The password to allow access to the User record. </dd>
 * <dd>&nbsp;values: Valid password</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_USER_DONE extends TRANSACTION_DONE {

	/** Create a ADD_USER_DONE with specified id.
	 * @param id The unique id of this ADD_USER_DONE.
	 */
	public ADD_USER_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_USER_DONE].

