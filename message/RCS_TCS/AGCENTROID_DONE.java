package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: AGCENTROID_DONE.<br>
 * Instructs the TCS to centroid on the current guide source.<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>xPixel - X -axis ixel position of guide star on CCD.</dd>
 * <dd>yPixel - Y -axis ixel position of guide star on CCD.</dd>
 * <dd>fwhm - FWHM for centroid source.</dd>
 * <dd>peak - Peak pixel value of source.</dd>
 * </dl>
 * <br>
 */
public class AGCENTROID_DONE extends RCS_TO_TCS_DONE {

	// Variables.

	/** The X -axis ixel position of guide star on CCD.*/
	protected double xPixel;

	/** The Y -axis ixel position of guide star on CCD.*/
	protected double yPixel;

	/** The FWHM for centroid source.*/
	protected double fwhm;

	/** The Peak pixel value of source.*/
	protected int peak;

	/** Create a AGCENTROID_DONE with specified id.
	 * @param id The unique id of this AGCENTROID_DONE.
	 */
	public AGCENTROID_DONE (String id) { super(id); }

	/** Set the X -axis ixel position of guide star on CCD.
	 * @param xPixel The X -axis ixel position of guide star on CCD..
	 */
	public void setXPixel(double xPixel) { this.xPixel = xPixel; }

	/** Get the X -axis ixel position of guide star on CCD.
	 * @return The X -axis ixel position of guide star on CCD.
	 */
	public double getXPixel() { return xPixel; }

	/** Set the Y -axis ixel position of guide star on CCD.
	 * @param yPixel The Y -axis ixel position of guide star on CCD..
	 */
	public void setYPixel(double yPixel) { this.yPixel = yPixel; }

	/** Get the Y -axis ixel position of guide star on CCD.
	 * @return The Y -axis ixel position of guide star on CCD.
	 */
	public double getYPixel() { return yPixel; }

	/** Set the FWHM for centroid source.
	 * @param fwhm The FWHM for centroid source..
	 */
	public void setFwhm(double fwhm) { this.fwhm = fwhm; }

	/** Get the FWHM for centroid source.
	 * @return The FWHM for centroid source.
	 */
	public double getFwhm() { return fwhm; }

	/** Set the Peak pixel value of source.
	 * @param peak The Peak pixel value of source..
	 */
	public void setPeak(int peak) { this.peak = peak; }

	/** Get the Peak pixel value of source.
	 * @return The Peak pixel value of source.
	 */
	public int getPeak() { return peak; }

	// Hand generated code.

} // class def. [AGCENTROID_DONE].

