package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: UNWRAP.<br>
 * Instructs TCS to unwrap the specified mechanism if possible.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mechanism - mechanism to attempt to unwrap.</dd>
 * <dd>&nbsp;values: { AZIMUTH | ROTATOR }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class UNWRAP extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates azimuth axis whould be unwrapped.*/
	public static final int AZIMUTH = 0;

	/** Constant: Indicates rotatot axis should be unwrapped.*/
	public static final int ROTATOR = 1;

	// Variables.

	/** The mechanism to attempt to unwrap.*/
	protected int mechanism;

	/** Create a UNWRAP with specified id.
	 * @param id The unique id of this UNWRAP.
	 */
	public UNWRAP(String id) { super(id); }

	/** Create a UNWRAP with specified id and parameters.
	 * @param id The unique id of this UNWRAP.
	 * @param mechanism The mechanism to attempt to unwrap.
	 */
	public UNWRAP(
	         String id,
	         int mechanism) {
	         super(id);
	           this.mechanism = mechanism;
	         }

	/** Set the mechanism to attempt to unwrap.
	 * @param mechanism The mechanism to attempt to unwrap.
	 */
	public void setMechanism(int mechanism) { this.mechanism = mechanism; }

	/** Get the mechanism to attempt to unwrap.
	 * @return The mechanism to attempt to unwrap.
	 */
	public int getMechanism() { return mechanism; }

	// Hand generated code.

} // class def. [UNWRAP].

