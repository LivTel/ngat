package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: UT1UTC.<br>
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
public class UT1UTC extends RCS_TO_TCS {

	// Variables.

	/** The value to apply in UT1-UTC correction.*/
	protected int value;

	/** Create a UT1UTC with specified id.
	 * @param id The unique id of this UT1UTC.
	 */
	public UT1UTC(String id) { super(id); }

	/** Create a UT1UTC with specified id and parameters.
	 * @param id The unique id of this UT1UTC.
	 * @param value The value to apply in UT1-UTC correction.
	 */
	public UT1UTC(
	         String id,
	         int value) {
	         super(id);
	           this.value = value;
	         }

	/** Set the value to apply in UT1-UTC correction.
	 * @param value The value to apply in UT1-UTC correction.
	 */
	public void setValue(int value) { this.value = value; }

	/** Get the value to apply in UT1-UTC correction.
	 * @return The value to apply in UT1-UTC correction.
	 */
	public int getValue() { return value; }

	// Hand generated code.

} // class def. [UT1UTC].

