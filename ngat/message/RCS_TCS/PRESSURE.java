package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: PRESSURE.<br>
 * Instructs TCS the value of the barometric pressure to use in refraction calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>pressure - pressure to use for calculations.</dd>
 * <dd>&nbsp;values: 500.00 to 1200.00 mbars</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class PRESSURE extends RCS_TO_TCS {

	// Variables.

	/** The pressure to use for calculations.*/
	protected double pressure;

	/** Create a PRESSURE with specified id.
	 * @param id The unique id of this PRESSURE.
	 */
	public PRESSURE(String id) { super(id); }

	/** Create a PRESSURE with specified id and parameters.
	 * @param id The unique id of this PRESSURE.
	 * @param pressure The pressure to use for calculations.
	 */
	public PRESSURE(
	         String id,
	         double pressure) {
	         super(id);
	           this.pressure = pressure;
	         }

	/** Set the pressure to use for calculations.
	 * @param pressure The pressure to use for calculations.
	 */
	public void setPressure(double pressure) { this.pressure = pressure; }

	/** Get the pressure to use for calculations.
	 * @return The pressure to use for calculations.
	 */
	public double getPressure() { return pressure; }

	// Hand generated code.

} // class def. [PRESSURE].

