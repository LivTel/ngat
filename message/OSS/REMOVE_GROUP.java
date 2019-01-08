package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: REMOVE_GROUP.<br>
 * Command: REMOVE_GROUP<br>
 * Removes a Group from the database. <br>
 * Module code: 702600<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>groupPath - The path used to locate the Group in the P2DB</dd>
 * <dd>&nbsp;values: Path to a valid Group</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class REMOVE_GROUP extends TRANSACTION {

	// Variables.

	/** The The path used to locate the Group in the P2DB*/
	protected Path groupPath;

	/** Create a REMOVE_GROUP with specified id.
	 * @param id The unique id of this REMOVE_GROUP.
	 */
	public REMOVE_GROUP(String id) { super(id); }

	/** Create a REMOVE_GROUP with specified id and parameters.
	 * @param id The unique id of this REMOVE_GROUP.
	 * @param groupPath The The path used to locate the Group in the P2DB
	 */
	public REMOVE_GROUP(
	         String id,
	         Path groupPath) {
	         super(id);
	           this.groupPath = groupPath;
	         }

	/** Set the The path used to locate the Group in the P2DB
	 * @param groupPath The The path used to locate the Group in the P2DB
	 */
	public void setGroupPath(Path groupPath) { this.groupPath = groupPath; }

	/** Get the The path used to locate the Group in the P2DB
	 * @return The The path used to locate the Group in the P2DB
	 */
	public Path getGroupPath() { return groupPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", groupPath="+groupPath+"]";
	}
	// Hand generated code.

} // class def. [REMOVE_GROUP].

