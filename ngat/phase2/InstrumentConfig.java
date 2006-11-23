package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Generic InstrumentConfiguration - we never actually create one of these.
 */
public class InstrumentConfig extends NPDBObject implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -3204443095114044477L;
    
    // Variables.   
    
    /** Determines whether to take calibration exposures <b>before</b> an actual exposure.*/
    protected boolean calibrateBefore;

    /** Determines whether to take calibration exposures <b>after</b> an actual exposure.*/
    protected boolean calibrateAfter;

    /**  time (millis) required to set up this config (..Simulation only). */
    protected float setupTime;

    /** text description. */
    protected String description;
    
    /** Holds the list of detectors for this Instrument. */
    protected Detector[] detectors;
    
    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() { return 0;}
    
    // Constructor.
    
    public InstrumentConfig() {this("untitled");}
    
    public InstrumentConfig(String name) {
	super(name);
    }
    
    // Accessors.
    
    /** Sets the  time (millis) required to set up this config (..Simulation only) .*/
    public void setSetupTime(float in) { this.setupTime = in;}
        
    /** Returns the  time (millis) required to set up this config (..Simulation only). */
    public float getSetupTime() { return setupTime;}
    
    /** Sets the etx description.*/
    public void setDescription(String in) { this.description = in;}

    /** Returns the text description. */
    public String getDescription() { return description;}
    
    /** Sets whether to take calibration exposures <b>before</b> an actual exposure.*/
    public void setCalibrateBefore(boolean calib) {this.calibrateBefore = calib; } 

    /** Returns whether to take calibration exposures <b>before</b> an actual exposure.*/
    public boolean getCalibrateBefore() { return calibrateBefore; }
    
    /** Sets whether to take calibration exposures <b>before</b> an actual exposure.*/
    public void setCalibrateAfter(boolean calib) {this.calibrateAfter = calib; } 
    
    /** Returns whether to take calibration exposures <b>after</b> an actual exposure.*/
    public boolean getCalibrateAfter() { return calibrateAfter; }
    
    // Detector Methods.
    
    /** Return a reference to the list of Detectors.*/
    public Detector[] getDetectors() { return detectors;}

    /** Returns the specified detector. 
     * @param index The index of the detector to return.*/
    public Detector getDetector(int index) {
	return detectors[index];
    }
       
    /** Set the specified Detector to that supplied.
     * @param i The number of the detector to set must be >=0 and < getMaxDetectorCount().
     * @param detector The detector to set at that location.
     */
    public void setDetector(int i, Detector detector) throws IllegalArgumentException {
	
	if (i < 0 || i >= getMaxDetectorCount()) 
	    throw new IllegalArgumentException("InstrumentConfig: "+name+" Detector number: "+i+
					       " out of range [0,"+(getMaxDetectorCount()-1));
	detectors[i] = detector;
    }
    
    /** Returns an estimate of the time (millis) required to reconfigure the instruemnt
     * to the new (specified) config from this config.*/
    public double getReconfigurationTime(InstrumentConfig other) { return 5.0;}
    
    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	System.err.println("IC::Checking: "+this.getClass().getName()+
			   " Against: "+other.getClass().getName());
	if (other == null) return false;
	return this.getClass().getName().equals(other.getClass().getName());
    }
     
    // Formatted Text Output.   
    public void writeXml(PrintStream out, int level) {
	super.writeXml(out, level);
	out.println(tab(level+1)+"<setupTime>"+setupTime+"</setupTime>");
	out.println(tab(level+1)+"<description>"+description+"</description>");
	out.println(tab(level+1)+"<calBefore>"+calibrateBefore+"</calBefore>");
	out.println(tab(level+1)+"<calAfter>"+calibrateAfter+"</calAfter>");
    } // end (write).
    
    // Clone Constructor.   
    public NPDBObject copy() {
	try {
	      return (InstrumentConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
} // end class def [InstrumentConfig].
