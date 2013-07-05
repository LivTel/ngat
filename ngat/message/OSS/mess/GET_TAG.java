package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: GET_TAG.<br>
 * Command: GET_TAG<br>
 * Retreives a TAG from the P2DB. <br>
 * Module code: 705200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagPath - The path used to locate the TAG in the P2DB</dd>
 * <dd>&nbsp;values: Path to a TAG already in the P2DB</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>tag - The ngat.phase2.TAG requested</dd>
 * </dl>
 * <br>
 */
public class GET_TAG extends TRANSACTION {

	// Variables.

	/** The The path used to locate the TAG in the P2DB*/
	protected Path tagPath;

	/** Create a GET_TAG with specified id.
	 * @param id The unique id of this GET_TAG.
	 */
	public GET_TAG(String id) { super(id); }

	/** Create a GET_TAG with specified id and parameters.
	 * @param id The unique id of this GET_TAG.
	 * @param tagPath The The path used to locate the TAG in the P2DB
	 */
	public GET_TAG(
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

} // class def. [GET_TAG].

