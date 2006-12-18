package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: BEAMSWITCH_DONE.<br>
 * Instruct the TCS to offset the telescope from the reference position so that an image moves to a given (x,y) in the focal plane.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>offsetX - offset in x direction.</dd>
 * <dd>&nbsp;values: -100.00 to +100.00 arcsecs</dd>
 * <dd>offsetY - offset in y direction.</dd>
 * <dd>&nbsp;values: -100.00 to +100.00 arcsecs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class BEAMSWITCH_DONE extends RCS_TO_TCS_DONE {

	/** Create a BEAMSWITCH_DONE with specified id.
	 * @param id The unique id of this BEAMSWITCH_DONE.
	 */
	public BEAMSWITCH_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [BEAMSWITCH_DONE].

