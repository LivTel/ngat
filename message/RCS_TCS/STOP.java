package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: STOP.<br>
 * Instructs TCS to stop the telescope or specified mechanism.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - mechanism to stop.</dd>
 * <dd>&nbsp;values: { ALL | AGFOCUS | AGPROBE | ALTITUDE | AZIMUTH | ENCLOSURE | FOCUS | ROTATOR }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class STOP extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates all mechanisms should be stopped.*/
	public static final int ALL = 0;

	/** Constant: Indicates autoguider focus tracking should be stopped.*/
	public static final int AGFOCUS = 1;

	/** Constant: Indicates autoguider probe motion should be stopped.*/
	public static final int AGPROBE = 2;

	/** Constant: Indicates altitude drive should be stopped.*/
	public static final int ALTITUDE = 3;

	/** Constant: Indicates azimuth drive should be stopped.*/
	public static final int AZIMUTH = 4;

	/** Constant: Indicates enclosure motion should be stopped.*/
	public static final int ENCLOSURE = 5;

	/** Constant: Indicates focus tracking should be stopped.*/
	public static final int FOCUS = 6;

	/** Constant: Indicates rotator motor should be stopped.*/
	public static final int ROTATOR = 7;

	// Variables.

	/** The mechanism to stop.*/
	protected int mechanism;

	/** Create a STOP with specified id.
	 * @param id The unique id of this STOP.
	 */
	public STOP(String id) { super(id); }

	/** Create a STOP with specified id and parameters.
	 * @param id The unique id of this STOP.
	 * @param mechanism The mechanism to stop.
	 */
	public STOP(
	         String id,
	         int mechanism) {
	         super(id);
	           this.mechanism = mechanism;
	         }

	/** Set the mechanism to stop.
	 * @param mechanism The mechanism to stop.
	 */
	public void setMechanism(int mechanism) { this.mechanism = mechanism; }

	/** Get the mechanism to stop.
	 * @return The mechanism to stop.
	 */
	public int getMechanism() { return mechanism; }

	// Hand generated code.

} // class def. [STOP].

