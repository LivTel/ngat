package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_TAG.<br>
 * Command: ADD_TAG<br>
 * Add the TAG to the DB root.<br>
 * <br>
 * Module code: 704500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tag - The Tag to add</dd>
 * <dd>&nbsp;values: A valid but not neccessarily consistent or complete Tag</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_TAG extends TRANSACTION {

	// Variables.

	/** The The Tag to add*/
	protected Tag tag;

	/** Create a ADD_TAG with specified id.
	 * @param id The unique id of this ADD_TAG.
	 */
	public ADD_TAG(String id) { super(id); }

	/** Create a ADD_TAG with specified id and parameters.
	 * @param id The unique id of this ADD_TAG.
	 * @param tag The The Tag to add
	 */
	public ADD_TAG(
	         String id,
	         Tag tag) {
	         super(id);
	           this.tag = tag;
	         }

	/** Set the The Tag to add
	 * @param tag The The Tag to add
	 */
	public void setTag(Tag tag) { this.tag = tag; }

	/** Get the The Tag to add
	 * @return The The Tag to add
	 */
	public Tag getTag() { return tag; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", tag="+tag+"]";
	}
	// Hand generated code.

} // class def. [ADD_TAG].

