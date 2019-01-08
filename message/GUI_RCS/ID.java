package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: ID.<br>
 *  Request status information.<br>
 * <br>
 *  Module code: 680400<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>control - process in control</dd>
 * <dd>engineering - the RCS is running in ENGINEERING mode</dd>
 * <dd>operational - the RCS is currently OPERATIONAL</dd>
 * <dd>lastStatus - RCS exit status on last startup attempt by RCW</dd>
 * <dd>agentInControl - ID of MCA currently in control or null</dd>
 * <dd>agentName - Name of the MCA currently in control or null</dd>
 * <dd>agentActivity - Activity that AIC is doing or null</dd>
 * </dl>
 * <br>
 */
public class ID extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS is currently in control*/
	public static final int RCS_PROCESS = 680401;

	/** Constant: Indicates that the Watchdog is in control*/
	public static final int WATCHDOG_PROCESS = 680402;

	/** Create a ID with specified id.
	 * @param id The unique id of this ID.
	 */
	public ID(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ID].

