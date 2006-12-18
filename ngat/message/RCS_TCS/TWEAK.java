package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: TWEAK.<br>
 * Instructs TCS to apply a given x,y rotational aperture offset to align a field on the instrument.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>xOffset - offset in X axis.</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dd>yOffset - offset in Y axis.</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dd>rotation - rotation offset..</dd>
 * <dd>&nbsp;values: -3600.00 to +3600.00 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TWEAK extends RCS_TO_TCS {

	// Variables.

	/** The offset in X axis.*/
	protected double xOffset;

	/** The offset in Y axis.*/
	protected double yOffset;

	/** The rotation offset..*/
	protected double rotation;

	/** Create a TWEAK with specified id.
	 * @param id The unique id of this TWEAK.
	 */
	public TWEAK(String id) { super(id); }

	/** Create a TWEAK with specified id and parameters.
	 * @param id The unique id of this TWEAK.
	 * @param xOffset The offset in X axis.
	 * @param yOffset The offset in Y axis.
	 * @param rotation The rotation offset..
	 */
	public TWEAK(
	         String id,
	         double xOffset,
	         double yOffset,
	         double rotation) {
	         super(id);
	           this.xOffset = xOffset;
	           this.yOffset = yOffset;
	           this.rotation = rotation;
	         }

	/** Set the offset in X axis.
	 * @param xOffset The offset in X axis.
	 */
	public void setXOffset(double xOffset) { this.xOffset = xOffset; }

	/** Get the offset in X axis.
	 * @return The offset in X axis.
	 */
	public double getXOffset() { return xOffset; }

	/** Set the offset in Y axis.
	 * @param yOffset The offset in Y axis.
	 */
	public void setYOffset(double yOffset) { this.yOffset = yOffset; }

	/** Get the offset in Y axis.
	 * @return The offset in Y axis.
	 */
	public double getYOffset() { return yOffset; }

	/** Set the rotation offset..
	 * @param rotation The rotation offset..
	 */
	public void setRotation(double rotation) { this.rotation = rotation; }

	/** Get the rotation offset..
	 * @return The rotation offset..
	 */
	public double getRotation() { return rotation; }

	// Hand generated code.

} // class def. [TWEAK].

