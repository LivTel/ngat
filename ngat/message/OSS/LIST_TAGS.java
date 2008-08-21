package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: LIST_TAGS.<br>
 * Command: LIST_TAGS<br>
 * Returns a list of TAGS<br>
 * Module code: 703900  <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>tagList - A java.util.List containing the ngat.phase2.TagDescriptors of the Tags</dd>
 * </dl>
 * <br>
 */
public class LIST_TAGS extends TRANSACTION {

	/** Create a LIST_TAGS with specified id.
	 * @param id The unique id of this LIST_TAGS.
	 */
	public LIST_TAGS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [LIST_TAGS].

