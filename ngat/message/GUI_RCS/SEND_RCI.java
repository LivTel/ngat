package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SEND_RCI.<br>
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
public class SEND_RCI extends GUI_TO_RCS {

	// Variables.

	/** The RCI Command*/
	protected String command;

	/** Create a SEND_RCI with specified id.
	 * @param id The unique id of this SEND_RCI.
	 */
	public SEND_RCI(String id) { super(id); }

	/** Create a SEND_RCI with specified id and parameters.
	 * @param id The unique id of this SEND_RCI.
	 * @param command The RCI Command
	 */
	public SEND_RCI(
	         String id,
	         String command) {
	         super(id);
	           this.command = command;
	         }

	/** Set the RCI Command
	 * @param command The RCI Command
	 */
	public void setCommand(String command) { this.command = command; }

	/** Get the RCI Command
	 * @return The RCI Command
	 */
	public String getCommand() { return command; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", command="+command+"]";
	}
	// Hand generated code.

} // class def. [SEND_RCI].

