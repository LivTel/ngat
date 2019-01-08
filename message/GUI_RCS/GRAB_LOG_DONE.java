package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GRAB_LOG_DONE.<br>
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
public class GRAB_LOG_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The one of many logger output records*/
	protected LogRecord record;

	/** Create a GRAB_LOG_DONE with specified id.
	 * @param id The unique id of this GRAB_LOG_DONE.
	 */
	public GRAB_LOG_DONE (String id) { super(id); }

	/** Set the one of many logger output records
	 * @param record The one of many logger output records.
	 */
	public void setRecord(LogRecord record) { this.record = record; }

	/** Get the one of many logger output records
	 * @return The one of many logger output records
	 */
	public LogRecord getRecord() { return record; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", record="+record+"]";
	}
	// Hand generated code.

} // class def. [GRAB_LOG_DONE].

