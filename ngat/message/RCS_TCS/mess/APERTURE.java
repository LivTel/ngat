package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND: APERTURE.<br>
 * Instruct the TCS to apply an aperture offset<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>number - number of stored aperture offset</dd>
 * <dd>&nbsp;values: 0-max</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class APERTURE extends RCS_TO_TCS {

	// Variables.

	/** The number of stored aperture offset*/
	protected int number;

	/** Create a APERTURE with specified id.
	 * @param id The unique id of this APERTURE.
	 */
	public APERTURE(String id) { super(id); }

	/** Create a APERTURE with specified id and parameters.
	 * @param id The unique id of this APERTURE.
	 * @param number The number of stored aperture offset
	 */
	public APERTURE(
	         String id,
	         int number) {
	         super(id);
	           this.number = number;
	         }

	/** Set the number of stored aperture offset
	 * @param number The number of stored aperture offset
	 */
	public void setNumber(int number) { this.number = number; }

	/** Get the number of stored aperture offset
	 * @return The number of stored aperture offset
	 */
	public int getNumber() { return number; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", number="+number+"]";
	}
	// Hand generated code.

} // class def. [APERTURE].

