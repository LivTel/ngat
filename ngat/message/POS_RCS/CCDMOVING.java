package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDMOVING.<br>
 *  Supplies the information required to slew the telescope to <br>
 *  a moving (Solar System) source, configure the instrument <br>
 *  and take an exposure.<br>
 *  Module code 690300<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>srcId - the id of the source to observe</dd>
 * <dd>&nbsp;values: currently {MOON} only</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDMOVING extends CCDOBSERVE {

	// Constants.

	/** Constant: Indicates source is the MOON.*/
	public static final int MOON = 690301;

	/** Constant: Indicates source is MERCURY.*/
	public static final int MERCURY = 690302;

	/** Constant: Indicates source is VENUS.*/
	public static final int VENUS = 690303;

	/** Constant: Indicates source is MARS.*/
	public static final int MARS = 690304;

	/** Constant: Indicates source is JUPITER.*/
	public static final int JUPITER = 690305;

	/** Constant: Indicates source is SATURN.*/
	public static final int SATURN = 690306;

	/** Constant: Indicates source is URANUS.*/
	public static final int URANUS = 690307;

	/** Constant: Indicates source is NEPTUNE.*/
	public static final int NEPTUNE = 690308;

	/** Constant: Indicates source is PLUTO.*/
	public static final int PLUTO = 690309;

	// Variables.

	/** The the id of the source to observe*/
	protected int srcId;

	/** Create a CCDMOVING with specified id.
	 * @param id The unique id of this CCDMOVING.
	 */
	public CCDMOVING(String id) { super(id); }

	/** Create a CCDMOVING with specified id and parameters.
	 * @param id The unique id of this CCDMOVING.
	 * @param srcId The the id of the source to observe
	 */
	public CCDMOVING(
	         String id,
	         int srcId) {
	         super(id);
	           this.srcId = srcId;
	         }

	/** Set the the id of the source to observe
	 * @param srcId The the id of the source to observe
	 */
	public void setSrcId(int srcId) { this.srcId = srcId; }

	/** Get the the id of the source to observe
	 * @return The the id of the source to observe
	 */
	public int getSrcId() { return srcId; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", srcId="+srcId+"]";
	}
	// Hand generated code.

} // class def. [CCDMOVING].

