package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: TRACK_DONE.<br>
 * Instructs TCS to start or stop various mechanisms.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - keyword to indicate mechanism to set state of.</dd>
 * <dd>&nbsp;values: {ALL | AGFOCUS | ALTITUDE | AZIMUTH | FOCUS | ROTATOR}</dd>
 * <dd>state - keyword to indicate state to put specified mechanism into.</dd>
 * <dd>&nbsp;values: {ON | OFF}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TRACK_DONE extends RCS_TO_TCS_DONE {

	/** Create a TRACK_DONE with specified id.
	 * @param id The unique id of this TRACK_DONE.
	 */
	public TRACK_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [TRACK_DONE].

