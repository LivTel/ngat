package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SET_SEEING_DONE.<br>
 *  Set the current seeing used by the OSS.<br>
 * <br>
 *  Module code: 681700<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>seeing - seeing value</dd>
 * <dd>&nbsp;values: { SEEING_POOR |  SEEING_AVERAGE | SEEING_EXCELLENT }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_SEEING_DONE extends GUI_TO_RCS_DONE {

	/** Create a SET_SEEING_DONE with specified id.
	 * @param id The unique id of this SET_SEEING_DONE.
	 */
	public SET_SEEING_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SET_SEEING_DONE].

