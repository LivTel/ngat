package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GET_AGENT_INFO.<br>
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
public class GET_AGENT_INFO extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the agent could not be located*/
	public static final int NO_SUCH_AGENT = 680801;

	// Variables.

	/** The ID of the MCA*/
	protected String agentId;

	/** Create a GET_AGENT_INFO with specified id.
	 * @param id The unique id of this GET_AGENT_INFO.
	 */
	public GET_AGENT_INFO(String id) { super(id); }

	/** Create a GET_AGENT_INFO with specified id and parameters.
	 * @param id The unique id of this GET_AGENT_INFO.
	 * @param agentId The ID of the MCA
	 */
	public GET_AGENT_INFO(
	         String id,
	         String agentId) {
	         super(id);
	           this.agentId = agentId;
	         }

	/** Set the ID of the MCA
	 * @param agentId The ID of the MCA
	 */
	public void setAgentId(String agentId) { this.agentId = agentId; }

	/** Get the ID of the MCA
	 * @return The ID of the MCA
	 */
	public String getAgentId() { return agentId; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", agentId="+agentId+"]";
	}
	// Hand generated code.

} // class def. [GET_AGENT_INFO].

