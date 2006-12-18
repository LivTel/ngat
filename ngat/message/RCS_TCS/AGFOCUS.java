package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGFOCUS.<br>
 * Instructs the TCS to set the autoguider focus position.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>focus - required focus position.</dd>
 * <dd>&nbsp;values: 0.00 to 25.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGFOCUS extends RCS_TO_TCS {

	// Variables.

	/** The required focus position.*/
	protected double focus;

	/** Create a AGFOCUS with specified id.
	 * @param id The unique id of this AGFOCUS.
	 */
	public AGFOCUS(String id) { super(id); }

	/** Create a AGFOCUS with specified id and parameters.
	 * @param id The unique id of this AGFOCUS.
	 * @param focus The required focus position.
	 */
	public AGFOCUS(
	         String id,
	         double focus) {
	         super(id);
	           this.focus = focus;
	         }

	/** Set the required focus position.
	 * @param focus The required focus position.
	 */
	public void setFocus(double focus) { this.focus = focus; }

	/** Get the required focus position.
	 * @return The required focus position.
	 */
	public double getFocus() { return focus; }

	// Hand generated code.

} // class def. [AGFOCUS].

