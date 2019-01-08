package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND_DONE: SLEW_DONE.<br>
 * Instruct the TCS to slew the telescope to the specified position and begin tracking.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>source - source object to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.Phase2.Source or subclass</dd>
 * <dd>offsetRA - offset in RA.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dd>offsetDec - offset in dec.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SLEW_DONE extends RCS_TO_TCS_DONE {

	/** Create a SLEW_DONE with specified id.
	 * @param id The unique id of this SLEW_DONE.
	 */
	public SLEW_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SLEW_DONE].

