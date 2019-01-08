package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGRADIAL_DONE.<br>
 * Instructs the TCS to move the autoguider probe to a radial position.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - radial position from field edge.</dd>
 * <dd>&nbsp;values: 0.00 to 110.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGRADIAL_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGRADIAL_DONE with specified id.
	 * @param id The unique id of this AGRADIAL_DONE.
	 */
	public AGRADIAL_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGRADIAL_DONE].

