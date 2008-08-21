package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SEND_LOG.<br>
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
public class SEND_LOG extends GUI_TO_RCS {

	// Variables.

	/** The a valid log level for the message*/
	protected int level;

	/** The name of the Logger*/
	protected String logName;

	/** The log message*/
	protected String message;

	/** The the category of the message*/
	protected String category;

	/** The name of the originator class*/
	protected String clazz;

	/** The name of the originator instance*/
	protected String name;

	/** Create a SEND_LOG with specified id.
	 * @param id The unique id of this SEND_LOG.
	 */
	public SEND_LOG(String id) { super(id); }

	/** Create a SEND_LOG with specified id and parameters.
	 * @param id The unique id of this SEND_LOG.
	 * @param level The a valid log level for the message
	 * @param logName The name of the Logger
	 * @param message The log message
	 * @param category The the category of the message
	 * @param clazz The name of the originator clazz
	 * @param name The name of the originator instance
	 */
	public SEND_LOG(
	         String id,
	         int level,
	         String logName,
	         String message,
	         String category,
	         String clazz,
	         String name) {
	         super(id);
	           this.level = level;
	           this.logName = logName;
	           this.message = message;
	           this.category = category;
	           this.clazz = clazz;
	           this.name = name;
	         }

	/** Set the a valid log level for the message
	 * @param level The a valid log level for the message
	 */
	public void setLevel(int level) { this.level = level; }

	/** Get the a valid log level for the message
	 * @return The a valid log level for the message
	 */
	public int getLevel() { return level; }

	/** Set the name of the Logger
	 * @param logName The name of the Logger
	 */
	public void setLogName(String logName) { this.logName = logName; }

	/** Get the name of the Logger
	 * @return The name of the Logger
	 */
	public String getLogName() { return logName; }

	/** Set the log message
	 * @param message The log message
	 */
	public void setMessage(String message) { this.message = message; }

	/** Get the log message
	 * @return The log message
	 */
	public String getMessage() { return message; }

	/** Set the the category of the message
	 * @param category The the category of the message
	 */
	public void setCategory(String category) { this.category = category; }

	/** Get the the category of the message
	 * @return The the category of the message
	 */
	public String getCategory() { return category; }

	/** Set the name of the originator clazz
	 * @param clazz The name of the originator clazz
	 */
	public void setClazz(String clazz) { this.clazz = clazz; }

	/** Get the name of the originator clazz
	 * @return The name of the originator clazz
	 */
	public String getClazz() { return clazz; }

	/** Set the name of the originator instance
	 * @param name The name of the originator instance
	 */
	public void setName(String name) { this.name = name; }

	/** Get the name of the originator instance
	 * @return The name of the originator instance
	 */
	public String getName() { return name; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", level="+level+
			", logName="+logName+
			", message="+message+
			", category="+category+
			", clazz="+clazz+
			", name="+name+"]";
	}
	// Hand generated code.

} // class def. [SEND_LOG].

