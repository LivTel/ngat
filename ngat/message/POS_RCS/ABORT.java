package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: ABORT.<br>
 *  Terminates a specified, currently executing or pending request.<br>
 *  Pending requests are just removed from the queue. Executing requests<br>
 *  are stopped in mid action where this is possible.<br>
 *  Module code: 690100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>requestCode - reference code of request to abort.</dd>
 * <dd>&nbsp;values: a valid acknowledgement reference number returned by any accepted request</dd>
 * <dd>all - True if ALL pending and executing requests should be aborted.</dd>
 * <dd>&nbsp;values: { True | False }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ABORT extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates an invalid request number.*/
	public static final int NO_SUCH_REQUEST = 690101;

	// Variables.

	/** The reference code of request to abort.*/
	protected int requestCode;

	/** The True if ALL pending and executing requests should be aborted.*/
	protected boolean all;

	/** Create a ABORT with specified id.
	 * @param id The unique id of this ABORT.
	 */
	public ABORT(String id) { super(id); }

	/** Create a ABORT with specified id and parameters.
	 * @param id The unique id of this ABORT.
	 * @param requestCode The reference code of request to abort.
	 * @param all The True if ALL pending and executing requests should be aborted.
	 */
	public ABORT(
	         String id,
	         int requestCode,
	         boolean all) {
	         super(id);
	           this.requestCode = requestCode;
	           this.all = all;
	         }

	/** Set the reference code of request to abort.
	 * @param requestCode The reference code of request to abort.
	 */
	public void setRequestCode(int requestCode) { this.requestCode = requestCode; }

	/** Get the reference code of request to abort.
	 * @return The reference code of request to abort.
	 */
	public int getRequestCode() { return requestCode; }

	/** Set the True if ALL pending and executing requests should be aborted.
	 * @param all The True if ALL pending and executing requests should be aborted.
	 */
	public void setAll(boolean all) { this.all = all; }

	/** Get the True if ALL pending and executing requests should be aborted.
	 * @return The True if ALL pending and executing requests should be aborted.
	 */
	public boolean getAll() { return all; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", requestCode="+requestCode+
			", all="+all+"]";
	}
	// Hand generated code.

} // class def. [ABORT].

