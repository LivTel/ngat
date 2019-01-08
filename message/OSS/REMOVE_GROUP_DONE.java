package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: REMOVE_GROUP_DONE.<br>
 * Command: REMOVE_GROUP<br>
 * Removes a Group from the database. <br>
 * Module code: 702600<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>groupPath - The path used to locate the Group in the P2DB</dd>
 * <dd>&nbsp;values: Path to a valid Group</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class REMOVE_GROUP_DONE extends TRANSACTION_DONE {

	/** Create a REMOVE_GROUP_DONE with specified id.
	 * @param id The unique id of this REMOVE_GROUP_DONE.
	 */
	public REMOVE_GROUP_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [REMOVE_GROUP_DONE].

