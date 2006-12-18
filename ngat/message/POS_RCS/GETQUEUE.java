package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: GETQUEUE.<br>
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
public class GETQUEUE extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates that the specified request is currently executing.*/
	public static final int EXECUTING = 690701;

	/** Constant: Indicates that the specified request is pending in the queue.*/
	public static final int PENDING = 690702;

	/** Create a GETQUEUE with specified id.
	 * @param id The unique id of this GETQUEUE.
	 */
	public GETQUEUE(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [GETQUEUE].

