package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TELOFFSET_DONE.<br>
 *  Supplies the information required to offset the telescope from its current position.<br>
 *  The rotator is not moved by a rotation parameter it merely<br>
 *  Module code: 692100<br>
 * <br>
 *  Example: TELOFFSET TANGENT 24.0 24.0 35.0<br>
 * <br>
 *  Example: TELOFFSET SKY 50.0 45.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>raOffset - offset in RA.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>decOffset - offset in declination.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>xOffset - offset in X direction of rotated tangent plane.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>yOffset - offset in Y direction of rotated tangent plane.</dd>
 * <dd>&nbsp;values: -3600 to 3600 arcsec</dd>
 * <dd>rotation - tangent plane rotation (from Pole cwise).</dd>
 * <dd>&nbsp;values: 0 to 2*PI</dd>
 * <dd>mode - indicates whether sky or tangent plane coordinates.</dd>
 * <dd>&nbsp;values: { TANGENT | SKY }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELOFFSET_DONE extends POS_TO_RCS_DONE {

	/** Create a TELOFFSET_DONE with specified id.
	 * @param id The unique id of this TELOFFSET_DONE.
	 */
	public TELOFFSET_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELOFFSET_DONE].

