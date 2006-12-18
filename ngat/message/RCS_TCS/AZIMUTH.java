package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AZIMUTH.<br>
 * Instruct the TCS to slew the telescope to the spcified azimuth and stop.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>angle - azimuth angle to slew to.</dd>
 * <dd>&nbsp;values: -270.00 to +270.00 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AZIMUTH extends RCS_TO_TCS {

	// Variables.

	/** The azimuth angle to slew to.*/
	protected double angle;

	/** Create a AZIMUTH with specified id.
	 * @param id The unique id of this AZIMUTH.
	 */
	public AZIMUTH(String id) { super(id); }

	/** Create a AZIMUTH with specified id and parameters.
	 * @param id The unique id of this AZIMUTH.
	 * @param angle The azimuth angle to slew to.
	 */
	public AZIMUTH(
	         String id,
	         double angle) {
	         super(id);
	           this.angle = angle;
	         }

	/** Set the azimuth angle to slew to.
	 * @param angle The azimuth angle to slew to.
	 */
	public void setAngle(double angle) { this.angle = angle; }

	/** Get the azimuth angle to slew to.
	 * @return The azimuth angle to slew to.
	 */
	public double getAngle() { return angle; }

	// Hand generated code.

} // class def. [AZIMUTH].

