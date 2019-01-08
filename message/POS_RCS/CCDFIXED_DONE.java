package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDFIXED_DONE.<br>
 *  Supplies the information required to slew the telescope to a fixed (ExtraSolar) source<br>
 *  configure the instrument and take an exposure.<br>
 *  Module code 690200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - position to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.astrometry.Position - ie. a (RA,dec) pair</dd>
 * <dd>sourceId - position to slew to.</dd>
 * <dd>&nbsp;values: a valid Id for the Target.</dd>
 * <dd>sourceType - class/type of target for FITS headers</dd>
 * <dd>&nbsp;values: a valid target class</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDFIXED_DONE extends CCDOBSERVE_DONE {

	/** Create a CCDFIXED_DONE with specified id.
	 * @param id The unique id of this CCDFIXED_DONE.
	 */
	public CCDFIXED_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [CCDFIXED_DONE].

