package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: SCHEDULE_DONE.<br>
 * Command: SCHEDULE<br>
 * Sent by RCS in robotic mode to request the next Group of Observations<br>
 * to perform. The Group returned may be Fixed or Flexible. The request may <br>
 * specify parameters (maxIdle and latestTime) to constrain the range of the<br>
 * search performed by the Scheduling algorithm.<br>
 * Module code: 702900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>maxIdle - Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  </dd>
 * <dd>&nbsp;values: > 0 (millis)</dd>
 * <dd>latestTime - Specifies the latest time by which the returned observation sequence must be expected to have been completed by.</dd>
 * <dd>&nbsp;values: a future time</dd>
 * <dd>deltaTime - Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.</dd>
 * <dd>&nbsp;values: > 0 (millis)</dd>
 * <dd>schedId - Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  </dd>
 * <dd>&nbsp;values: { OPTIMAL | FAST | LOOKAHEAD | CACHE | others }</dd>
 * <dd>lock - Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>lockOwner - Identifies the client who has requested the lock.</dd>
 * <dd>&nbsp;values: A valid lock owner ID</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>group - The best (optimal) Group of observations to perform under current constraints or null.</dd>
 * <dd>score - The score acheived by this Group</dd>
 * <dd>latestTime - The latest time by which this Group must have been completed (e.g. target sets)</dd>
 * <dd>nominalTime - The nominal execution time of the Group</dd>
 * </dl>
 * <br>
 */
public class SCHEDULE_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The best (optimal) Group of observations to perform under current constraints or null.*/
	protected Group group;

	/** The The score acheived by this Group*/
	protected double score;

	/** The The latest time by which this Group must have been completed (e.g. target sets)*/
	protected long latestTime;

	/** The The nominal execution time of the Group*/
	protected long nominalTime;

	/** Create a SCHEDULE_DONE with specified id.
	 * @param id The unique id of this SCHEDULE_DONE.
	 */
	public SCHEDULE_DONE (String id) { super(id); }

	/** Set the The best (optimal) Group of observations to perform under current constraints or null.
	 * @param group The The best (optimal) Group of observations to perform under current constraints or null..
	 */
	public void setGroup(Group group) { this.group = group; }

	/** Get the The best (optimal) Group of observations to perform under current constraints or null.
	 * @return The The best (optimal) Group of observations to perform under current constraints or null.
	 */
	public Group getGroup() { return group; }

	/** Set the The score acheived by this Group
	 * @param score The The score acheived by this Group.
	 */
	public void setScore(double score) { this.score = score; }

	/** Get the The score acheived by this Group
	 * @return The The score acheived by this Group
	 */
	public double getScore() { return score; }

	/** Set the The latest time by which this Group must have been completed (e.g. target sets)
	 * @param latestTime The The latest time by which this Group must have been completed (e.g. target sets).
	 */
	public void setLatestTime(long latestTime) { this.latestTime = latestTime; }

	/** Get the The latest time by which this Group must have been completed (e.g. target sets)
	 * @return The The latest time by which this Group must have been completed (e.g. target sets)
	 */
	public long getLatestTime() { return latestTime; }

	/** Set the The nominal execution time of the Group
	 * @param nominalTime The The nominal execution time of the Group.
	 */
	public void setNominalTime(long nominalTime) { this.nominalTime = nominalTime; }

	/** Get the The nominal execution time of the Group
	 * @return The The nominal execution time of the Group
	 */
	public long getNominalTime() { return nominalTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", group="+group+
			", score="+score+
			", latestTime="+latestTime+
			", nominalTime="+nominalTime+"]";
	}
	// Hand generated code.

} // class def. [SCHEDULE_DONE].

