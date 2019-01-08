package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGSELECT.<br>
 * Instructs the TCS to select a specified autoguider.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>autoguider - autoguider to use.</dd>
 * <dd>&nbsp;values: { from config file }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class AGSELECT extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates only available autoguider for LT.*/
	public static final int CASSEGRAIN = 0;

	// Variables.

	/** The autoguider to use.*/
	protected int autoguider;

	/** Create a AGSELECT with specified id.
	 * @param id The unique id of this AGSELECT.
	 */
	public AGSELECT(String id) { super(id); }

	/** Create a AGSELECT with specified id and parameters.
	 * @param id The unique id of this AGSELECT.
	 * @param autoguider The autoguider to use.
	 */
	public AGSELECT(
	         String id,
	         int autoguider) {
	         super(id);
	           this.autoguider = autoguider;
	         }

	/** Set the autoguider to use.
	 * @param autoguider The autoguider to use.
	 */
	public void setAutoguider(int autoguider) { this.autoguider = autoguider; }

	/** Get the autoguider to use.
	 * @return The autoguider to use.
	 */
	public int getAutoguider() { return autoguider; }

	// Hand generated code.

} // class def. [AGSELECT].

