package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_SOURCE_DONE.<br>
 * Command: ADD_SOURCE<br>
 * Adds a source to an existing proposal.<br>
 * Module code: 700500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the OSS database</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>source - The Source to add</dd>
 * <dd>&nbsp;values: A valid Source</dd>
 * <dd>replace - The Source to add</dd>
 * <dd>&nbsp;values: True if the Source should be replaced (if it already exists)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_SOURCE_DONE extends TRANSACTION_DONE {

	/** Create a ADD_SOURCE_DONE with specified id.
	 * @param id The unique id of this ADD_SOURCE_DONE.
	 */
	public ADD_SOURCE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_SOURCE_DONE].

