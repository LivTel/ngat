package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: ENCLOSURE_DONE.<br>
 * Instructs the TCS to open or close the enclosure.<br>
 * (Which shutter is which the TCS uses Shutter1 and Shutter2?)<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - enclosure section to open or close.</dd>
 * <dd>&nbsp;values: { BOTH | NORTH | SOUTH }</dd>
 * <dd>state - state to move the enclosure to.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dd>pos - precise angle to open/close to.</dd>
 * <dd>&nbsp;values: 0.00 to 90.00 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ENCLOSURE_DONE extends RCS_TO_TCS_DONE {

	/** Create a ENCLOSURE_DONE with specified id.
	 * @param id The unique id of this ENCLOSURE_DONE.
	 */
	public ENCLOSURE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [ENCLOSURE_DONE].

