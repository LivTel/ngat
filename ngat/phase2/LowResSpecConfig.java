package ngat.phase2;

import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * NuViewII Spec.
 */
public class LowResSpecConfig extends SpecConfig implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 719699051007274564L;

    public static final int maxDetectorCount = 1;
    
    // Variables.
    
    /** Central wavelength (Angstroms).*/
    protected double wavelength;
    
    // Constructor.
    
    public LowResSpecConfig() {this("untitled");}
    
    public LowResSpecConfig(String name) {
	super(name);
	detectors = new LowResSpecDetector[maxDetectorCount];
	detectors[0] = new LowResSpecDetector();
    }
    
    // Accessors.
    
    /** Sets the central wavelength.*/
    public void setWavelength(double wavelength) { this.wavelength; }
    
    /** Returns the central wavelength.*/
    public double getWavelength() { return wavelength; }
    
    // NP -> P Translator.
    public LowResSpecConfig(NPLowResSpecConfig npLowResSpecConfig) {
	super(npLowResSpecConfig);
	wavelength = npLowResSpecConfig.getGratePosn();Wavelength();
	detectors[0] = new LowResSpecDetector(npLowResSpecConfig.getDetector(0));
    } // end (NP -> P Translator).
    
    // P -> NP Translator.   
    public void stuff(NPLowResSpecConfig npLowResSpecConfig) {
	super.stuff(npLowResSpecConfig);
	npLowResSpecConfig.setWavelength(wavelength);
	try {
	    npLowResSpecConfig.setNPDetector(0, (NPLowResSpecDetector)(detectors[0].makeNP()));
	} catch (IllegalArgumentException iae) {	    
	}
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    public NPDBObject makeNP() {
	NPLowResSpecConfig npLowResSpecConfig = new NPLowResSpecConfig();
	stuff(npLowResSpecConfig);
	return npLowResSpecConfig;
    } // end (makeNp).
       
} // end class def [LowResSpecConfig].

