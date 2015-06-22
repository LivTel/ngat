// LOTUSConfig.java
// $HeadURL$
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * LOTUS Spectrograpg configuration class. This is the Liverpool OpTical Ultraviolet Spectrograph. It has a fixed
 * slit and dispersion element optimised for blue (UV) wavelengths. It has a variable width slit, part of it is narrow
 * and part is broad. The instrument will issue a suitable offby x y to position the source onto the part of the
 * slit with the correct width, after an acquisition has taken place.
 */
public class LOTUSConfig extends SpecConfig implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	static final long serialVersionUID = 6950132435210704976L;
	public static final int maxDetectorCount = 1;
	/**
	 * Position the source on the narrow bit of the slit.
	 */
	public final static int SLIT_WIDTH_NARROW = 1;
	/**
	 * Position the source on the wide bit of the slit.
	 */
	public final static int SLIT_WIDTH_WIDE = 2;

	// Variables.
	/**
	 * Slit width.
	 * @see #SLIT_WIDTH_NARROW
	 * @see #SLIT_WIDTH_WIDE
	 */
	protected int slitWidth;

	// Constructor.
	/**
	 * Default constructor.
	 */
	public LOTUSConfig()
	{
		this("untitled");
	}
    
	/**
	 * Constructor.
	 * @param name The name of the config.
	 * @see #detectors
	 */
	public LOTUSConfig(String name)
	{
		super(name);
		detectors = new LOTUSDetector[maxDetectorCount];
		detectors[0] = new LOTUSDetector();
	}
    
	/** 
	 * Sets the slit width.
	 * @param w The slit width, one of SLIT_WIDTH_NARROW, SLIT_WIDTH_WIDE.
	 * @see #slitWidth
	 * @see #SLIT_WIDTH_NARROW
	 * @see #SLIT_WIDTH_WIDE
	 */
	public void setSlitWidth(int w) 
	{ 
		this.slitWidth = w;
	}
    
	/** 
	 * Returns the slit width.
	 * @return The slit width requested in this config, legal configs have one of:
	 *         SLIT_WIDTH_NARROW, SLIT_WIDTH_WIDE.
	 * @see #slitWidth
	 * @see #SLIT_WIDTH_NARROW
	 * @see #SLIT_WIDTH_WIDE
	 */
	public int getSlitWidth()
	{
		return slitWidth;
	}
    
	/**
	 * Return the maximum number of detectors.
	 * @return An integer, the maximum number of detectors, held in maxDetectorCount.
	 * @see #maxDetectorCount
	 */
	public int getMaxDetectorCount()
	{
		return maxDetectorCount; 
	}

	/** 
	 * Compares with another InstConfig to see if they are effectively the same.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
		//System.err.println("Checking LOTUSConfig with another one: "+
		//		   this.toString()+" with "+other.toString());
		if (! super.sameAs(other))
			return false;
	
		LOTUSConfig cother = (LOTUSConfig)other;

		if(slitWidth != cother.getSlitWidth())
			return false;
		
		if(! detectors[0].equals(cother.getDetector(0)))
			return false;
		return true;
	}

	/** 
	 * Clone Constructor.
	 */
	public NPDBObject copy() 
	{
		try
		{
			return (LOTUSConfig)clone();
		} 
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	} // end (copy).
    
	/**
	 * Produce a string representation of this config.
	 * @return A string describing this config.
	 * @see #name
	 * @see #slitWidthToString
	 * @see #detectors
	 */
	public String toString() 
	{ 
		return new String("LOTUSConfig: "+name+
				  " : Slit Width "+slitWidthToString()+
				  ", Bin ["+detectors[0].getXBin()+", "+detectors[0].getYBin()+"]");
	}

	/**
	 * Print a string version of the config's slit width.
	 * @return A string, one of "wide" or "narrow".
	 * @exception IllegalArgumentException Thrown if the slit width is not a legal number.
	 * @see #SLIT_WIDTH_NARROW
	 * @see #SLIT_WIDTH_WIDE
	 * @see #slitWidth
	 */
	public String slitWidthToString() throws IllegalArgumentException
	{
		if(slitWidth == SLIT_WIDTH_NARROW)
			return "narrow";
		else if(slitWidth == SLIT_WIDTH_WIDE)
			return "wide";
		else
			throw new IllegalArgumentException("ngat.phase2.LOTUSConfig:slitWidthToString:"+
							   "Illegal number:"+slitWidth);
	}

	/**
	 * Parse a string into a suitable slit width, one of SLIT_WIDTH_NARROW or SLIT_WIDTH_WIDE.
	 * @param widthString A String, should be one of "narrow" or "wide" to be successful.
	 * @exception IllegalArgumentException Thrown if widthString is not a suitable string.
	 * @see #SLIT_WIDTH_NARROW
	 * @see #SLIT_WIDTH_WIDE
	 * @see #slitWidth
	 */
	public void parseSlitWidth(String widthString) throws IllegalArgumentException
	{
		if(widthString.equals("wide"))
			slitWidth = SLIT_WIDTH_WIDE;
		else if(widthString.equals("narrow"))
			slitWidth = SLIT_WIDTH_NARROW;
		else
			throw new IllegalArgumentException("ngat.phase2.LOTUSConfig:parseSlitWidth:"+
							   "Illegal width string:"+widthString);
	}

}
