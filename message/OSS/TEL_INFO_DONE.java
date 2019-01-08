package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: TEL_INFO_DONE.<br>
 * Command: TEL_INFO<br>
 * Used to update the OSS with information about the current telescope position<br>
 * and availability of autoguider (+ others later).<br>
 * Module code: 703100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>telescopeAzimuth - The current azimuth of the telescope (rads)</dd>
 * <dd>&nbsp;values: 0 <= az <= 2*PI</dd>
 * <dd>telescopeAltitude - The current altitude of the telescope (rads)</dd>
 * <dd>&nbsp;values: 0 <= alt <= PI/2</dd>
 * <dd>autoguiderAvailable - True if the autoguider is currently available</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>azNegLimit - Telescope azimuth negative wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>azPosLimit - Telescope azimuth positive wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>altLowLimit - Telescope altitude low limit (rads)</dd>
 * <dd>&nbsp;values: [0, Pi/2]</dd>
 * <dd>altHighLimit - Telescope altitude high limit (rads)</dd>
 * <dd>&nbsp;values: [0, Pi/2]</dd>
 * <dd>rotNegLimit - Telescope rotator negative wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>rotPosLimit - Telescope rotator positive wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TEL_INFO_DONE extends TRANSACTION_DONE {

	/** Create a TEL_INFO_DONE with specified id.
	 * @param id The unique id of this TEL_INFO_DONE.
	 */
	public TEL_INFO_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TEL_INFO_DONE].

