package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGFILTER_DONE.<br>
 * Instructs the TCS to move the autoguider filter in or out.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place autoguider filter in.</dd>
 * <dd>&nbsp;values: { IN | OUT }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGFILTER_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGFILTER_DONE with specified id.
	 * @param id The unique id of this AGFILTER_DONE.
	 */
	public AGFILTER_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGFILTER_DONE].

