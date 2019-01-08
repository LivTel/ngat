package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND: DARKSLIDE.<br>
 * Instructs the TCS to move the darkslide filter in/closed or out/open.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - state to place darkslide filter in.</dd>
 * <dd>&nbsp;values: { OPEN | CLOSE }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class DARKSLIDE extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates the filter is to be slid in.*/
	public static final int CLOSE = 0;

	/** Constant: Indicates the filter is to be slid out.*/
	public static final int OPEN = 1;

	// Variables.

	/** The state to place darkslide filter in.*/
	protected int state;

	/** Create a DARKSLIDE with specified id.
	 * @param id The unique id of this DARKSLIDE.
	 */
	public DARKSLIDE(String id) { super(id); }

	/** Create a DARKSLIDE with specified id and parameters.
	 * @param id The unique id of this DARKSLIDE.
	 * @param state The state to place darkslide filter in.
	 */
	public DARKSLIDE(
	         String id,
	         int state) {
	         super(id);
	           this.state = state;
	         }

	/** Set the state to place darkslide filter in.
	 * @param state The state to place darkslide filter in.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the state to place darkslide filter in.
	 * @return The state to place darkslide filter in.
	 */
	public int getState() { return state; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", state="+state+"]";
	}
	// Hand generated code.

} // class def. [DARKSLIDE].

