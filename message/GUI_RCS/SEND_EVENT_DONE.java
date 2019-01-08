package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SEND_EVENT_DONE.<br>
 *  Send an Event message.<br>
 * <br>
 *  Module code: 680600<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>priority - message priority</dd>
 * <dd>&nbsp;values: { DEFAULT | PRIORITY }</dd>
 * <dd>topic - event topic</dd>
 * <dd>&nbsp;values: a valid topic</dd>
 * <dd>timed - when the event is to be despatched at a later time</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>after - time in seconds until despatched</dd>
 * <dd>&nbsp;values: 0 <= after</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SEND_EVENT_DONE extends GUI_TO_RCS_DONE {

	/** Create a SEND_EVENT_DONE with specified id.
	 * @param id The unique id of this SEND_EVENT_DONE.
	 */
	public SEND_EVENT_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SEND_EVENT_DONE].

