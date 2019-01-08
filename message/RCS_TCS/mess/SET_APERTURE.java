package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** RCS_TCS COMMAND: SET_APERTURE.<br>
 * Instruct the TCS to setup an aperture offset.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>number - instrument offset number (associated with an instrument)</dd>
 * <dd>&nbsp;values: 0-max</dd>
 * <dd>offsetX - focal plane offset in X.</dd>
 * <dd>&nbsp;values: +/- max mm</dd>
 * <dd>offsetY - focal plane offset in Y.</dd>
 * <dd>&nbsp;values: +/- max mm</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_APERTURE extends RCS_TO_TCS {

	// Variables.

	/** The instrument offset number (associated with an instrument)*/
	protected int number;

	/** The focal plane offset in X.*/
	protected double offsetX;

	/** The focal plane offset in Y.*/
	protected double offsetY;

	/** Create a SET_APERTURE with specified id.
	 * @param id The unique id of this SET_APERTURE.
	 */
	public SET_APERTURE(String id) { super(id); }

	/** Create a SET_APERTURE with specified id and parameters.
	 * @param id The unique id of this SET_APERTURE.
	 * @param number The instrument offset number (associated with an instrument)
	 * @param offsetX The focal plane offset in X.
	 * @param offsetY The focal plane offset in Y.
	 */
	public SET_APERTURE(
	         String id,
	         int number,
	         double offsetX,
	         double offsetY) {
	         super(id);
	           this.number = number;
	           this.offsetX = offsetX;
	           this.offsetY = offsetY;
	         }

	/** Set the instrument offset number (associated with an instrument)
	 * @param number The instrument offset number (associated with an instrument)
	 */
	public void setNumber(int number) { this.number = number; }

	/** Get the instrument offset number (associated with an instrument)
	 * @return The instrument offset number (associated with an instrument)
	 */
	public int getNumber() { return number; }

	/** Set the focal plane offset in X.
	 * @param offsetX The focal plane offset in X.
	 */
	public void setOffsetX(double offsetX) { this.offsetX = offsetX; }

	/** Get the focal plane offset in X.
	 * @return The focal plane offset in X.
	 */
	public double getOffsetX() { return offsetX; }

	/** Set the focal plane offset in Y.
	 * @param offsetY The focal plane offset in Y.
	 */
	public void setOffsetY(double offsetY) { this.offsetY = offsetY; }

	/** Get the focal plane offset in Y.
	 * @return The focal plane offset in Y.
	 */
	public double getOffsetY() { return offsetY; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", number="+number+
			", offsetX="+offsetX+
			", offsetY="+offsetY+"]";
	}
	// Hand generated code.

} // class def. [SET_APERTURE].

