package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: ALTITUDE_DONE.<br>
 * Instruct the TCS to slew the telescope to the specified altitude and stop.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>angle - altitude angle to move to.</dd>
 * <dd>&nbsp;values: 10.0 to 95.0 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ALTITUDE_DONE extends RCS_TO_TCS_DONE {

	/** Create a ALTITUDE_DONE with specified id.
	 * @param id The unique id of this ALTITUDE_DONE.
	 */
	public ALTITUDE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [ALTITUDE_DONE].

