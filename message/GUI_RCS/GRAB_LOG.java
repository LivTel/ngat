package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GRAB_LOG.<br>
 *  Request logging to be directed to the GUI a handler will<br>
 *  then log to screen or a popup textfield. Handler may impose<br>
 *  any formatting it wishes. Multiple LogRecords will be received<br>
 *  at unpredictable times until the connection is closed at either end.<br>
 * <br>
 *  Module code: 680300<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>logName - logger name</dd>
 * <dd>&nbsp;values: a valid logger</dd>
 * <dd>level - the log level for the stream handler</dd>
 * <dd>&nbsp;values: { 0 - 9, OFF, ALL }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>record - one of many logger output records</dd>
 * </dl>
 * <br>
 */
public class GRAB_LOG extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates the logger does not exist or cannot be found*/
	public static final int NO_SUCH_LOGGER = 680301;

	/** Constant: Indicates the log level was not valid*/
	public static final int INVALID_LEVEL = 680302;

	// Variables.

	/** The logger name*/
	protected String logName;

	/** The the log level for the stream handler*/
	protected int level;

	/** Create a GRAB_LOG with specified id.
	 * @param id The unique id of this GRAB_LOG.
	 */
	public GRAB_LOG(String id) { super(id); }

	/** Create a GRAB_LOG with specified id and parameters.
	 * @param id The unique id of this GRAB_LOG.
	 * @param logName The logger name
	 * @param level The the log level for the stream handler
	 */
	public GRAB_LOG(
	         String id,
	         String logName,
	         int level) {
	         super(id);
	           this.logName = logName;
	           this.level = level;
	         }

	/** Set the logger name
	 * @param logName The logger name
	 */
	public void setLogName(String logName) { this.logName = logName; }

	/** Get the logger name
	 * @return The logger name
	 */
	public String getLogName() { return logName; }

	/** Set the the log level for the stream handler
	 * @param level The the log level for the stream handler
	 */
	public void setLevel(int level) { this.level = level; }

	/** Get the the log level for the stream handler
	 * @return The the log level for the stream handler
	 */
	public int getLevel() { return level; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", logName="+logName+
			", level="+level+"]";
	}
	// Hand generated code.

} // class def. [GRAB_LOG].

