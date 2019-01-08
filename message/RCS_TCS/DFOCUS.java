package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: DFOCUS.<br>
 * Instructs TCS to change the focus by a specified amount. Usaually this is issued by an instrument via the ISS to allow focus offsetting to compensate for filter changes.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>offset - amount to offset focus by.</dd>
 * <dd>&nbsp;values: -30.00 to +30.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class DFOCUS extends RCS_TO_TCS {

	// Variables.

	/** The amount to offset focus by.*/
	protected double offset;

	/** Create a DFOCUS with specified id.
	 * @param id The unique id of this DFOCUS.
	 */
	public DFOCUS(String id) { super(id); }

	/** Create a DFOCUS with specified id and parameters.
	 * @param id The unique id of this DFOCUS.
	 * @param offset The amount to offset focus by.
	 */
	public DFOCUS(
	         String id,
	         double offset) {
	         super(id);
	           this.offset = offset;
	         }

	/** Set the amount to offset focus by.
	 * @param offset The amount to offset focus by.
	 */
	public void setOffset(double offset) { this.offset = offset; }

	/** Get the amount to offset focus by.
	 * @return The amount to offset focus by.
	 */
	public double getOffset() { return offset; }

	// Hand generated code.

} // class def. [DFOCUS].

