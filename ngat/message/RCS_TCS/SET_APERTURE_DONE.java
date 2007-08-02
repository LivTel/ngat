package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND_DONE: SET_APERTURE_DONE.<br>
 * Instruct the TCS to setup an aperture offset.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>number - instrument offset number (associated with an instrument)</dd>
 * <dd>&nbsp;values: 0-max</dd>
 * <dd>offsetX - focal plane offset in X.</dd>
 * <dd>&nbsp;values: +/- max mm</dd>
 * <dd>offsetY - focal plane offset in Y.</dd>
 * <dd>&nbsp;values: +/- max mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_APERTURE_DONE extends RCS_TO_TCS_DONE {

	/** Create a SET_APERTURE_DONE with specified id.
	 * @param id The unique id of this SET_APERTURE_DONE.
	 */
	public SET_APERTURE_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [SET_APERTURE_DONE].

