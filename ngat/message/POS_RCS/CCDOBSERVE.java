package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDOBSERVE.<br>
 *  Supplies the information required to slew the telescope to a source<br>
 *  configure the instrument and take an exposure.<br>
 *  Module code: 690400<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>filter1 - the filter type in the lower wheel</dd>
 * <dd>&nbsp;values: a valid lower filter type</dd>
 * <dd>filter2 - the filter type in the upper wheel</dd>
 * <dd>&nbsp;values: a valid upper filter type</dd>
 * <dd>exposure - exposure time in secs.</dd>
 * <dd>&nbsp;values: 0.000 to 7200.000</dd>
 * <dd>bin - CCD binning factor.</dd>
 * <dd>&nbsp;values: 1, 2, 4 only</dd>
 * <dd>mode - whether the exposure is part of a mosaic.</dd>
 * <dd>&nbsp;values: { SINGLE | MOSAIC | MOSAIC_SETUP}</dd>
 * <dd>xOffset - offset in (rotated) X axis (arcsecs).</dd>
 * <dd>&nbsp;values: small +/- offset</dd>
 * <dd>yOffset - offset in (rotated) Y axis (arcsecs).</dd>
 * <dd>&nbsp;values: small +/- offset</dd>
 * <dd>rotation - rotator sky position angle.</dd>
 * <dd>&nbsp;values: 0.0 - 360.0</dd>
 * <dd>filterClass - the variety of filter</dd>
 * <dd>&nbsp;values: {RED | GREEN | BLUE | UNKNOWN}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>frameNumber - reference number of the image frame.</dd>
 * </dl>
 * <br>
 */
public class CCDOBSERVE extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates exposure is first part of a mosaic.*/
	public static final int MOSAIC_SETUP = 690401;

	/** Constant: Indicates exposure is (not first) part of a mosaic.*/
	public static final int MOSAIC = 690402;

	/** Constant: Indicates exposure is a single (one-off).*/
	public static final int SINGLE = 690403;

	/** Constant: Indicates just a slew and no exposure.*/
	public static final int SLEW_ONLY = 690404;

	/** Constant: Indicates a lsew with rotation.*/
	public static final int SLEW_PLUS_ROT = 690405;

	/** Constant: Indicates an error in the CCD.*/
	public static final int CCD_FAULT = 690401;

	/** Constant: Indicates an error with the telescope control system or a mechanism.*/
	public static final int TELESCOPE_FAULT = 690402;

	/** Constant: Indicates an unspecified fault with the enclosure.*/
	public static final int ENCLOSURE_FAULT = 690403;

	/** Constant: Indicates that the enclosure has been closed due to extreme weather.*/
	public static final int BAD_WEATHER = 690404;

	/** Constant: Indicates that the coordinates were dodgy.*/
	public static final int BAD_COORDS = 690405;

	/** Constant: Indicates that the CCD binning was not valid.*/
	public static final int BAD_BINNING = 690406;

	/** Constant: Indicates that the specifed filter combination was not available.*/
	public static final int BAD_FILTER = 690407;

	/** Constant: Indicates that the target has/will set before the exposure is complete.*/
	public static final int OBJECT_SET = 690408;

	/** Constant: Indicates the filter is of the RED variety.*/
	public static final int RED_FILTER = 690410;

	/** Constant: Indicates the filter is of the GREEN variety.*/
	public static final int GREEN_FILTER = 690411;

	/** Constant: Indicates the filter is of the BLUE variety*/
	public static final int BLUE_FILTER = 690412;

	/** Constant: Indicates the filter variety is UNKNOWN*/
	public static final int UNKNOWN_FILTER = 690413;

	// Variables.

	/** The the filter type in the lower wheel*/
	protected String filter1;

	/** The the filter type in the upper wheel*/
	protected String filter2;

	/** The exposure time in secs.*/
	protected double exposure;

	/** The CCD binning factor.*/
	protected int bin;

	/** The whether the exposure is part of a mosaic.*/
	protected int mode;

	/** The offset in (rotated) X axis (arcsecs).*/
	protected double xOffset;

	/** The offset in (rotated) Y axis (arcsecs).*/
	protected double yOffset;

	/** The rotator sky position angle.*/
	protected double rotation;

	/** The the variety of filter*/
	protected int filterClass;

	/** Create a CCDOBSERVE with specified id.
	 * @param id The unique id of this CCDOBSERVE.
	 */
	public CCDOBSERVE(String id) { super(id); }

	/** Create a CCDOBSERVE with specified id and parameters.
	 * @param id The unique id of this CCDOBSERVE.
	 * @param filter1 The the filter type in the lower wheel
	 * @param filter2 The the filter type in the upper wheel
	 * @param exposure The exposure time in secs.
	 * @param bin The CCD binning factor.
	 * @param mode The whether the exposure is part of a mosaic.
	 * @param xOffset The offset in (rotated) X axis (arcsecs).
	 * @param yOffset The offset in (rotated) Y axis (arcsecs).
	 * @param rotation The rotator sky position angle.
	 * @param filterClass The the variety of filter
	 */
	public CCDOBSERVE(
	         String id,
	         String filter1,
	         String filter2,
	         double exposure,
	         int bin,
	         int mode,
	         double xOffset,
	         double yOffset,
	         double rotation,
	         int filterClass) {
	         super(id);
	           this.filter1 = filter1;
	           this.filter2 = filter2;
	           this.exposure = exposure;
	           this.bin = bin;
	           this.mode = mode;
	           this.xOffset = xOffset;
	           this.yOffset = yOffset;
	           this.rotation = rotation;
	           this.filterClass = filterClass;
	         }

	/** Set the the filter type in the lower wheel
	 * @param filter1 The the filter type in the lower wheel
	 */
	public void setFilter1(String filter1) { this.filter1 = filter1; }

	/** Get the the filter type in the lower wheel
	 * @return The the filter type in the lower wheel
	 */
	public String getFilter1() { return filter1; }

	/** Set the the filter type in the upper wheel
	 * @param filter2 The the filter type in the upper wheel
	 */
	public void setFilter2(String filter2) { this.filter2 = filter2; }

	/** Get the the filter type in the upper wheel
	 * @return The the filter type in the upper wheel
	 */
	public String getFilter2() { return filter2; }

	/** Set the exposure time in secs.
	 * @param exposure The exposure time in secs.
	 */
	public void setExposure(double exposure) { this.exposure = exposure; }

	/** Get the exposure time in secs.
	 * @return The exposure time in secs.
	 */
	public double getExposure() { return exposure; }

	/** Set the CCD binning factor.
	 * @param bin The CCD binning factor.
	 */
	public void setBin(int bin) { this.bin = bin; }

	/** Get the CCD binning factor.
	 * @return The CCD binning factor.
	 */
	public int getBin() { return bin; }

	/** Set the whether the exposure is part of a mosaic.
	 * @param mode The whether the exposure is part of a mosaic.
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the whether the exposure is part of a mosaic.
	 * @return The whether the exposure is part of a mosaic.
	 */
	public int getMode() { return mode; }

	/** Set the offset in (rotated) X axis (arcsecs).
	 * @param xOffset The offset in (rotated) X axis (arcsecs).
	 */
	public void setXOffset(double xOffset) { this.xOffset = xOffset; }

	/** Get the offset in (rotated) X axis (arcsecs).
	 * @return The offset in (rotated) X axis (arcsecs).
	 */
	public double getXOffset() { return xOffset; }

	/** Set the offset in (rotated) Y axis (arcsecs).
	 * @param yOffset The offset in (rotated) Y axis (arcsecs).
	 */
	public void setYOffset(double yOffset) { this.yOffset = yOffset; }

	/** Get the offset in (rotated) Y axis (arcsecs).
	 * @return The offset in (rotated) Y axis (arcsecs).
	 */
	public double getYOffset() { return yOffset; }

	/** Set the rotator sky position angle.
	 * @param rotation The rotator sky position angle.
	 */
	public void setRotation(double rotation) { this.rotation = rotation; }

	/** Get the rotator sky position angle.
	 * @return The rotator sky position angle.
	 */
	public double getRotation() { return rotation; }

	/** Set the the variety of filter
	 * @param filterClass The the variety of filter
	 */
	public void setFilterClass(int filterClass) { this.filterClass = filterClass; }

	/** Get the the variety of filter
	 * @return The the variety of filter
	 */
	public int getFilterClass() { return filterClass; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", filter1="+filter1+
			", filter2="+filter2+
			", exposure="+exposure+
			", bin="+bin+
			", mode="+mode+
			", xOffset="+xOffset+
			", yOffset="+yOffset+
			", rotation="+rotation+
			", filterClass="+filterClass+"]";
	}
	// Hand generated code.

} // class def. [CCDOBSERVE].

