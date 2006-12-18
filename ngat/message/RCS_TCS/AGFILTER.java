package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGFILTER.<br>
 * Instructs the TCS to move the autoguider filter in or out.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place autoguider filter in.</dd>
 * <dd>&nbsp;values: { IN | OUT }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGFILTER extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates the filter is to be slid in.*/
	public static final int IN = 0;

	/** Constant: Indicates the filter is to be slid out.*/
	public static final int OUT = 1;

	// Variables.

	/** The state to place autoguider filter in.*/
	protected int state;

	/** Create a AGFILTER with specified id.
	 * @param id The unique id of this AGFILTER.
	 */
	public AGFILTER(String id) { super(id); }

	/** Create a AGFILTER with specified id and parameters.
	 * @param id The unique id of this AGFILTER.
	 * @param state The state to place autoguider filter in.
	 */
	public AGFILTER(
	         String id,
	         int state) {
	         super(id);
	           this.state = state;
	         }

	/** Set the state to place autoguider filter in.
	 * @param state The state to place autoguider filter in.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the state to place autoguider filter in.
	 * @return The state to place autoguider filter in.
	 */
	public int getState() { return state; }

	// Hand generated code.

} // class def. [AGFILTER].

