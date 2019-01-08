// RISEConfig.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/RISEConfig.java,v 1.1 2007-12-11 16:00:28 cjm Exp $
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Instrument configuration for the RISE instrument. This is a simple CCD with binning configuration only.
 */
public class RISEConfig extends InstrumentConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */    
	private static final long serialVersionUID = 7601269335374063337L;
    
	// Variables.
	/**
	 * Maxim,um number of detectors.
	 */
        public static final int maxDetectorCount = 1;

	// Constructor.
	/**
	 * Default constructor.
	 */
	public RISEConfig()
	{
		this("untitled");
	}
    
	/**
	 * Constructor. Allocates detector array.
	 */
	public RISEConfig(String name)
	{
		super(name);
		detectors = new RISEDetector[maxDetectorCount];
		detectors[0] = new RISEDetector();
	}
    
	// Accessors.
    
	public int getMaxDetectorCount()
	{	
		return maxDetectorCount; 
	}


	/** 
	 * Compares with another InstConfig to see if they are effectively the same.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
	    //		System.err.println("Checking PolarimterConfig with another one: "+
	    //		   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
	
		RISEConfig cother = (RISEConfig)other;

		if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
			return false;
		if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
			return false;
		// Remember to look at CalBef and CalAft.
		return true;
	}

	// Clone Constructor.
	public NPDBObject copy()
	{
		try
		{
			return (RISEConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<RISEConfig name = '"+name+"'>");
	detectors[0].writeXml(out,level+1);
	out.println(tab(level)+"</RISEConfig>");
    } // end (write).
    

    public String toString() {
	return "RISEConfig: "+instrumentName+"/"+name+", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }

} 
//
// $Log
//
