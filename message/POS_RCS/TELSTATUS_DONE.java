package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TELSTATUS_DONE.<br>
 *  Requests telescope status information from the RCS. This can only work during<br>
 *  scheduled planetarium mode periods. Outside of this time a status service is<br>
 *  obtained via a dedicated web site. <a href = "http://TBD">Telescope status pages</a>.<br>
 *  Module code: 690900<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - table of name, value pairs indicating various telescope statii.</dd>
 * </dl>
 * <br>
 */
public class TELSTATUS_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The table of name, value pairs indicating various telescope statii.*/
	protected Hashtable status;

	/** Create a TELSTATUS_DONE with specified id.
	 * @param id The unique id of this TELSTATUS_DONE.
	 */
	public TELSTATUS_DONE (String id) { super(id); }

	/** Set the table of name, value pairs indicating various telescope statii.
	 * @param status The table of name, value pairs indicating various telescope statii..
	 */
	public void setStatus(Hashtable status) { this.status = status; }

	/** Get the table of name, value pairs indicating various telescope statii.
	 * @return The table of name, value pairs indicating various telescope statii.
	 */
	public Hashtable getStatus() { return status; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", status="+status+"]";
	}
	// Hand generated code.

} // class def. [TELSTATUS_DONE].

