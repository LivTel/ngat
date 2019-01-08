package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_TELE_CONFIG.<br>
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
public class ADD_TELE_CONFIG extends TRANSACTION {

	// Constants.

	/** Constant: Indicates The Specified TeleConfig already exists*/
	public static final int CONFIG_ALREADY_DEFINED = 700601;

	// Variables.

	/** The The path used to locate the Proposal in the P2DB*/
	protected Path proposalPath;

	/** The The TelescopeConfig to add*/
	protected TelescopeConfig config;

	/** The The TelescopeConfig to add*/
	protected boolean replace;

	/** Create a ADD_TELE_CONFIG with specified id.
	 * @param id The unique id of this ADD_TELE_CONFIG.
	 */
	public ADD_TELE_CONFIG(String id) { super(id); }

	/** Create a ADD_TELE_CONFIG with specified id and parameters.
	 * @param id The unique id of this ADD_TELE_CONFIG.
	 * @param proposalPath The The path used to locate the Proposal in the P2DB
	 * @param config The The TelescopeConfig to add
	 * @param replace The The TelescopeConfig to add
	 */
	public ADD_TELE_CONFIG(
	         String id,
	         Path proposalPath,
	         TelescopeConfig config,
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

	/** Set the The TelescopeConfig to add
	 * @param config The The TelescopeConfig to add
	 */
	public void setConfig(TelescopeConfig config) { this.config = config; }

	/** Get the The TelescopeConfig to add
	 * @return The The TelescopeConfig to add
	 */
	public TelescopeConfig getConfig() { return config; }

	/** Set the The TelescopeConfig to add
	 * @param replace The The TelescopeConfig to add
	 */
	public void setReplace(boolean replace) { this.replace = replace; }

	/** Get the The TelescopeConfig to add
	 * @return The The TelescopeConfig to add
	 */
	public boolean getReplace() { return replace; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", config="+config+
			", replace="+replace+"]";
	}
	// Hand generated code.

} // class def. [ADD_TELE_CONFIG].

