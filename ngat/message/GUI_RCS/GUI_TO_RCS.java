package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GUI_TO_RCS.<br>
 *  Base class for messaging between GUI and RCS.<br>
 * <br>
 *  Module code: 680000<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class GUI_TO_RCS extends COMMAND {

	// Constants.

	/** Constant: Indicates that the command class is unknown*/
	public static final int UNKNOWN_COMMAND = 680001;

	/** Create a GUI_TO_RCS with specified id.
	 * @param id The unique id of this GUI_TO_RCS.
	 */
	public GUI_TO_RCS(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [GUI_TO_RCS].

