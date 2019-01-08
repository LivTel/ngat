package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: ID_DONE.<br>
 *  Request status information.<br>
 * <br>
 *  Module code: 680400<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>control - process in control</dd>
 * <dd>engineering - the RCS is running in ENGINEERING mode</dd>
 * <dd>operational - the RCS is currently OPERATIONAL</dd>
 * <dd>lastStatus - RCS exit status on last startup attempt by RCW</dd>
 * <dd>agentInControl - ID of MCA currently in control or null</dd>
 * <dd>agentName - Name of the MCA currently in control or null</dd>
 * <dd>agentActivity - Activity that AIC is doing or null</dd>
 * </dl>
 * <br>
 */
public class ID_DONE extends GUI_TO_RCS_DONE {

	// Variables.
    public static final int IDLE = 0;
    public static final int SOCA_MODE = 6;
    public static final int BGCA_MODE = 7;
    public static final int TOCA_MODE = 8;
    public static final int X_MODE = 9;
    public static final int CAL_MODE = 10;
    
	/** The process in control*/
	protected int control;

	/** The the RCS is running in ENGINEERING mode*/
	protected boolean engineering;

	/** The the RCS is currently OPERATIONAL*/
	protected boolean operational;

	/** The RCS exit status on last startup attempt by RCW*/
	protected int lastStatus;

	/** The ID of MCA currently in control or null*/
	protected String agentInControl;


	/** The Name of the MCA currently in control or null*/
	protected String agentName;

	/** The Activity that AIC is doing or null*/
	protected String agentActivity;

    /** Uptime of RCS.*/
    protected long uptime;

	/** Create a ID_DONE with specified id.
	 * @param id The unique id of this ID_DONE.
	 */
	public ID_DONE (String id) { super(id); }

	/** Set the process in control
	 * @param control The process in control.
	 */
	public void setControl(int control) { this.control = control; }

	/** Get the process in control
	 * @return The process in control
	 */
	public int getControl() { return control; }

	/** Set the the RCS is running in ENGINEERING mode
	 * @param engineering The the RCS is running in ENGINEERING mode.
	 */
	public void setEngineering(boolean engineering) { this.engineering = engineering; }

	/** Get the the RCS is running in ENGINEERING mode
	 * @return The the RCS is running in ENGINEERING mode
	 */
	public boolean getEngineering() { return engineering; }

	/** Set the the RCS is currently OPERATIONAL
	 * @param operational The the RCS is currently OPERATIONAL.
	 */
	public void setOperational(boolean operational) { this.operational = operational; }

	/** Get the the RCS is currently OPERATIONAL
	 * @return The the RCS is currently OPERATIONAL
	 */
	public boolean getOperational() { return operational; }

	/** Set the RCS exit status on last startup attempt by RCW
	 * @param lastStatus The RCS exit status on last startup attempt by RCW.
	 */
	public void setLastStatus(int lastStatus) { this.lastStatus = lastStatus; }

	/** Get the RCS exit status on last startup attempt by RCW
	 * @return The RCS exit status on last startup attempt by RCW
	 */
	public int getLastStatus() { return lastStatus; }

	/** Set the ID of MCA currently in control or null
	 * @param agentInControl The ID of MCA currently in control or null.
	 */
	public void setAgentInControl(String agentInControl) { this.agentInControl = agentInControl; }

	/** Get the ID of MCA currently in control or null
	 * @return The ID of MCA currently in control or null
	 */
	public String getAgentInControl() { return agentInControl; }




	/** Set the Name of the MCA currently in control or null
	 * @param agentName The Name of the MCA currently in control or null.
	 */
	public void setAgentName(String agentName) { this.agentName = agentName; }

	/** Get the Name of the MCA currently in control or null
	 * @return The Name of the MCA currently in control or null
	 */
	public String getAgentName() { return agentName; }

	/** Set the Activity that AIC is doing or null
	 * @param agentActivity The Activity that AIC is doing or null.
	 */
	public void setAgentActivity(String agentActivity) { this.agentActivity = agentActivity; }

	/** Get the Activity that AIC is doing or null
	 * @return The Activity that AIC is doing or null
	 */
	public String getAgentActivity() { return agentActivity; }


    public void setUptime(long uptime) { this.uptime = uptime;}

    public long getUptime() { return uptime; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", control="+control+
			", engineering="+engineering+
			", operational="+operational+
			", lastStatus="+lastStatus+
			", agentInControl="+agentInControl+
			", agentName="+agentName+
			", agentActivity="+agentActivity+
		    ", uptime="+uptime+
		    "]";
	}
	// Hand generated code.

} // class def. [ID_DONE].

