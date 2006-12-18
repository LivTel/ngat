package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: PARK_DONE.<br>
 * Instruct TCS to park the telescope.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - position to park the telescope.</dd>
 * <dd>&nbsp;values: { in config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class PARK_DONE extends RCS_TO_TCS_DONE {

	/** Create a PARK_DONE with specified id.
	 * @param id The unique id of this PARK_DONE.
	 */
	public PARK_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [PARK_DONE].

