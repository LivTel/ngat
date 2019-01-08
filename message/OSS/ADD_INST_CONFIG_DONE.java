package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_INST_CONFIG_DONE.<br>
 * Command: ADD_INST_CFG<br>
 * Adds an instrument-config to an existing proposal.<br>
 * Module code: 700300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>config - The InstrumentConfig to add</dd>
 * <dd>&nbsp;values: A valid InstrumentConfig</dd>
 * <dd>replace - The InstrumentConfig to add</dd>
 * <dd>&nbsp;values: True if the InstrumentConfig should be replaced (if it already exists)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_INST_CONFIG_DONE extends TRANSACTION_DONE {

	/** Create a ADD_INST_CONFIG_DONE with specified id.
	 * @param id The unique id of this ADD_INST_CONFIG_DONE.
	 */
	public ADD_INST_CONFIG_DONE (String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [ADD_INST_CONFIG_DONE].

