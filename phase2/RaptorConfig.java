// RaptorConfig.java
// $Id$
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Instrument configuration for the Raptor IR camera. This consists of:
 * <ul>
 * <li>A Starlight Express 5 position filter wheel containing filters.
 * <li>A 'Nudgematic' mechanisn that we use to offset the target on the detector, 
 *     for each 'exposure' (actually a series of coaads equalling the exposure length) in a multrun. 
 *     The offsets can be 'SMALL' or 'LARGE'.
 * <li>A Raptor Ninox-640 IR detector.  This only has a few fixed coadd exposure lengths, and the exposure length
 *     for each exposure in a multrun is actually a series of coadds. We define the coadd exposure length here
 *     (which must have a '.fmt' Raptor API config file defined for it).
 * </ul>
 * @version $Revision$
 * @author cjm
 * @see InstrumentConfig
 */
public class RaptorConfig extends InstrumentConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */    
	//private static final long serialVersionUID = ;
	/**
	 * Nudgematic offset sizes - in this case, small.
	 */
	public final static int NUDGEMATIC_OFFSET_SIZE_SMALL = 1;
	/**
	 * Nudgematic offset sizes - in this case, large.
	 */
	public final static int NUDGEMATIC_OFFSET_SIZE_LARGE = 2;

	// Variables.
	/**
	 * Number of filter wheels: 1.
	 */
        public static final int maxWheelCount = 1;
	/**
	 * Maximum number of detectors.
	 */
        public static final int maxDetectorCount = 1;
	/** 
	 * Actual number of filter wheels: 1
	 */
	protected int wheelCount = maxWheelCount;
	/** 
	 * Name of the selected filter in the filter wheel. 
	 */
	protected String filterName;
	/**
	 * Whether the nudgematic will be doing small or large offsets.
	 * @see #NUDGEMATIC_OFFSET_SIZE_SMALL
	 * @see #NUDGEMATIC_OFFSET_SIZE_LARGE
	 */
	protected int nudgematicOffsetSize = NUDGEMATIC_OFFSET_SIZE_SMALL;
	/**
	 * The exposure length of an individual coadd, in milliseconds. This value can only take a few
	 * fixed values, dependant on a Raptor API '.fmt' file being generated for the relevant length.
	 */
	protected int coaddExposureLength;

	// Constructor.
	/**
	 * Default constructor.
	 */
	public RaptorConfig()
	{
		this("untitled");
	}
    
	/**
	 * Constructor. Constructs the detector objects in the detector list.
	 * @see RaptorDetector
	 * @see #maxDetectorCount
	 * @see #wheelCount
	 * @see #maxWheelCount
	 */
	public RaptorConfig(String name)
	{
		super(name);
		detectors = new RaptorDetector[maxDetectorCount];
		for(int i = 0; i < maxDetectorCount; i++)
			detectors[i] = new RaptorDetector();
		wheelCount = maxWheelCount;
	}
    
	// Accessors.
	/** 
	 * Sets the String identity of the filter.
	 * @param in The identity string of the filter to set.
	 */
	public void setFilterName(String in) 
	{
		this.filterName = in;
	}
    
	/** 
	 * Get the String identity of the filter in the filter wheel.
	 * @return The String identity of the current filter in the filter wheel.
	 */
	public String getFilterName()
	{
		return this.filterName;
	}

	/**
	 * Set the size of offsets the Nudgematic is to perform.
	 * @param size The Nudgematic offset size, one of NUDGEMATIC_OFFSET_SIZE_SMALL/NUDGEMATIC_OFFSET_SIZE_LARGE.
	 * @see #nudgematicOffsetSize
	 * @see #NUDGEMATIC_OFFSET_SIZE_SMALL
	 * @see #NUDGEMATIC_OFFSET_SIZE_LARGE
	 */
	public void setNudgematicOffsetSize(int size)
	{
		this.nudgematicOffsetSize = size;
	}

	/**
	 * Returns the currently selected size of offsets the Nudgematic is to perform.
	 * @return The Nudgematic offset size, one of NUDGEMATIC_OFFSET_SIZE_SMALL/NUDGEMATIC_OFFSET_SIZE_LARGE.
	 * @see #nudgematicOffsetSize
	 * @see #NUDGEMATIC_OFFSET_SIZE_SMALL
	 * @see #NUDGEMATIC_OFFSET_SIZE_LARGE
	 */
	public int getNudgematicOffsetSize()
	{
		return nudgematicOffsetSize;
	}

	/**
	 * Set the Coadd exposure length. This value can only take a few
	 * fixed values, dependant on a Raptor API '.fmt' file being generated for the relevant length.
	 * @param length The Coadd exposure length, in milliseconds.
	 * @see #coaddExposureLength
	 */
	public void setCoaddExposureLength(int length)
	{
		this.coaddExposureLength = length;
	}

	/**
	 * Return the current coadd exposure length in milliseconds.
	 * @return The coadd exposure length in milliseconds.
	 * @see #coaddExposureLength
	 */
	public int getCoaddExposureLength()
	{
		return coaddExposureLength;
	}

	/**
	 * Return the number of detectors.
	 * @return An integer, the number of detectors
	 * @see #maxDetectorCount
	 */
	public int getMaxDetectorCount()
	{	
		return maxDetectorCount; 
	}

	/** 
	 * Compares this instance of RaptorConfig with another InstConfig to see if they are the same.
	 * @param other The comparison InstrumentConfig.
	 * @return The method returns true if the instrument configurations are the same, and false otherwise.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
		if (! super.sameAs(other))
			return false;
		RaptorConfig cother = (RaptorConfig)other;
		// filter wheel
		if(filterName.equals(cother.getFilterName()) == false)
			return false;		
		// nudgematic offset size
		if(nudgematicOffsetSize != cother.getNudgematicOffsetSize())
			return false;		
		// coadd exposure length
		if(coaddExposureLength != cother.getCoaddExposureLength())
			return false;		
		// detectors
		if(maxDetectorCount != cother.getMaxDetectorCount())
			return false;
		for(int i = 0; i < maxDetectorCount; i++)
		{
			if(! detectors[i].equals(cother.getDetector(i)))
				return false;
		}
		return true;
	}
	
	// Clone Constructor.
	public NPDBObject copy()
	{
		try
		{
			return (RaptorConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).

	// Formatted Text Output.
	public void writeXml(PrintStream out, int level)
	{
		out.println(tab(level)+"<RaptorConfig name = '"+name+"'>");
		out.println(tab(level+1)+"<filterName>"+filterName+"</filterName>");
	        out.println(tab(level+1)+"<nudgematicOffsetSize>"+nudgematicOffsetSizeToString()+
			    "</nudgematicOffsetSize>");
	        out.println(tab(level+1)+"<coaddExposureLength>"+coaddExposureLength+"</coaddExposureLength>");
		for(int i = 0; i < maxDetectorCount; i++)
		{
			detectors[i].writeXml(out,level+1);
		}
		out.println(tab(level)+"</RaptorConfig>");
	} // end (write).
	
	public String toString()
	{
		StringBuffer sb = null;
		sb = new StringBuffer();
		sb.append("RaptorConfig: ");
		sb.append(instrumentName);
		sb.append(" name = "+name);
		sb.append(" filter = "+filterName);
		sb.append(" nudgematicOffsetSize = "+nudgematicOffsetSizeToString());
		sb.append(" coaddExposureLength = "+coaddExposureLength);
		for(int i = 0; i < maxDetectorCount; i++)
		{
			sb.append("Detector "+i);
			sb.append(" xbin = "+detectors[i].getXBin());
			sb.append(" ybin = "+detectors[i].getYBin());
		}
		return sb.toString();
	}
	
	/**
	 * Return a string version of the nudgematicOffsetSize.
	 * @return A string, one of "small" or "large".
	 * @exception IllegalArgumentException Thrown if the nudgematicOffsetSize is not a known offset size.
	 * @see #nudgematicOffsetSize
	 * @see #NUDGEMATIC_OFFSET_SIZE_SMALL
	 * @see #NUDGEMATIC_OFFSET_SIZE_LARGE
	 */
	public String nudgematicOffsetSizeToString() throws IllegalArgumentException
	{
		if(nudgematicOffsetSize == NUDGEMATIC_OFFSET_SIZE_SMALL)
			return "small";
		else if(nudgematicOffsetSize == NUDGEMATIC_OFFSET_SIZE_LARGE)
			return "large";
		else
			throw new IllegalArgumentException("ngat.phase2.RaptorConfig:nudgematicOffsetSizeToString:"+
							   "Illegal offset size number:"+nudgematicOffsetSize);

	}
	
	/**
	 * Parse a string into a suitable nudgematic offset size.
	 * @param sizeString A String, should be one of "small" or "large" to be successful.
	 * @return The nudgematic offset size number corresponding to the sizeString, one of 
	 *         NUDGEMATIC_OFFSET_SIZE_SMALL or NUDGEMATIC_OFFSET_SIZE_LARGE.
	 * @exception IllegalArgumentException Thrown if sizeString is not a suitable string.
	 * @see #NUDGEMATIC_OFFSET_SIZE_SMALL
	 * @see #NUDGEMATIC_OFFSET_SIZE_LARGE
	 */
	public static int parseNudgematicOffsetSize(String sizeString) throws IllegalArgumentException
	{
		int offsetSize;

		if(sizeString.equals("small"))
			offsetSize = NUDGEMATIC_OFFSET_SIZE_SMALL;
		else if(sizeString.equals("large"))
			offsetSize = NUDGEMATIC_OFFSET_SIZE_LARGE;
		else
			throw new IllegalArgumentException("ngat.phase2.RaptorConfig:parseNudgematicOffsetSize:"+
							   "Illegal size string:"+sizeString);
		return offsetSize;
	}
}
