package ngat.phase2;

import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Generic InstrumentConfiguration - we never actually create one of these.
 */
public class InstrumentConfig extends DBObject implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -3204443095114044477L;
    
    // Variables.   
    
    /** Determines whether to take calibration exposures <b>before</b> an actual exposure.*/
    protected boolean calibrateBefore;

    /** Determines whether to take calibration exposures <b>after</b> an actual exposure.*/
    protected boolean calibrateBefore;

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
	detectors.[i] = detector;
    }
    
    /** Returns an estimate of the time (millis) required to reconfigure the instruemnt
     * to the new (specified) config from this config.*/
    public double getReconfigurationTime(InstrumentConfig other) { return 5.0;}
    
    /** NP -> P Translator. It is neccessary to iterate over the NPInstConfig's
     * list of NPDetectors and add each one to this IC's list.*/    
    public InstrumentConfig(NPInstrumentConfig npInstrumentConfig) {
	super(npInstrumentConfig);
	setupTime       = npInstrumentConfig.getSetupTime();
	description     = npInstrumentConfig.getDescription();
	calibrateBefore = npInstrumentConfig.getCalibrateBefore();
	calibrateAfter  = npInstrumentConfig.getCalibrateAfter();
    } // end (NP -> P Translator).
    
    /** P -> NP Translator.*/  
    public void stuff(NPInstrumentConfig npInstrumentConfig) {
	super.stuff(npInstrumentConfig);
	npInstrumentConfig.setSetupTime(getSetupTime());
	npInstrumentConfig.setDescription(getDescription());
	npInstrumentConfig.setCalibrateBefore(getCalibrateBefore());
	npInstrumentConfig.setCalibrateAfter(getCalibrateAfter());
    } // end (P -> NP Translator).
    
} // end class def [InstrumentConfig].
