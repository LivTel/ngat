package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGRADIAL.<br>
 * Instructs the TCS to move the autoguider probe to a radial position.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - radial position from field edge.</dd>
 * <dd>&nbsp;values: 0.00 to 110.00 mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGRADIAL extends RCS_TO_TCS {

	// Variables.

	/** The radial position from field edge.*/
	protected double position;

	/** Create a AGRADIAL with specified id.
	 * @param id The unique id of this AGRADIAL.
	 */
	public AGRADIAL(String id) { super(id); }

	/** Create a AGRADIAL with specified id and parameters.
	 * @param id The unique id of this AGRADIAL.
	 * @param position The radial position from field edge.
	 */
	public AGRADIAL(
	         String id,
	         double position) {
	         super(id);
	           this.position = position;
	         }

	/** Set the radial position from field edge.
	 * @param position The radial position from field edge.
	 */
	public void setPosition(double position) { this.position = position; }

	/** Get the radial position from field edge.
	 * @return The radial position from field edge.
	 */
	public double getPosition() { return position; }

	// Hand generated code.

} // class def. [AGRADIAL].

