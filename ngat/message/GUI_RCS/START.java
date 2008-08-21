package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: START.<br>
 *  Request the RCS to start.<br>
 * <br>
 *  Module code: 680500<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>engineering - null</dd>
 * <dd>&nbsp;values: indicates whether to start in engineering mode</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class START extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS is already running or starting up*/
	public static final int ALREADY_RUNNING = 680501;

	/** Constant: Indicates some other generic RCW error*/
	public static final int WATCHDOG_ERROR = 680502;

	// Variables.

	/** The null*/
	protected boolean engineering;

	/** Create a START with specified id.
	 * @param id The unique id of this START.
	 */
	public START(String id) { super(id); }

	/** Create a START with specified id and parameters.
	 * @param id The unique id of this START.
	 * @param engineering The null
	 */
	public START(
	         String id,
	         boolean engineering) {
	         super(id);
	           this.engineering = engineering;
	         }

	/** Set the null
	 * @param engineering The null
	 */
	public void setEngineering(boolean engineering) { this.engineering = engineering; }

	/** Get the null
	 * @return The null
	 */
	public boolean getEngineering() { return engineering; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", engineering="+engineering+"]";
	}
	// Hand generated code.

} // class def. [START].

