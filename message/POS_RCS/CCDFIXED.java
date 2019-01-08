package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDFIXED.<br>
 *  Supplies the information required to slew the telescope to a fixed (ExtraSolar) source<br>
 *  configure the instrument and take an exposure.<br>
 *  Module code 690200<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>position - position to slew to.</dd>
 * <dd>&nbsp;values: any valid ngat.astrometry.Position - ie. a (RA,dec) pair</dd>
 * <dd>sourceId - position to slew to.</dd>
 * <dd>&nbsp;values: a valid Id for the Target.</dd>
 * <dd>sourceType - class/type of target for FITS headers</dd>
 * <dd>&nbsp;values: a valid target class</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 *    none.
 * </dl>
 * <br>
 */
public class CCDFIXED extends CCDOBSERVE {

	// Variables.

	/** The position to slew to.*/
	protected Position position;

	/** The position to slew to.*/
	protected String sourceId;

	/** The class/type of target for FITS headers*/
	protected String sourceType;

	/** Create a CCDFIXED with specified id.
	 * @param id The unique id of this CCDFIXED.
	 */
	public CCDFIXED(String id) { super(id); }

	/** Create a CCDFIXED with specified id and parameters.
	 * @param id The unique id of this CCDFIXED.
	 * @param position The position to slew to.
	 * @param sourceId The position to slew to.
	 * @param sourceType The class/type of target for FITS headers
	 */
	public CCDFIXED(
	         String id,
	         Position position,
	         String sourceId,
	         String sourceType) {
	         super(id);
	           this.position = position;
	           this.sourceId = sourceId;
	           this.sourceType = sourceType;
	         }

	/** Set the position to slew to.
	 * @param position The position to slew to.
	 */
	public void setPosition(Position position) { this.position = position; }

	/** Get the position to slew to.
	 * @return The position to slew to.
	 */
	public Position getPosition() { return position; }

	/** Set the position to slew to.
	 * @param sourceId The position to slew to.
	 */
	public void setSourceId(String sourceId) { this.sourceId = sourceId; }

	/** Get the position to slew to.
	 * @return The position to slew to.
	 */
	public String getSourceId() { return sourceId; }

	/** Set the class/type of target for FITS headers
	 * @param sourceType The class/type of target for FITS headers
	 */
	public void setSourceType(String sourceType) { this.sourceType = sourceType; }

	/** Get the class/type of target for FITS headers
	 * @return The class/type of target for FITS headers
	 */
	public String getSourceType() { return sourceType; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", position="+position+
			", sourceId="+sourceId+
			", sourceType="+sourceType+"]";
	}
	// Hand generated code.

} // class def. [CCDFIXED].

