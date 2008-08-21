package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: SET_EXTINCTION.<br>
 *  Set the current seeing used by the OSS.<br>
 * <br>
 *  Module code: 681900<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>extinction - extinction value</dd>
 * <dd>&nbsp;values: { PHOTOMETRIC |  SPECTROSCOPIC }</dd>
 * <dd>source -  extinction source</dd>
 * <dd>&nbsp;values: { MANUAL | EXTERNAL}</dd>
 * <dd>sourceName -  source provider identity</dd>
 * <dd>&nbsp;values: a valid source name</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class SET_EXTINCTION extends GUI_TO_RCS {

	// Constants.

	/** Constant: Indicates that the extinction is good */
	public static final int PHOTOMETRIC = 681901;

	/** Constant: Indicates that the extinction is bad*/
	public static final int SPECTROSCOPIC = 681902;

	/** Constant: Indicates  manual source*/
	public static final int MANUAL_SOURCE = 681910;

	/** Constant: Indicates  external source*/
	public static final int EXTERNAL_SOURCE = 681911;

	// Variables.

	/** The extinction value*/
	protected int extinction;

	/** The  extinction source*/
	protected int source;

	/** The  source provider identity*/
	protected String sourceName;

	/** Create a SET_EXTINCTION with specified id.
	 * @param id The unique id of this SET_EXTINCTION.
	 */
	public SET_EXTINCTION(String id) { super(id); }

	/** Create a SET_EXTINCTION with specified id and parameters.
	 * @param id The unique id of this SET_EXTINCTION.
	 * @param extinction The extinction value
	 * @param source The  extinction source
	 * @param sourceName The  source provider identity
	 */
	public SET_EXTINCTION(
	         String id,
	         int extinction,
	         int source,
	         String sourceName) {
	         super(id);
	           this.extinction = extinction;
	           this.source = source;
	           this.sourceName = sourceName;
	         }

	/** Set the extinction value
	 * @param extinction The extinction value
	 */
	public void setExtinction(int extinction) { this.extinction = extinction; }

	/** Get the extinction value
	 * @return The extinction value
	 */
	public int getExtinction() { return extinction; }

	/** Set the  extinction source
	 * @param source The  extinction source
	 */
	public void setSource(int source) { this.source = source; }

	/** Get the  extinction source
	 * @return The  extinction source
	 */
	public int getSource() { return source; }

	/** Set the  source provider identity
	 * @param sourceName The  source provider identity
	 */
	public void setSourceName(String sourceName) { this.sourceName = sourceName; }

	/** Get the  source provider identity
	 * @return The  source provider identity
	 */
	public String getSourceName() { return sourceName; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", extinction="+extinction+
			", source="+source+
			", sourceName="+sourceName+"]";
	}
	// Hand generated code.

} // class def. [SET_EXTINCTION].

