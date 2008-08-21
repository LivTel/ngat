package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REGISTER_TOOL.<br>
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
public class REGISTER_TOOL extends TRANSACTION {

	// Variables.

	/** The Host address of the machine it is being installed on*/
	protected String clientAddress;

	/** The Name of user who is registering this installation*/
	protected String clientName;

	/** The User's installation directory*/
	protected String installDir;

	/** Create a REGISTER_TOOL with specified id.
	 * @param id The unique id of this REGISTER_TOOL.
	 */
	public REGISTER_TOOL(String id) { super(id); }

	/** Create a REGISTER_TOOL with specified id and parameters.
	 * @param id The unique id of this REGISTER_TOOL.
	 * @param clientAddress The Host address of the machine it is being installed on
	 * @param clientName The Name of user who is registering this installation
	 * @param installDir The User's installation directory
	 */
	public REGISTER_TOOL(
	         String id,
	         String clientAddress,
	         String clientName,
	         String installDir) {
	         super(id);
	           this.clientAddress = clientAddress;
	           this.clientName = clientName;
	           this.installDir = installDir;
	         }

	/** Set the Host address of the machine it is being installed on
	 * @param clientAddress The Host address of the machine it is being installed on
	 */
	public void setClientAddress(String clientAddress) { this.clientAddress = clientAddress; }

	/** Get the Host address of the machine it is being installed on
	 * @return The Host address of the machine it is being installed on
	 */
	public String getClientAddress() { return clientAddress; }

	/** Set the Name of user who is registering this installation
	 * @param clientName The Name of user who is registering this installation
	 */
	public void setClientName(String clientName) { this.clientName = clientName; }

	/** Get the Name of user who is registering this installation
	 * @return The Name of user who is registering this installation
	 */
	public String getClientName() { return clientName; }

	/** Set the User's installation directory
	 * @param installDir The User's installation directory
	 */
	public void setInstallDir(String installDir) { this.installDir = installDir; }

	/** Get the User's installation directory
	 * @return The User's installation directory
	 */
	public String getInstallDir() { return installDir; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", clientAddress="+clientAddress+
			", clientName="+clientName+
			", installDir="+installDir+"]";
	}
	// Hand generated code.

} // class def. [REGISTER_TOOL].

