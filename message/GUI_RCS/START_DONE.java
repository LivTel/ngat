package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: START_DONE.<br>
 *  Request the RCS to start.<br>
 * <br>
 *  Module code: 680500<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>engineering - null</dd>
 * <dd>&nbsp;values: indicates whether to start in engineering mode</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class START_DONE extends GUI_TO_RCS_DONE {

	/** Create a START_DONE with specified id.
	 * @param id The unique id of this START_DONE.
	 */
	public START_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [START_DONE].

