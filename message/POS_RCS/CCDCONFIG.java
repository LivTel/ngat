package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDCONFIG.<br>
 *  Supplies the information required to configure the CCD camera.<br>
 *  Module code: 692200<br>
 * <br>
 *  Example: CCDCONFIG CB 2 340.0<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>config - null</dd>
 * <dd>&nbsp;values: a valid config for the CCD camera.</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDCONFIG extends POS_TO_RCS {

	// Variables.

	/** The null*/
	protected InstrumentConfig config;

	/** Create a CCDCONFIG with specified id.
	 * @param id The unique id of this CCDCONFIG.
	 */
	public CCDCONFIG(String id) { super(id); }

	/** Create a CCDCONFIG with specified id and parameters.
	 * @param id The unique id of this CCDCONFIG.
	 * @param config The null
	 */
	public CCDCONFIG(
	         String id,
	         InstrumentConfig config) {
	         super(id);
	           this.config = config;
	         }

	/** Set the null
	 * @param config The null
	 */
	public void setConfig(InstrumentConfig config) { this.config = config; }

	/** Get the null
	 * @return The null
	 */
	public InstrumentConfig getConfig() { return config; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", config="+config+"]";
	}
	// Hand generated code.

} // class def. [CCDCONFIG].

