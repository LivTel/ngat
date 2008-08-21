package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: CHECK_GROUP.<br>
 * Command: CHECK_GROUP<br>
 * Returns the scheduling/execution history of a group.<br>
 * Module code: 700900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>groupPath - null</dd>
 * <dd>&nbsp;values: The path used to identify the Group in the P2DB</dd>
 * <dd>startTime - The start of the time period to check whether Group was done</dd>
 * <dd>&nbsp;values: Time in millis since epoch</dd>
 * <dd>endTime - The end of the time period to check whether Group was done</dd>
 * <dd>&nbsp;values: Time in millis since epoch</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>history - A java.util.List containing zero or more ngat.phase2.Group.History objects</dd>
 * </dl>
 * <br>
 */
public class CHECK_GROUP extends TRANSACTION {

	// Variables.

	/** The null*/
	protected Path groupPath;

	/** The The start of the time period to check whether Group was done*/
	protected long startTime;

	/** The The end of the time period to check whether Group was done*/
	protected long endTime;

	/** Create a CHECK_GROUP with specified id.
	 * @param id The unique id of this CHECK_GROUP.
	 */
	public CHECK_GROUP(String id) { super(id); }

	/** Create a CHECK_GROUP with specified id and parameters.
	 * @param id The unique id of this CHECK_GROUP.
	 * @param groupPath The null
	 * @param startTime The The start of the time period to check whether Group was done
	 * @param endTime The The end of the time period to check whether Group was done
	 */
	public CHECK_GROUP(
	         String id,
	         Path groupPath,
	         long startTime,
	         long endTime) {
	         super(id);
	           this.groupPath = groupPath;
	           this.startTime = startTime;
	           this.endTime = endTime;
	         }

	/** Set the null
	 * @param groupPath The null
	 */
	public void setGroupPath(Path groupPath) { this.groupPath = groupPath; }

	/** Get the null
	 * @return The null
	 */
	public Path getGroupPath() { return groupPath; }

	/** Set the The start of the time period to check whether Group was done
	 * @param startTime The The start of the time period to check whether Group was done
	 */
	public void setStartTime(long startTime) { this.startTime = startTime; }

	/** Get the The start of the time period to check whether Group was done
	 * @return The The start of the time period to check whether Group was done
	 */
	public long getStartTime() { return startTime; }

	/** Set the The end of the time period to check whether Group was done
	 * @param endTime The The end of the time period to check whether Group was done
	 */
	public void setEndTime(long endTime) { this.endTime = endTime; }

	/** Get the The end of the time period to check whether Group was done
	 * @return The The end of the time period to check whether Group was done
	 */
	public long getEndTime() { return endTime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", groupPath="+groupPath+
			", startTime="+startTime+
			", endTime="+endTime+"]";
	}
	// Hand generated code.

} // class def. [CHECK_GROUP].

