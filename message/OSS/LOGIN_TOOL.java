package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND: LOGIN_TOOL.<br>
 * Command: LOGIN_TOOL<br>
 * Checks the version/upgrade information of a PEST installation<br>
 * against the OSS master upgrade records.<br>
 * Module code: 701900<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>versions - Stores the PEST installation's version information</dd>
 * <dd>&nbsp;values: Various key:value pairs defined in PEST Upgrade Doc (TBD)</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>upgrades - Stores the PEST installation upgrade information</dd>
 * </dl>
 * <br>
 */
public class LOGIN_TOOL extends TRANSACTION {

	// Variables.

	/** The Stores the PEST installation's version information*/
	protected Properties versions;

	/** Create a LOGIN_TOOL with specified id.
	 * @param id The unique id of this LOGIN_TOOL.
	 */
	public LOGIN_TOOL(String id) { super(id); }

	/** Create a LOGIN_TOOL with specified id and parameters.
	 * @param id The unique id of this LOGIN_TOOL.
	 * @param versions The Stores the PEST installation's version information
	 */
	public LOGIN_TOOL(
	         String id,
	         Properties versions) {
	         super(id);
	           this.versions = versions;
	         }

	/** Set the Stores the PEST installation's version information
	 * @param versions The Stores the PEST installation's version information
	 */
	public void setVersions(Properties versions) { this.versions = versions; }

	/** Get the Stores the PEST installation's version information
	 * @return The Stores the PEST installation's version information
	 */
	public Properties getVersions() { return versions; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", versions="+versions+"]";
	}
	// Hand generated code.

} // class def. [LOGIN_TOOL].

