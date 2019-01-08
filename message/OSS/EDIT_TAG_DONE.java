package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: EDIT_TAG_DONE.<br>
 * Command: EDIT_TAG<br>
 * Modify a stored TAG's accounting info<br>
 * <br>
 * Module code: 705000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tag - The Tag to edit</dd>
 * <dd>&nbsp;values: A valid and complete Tag</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class EDIT_TAG_DONE extends TRANSACTION_DONE {

	/** Create a EDIT_TAG_DONE with specified id.
	 * @param id The unique id of this EDIT_TAG_DONE.
	 */
	public EDIT_TAG_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [EDIT_TAG_DONE].

