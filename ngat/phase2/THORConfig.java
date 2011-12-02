package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

public class THORConfig extends InstrumentConfig implements Serializable 
{
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = -1885321170923597100L;
 

	public static final int maxDetectorCount = 1;


	// Variables.

	/**
	 * The EMGain to use.
	 */
	private int emGain;


	// Constructor.
    
	public THORConfig() 
	{
		this("untitled");
	}
 	
	public THORConfig(String name) 
	{
		super(name);
		detectors = new THORDetector[1];
		detectors[0] = new THORDetector();
	}

	// Accessors.
    
	/** 
	 * Sets the EMGain.
	 * @see #emGain
	 */
	public void setEmGain(int emGain) 
	{
		this.emGain = emGain;
	}

	/** 
	 * Returns the emGain. 
	 * @see #emGain
	 */
	public int getEmGain() 
	{ 
		return emGain;
	}

	public int getMaxDetectorCount() 
	{
		return maxDetectorCount; 
	}

	// Clone Constructor.
	public NPDBObject copy() 
	{
		try 
		{
			return (THORConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).

	/** Compares with another InstConfig to see if they are effectively the same.*/
	public boolean sameAs(InstrumentConfig other) 
	{
		//	System.err.println("Checking IRC with another one: "+
		//	   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
		// Ok we know they are the same class now..
		THORConfig cother = (THORConfig)other;	
		if (emGain != cother.getEmGain())
			return false;
		if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
			return false;
		if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
			return false;
		// Remember to look at CalBef and CalAft and windows..
		return true;
	}
    
	/** 
	 * Create a Default THORConfig.
	 */
	public static THORConfig getDefault() 
	{
		THORConfig DEFAULT = new THORConfig("DEFAULT");
		DEFAULT.setDescription("Default THORConfig");
		DEFAULT.setEmGain(1);
		THORDetector detector = new THORDetector();
		detector.setXBin(1);
		detector.setYBin(1);
		detector.clearAllWindows();
		DEFAULT.setDetector(0, detector);
		return DEFAULT;
	}

	/**
	 * Formatted Text Output.
	 */
	public void writeXml(PrintStream out, int level) 
	{
		out.println(tab(level)+"<THORConfig name = '"+name+"'>");
		out.println(tab(level+1)+"<emgain>"+emGain+"</emgain>");
		detectors[0].writeXml(out,level+1);
		out.println(tab(level)+"</THORConfig>");
	} // end (write).
    
	public String toString() 
	{ 
		return "THORConfig: "+instrumentName+"/"+name+" : emGain "+emGain+
			", Bin: ["+detectors[0].getXBin()+" x "+detectors[0].getYBin()+"]";
	}
}
//
// $Log: not supported by cvs2svn $
//
