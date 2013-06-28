package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * RingoIII Polarimeter config.
 */
public class Ringo3PolarimeterConfig extends InstrumentConfig implements Serializable {

    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    //private static final long serialVersionUID = 
	// Note the IcsGUI currenly assumes TRIGGER_TYPE_* values are identical
	// to those in Ringo2PolarimeterConfig. If this changes,
	// ~dev/src/ccs_gui/java/IcsGUIConfigProperties.java:setConfigTriggerType and associated methods must change
    public static final int TRIGGER_TYPE_INTERNAL = 1;
    
    public static final int TRIGGER_TYPE_EXTERNAL = 2;

    // Variables.
    public static final int maxDetectorCount = 3;
    
    private int emGain;

    private int triggerType;
    

    // Constructor.
    /**
     * Default constructor.
     */
    public Ringo3PolarimeterConfig()
    {
	this("untitled");
    }
    
    /**
     * Constructor. Allocates detector array.
     */
    public Ringo3PolarimeterConfig(String name)
    {
	super(name);
	detectors = new Ringo3PolarimeterDetector[maxDetectorCount];
	for(int i = 0; i < maxDetectorCount; i++)
	{
		detectors[i] = new Ringo3PolarimeterDetector();
	}
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
	
	Ringo3PolarimeterConfig cother = (Ringo3PolarimeterConfig)other;
	
	for(int i = 0; i < maxDetectorCount; i++)
	{
		if (detectors[i].getXBin() != cother.getDetector(i).getXBin())
			return false;
		if (detectors[i].getYBin() != cother.getDetector(i).getYBin())
			return false;
	}
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
		    return (Ringo3PolarimeterConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		    {
			return null;
		}
	} // end (copy).

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<Ringo3PolarimeterConfig name = '"+name+"'>");
	for(int i = 0; i < maxDetectorCount; i++)
	{
		detectors[i].writeXml(out,level+1);
	}
	out.println(tab(level)+"</Ringo3PolarimeterConfig>");
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

	/**
	 * toString method.
	 */
	public String toString()
	{
		StringBuffer returnString = null;

		returnString = new StringBuffer("Ringo3PolarimeterConfig: "+instrumentName+"/"+name+
					  ", EmGain="+emGain+", Trigger="+toTriggerTypeString(triggerType)+", ");
		for(int i = 0; i < maxDetectorCount; i++)
		{
			returnString = returnString.append(detectors[i].toString());
		}
		return returnString.toString();
	}

} 
