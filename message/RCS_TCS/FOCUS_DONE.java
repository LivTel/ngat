package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: FOCUS_DONE.<br>
 * Instruct the TCS to drive the focus to a specified setting.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>focus - virtual focus position.</dd>
 * <dd>&nbsp;values: -30.00 to +30.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class FOCUS_DONE extends RCS_TO_TCS_DONE {

	/** Create a FOCUS_DONE with specified id.
	 * @param id The unique id of this FOCUS_DONE.
	 */
	public FOCUS_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [FOCUS_DONE].

