package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: INIT_SCHEDULE.<br>
 * Command: INIT_SCHEDULE<br>
 * Requests the OSS to start scheduled operations.<br>
 * The OSS may use the opportunity to carry out any caching results of<br>
 * regularly used but expensive calculations or searches. E.g. the<br>
 * sequence of FixedGroups over the (rest of) the night could be <br>
 * determined and saved at this time.<br>
 * Module code: 703800<br>
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
public class INIT_SCHEDULE extends TRANSACTION {

	/** Create a INIT_SCHEDULE with specified id.
	 * @param id The unique id of this INIT_SCHEDULE.
	 */
	public INIT_SCHEDULE(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [INIT_SCHEDULE].

