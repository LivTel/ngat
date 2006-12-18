package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND_DONE: CCDPROCESS_DONE.<br>
 *  Controls the processing and return of CCD image data.<br>
 *  Module code: 690500<br>
 * <dl>
 * <dt>command params: </dt>
 * <dd>destination - destination for the processed data.</dd>
 * <dd>&nbsp;values: { SERVERPC (= 0) | ALTERNATIVE (= 1-n) }</dd>
 * <dd>type - type of processing to perform.</dd>
 * <dd>&nbsp;values: { COLO[U]R_JPEG | BEST_JPEG | JPEG | BEST_FITS | FITS }</dd>
 * <dd>startFrame - frame number of the first frame to process.</dd>
 * <dd>&nbsp;values: a valid frame number as returned by CCDOBSERVE</dd>
 * <dd>endFrame - frame number of the last frame to process.</dd>
 * <dd>&nbsp;values: a valid frame number as returned by CCDOBSERVE</dd>
 * <dd>sourceType - type of source object - for processing algorithm.</dd>
 * <dd>&nbsp;values: { PLANETARY | STELLAR | GALACTIC }</dd>
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>filename - name or URL of the resulting image file.</dd>
 * </dl>
 * <br>
 */
public class CCDPROCESS_DONE extends POS_TO_RCS_DONE {

	// Variables.

	/** The name or URL of the resulting image file.*/
	protected String filename;

	/** Create a CCDPROCESS_DONE with specified id.
	 * @param id The unique id of this CCDPROCESS_DONE.
	 */
	public CCDPROCESS_DONE (String id) { super(id); }

	/** Set the name or URL of the resulting image file.
	 * @param filename The name or URL of the resulting image file..
	 */
	public void setFilename(String filename) { this.filename = filename; }

	/** Get the name or URL of the resulting image file.
	 * @return The name or URL of the resulting image file.
	 */
	public String getFilename() { return filename; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", filename="+filename+"]";
	}
	// Hand generated code.

} // class def. [CCDPROCESS_DONE].

