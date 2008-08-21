package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REBOOT_SERVER.<br>
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
public class REBOOT_SERVER extends TRANSACTION {

	// Constants.

	/** Constant: Indicates Level 0 - Redatum (re-load config settings without stopping DO NOT USE).*/
	public static final int REDATUM = 702101;

	/** Constant: Indicates  Level 1 - Restart JVM (OSS watchdog).*/
	public static final int RESTARTM = 702102;

	/** Constant: Indicates  Level 2 - Stop the JVM and watchdog.*/
	public static final int STOP = 702103;

	/** Constant: Indicates  Level 3 - Reboot the system (..sbin/reboot).*/
	public static final int REBOOT = 702104;

	/** Constant: Indicates  Level 4 - Shutdown the system (..sbin/shutdown -h now).*/
	public static final int SHUTDOWN = 702105;

	// Variables.

	/** The Selects the appropriate level of reboot*/
	protected int level;

	/** Create a REBOOT_SERVER with specified id.
	 * @param id The unique id of this REBOOT_SERVER.
	 */
	public REBOOT_SERVER(String id) { super(id); }

	/** Create a REBOOT_SERVER with specified id and parameters.
	 * @param id The unique id of this REBOOT_SERVER.
	 * @param level The Selects the appropriate level of reboot
	 */
	public REBOOT_SERVER(
	         String id,
	         int level) {
	         super(id);
	           this.level = level;
	         }

	/** Set the Selects the appropriate level of reboot
	 * @param level The Selects the appropriate level of reboot
	 */
	public void setLevel(int level) { this.level = level; }

	/** Get the Selects the appropriate level of reboot
	 * @return The Selects the appropriate level of reboot
	 */
	public int getLevel() { return level; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", level="+level+"]";
	}
	// Hand generated code.

} // class def. [REBOOT_SERVER].

