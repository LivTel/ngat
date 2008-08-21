package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: TEL_INFO.<br>
 * Command: TEL_INFO<br>
 * Used to update the OSS with information about the current telescope position<br>
 * and availability of autoguider (+ others later).<br>
 * Module code: 703100<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>telescopeAzimuth - The current azimuth of the telescope (rads)</dd>
 * <dd>&nbsp;values: 0 <= az <= 2*PI</dd>
 * <dd>telescopeAltitude - The current altitude of the telescope (rads)</dd>
 * <dd>&nbsp;values: 0 <= alt <= PI/2</dd>
 * <dd>autoguiderAvailable - True if the autoguider is currently available</dd>
 * <dd>&nbsp;values: { T | F }</dd>
 * <dd>azNegLimit - Telescope azimuth negative wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>azPosLimit - Telescope azimuth positive wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>altLowLimit - Telescope altitude low limit (rads)</dd>
 * <dd>&nbsp;values: [0, Pi/2]</dd>
 * <dd>altHighLimit - Telescope altitude high limit (rads)</dd>
 * <dd>&nbsp;values: [0, Pi/2]</dd>
 * <dd>rotNegLimit - Telescope rotator negative wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dd>rotPosLimit - Telescope rotator positive wrap limit (rads)</dd>
 * <dd>&nbsp;values: Can be outside [ -2*PI , 2+PI ]</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class TEL_INFO extends TRANSACTION {

	// Variables.

	/** The The current azimuth of the telescope (rads)*/
	protected double telescopeAzimuth;

	/** The The current altitude of the telescope (rads)*/
	protected double telescopeAltitude;

	/** The True if the autoguider is currently available*/
	protected boolean autoguiderAvailable;

	/** The Telescope azimuth negative wrap limit (rads)*/
	protected double azNegLimit;

	/** The Telescope azimuth positive wrap limit (rads)*/
	protected double azPosLimit;

	/** The Telescope altitude low limit (rads)*/
	protected double altLowLimit;

	/** The Telescope altitude high limit (rads)*/
	protected double altHighLimit;

	/** The Telescope rotator negative wrap limit (rads)*/
	protected double rotNegLimit;

	/** The Telescope rotator positive wrap limit (rads)*/
	protected double rotPosLimit;

	/** Create a TEL_INFO with specified id.
	 * @param id The unique id of this TEL_INFO.
	 */
	public TEL_INFO(String id) { super(id); }

	/** Create a TEL_INFO with specified id and parameters.
	 * @param id The unique id of this TEL_INFO.
	 * @param telescopeAzimuth The The current azimuth of the telescope (rads)
	 * @param telescopeAltitude The The current altitude of the telescope (rads)
	 * @param autoguiderAvailable The True if the autoguider is currently available
	 * @param azNegLimit The Telescope azimuth negative wrap limit (rads)
	 * @param azPosLimit The Telescope azimuth positive wrap limit (rads)
	 * @param altLowLimit The Telescope altitude low limit (rads)
	 * @param altHighLimit The Telescope altitude high limit (rads)
	 * @param rotNegLimit The Telescope rotator negative wrap limit (rads)
	 * @param rotPosLimit The Telescope rotator positive wrap limit (rads)
	 */
	public TEL_INFO(
	         String id,
	         double telescopeAzimuth,
	         double telescopeAltitude,
	         boolean autoguiderAvailable,
	         double azNegLimit,
	         double azPosLimit,
	         double altLowLimit,
	         double altHighLimit,
	         double rotNegLimit,
	         double rotPosLimit) {
	         super(id);
	           this.telescopeAzimuth = telescopeAzimuth;
	           this.telescopeAltitude = telescopeAltitude;
	           this.autoguiderAvailable = autoguiderAvailable;
	           this.azNegLimit = azNegLimit;
	           this.azPosLimit = azPosLimit;
	           this.altLowLimit = altLowLimit;
	           this.altHighLimit = altHighLimit;
	           this.rotNegLimit = rotNegLimit;
	           this.rotPosLimit = rotPosLimit;
	         }

	/** Set the The current azimuth of the telescope (rads)
	 * @param telescopeAzimuth The The current azimuth of the telescope (rads)
	 */
	public void setTelescopeAzimuth(double telescopeAzimuth) { this.telescopeAzimuth = telescopeAzimuth; }

	/** Get the The current azimuth of the telescope (rads)
	 * @return The The current azimuth of the telescope (rads)
	 */
	public double getTelescopeAzimuth() { return telescopeAzimuth; }

	/** Set the The current altitude of the telescope (rads)
	 * @param telescopeAltitude The The current altitude of the telescope (rads)
	 */
	public void setTelescopeAltitude(double telescopeAltitude) { this.telescopeAltitude = telescopeAltitude; }

	/** Get the The current altitude of the telescope (rads)
	 * @return The The current altitude of the telescope (rads)
	 */
	public double getTelescopeAltitude() { return telescopeAltitude; }

	/** Set the True if the autoguider is currently available
	 * @param autoguiderAvailable The True if the autoguider is currently available
	 */
	public void setAutoguiderAvailable(boolean autoguiderAvailable) { this.autoguiderAvailable = autoguiderAvailable; }

	/** Get the True if the autoguider is currently available
	 * @return The True if the autoguider is currently available
	 */
	public boolean getAutoguiderAvailable() { return autoguiderAvailable; }

	/** Set the Telescope azimuth negative wrap limit (rads)
	 * @param azNegLimit The Telescope azimuth negative wrap limit (rads)
	 */
	public void setAzNegLimit(double azNegLimit) { this.azNegLimit = azNegLimit; }

	/** Get the Telescope azimuth negative wrap limit (rads)
	 * @return The Telescope azimuth negative wrap limit (rads)
	 */
	public double getAzNegLimit() { return azNegLimit; }

	/** Set the Telescope azimuth positive wrap limit (rads)
	 * @param azPosLimit The Telescope azimuth positive wrap limit (rads)
	 */
	public void setAzPosLimit(double azPosLimit) { this.azPosLimit = azPosLimit; }

	/** Get the Telescope azimuth positive wrap limit (rads)
	 * @return The Telescope azimuth positive wrap limit (rads)
	 */
	public double getAzPosLimit() { return azPosLimit; }

	/** Set the Telescope altitude low limit (rads)
	 * @param altLowLimit The Telescope altitude low limit (rads)
	 */
	public void setAltLowLimit(double altLowLimit) { this.altLowLimit = altLowLimit; }

	/** Get the Telescope altitude low limit (rads)
	 * @return The Telescope altitude low limit (rads)
	 */
	public double getAltLowLimit() { return altLowLimit; }

	/** Set the Telescope altitude high limit (rads)
	 * @param altHighLimit The Telescope altitude high limit (rads)
	 */
	public void setAltHighLimit(double altHighLimit) { this.altHighLimit = altHighLimit; }

	/** Get the Telescope altitude high limit (rads)
	 * @return The Telescope altitude high limit (rads)
	 */
	public double getAltHighLimit() { return altHighLimit; }

	/** Set the Telescope rotator negative wrap limit (rads)
	 * @param rotNegLimit The Telescope rotator negative wrap limit (rads)
	 */
	public void setRotNegLimit(double rotNegLimit) { this.rotNegLimit = rotNegLimit; }

	/** Get the Telescope rotator negative wrap limit (rads)
	 * @return The Telescope rotator negative wrap limit (rads)
	 */
	public double getRotNegLimit() { return rotNegLimit; }

	/** Set the Telescope rotator positive wrap limit (rads)
	 * @param rotPosLimit The Telescope rotator positive wrap limit (rads)
	 */
	public void setRotPosLimit(double rotPosLimit) { this.rotPosLimit = rotPosLimit; }

	/** Get the Telescope rotator positive wrap limit (rads)
	 * @return The Telescope rotator positive wrap limit (rads)
	 */
	public double getRotPosLimit() { return rotPosLimit; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", telescopeAzimuth="+telescopeAzimuth+
			", telescopeAltitude="+telescopeAltitude+
			", autoguiderAvailable="+autoguiderAvailable+
			", azNegLimit="+azNegLimit+
			", azPosLimit="+azPosLimit+
			", altLowLimit="+altLowLimit+
			", altHighLimit="+altHighLimit+
			", rotNegLimit="+rotNegLimit+
			", rotPosLimit="+rotPosLimit+"]";
	}
	// Hand generated code.

} // class def. [TEL_INFO].

