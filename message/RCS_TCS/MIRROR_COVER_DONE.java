package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: MIRROR_COVER_DONE.<br>
 * Instruct TCS to open or close the mirror cover.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place mirror cover into.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class MIRROR_COVER_DONE extends RCS_TO_TCS_DONE {

	/** Create a MIRROR_COVER_DONE with specified id.
	 * @param id The unique id of this MIRROR_COVER_DONE.
	 */
	public MIRROR_COVER_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [MIRROR_COVER_DONE].

