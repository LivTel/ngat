package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: POLE_DONE.<br>
 * Instructs TCS to set the polar motion corrections.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>xPos - X axis correction for polar motion.</dd>
 * <dd>&nbsp;values: -1.00 to +1.000 arcsec</dd>
 * <dd>yPos - Y axis correction for polar motion.</dd>
 * <dd>&nbsp;values: -1.00 to +1.000 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class POLE_DONE extends RCS_TO_TCS_DONE {

	/** Create a POLE_DONE with specified id.
	 * @param id The unique id of this POLE_DONE.
	 */
	public POLE_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [POLE_DONE].

