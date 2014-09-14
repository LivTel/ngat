package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.message.base.*;

/** GUI_RCS COMMAND: GET_SEEING.<br>
 *  Request seeing information.<br>
 * <br>
 *  Module code: 680100<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>category - status category.</dd>
 * <dd>&nbsp;values: a valid status category</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - status information.</dd>
 * </dl>
 * <br>
 */
public class GET_SEEING extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the category was not recognized.*/
	public static final int UNKNOWN_CATEGORY = 680101;

	/** Constant: Indicates that the status information is not currently available.*/
	public static final int NOT_AVAILABLE = 680102;

	/** Constant: Indicates that the status information is out of date.*/
	public static final int EXPIRED = 680103;

	// Variables.

	/** The last sequence number.*/
    protected int lastSequenceNumber;

    /** Time of last sample.*/
    protected long lastSampleTime;

	/** Create a GET_STATUS with specified id.
	 * @param id The unique id of this GET_STATUS.
	 */
	public GET_SEEING(String id) { super(id); }

	/** Create a GET_STATUS with specified id and parameters.
	 * @param id The unique id of this GET_STATUS.
	 * @param category The status category.
	 */
	public GET_SEEING(
	         String id,
	         int lastSequenceNumber,
		 long lastSampleTime ) {
	         super(id);
	           this.lastSequenceNumber = lastSequenceNumber;
		   this.lastSampleTime = lastSampleTime;
	         }

	/** Set the lastSequenceNumber.
	 * @param lastSequenceNumber The last sequence number received.
	 */
	public void setLastSequenceNumber(int lastSequenceNumber) { this.lastSequenceNumber = lastSequenceNumber; }

	/** Get the lastSequenceNumber.
	 * @return The lastSequenceNumber.
	 */
	public int getLastSequenceNumber() { return lastSequenceNumber; }


    public void setLastSampleTime(long lastSampleTime) {
	this.lastSampleTime = lastSampleTime;
    }

    public long getLastSampleTime() {
	return lastSampleTime;
    }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
		    getId()+
		    ", LSN="+lastSequenceNumber+
		    ", LST="+lastSampleTime+"]";
	}
	// Hand generated code.

} // class def. [GET_SEEING].

