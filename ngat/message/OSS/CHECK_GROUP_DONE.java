package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: CHECK_GROUP_DONE.<br>
 * Command: CHECK_GROUP<br>
 * Returns the scheduling/execution history of a group.<br>
 * Module code: 700900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>groupPath - null</dd>
 * <dd>&nbsp;values: The path used to identify the Group in the P2DB</dd>
 * <dd>startTime - The start of the time period to check whether Group was done</dd>
 * <dd>&nbsp;values: Time in millis since epoch</dd>
 * <dd>endTime - The end of the time period to check whether Group was done</dd>
 * <dd>&nbsp;values: Time in millis since epoch</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>history - A java.util.List containing zero or more ngat.phase2.Group.History objects</dd>
 * </dl>
 * <br>
 */
public class CHECK_GROUP_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List containing zero or more ngat.phase2.Group.History objects*/
	protected List history;

	/** Create a CHECK_GROUP_DONE with specified id.
	 * @param id The unique id of this CHECK_GROUP_DONE.
	 */
	public CHECK_GROUP_DONE (String id) { super(id); }

	/** Set the A java.util.List containing zero or more ngat.phase2.Group.History objects
	 * @param history The A java.util.List containing zero or more ngat.phase2.Group.History objects.
	 */
	public void setHistory(List history) { this.history = history; }

	/** Get the A java.util.List containing zero or more ngat.phase2.Group.History objects
	 * @return The A java.util.List containing zero or more ngat.phase2.Group.History objects
	 */
	public List getHistory() { return history; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", history="+history+"]";
	}
	// Hand generated code.

} // class def. [CHECK_GROUP_DONE].

