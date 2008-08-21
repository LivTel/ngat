package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: KILL_SERVER_DONE.<br>
 * Command: KILL_SERVER<br>
 * Requests the OSS to shutdown. The OSS will get the signal after a period<br>
 * but may take a while for currently executing request to complete first.<br>
 * Any queued requests will be zapped.<br>
 * Module code: 701800<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>delay - Specifies the delay (millis) before the Launcher should be given the kill signal</dd>
 * <dd>&nbsp;values: > 0 millis</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>requestTime - Time the request was sent to the OSS - no way to tell if it worked.</dd>
 * </dl>
 * <br>
 */
public class KILL_SERVER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The Time the request was sent to the OSS - no way to tell if it worked.*/
	protected long requestTime;

	/** Create a KILL_SERVER_DONE with specified id.
	 * @param id The unique id of this KILL_SERVER_DONE.
	 */
	public KILL_SERVER_DONE (String id) { super(id); }

	/** Set the Time the request was sent to the OSS - no way to tell if it worked.
	 * @param requestTime The Time the request was sent to the OSS - no way to tell if it worked..
	 */
	public void setRequestTime(long requestTime) { this.requestTime = requestTime; }

	/** Get the Time the request was sent to the OSS - no way to tell if it worked.
	 * @return The Time the request was sent to the OSS - no way to tell if it worked.
	 */
	public long getRequestTime() { return requestTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", requestTime="+requestTime+"]";
	}
	// Hand generated code.

} // class def. [KILL_SERVER_DONE].

