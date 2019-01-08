package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: TELEMETRY_DONE.<br>
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
public class TELEMETRY_DONE extends GUI_TO_RCS_DONE {

	/** Create a TELEMETRY_DONE with specified id.
	 * @param id The unique id of this TELEMETRY_DONE.
	 */
	public TELEMETRY_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELEMETRY_DONE].

