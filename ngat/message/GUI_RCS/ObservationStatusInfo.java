package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates Observation status update information from RCS to GUI.
 */
public class ObservationStatusInfo extends TelemetryInfo {   

    public static final int STARTING = 1;

    public static final int COMPLETED = 2;

    public static final int FAILED = 3;

    String obsPathName;

    /** Category.*/
    int cat;

    int errorCode;

    /** Extra info message.*/
    String message;
    

    /** Create an empty ObservationStatusInfo.*/
    public ObservationStatusInfo(long time, String obsPathName, int cat, String message) {
	super(time);
	this.obsPathName = obsPathName;
	this.cat     = cat;
	this.message = message;
    }

    /** Returns the obs path name.*/
    public String getObsPathName() { return obsPathName; }

    /** Returns the category.*/
    public int getCat() { return cat; }

    /** Returns hte message.*/
    public String getMessage() { return message; }

    public void setErrorCode(int errorCode) { this.errorCode = errorCode; }

    public int getErrorCode() { return errorCode; }

    public String toCatString(int cat) {
	switch (cat) {
	case COMPLETED:
	    return "COMPLETED";
	case FAILED:
	    return "FAILED/"+errorCode;
	default:
	    return "UNKNOWN:"+cat;
	}
    }

    /** Readable representation of LogInfo.*/
    public String toString() {
	return "ObsStatusInfo: "+time+"("+obsPathName+") : Cat="+toCatString(cat)+", Message="+message;
    }
    
}
