package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND_DONE: APERTURE_DONE.<br>
 * Instruct the TCS to apply an aperture offset<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>number - number of stored aperture offset</dd>
 * <dd>&nbsp;values: 0-max</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class APERTURE_DONE extends RCS_TO_TCS_DONE {

	/** Create a APERTURE_DONE with specified id.
	 * @param id The unique id of this APERTURE_DONE.
	 */
	public APERTURE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [APERTURE_DONE].

