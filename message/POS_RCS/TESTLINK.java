package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TESTLINK.<br>
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
public class TESTLINK extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates that the link is down or some sort of internal network fault.*/
	public static final int LINK_DOWN = 691101;

	/** Constant: Indicates that the system is in Planetrium mode.*/
	public static final int PLANETARIUM_MODE = 691102;

	/** Constant: Indicates that the PCA has been overridden by a higher priority MCA.*/
	public static final int OVERRIDDEN = 691103;

	/** Constant: Indicates that the PCA is being initialized*/
	public static final int PLANETARIUM_INITIALIZING = 691104;

	/** Create a TESTLINK with specified id.
	 * @param id The unique id of this TESTLINK.
	 */
	public TESTLINK(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [TESTLINK].

