package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: PRESSURE_DONE.<br>
 * Instructs TCS the value of the barometric pressure to use in refraction calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>pressure - pressure to use for calculations.</dd>
 * <dd>&nbsp;values: 500.00 to 1200.00 mbars</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class PRESSURE_DONE extends RCS_TO_TCS_DONE {

	/** Create a PRESSURE_DONE with specified id.
	 * @param id The unique id of this PRESSURE_DONE.
	 */
	public PRESSURE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [PRESSURE_DONE].

