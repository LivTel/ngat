package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: OPERATIONAL.<br>
 * Instruct TCS to change from day to night state or vice versa.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state of operation.</dd>
 * <dd>&nbsp;values: { ON | OFF }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OPERATIONAL extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates go into night-time mode.*/
	public static final int ON = 0;

	/** Constant: Indicates go into day-time mode.*/
	public static final int OFF = 1;

	// Variables.

	/** The state of operation.*/
	protected int state;

	/** Create a OPERATIONAL with specified id.
	 * @param id The unique id of this OPERATIONAL.
	 */
	public OPERATIONAL(String id) { super(id); }

	/** Create a OPERATIONAL with specified id and parameters.
	 * @param id The unique id of this OPERATIONAL.
	 * @param state The state of operation.
	 */
	public OPERATIONAL(
	         String id,
	         int state) {
	         super(id);
	           this.state = state;
	         }

	/** Set the state of operation.
	 * @param state The state of operation.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the state of operation.
	 * @return The state of operation.
	 */
	public int getState() { return state; }

	// Hand generated code.

} // class def. [OPERATIONAL].

