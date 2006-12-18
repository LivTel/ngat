package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDEXPOSE.<br>
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
public class CCDEXPOSE extends POS_TO_RCS {

	// Variables.

	/** The exposure (millis).*/
	protected int exposure;

	/** Create a CCDEXPOSE with specified id.
	 * @param id The unique id of this CCDEXPOSE.
	 */
	public CCDEXPOSE(String id) { super(id); }

	/** Create a CCDEXPOSE with specified id and parameters.
	 * @param id The unique id of this CCDEXPOSE.
	 * @param exposure The exposure (millis).
	 */
	public CCDEXPOSE(
	         String id,
	         int exposure) {
	         super(id);
	           this.exposure = exposure;
	         }

	/** Set the exposure (millis).
	 * @param exposure The exposure (millis).
	 */
	public void setExposure(int exposure) { this.exposure = exposure; }

	/** Get the exposure (millis).
	 * @return The exposure (millis).
	 */
	public int getExposure() { return exposure; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", exposure="+exposure+"]";
	}
	// Hand generated code.

} // class def. [CCDEXPOSE].

