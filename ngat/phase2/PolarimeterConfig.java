// PolarimeterConfig.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/PolarimeterConfig.java,v 1.2 2007-08-07 08:41:14 snf Exp $
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Instrument configuration for a generic Polarimeter.
 */
public class PolarimeterConfig extends InstrumentConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: PolarimeterConfig.java,v 1.2 2007-08-07 08:41:14 snf Exp $"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */    
	private static final long serialVersionUID = 779908974751778738L;
    
	// Variables.
	/**
	 * Maxim,um number of detectors.
	 */
        public static final int maxDetectorCount = 1;

	// Constructor.
	/**
	 * Default constructor.
	 */
	public PolarimeterConfig()
	{
		this("untitled");
	}
    
	/**
	 * Constructor. Allocates detector array.
	 */
	public PolarimeterConfig(String name)
	{
		super(name);
		detectors = new PolarimeterDetector[maxDetectorCount];
		detectors[0] = new PolarimeterDetector();
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
		System.err.println("Checking PolarimterConfig with another one: "+
				   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
	
		PolarimeterConfig cother = (PolarimeterConfig)other;

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
			return (PolarimeterConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).

    // Formatted Text Output.
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<PolarimeterConfig name = '"+name+"'>");
	detectors[0].writeXml(out,level+1);
	out.println(tab(level)+"</PolarimeterConfig>");
    } // end (write).
    

    public String toString() {
	return "PolarimeterConfig: "+instrumentName+"/"+name+", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]";
    }

} 
//
// $Log: not supported by cvs2svn $
// Revision 1.1  2006/11/20 14:51:23  cjm
// Initial revision
//
//

