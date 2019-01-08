package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GET_SEEING_DONE.<br>
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
public class GET_SEEING_DONE extends GUI_TO_RCS_DONE {

	// Variables.

    /** Contains the seeing samples.*/
    private Vector seeingData;


	/** Create a GET_SEEING_DONE with specified id.
	 * @param id The unique id of this GET_STATUS_DONE.
	 */
	public GET_SEEING_DONE (String id) { super(id); }

    public Vector getSeeingData() { return seeingData;}

    public void setSeeingData(Vector seeingData) {
	this.seeingData = seeingData;
    }
    
	// Hand generated code.

} // class def. [GET_STATUS_DONE].

