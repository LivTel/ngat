package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REGISTER_TOOL_DONE.<br>
 * Command: REGISTER_TOOL<br>
 * Sent by PEST when a new installation is made. This request is used<br>
 * to obtain a host-installation-specific registration ID.<br>
 * Module code: 702300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>clientAddress - Host address of the machine it is being installed on</dd>
 * <dd>&nbsp;values: Client's IP Address or host</dd>
 * <dd>clientName - Name of user who is registering this installation</dd>
 * <dd>&nbsp;values: A Valid UserID</dd>
 * <dd>installDir - User's installation directory</dd>
 * <dd>&nbsp;values: Directory path</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>registrationCode - The registration code for this specific installation of the PEST</dd>
 * </dl>
 * <br>
 */
public class REGISTER_TOOL_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The registration code for this specific installation of the PEST*/
	protected long registrationCode;

	/** Create a REGISTER_TOOL_DONE with specified id.
	 * @param id The unique id of this REGISTER_TOOL_DONE.
	 */
	public REGISTER_TOOL_DONE (String id) { super(id); }

	/** Set the The registration code for this specific installation of the PEST
	 * @param registrationCode The The registration code for this specific installation of the PEST.
	 */
	public void setRegistrationCode(long registrationCode) { this.registrationCode = registrationCode; }

	/** Get the The registration code for this specific installation of the PEST
	 * @return The The registration code for this specific installation of the PEST
	 */
	public long getRegistrationCode() { return registrationCode; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", registrationCode="+registrationCode+"]";
	}
	// Hand generated code.

} // class def. [REGISTER_TOOL_DONE].

