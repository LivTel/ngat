package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDCONFIG_DONE.<br>
 *  Supplies the information required to configure the CCD camera.<br>
 *  Module code: 692200<br>
 * <br>
 *  Example: CCDCONFIG CB 2 340.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>config - null</dd>
 * <dd>&nbsp;values: a valid config for the CCD camera.</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDCONFIG_DONE extends POS_TO_RCS_DONE {

	/** Create a CCDCONFIG_DONE with specified id.
	 * @param id The unique id of this CCDCONFIG_DONE.
	 */
	public CCDCONFIG_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [CCDCONFIG_DONE].

