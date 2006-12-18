package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: WAVELENGTH.<br>
 * Instructs TCS to set the wavelength used in refraction calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>wavelength - null</dd>
 * <dd>&nbsp;values: 0.30 to 35.00 microns</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class WAVELENGTH extends RCS_TO_TCS {

	// Variables.

	/** The null*/
	protected double wavelength;

	/** Create a WAVELENGTH with specified id.
	 * @param id The unique id of this WAVELENGTH.
	 */
	public WAVELENGTH(String id) { super(id); }

	/** Create a WAVELENGTH with specified id and parameters.
	 * @param id The unique id of this WAVELENGTH.
	 * @param wavelength The null
	 */
	public WAVELENGTH(
	         String id,
	         double wavelength) {
	         super(id);
	           this.wavelength = wavelength;
	         }

	/** Set the null
	 * @param wavelength The null
	 */
	public void setWavelength(double wavelength) { this.wavelength = wavelength; }

	/** Get the null
	 * @return The null
	 */
	public double getWavelength() { return wavelength; }

	// Hand generated code.

} // class def. [WAVELENGTH].

