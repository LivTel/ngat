package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SWITCH_MODE.<br>
 *  Request RCS to switch between ENG and AUTO states.<br>
 *  <br>
 *  If the RCS is in ENG state then AUTO will move it to INIT thence<br>
 *  depending on conditions towards operationality.<br>
 * <br>
 *  If the RCS is running operations or waiting on conditions, then ENG<br>
 *  will return it immediately to ENG state.<br>
 * <br>
 *  NOTE: Currently it will not perform any finalization or other tasks<br>
 *        but will abort a running MCA - (awaiting state model changes).<br>
 * <br>
 *  The result of this command will be observable via subsequent ID responses.<br>
 * <br>
 *  Module code: 680700<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - the state to switch to</dd>
 * <dd>&nbsp;values: { ENG | AUTO }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SWITCH_MODE extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS should switch to ENGINEERING state*/
	public static final int ENGINEERING = 680701;

	/** Constant: Indicates that the RCS should switch to AUTOMATIC/INIT state*/
	public static final int AUTOMATIC = 680702;

	/** Constant: Indicates that the mode switch is not currently implemented by the RCS*/
	public static final int NOT_IMPLEMENTED = 680710;

	// Variables.

	/** The the state to switch to*/
	protected int mode;

	/** Create a SWITCH_MODE with specified id.
	 * @param id The unique id of this SWITCH_MODE.
	 */
	public SWITCH_MODE(String id) { super(id); }

	/** Create a SWITCH_MODE with specified id and parameters.
	 * @param id The unique id of this SWITCH_MODE.
	 * @param mode The the state to switch to
	 */
	public SWITCH_MODE(
	         String id,
	         int mode) {
	         super(id);
	           this.mode = mode;
	         }

	/** Set the the state to switch to
	 * @param mode The the state to switch to
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the the state to switch to
	 * @return The the state to switch to
	 */
	public int getMode() { return mode; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", mode="+mode+"]";
	}
	// Hand generated code.

} // class def. [SWITCH_MODE].

