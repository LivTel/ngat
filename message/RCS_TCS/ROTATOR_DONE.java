package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: ROTATOR_DONE.<br>
 * Instruct TCS to switch the rotator mode or move it.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - mode of operation.</dd>
 * <dd>&nbsp;values: { SKY | MOUNT | FLOAT | VERTICAL | VFLOAT }</dd>
 * <dd>position - sky position angles.</dd>
 * <dd>&nbsp;values: SKY (0.00 to 360.00 degs) or MOUNT (-270.00 to +270.00 degs)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ROTATOR_DONE extends RCS_TO_TCS_DONE {

	/** Create a ROTATOR_DONE with specified id.
	 * @param id The unique id of this ROTATOR_DONE.
	 */
	public ROTATOR_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [ROTATOR_DONE].

