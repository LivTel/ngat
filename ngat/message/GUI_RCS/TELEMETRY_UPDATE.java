package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: TELEMETRY_UPDATE.<br>
 *  Send a Telemetry connection request.<br>
 * <br>
 *  Module code: 681800<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>data - Serializable payload</dd>
 * <dd>&nbsp;values: an instance od TelemtryInfo</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>time - time when payload was generated</dd>
 * </dl>
 * <br>
 */
public class TELEMETRY_UPDATE extends GUI_TO_RCS {

	// Variables.

	/** The Serializable payload*/
	protected TelemetryInfo data;

	/** Create a TELEMETRY_UPDATE with specified id.
	 * @param id The unique id of this TELEMETRY_UPDATE.
	 */
	public TELEMETRY_UPDATE(String id) { super(id); }

	/** Create a TELEMETRY_UPDATE with specified id and parameters.
	 * @param id The unique id of this TELEMETRY_UPDATE.
	 * @param data The Serializable payload
	 */
	public TELEMETRY_UPDATE(
	         String id,
	         TelemetryInfo data) {
	         super(id);
	           this.data = data;
	         }

	/** Set the Serializable payload
	 * @param data The Serializable payload
	 */
	public void setData(TelemetryInfo data) { this.data = data; }

	/** Get the Serializable payload
	 * @return The Serializable payload
	 */
	public TelemetryInfo getData() { return data; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", data="+data+"]";
	}
	// Hand generated code.

} // class def. [TELEMETRY_UPDATE].

