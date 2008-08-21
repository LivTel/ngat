package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND: MOVE_FOLD.<br>
 * Instruct TCS to move the fold mirror to a specified position.<br>
 * (These keywords may have changed).<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>state - instrument port.</dd>
 * <dd>&nbsp;values: { STOWED | POSITION1-8 }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class MOVE_FOLD extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates place fold into stowed position.*/
	public static final int STOWED = 0;

	/** Constant: Indicates place fold mirror towards port1 .*/
	public static final int POSITION1 = 1;

	/** Constant: Indicates place fold mirror towards port2 .*/
	public static final int POSITION2 = 2;

	/** Constant: Indicates place fold mirror towards port3 .*/
	public static final int POSITION3 = 3;

	/** Constant: Indicates place fold mirror towards port4 .*/
	public static final int POSITION4 = 4;

	/** Constant: Indicates place fold mirror towards port5 .*/
	public static final int POSITION5 = 5;

	/** Constant: Indicates place fold mirror towards port6 .*/
	public static final int POSITION6 = 6;

	/** Constant: Indicates place fold mirror towards port7 .*/
	public static final int POSITION7 = 7;

	/** Constant: Indicates place fold mirror towards port8 .*/
	public static final int POSITION8 = 8;

	// Variables.

	/** The instrument port.*/
	protected int state;

	/** Create a MOVE_FOLD with specified id.
	 * @param id The unique id of this MOVE_FOLD.
	 */
	public MOVE_FOLD(String id) { super(id); }

	/** Create a MOVE_FOLD with specified id and parameters.
	 * @param id The unique id of this MOVE_FOLD.
	 * @param state The instrument port.
	 */
	public MOVE_FOLD(
	         String id,
	         int state) {
	         super(id);
	           this.state = state;
	         }

	/** Set the instrument port.
	 * @param state The instrument port.
	 */
	public void setState(int state) { this.state = state; }

	/** Get the instrument port.
	 * @return The instrument port.
	 */
	public int getState() { return state; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", state="+state+"]";
	}
	// Hand generated code.

} // class def. [MOVE_FOLD].

