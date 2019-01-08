package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GET_AGENT_INFO_DONE.<br>
 *  Request agent (MCA) information.<br>
 * <br>
 *  Module code: 681300<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>agentId - ID of the MCA</dd>
 * <dd>&nbsp;values: a valied agent ID</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>agentVersion - version number of MCA</dd>
 * <dd>agentDesc - description of the MCA</dd>
 * <dd>agentProperties - set of MCA-specific properties</dd>
 * </dl>
 * <br>
 */
public class GET_AGENT_INFO_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The version number of MCA*/
	protected String agentVersion;

	/** The description of the MCA*/
	protected String agentDesc;

	/** The set of MCA-specific properties*/
	protected Properties agentProperties;

	/** Create a GET_AGENT_INFO_DONE with specified id.
	 * @param id The unique id of this GET_AGENT_INFO_DONE.
	 */
	public GET_AGENT_INFO_DONE (String id) { super(id); }

	/** Set the version number of MCA
	 * @param agentVersion The version number of MCA.
	 */
	public void setAgentVersion(String agentVersion) { this.agentVersion = agentVersion; }

	/** Get the version number of MCA
	 * @return The version number of MCA
	 */
	public String getAgentVersion() { return agentVersion; }

	/** Set the description of the MCA
	 * @param agentDesc The description of the MCA.
	 */
	public void setAgentDesc(String agentDesc) { this.agentDesc = agentDesc; }

	/** Get the description of the MCA
	 * @return The description of the MCA
	 */
	public String getAgentDesc() { return agentDesc; }

	/** Set the set of MCA-specific properties
	 * @param agentProperties The set of MCA-specific properties.
	 */
	public void setAgentProperties(Properties agentProperties) { this.agentProperties = agentProperties; }

	/** Get the set of MCA-specific properties
	 * @return The set of MCA-specific properties
	 */
	public Properties getAgentProperties() { return agentProperties; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", agentVersion="+agentVersion+
			", agentDesc="+agentDesc+
			", agentProperties="+agentProperties+"]";
	}
	// Hand generated code.

} // class def. [GET_AGENT_INFO_DONE].

