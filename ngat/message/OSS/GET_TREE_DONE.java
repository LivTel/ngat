package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_TREE_DONE.<br>
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
public class GET_TREE_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The tree descriptor containing descriptors of all content.*/
	protected ItemDescriptor treeDescriptor;

	/** Create a GET_TREE_DONE with specified id.
	 * @param id The unique id of this GET_TREE_DONE.
	 */
	public GET_TREE_DONE (String id) { super(id); }

	/** Set the The tree descriptor containing descriptors of all content.
	 * @param treeDescriptor The The tree descriptor containing descriptors of all content..
	 */
	public void setTreeDescriptor(ItemDescriptor treeDescriptor) { this.treeDescriptor = treeDescriptor; }

	/** Get the The tree descriptor containing descriptors of all content.
	 * @return The The tree descriptor containing descriptors of all content.
	 */
	public ItemDescriptor getTreeDescriptor() { return treeDescriptor; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", treeDescriptor="+treeDescriptor+"]";
	}
	// Hand generated code.

} // class def. [GET_TREE_DONE].

