package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND_DONE: UT1UTC_DONE.<br>
 * Instructs TCS to set the current UT1-UTC correction within pointing calculations.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>value - value to apply in UT1-UTC correction.</dd>
 * <dd>&nbsp;values: -1.000000 to + 1.000000 secs</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class UT1UTC_DONE extends RCS_TO_TCS_DONE {

	/** Create a UT1UTC_DONE with specified id.
	 * @param id The unique id of this UT1UTC_DONE.
	 */
	public UT1UTC_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [UT1UTC_DONE].

