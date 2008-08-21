package ngat.message.OSS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** OSS COMMAND_DONE: LOGIN_TOOL_DONE.<br>
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
public class LOGIN_TOOL_DONE extends TRANSACTION_DONE {

	// Variables.

	/** The Stores the PEST installation upgrade information*/
	protected Properties upgrades;

	/** Create a LOGIN_TOOL_DONE with specified id.
	 * @param id The unique id of this LOGIN_TOOL_DONE.
	 */
	public LOGIN_TOOL_DONE (String id) { super(id); }

	/** Set the Stores the PEST installation upgrade information
	 * @param upgrades The Stores the PEST installation upgrade information.
	 */
	public void setUpgrades(Properties upgrades) { this.upgrades = upgrades; }

	/** Get the Stores the PEST installation upgrade information
	 * @return The Stores the PEST installation upgrade information
	 */
	public Properties getUpgrades() { return upgrades; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", upgrades="+upgrades+"]";
	}
	// Hand generated code.

} // class def. [LOGIN_TOOL_DONE].

