package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GET_STATE_MODEL.<br>
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
public class GET_STATE_MODEL extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the state model information is not available*/
	public static final int NOT_AVAILABLE = 680201;

	/** Create a GET_STATE_MODEL with specified id.
	 * @param id The unique id of this GET_STATE_MODEL.
	 */
	public GET_STATE_MODEL(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [GET_STATE_MODEL].

