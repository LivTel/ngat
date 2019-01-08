package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: ENCLOSURE.<br>
 * Instructs the TCS to open or close the enclosure.<br>
 * (Which shutter is which the TCS uses Shutter1 and Shutter2?)<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - enclosure section to open or close.</dd>
 * <dd>&nbsp;values: { BOTH | NORTH | SOUTH }</dd>
 * <dd>state - state to move the enclosure to.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dd>pos - precise angle to open/close to.</dd>
 * <dd>&nbsp;values: 0.00 to 90.00 degs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ENCLOSURE extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates the enclosure should be opened.*/
	public static final int OPEN = 0;

	/** Constant: Indicates the enclosure should be closed.*/
	public static final int CLOSE = 1;

	/** Constant: Indicates both sections should be moved.*/
	public static final int BOTH = 2;

	/** Constant: Indicates only the north section is to be moved.*/
	public static final int NORTH = 3;

	/** Constant: Indicates only the south section is to be moved.*/
	public static final int SOUTH = 4;

	// Variables.

	/** The enclosure section to open or close.*/
	protected int mechanism;

	/** The state to move the enclosure to.*/
	protected int state;

	/** The precise angle to open/close to.*/
	protected int pos;

	/** Create a ENCLOSURE with specified id.
	 * @param id The unique id of this ENCLOSURE.
	 */
	public ENCLOSURE(String id) { super(id); }

	/** Create a ENCLOSURE with specified id and parameters.
	 * @param id The unique id of this ENCLOSURE.
	 * @param mechanism The enclosure section to open or close.
	 * @param state The state to move the enclosure to.
	 * @param pos The precise angle to open/close to.
	 */
	public ENCLOSURE(
	         String id,
	         int mechanism,
	         int state,
	         int pos) {
	         super(id);
	           this.mechanism = mechanism;
	           this.state = state;
	           this.pos = pos;
	         }

	/** Set the enclosure section to open or close.
	 * @param mechanism The enclosure section to open or close.
	 */
	public void setMechanism(int mechanism) { this.mechanism = mechanism; }

	/** Get the enclosure section to open or close.
	 * @return The enclosure section to open or close.
	 */
	public int getMechanism() { return mechanism; }

	/** Set the state to move the enclosure to.
	 * @param state The state to move the enclosure to.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the state to move the enclosure to.
	 * @return The state to move the enclosure to.
	 */
	public int getState() { return state; }

	/** Set the precise angle to open/close to.
	 * @param pos The precise angle to open/close to.
	 */
	public void setPos(int pos) { this.pos = pos; }

	/** Get the precise angle to open/close to.
	 * @return The precise angle to open/close to.
	 */
	public int getPos() { return pos; }

	// Hand generated code.

} // class def. [ENCLOSURE].

