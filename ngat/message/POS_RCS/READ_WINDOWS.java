package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: READ_WINDOWS.<br>
 *  Request the current operations scheduled windows<br>
 *  from the RCS. The windows are returned as a<br>
 *  java.util.TreeSet containing the TimeWindows.<br>
 * <br>
 *  Module code: 692600<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>windows - window information</dd>
 * </dl>
 * <br>
 */
public class READ_WINDOWS extends POS_TO_RCS {

	/** Create a READ_WINDOWS with specified id.
	 * @param id The unique id of this READ_WINDOWS.
	 */
	public READ_WINDOWS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [READ_WINDOWS].

