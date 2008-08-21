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
 * <dd>model - state model information</dd>
 * </dl>
 * <br>
 */
public class GET_STATE_MODEL_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The state model information*/
	protected HashMap model;

	/** Create a GET_STATE_MODEL_DONE with specified id.
	 * @param id The unique id of this GET_STATE_MODEL_DONE.
	 */
	public GET_STATE_MODEL_DONE (String id) { super(id); }

	/** Set the state model information
	 * @param model The state model information.
	 */
	public void setModel(HashMap model) { this.model = model; }

	/** Get the state model information
	 * @return The state model information
	 */
	public HashMap getModel() { return model; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", model="+model+"]";
	}
	// Hand generated code.

} // class def. [GET_STATE_MODEL_DONE].

