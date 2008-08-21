package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: LIST_TAGS_DONE.<br>
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
public class LIST_TAGS_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The A java.util.List containing the ngat.phase2.TagDescriptors of the Tags*/
	protected List tagList;

	/** Create a LIST_TAGS_DONE with specified id.
	 * @param id The unique id of this LIST_TAGS_DONE.
	 */
	public LIST_TAGS_DONE (String id) { super(id); }

	/** Set the A java.util.List containing the ngat.phase2.TagDescriptors of the Tags
	 * @param tagList The A java.util.List containing the ngat.phase2.TagDescriptors of the Tags.
	 */
	public void setTagList(List tagList) { this.tagList = tagList; }

	/** Get the A java.util.List containing the ngat.phase2.TagDescriptors of the Tags
	 * @return The A java.util.List containing the ngat.phase2.TagDescriptors of the Tags
	 */
	public List getTagList() { return tagList; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tagList="+tagList+"]";
	}
	// Hand generated code.

} // class def. [LIST_TAGS_DONE].

