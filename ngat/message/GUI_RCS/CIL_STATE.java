package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: CIL_STATE.<br>
 *  Request the RCS to bind/release CIL port.<br>
 * <br>
 *  Module code: 681200<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>option - null</dd>
 * <dd>&nbsp;values: indicates whether to bind or relase the CIL port</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>host - destination host</dd>
 * <dd>sendPort - port RCS is sending from</dd>
 * <dd>destPort - port RCS is sending to</dd>
 * <dd>active - whether the socket is active (or closed)</dd>
 * </dl>
 * <br>
 */
public class CIL_STATE extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the RCS should bind to the CIL port*/
	public static final int BIND = 681201;

	/** Constant: Indicates that the RCS should release the CIL port*/
	public static final int RELEASE = 681202;

	/** Constant: Indicates that the CIL status should be returned*/
	public static final int SHOW = 681203;

	/** Constant: Indicates that the bind failed.*/
	public static final int BIND_ERROR = 681201;

	/** Constant: Indicates that the relase failed - bizarrely*/
	public static final int RELEASE_ERROR = 681202;

	// Variables.

	/** The null*/
	protected int option;

	/** Create a CIL_STATE with specified id.
	 * @param id The unique id of this CIL_STATE.
	 */
	public CIL_STATE(String id) { super(id); }

	/** Create a CIL_STATE with specified id and parameters.
	 * @param id The unique id of this CIL_STATE.
	 * @param option The null
	 */
	public CIL_STATE(
	         String id,
	         int option) {
	         super(id);
	           this.option = option;
	         }

	/** Set the null
	 * @param option The null
	 */
	public void setOption(int option) { this.option = option; }

	/** Get the null
	 * @return The null
	 */
	public int getOption() { return option; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", option="+option+"]";
	}
	// Hand generated code.

} // class def. [CIL_STATE].

