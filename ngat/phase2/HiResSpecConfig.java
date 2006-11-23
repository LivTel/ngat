package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * MES Spectrograph configuration.
 */
public class HiResSpecConfig extends SpecConfig implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 723884528095028233L;
     
    public static final int maxDetectorCount = 1;

    // Variables.
    
    /** Name used to identify the filter-thingy/position.*/
    protected String filterSlideName;
    
    // Constructor.
    
    /** Create a HiResSpecConfig with no title.*/
    public HiResSpecConfig() {this("untitled");}

    /** Create a HiResSpecConfig with no title.*/
    public HiResSpecConfig(String name) {
	super(name);
	detectors = new HiResSpecDetector[maxDetectorCount];
	detectors[0] = new HiResSpecDetector();
    }
    
    // Accessors.
  
    /** Set the name to identify the filter-thingy/position.*/
    public void setFilterSlideName(String name) { filterSlideName = name; }
    
    /**Returns the identify of the filter-thingy/position.*/
    public String getFilterSlideName() {  return filterSlideName; }

    public int getMaxDetectorCount() {	
	return maxDetectorCount; 
    }
    
    /** Compares with another InstConfig to see if they are effectively the same.*/
    public boolean sameAs(InstrumentConfig other) {
	System.err.println("Checking LRSC with another one: "+
			   this.toString()+" with "+other.toString());
	if (! super.sameAs(other))
	    return false;
	
	HiResSpecConfig cother = (HiResSpecConfig)other;
	
	if (!filterSlideName.equals(cother.getFilterSlideName()))
	    return false;
	
	if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
	    return false;
	if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
	    return false;
	// Remember to look at CalBef and CalAft.
	return true;
    }
    
    // Clone Constructor.    
    public NPDBObject copy() {
	try {
	    return (HiResSpecConfig)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).

    public String toString() { return "HiResSpecConfig: "+name+
				   " FilterSlideName: "+filterSlideName+
				   " Bin: ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }

} // end class def [HiResSpecConfig].
