package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TELSLEW_DONE.<br>
 *  Supplies the information required to slew the telescope to a source.<br>
 *  The source can be moving or fixed with appropriate parameters.<br>
 *  Module code: 692000<br>
 * <br>
 *  Example: TELSLEW FIXED "NGC 2525" 10 10 12.3 24 26 56.6 J2000.0<br>
 * <br>
 *  Example: TELSLEW MOVING MARS<br>
 * <br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>source - target to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.phase2.Source or subclass</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELSLEW_DONE extends POS_TO_RCS_DONE {

	/** Create a TELSLEW_DONE with specified id.
	 * @param id The unique id of this TELSLEW_DONE.
	 */
	public TELSLEW_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELSLEW_DONE].

