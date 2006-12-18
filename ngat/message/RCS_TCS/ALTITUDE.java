package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: ALTITUDE.<br>
 * Instruct the TCS to slew the telescope to the specified altitude and stop.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>angle - altitude angle to move to.</dd>
 * <dd>&nbsp;values: 10.0 to 95.0 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ALTITUDE extends RCS_TO_TCS {

	// Variables.

	/** The altitude angle to move to.*/
	protected double angle;

	/** Create a ALTITUDE with specified id.
	 * @param id The unique id of this ALTITUDE.
	 */
	public ALTITUDE(String id) { super(id); }

	/** Create a ALTITUDE with specified id and parameters.
	 * @param id The unique id of this ALTITUDE.
	 * @param angle The altitude angle to move to.
	 */
	public ALTITUDE(
	         String id,
	         double angle) {
	         super(id);
	           this.angle = angle;
	         }

	/** Set the altitude angle to move to.
	 * @param angle The altitude angle to move to.
	 */
	public void setAngle(double angle) { this.angle = angle; }

	/** Get the altitude angle to move to.
	 * @return The altitude angle to move to.
	 */
	public double getAngle() { return angle; }

	// Hand generated code.

} // class def. [ALTITUDE].

