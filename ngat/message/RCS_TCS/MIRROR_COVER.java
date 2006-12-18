package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: MIRROR_COVER.<br>
 * Instruct TCS to open or close the mirror cover.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place mirror cover into.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class MIRROR_COVER extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates mirror cover should be opened.*/
	public static final int OPEN = 0;

	/** Constant: Indicates mirror cover should be closed.*/
	public static final int CLOSE = 1;

	// Variables.

	/** The state to place mirror cover into.*/
	protected int state;

	/** Create a MIRROR_COVER with specified id.
	 * @param id The unique id of this MIRROR_COVER.
	 */
	public MIRROR_COVER(String id) { super(id); }

	/** Create a MIRROR_COVER with specified id and parameters.
	 * @param id The unique id of this MIRROR_COVER.
	 * @param state The state to place mirror cover into.
	 */
	public MIRROR_COVER(
	         String id,
	         int state) {
	         super(id);
	           this.state = state;
	         }

	/** Set the state to place mirror cover into.
	 * @param state The state to place mirror cover into.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the state to place mirror cover into.
	 * @return The state to place mirror cover into.
	 */
	public int getState() { return state; }

	// Hand generated code.

} // class def. [MIRROR_COVER].

