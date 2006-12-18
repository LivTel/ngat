package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDSTATUS.<br>
 *  Requests CCD camera status information from the Robotic Control Syatem. This data is<br>
 *  only available during scheduled planetarium mode operations. Outside of<br>
 *  this period weather data is available from a dedicated web site. <br>
 *  <a href = "http://TBD">Instrument status pages</a>.<br>
 *  Module code: 690600<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - table of name, value pairs indicating various CCD statii.</dd>
 * </dl>
 * <br>
 */
public class CCDSTATUS extends POS_TO_RCS {

	/** Create a CCDSTATUS with specified id.
	 * @param id The unique id of this CCDSTATUS.
	 */
	public CCDSTATUS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [CCDSTATUS].

