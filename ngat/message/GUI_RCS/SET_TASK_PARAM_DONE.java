package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SET_TASK_PARAM_DONE.<br>
 *  Dynamically change a task-configuration parameter.<br>
 * <br>
 *  Module code: 681900<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>taskName - full name of the task class.</dd>
 * <dd>&nbsp;values: a valid task class name</dd>
 * <dd>param - task configuration key.</dd>
 * <dd>&nbsp;values: a valid task configuration parameter key</dd>
 * <dd>value - value to associate with the key.</dd>
 * <dd>&nbsp;values: any valid value</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_TASK_PARAM_DONE extends GUI_TO_RCS_DONE {

	/** Create a SET_TASK_PARAM_DONE with specified id.
	 * @param id The unique id of this SET_TASK_PARAM_DONE.
	 */
	public SET_TASK_PARAM_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SET_TASK_PARAM_DONE].

