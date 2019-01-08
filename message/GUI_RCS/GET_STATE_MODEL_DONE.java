package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GET_STATE_MODEL_DONE.<br>
 *  Request state-model information.<br>
 * <br>
 *  Module code: 680200<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>variable - state model variable information</dd>
 * <dd>currentState - current state</dd>
 * <dd>currentOperation - current operation</dd>
 * </dl>
 * <br>
 */
public class GET_STATE_MODEL_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The state model variable information*/
	protected HashMap variables;

	/** The current state*/
	protected int currentState;

	/** The current operation*/
	protected int currentOperation;

	/** Create a GET_STATE_MODEL_DONE with specified id.
	 * @param id The unique id of this GET_STATE_MODEL_DONE.
	 */
	public GET_STATE_MODEL_DONE (String id) { super(id); }

	/** Set the state model variable information
	 * @param variable The state model variable information.
	 */
	public void setVariables(HashMap variables) { this.variables = variables; }

	/** Get the state model variable information
	 * @return The state model variable information
	 */
	public HashMap getVariables() { return variables; }

	/** Set the current state
	 * @param currentState The current state.
	 */
	public void setCurrentState(int currentState) { this.currentState = currentState; }

	/** Get the current state
	 * @return The current state
	 */
	public int getCurrentState() { return currentState; }

	/** Set the current operation
	 * @param currentOperation The current operation.
	 */
	public void setCurrentOperation(int currentOperation) { this.currentOperation = currentOperation; }

	/** Get the current operation
	 * @return The current operation
	 */
	public int getCurrentOperation() { return currentOperation; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", variables="+variables+
			", currentState="+currentState+
			", currentOperation="+currentOperation+"]";
	}
	// Hand generated code.

} // class def. [GET_STATE_MODEL_DONE].

