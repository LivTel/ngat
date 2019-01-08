package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: OPERATIONAL_DONE.<br>
 * Instruct TCS to change from day to night state or vice versa.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state of operation.</dd>
 * <dd>&nbsp;values: { ON | OFF }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OPERATIONAL_DONE extends RCS_TO_TCS_DONE {

	/** Create a OPERATIONAL_DONE with specified id.
	 * @param id The unique id of this OPERATIONAL_DONE.
	 */
	public OPERATIONAL_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [OPERATIONAL_DONE].

