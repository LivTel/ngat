package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SYSTEM_DONE.<br>
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
public class SYSTEM_DONE extends GUI_TO_RCS_DONE {

	/** Create a SYSTEM_DONE with specified id.
	 * @param id The unique id of this SYSTEM_DONE.
	 */
	public SYSTEM_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SYSTEM_DONE].

