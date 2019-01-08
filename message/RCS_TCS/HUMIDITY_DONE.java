package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: HUMIDITY_DONE.<br>
 * Instruct TCS to use the specified humidity level in its refraction calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>humidity - relative humidity.</dd>
 * <dd>&nbsp;values: 0.000 to 1.000</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class HUMIDITY_DONE extends RCS_TO_TCS_DONE {

	/** Create a HUMIDITY_DONE with specified id.
	 * @param id The unique id of this HUMIDITY_DONE.
	 */
	public HUMIDITY_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [HUMIDITY_DONE].

