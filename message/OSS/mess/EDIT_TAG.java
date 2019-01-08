package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: EDIT_TAG.<br>
 * Command: EDIT_TAG<br>
 * Modify a stored TAG's accounting info<br>
 * <br>
 * Module code: 705000<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tag - The Tag to edit</dd>
 * <dd>&nbsp;values: A valid and complete Tag</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class EDIT_TAG extends TRANSACTION {

	// Variables.

	/** The The Tag to edit*/
	protected Tag tag;

	/** Create a EDIT_TAG with specified id.
	 * @param id The unique id of this EDIT_TAG.
	 */
	public EDIT_TAG(String id) { super(id); }

	/** Create a EDIT_TAG with specified id and parameters.
	 * @param id The unique id of this EDIT_TAG.
	 * @param tag The The Tag to edit
	 */
	public EDIT_TAG(
	         String id,
	         Tag tag) {
	         super(id);
	           this.tag = tag;
	         }

	/** Set the The Tag to edit
	 * @param tag The The Tag to edit
	 */
	public void setTag(Tag tag) { this.tag = tag; }

	/** Get the The Tag to edit
	 * @return The The Tag to edit
	 */
	public Tag getTag() { return tag; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tag="+tag+"]";
	}
	// Hand generated code.

} // class def. [EDIT_TAG].

