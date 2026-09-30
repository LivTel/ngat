// LUMENConfig.java
// $Header$
package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Configuration for the 'LUMEN' optical CMOS camera.
 * Currently consists of:
 * <ul>
 * <li>A Teledyne COSMOS-4K CMOS camera.
 * <li>A 14 position single filter wheel (filter 1).
 * <li>Two neutral density filter slides containing a neutral density filter (filter 2 and filter 3).
 * </ul>
 * @version $Revision$
 * @author cjm
 * @see InstrumentConfig
 */
public class LUMENConfig  extends InstrumentConfig implements Serializable 
{
	/**
	 * Index in a list of filters, for the primary filter wheel. We are trying to use consistent numbering
	 * (with FITS headers for instance), so this is numbered 1 (not 0).
	 */
	public final static int LUMEN_FILTER_INDEX_FILTER_WHEEL             = 1;
	/**
	 * Index in a list of filters, for the lower neutral density filter slide. 
	 * We are trying to use consistent numbering
	 * (with FITS headers for instance), so this is numbered 2.
	 */
	public final static int LUMEN_FILTER_INDEX_FILTER_SLIDE_LOWER       = 2;
	/**
	 * Index in a list of filters, for the upper neutral density filter slide. 
	 * We are trying to use consistent numbering
	 * (with FITS headers for instance), so this is numbered 3.
	 */
	public final static int LUMEN_FILTER_INDEX_FILTER_SLIDE_UPPER       = 3;
	/**
	 * The number of filters to interate over. Filters are numbered 1-based, to maintain
	 * consistency with FITS headers, so this is 4 rather than 3.
	 */
	public final static int LUMEN_FILTER_INDEX_COUNT                     = 4;
	/**
	 * Maximum number of filters. Always 1 more than actual as 0 is always empty.
	 * @see #LUMEN_FILTER_INDEX_COUNT
	 */
        public static final int maxWheelCount = LUMEN_FILTER_INDEX_COUNT;
	/**
	 * Maximum number of detectors:1.
	 */
	public static final int maxDetectorCount = 1;
	/**
	 * Serial version UID. This is used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = ;
	/** 
	 * Default clear filter.
	 */
	public static final String CLEAR = "clear";
	
	/** Identity of filter in each wheel. */
	protected String filterName[];

	/** Actual number of wheels.*/
	protected int wheelCount;

	/**
	 * Default constructor.
	 */
	public LUMENConfig() 
	{
		this("untitled");
	}

	/**
	 * Constructor. Initialises detectors list.
	 * @param name The name of the config.
	 * @see #detectors
	 * @see LUMENDetector
	 */
	public LUMENConfig(String name) 
	{
		super(name);
		detectors = new LUMENDetector[1];
		detectors[0] = new LUMENDetector();
		filterName = new String[maxWheelCount];
		wheelCount = maxWheelCount;
	}

	/** 
	 * Sets the String identity of the filter in the specified wheel.
	 * @param wheel The wheel to set the filter for, numbered from 1 to 3 at the moment.
	 * @param in The identity string of the filter to set.
	 */
	public void setFilterName(int wheel, String in) 
	{
		if (wheel < LUMEN_FILTER_INDEX_FILTER_WHEEL || wheel >= wheelCount)
		{
			throw new IllegalArgumentException("LUMENConfig: " + name + " Wheel index " + wheel
							   + " out of range ["+LUMEN_FILTER_INDEX_FILTER_WHEEL+", " +
							   (wheelCount-1) + "]");
		}
		this.filterName[wheel] = in;
	}
    
	/** 
	 * Get the String identity of the filter in the specified wheel.
	 * @param wheel The wheel to get the filter identity of, numbered from 1 to 3 at the moment.
	 * @return The String identity of the current filter in the specified wheel.
	 * @throws IllegalArgumentException If the wheel number is out of range.
	 */
	public String getFilterName(int wheel) throws IllegalArgumentException
	{
		if ((wheel < LUMEN_FILTER_INDEX_FILTER_WHEEL) || (wheel >= wheelCount))
		{
			throw new IllegalArgumentException("LUMENConfig: " + name + " Wheel index " + wheel
							   + " out of range ["+LUMEN_FILTER_INDEX_FILTER_WHEEL+", "+
							   (wheelCount-1) + "]");
		}
		return this.filterName[wheel];
	}

	/**
	 * Return the maximum number of detectors.
	 * @return The maximum number of detectors.
	 * @see #maxDetectorCount
	 */
	public int getMaxDetectorCount()
	{
		return maxDetectorCount;
	}

	/**
	 * Clone constructor.
	 * @see #clone
	 */
	public NPDBObject copy()
	{
		try 
		{
			return (LUMENConfig)clone();
		}
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	}

	/**
	 * Compares with another InstConfig to see if they are effectively the same.
	 * @param other An InstrumentConfig to compare the context object with.
	 * @return true if the config objects are equivalent, false if not.
	 */
	public boolean sameAs(InstrumentConfig other) 
	{
		if (!super.sameAs(other))
			return false;
		// Ok we know they are the same class now..
		LUMENConfig cother = (LUMENConfig)other;

		for (int wheel = LUMEN_FILTER_INDEX_FILTER_WHEEL; wheel < wheelCount; wheel++)
		{
			if (!filterName[wheel].equals(cother.getFilterName(wheel)))
				return false;
		}
		if (detectors[0].getXBin() != cother.getDetector(0).getXBin())
			return false;
		if (detectors[0].getYBin() != cother.getDetector(0).getYBin())
			return false;
		// Remember to look at CalBef and CalAft and windows..
		return true;
	}
    
	/** 
	 * Create a Default LUMENConfig.
	 * @return Reference to a new LUMENConfig.
	 */
	public static LUMENConfig getDefault() 
	{
		LUMENConfig DEFAULT = new LUMENConfig("DEFAULT");
		DEFAULT.setDescription("Default LUMENConfig");
		DEFAULT.setFilterName(LUMEN_FILTER_INDEX_FILTER_WHEEL, CLEAR);
		DEFAULT.setFilterName(LUMEN_FILTER_INDEX_FILTER_SLIDE_LOWER, CLEAR);
		DEFAULT.setFilterName(LUMEN_FILTER_INDEX_FILTER_SLIDE_UPPER, CLEAR);
		LUMENDetector detector = new LUMENDetector();
		detector.setXBin(2);
		detector.setYBin(2);
		detector.clearAllWindows();
		DEFAULT.setDetector(0, detector); 	
		return DEFAULT;
	}

	/**
	 * Write formatted text output XML.
	 * @param out The PrintStream to write to.
	 * @param level The tab level to use for this output.
	 */
	public void writeXml(PrintStream out, int level) 
	{
		out.println(tab(level)+"<LUMENConfig name = '"+name+"'>");
		for (int wheel = LUMEN_FILTER_INDEX_FILTER_WHEEL; wheel < wheelCount; wheel++)
			out.println(tab(level+1)+"<filterName"+wheel+">"+filterName[wheel]+"</filterName"+wheel+">");
		detectors[0].writeXml(out,level+1);
		out.println(tab(level)+"</LUMENConfig>");
	}

	/**
	 * Get a String description of this object.
	 * @return A String description of this object.
	 */
	public String toString() 
	{ 
		String desc = "LUMENConfig: "+name;
		for (int wheel = LUMEN_FILTER_INDEX_FILTER_WHEEL; wheel < wheelCount; wheel++)
			desc += ", Wheel["+wheel+"]: "+filterName[wheel];
		desc += ", Bin: ["+detectors[0].getXBin()+" x "+detectors[0].getYBin()+"]";
		return desc;
	}
}
	
