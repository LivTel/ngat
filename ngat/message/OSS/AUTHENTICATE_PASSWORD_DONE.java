package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: AUTHENTICATE_PASSWORD_DONE.<br>
 * Command: AUTHENTICATE_PASSWORD<br>
 * Checks whether a password change was successful.<br>
 * Module code: 700400<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AUTHENTICATE_PASSWORD_DONE extends TRANSACTION_DONE {

	/** Create a AUTHENTICATE_PASSWORD_DONE with specified id.
	 * @param id The unique id of this AUTHENTICATE_PASSWORD_DONE.
	 */
	public AUTHENTICATE_PASSWORD_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [AUTHENTICATE_PASSWORD_DONE].

