package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SEND_EVENT.<br>
 *  Send an Event message.<br>
 * <br>
 *  Module code: 680600<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>priority - message priority</dd>
 * <dd>&nbsp;values: { DEFAULT | PRIORITY }</dd>
 * <dd>topic - event topic</dd>
 * <dd>&nbsp;values: a valid topic</dd>
 * <dd>timed - when the event is to be despatched at a later time</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>after - time in seconds until despatched</dd>
 * <dd>&nbsp;values: 0 <= after</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SEND_EVENT extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the message has standard priority*/
	public static final int DEFAULT_LEVEL = 680601;

	/** Constant: Indicates that the message is urgent*/
	public static final int PRIORITY_LEVEL = 680602;

	// Variables.

	/** The message priority*/
	protected int priority;

	/** The event topic*/
	protected String topic;

	/** The when the event is to be despatched at a later time*/
	protected boolean timed;

	/** The time in seconds until despatched*/
	protected int after;

	/** Create a SEND_EVENT with specified id.
	 * @param id The unique id of this SEND_EVENT.
	 */
	public SEND_EVENT(String id) { super(id); }

	/** Create a SEND_EVENT with specified id and parameters.
	 * @param id The unique id of this SEND_EVENT.
	 * @param priority The message priority
	 * @param topic The event topic
	 * @param timed The when the event is to be despatched at a later time
	 * @param after The time in seconds until despatched
	 */
	public SEND_EVENT(
	         String id,
	         int priority,
	         String topic,
	         boolean timed,
	         int after) {
	         super(id);
	           this.priority = priority;
	           this.topic = topic;
	           this.timed = timed;
	           this.after = after;
	         }

	/** Set the message priority
	 * @param priority The message priority
	 */
	public void setPriority(int priority) { this.priority = priority; }

	/** Get the message priority
	 * @return The message priority
	 */
	public int getPriority() { return priority; }

	/** Set the event topic
	 * @param topic The event topic
	 */
	public void setTopic(String topic) { this.topic = topic; }

	/** Get the event topic
	 * @return The event topic
	 */
	public String getTopic() { return topic; }

	/** Set the when the event is to be despatched at a later time
	 * @param timed The when the event is to be despatched at a later time
	 */
	public void setTimed(boolean timed) { this.timed = timed; }

	/** Get the when the event is to be despatched at a later time
	 * @return The when the event is to be despatched at a later time
	 */
	public boolean getTimed() { return timed; }

	/** Set the time in seconds until despatched
	 * @param after The time in seconds until despatched
	 */
	public void setAfter(int after) { this.after = after; }

	/** Get the time in seconds until despatched
	 * @return The time in seconds until despatched
	 */
	public int getAfter() { return after; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", priority="+priority+
			", topic="+topic+
			", timed="+timed+
			", after="+after+"]";
	}
	// Hand generated code.

} // class def. [SEND_EVENT].

