package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: POS_TO_RCS.<br>
 *  Base class for messaging between POS and RCS.<br>
 *  Module code: 690000.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>requestNumber - unique ID for this request.</dd>
 * <dd>&nbsp;values: a unique value</dd>
 * <dd>controllerAddress - Address of the client (RTOC).</dd>
 * <dd>&nbsp;values: a valid IP Address</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class POS_TO_RCS extends COMMAND {

	// Constants.

	/** Constant: Indicates that a non-specific error has occurred.  */
	public static final int UNSPECIFIED_ERROR = 690001;

	/** Constant: Indicates that the client aborted this request.   */
	public static final int CLIENT_ABORTED = 690002;

	/** Constant: Indicates that the request was aborted for some unspecified reason.*/
	public static final int TASK_ABORTED = 690003;

	/** Constant: Indicates that the serve is not accepting connections.*/
	public static final int SERVER_BUSY = 690004;

	/** Constant: Indicates that the request has been queued for longer than anticipated*/
	public static final int TIMED_OUT = 690005;

	/** Constant: Indicates that the RTOC is not currently in control*/
	public static final int NOT_IN_CONTROL = 690006;

	/** Constant: Indicates that the RTOC is not known to the RCS*/
	public static final int INVALID_RTOC = 690007;

	/** Constant: Indicates that the RCS is not currently operational*/
	public static final int NOT_OPERATIONAL = 690008;

	/** Constant: Indicates that the RCS is not currently in planetarium (realtime) mode.*/
	public static final int NOT_PLANETARIUM_MODE = 690009;

	// Variables.

	/** The unique ID for this request.*/
	protected int requestNumber;

	/** The Address of the client (RTOC).*/
	protected String controllerAddress;

	/** Create a POS_TO_RCS with specified id.
	 * @param id The unique id of this POS_TO_RCS.
	 */
	public POS_TO_RCS(String id) { super(id); }

	/** Create a POS_TO_RCS with specified id and parameters.
	 * @param id The unique id of this POS_TO_RCS.
	 * @param requestNumber The unique ID for this request.
	 * @param controllerAddress The Address of the client (RTOC).
	 */
	public POS_TO_RCS(
	         String id,
	         int requestNumber,
	         String controllerAddress) {
	         super(id);
	           this.requestNumber = requestNumber;
	           this.controllerAddress = controllerAddress;
	         }

	/** Set the unique ID for this request.
	 * @param requestNumber The unique ID for this request.
	 */
	public void setRequestNumber(int requestNumber) { this.requestNumber = requestNumber; }

	/** Get the unique ID for this request.
	 * @return The unique ID for this request.
	 */
	public int getRequestNumber() { return requestNumber; }

	/** Set the Address of the client (RTOC).
	 * @param controllerAddress The Address of the client (RTOC).
	 */
	public void setControllerAddress(String controllerAddress) { this.controllerAddress = controllerAddress; }

	/** Get the Address of the client (RTOC).
	 * @return The Address of the client (RTOC).
	 */
	public String getControllerAddress() { return controllerAddress; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", requestNumber="+requestNumber+
			", controllerAddress="+controllerAddress+"]";
	}
	// Hand generated code.

} // class def. [POS_TO_RCS].

