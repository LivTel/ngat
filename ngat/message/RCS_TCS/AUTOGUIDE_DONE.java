package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AUTOGUIDE_DONE.<br>
 * Instructs TCS to switch the autoguider state.<br>
 * see notes in RCS_TCS ICD.<br>
 * This is ASTROGUIDE !<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to put autoguider in.</dd>
 * <dd>&nbsp;values: { ON | OFF | SUSPEND | RESUME }</dd>
 * <dd>mode - guide star selection criterion.</dd>
 * <dd>&nbsp;values: { RANK Ý RANGE Ý PIXEL }</dd>
 * <dd>range1 - start of magnitude range for guide star selection.</dd>
 * <dd>&nbsp;values: 0.0 to 20.0 mag</dd>
 * <dd>range2 - end of magnitude range for guide star selection.</dd>
 * <dd>&nbsp;values: 0.0 to 20.0 mag</dd>
 * <dd>rank - brightness ranking of star to select for guiding.</dd>
 * <dd>&nbsp;values: 1 to 10</dd>
 * <dd>xPix - x coordinate of pixel where guide star is located.</dd>
 * <dd>&nbsp;values: 0 to 2047</dd>
 * <dd>yPix - y coordinate of pixel where guide star is located.</dd>
 * <dd>&nbsp;values: 0 to 2047</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>guidemag - magnitude of the selected guide star.</dd>
 * </dl>
 * <br>
 */
public class AUTOGUIDE_DONE extends RCS_TO_TCS_DONE {

	// Variables.

	/** The magnitude of the selected guide star.*/
	protected double guidemag;

	/** Create a AUTOGUIDE_DONE with specified id.
	 * @param id The unique id of this AUTOGUIDE_DONE.
	 */
	public AUTOGUIDE_DONE (String id) { super(id); }

	/** Set the magnitude of the selected guide star.
	 * @param guidemag The magnitude of the selected guide star..
	 */
	public void setGuidemag(double guidemag) { this.guidemag = guidemag; }

	/** Get the magnitude of the selected guide star.
	 * @return The magnitude of the selected guide star.
	 */
	public double getGuidemag() { return guidemag; }

	// Hand generated code.

} // class def. [AUTOGUIDE_DONE].

