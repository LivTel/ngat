package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Fixed Format Spectrograph Config, for a spectrograpg which cannot configure it's wavelength range.
 * Currently used for FTNSpec and FTSSpec.
 */
public class FixedFormatSpecConfig extends SpecConfig implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.
     */
    private static final long serialVersionUID = 6586864834989546802L;

    public static final int maxDetectorCount = 1;
       
    // Constructor.
    
    public FixedFormatSpecConfig() {this("untitled");}
    
    public FixedFormatSpecConfig(String name) {
	super(name);
	detectors = new FixedFormatSpecDetector[maxDetectorCount];
	detectors[0] = new FixedFormatSpecDetector();
    }
    
    // Accessors.
    
    public int getMaxDetectorCount() {	
	return maxDetectorCount; 
    }

    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	System.err.println("Checking FFSC with another one: "+
			   this.toString()+" with "+other.toString());
	if (! super.sameAs(other))
	    return false;
	
	FixedFormatSpecConfig cother = (FixedFormatSpecConfig)other;

	if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
	    return false;
	if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
	    return false;
	// Remember to look at CalBef and CalAft.
	return true;
    }

    /** Clone Constructor.*/
    public NPDBObject copy() {
	try {
	    return (FixedFormatSpecConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    public String toString() { return "FixedFormatSpecConfig: "+name+
				   ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }
  
} // end class def [FixedFormatSpecConfig].

