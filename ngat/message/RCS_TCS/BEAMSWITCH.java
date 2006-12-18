package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: BEAMSWITCH.<br>
 * Instruct the TCS to offset the telescope from the reference position so that an image moves to a given (x,y) in the focal plane.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>offsetX - offset in x direction.</dd>
 * <dd>&nbsp;values: -100.00 to +100.00 arcsecs</dd>
 * <dd>offsetY - offset in y direction.</dd>
 * <dd>&nbsp;values: -100.00 to +100.00 arcsecs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class BEAMSWITCH extends RCS_TO_TCS {

	// Variables.

	/** The offset in x direction.*/
	protected double offsetX;

	/** The offset in y direction.*/
	protected double offsetY;

	/** Create a BEAMSWITCH with specified id.
	 * @param id The unique id of this BEAMSWITCH.
	 */
	public BEAMSWITCH(String id) { super(id); }

	/** Create a BEAMSWITCH with specified id and parameters.
	 * @param id The unique id of this BEAMSWITCH.
	 * @param offsetX The offset in x direction.
	 * @param offsetY The offset in y direction.
	 */
	public BEAMSWITCH(
	         String id,
	         double offsetX,
	         double offsetY) {
	         super(id);
	           this.offsetX = offsetX;
	           this.offsetY = offsetY;
	         }

	/** Set the offset in x direction.
	 * @param offsetX The offset in x direction.
	 */
	public void setOffsetX(double offsetX) { this.offsetX = offsetX; }

	/** Get the offset in x direction.
	 * @return The offset in x direction.
	 */
	public double getOffsetX() { return offsetX; }

	/** Set the offset in y direction.
	 * @param offsetY The offset in y direction.
	 */
	public void setOffsetY(double offsetY) { this.offsetY = offsetY; }

	/** Get the offset in y direction.
	 * @return The offset in y direction.
	 */
	public double getOffsetY() { return offsetY; }

	// Hand generated code.

} // class def. [BEAMSWITCH].

