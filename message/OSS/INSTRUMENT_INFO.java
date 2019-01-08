package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: INSTRUMENT_INFO.<br>
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
public class INSTRUMENT_INFO extends TRANSACTION {

	// Constants.

	/** Constant: Indicates The instrument named by instName was not known to the OSS*/
	public static final int NO_SUCH_INSTRUMENT = 701701;

	/** Constant: Indicates The supplied InstrumentConfig was not suitable for the named instrument.*/
	public static final int UNSUITABLE_CONFIG = 701702;

	// Variables.

	/** The The configuration settings for the specified Instrument*/
	protected InstrumentConfig config;

	/** The The name/id of the instrument whose config is to be set*/
	protected String instName;

	/** The The name/id of the instrument whose config is to be set*/
	protected boolean current;

	/** The The current status of the Instrument*/
	protected int status;

	/** The The current operational status of the Instrument*/
	protected int operationalStatus;

	/** Create a INSTRUMENT_INFO with specified id.
	 * @param id The unique id of this INSTRUMENT_INFO.
	 */
	public INSTRUMENT_INFO(String id) { super(id); }

	/** Create a INSTRUMENT_INFO with specified id and parameters.
	 * @param id The unique id of this INSTRUMENT_INFO.
	 * @param config The The configuration settings for the specified Instrument
	 * @param instName The The name/id of the instrument whose config is to be set
	 * @param current The The name/id of the instrument whose config is to be set
	 * @param status The The current status of the Instrument
	 * @param operationalStatus The The current operational status of the Instrument
	 */
	public INSTRUMENT_INFO(
	         String id,
	         InstrumentConfig config,
	         String instName,
	         boolean current,
	         int status,
	         int operationalStatus) {
	         super(id);
	           this.config = config;
	           this.instName = instName;
	           this.current = current;
	           this.status = status;
	           this.operationalStatus = operationalStatus;
	         }

	/** Set the The configuration settings for the specified Instrument
	 * @param config The The configuration settings for the specified Instrument
	 */
	public void setConfig(InstrumentConfig config) { this.config = config; }

	/** Get the The configuration settings for the specified Instrument
	 * @return The The configuration settings for the specified Instrument
	 */
	public InstrumentConfig getConfig() { return config; }

	/** Set the The name/id of the instrument whose config is to be set
	 * @param instName The The name/id of the instrument whose config is to be set
	 */
	public void setInstName(String instName) { this.instName = instName; }

	/** Get the The name/id of the instrument whose config is to be set
	 * @return The The name/id of the instrument whose config is to be set
	 */
	public String getInstName() { return instName; }

	/** Set the The name/id of the instrument whose config is to be set
	 * @param current The The name/id of the instrument whose config is to be set
	 */
	public void setCurrent(boolean current) { this.current = current; }

	/** Get the The name/id of the instrument whose config is to be set
	 * @return The The name/id of the instrument whose config is to be set
	 */
	public boolean getCurrent() { return current; }

	/** Set the The current status of the Instrument
	 * @param status The The current status of the Instrument
	 */
	public void setStatus(int status) { this.status = status; }

	/** Get the The current status of the Instrument
	 * @return The The current status of the Instrument
	 */
	public int getStatus() { return status; }

	/** Set the The current operational status of the Instrument
	 * @param operationalStatus The The current operational status of the Instrument
	 */
	public void setOperationalStatus(int operationalStatus) { this.operationalStatus = operationalStatus; }

	/** Get the The current operational status of the Instrument
	 * @return The The current operational status of the Instrument
	 */
	public int getOperationalStatus() { return operationalStatus; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", config="+config+
			", instName="+instName+
			", current="+current+
			", status="+status+
			", operationalStatus="+operationalStatus+"]";
	}
	// Hand generated code.

} // class def. [INSTRUMENT_INFO].

