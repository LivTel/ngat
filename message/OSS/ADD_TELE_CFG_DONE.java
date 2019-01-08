package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: ADD_TELE_CFG_DONE.<br>
 * Command: ADD_TELE_CFG<br>
 * Adds a telescope-config to an existing proposal.<br>
 * Module code: 700600<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the P2DB</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>config - The TelescopeConfig to add</dd>
 * <dd>&nbsp;values: A valid TelescopeConfig</dd>
 * <dd>replace - The TelescopeConfig to add</dd>
 * <dd>&nbsp;values: True if the TelescopeConfig should be replaced (if it already exists)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_TELE_CFG_DONE extends TRANSACTION_DONE {

	/** Create a ADD_TELE_CFG_DONE with specified id.
	 * @param id The unique id of this ADD_TELE_CFG_DONE.
	 */
	public ADD_TELE_CFG_DONE (String id) { super(id); }

	// Hand generated code.

} // class def. [ADD_TELE_CFG_DONE].

