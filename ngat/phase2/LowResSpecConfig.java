package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

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
    public void setWavelength(double wavelength) { this.wavelength = wavelength; }
    
    /** Returns the central wavelength.*/
    public double getWavelength() {  return wavelength; }
    
    public int getMaxDetectorCount() {	
	return maxDetectorCount; 
    }

    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	System.err.println("Checking LRSC with another one: "+
			   this.toString()+" with "+other.toString());
	if (! super.sameAs(other))
	    return false;
	
	LowResSpecConfig cother = (LowResSpecConfig)other;

	if (wavelength != cother.getWavelength())
	    return false;
	
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
	    return (LowResSpecConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    public String toString() { return "LowResSpecConfig: "+name+
				   " : Wavelength "+wavelength+
				   ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }
  
} // end class def [LowResSpecConfig].

