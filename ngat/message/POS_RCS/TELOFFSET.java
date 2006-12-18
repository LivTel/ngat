package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TELOFFSET.<br>
 *  Supplies the information required to offset the telescope from its current position.<br>
 *  The rotator is not moved by a rotation parameter it merely<br>
 *  Module code: 692100<br>
 * <br>
 *  Example: TELOFFSET TANGENT 24.0 24.0 35.0<br>
 * <br>
 *  Example: TELOFFSET SKY 50.0 45.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>raOffset - offset in RA.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>decOffset - offset in declination.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>xOffset - offset in X direction of rotated tangent plane.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>yOffset - offset in Y direction of rotated tangent plane.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>rotation - tangent plane rotation (from Pole cwise).</dd>
 * <dd>&nbsp;values: 0 to 2*PI</dd>
 * <dd>mode - indicates whether sky or tangent plane coordinates.</dd>
 * <dd>&nbsp;values: { TANGENT | SKY }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELOFFSET extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates tangent plane offset in (X, Y) rotated by r.*/
	public static final int TANGENT = 692101;

	/** Constant: Indicates sky offset in (RA, Dec).*/
	public static final int SKY = 692102;

	// Variables.

	/** The offset in RA.*/
	protected double raOffset;

	/** The offset in declination.*/
	protected double decOffset;

	/** The offset in X direction of rotated tangent plane.*/
	protected double xOffset;

	/** The offset in Y direction of rotated tangent plane.*/
	protected double yOffset;

	/** The tangent plane rotation (from Pole cwise).*/
	protected double rotation;

	/** The indicates whether sky or tangent plane coordinates.*/
	protected int mode;

	/** Create a TELOFFSET with specified id.
	 * @param id The unique id of this TELOFFSET.
	 */
	public TELOFFSET(String id) { super(id); }

	/** Create a TELOFFSET with specified id and parameters.
	 * @param id The unique id of this TELOFFSET.
	 * @param raOffset The offset in RA.
	 * @param decOffset The offset in declination.
	 * @param xOffset The offset in X direction of rotated tangent plane.
	 * @param yOffset The offset in Y direction of rotated tangent plane.
	 * @param rotation The tangent plane rotation (from Pole cwise).
	 * @param mode The indicates whether sky or tangent plane coordinates.
	 */
	public TELOFFSET(
	         String id,
	         double raOffset,
	         double decOffset,
	         double xOffset,
	         double yOffset,
	         double rotation,
	         int mode) {
	         super(id);
	           this.raOffset = raOffset;
	           this.decOffset = decOffset;
	           this.xOffset = xOffset;
	           this.yOffset = yOffset;
	           this.rotation = rotation;
	           this.mode = mode;
	         }

	/** Set the offset in RA.
	 * @param raOffset The offset in RA.
	 */
	public void setRaOffset(double raOffset) { this.raOffset = raOffset; }

	/** Get the offset in RA.
	 * @return The offset in RA.
	 */
	public double getRaOffset() { return raOffset; }

	/** Set the offset in declination.
	 * @param decOffset The offset in declination.
	 */
	public void setDecOffset(double decOffset) { this.decOffset = decOffset; }

	/** Get the offset in declination.
	 * @return The offset in declination.
	 */
	public double getDecOffset() { return decOffset; }

	/** Set the offset in X direction of rotated tangent plane.
	 * @param xOffset The offset in X direction of rotated tangent plane.
	 */
	public void setXOffset(double xOffset) { this.xOffset = xOffset; }

	/** Get the offset in X direction of rotated tangent plane.
	 * @return The offset in X direction of rotated tangent plane.
	 */
	public double getXOffset() { return xOffset; }

	/** Set the offset in Y direction of rotated tangent plane.
	 * @param yOffset The offset in Y direction of rotated tangent plane.
	 */
	public void setYOffset(double yOffset) { this.yOffset = yOffset; }

	/** Get the offset in Y direction of rotated tangent plane.
	 * @return The offset in Y direction of rotated tangent plane.
	 */
	public double getYOffset() { return yOffset; }

	/** Set the tangent plane rotation (from Pole cwise).
	 * @param rotation The tangent plane rotation (from Pole cwise).
	 */
	public void setRotation(double rotation) { this.rotation = rotation; }

	/** Get the tangent plane rotation (from Pole cwise).
	 * @return The tangent plane rotation (from Pole cwise).
	 */
	public double getRotation() { return rotation; }

	/** Set the indicates whether sky or tangent plane coordinates.
	 * @param mode The indicates whether sky or tangent plane coordinates.
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the indicates whether sky or tangent plane coordinates.
	 * @return The indicates whether sky or tangent plane coordinates.
	 */
	public int getMode() { return mode; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", raOffset="+raOffset+
			", decOffset="+decOffset+
			", xOffset="+xOffset+
			", yOffset="+yOffset+
			", rotation="+rotation+
			", mode="+mode+"]";
	}
	// Hand generated code.

} // class def. [TELOFFSET].

