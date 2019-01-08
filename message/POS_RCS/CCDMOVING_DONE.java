package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDMOVING_DONE.<br>
 *  Supplies the information required to slew the telescope to <br>
 *  a moving (Solar System) source, configure the instrument <br>
 *  and take an exposure.<br>
 *  Module code 690300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>srcId - the id of the source to observe</dd>
 * <dd>&nbsp;values: currently {MOON} only</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDMOVING_DONE extends CCDOBSERVE_DONE {

	/** Create a CCDMOVING_DONE with specified id.
	 * @param id The unique id of this CCDMOVING_DONE.
	 */
	public CCDMOVING_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [CCDMOVING_DONE].

