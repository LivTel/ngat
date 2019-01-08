package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGWAVELENGTH_DONE.<br>
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
public class AGWAVELENGTH_DONE extends RCS_TO_TCS_DONE {

	/** Create a AGWAVELENGTH_DONE with specified id.
	 * @param id The unique id of this AGWAVELENGTH_DONE.
	 */
	public AGWAVELENGTH_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [AGWAVELENGTH_DONE].

