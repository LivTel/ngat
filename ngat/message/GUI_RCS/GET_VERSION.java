package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND: GET_VERSION.<br>
 *  Request RCS version information.<br>
 * <br>
 *  Module code: 682100<br>
 * <br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>majorVersion - Major version number</dd>
 * <dd>minorVersion - Minor version number</dd>
 * <dd>patchVersion - Patch version number</dd>
 * <dd>releaseName - Release name</dd>
 * <dd>buildNumber - Build number</dd>
 * <dd>buildDate - Build date/time</dd>
 * </dl>
 * <br>
 */
public class GET_VERSION extends GUI_TO_RCS {

	/** Create a GET_VERSION with specified id.
	 * @param id The unique id of this GET_VERSION.
	 */
	public GET_VERSION(String id) { super(id); }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+"]";
	}
	// Hand generated code.

} // class def. [GET_VERSION].

