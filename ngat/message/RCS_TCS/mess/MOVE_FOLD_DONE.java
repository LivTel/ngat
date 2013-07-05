package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND_DONE: MOVE_FOLD_DONE.<br>
 * Instruct TCS to move the fold mirror to a specified position.<br>
 * (These keywords may have changed).<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - instrument port.</dd>
 * <dd>&nbsp;values: { STOWED | POSITION1-8 }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class MOVE_FOLD_DONE extends RCS_TO_TCS_DONE {

	/** Create a MOVE_FOLD_DONE with specified id.
	 * @param id The unique id of this MOVE_FOLD_DONE.
	 */
	public MOVE_FOLD_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [MOVE_FOLD_DONE].

