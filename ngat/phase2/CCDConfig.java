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

    public int getMaxDetectorCount() { return maxDetectorCount; }
    
    /** Identity of filter in upper wheel. */
    protected String upperFilterWheel;
    
    /** Identity of filter in lower wheel. */
    protected String lowerFilterWheel;

    // Constructor.
    
    public CCDConfig() {this("untitled");}
    
    /** Make a CCD-Config, Adds 1 CCD-Detector only.*/
    public CCDConfig(String name) {
	super(name);
	try {
	    addDetector(new CCDDetector());
	} catch (IllegalArgumentException iae) {
	}
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
    
    // Descendant Mutators.
         
    // NP -> P Translator.
    public CCDConfig(NPCCDConfig npCCDConfig) {
	super(npCCDConfig);
	upperFilterWheel = npCCDConfig.getUpperFilterWheel();
	lowerFilterWheel = npCCDConfig.getLowerFilterWheel();
	description = npCCDConfig.getDescription();
	Iterator it = npCCDConfig.listAllNPDetectors();
	while (it.hasNext()) {
	    try {
		addDetector(new CCDDetector((NPCCDDetector)it.next()));
	    } catch (IllegalArgumentException iae) {
	    }
	}
    } // end (NP -> P Translator).
    
    
    // P -> NP Translator.
    public void stuff(NPCCDConfig npCCDConfig) {
	super.stuff(npCCDConfig);
	npCCDConfig.setUpperFilterWheel(getUpperFilterWheel());
	npCCDConfig.setLowerFilterWheel(getLowerFilterWheel());
	npCCDConfig.setSetupTime(getSetupTime());
	npCCDConfig.setDescription(getDescription());
	// Copy all detector references to NP
	Iterator it = listAllDetectors();
	while (it.hasNext()) { 
	    try {
		npCCDConfig.addNPDetector((NPCCDDetector)(((CCDDetector)it.next()).makeNP()));
	    } catch (IllegalArgumentException iae) {
		
	    }
	}
    } // end (P -> NP Translator).
    

    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPCCDConfig npCCDConfig = new NPCCDConfig();
	stuff(npCCDConfig);
	return npCCDConfig;
    } // end (makeNP).
    
} // end class def [CCDConfig].
