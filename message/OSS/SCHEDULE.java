package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: SCHEDULE.<br>
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
public class SCHEDULE extends TRANSACTION {

	// Constants.

	/** Constant: Indicates Key used by RCS to lock the Group selected*/
	public static final int RCS_KEY = 0xABABABAB;

	/** Constant: Indicates No Groups were available for scheduling under the specified constraints*/
	public static final int NO_SCHEDULABLE_GROUPS = 702901;

	/** Constant: Indicates No FlexibleGroups were available and the idle time before a FixedGroup becomes available is longer than allowed*/
	public static final int IDLE_TIME_TOO_LONG = 702902;

	/** Constant: Indicates The 'next FixedGroup' could not be located for some reason*/
	public static final int FIXED_GROUP_NOT_FOUND = 702903;

	/** Constant: Indicates The nominated scheduler is either not loaded or not known to the OSS*/
	public static final int SCHEDULER_NOT_AVAILABLE = 702904;

	/** Constant: Indicates The next FixedGroup was of the wrong class*/
	public static final int FIXED_GROUP_CLASS_ERROR = 702905;

	// Variables.

	/** The Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  */
	protected long maxIdle;

	/** The Specifies the latest time by which the returned observation sequence must be expected to have been completed by.*/
	protected long latestTime;

	/** The Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.*/
	protected long deltaTime;

	/** The Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  */
	protected String schedId;

	/** The Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.*/
	protected boolean lock;

	/** The Identifies the client who has requested the lock.*/
	protected String lockOwner;

	/** Create a SCHEDULE with specified id.
	 * @param id The unique id of this SCHEDULE.
	 */
	public SCHEDULE(String id) { super(id); }

	/** Create a SCHEDULE with specified id and parameters.
	 * @param id The unique id of this SCHEDULE.
	 * @param maxIdle The Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  
	 * @param latestTime The Specifies the latest time by which the returned observation sequence must be expected to have been completed by.
	 * @param deltaTime The Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.
	 * @param schedId The Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  
	 * @param lock The Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.
	 * @param lockOwner The Identifies the client who has requested the lock.
	 */
	public SCHEDULE(
	         String id,
	         long maxIdle,
	         long latestTime,
	         long deltaTime,
	         String schedId,
	         boolean lock,
	         String lockOwner) {
	         super(id);
	           this.maxIdle = maxIdle;
	           this.latestTime = latestTime;
	           this.deltaTime = deltaTime;
	           this.schedId = schedId;
	           this.lock = lock;
	           this.lockOwner = lockOwner;
	         }

	/** Set the Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  
	 * @param maxIdle The Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  
	 */
	public void setMaxIdle(long maxIdle) { this.maxIdle = maxIdle; }

	/** Get the Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  
	 * @return The Specifies the longest period the caller (RCS) wants to remain idle if no Flexible Groups can be scheduled at this time. It is the longest period between NOW and the start of the next Fixed (or Sliding) Group that can be returned for scheduling. If no Groups can satisfy the constraint, the RCS may carry out Calibration/Focus etc before asking for another attempt at scheduling.  
	 */
	public long getMaxIdle() { return maxIdle; }

	/** Set the Specifies the latest time by which the returned observation sequence must be expected to have been completed by.
	 * @param latestTime The Specifies the latest time by which the returned observation sequence must be expected to have been completed by.
	 */
	public void setLatestTime(long latestTime) { this.latestTime = latestTime; }

	/** Get the Specifies the latest time by which the returned observation sequence must be expected to have been completed by.
	 * @return The Specifies the latest time by which the returned observation sequence must be expected to have been completed by.
	 */
	public long getLatestTime() { return latestTime; }

	/** Set the Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.
	 * @param deltaTime The Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.
	 */
	public void setDeltaTime(long deltaTime) { this.deltaTime = deltaTime; }

	/** Get the Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.
	 * @return The Specifies the time difference to add to the current time (now) to give an initial float period for the selected group's start time - i.e. the selected Group must be able to start any time between <i>now</i> and <i>now+deltaTime</i>.
	 */
	public long getDeltaTime() { return deltaTime; }

	/** Set the Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  
	 * @param schedId The Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  
	 */
	public void setSchedId(String schedId) { this.schedId = schedId; }

	/** Get the Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  
	 * @return The Identifies the scheduler which is to be used - it is up to the client and server to agree on what constitute valid scheduler IDs.  
	 */
	public String getSchedId() { return schedId; }

	/** Set the Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.
	 * @param lock The Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.
	 */
	public void setLock(boolean lock) { this.lock = lock; }

	/** Get the Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.
	 * @return The Determines whether the returned Group should be locked after fetching - This is normally true for robotic operations - but can be set false to allow simulations to be performed on data while leaving the records accessable to users.
	 */
	public boolean getLock() { return lock; }

	/** Set the Identifies the client who has requested the lock.
	 * @param lockOwner The Identifies the client who has requested the lock.
	 */
	public void setLockOwner(String lockOwner) { this.lockOwner = lockOwner; }

	/** Get the Identifies the client who has requested the lock.
	 * @return The Identifies the client who has requested the lock.
	 */
	public String getLockOwner() { return lockOwner; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", maxIdle="+maxIdle+
			", latestTime="+latestTime+
			", deltaTime="+deltaTime+
			", schedId="+schedId+
			", lock="+lock+
			", lockOwner="+lockOwner+"]";
	}
	// Hand generated code.

} // class def. [SCHEDULE].

