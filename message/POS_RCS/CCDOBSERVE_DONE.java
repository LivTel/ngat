package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDOBSERVE_DONE.<br>
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
public class CCDOBSERVE_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The reference number of the image frame.*/
	protected long frameNumber;

	/** Create a CCDOBSERVE_DONE with specified id.
	 * @param id The unique id of this CCDOBSERVE_DONE.
	 */
	public CCDOBSERVE_DONE (String id) { super(id); }

	/** Set the reference number of the image frame.
	 * @param frameNumber The reference number of the image frame..
	 */
	public void setFrameNumber(long frameNumber) { this.frameNumber = frameNumber; }

	/** Get the reference number of the image frame.
	 * @return The reference number of the image frame.
	 */
	public long getFrameNumber() { return frameNumber; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", frameNumber="+frameNumber+"]";
	}
	// Hand generated code.

} // class def. [CCDOBSERVE_DONE].

