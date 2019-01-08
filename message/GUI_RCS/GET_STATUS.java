package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GET_STATUS.<br>
 *  Request status information.<br>
 * <br>
 *  Module code: 680100<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>category - status category.</dd>
 * <dd>&nbsp;values: a valid status category</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - status information.</dd>
 * </dl>
 * <br>
 */
public class GET_STATUS extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the category was not recognized.*/
	public static final int UNKNOWN_CATEGORY = 680101;

	/** Constant: Indicates that the status information is not currently available.*/
	public static final int NOT_AVAILABLE = 680102;

	/** Constant: Indicates that the status information is out of date.*/
	public static final int EXPIRED = 680103;

	// Variables.

	/** The status category.*/
	protected String category;

	/** Create a GET_STATUS with specified id.
	 * @param id The unique id of this GET_STATUS.
	 */
	public GET_STATUS(String id) { super(id); }

	/** Create a GET_STATUS with specified id and parameters.
	 * @param id The unique id of this GET_STATUS.
	 * @param category The status category.
	 */
	public GET_STATUS(
	         String id,
	         String category) {
	         super(id);
	           this.category = category;
	         }

	/** Set the status category.
	 * @param category The status category.
	 */
	public void setCategory(String category) { this.category = category; }

	/** Get the status category.
	 * @return The status category.
	 */
	public String getCategory() { return category; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", category="+category+"]";
	}
	// Hand generated code.

} // class def. [GET_STATUS].

