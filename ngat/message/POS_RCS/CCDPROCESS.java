package ngat.message.POS_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** POS_RCS COMMAND: CCDPROCESS.<br>
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
public class CCDPROCESS extends POS_TO_RCS {

	// Constants.

	/** Constant: Indicates processing to generate a color JPEG from at least 2 frames.*/
	public static final int COLOR_JPEG = 690501;

	/** Constant: Indicates processing to return the best (based on image sharpness) of a series of JPEGs.*/
	public static final int BEST_JPEG = 690502;

	/** Constant: Indicates processing should return a greyscale JPEG (single frame or average of several).*/
	public static final int JPEG = 690503;

	/** Constant: Indicates processing to return the best (based on image sharpness) of a series of FITS images.*/
	public static final int BEST_FITS = 690504;

	/** Constant: Indicates processing should return a greyscale FITS image (single frame or average of several).*/
	public static final int FITS = 690505;

	/** Constant: Indicates processing should return a mosaic of the suplied images as a single FITS file.*/
	public static final int MOSAIC_FITS = 690506;

	/** Constant: Indicates processing should return a mosaic of the suplied images as a single JPEG.*/
	public static final int MOSAIC_JPEG = 690507;

	/** Constant: Indicates a planetary source type.*/
	public static final int PLANETARY = 690508;

	/** Constant: Indicates an extended source type - e.g. galaxy, nebula.*/
	public static final int GALACTIC = 690509;

	/** Constant: Indicates a stellar source type.*/
	public static final int STELLAR = 690510;

	/** Constant: Indicates that one or more of the specifed images is missing or corrupted.*/
	public static final int MISSING_IMAGE = 690512;

	/** Constant: Indicates that a fault during the IMCOLOR processing.*/
	public static final int IMCOLOR_FAULT = 690513;

	/** Constant: Indicates that a fault during the IMBEST processing.*/
	public static final int IMBEST_FAULT = 690514;

	/** Constant: Indicates that a fault occurred during JPEG processing.*/
	public static final int JPEG_FAULT = 690515;

	/** Constant: Indicates that a fault occurred during FITS compression.*/
	public static final int COMPRESSION_FAULT = 690516;

	/** Constant: Indicates that a fault occurred during image transfer.*/
	public static final int TRANSFER_FAULT = 690517;

	/** Constant: Indicates that a fault has occurred during mosaicing.*/
	public static final int MOSAIC_FAULT = 690518;

	/** Constant: Indicates that the image should be transferred to the server-pc.*/
	public static final int SERVERPC = 0;

	// Variables.

	/** The destination for the processed data.*/
	protected int destination;

	/** The type of processing to perform.*/
	protected int type;

	/** The frame number of the first frame to process.*/
	protected long startFrame;

	/** The frame number of the last frame to process.*/
	protected long endFrame;

	/** The type of source object - for processing algorithm.*/
	protected int sourceType;

	/** Create a CCDPROCESS with specified id.
	 * @param id The unique id of this CCDPROCESS.
	 */
	public CCDPROCESS(String id) { super(id); }

	/** Create a CCDPROCESS with specified id and parameters.
	 * @param id The unique id of this CCDPROCESS.
	 * @param destination The destination for the processed data.
	 * @param type The type of processing to perform.
	 * @param startFrame The frame number of the first frame to process.
	 * @param endFrame The frame number of the last frame to process.
	 * @param sourceType The type of source object - for processing algorithm.
	 */
	public CCDPROCESS(
	         String id,
	         int destination,
	         int type,
	         long startFrame,
	         long endFrame,
	         int sourceType) {
	         super(id);
	           this.destination = destination;
	           this.type = type;
	           this.startFrame = startFrame;
	           this.endFrame = endFrame;
	           this.sourceType = sourceType;
	         }

	/** Set the destination for the processed data.
	 * @param destination The destination for the processed data.
	 */
	public void setDestination(int destination) { this.destination = destination; }

	/** Get the destination for the processed data.
	 * @return The destination for the processed data.
	 */
	public int getDestination() { return destination; }

	/** Set the type of processing to perform.
	 * @param type The type of processing to perform.
	 */
	public void setType(int type) { this.type = type; }

	/** Get the type of processing to perform.
	 * @return The type of processing to perform.
	 */
	public int getType() { return type; }

	/** Set the frame number of the first frame to process.
	 * @param startFrame The frame number of the first frame to process.
	 */
	public void setStartFrame(long startFrame) { this.startFrame = startFrame; }

	/** Get the frame number of the first frame to process.
	 * @return The frame number of the first frame to process.
	 */
	public long getStartFrame() { return startFrame; }

	/** Set the frame number of the last frame to process.
	 * @param endFrame The frame number of the last frame to process.
	 */
	public void setEndFrame(long endFrame) { this.endFrame = endFrame; }

	/** Get the frame number of the last frame to process.
	 * @return The frame number of the last frame to process.
	 */
	public long getEndFrame() { return endFrame; }

	/** Set the type of source object - for processing algorithm.
	 * @param sourceType The type of source object - for processing algorithm.
	 */
	public void setSourceType(int sourceType) { this.sourceType = sourceType; }

	/** Get the type of source object - for processing algorithm.
	 * @return The type of source object - for processing algorithm.
	 */
	public int getSourceType() { return sourceType; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", destination="+destination+
			", type="+type+
			", startFrame="+startFrame+
			", endFrame="+endFrame+
			", sourceType="+sourceType+"]";
	}
	// Hand generated code.

} // class def. [CCDPROCESS].

