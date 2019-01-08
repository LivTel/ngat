package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REGISTER_USER_DONE.<br>
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
public class REGISTER_USER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The registration code for the user for this specific installation of the PEST*/
	protected long registrationCode;

	/** Create a REGISTER_USER_DONE with specified id.
	 * @param id The unique id of this REGISTER_USER_DONE.
	 */
	public REGISTER_USER_DONE (String id) { super(id); }

	/** Set the The registration code for the user for this specific installation of the PEST
	 * @param registrationCode The The registration code for the user for this specific installation of the PEST.
	 */
	public void setRegistrationCode(long registrationCode) { this.registrationCode = registrationCode; }

	/** Get the The registration code for the user for this specific installation of the PEST
	 * @return The The registration code for the user for this specific installation of the PEST
	 */
	public long getRegistrationCode() { return registrationCode; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", registrationCode="+registrationCode+"]";
	}
	// Hand generated code.

} // class def. [REGISTER_USER_DONE].

