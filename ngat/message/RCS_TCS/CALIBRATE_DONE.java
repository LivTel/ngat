package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: CALIBRATE_DONE.<br>
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
public class CALIBRATE_DONE extends RCS_TO_TCS_DONE {

	// Variables.

	/** The calibration sky sigma.*/
	protected double rmsError;

	/** Create a CALIBRATE_DONE with specified id.
	 * @param id The unique id of this CALIBRATE_DONE.
	 */
	public CALIBRATE_DONE (String id) { super(id); }

	/** Set the calibration sky sigma.
	 * @param rmsError The calibration sky sigma..
	 */
	public void setRmsError(double rmsError) { this.rmsError = rmsError; }

	/** Get the calibration sky sigma.
	 * @return The calibration sky sigma.
	 */
	public double getRmsError() { return rmsError; }

	// Hand generated code.

} // class def. [CALIBRATE_DONE].

