package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND_DONE: DARKSLIDE_DONE.<br>
 * Instructs the TCS to move the darkslide filter in/closed or out/open.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place darkslide filter in.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class DARKSLIDE_DONE extends RCS_TO_TCS_DONE {

	/** Create a DARKSLIDE_DONE with specified id.
	 * @param id The unique id of this DARKSLIDE_DONE.
	 */
	public DARKSLIDE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [DARKSLIDE_DONE].

