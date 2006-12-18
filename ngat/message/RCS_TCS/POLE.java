package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: POLE.<br>
 * Instructs TCS to set the polar motion corrections.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>xPos - X axis correction for polar motion.</dd>
 * <dd>&nbsp;values: -1.00 to +1.000 arcsec</dd>
 * <dd>yPos - Y axis correction for polar motion.</dd>
 * <dd>&nbsp;values: -1.00 to +1.000 arcsec</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class POLE extends RCS_TO_TCS {

	// Variables.

	/** The X axis correction for polar motion.*/
	protected double xPos;

	/** The Y axis correction for polar motion.*/
	protected double yPos;

	/** Create a POLE with specified id.
	 * @param id The unique id of this POLE.
	 */
	public POLE(String id) { super(id); }

	/** Create a POLE with specified id and parameters.
	 * @param id The unique id of this POLE.
	 * @param xPos The X axis correction for polar motion.
	 * @param yPos The Y axis correction for polar motion.
	 */
	public POLE(
	         String id,
	         double xPos,
	         double yPos) {
	         super(id);
	           this.xPos = xPos;
	           this.yPos = yPos;
	         }

	/** Set the X axis correction for polar motion.
	 * @param xPos The X axis correction for polar motion.
	 */
	public void setXPos(double xPos) { this.xPos = xPos; }

	/** Get the X axis correction for polar motion.
	 * @return The X axis correction for polar motion.
	 */
	public double getXPos() { return xPos; }

	/** Set the Y axis correction for polar motion.
	 * @param yPos The Y axis correction for polar motion.
	 */
	public void setYPos(double yPos) { this.yPos = yPos; }

	/** Get the Y axis correction for polar motion.
	 * @return The Y axis correction for polar motion.
	 */
	public double getYPos() { return yPos; }

	// Hand generated code.

} // class def. [POLE].

