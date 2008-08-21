package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GET_STATUS_DONE.<br>
 *  Request status information.<br>
 * <br>
 *  Module code: 680100<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>category - status category.</dd>
 * <dd>&nbsp;values: a valid status category</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - status information.</dd>
 * </dl>
 * <br>
 */
public class GET_STATUS_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The status information.*/
	protected StatusCategory status;

	/** Create a GET_STATUS_DONE with specified id.
	 * @param id The unique id of this GET_STATUS_DONE.
	 */
	public GET_STATUS_DONE (String id) { super(id); }

	/** Set the status information.
	 * @param status The status information..
	 */
	public void setStatus(StatusCategory status) { this.status = status; }

	/** Get the status information.
	 * @return The status information.
	 */
	public StatusCategory getStatus() { return status; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", status="+status+"]";
	}
	// Hand generated code.

} // class def. [GET_STATUS_DONE].

