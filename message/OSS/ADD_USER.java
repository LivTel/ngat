package ngat.message.OSS;

import java.util.*;

import jyd.storable.*;
import jyd.collection.*;

import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_USER.<br>
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
public class ADD_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the User's owner in the P2DB*/
	protected Path tagPath;

	/** The The User to add*/
	protected User user;

	/** The The password to allow access to the User record. */
	protected String password;

	/** Create a ADD_USER with specified id.
	 * @param id The unique id of this ADD_USER.
	 */
	public ADD_USER(String id) { super(id); }

	/** Create a ADD_USER with specified id and parameters.
	 * @param id The unique id of this ADD_USER.
	 * @param tagPath The The path used to locate the User's owner in the P2DB
	 * @param user The The User to add
	 * @param password The The password to allow access to the User record. 
	 */
	public ADD_USER(
	         String id,
	         Path tagPath,
	         User user,
	         String password) {
	         super(id);
	           this.tagPath = tagPath;
	           this.user = user;
	           this.password = password;
	         }

	/** Set the The path used to locate the User's owner in the P2DB
	 * @param tagPath The The path used to locate the User's owner in the P2DB
	 */
	public void setTagPath(Path tagPath) { this.tagPath = tagPath; }

	/** Get the The path used to locate the User's owner in the P2DB
	 * @return The The path used to locate the User's owner in the P2DB
	 */
	public Path getTagPath() { return tagPath; }

	/** Set the The User to add
	 * @param user The The User to add
	 */
	public void setUser(User user) { this.user = user; }

	/** Get the The User to add
	 * @return The The User to add
	 */
	public User getUser() { return user; }

	/** Set the The password to allow access to the User record. 
	 * @param password The The password to allow access to the User record. 
	 */
	public void setPassword(String password) { this.password = password; }

	/** Get the The password to allow access to the User record. 
	 * @return The The password to allow access to the User record. 
	 */
	public String getPassword() { return password; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tagPath="+tagPath+
			", user="+user+
			", password="+password+"]";
	}
	// Hand generated code.

} // class def. [ADD_USER].

