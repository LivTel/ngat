package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: INSTRUMENT_INFO_DONE.<br>
 * Command: INSTRUMENT_INFO<br>
 * Used to update the OSS with information about the current status,<br>
 * configuration and selection of instrument. The OSS InstrumentRegistry impl<br>
 * will select the relevant Instrument object and set it with this data.<br>
 * Module code: 701700<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>config - The configuration settings for the specified Instrument</dd>
 * <dd>&nbsp;values: Instrument-specific config</dd>
 * <dd>instName - The name/id of the instrument whose config is to be set</dd>
 * <dd>&nbsp;values: The name of a valid Instrument (Registry entry)</dd>
 * <dd>current - The name/id of the instrument whose config is to be set</dd>
 * <dd>&nbsp;values: True if the selected instrument is current</dd>
 * <dd>status - The current status of the Instrument</dd>
 * <dd>&nbsp;values: Const: ngat.Instrument.{ ONLINE | OFFLINE }</dd>
 * <dd>operationalStatus - The current operational status of the Instrument</dd>
 * <dd>&nbsp;values: Const: ngat.Instrument. {OKAY | WARN | FAIL | UNAVAILABLE}</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class INSTRUMENT_INFO_DONE extends TRANSACTION_DONE {

	/** Create a INSTRUMENT_INFO_DONE with specified id.
	 * @param id The unique id of this INSTRUMENT_INFO_DONE.
	 */
	public INSTRUMENT_INFO_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [INSTRUMENT_INFO_DONE].

