package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: TWEAK_DONE.<br>
 * Instructs TCS to apply a given x,y rotational aperture offset to align a field on the instrument.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>xOffset - offset in X axis.</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dd>yOffset - offset in Y axis.</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dd>rotation - rotation offset..</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TWEAK_DONE extends RCS_TO_TCS_DONE {

	/** Create a TWEAK_DONE with specified id.
	 * @param id The unique id of this TWEAK_DONE.
	 */
	public TWEAK_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [TWEAK_DONE].

