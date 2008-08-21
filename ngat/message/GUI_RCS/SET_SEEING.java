package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SET_SEEING.<br>
 *  Set the current seeing used by the OSS.<br>
 * <br>
 *  Module code: 681700<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>seeing - seeing value</dd>
 * <dd>&nbsp;values: { SEEING_POOR |  SEEING_AVERAGE | SEEING_EXCELLENT }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_SEEING extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the seeing is currently POOR */
	public static final int SEEING_POOR = 681701;

	/** Constant: Indicates taht the seeing is AVERAGE*/
	public static final int SEEING_AVERAGE = 681702;

	/** Constant: Indicates taht the seeing is EXCELLENT*/
	public static final int SEEING_EXCELLENT = 681703;

	// Variables.

	/** The seeing value*/
	protected int seeing;

	/** Create a SET_SEEING with specified id.
	 * @param id The unique id of this SET_SEEING.
	 */
	public SET_SEEING(String id) { super(id); }

	/** Create a SET_SEEING with specified id and parameters.
	 * @param id The unique id of this SET_SEEING.
	 * @param seeing The seeing value
	 */
	public SET_SEEING(
	         String id,
	         int seeing) {
	         super(id);
	           this.seeing = seeing;
	         }

	/** Set the seeing value
	 * @param seeing The seeing value
	 */
	public void setSeeing(int seeing) { this.seeing = seeing; }

	/** Get the seeing value
	 * @return The seeing value
	 */
	public int getSeeing() { return seeing; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", seeing="+seeing+"]";
	}
	// Hand generated code.

} // class def. [SET_SEEING].

