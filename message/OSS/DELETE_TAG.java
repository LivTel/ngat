package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: DELETE_TAG.<br>
 * Command: DELETE_COI_USER<br>
 * Removes a user as co-investigator of proposal.<br>
 * Module code: 704900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagPath - The path used to locate the TAG in the P2DB</dd>
 * <dd>&nbsp;values: Path to a TAG already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>removed - True if the TAG was removed</dd>
 * </dl>
 * <br>
 */
public class DELETE_TAG extends TRANSACTION {

	// Variables.

	/** The The path used to locate the TAG in the P2DB*/
	protected Path tagPath;

	/** Create a DELETE_TAG with specified id.
	 * @param id The unique id of this DELETE_TAG.
	 */
	public DELETE_TAG(String id) { super(id); }

	/** Create a DELETE_TAG with specified id and parameters.
	 * @param id The unique id of this DELETE_TAG.
	 * @param tagPath The The path used to locate the TAG in the P2DB
	 */
	public DELETE_TAG(
	         String id,
	         Path tagPath) {
	         super(id);
	           this.tagPath = tagPath;
	         }

	/** Set the The path used to locate the TAG in the P2DB
	 * @param tagPath The The path used to locate the TAG in the P2DB
	 */
	public void setTagPath(Path tagPath) { this.tagPath = tagPath; }

	/** Get the The path used to locate the TAG in the P2DB
	 * @return The The path used to locate the TAG in the P2DB
	 */
	public Path getTagPath() { return tagPath; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tagPath="+tagPath+"]";
	}
	// Hand generated code.

} // class def. [DELETE_TAG].

