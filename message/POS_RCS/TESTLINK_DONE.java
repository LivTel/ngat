package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: TESTLINK_DONE.<br>
 *  Tests the link between the client (Planetarium) and server (RCS)<br>
 *  and whether the RCS is actually in Planetarium Mode / functioning.<br>
 *  Module code: 691100<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>returnCode - { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }</dd>
 * </dl>
 * <br>
 */
public class TESTLINK_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }*/
	protected int returnCode;

	/** Create a TESTLINK_DONE with specified id.
	 * @param id The unique id of this TESTLINK_DONE.
	 */
	public TESTLINK_DONE (String id) { super(id); }

	/** Set the { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }
	 * @param returnCode The { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }.
	 */
	public void setReturnCode(int returnCode) { this.returnCode = returnCode; }

	/** Get the { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }
	 * @return The { PLANETARIUM_MODE | LINK_DOWN | NOT_PLANETARIUM_MODE | OVERRIDDEN }
	 */
	public int getReturnCode() { return returnCode; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", returnCode="+returnCode+"]";
	}
	// Hand generated code.

} // class def. [TESTLINK_DONE].

