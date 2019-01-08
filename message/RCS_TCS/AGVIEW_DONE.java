package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGVIEW_DONE.<br>
 * Instructs the TCS to perform an aperture offset to move an image into the autoguider field of view.<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGVIEW_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGVIEW_DONE with specified id.
	 * @param id The unique id of this AGVIEW_DONE.
	 */
	public AGVIEW_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGVIEW_DONE].

