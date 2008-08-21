package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SEND_RCI_DONE.<br>
 *  Send an RCI Command to the RCS.<br>
 * <br>
 *  Module code: 681500<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>command - RCI Command</dd>
 * <dd>&nbsp;values: A valid RCI Command string</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>response - Response from the RCI Command execution.</dd>
 * </dl>
 * <br>
 */
public class SEND_RCI_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The Response from the RCI Command execution.*/
	protected String response;

	/** Create a SEND_RCI_DONE with specified id.
	 * @param id The unique id of this SEND_RCI_DONE.
	 */
	public SEND_RCI_DONE (String id) { super(id); }

	/** Set the Response from the RCI Command execution.
	 * @param response The Response from the RCI Command execution..
	 */
	public void setResponse(String response) { this.response = response; }

	/** Get the Response from the RCI Command execution.
	 * @return The Response from the RCI Command execution.
	 */
	public String getResponse() { return response; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", response="+response+"]";
	}
	// Hand generated code.

} // class def. [SEND_RCI_DONE].

