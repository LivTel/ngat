package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: OFFBY.<br>
 * Instruct the TCS to make a position offset.<br>
 * 1).When mode is set to ARC offsets are made in the tangent plane.<br>
 * 2).When mode is set to TIME offsets are made with constant<br>
 *    displacements in ra and dec whatever the altitude.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - operational mode.</dd>
 * <dd>&nbsp;values: { ARC | TIME }</dd>
 * <dd>offsetRA - offset in RA.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dd>offsetDec - offset in dec.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OFFBY extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates offsets are applied in the tangent plane.*/
	public static final int ARC = 0;

	/** Constant: Indicates offsets are applied by constant amount.*/
	public static final int TIME = 1;

	// Variables.

	/** The operational mode.*/
	protected int mode;

	/** The offset in RA.*/
	protected double offsetRA;

	/** The offset in dec.*/
	protected double offsetDec;

	/** Create a OFFBY with specified id.
	 * @param id The unique id of this OFFBY.
	 */
	public OFFBY(String id) { super(id); }

	/** Create a OFFBY with specified id and parameters.
	 * @param id The unique id of this OFFBY.
	 * @param mode The operational mode.
	 * @param offsetRA The offset in RA.
	 * @param offsetDec The offset in dec.
	 */
	public OFFBY(
	         String id,
	         int mode,
	         double offsetRA,
	         double offsetDec) {
	         super(id);
	           this.mode = mode;
	           this.offsetRA = offsetRA;
	           this.offsetDec = offsetDec;
	         }

	/** Set the operational mode.
	 * @param mode The operational mode.
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the operational mode.
	 * @return The operational mode.
	 */
	public int getMode() { return mode; }

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

	// Hand generated code.

} // class def. [OFFBY].

