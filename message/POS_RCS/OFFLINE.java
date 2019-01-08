package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: OFFLINE.<br>
 *  Requests the RCS to pass control over to the Offline mode<br>
 *  via the Science Control Agent until a specified time or<br>
 *  the end of the current RTOC window.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>until - time to switch to Offline mode until.</dd>
 * <dd>&nbsp;values: a valid (ISO 8601 Formatted date/time)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OFFLINE extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates that the specified time exceeds the current RTOC window.*/
	public static final int WINDOW_EXCEEDED = 691201;

	// Variables.

	/** The time to switch to Offline mode until.*/
	protected long until;

	/** Create a OFFLINE with specified id.
	 * @param id The unique id of this OFFLINE.
	 */
	public OFFLINE(String id) { super(id); }

	/** Create a OFFLINE with specified id and parameters.
	 * @param id The unique id of this OFFLINE.
	 * @param until The time to switch to Offline mode until.
	 */
	public OFFLINE(
	         String id,
	         long until) {
	         super(id);
	           this.until = until;
	         }

	/** Set the time to switch to Offline mode until.
	 * @param until The time to switch to Offline mode until.
	 */
	public void setUntil(long until) { this.until = until; }

	/** Get the time to switch to Offline mode until.
	 * @return The time to switch to Offline mode until.
	 */
	public long getUntil() { return until; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", until="+until+"]";
	}
	// Hand generated code.

} // class def. [OFFLINE].

