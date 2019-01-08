package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TELSTATUS.<br>
 *  Requests telescope status information from the RCS. This can only work during<br>
 *  scheduled planetarium mode periods. Outside of this time a status service is<br>
 *  obtained via a dedicated web site. <a href = "http://TBD">Telescope status pages</a>.<br>
 *  Module code: 690900<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - table of name, value pairs indicating various telescope statii.</dd>
 * </dl>
 * <br>
 */
public class TELSTATUS extends POS_TO_RCS {

	/** Create a TELSTATUS with specified id.
	 * @param id The unique id of this TELSTATUS.
	 */
	public TELSTATUS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELSTATUS].

