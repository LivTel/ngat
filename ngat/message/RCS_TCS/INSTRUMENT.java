package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: INSTRUMENT.<br>
 * Instruct TCS which instrument is to be used. This tells the TCS which focal station, rotator, fold position and autoguider to use.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>instrument - instrument to use.</dd>
 * <dd>&nbsp;values: { from config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class INSTRUMENT extends RCS_TO_TCS {
	
	// Variables.

	/** The instrument to use.*/
	protected String instrumentAlias;

	/** Create a INSTRUMENT with specified id.
	 * @param id The unique id of this INSTRUMENT.
	 */
	public INSTRUMENT(String id) { super(id); }

	/** Create a INSTRUMENT with specified id and parameters.
	 * @param id The unique id of this INSTRUMENT.
	 * @param instrument The instrument to use.
	 */
	public INSTRUMENT(
	         String id,
	         String instrumentAlias) {
	         super(id);
	           this.instrumentAlias = instrumentAlias;
	         }

	/** Set the instrument to use.
	 * @param instrumentAlias The instrument to use.
	 */
	public void setInstrumentAlias(String instrumentAlias) { this.instrumentAlias = instrumentAlias; }

	/** Get the instrument to use.
	 * @return The instrument to use.
	 */
	public String getInstrumentAlias() { return instrumentAlias; }

	// Hand generated code.

} // class def. [INSTRUMENT].

