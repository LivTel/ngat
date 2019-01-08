package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates Exposure update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public class ExposureInfo extends TelemetryInfo {

    protected String fileName;

    protected int countCompletedExposures;

    /** Observation Path.*/
    protected String obsPathName;

    public ExposureInfo(long time) {
	super(time);
    }

    public String getObsPathName() { return obsPathName; }

    public void setObsPathName(String obsPathName) { this.obsPathName = obsPathName; }
    
    public void setFileName(String fileName) { this.fileName = fileName ; }

    public String getFileName() { return fileName; }

    public void setCountCompletedExposures(int c) { this.countCompletedExposures = c; }

    public int getCountCompletedExposures() { return countCompletedExposures; }

    public String toString() { 
	return "ExposureInfo: "+time+" : FileName="+fileName+", exposures="+countCompletedExposures;
    }

}
