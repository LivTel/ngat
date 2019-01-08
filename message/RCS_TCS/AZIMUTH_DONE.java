package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AZIMUTH_DONE.<br>
 * Instruct the TCS to slew the telescope to the spcified azimuth and stop.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>angle - azimuth angle to slew to.</dd>
 * <dd>&nbsp;values: -270.00 to +270.00 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AZIMUTH_DONE extends RCS_TO_TCS_DONE {

	/** Create a AZIMUTH_DONE with specified id.
	 * @param id The unique id of this AZIMUTH_DONE.
	 */
	public AZIMUTH_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AZIMUTH_DONE].

