package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGMOVE_DONE.<br>
 * Instructs the TCS to move the autoguider probe to a position<br>
 * and Rotator to a given mount position angle and guide on a pixels.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - radial position from field edge.</dd>
 * <dd>&nbsp;values: 0.00 to 110.00 mm</dd>
 * <dd>angle - mount position angle.</dd>
 * <dd>&nbsp;values: -270.00 to +270.00 degs</dd>
 * <dd>pixelX - autoguider X pixel.</dd>
 * <dd>&nbsp;values: 0.00 to 1024.00</dd>
 * <dd>pixelY - autoguider Y pixel.</dd>
 * <dd>&nbsp;values: 0.00 to 1024.00</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGMOVE_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGMOVE_DONE with specified id.
	 * @param id The unique id of this AGMOVE_DONE.
	 */
	public AGMOVE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGMOVE_DONE].

