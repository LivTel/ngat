package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: ISS_SET_HEADERS_DONE.<br>
 *  Set some FITS headers when observing in manual mode.<br>
 * <br>
 *  Headers are: TAG, USER, PROP, GRP and OBS IDs.<br>
 * <br>
 *  Module code: 681400<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>tagId - TAG Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>userId - User Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>proposalId - Proposal Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>groupId - Group Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>obsId - Observation Id</dd>
 * <dd>&nbsp;values: A valid ID</dd>
 * <dd>manual - true if this is to set manual headers, false to reset to automatic</dd>
 * <dd>&nbsp;values: {T|F}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ISS_SET_HEADERS_DONE extends GUI_TO_RCS_DONE {

	/** Create a ISS_SET_HEADERS_DONE with specified id.
	 * @param id The unique id of this ISS_SET_HEADERS_DONE.
	 */
	public ISS_SET_HEADERS_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ISS_SET_HEADERS_DONE].

