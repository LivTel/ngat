package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: STOP_DONE.<br>
 * Instructs TCS to stop the telescope or specified mechanism.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - mechanism to stop.</dd>
 * <dd>&nbsp;values: { ALL | AGFOCUS | AGPROBE | ALTITUDE | AZIMUTH | ENCLOSURE | FOCUS | ROTATOR }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class STOP_DONE extends RCS_TO_TCS_DONE {

	/** Create a STOP_DONE with specified id.
	 * @param id The unique id of this STOP_DONE.
	 */
	public STOP_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [STOP_DONE].

