package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_TAG_DONE.<br>
 * Command: ADD_TAG<br>
 * Add the TAG to the DB root.<br>
 * <br>
 * Module code: 704500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tag - The Tag to add</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete Tag</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_TAG_DONE extends TRANSACTION_DONE {

	/** Create a ADD_TAG_DONE with specified id.
	 * @param id The unique id of this ADD_TAG_DONE.
	 */
	public ADD_TAG_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_TAG_DONE].

