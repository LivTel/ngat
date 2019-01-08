package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: WAVELENGTH_DONE.<br>
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
public class WAVELENGTH_DONE extends RCS_TO_TCS_DONE {

	/** Create a WAVELENGTH_DONE with specified id.
	 * @param id The unique id of this WAVELENGTH_DONE.
	 */
	public WAVELENGTH_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [WAVELENGTH_DONE].

