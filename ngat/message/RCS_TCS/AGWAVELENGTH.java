package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGWAVELENGTH.<br>
 * Instructs the TCS to enter the specified effective wavelength for use in refraction calculations for the autoguider.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>wavelength - wavelength to use for refraction calculations.</dd>
 * <dd>&nbsp;values: 300 to 35000 nm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGWAVELENGTH extends RCS_TO_TCS {

	// Variables.

	/** The wavelength to use for refraction calculations.*/
	protected int wavelength;

	/** Create a AGWAVELENGTH with specified id.
	 * @param id The unique id of this AGWAVELENGTH.
	 */
	public AGWAVELENGTH(String id) { super(id); }

	/** Create a AGWAVELENGTH with specified id and parameters.
	 * @param id The unique id of this AGWAVELENGTH.
	 * @param wavelength The wavelength to use for refraction calculations.
	 */
	public AGWAVELENGTH(
	         String id,
	         int wavelength) {
	         super(id);
	           this.wavelength = wavelength;
	         }

	/** Set the wavelength to use for refraction calculations.
	 * @param wavelength The wavelength to use for refraction calculations.
	 */
	public void setWavelength(int wavelength) { this.wavelength = wavelength; }

	/** Get the wavelength to use for refraction calculations.
	 * @return The wavelength to use for refraction calculations.
	 */
	public int getWavelength() { return wavelength; }

	// Hand generated code.

} // class def. [AGWAVELENGTH].

