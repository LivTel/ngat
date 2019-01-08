package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SWITCH_MODE_DONE.<br>
 *  Request RCS to switch between ENG and AUTO states.<br>
 *  <br>
 *  If the RCS is in ENG state then AUTO will move it to INIT thence<br>
 *  depending on conditions towards operationality.<br>
 * <br>
 *  If the RCS is running operations or waiting on conditions, then ENG<br>
 *  will return it immediately to ENG state.<br>
 * <br>
 *  NOTE: Currently it will not perform any finalization or other tasks<br>
 *        but will abort a running MCA - (awaiting state model changes).<br>
 * <br>
 *  The result of this command will be observable via subsequent ID responses.<br>
 * <br>
 *  Module code: 680700<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - the state to switch to</dd>
 * <dd>&nbsp;values: { ENG | AUTO }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SWITCH_MODE_DONE extends GUI_TO_RCS_DONE {

	/** Create a SWITCH_MODE_DONE with specified id.
	 * @param id The unique id of this SWITCH_MODE_DONE.
	 */
	public SWITCH_MODE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SWITCH_MODE_DONE].

