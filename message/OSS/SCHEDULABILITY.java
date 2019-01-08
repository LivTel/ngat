package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: SCHEDULABILITY.<br>
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
public class SCHEDULABILITY extends TRANSACTION {

	// Variables.

	/** The the group whose schedulability we want to test.*/
	protected Group group;

	/** The the start of the period we want to check*/
	protected long start;

	/** The the end of the period we want to check*/
	protected long end;

	/** The the time resolution*/
	protected long resolution;

	/** Create a SCHEDULABILITY with specified id.
	 * @param id The unique id of this SCHEDULABILITY.
	 */
	public SCHEDULABILITY(String id) { super(id); }

	/** Create a SCHEDULABILITY with specified id and parameters.
	 * @param id The unique id of this SCHEDULABILITY.
	 * @param group The the group whose schedulability we want to test.
	 * @param start The the start of the period we want to check
	 * @param end The the end of the period we want to check
	 * @param resolution The the time resolution
	 */
	public SCHEDULABILITY(
	         String id,
	         Group group,
	         long start,
	         long end,
	         long resolution) {
	         super(id);
	           this.group = group;
	           this.start = start;
	           this.end = end;
	           this.resolution = resolution;
	         }

	/** Set the the group whose schedulability we want to test.
	 * @param group The the group whose schedulability we want to test.
	 */
	public void setGroup(Group group) { this.group = group; }

	/** Get the the group whose schedulability we want to test.
	 * @return The the group whose schedulability we want to test.
	 */
	public Group getGroup() { return group; }

	/** Set the the start of the period we want to check
	 * @param start The the start of the period we want to check
	 */
	public void setStart(long start) { this.start = start; }

	/** Get the the start of the period we want to check
	 * @return The the start of the period we want to check
	 */
	public long getStart() { return start; }

	/** Set the the end of the period we want to check
	 * @param end The the end of the period we want to check
	 */
	public void setEnd(long end) { this.end = end; }

	/** Get the the end of the period we want to check
	 * @return The the end of the period we want to check
	 */
	public long getEnd() { return end; }

	/** Set the the time resolution
	 * @param resolution The the time resolution
	 */
	public void setResolution(long resolution) { this.resolution = resolution; }

	/** Get the the time resolution
	 * @return The the time resolution
	 */
	public long getResolution() { return resolution; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", group="+group+
			", start="+start+
			", end="+end+
			", resolution="+resolution+"]";
	}
	// Hand generated code.

} // class def. [SCHEDULABILITY].

