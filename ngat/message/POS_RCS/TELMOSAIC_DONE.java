package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TELMOSAIC_DONE.<br>
 *  Supplies the information required to perform a mosaic <br>
 *  exposure sequence.<br>
 *  Module code: 692500<br>
 * <br>
 *  Example: TELMOSAIC 35.0 CB 2 3 3 240.0 240.0 10.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>rotation - tangent plane rotation.</dd>
 * <dd>&nbsp;values: 0 to 2*PI</dd>
 * <dd>config - camera configuration</dd>
 * <dd>&nbsp;values: a valid config for the CCD camera</dd>
 * <dd>xCount - number of cells in rotated X direction.</dd>
 * <dd>&nbsp;values: 1 - 5</dd>
 * <dd>yCount - number of cells in rotated Y direction.</dd>
 * <dd>&nbsp;values: 1 - 5</dd>
 * <dd>xOffset - offset in rotated X direction.</dd>
 * <dd>&nbsp;values: 0 - 3600 arcsec</dd>
 * <dd>yOffset - offset in rotated Y direction.</dd>
 * <dd>&nbsp;values: 0 - 3600 arcsec</dd>
 * <dd>exposure - exposure (millis).</dd>
 * <dd>&nbsp;values:  0 < exposure < 7200000</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELMOSAIC_DONE extends POS_TO_RCS_DONE {

	/** Create a TELMOSAIC_DONE with specified id.
	 * @param id The unique id of this TELMOSAIC_DONE.
	 */
	public TELMOSAIC_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TELMOSAIC_DONE].

