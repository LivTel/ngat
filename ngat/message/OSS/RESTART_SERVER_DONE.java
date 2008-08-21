package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: RESTART_SERVER_DONE.<br>
 * Command: RESTART_SERVER<br>
 * Wakes the OSS server up from a pause (see PAUSE_SERVER).<br>
 * There are no parameters.<br>
 * Module code: 702700<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class RESTART_SERVER_DONE extends TRANSACTION_DONE {

	/** Create a RESTART_SERVER_DONE with specified id.
	 * @param id The unique id of this RESTART_SERVER_DONE.
	 */
	public RESTART_SERVER_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [RESTART_SERVER_DONE].

