package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: PAUSE_SERVER_DONE.<br>
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
public class PAUSE_SERVER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The Time when request was made to OSS server*/
	protected long implementationTime;

	/** Create a PAUSE_SERVER_DONE with specified id.
	 * @param id The unique id of this PAUSE_SERVER_DONE.
	 */
	public PAUSE_SERVER_DONE (String id) { super(id); }

	/** Set the Time when request was made to OSS server
	 * @param implementationTime The Time when request was made to OSS server.
	 */
	public void setImplementationTime(long implementationTime) { this.implementationTime = implementationTime; }

	/** Get the Time when request was made to OSS server
	 * @return The Time when request was made to OSS server
	 */
	public long getImplementationTime() { return implementationTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", implementationTime="+implementationTime+"]";
	}
	// Hand generated code.

} // class def. [PAUSE_SERVER_DONE].

