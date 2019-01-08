package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: SCHEDULABILITY_DONE.<br>
 * Command: SCHEDULABILITY<br>
 * Checks a group for the schedule contention over its lifetime.<br>
 *  <br>
 * Module code: 704700<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>group - the group whose schedulability we want to test.</dd>
 * <dd>&nbsp;values: a valid group</dd>
 * <dd>start - the start of the period we want to check</dd>
 * <dd>&nbsp;values: a time</dd>
 * <dd>end - the end of the period we want to check</dd>
 * <dd>&nbsp;values: a time</dd>
 * <dd>resolution - the time resolution</dd>
 * <dd>&nbsp;values: a time interval in ms</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>schedulability - the schedulability of the group</dd>
 * <dd>differentialFunction - the time varying probability</dd>
 * <dd>cumulativeFunction - the cumulative probability</dd>
 * <dd>failureReasons - reasons for failure to score</dd>
 * </dl>
 * <br>
 */
public class SCHEDULABILITY_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The the schedulability of the group*/
	protected double schedulability;

	/** The the time varying probability*/
	protected double[] differentialFunction;

	/** The the cumulative probability*/
	protected double[] cumulativeFunction;

	/** The reasons for failure to score*/
	protected String failureReasons;

	/** Create a SCHEDULABILITY_DONE with specified id.
	 * @param id The unique id of this SCHEDULABILITY_DONE.
	 */
	public SCHEDULABILITY_DONE (String id) { super(id); }

	/** Set the the schedulability of the group
	 * @param schedulability The the schedulability of the group.
	 */
	public void setSchedulability(double schedulability) { this.schedulability = schedulability; }

	/** Get the the schedulability of the group
	 * @return The the schedulability of the group
	 */
	public double getSchedulability() { return schedulability; }

	/** Set the the time varying probability
	 * @param differentialFunction The the time varying probability.
	 */
	public void setDifferentialFunction(double[] differentialFunction) { this.differentialFunction = differentialFunction; }

	/** Get the the time varying probability
	 * @return The the time varying probability
	 */
	public double[] getDifferentialFunction() { return differentialFunction; }

	/** Set the the cumulative probability
	 * @param cumulativeFunction The the cumulative probability.
	 */
	public void setCumulativeFunction(double[] cumulativeFunction) { this.cumulativeFunction = cumulativeFunction; }

	/** Get the the cumulative probability
	 * @return The the cumulative probability
	 */
	public double[] getCumulativeFunction() { return cumulativeFunction; }

	/** Set the reasons for failure to score
	 * @param failureReasons The reasons for failure to score.
	 */
	public void setFailureReasons(String failureReasons) { this.failureReasons = failureReasons; }

	/** Get the reasons for failure to score
	 * @return The reasons for failure to score
	 */
	public String getFailureReasons() { return failureReasons; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", schedulability="+schedulability+
			", differentialFunction="+differentialFunction+
			", cumulativeFunction="+cumulativeFunction+
			", failureReasons="+failureReasons+"]";
	}
	// Hand generated code.

} // class def. [SCHEDULABILITY_DONE].

