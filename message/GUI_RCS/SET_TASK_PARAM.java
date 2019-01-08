package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SET_TASK_PARAM.<br>
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
public class SET_TASK_PARAM extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the task class was not valid.*/
	public static final int UNKNOWN_TASK = 681901;

	/** Constant: Indicates that some system resource was not available to carry out this operation.*/
	public static final int MISSING_RESOURCE = 681902;

	// Variables.

	/** The full name of the task class.*/
	protected String taskName;

	/** The task configuration key.*/
	protected String param;

	/** The value to associate with the key.*/
	protected String value;

	/** Create a SET_TASK_PARAM with specified id.
	 * @param id The unique id of this SET_TASK_PARAM.
	 */
	public SET_TASK_PARAM(String id) { super(id); }

	/** Create a SET_TASK_PARAM with specified id and parameters.
	 * @param id The unique id of this SET_TASK_PARAM.
	 * @param taskName The full name of the task class.
	 * @param param The task configuration key.
	 * @param value The value to associate with the key.
	 */
	public SET_TASK_PARAM(
	         String id,
	         String taskName,
	         String param,
	         String value) {
	         super(id);
	           this.taskName = taskName;
	           this.param = param;
	           this.value = value;
	         }

	/** Set the full name of the task class.
	 * @param taskName The full name of the task class.
	 */
	public void setTaskName(String taskName) { this.taskName = taskName; }

	/** Get the full name of the task class.
	 * @return The full name of the task class.
	 */
	public String getTaskName() { return taskName; }

	/** Set the task configuration key.
	 * @param param The task configuration key.
	 */
	public void setParam(String param) { this.param = param; }

	/** Get the task configuration key.
	 * @return The task configuration key.
	 */
	public String getParam() { return param; }

	/** Set the value to associate with the key.
	 * @param value The value to associate with the key.
	 */
	public void setValue(String value) { this.value = value; }

	/** Get the value to associate with the key.
	 * @return The value to associate with the key.
	 */
	public String getValue() { return value; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", taskName="+taskName+
			", param="+param+
			", value="+value+"]";
	}
	// Hand generated code.

} // class def. [SET_TASK_PARAM].

