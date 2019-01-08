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
public class Ringo2PolarimeterConfig extends InstrumentConfig implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    //private static final long serialVersionUID = 

    public static final int TRIGGER_TYPE_INTERNAL = 1;
    
    public static final int TRIGGER_TYPE_EXTERNAL = 2;

    // Variables.
    public static final int maxDetectorCount = 1;
    
    private int emGain;

    private int triggerType;
    

    // Constructor.
    /**
     * Default constructor.
     */
    public Ringo2PolarimeterConfig()
    {
	this("untitled");
    }
    
    /**
     * Constructor. Allocates detector array.
     */
    public Ringo2PolarimeterConfig(String name)
    {
	super(name);
	detectors = new Ringo2PolarimeterDetector[maxDetectorCount];
	detectors[0] = new Ringo2PolarimeterDetector();
    }
    
    // Accessors.

    public int getEmGain() { return emGain;}

    public void setEmGain(int emGain) {
	this.emGain = emGain;
    }

    public int getTriggerType() { return triggerType;}

    public void setTriggerType(int triggerType) {
	this.triggerType = triggerType;
    }

    
    public int getMaxDetectorCount()
    {	
	return maxDetectorCount; 
    }
    
    /** 
     * Compares with another InstConfig to see if they are effectively the same.
     */
    public boolean sameAs(InstrumentConfig other)
    {
	
	if (! super.sameAs(other))
	    return false;
	
	Ringo2PolarimeterConfig cother = (Ringo2PolarimeterConfig)other;
	
	if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
	    return false;
	if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
	    return false;

	if (emGain != cother.getEmGain())
	     return false;

	if (triggerType != cother.getTriggerType())
	     return false;

	return true;
    }



	// Clone Constructor.
	public NPDBObject copy()
	{
		try
		{
		    return (Ringo2PolarimeterConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		    {
			return null;
		}
	} // end (copy).

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<Ringo2PolarimeterConfig name = '"+name+"'>");
	detectors[0].writeXml(out,level+1);
	out.println(tab(level)+"</Ringo2PolarimeterConfig>");
    } // end (write).

    public static String toTriggerTypeString(int triggerType) {

	switch (triggerType) {
	case TRIGGER_TYPE_INTERNAL:
	    return "INTERNAL";
	case TRIGGER_TYPE_EXTERNAL:
	    return "EXTERNAL";
	default:
	    return "UNKNOWN("+triggerType+")";
	}
    }

    public String toString() {
	return "Ringo2PolarimeterConfig: "+instrumentName+"/"+name+
	    ", EmGain="+emGain+
	    ", Trigger="+toTriggerTypeString(triggerType)+
	    ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }

} 