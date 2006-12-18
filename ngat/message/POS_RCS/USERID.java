package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: USERID.<br>
 *  Sets the current USerID for FITS headers Used at the start<br>
 *  of a time slot in a window to indicate that a new User is<br>
 *  now in control of the telescope.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>userId - Id of a school or other controlling user.</dd>
 * <dd>&nbsp;values: a unique (per School) ID</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class USERID extends POS_TO_RCS {

	// Variables.

	/** The Id of a school or other controlling user.*/
	protected String userId;

	/** Create a USERID with specified id.
	 * @param id The unique id of this USERID.
	 */
	public USERID(String id) { super(id); }

	/** Create a USERID with specified id and parameters.
	 * @param id The unique id of this USERID.
	 * @param userId The Id of a school or other controlling user.
	 */
	public USERID(
	         String id,
	         String userId) {
	         super(id);
	           this.userId = userId;
	         }

	/** Set the Id of a school or other controlling user.
	 * @param userId The Id of a school or other controlling user.
	 */
	public void setUserId(String userId) { this.userId = userId; }

	/** Get the Id of a school or other controlling user.
	 * @return The Id of a school or other controlling user.
	 */
	public String getUserId() { return userId; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", userId="+userId+"]";
	}
	// Hand generated code.

} // class def. [USERID].

