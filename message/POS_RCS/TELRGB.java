package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TELRGB.<br>
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
public class TELRGB extends POS_TO_RCS {

	// Variables.

	/** The redfilter.*/
	protected String redFilter;

	/** The greenfilter.*/
	protected String greenFilter;

	/** The blue filter.*/
	protected String blueFilter;

	/** The red exposure (millis).*/
	protected int redExposure;

	/** The green exposure (millis).*/
	protected int greenExposure;

	/** The blue exposure (millis).*/
	protected int blueExposure;

	/** The binning.*/
	protected int bin;

	/** Create a TELRGB with specified id.
	 * @param id The unique id of this TELRGB.
	 */
	public TELRGB(String id) { super(id); }

	/** Create a TELRGB with specified id and parameters.
	 * @param id The unique id of this TELRGB.
	 * @param redFilter The redfilter.
	 * @param greenFilter The greenfilter.
	 * @param blueFilter The blue filter.
	 * @param redExposure The red exposure (millis).
	 * @param greenExposure The green exposure (millis).
	 * @param blueExposure The blue exposure (millis).
	 * @param bin The binning.
	 */
	public TELRGB(
	         String id,
	         String redFilter,
	         String greenFilter,
	         String blueFilter,
	         int redExposure,
	         int greenExposure,
	         int blueExposure,
	         int bin) {
	         super(id);
	           this.redFilter = redFilter;
	           this.greenFilter = greenFilter;
	           this.blueFilter = blueFilter;
	           this.redExposure = redExposure;
	           this.greenExposure = greenExposure;
	           this.blueExposure = blueExposure;
	           this.bin = bin;
	         }

	/** Set the redfilter.
	 * @param redFilter The redfilter.
	 */
	public void setRedFilter(String redFilter) { this.redFilter = redFilter; }

	/** Get the redfilter.
	 * @return The redfilter.
	 */
	public String getRedFilter() { return redFilter; }

	/** Set the greenfilter.
	 * @param greenFilter The greenfilter.
	 */
	public void setGreenFilter(String greenFilter) { this.greenFilter = greenFilter; }

	/** Get the greenfilter.
	 * @return The greenfilter.
	 */
	public String getGreenFilter() { return greenFilter; }

	/** Set the blue filter.
	 * @param blueFilter The blue filter.
	 */
	public void setBlueFilter(String blueFilter) { this.blueFilter = blueFilter; }

	/** Get the blue filter.
	 * @return The blue filter.
	 */
	public String getBlueFilter() { return blueFilter; }

	/** Set the red exposure (millis).
	 * @param redExposure The red exposure (millis).
	 */
	public void setRedExposure(int redExposure) { this.redExposure = redExposure; }

	/** Get the red exposure (millis).
	 * @return The red exposure (millis).
	 */
	public int getRedExposure() { return redExposure; }

	/** Set the green exposure (millis).
	 * @param greenExposure The green exposure (millis).
	 */
	public void setGreenExposure(int greenExposure) { this.greenExposure = greenExposure; }

	/** Get the green exposure (millis).
	 * @return The green exposure (millis).
	 */
	public int getGreenExposure() { return greenExposure; }

	/** Set the blue exposure (millis).
	 * @param blueExposure The blue exposure (millis).
	 */
	public void setBlueExposure(int blueExposure) { this.blueExposure = blueExposure; }

	/** Get the blue exposure (millis).
	 * @return The blue exposure (millis).
	 */
	public int getBlueExposure() { return blueExposure; }

	/** Set the binning.
	 * @param bin The binning.
	 */
	public void setBin(int bin) { this.bin = bin; }

	/** Get the binning.
	 * @return The binning.
	 */
	public int getBin() { return bin; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", redFilter="+redFilter+
			", greenFilter="+greenFilter+
			", blueFilter="+blueFilter+
			", redExposure="+redExposure+
			", greenExposure="+greenExposure+
			", blueExposure="+blueExposure+
			", bin="+bin+"]";
	}
	// Hand generated code.

} // class def. [TELRGB].

