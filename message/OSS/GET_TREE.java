package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_TREE.<br>
 * Command: GET_TREE<br>
 * Retreives the full Tree starting at Root and including TAGs, User, Proposals.<br>
 *  These are returned in the form of Descriptors and content lists.<br>
 * Module code: 704600<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>treeDescriptor - The tree descriptor containing descriptors of all content.</dd>
 * </dl>
 * <br>
 */
public class GET_TREE extends TRANSACTION {

	/** Create a GET_TREE with specified id.
	 * @param id The unique id of this GET_TREE.
	 */
	public GET_TREE(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [GET_TREE].

