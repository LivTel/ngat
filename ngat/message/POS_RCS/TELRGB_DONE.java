package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TELRGB_DONE.<br>
 *  Supplies the information required to perform a series of 3<br>
 *  exposures to form an RGB image from FITS exposures.<br>
 *  Module code: 692400<br>
 * <br>
 *  Example: TELRGB CRCVCB 10.0 20.0 40.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>redFilter - redfilter.</dd>
 * <dd>&nbsp;values: a valid filter name</dd>
 * <dd>greenFilter - greenfilter.</dd>
 * <dd>&nbsp;values: a valid filter name</dd>
 * <dd>blueFilter - blue filter.</dd>
 * <dd>&nbsp;values: a valid filter name</dd>
 * <dd>redExposure - red exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dd>greenExposure - green exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dd>blueExposure - blue exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dd>bin - binning.</dd>
 * <dd>&nbsp;values: a valid binning factor (1, 2, 4, 8)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELRGB_DONE extends POS_TO_RCS_DONE {

	/** Create a TELRGB_DONE with specified id.
	 * @param id The unique id of this TELRGB_DONE.
	 */
	public TELRGB_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELRGB_DONE].

