package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Hand Generated.


public class CCDConfig extends InstrumentConfig implements Serializable {

     // Variables.

    public static final int maxDetectorCount = 1;
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 759627829525815554L;
    
    /** Default clear filter.*/
    public static final String CLEAR = "clear";

    /** Identity of filter in upper wheel. */
    protected String upperFilterWheel;
    
    /** Identity of filter in lower wheel. */
    protected String lowerFilterWheel;

    // Constructor.
    
    public CCDConfig() {this("untitled");}
    
    /** Make a CCD-Config, Adds 1 CCD-Detector only.*/
    public CCDConfig(String name) {
	super(name);
	detectors = new CCDDetector[maxDetectorCount];
	detectors[0] = new CCDDetector();
    }
    
    // Accessors.
    
    /** Sets the Identity of filter in upper wheel.*/
    public void setUpperFilterWheel(String in) {
	
	this.upperFilterWheel = in;
    }
    
    /** Returns the Identity of filter in upper wheel. */
    public String getUpperFilterWheel() {
	
	return upperFilterWheel;
    }
    
    /** Sets the Identity of filter in lower wheel.*/
    public void setLowerFilterWheel(String in) {
	 
	this.lowerFilterWheel = in;
    }
    
    /** Returns the Identity of filter in lower wheel . */
    public String getLowerFilterWheel() {
	
	return lowerFilterWheel;
    }
    
    public int getMaxDetectorCount() {
	
	return maxDetectorCount; 
    }
        
    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	System.err.println("Checking CCDC with another one: "+
			   this.toString()+" with "+other.toString());
	if (! super.sameAs(other))
	    return false;
	// Ok we know they are the same class now..
	CCDConfig cother = (CCDConfig)other;

	if (! upperFilterWheel.equals(cother.getUpperFilterWheel()))
	    return false;
	if (! lowerFilterWheel.equals(cother.getLowerFilterWheel()))
	    return false;
	if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
	    return false;
	if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
	    return false;
	// Remember to look at CalBef and CalAft and windows..
	return true;
    }
    
    /** Create a Default CCDConfig.*/
    public static CCDConfig getDefault() {
	CCDConfig DEFAULT = new CCDConfig("DEFAULT");
	DEFAULT.setDescription("Default CCDConfig");
	DEFAULT.setUpperFilterWheel(CLEAR);
	DEFAULT.setLowerFilterWheel(CLEAR);
	CCDDetector detector = new CCDDetector();
	detector.setXBin(1);
	detector.setYBin(1);
	detector.clearAllWindows();
	DEFAULT.setDetector(0, detector); 	
	return DEFAULT;
    }
    
    // Clone Constructor.
    public NPDBObject copy() {
	try {
	    return (CCDConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<ccdConfig name = '"+name+"'>");
	out.println(tab(level+1)+"<upperFilterWheel>"+upperFilterWheel+"</upperFilterWheel>");
	out.println(tab(level+1)+"<lowerFilterWheel>"+lowerFilterWheel+"</lowerFilterWheel>");
	detectors[0].writeXml(out,level+1);
	out.println(tab(level)+"</ccdConfig>");
    } // end (write).

    public String toString() { return "CCDConfig: "+name+
				   " : U "+upperFilterWheel+
				   ", L "+lowerFilterWheel+
				   ", Bin: ["+detectors[0].getXBin()+" x "+detectors[0].getYBin()+"]";
    }

} // end class def [CCDConfig].

