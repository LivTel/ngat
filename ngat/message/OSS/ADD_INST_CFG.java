package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_INST_CFG.<br>
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
public class ADD_INST_CFG extends TRANSACTION {

	// Constants.

	/** Constant: Indicates The Specified Config already exists*/
	public static final int CONFIG_ALREADY_DEFINED = 700301;
	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The The InstrumentConfig to add*/
	protected InstrumentConfig config;

	/** The The InstrumentConfig to add*/
	protected boolean replace;

	/** Create a ADD_INST_CFG with specified id.
	 * @param id The unique id of this ADD_INST_CFG.
	 */
	public ADD_INST_CFG(String id) { super(id); }

	/** Create a ADD_INST_CFG with specified id and parameters.
	 * @param id The unique id of this ADD_INST_CFG.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param config The The InstrumentConfig to add
	 * @param replace The The InstrumentConfig to add
	 */
	public ADD_INST_CFG(
	         String id,
	         Path proposalPath,
	         InstrumentConfig config,
	         boolean replace) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.config = config;
	           this.replace = replace;
	         }

	/** Set the The path used to locate the Proposal in the P2DB
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the P2DB
	 * @return The The path used to locate the Proposal in the P2DB
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The InstrumentConfig to add
	 * @param config The The InstrumentConfig to add
	 */
	public void setConfig(InstrumentConfig config) { this.config = config; }

	/** Get the The InstrumentConfig to add
	 * @return The The InstrumentConfig to add
	 */
	public InstrumentConfig getConfig() { return config; }

	/** Set the The InstrumentConfig to add
	 * @param replace The The InstrumentConfig to add
	 */
	public void setReplace(boolean replace) { this.replace = replace; }

	/** Get the The InstrumentConfig to add
	 * @return The The InstrumentConfig to add
	 */
	public boolean getReplace() { return replace; }

	// Hand generated code.

} // class def. [ADD_INST_CFG].

