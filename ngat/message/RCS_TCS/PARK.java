package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: PARK.<br>
 * Instruct TCS to park the telescope.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - position to park the telescope.</dd>
 * <dd>&nbsp;values: { in config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class PARK extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates default park position.*/
	public static final int DEFAULT = 0;

	// Variables.

	/** The position to park the telescope.*/
	protected int position;

	/** Create a PARK with specified id.
	 * @param id The unique id of this PARK.
	 */
	public PARK(String id) { super(id); }

	/** Create a PARK with specified id and parameters.
	 * @param id The unique id of this PARK.
	 * @param position The position to park the telescope.
	 */
	public PARK(
	         String id,
	         int position) {
	         super(id);
	           this.position = position;
	         }

	/** Set the position to park the telescope.
	 * @param position The position to park the telescope.
	 */
	public void setPosition(int position) { this.position = position; }

	/** Get the position to park the telescope.
	 * @return The position to park the telescope.
	 */
	public int getPosition() { return position; }

	// Hand generated code.

} // class def. [PARK].

