package ngat.message.GUI_RCS;

import java.io.*;
import java.util.*;

import ngat.phase2.*;

/** Encapsulates DPRT update information from RCS to GUI which will be
 * transferred in a TELEMETRY_DONE.
 */
public class ReductionInfo extends TelemetryInfo {

    /** Observation Path.*/
    protected String obsPathName;

    protected String fileName;

    protected int countReducedExposures;
    
    protected double seeing;
    
    protected double photometricity;
    
    protected double skyBrightness;

    protected int counts;

    protected int xpix;

    protected int ypix;
    
    public ReductionInfo(long time) {
	super(time);
    }

    public String getObsPathName() { return obsPathName; }

    public void setObsPathName(String obsPathName) { this.obsPathName = obsPathName; }

    public void setFileName(String fileName) { this.fileName = fileName ; }

    public String getFileName() { return fileName; }

    public void setCountReducedExposures(int c) { this.countReducedExposures = c; }

    public int getCountReducedExposures() { return countReducedExposures; }

    public void setSeeing(double seeing) { this.seeing = seeing; }

    public double getSeeing() { return seeing; }

    public void setCounts(int counts) { this.counts = counts;}

    public int getCounts() { return counts; }

    public void setXpix(int xpix) { this.xpix = xpix; }

    public int getXpix() { return xpix; }

    public void setYpix(int ypix) { this.ypix = ypix; }

    public int getYpix() { return ypix; }

    public void setPhotometricity(double photometricity) { this.photometricity = photometricity; }

    public double getPhotometricity() { return photometricity; }

    public void setSkyBrightness(double skyBrightness) { this.skyBrightness = skyBrightness; }

    public double getSkyBrightness() { return skyBrightness; }

    public String toString() { 
	return "ReductionInfo: "+time+" : FileName="+fileName+
	    ", reduced="+countReducedExposures+
	    ", seeing="+seeing+
	    ", photom="+photometricity+
	    ", skybright="+skyBrightness+
	    ", counts="+counts+
	    ", xpix="+xpix+
	    ", ypix="+ypix;
    }
    
}
