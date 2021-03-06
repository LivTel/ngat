package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SPY_DONE.<br>
 *  Send a Spy  request.<br>
 * <br>
 *  Module code: 681600<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>category - target category</dd>
 * <dd>&nbsp;values: A valid category of target {SENSOR, FILTER, RULE etc}</dd>
 * <dd>target - name of the target object to watch</dd>
 * <dd>&nbsp;values: a valid target</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SPY_DONE extends GUI_TO_RCS_DONE {

	/** Create a SPY_DONE with specified id.
	 * @param id The unique id of this SPY_DONE.
	 */
	public SPY_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SPY_DONE].

