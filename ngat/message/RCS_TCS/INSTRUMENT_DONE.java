package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: INSTRUMENT_DONE.<br>
 * Instruct TCS which instrument is to be used. This tells the TCS which focal station, rotator, fold position and autoguider to use.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>instrument - instrument to use.</dd>
 * <dd>&nbsp;values: { from config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class INSTRUMENT_DONE extends RCS_TO_TCS_DONE {

	/** Create a INSTRUMENT_DONE with specified id.
	 * @param id The unique id of this INSTRUMENT_DONE.
	 */
	public INSTRUMENT_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [INSTRUMENT_DONE].

