package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: SERVERSTATUS_DONE.<br>
 *  Supplies status information about the PCR server.<br>
 * <br>
 *  Module code 691000<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>uptime - time the server has been up (millis).</dd>
 * </dl>
 * <br>
 */
public class SERVERSTATUS_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The time the server has been up (millis).*/
	protected long uptime;

	/** Create a SERVERSTATUS_DONE with specified id.
	 * @param id The unique id of this SERVERSTATUS_DONE.
	 */
	public SERVERSTATUS_DONE (String id) { super(id); }

	/** Set the time the server has been up (millis).
	 * @param uptime The time the server has been up (millis)..
	 */
	public void setUptime(long uptime) { this.uptime = uptime; }

	/** Get the time the server has been up (millis).
	 * @return The time the server has been up (millis).
	 */
	public long getUptime() { return uptime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", uptime="+uptime+"]";
	}
	// Hand generated code.

} // class def. [SERVERSTATUS_DONE].

