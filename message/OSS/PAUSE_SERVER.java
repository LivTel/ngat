package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: PAUSE_SERVER.<br>
 * Command: PAUSE_SERVER<br>
 * Requests the OSS server to pause <br>
 * While paused, it will only accept requests from clients of types<br>
 *  [CONTROL] or [ADMIN].<br>
 * The OSS server is restarted by issuing a RESTART_SERVER request.<br>
 * Module code: 702200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>message - null</dd>
 * <dd>&nbsp;values: A message to deliver to clients which try to connect while paused</dd>
 * <dd>expectedRestartTime - null</dd>
 * <dd>&nbsp;values: Time when the OSS Server is 'expected' to restart - not guaranteed</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>implementationTime - Time when request was made to OSS server</dd>
 * </dl>
 * <br>
 */
public class PAUSE_SERVER extends TRANSACTION {

	// Variables.

	/** The null*/
	protected String message;

	/** The null*/
	protected long expectedRestartTime;

	/** Create a PAUSE_SERVER with specified id.
	 * @param id The unique id of this PAUSE_SERVER.
	 */
	public PAUSE_SERVER(String id) { super(id); }

	/** Create a PAUSE_SERVER with specified id and parameters.
	 * @param id The unique id of this PAUSE_SERVER.
	 * @param message The null
	 * @param expectedRestartTime The null
	 */
	public PAUSE_SERVER(
	         String id,
	         String message,
	         long expectedRestartTime) {
	         super(id);
	           this.message = message;
	           this.expectedRestartTime = expectedRestartTime;
	         }

	/** Set the null
	 * @param message The null
	 */
	public void setMessage(String message) { this.message = message; }

	/** Get the null
	 * @return The null
	 */
	public String getMessage() { return message; }

	/** Set the null
	 * @param expectedRestartTime The null
	 */
	public void setExpectedRestartTime(long expectedRestartTime) { this.expectedRestartTime = expectedRestartTime; }

	/** Get the null
	 * @return The null
	 */
	public long getExpectedRestartTime() { return expectedRestartTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", message="+message+
			", expectedRestartTime="+expectedRestartTime+"]";
	}
	// Hand generated code.

} // class def. [PAUSE_SERVER].

