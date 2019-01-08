package ngat.message.GUI_RCS;

import java.util.*;
import ngat.util.*;
import ngat.phase2.*;
import ngat.astrometry.*;
import ngat.message.base.*;
/** GUI_RCS COMMAND_DONE: GET_VERSION_DONE.<br>
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
public class GET_VERSION_DONE extends GUI_TO_RCS_DONE {

	// Variables.

	/** The Major version number*/
	protected String majorVersion;

	/** The Minor version number*/
	protected String minorVersion;

	/** The Patch version number*/
	protected String patchVersion;

	/** The Release name*/
	protected String releaseName;

	/** The Build number*/
	protected String buildNumber;

	/** The Build date/time*/
	protected String buildDate;

	/** Create a GET_VERSION_DONE with specified id.
	 * @param id The unique id of this GET_VERSION_DONE.
	 */
	public GET_VERSION_DONE (String id) { super(id); }

	/** Set the Major version number
	 * @param majorVersion The Major version number.
	 */
	public void setMajorVersion(String majorVersion) { this.majorVersion = majorVersion; }

	/** Get the Major version number
	 * @return The Major version number
	 */
	public String getMajorVersion() { return majorVersion; }

	/** Set the Minor version number
	 * @param minorVersion The Minor version number.
	 */
	public void setMinorVersion(String minorVersion) { this.minorVersion = minorVersion; }

	/** Get the Minor version number
	 * @return The Minor version number
	 */
	public String getMinorVersion() { return minorVersion; }

	/** Set the Patch version number
	 * @param patchVersion The Patch version number.
	 */
	public void setPatchVersion(String patchVersion) { this.patchVersion = patchVersion; }

	/** Get the Patch version number
	 * @return The Patch version number
	 */
	public String getPatchVersion() { return patchVersion; }

	/** Set the Release name
	 * @param releaseName The Release name.
	 */
	public void setReleaseName(String releaseName) { this.releaseName = releaseName; }

	/** Get the Release name
	 * @return The Release name
	 */
	public String getReleaseName() { return releaseName; }

	/** Set the Build number
	 * @param buildNumber The Build number.
	 */
	public void setBuildNumber(String buildNumber) { this.buildNumber = buildNumber; }

	/** Get the Build number
	 * @return The Build number
	 */
	public String getBuildNumber() { return buildNumber; }

	/** Set the Build date/time
	 * @param buildDate The Build date/time.
	 */
	public void setBuildDate(String buildDate) { this.buildDate = buildDate; }

	/** Get the Build date/time
	 * @return The Build date/time
	 */
	public String getBuildDate() { return buildDate; }

	/** Returns a String representation of the object.*/
	public String toString() {
		return "["+this.getClass().getName()+":"+
			getId()+
			", majorVersion="+majorVersion+
			", minorVersion="+minorVersion+
			", patchVersion="+patchVersion+
			", releaseName="+releaseName+
			", buildNumber="+buildNumber+
			", buildDate="+buildDate+"]";
	}
	// Hand generated code.

} // class def. [GET_VERSION_DONE].

