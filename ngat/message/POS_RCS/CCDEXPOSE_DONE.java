package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDEXPOSE_DONE.<br>
 *  Supplies the information required to perform a CCD camera exposure.<br>
 *  Module code: 692300<br>
 * <br>
 *  Example: CCDEXPOSE 300.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>exposure - exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDEXPOSE_DONE extends POS_TO_RCS_DONE {

	/** Create a CCDEXPOSE_DONE with specified id.
	 * @param id The unique id of this CCDEXPOSE_DONE.
	 */
	public CCDEXPOSE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [CCDEXPOSE_DONE].

