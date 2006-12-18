package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: CALIBRATE.<br>
 * Instructs the TCS to carry out its pointing calibration.<br>
 * 1). DEFAULT mode causes the TCS to revert to the default pointing solution.<br>
 * 2). NEW mode initiates an automatic sequence which does pointing measurements on 7 stars from a pointing grid.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - calibration mode.</dd>
 * <dd>&nbsp;values: { DEFAULT | NEW }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>rmsError - calibration sky sigma.</dd>
 * </dl>
 * <br>
 */
public class CALIBRATE extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates the default calibration is to be used.*/
	public static final int DEFAULT = 0;

	/** Constant: Indicates a new set of pointing measures is to be taken.*/
	public static final int NEW = 1;

	/** Constant: Indicates previous pointing calibration is used.*/
	public static final int LAST = 2;

	// Variables.

	/** The calibration mode.*/
	protected int mode;

	/** Create a CALIBRATE with specified id.
	 * @param id The unique id of this CALIBRATE.
	 */
	public CALIBRATE(String id) { super(id); }

	/** Create a CALIBRATE with specified id and parameters.
	 * @param id The unique id of this CALIBRATE.
	 * @param mode The calibration mode.
	 */
	public CALIBRATE(
	         String id,
	         int mode) {
	         super(id);
	           this.mode = mode;
	         }

	/** Set the calibration mode.
	 * @param mode The calibration mode.
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the calibration mode.
	 * @return The calibration mode.
	 */
	public int getMode() { return mode; }

	// Hand generated code.

} // class def. [CALIBRATE].

