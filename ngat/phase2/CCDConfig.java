package ngat.phase2;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
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
    public void setUpperFilterWheel(String in) { this.upperFilterWheel = in;}
    
    /** Returns the Identity of filter in upper wheel. */
    public String getUpperFilterWheel() { return upperFilterWheel;}
    
    /** Sets the Identity of filter in lower wheel.*/
    public void setLowerFilterWheel(String in) { this.lowerFilterWheel = in;}
    
    /** Returns the Identity of filter in lower wheel . */
    public String getLowerFilterWheel() { return lowerFilterWheel;}

    public int getMaxDetectorCount() { return maxDetectorCount; }
    
    // Descendant Mutators.
         
    // NP -> P Translator.
    public CCDConfig(NPCCDConfig npCCDConfig) {
	super(npCCDConfig);
	upperFilterWheel = npCCDConfig.getUpperFilterWheel();
	lowerFilterWheel = npCCDConfig.getLowerFilterWheel();
	detectors[0] = new CCDDetector(npCCDConfig.getDetector(0));
    } // end (NP -> P Translator).
        
    // P -> NP Translator.
    public void stuff(NPCCDConfig npCCDConfig) {
	super.stuff(npCCDConfig);
	npCCDConfig.setUpperFilterWheel(getUpperFilterWheel());
	npCCDConfig.setLowerFilterWheel(getLowerFilterWheel());	
	try {
	    npCCDConfig.setNPDetector(0, (NPCCDDetector)(detectors[0].makeNP()));
	} catch (IllegalArgumentException iae) {	    
	}
    } // end (P -> NP Translator).
    

    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPCCDConfig npCCDConfig = new NPCCDConfig();
	stuff(npCCDConfig);
	return npCCDConfig;
    } // end (makeNP).
    
} // end class def [CCDConfig].
