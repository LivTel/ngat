package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGFOCUS_DONE.<br>
 * Instructs the TCS to set the autoguider focus position.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>focus - required focus position.</dd>
 * <dd>&nbsp;values: 0.00 to 25.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGFOCUS_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGFOCUS_DONE with specified id.
	 * @param id The unique id of this AGFOCUS_DONE.
	 */
	public AGFOCUS_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGFOCUS_DONE].

