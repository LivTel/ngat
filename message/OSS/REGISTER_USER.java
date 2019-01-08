package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REGISTER_USER.<br>
 * Command: REGISTER_USER<br>
 * Sent by PEST to register a new user of an installation. This request is used<br>
 * to obtain a host-installation-user-specific registration ID.<br>
 * Module code: 702400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userPath - The path used to locate the User in the P2DB</dd>
 * <dd>&nbsp;values: UserId of a user already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>registrationCode - The registration code for the user for this specific installation of the PEST</dd>
 * </dl>
 * <br>
 */
public class REGISTER_USER extends TRANSACTION {

	// Variables.

	/** The The path used to locate the User in the P2DB*/
	protected Path userPath;

	/** Create a REGISTER_USER with specified id.
	 * @param id The unique id of this REGISTER_USER.
	 */
	public REGISTER_USER(String id) { super(id); }

	/** Create a REGISTER_USER with specified id and parameters.
	 * @param id The unique id of this REGISTER_USER.
	 * @param userPath The The path used to locate the User in the P2DB
	 */
	public REGISTER_USER(
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

} // class def. [REGISTER_USER].

