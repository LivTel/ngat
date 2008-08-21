package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: UPDATE_SEEING_DONE.<br>
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
public class UPDATE_SEEING_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The The current seeing/photom prediction (coded value)*/
	protected int prediction;

	/** Create a UPDATE_SEEING_DONE with specified id.
	 * @param id The unique id of this UPDATE_SEEING_DONE.
	 */
	public UPDATE_SEEING_DONE (String id) { super(id); }

	/** Set the The current seeing/photom prediction (coded value)
	 * @param prediction The The current seeing/photom prediction (coded value).
	 */
	public void setPrediction(int prediction) { this.prediction = prediction; }

	/** Get the The current seeing/photom prediction (coded value)
	 * @return The The current seeing/photom prediction (coded value)
	 */
	public int getPrediction() { return prediction; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", prediction="+prediction+"]";
	}
	// Hand generated code.

} // class def. [UPDATE_SEEING_DONE].

