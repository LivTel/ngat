package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: HUMIDITY.<br>
 * Instruct TCS to use the specified humidity level in its refraction calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>humidity - relative humidity.</dd>
 * <dd>&nbsp;values: 0.000 to 1.000</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class HUMIDITY extends RCS_TO_TCS {

	// Variables.

	/** The relative humidity.*/
	protected double humidity;

	/** Create a HUMIDITY with specified id.
	 * @param id The unique id of this HUMIDITY.
	 */
	public HUMIDITY(String id) { super(id); }

	/** Create a HUMIDITY with specified id and parameters.
	 * @param id The unique id of this HUMIDITY.
	 * @param humidity The relative humidity.
	 */
	public HUMIDITY(
	         String id,
	         double humidity) {
	         super(id);
	           this.humidity = humidity;
	         }

	/** Set the relative humidity.
	 * @param humidity The relative humidity.
	 */
	public void setHumidity(double humidity) { this.humidity = humidity; }

	/** Get the relative humidity.
	 * @return The relative humidity.
	 */
	public double getHumidity() { return humidity; }

	// Hand generated code.

} // class def. [HUMIDITY].

