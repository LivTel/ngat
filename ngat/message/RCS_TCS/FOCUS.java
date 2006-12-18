package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: FOCUS.<br>
 * Instruct the TCS to drive the focus to a specified setting.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>focus - virtual focus position.</dd>
 * <dd>&nbsp;values: -30.00 to +30.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class FOCUS extends RCS_TO_TCS {

	// Variables.

	/** The virtual focus position.*/
	protected double focus;

	/** Create a FOCUS with specified id.
	 * @param id The unique id of this FOCUS.
	 */
	public FOCUS(String id) { super(id); }

	/** Create a FOCUS with specified id and parameters.
	 * @param id The unique id of this FOCUS.
	 * @param focus The virtual focus position.
	 */
	public FOCUS(
	         String id,
	         double focus) {
	         super(id);
	           this.focus = focus;
	         }

	/** Set the virtual focus position.
	 * @param focus The virtual focus position.
	 */
	public void setFocus(double focus) { this.focus = focus; }

	/** Get the virtual focus position.
	 * @return The virtual focus position.
	 */
	public double getFocus() { return focus; }

	// Hand generated code.

} // class def. [FOCUS].

