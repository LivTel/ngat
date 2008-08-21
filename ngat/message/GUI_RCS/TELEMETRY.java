package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: TELEMETRY.<br>
 *  Send a Telemetry connection request.<br>
 * <br>
 *  Module code: 680900<br>
 * <br>
 *  If the wants is left empty client will get the lot.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>clientId - clients ID</dd>
 * <dd>&nbsp;values: a unique ID</dd>
 * <dd>connect - how to connect back to client</dd>
 * <dd>&nbsp;values: a valid connection specification</dd>
 * <dd>wants - list of classes client wants to receive</dd>
 * <dd>&nbsp;values: a java.util.List of TelemetryInfo classes</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELEMETRY extends GUI_TO_RCS {

	// Variables.

	/** The clients ID*/
	protected String clientId;

	/** The how to connect back to client*/
	protected ConnectionSetupInfo connect;

	/** The list of classes client wants to receive*/
	protected Vector wants;

	/** Create a TELEMETRY with specified id.
	 * @param id The unique id of this TELEMETRY.
	 */
	public TELEMETRY(String id) { super(id); }

	/** Create a TELEMETRY with specified id and parameters.
	 * @param id The unique id of this TELEMETRY.
	 * @param clientId The clients ID
	 * @param connect The how to connect back to client
	 * @param wants The list of classes client wants to receive
	 */
	public TELEMETRY(
	         String id,
	         String clientId,
	         ConnectionSetupInfo connect,
	         Vector wants) {
	         super(id);
	           this.clientId = clientId;
	           this.connect = connect;
	           this.wants = wants;
	         }

	/** Set the clients ID
	 * @param clientId The clients ID
	 */
	public void setClientId(String clientId) { this.clientId = clientId; }

	/** Get the clients ID
	 * @return The clients ID
	 */
	public String getClientId() { return clientId; }

	/** Set the how to connect back to client
	 * @param connect The how to connect back to client
	 */
	public void setConnect(ConnectionSetupInfo connect) { this.connect = connect; }

	/** Get the how to connect back to client
	 * @return The how to connect back to client
	 */
	public ConnectionSetupInfo getConnect() { return connect; }

	/** Set the list of classes client wants to receive
	 * @param wants The list of classes client wants to receive
	 */
	public void setWants(Vector wants) { this.wants = wants; }

	/** Get the list of classes client wants to receive
	 * @return The list of classes client wants to receive
	 */
	public Vector getWants() { return wants; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", clientId="+clientId+
			", connect="+connect+
			", wants="+wants+"]";
	}
	// Hand generated code.

} // class def. [TELEMETRY].

