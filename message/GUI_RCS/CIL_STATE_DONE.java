package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: CIL_STATE_DONE.<br>
 *  Request the RCS to bind/release CIL port.<br>
 * <br>
 *  Module code: 681200<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>option - null</dd>
 * <dd>&nbsp;values: indicates whether to bind or relase the CIL port</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>host - destination host</dd>
 * <dd>sendPort - port RCS is sending from</dd>
 * <dd>destPort - port RCS is sending to</dd>
 * <dd>active - whether the socket is active (or closed)</dd>
 * </dl>
 * <br>
 */
public class CIL_STATE_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The destination host*/
	protected String host;

	/** The port RCS is sending from*/
	protected int sendPort;

	/** The port RCS is sending to*/
	protected int destPort;

	/** The whether the socket is active (or closed)*/
	protected boolean active;

	/** Create a CIL_STATE_DONE with specified id.
	 * @param id The unique id of this CIL_STATE_DONE.
	 */
	public CIL_STATE_DONE (String id) { super(id); }

	/** Set the destination host
	 * @param host The destination host.
	 */
	public void setHost(String host) { this.host = host; }

	/** Get the destination host
	 * @return The destination host
	 */
	public String getHost() { return host; }

	/** Set the port RCS is sending from
	 * @param sendPort The port RCS is sending from.
	 */
	public void setSendPort(int sendPort) { this.sendPort = sendPort; }

	/** Get the port RCS is sending from
	 * @return The port RCS is sending from
	 */
	public int getSendPort() { return sendPort; }

	/** Set the port RCS is sending to
	 * @param destPort The port RCS is sending to.
	 */
	public void setDestPort(int destPort) { this.destPort = destPort; }

	/** Get the port RCS is sending to
	 * @return The port RCS is sending to
	 */
	public int getDestPort() { return destPort; }

	/** Set the whether the socket is active (or closed)
	 * @param active The whether the socket is active (or closed).
	 */
	public void setActive(boolean active) { this.active = active; }

	/** Get the whether the socket is active (or closed)
	 * @return The whether the socket is active (or closed)
	 */
	public boolean getActive() { return active; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", host="+host+
			", sendPort="+sendPort+
			", destPort="+destPort+
			", active="+active+"]";
	}
	// Hand generated code.

} // class def. [CIL_STATE_DONE].

