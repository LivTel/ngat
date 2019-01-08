package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SPY.<br>
 *  Send a Spy  request.<br>
 * <br>
 *  Module code: 681600<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>category - target category</dd>
 * <dd>&nbsp;values: A valid category of target {SENSOR, FILTER, RULE etc}</dd>
 * <dd>target - name of the target object to watch</dd>
 * <dd>&nbsp;values: a valid target</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SPY extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the category was not recognized.*/
	public static final int UNKNOWN_CATEGORY = 681501;

	/** Constant: Indicates that the target was not recognized*/
	public static final int UNKNOWN_TARGET = 681502;

	/** Constant: Indicates target is a Sensor.*/
	public static final int SENSOR = 681501;

	/** Constant: Indicates target is a filter.*/
	public static final int FILTER = 681502;

	/** Constant: Indicates target is a monitor.*/
	public static final int MONITOR = 681503;

	/** Constant: Indicates target is a rule.*/
	public static final int RULE = 681504;

	/** Constant: Indicates target is a ruleset.*/
	public static final int RULESET = 681505;

	// Variables.

	/** The target category*/
	protected int category;

	/** The name of the target object to watch*/
	protected String target;

	/** Create a SPY with specified id.
	 * @param id The unique id of this SPY.
	 */
	public SPY(String id) { super(id); }

	/** Create a SPY with specified id and parameters.
	 * @param id The unique id of this SPY.
	 * @param category The target category
	 * @param target The name of the target object to watch
	 */
	public SPY(
	         String id,
	         int category,
	         String target) {
	         super(id);
	           this.category = category;
	           this.target = target;
	         }

	/** Set the target category
	 * @param category The target category
	 */
	public void setCategory(int category) { this.category = category; }

	/** Get the target category
	 * @return The target category
	 */
	public int getCategory() { return category; }

	/** Set the name of the target object to watch
	 * @param target The name of the target object to watch
	 */
	public void setTarget(String target) { this.target = target; }

	/** Get the name of the target object to watch
	 * @return The name of the target object to watch
	 */
	public String getTarget() { return target; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", category="+category+
			", target="+target+"]";
	}
	// Hand generated code.

} // class def. [SPY].

