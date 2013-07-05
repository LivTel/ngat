package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: EDIT_USER.<br>
 * Command: EDIT_USER<br>
 * Modify a stored User's accounting info<br>
 * <br>
 * Module code: 705400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>user - The User to edit</dd>
 * <dd>&nbsp;values: A valid and complete User</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class EDIT_USER extends TRANSACTION {

	// Variables.

	/** The The User to edit*/
	protected User user;

	/** Create a EDIT_USER with specified id.
	 * @param id The unique id of this EDIT_USER.
	 */
	public EDIT_USER(String id) { super(id); }

	/** Create a EDIT_USER with specified id and parameters.
	 * @param id The unique id of this EDIT_USER.
	 * @param user The The User to edit
	 */
	public EDIT_USER(
	         String id,
	         User user) {
	         super(id);
	           this.user = user;
	         }

	/** Set the The User to edit
	 * @param user The The User to edit
	 */
	public void setUser(User user) { this.user = user; }

	/** Get the The User to edit
	 * @return The The User to edit
	 */
	public User getUser() { return user; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", user="+user+"]";
	}
	// Hand generated code.

} // class def. [EDIT_USER].

