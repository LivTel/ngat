package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: TEMPERATURE_DONE.<br>
 * Instructs TCS the value of atmospheric temperature to use in refraction claculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>temperature - temperature to use in refraction calculation.</dd>
 * <dd>&nbsp;values: -20.00 to +40.00 degs C</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TEMPERATURE_DONE extends RCS_TO_TCS_DONE {

	/** Create a TEMPERATURE_DONE with specified id.
	 * @param id The unique id of this TEMPERATURE_DONE.
	 */
	public TEMPERATURE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [TEMPERATURE_DONE].

