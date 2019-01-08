package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: OFFBY_DONE.<br>
 * Instruct the TCS to make a position offset.<br>
 * 1).When mode is set to ARC offsets are made in the tangent plane.<br>
 * 2).When mode is set to TIME offsets are made with constant<br>
 *    displacements in ra and dec whatever the altitude.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - operational mode.</dd>
 * <dd>&nbsp;values: { ARC | TIME }</dd>
 * <dd>offsetRA - offset in RA.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dd>offsetDec - offset in dec.</dd>
 * <dd>&nbsp;values: +/- 1 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class OFFBY_DONE extends RCS_TO_TCS_DONE {

	/** Create a OFFBY_DONE with specified id.
	 * @param id The unique id of this OFFBY_DONE.
	 */
	public OFFBY_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [OFFBY_DONE].

