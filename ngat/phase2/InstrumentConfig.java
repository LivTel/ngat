package ngat.phase2;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Hand Generated.


public class InstrumentConfig extends DBObject implements Serializable {
    
    // Variables.   
    
    /**  time (millis) required to set up this config (..Simulation only). */
    protected float setupTime;

    /** text description. */
    protected String description;
    
    /** Holds the list of detectors for this Instrument. */
    protected OSVector detectors;
    
    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() { return 0;}
    
    // Constructor.
    
    public InstrumentConfig() {this("untitled");}
    
    public InstrumentConfig(String name) {
	super(name);
	detectors = new OSVector();
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
    


    // Descendant Mutators.
    
    // Detector Methods.
    
    public void addDetector(Detector detector) throws IllegalArgumentException {
	if (detectors.size() == getMaxDetectorCount())
	    throw new IllegalArgumentException("Too many detectors "+
					       detectors.size()+" > "+getMaxDetectorCount());
	detectors.add(detector);
    }
    
    public void deleteDetector(Detector detector) {
	if (detectors.contains(detector)) 
	    detectors.remove(detector);	
    }
    
    public void removeAllDetectors() {
	detectors.clear();
    }
    
    public Iterator listAllDetectors() {
	return detectors.iterator();
    }
    
    public OSVector getDetectors() { return detectors;}

    /** Returns the specified detector. 
     * @param index The index of the detector to return.*/
    public Detector getDetector(int index) {
	return (Detector)(detectors.get(index));
    }


    public double getReconfigurationTime(InstrumentConfig other) { return 5.0;}

    // NP -> P Translator.    
    public InstrumentConfig(NPInstrumentConfig npInstrumentConfig) {
	super(npInstrumentConfig);
	setupTime = npInstrumentConfig.getSetupTime();
	description = npInstrumentConfig.getDescription();
	detectors = new OSVector();
    } // end (NP -> P Translator).
    
    // P -> NP Translator.    
    public void stuff(NPInstrumentConfig npInstrumentConfig) {
	super.stuff(npInstrumentConfig);
    } // end (P -> NP Translator).
    
} // end class def [InstrumentConfig].
