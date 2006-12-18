package ngat.message.RCS_TCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
/** RCS_TCS COMMAND: AGCENTROID.<br>
 * Instructs the TCS to centroid on the current guide source.<br>
 * <dl>
 * <dt>command params: </dt>
 *    none.
 * <dt>ack params: </dt>
 *    none.
 * <dt>done params: </dt>
 * <dd>xPixel - X -axis ixel position of guide star on CCD.</dd>
 * <dd>yPixel - Y -axis ixel position of guide star on CCD.</dd>
 * <dd>fwhm - FWHM for centroid source.</dd>
 * <dd>peak - Peak pixel value of source.</dd>
 * </dl>
 * <br>
 */
public class AGCENTROID extends RCS_TO_TCS {

	/** Create a AGCENTROID with specified id.
	 * @param id The unique id of this AGCENTROID.
	 */
	public AGCENTROID(String id) { super(id); }

	// Hand generated code.

} // class def. [AGCENTROID].

