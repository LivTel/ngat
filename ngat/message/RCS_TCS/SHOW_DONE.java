package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: SHOW_DONE.<br>
 * Instructs TCS to deliver status information.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>key - keyword to indicate which set of data to deliver.</dd>
 * <dd>&nbsp;values: { ALL | ASTROMETRY | AUTOGUIDER | CALIBRATE | FOCAL_STATION | LIMITS | MECHANISMS | METEOROLOGY | SOURCE | STATE | TIME | VERSION }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - current TCS and telescope status information.</dd>
 * </dl>
 * <br>
 */
public class SHOW_DONE extends RCS_TO_TCS_DONE {

	// Variables.

	/** The current TCS and telescope status information.*/
	protected TCS_Status.Segment status;

	/** Create a SHOW_DONE with specified id.
	 * @param id The unique id of this SHOW_DONE.
	 */
	public SHOW_DONE (String id) { super(id); }

	/** Set the current TCS and telescope status information.
	 * @param status The current TCS and telescope status information..
	 */
	public void setStatus(TCS_Status.Segment status) { this.status = status; }

	/** Get the current TCS and telescope status information.
	 * @return The current TCS and telescope status information.
	 */
	public TCS_Status.Segment getStatus() { return status; }

	// Hand generated code.

} // class def. [SHOW_DONE].

