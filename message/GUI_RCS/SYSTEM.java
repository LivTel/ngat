package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SYSTEM.<br>
 *  Restart, reboot, shutdown the RCS / RCW.<br>
 * <br>
 *  The implementation matches the REBOOT command used<br>
 *  on instrument (ICS) and OSS systems.<br>
 * <br>
 *  Module code: 680800<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>level - level option</dd>
 * <dd>&nbsp;values: { RESTART_ENG | RESTART_AUTO | HALT | REBOOT | SHUTDOWN }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SYSTEM extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS should be restarted in ENG mode*/
	public static final int RESTART_ENGINEERING = 680801;

	/** Constant: Indicates that the RCS should be restarted in AUTO mode*/
	public static final int RESTART_AUTOMATIC = 680802;

	/** Constant: Indicates that the RCS should shutdown and not be restarted by RCW*/
	public static final int HALT = 680803;

	/** Constant: Indicates that the RCW should request <b>occ</b> to reboot*/
	public static final int REBOOT = 680804;

	/** Constant: Indicates that the RCW should request <b>occ</b> to shutdown*/
	public static final int SHUTDOWN = 680805;

	/** Constant: Indicates that the system request cannot be actioned at this time*/
	public static final int NOT_AVAILABLE = 680801;

	// Variables.

	/** The level option*/
	protected int level;

	/** Create a SYSTEM with specified id.
	 * @param id The unique id of this SYSTEM.
	 */
	public SYSTEM(String id) { super(id); }

	/** Create a SYSTEM with specified id and parameters.
	 * @param id The unique id of this SYSTEM.
	 * @param level The level option
	 */
	public SYSTEM(
	         String id,
	         int level) {
	         super(id);
	           this.level = level;
	         }

	/** Set the level option
	 * @param level The level option
	 */
	public void setLevel(int level) { this.level = level; }

	/** Get the level option
	 * @return The level option
	 */
	public int getLevel() { return level; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", level="+level+"]";
	}
	// Hand generated code.

} // class def. [SYSTEM].

