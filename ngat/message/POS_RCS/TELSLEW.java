package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: TELSLEW.<br>
 *  Supplies the information required to slew the telescope to a source.<br>
 *  The source can be moving or fixed with appropriate parameters.<br>
 *  Module code: 692000<br>
 * <br>
 *  Example: TELSLEW FIXED "NGC 2525" 10 10 12.3 24 26 56.6 J2000.0<br>
 * <br>
 *  Example: TELSLEW MOVING MARS<br>
 * <br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>source - target to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.phase2.Source or subclass</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TELSLEW extends POS_TO_RCS {

	// Variables.

	/** The target to slew to.*/
	protected Source source;

	/** Create a TELSLEW with specified id.
	 * @param id The unique id of this TELSLEW.
	 */
	public TELSLEW(String id) { super(id); }

	/** Create a TELSLEW with specified id and parameters.
	 * @param id The unique id of this TELSLEW.
	 * @param source The target to slew to.
	 */
	public TELSLEW(
	         String id,
	         Source source) {
	         super(id);
	           this.source = source;
	         }

	/** Set the target to slew to.
	 * @param source The target to slew to.
	 */
	public void setSource(Source source) { this.source = source; }

	/** Get the target to slew to.
	 * @return The target to slew to.
	 */
	public Source getSource() { return source; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", source="+source+"]";
	}
	// Hand generated code.

} // class def. [TELSLEW].

