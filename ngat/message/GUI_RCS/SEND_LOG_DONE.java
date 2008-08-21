package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SEND_LOG_DONE.<br>
 *  Send a Log message.<br>
 * <br>
 *  Module code: 681000<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>level - a valid log level for the message</dd>
 * <dd>&nbsp;values: {0 - 10}</dd>
 * <dd>logName - name of the Logger</dd>
 * <dd>&nbsp;values: a valid logger</dd>
 * <dd>message - log message</dd>
 * <dd>&nbsp;values: a message string</dd>
 * <dd>category - the category of the message</dd>
 * <dd>&nbsp;values: a valid log category</dd>
 * <dd>class - name of the originator class</dd>
 * <dd>&nbsp;values: a class name</dd>
 * <dd>name - name of the originator instance</dd>
 * <dd>&nbsp;values: an object name</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SEND_LOG_DONE extends GUI_TO_RCS_DONE {

	/** Create a SEND_LOG_DONE with specified id.
	 * @param id The unique id of this SEND_LOG_DONE.
	 */
	public SEND_LOG_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SEND_LOG_DONE].

