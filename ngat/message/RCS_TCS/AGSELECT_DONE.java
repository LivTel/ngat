package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGSELECT_DONE.<br>
 * Instructs the TCS to select a specified autoguider.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>autoguider - autoguider to use.</dd>
 * <dd>&nbsp;values: { from config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGSELECT_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGSELECT_DONE with specified id.
	 * @param id The unique id of this AGSELECT_DONE.
	 */
	public AGSELECT_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGSELECT_DONE].

