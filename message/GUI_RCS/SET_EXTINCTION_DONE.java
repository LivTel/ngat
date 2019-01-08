package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: SET_EXTINCTION_DONE.<br>
 *  Set the current seeing used by the OSS.<br>
 * <br>
 *  Module code: 681900<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>extinction - extinction value</dd>
 * <dd>&nbsp;values: { PHOTOMETRIC |  SPECTROSCOPIC }</dd>
 * <dd>source -  extinction source</dd>
 * <dd>&nbsp;values: { MANUAL | EXTERNAL}</dd>
 * <dd>sourceName -  source provider identity</dd>
 * <dd>&nbsp;values: a valid source name</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_EXTINCTION_DONE extends GUI_TO_RCS_DONE {

	/** Create a SET_EXTINCTION_DONE with specified id.
	 * @param id The unique id of this SET_EXTINCTION_DONE.
	 */
	public SET_EXTINCTION_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SET_EXTINCTION_DONE].

