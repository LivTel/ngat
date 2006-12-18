package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: DFOCUS_DONE.<br>
 * Instructs TCS to change the focus by a specified amount. Usaually this is issued by an instrument via the ISS to allow focus offsetting to compensate for filter changes.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>offset - amount to offset focus by.</dd>
 * <dd>&nbsp;values: -30.00 to +30.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class DFOCUS_DONE extends RCS_TO_TCS_DONE {

	/** Create a DFOCUS_DONE with specified id.
	 * @param id The unique id of this DFOCUS_DONE.
	 */
	public DFOCUS_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [DFOCUS_DONE].

