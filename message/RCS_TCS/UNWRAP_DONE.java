package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: UNWRAP_DONE.<br>
 * Instructs TCS to unwrap the specified mechanism if possible.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - mechanism to attempt to unwrap.</dd>
 * <dd>&nbsp;values: { AZIMUTH | ROTATOR }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class UNWRAP_DONE extends RCS_TO_TCS_DONE {

	/** Create a UNWRAP_DONE with specified id.
	 * @param id The unique id of this UNWRAP_DONE.
	 */
	public UNWRAP_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [UNWRAP_DONE].

