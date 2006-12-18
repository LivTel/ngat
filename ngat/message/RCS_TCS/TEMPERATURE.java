package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: TEMPERATURE.<br>
 * Instructs TCS the value of atmospheric temperature to use in refraction claculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>temperature - temperature to use in refraction calculation.</dd>
 * <dd>&nbsp;values: -20.00 to +40.00 degs C</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TEMPERATURE extends RCS_TO_TCS {

	// Variables.

	/** The temperature to use in refraction calculation.*/
	protected double temperature;

	/** Create a TEMPERATURE with specified id.
	 * @param id The unique id of this TEMPERATURE.
	 */
	public TEMPERATURE(String id) { super(id); }

	/** Create a TEMPERATURE with specified id and parameters.
	 * @param id The unique id of this TEMPERATURE.
	 * @param temperature The temperature to use in refraction calculation.
	 */
	public TEMPERATURE(
	         String id,
	         double temperature) {
	         super(id);
	           this.temperature = temperature;
	         }

	/** Set the temperature to use in refraction calculation.
	 * @param temperature The temperature to use in refraction calculation.
	 */
	public void setTemperature(double temperature) { this.temperature = temperature; }

	/** Get the temperature to use in refraction calculation.
	 * @return The temperature to use in refraction calculation.
	 */
	public double getTemperature() { return temperature; }

	// Hand generated code.

} // class def. [TEMPERATURE].

