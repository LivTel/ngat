package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: NETWORK_TEST_DONE.<br>
 * Command: NETWORK_TEST<br>
 * Requests the OSS to return a number of blocks of data.<br>
 * Currently this is broken<br>
 * Module code: 702000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>blocks - null</dd>
 * <dd>&nbsp;values: Number of blocks of data to return</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class NETWORK_TEST_DONE extends TRANSACTION_DONE {

	/** Create a NETWORK_TEST_DONE with specified id.
	 * @param id The unique id of this NETWORK_TEST_DONE.
	 */
	public NETWORK_TEST_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [NETWORK_TEST_DONE].

