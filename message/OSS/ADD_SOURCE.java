package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: ADD_SOURCE.<br>
 * Command: ADD_SOURCE<br>
 * Adds a source to an existing proposal.<br>
 * Module code: 700500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>proposalPath - The path used to locate the Proposal in the OSS database</dd>
 * <dd>&nbsp;values: Path to a proposal already in the P2DB</dd>
 * <dd>source - The Source to add</dd>
 * <dd>&nbsp;values: A valid Source</dd>
 * <dd>replace - The Source to add</dd>
 * <dd>&nbsp;values: True if the Source should be replaced (if it already exists)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class ADD_SOURCE extends TRANSACTION {

	// Constants.

	/** Constant: Indicates The Specified Source already exists*/
	public static final int SOURCE_ALREADY_DEFINED = 700501;

	// Variables.

	/** The The path used to locate the Proposal in the OSS database*/
	protected Path proposalPath;

	/** The The Source to add*/
	protected Source source;

	/** The The Source to add*/
	protected boolean replace;

	/** Create a ADD_SOURCE with specified id.
	 * @param id The unique id of this ADD_SOURCE.
	 */
	public ADD_SOURCE(String id) { super(id); }

	/** Create a ADD_SOURCE with specified id and parameters.
	 * @param id The unique id of this ADD_SOURCE.
	 * @param proposalPath The The path used to locate the Proposal in the OSS database
	 * @param source The The Source to add
	 * @param replace The The Source to add
	 */
	public ADD_SOURCE(
	         String id,
	         Path proposalPath,
	         Source source,
	         boolean replace) {
	         super(id);
	           this.proposalPath = proposalPath;
	           this.source = source;
	           this.replace = replace;
	         }

	/** Set the The path used to locate the Proposal in the OSS database
	 * @param proposalPath The The path used to locate the Proposal in the OSS database
	 */
	public void setProposalPath(Path proposalPath) { this.proposalPath = proposalPath; }

	/** Get the The path used to locate the Proposal in the OSS database
	 * @return The The path used to locate the Proposal in the OSS database
	 */
	public Path getProposalPath() { return proposalPath; }

	/** Set the The Source to add
	 * @param source The The Source to add
	 */
	public void setSource(Source source) { this.source = source; }

	/** Get the The Source to add
	 * @return The The Source to add
	 */
	public Source getSource() { return source; }

	/** Set the The Source to add
	 * @param replace The The Source to add
	 */
	public void setReplace(boolean replace) { this.replace = replace; }

	/** Get the The Source to add
	 * @return The The Source to add
	 */
	public boolean getReplace() { return replace; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", proposalPath="+proposalPath+
			", source="+source+
			", replace="+replace+"]";
	}
	// Hand generated code.

} // class def. [ADD_SOURCE].

