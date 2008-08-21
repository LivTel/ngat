package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REBOOT_SERVER_DONE.<br>
 * Command: REBOOT_SERVER<br>
 * Requests the OSS to reboot the server. <br>
 * The levels of reboot are:<br>
 *  0 - Redatum (re-load config settings without stopping DO NOT USE).<br>
 *  1 - Restart JVM (OSS watchdog).<br>
 *  2 - Stop the JVM and watchdog.<br>
 *  3 - Reboot the system (..sbin/reboot).<br>
 *  4 - Shutdown the system (..sbin/shutdown -h now).<br>
 * <br>
 * Module code: 702100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>level - Selects the appropriate level of reboot</dd>
 * <dd>&nbsp;values: A valid reboot level</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>rebootTime - Time the request was made to the OSS</dd>
 * </dl>
 * <br>
 */
public class REBOOT_SERVER_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The Time the request was made to the OSS*/
	protected long rebootTime;

	/** Create a REBOOT_SERVER_DONE with specified id.
	 * @param id The unique id of this REBOOT_SERVER_DONE.
	 */
	public REBOOT_SERVER_DONE (String id) { super(id); }

	/** Set the Time the request was made to the OSS
	 * @param rebootTime The Time the request was made to the OSS.
	 */
	public void setRebootTime(long rebootTime) { this.rebootTime = rebootTime; }

	/** Get the Time the request was made to the OSS
	 * @return The Time the request was made to the OSS
	 */
	public long getRebootTime() { return rebootTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", rebootTime="+rebootTime+"]";
	}
	// Hand generated code.

} // class def. [REBOOT_SERVER_DONE].

