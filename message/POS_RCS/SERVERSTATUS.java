package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: SERVERSTATUS.<br>
 *  Supplies status information about the PCR server.<br>
 * <br>
 *  Module code 691000<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>uptime - time the server has been up (millis).</dd>
 * </dl>
 * <br>
 */
public class SERVERSTATUS extends POS_TO_RCS {

	/** Create a SERVERSTATUS with specified id.
	 * @param id The unique id of this SERVERSTATUS.
	 */
	public SERVERSTATUS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SERVERSTATUS].

