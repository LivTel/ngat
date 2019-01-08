package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: GET_TAG_DONE.<br>
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
public class GET_TAG_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The ngat.phase2.TAG requested*/
	protected Tag tag;

	/** Create a GET_TAG_DONE with specified id.
	 * @param id The unique id of this GET_TAG_DONE.
	 */
	public GET_TAG_DONE (String id) { super(id); }

	/** Set the The ngat.phase2.TAG requested
	 * @param tag The The ngat.phase2.TAG requested.
	 */
	public void setTag(Tag tag) { this.tag = tag; }

	/** Get the The ngat.phase2.TAG requested
	 * @return The The ngat.phase2.TAG requested
	 */
	public Tag getTag() { return tag; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tag="+tag+"]";
	}
	// Hand generated code.

} // class def. [GET_TAG_DONE].

