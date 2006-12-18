package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: ROTATOR.<br>
 * Instruct TCS to switch the rotator mode or move it.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>mode - mode of operation.</dd>
 * <dd>&nbsp;values: { SKY | MOUNT | FLOAT | VERTICAL | VFLOAT }</dd>
 * <dd>position - sky position angles.</dd>
 * <dd>&nbsp;values: SKY (0.00 to 360.00 degs) or MOUNT (-270.00 to +270.00 degs)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ROTATOR extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates position angle is relative to sky.*/
	public static final int SKY = 0;

	/** Constant: Indicates position angle is relative to mount.*/
	public static final int MOUNT = 1;

	/** Constant: Indicates rotator is not slewed from current setting on source change.*/
	public static final int FLOAT = 2;

	/** Constant: Indicates rotator is slewed to vertical position but stops tracking.*/
	public static final int VERTICAL = 3;

	/** Constant: Indicates rotator is slewed to vertical and tracking engaged to keep it vertical.*/
	public static final int VFLOAT = 4;

	// Variables.

	/** The mode of operation.*/
	protected int mode;

	/** The sky position angles.*/
	protected double position;

	/** Create a ROTATOR with specified id.
	 * @param id The unique id of this ROTATOR.
	 */
	public ROTATOR(String id) { super(id); }

	/** Create a ROTATOR with specified id and parameters.
	 * @param id The unique id of this ROTATOR.
	 * @param mode The mode of operation.
	 * @param position The sky position angles.
	 */
	public ROTATOR(
	         String id,
	         int mode,
	         double position) {
	         super(id);
	           this.mode = mode;
	           this.position = position;
	         }

	/** Set the mode of operation.
	 * @param mode The mode of operation.
	 */
	public void setMode(int mode) { this.mode = mode; }

	/** Get the mode of operation.
	 * @return The mode of operation.
	 */
	public int getMode() { return mode; }

	/** Set the sky position angles.
	 * @param position The sky position angles.
	 */
	public void setPosition(double position) { this.position = position; }

	/** Get the sky position angles.
	 * @return The sky position angles.
	 */
	public double getPosition() { return position; }

	// Hand generated code.

} // class def. [ROTATOR].

