package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: TRACK.<br>
 * Instructs TCS to start or stop various mechanisms.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - keyword to indicate mechanism to set state of.</dd>
 * <dd>&nbsp;values: {ALL | AGFOCUS | ALTITUDE | AZIMUTH | FOCUS | ROTATOR}</dd>
 * <dd>state - keyword to indicate state to put specified mechanism into.</dd>
 * <dd>&nbsp;values: {ON | OFF}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TRACK extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates all mechanisms.*/
	public static final int ALL = 0;

	/** Constant: Indicates autoguider focus.*/
	public static final int AGFOCUS = 1;

	/** Constant: Indicates altitude drive.*/
	public static final int ALTITUDE = 2;

	/** Constant: Indicates azimuth drive.*/
	public static final int AZIMUTH = 3;

	/** Constant: Indicates focus drive.*/
	public static final int FOCUS = 4;

	/** Constant: Indicates cassegrain rotator drive.*/
	public static final int ROTATOR = 5;

	/** Constant: Indicates set mechanism on.*/
	public static final int ON = 6;

	/** Constant: Indicates set mechanism off.*/
	public static final int OFF = 7;

	// Variables.

	/** The keyword to indicate mechanism to set state of.*/
	protected int mechanism;

	/** The keyword to indicate state to put specified mechanism into.*/
	protected int state;

	/** Create a TRACK with specified id.
	 * @param id The unique id of this TRACK.
	 */
	public TRACK(String id) { super(id); }

	/** Create a TRACK with specified id and parameters.
	 * @param id The unique id of this TRACK.
	 * @param mechanism The keyword to indicate mechanism to set state of.
	 * @param state The keyword to indicate state to put specified mechanism into.
	 */
	public TRACK(
	         String id,
	         int mechanism,
	         int state) {
	         super(id);
	           this.mechanism = mechanism;
	           this.state = state;
	         }

	/** Set the keyword to indicate mechanism to set state of.
	 * @param mechanism The keyword to indicate mechanism to set state of.
	 */
	public void setMechanism(int mechanism) { this.mechanism = mechanism; }

	/** Get the keyword to indicate mechanism to set state of.
	 * @return The keyword to indicate mechanism to set state of.
	 */
	public int getMechanism() { return mechanism; }

	/** Set the keyword to indicate state to put specified mechanism into.
	 * @param state The keyword to indicate state to put specified mechanism into.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the keyword to indicate state to put specified mechanism into.
	 * @return The keyword to indicate state to put specified mechanism into.
	 */
	public int getState() { return state; }

	// Hand generated code.

} // class def. [TRACK].

