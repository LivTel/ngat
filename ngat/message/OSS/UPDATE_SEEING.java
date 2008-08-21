package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: UPDATE_SEEING.<br>
 * Command: UPDATE_SEEING<br>
 * Supplies atmospheric-seeing and photometricity data collected at various times. <br>
 * Used to update the OSS Scheduler's atmospheric prediction model.<br>
 * <br>
 * Module code: 703500<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>type - Determines which type of data is supplied in 'values' array</dd>
 * <dd>&nbsp;values: One of {UPDATE_SEEING | UPDATE_PHOTOM}</dd>
 * <dd>values - The set of values to use for updating the model</dd>
 * <dd>&nbsp;values: An array of seeing/photom values (arcsec/mags) mapped to the times in 'times'</dd>
 * <dd>times - The set of times at which the 'values' array elements were taken.</dd>
 * <dd>&nbsp;values: A (sequential) array of times (millis)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>prediction - The current seeing/photom prediction (coded value)</dd>
 * </dl>
 * <br>
 */
public class UPDATE_SEEING extends TRANSACTION {

	// Constants.

	/** Constant: Indicates Indicates that the data supplied is seeing data (arcsec)*/
	public static final int UPDATE_SEEING = 703501;

	/** Constant: Indicates Indicates that the data supplied is photom data (mags)*/
	public static final int UPDATE_PHOTOM = 703502;

	/** Constant: Indicates that the seeing/photometricity is excellent.*/
	public static final int EXCELLENT = 703503;

	/** Constant: Indicates that the seeing/photometricity is average*/
	public static final int AVERAGE = 703504;

	/** Constant: Indicates that the  seeing/photometricity is poor*/
	public static final int POOR = 703505;

	/** Constant: Indicates that the type was unknown*/
	public static final int UNKNOWN_TYPE = 703501;

	// Variables.

	/** The Determines which type of data is supplied in 'value'.*/
	protected int type;

	/** The value to use for updating the model*/
	protected double value;

	/** The time at which the 'values' were taken.*/
	protected long time;

	/** Create a UPDATE_SEEING with specified id.
	 * @param id The unique id of this UPDATE_SEEING.
	 */
	public UPDATE_SEEING(String id) { super(id); }

	/** Create a UPDATE_SEEING with specified id and parameters.
	 * @param id The unique id of this UPDATE_SEEING.
	 * @param type The Determines which type of data is supplied in 'values' array
	 * @param values The The set of values to use for updating the model
	 * @param times The The set of times at which the 'values' array elements were taken.
	 */
	public UPDATE_SEEING(
	         String id,
	         int    type,
	         double value,
	         long   time) {
	         super(id);
	           this.type  = type;
	           this.value = value;
	           this.time  = time;
	         }

	/** Set the Determines which type of data is supplied in 'values' array
	 * @param type The Determines which type of data is supplied in 'values' array
	 */
	public void setType(int type) { this.type = type; }

	/** Get the Determines which type of data is supplied in 'values' array
	 * @return The Determines which type of data is supplied in 'values' array
	 */
	public int getType() { return type; }

	/** Set the The set of values to use for updating the model
	 * @param values The The set of values to use for updating the model
	 */
	public void setValue(double value) { this.value = value; }

	/** Get the The set of values to use for updating the model
	 * @return The The set of values to use for updating the model
	 */
	public double getValue() { return value; }

	/** Set the The set of times at which the 'values' array elements were taken.
	 * @param times The The set of times at which the 'values' array elements were taken.
	 */
	public void setTime(long time) { this.time = time; }

	/** Get the The set of times at which the 'values' array elements were taken.
	 * @return The The set of times at which the 'values' array elements were taken.
	 */
	public long getTime() { return time; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", type="+type+
			", value="+value+
			", time="+time+"]";
	}
	// Hand generated code.

} // class def. [UPDATE_SEEING].

