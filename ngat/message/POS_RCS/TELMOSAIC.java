package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TELMOSAIC.<br>
 *  Supplies the information required to perform a mosaic <br>
 *  exposure sequence.<br>
 *  Module code: 692500<br>
 * <br>
 *  Example: TELMOSAIC 35.0 CB 2 3 3 240.0 240.0 10.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>rotation - tangent plane rotation.</dd>
 * <dd>&nbsp;values: 0 to 2*PI</dd>
 * <dd>config - camera configuration</dd>
 * <dd>&nbsp;values: a valid config for the CCD camera</dd>
 * <dd>xCount - number of cells in rotated X direction.</dd>
 * <dd>&nbsp;values: 1 - 5</dd>
 * <dd>yCount - number of cells in rotated Y direction.</dd>
 * <dd>&nbsp;values: 1 - 5</dd>
 * <dd>xOffset - offset in rotated X direction.</dd>
 * <dd>&nbsp;values: 0 - 3600 arcsec</dd>
 * <dd>yOffset - offset in rotated Y direction.</dd>
 * <dd>&nbsp;values: 0 - 3600 arcsec</dd>
 * <dd>exposure - exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELMOSAIC extends POS_TO_RCS {

	// Variables.

	/** The tangent plane rotation.*/
	protected double rotation;

	/** The camera configuration*/
	protected InstrumentConfig config;

	/** The number of cells in rotated X direction.*/
	protected int xCount;

	/** The number of cells in rotated Y direction.*/
	protected int yCount;

	/** The offset in rotated X direction.*/
	protected double xOffset;

	/** The offset in rotated Y direction.*/
	protected double yOffset;

	/** The exposure (millis).*/
	protected int exposure;

	/** Create a TELMOSAIC with specified id.
	 * @param id The unique id of this TELMOSAIC.
	 */
	public TELMOSAIC(String id) { super(id); }

	/** Create a TELMOSAIC with specified id and parameters.
	 * @param id The unique id of this TELMOSAIC.
	 * @param rotation The tangent plane rotation.
	 * @param config The camera configuration
	 * @param xCount The number of cells in rotated X direction.
	 * @param yCount The number of cells in rotated Y direction.
	 * @param xOffset The offset in rotated X direction.
	 * @param yOffset The offset in rotated Y direction.
	 * @param exposure The exposure (millis).
	 */
	public TELMOSAIC(
	         String id,
	         double rotation,
	         InstrumentConfig config,
	         int xCount,
	         int yCount,
	         double xOffset,
	         double yOffset,
	         int exposure) {
	         super(id);
	           this.rotation = rotation;
	           this.config = config;
	           this.xCount = xCount;
	           this.yCount = yCount;
	           this.xOffset = xOffset;
	           this.yOffset = yOffset;
	           this.exposure = exposure;
	         }

	/** Set the tangent plane rotation.
	 * @param rotation The tangent plane rotation.
	 */
	public void setRotation(double rotation) { this.rotation = rotation; }

	/** Get the tangent plane rotation.
	 * @return The tangent plane rotation.
	 */
	public double getRotation() { return rotation; }

	/** Set the camera configuration
	 * @param config The camera configuration
	 */
	public void setConfig(InstrumentConfig config) { this.config = config; }

	/** Get the camera configuration
	 * @return The camera configuration
	 */
	public InstrumentConfig getConfig() { return config; }

	/** Set the number of cells in rotated X direction.
	 * @param xCount The number of cells in rotated X direction.
	 */
	public void setXCount(int xCount) { this.xCount = xCount; }

	/** Get the number of cells in rotated X direction.
	 * @return The number of cells in rotated X direction.
	 */
	public int getXCount() { return xCount; }

	/** Set the number of cells in rotated Y direction.
	 * @param yCount The number of cells in rotated Y direction.
	 */
	public void setYCount(int yCount) { this.yCount = yCount; }

	/** Get the number of cells in rotated Y direction.
	 * @return The number of cells in rotated Y direction.
	 */
	public int getYCount() { return yCount; }

	/** Set the offset in rotated X direction.
	 * @param xOffset The offset in rotated X direction.
	 */
	public void setXOffset(double xOffset) { this.xOffset = xOffset; }

	/** Get the offset in rotated X direction.
	 * @return The offset in rotated X direction.
	 */
	public double getXOffset() { return xOffset; }

	/** Set the offset in rotated Y direction.
	 * @param yOffset The offset in rotated Y direction.
	 */
	public void setYOffset(double yOffset) { this.yOffset = yOffset; }

	/** Get the offset in rotated Y direction.
	 * @return The offset in rotated Y direction.
	 */
	public double getYOffset() { return yOffset; }

	/** Set the exposure (millis).
	 * @param exposure The exposure (millis).
	 */
	public void setExposure(int exposure) { this.exposure = exposure; }

	/** Get the exposure (millis).
	 * @return The exposure (millis).
	 */
	public int getExposure() { return exposure; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", rotation="+rotation+
			", config="+config+
			", xCount="+xCount+
			", yCount="+yCount+
			", xOffset="+xOffset+
			", yOffset="+yOffset+
			", exposure="+exposure+"]";
	}
	// Hand generated code.

} // class def. [TELMOSAIC].

