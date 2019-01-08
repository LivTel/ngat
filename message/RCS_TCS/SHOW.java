package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: SHOW.<br>
 * Instructs TCS to deliver status information.<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>key - keyword to indicate which set of data to deliver.</dd>
 * <dd>&nbsp;values: { ALL | ASTROMETRY | AUTOGUIDER | CALIBRATE | FOCAL_STATION | LIMITS | MECHANISMS | METEOROLOGY | SOURCE | STATE | TIME | VERSION }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>status - current TCS and telescope status information.</dd>
 * </dl>
 * <br>
 */
public class SHOW extends RCS_TO_TCS {

	// Constants.

	/** Constant: Indicates all status data should be delivered.*/
	public static final int ALL = 0;

	/** Constant: Indicates atmospheric parameters for refraction calculations should be delivered.*/
	public static final int ASTROMETRY = 1;

	/** Constant: Indicates autoguider status should be delivered.*/
	public static final int AUTOGUIDER = 2;

	/** Constant: Indicates  calibration information should be delivered*/
	public static final int CALIBRATE = 3;

	/** Constant: Indicates  hardware and software selection should be delivered*/
	public static final int FOCUS = 4;

	/** Constant: Indicates  position limits should be delivered*/
	public static final int LIMITS = 5;

	/** Constant: Indicates  status and positions of mechanisms should be delivered*/
	public static final int MECHANISMS = 6;

	/** Constant: Indicates  weather measurements should be delivered*/
	public static final int METEOROLOGY = 7;

	/** Constant: Indicates  current source parameters should be delivered*/
	public static final int SOURCE = 8;

	/** Constant: Indicates  TCS and telescope state should be delivered*/
	public static final int STATE = 9;

	/** Constant: Indicates  current time in various formats should be delivered*/
	public static final int TIME = 10;

	/** Constant: Indicates  TCS version information should be delivered!*/
	public static final int VERSION = 11;

	// Variables.

	/** The keyword to indicate which set of data to deliver.*/
	protected int key;

	/** Create a SHOW with specified id.
	 * @param id The unique id of this SHOW.
	 */
	public SHOW(String id) { super(id); }

	/** Create a SHOW with specified id and parameters.
	 * @param id The unique id of this SHOW.
	 * @param key The keyword to indicate which set of data to deliver.
	 */
	public SHOW(
	         String id,
	         int key) {
	         super(id);
	           this.key = key;
	         }

	/** Set the keyword to indicate which set of data to deliver.
	 * @param key The keyword to indicate which set of data to deliver.
	 */
	public void setKey(int key) { this.key = key; }

	/** Get the keyword to indicate which set of data to deliver.
	 * @return The keyword to indicate which set of data to deliver.
	 */
	public int getKey() { return key; }

	// Hand generated code.

} // class def. [SHOW].

