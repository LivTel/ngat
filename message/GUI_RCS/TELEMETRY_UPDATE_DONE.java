package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: TELEMETRY_UPDATE_DONE.<br>
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
public class TELEMETRY_UPDATE_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The time when payload was generated*/
	protected long time;

	/** Create a TELEMETRY_UPDATE_DONE with specified id.
	 * @param id The unique id of this TELEMETRY_UPDATE_DONE.
	 */
	public TELEMETRY_UPDATE_DONE (String id) { super(id); }

	/** Set the time when payload was generated
	 * @param time The time when payload was generated.
	 */
	public void setTime(long time) { this.time = time; }

	/** Get the time when payload was generated
	 * @return The time when payload was generated
	 */
	public long getTime() { return time; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", time="+time+"]";
	}
	// Hand generated code.

} // class def. [TELEMETRY_UPDATE_DONE].

