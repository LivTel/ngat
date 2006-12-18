package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: GETQUEUE_DONE.<br>
 *  Retrieves a list of the currently executing requests.<br>
 *  Module code: 690700<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>processList - list of the currently queued and executing requests (at the telescope).</dd>
 * </dl>
 * <br>
 */
public class GETQUEUE_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The list of the currently queued and executing requests (at the telescope).*/
	protected Vector processList;

	/** Create a GETQUEUE_DONE with specified id.
	 * @param id The unique id of this GETQUEUE_DONE.
	 */
	public GETQUEUE_DONE (String id) { super(id); }

	/** Set the list of the currently queued and executing requests (at the telescope).
	 * @param processList The list of the currently queued and executing requests (at the telescope)..
	 */
	public void setProcessList(Vector processList) { this.processList = processList; }

	/** Get the list of the currently queued and executing requests (at the telescope).
	 * @return The list of the currently queued and executing requests (at the telescope).
	 */
	public Vector getProcessList() { return processList; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", processList="+processList+"]";
	}
	// Hand generated code.

} // class def. [GETQUEUE_DONE].

