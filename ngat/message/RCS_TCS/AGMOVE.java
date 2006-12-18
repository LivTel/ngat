package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGMOVE.<br>
 * Instructs the TCS to move the autoguider probe to a position<br>
 * and Rotator to a given mount position angle and guide on a pixels.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - radial position from field edge.</dd>
 * <dd>&nbsp;values: 0.00 to 110.00 mm</dd>
 * <dd>angle - mount position angle.</dd>
 * <dd>&nbsp;values: -270.00 to +270.00 degs</dd>
 * <dd>pixelX - autoguider X pixel.</dd>
 * <dd>&nbsp;values: 0.00 to 1024.00</dd>
 * <dd>pixelY - autoguider Y pixel.</dd>
 * <dd>&nbsp;values: 0.00 to 1024.00</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGMOVE extends RCS_TO_TCS {

	// Variables.

	/** The radial position from field edge.*/
	protected double position;

	/** The mount position angle.*/
	protected double angle;

	/** The autoguider X pixel.*/
	protected double pixelX;

	/** The autoguider Y pixel.*/
	protected double pixelY;

	/** Create a AGMOVE with specified id.
	 * @param id The unique id of this AGMOVE.
	 */
	public AGMOVE(String id) { super(id); }

	/** Create a AGMOVE with specified id and parameters.
	 * @param id The unique id of this AGMOVE.
	 * @param position The radial position from field edge.
	 * @param angle The mount position angle.
	 * @param pixelX The autoguider X pixel.
	 * @param pixelY The autoguider Y pixel.
	 */
	public AGMOVE(
	         String id,
	         double position,
	         double angle,
	         double pixelX,
	         double pixelY) {
	         super(id);
	           this.position = position;
	           this.angle = angle;
	           this.pixelX = pixelX;
	           this.pixelY = pixelY;
	         }

	/** Set the radial position from field edge.
	 * @param position The radial position from field edge.
	 */
	public void setPosition(double position) { this.position = position; }

	/** Get the radial position from field edge.
	 * @return The radial position from field edge.
	 */
	public double getPosition() { return position; }

	/** Set the mount position angle.
	 * @param angle The mount position angle.
	 */
	public void setAngle(double angle) { this.angle = angle; }

	/** Get the mount position angle.
	 * @return The mount position angle.
	 */
	public double getAngle() { return angle; }

	/** Set the autoguider X pixel.
	 * @param pixelX The autoguider X pixel.
	 */
	public void setPixelX(double pixelX) { this.pixelX = pixelX; }

	/** Get the autoguider X pixel.
	 * @return The autoguider X pixel.
	 */
	public double getPixelX() { return pixelX; }

	/** Set the autoguider Y pixel.
	 * @param pixelY The autoguider Y pixel.
	 */
	public void setPixelY(double pixelY) { this.pixelY = pixelY; }

	/** Get the autoguider Y pixel.
	 * @return The autoguider Y pixel.
	 */
	public double getPixelY() { return pixelY; }

	// Hand generated code.

} // class def. [AGMOVE].

