package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: TERMINATE_SERVER.<br>
 * Command: TERMINATE_SERVER<br>
 * Requests the OSS to shutdown. The OSS will get the signal after a period<br>
 * but may take a while for currently executing and queued requests to complete first.<br>
 * Module code: 703200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>delay - Specifies the delay (millis) before the Launcher should be given the terminate signal</dd>
 * <dd>&nbsp;values: > 0 millis</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>requestTime - Time the request was sent to the OSS - no way to tell if it worked.</dd>
 * </dl>
 * <br>
 */
public class TERMINATE_SERVER extends TRANSACTION {

	// Variables.

	/** The Specifies the delay (millis) before the Launcher should be given the terminate signal*/
	protected long delay;

	/** Create a TERMINATE_SERVER with specified id.
	 * @param id The unique id of this TERMINATE_SERVER.
	 */
	public TERMINATE_SERVER(String id) { super(id); }

	/** Create a TERMINATE_SERVER with specified id and parameters.
	 * @param id The unique id of this TERMINATE_SERVER.
	 * @param delay The Specifies the delay (millis) before the Launcher should be given the terminate signal
	 */
	public TERMINATE_SERVER(
	         String id,
	         long delay) {
	         super(id);
	           this.delay = delay;
	         }

	/** Set the Specifies the delay (millis) before the Launcher should be given the terminate signal
	 * @param delay The Specifies the delay (millis) before the Launcher should be given the terminate signal
	 */
	public void setDelay(long delay) { this.delay = delay; }

	/** Get the Specifies the delay (millis) before the Launcher should be given the terminate signal
	 * @return The Specifies the delay (millis) before the Launcher should be given the terminate signal
	 */
	public long getDelay() { return delay; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", delay="+delay+"]";
	}
	// Hand generated code.

} // class def. [TERMINATE_SERVER].

