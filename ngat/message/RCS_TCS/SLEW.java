package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND: SLEW.<br>
 * Instruct the TCS to slew the telescope to the specified position and begin tracking.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>source - source object to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.Phase2.Source or subclass</dd>
 * <dd>offsetRA - offset in RA.</dd>
 * <dd>&nbsp;values: +/- 60 arcsec</dd>
 * <dd>offsetDec - offset in dec.</dd>
 * <dd>&nbsp;values: +/- 60 arcsec</dd>
 * <dd>nstrack - whether non-sidereal tracking is wanted</dd>
 * <dd>&nbsp;values: t/f</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SLEW extends RCS_TO_TCS {

	// Variables.

	/** The source object to slew to.*/
	protected Source source;

	/** The offset in RA.*/
	protected double offsetRA;

	/** The offset in dec.*/
	protected double offsetDec;

	/** The whether non-sidereal tracking is wanted*/
	protected boolean nstrack;

	/** Create a SLEW with specified id.
	 * @param id The unique id of this SLEW.
	 */
	public SLEW(String id) { super(id); }

	/** Create a SLEW with specified id and parameters.
	 * @param id The unique id of this SLEW.
	 * @param source The source object to slew to.
	 * @param offsetRA The offset in RA.
	 * @param offsetDec The offset in dec.
	 * @param nstrack The whether non-sidereal tracking is wanted
	 */
	public SLEW(
	         String id,
	         Source source,
	         double offsetRA,
	         double offsetDec,
	         boolean nstrack) {
	         super(id);
	           this.source = source;
	           this.offsetRA = offsetRA;
	           this.offsetDec = offsetDec;
	           this.nstrack = nstrack;
	         }

	/** Set the source object to slew to.
	 * @param source The source object to slew to.
	 */
	public void setSource(Source source) { this.source = source; }

	/** Get the source object to slew to.
	 * @return The source object to slew to.
	 */
	public Source getSource() { return source; }

	/** Set the offset in RA.
	 * @param offsetRA The offset in RA.
	 */
	public void setOffsetRA(double offsetRA) { this.offsetRA = offsetRA; }

	/** Get the offset in RA.
	 * @return The offset in RA.
	 */
	public double getOffsetRA() { return offsetRA; }

	/** Set the offset in dec.
	 * @param offsetDec The offset in dec.
	 */
	public void setOffsetDec(double offsetDec) { this.offsetDec = offsetDec; }

	/** Get the offset in dec.
	 * @return The offset in dec.
	 */
	public double getOffsetDec() { return offsetDec; }

	/** Set the whether non-sidereal tracking is wanted
	 * @param nstrack The whether non-sidereal tracking is wanted
	 */
	public void setNstrack(boolean nstrack) { this.nstrack = nstrack; }

	/** Get the whether non-sidereal tracking is wanted
	 * @return The whether non-sidereal tracking is wanted
	 */
	public boolean getNstrack() { return nstrack; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", source="+source+
			", offsetRA="+offsetRA+
			", offsetDec="+offsetDec+
			", nstrack="+nstrack+"]";
	}
	// Hand generated code.

} // class def. [SLEW].

