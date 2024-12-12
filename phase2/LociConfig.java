// LociConfig.java
// $Id$
package ngat.phase2;

import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;

/**
 * Instrument configuration for the Liric IR camera. This consists of:
 * <ul>
 * <li>An E2V CCD42-40 2kx2k CCD chip.
 * <li>A Starlight Express 5 position filter wheel containing filters.
 * </ul>
 * @version $Revision$
 * @author cjm
 * @see InstrumentConfig
 */
public class LociConfig  extends InstrumentConfig implements Serializable 
{
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
	 * Default constructor.
	 */
	public LociConfig() 
	{
		this("untitled");
	}

	/**
	 * Constructor. Initialises detectors list. Initialises wheelCount.
	 * @param name The name of the config.
	 * @see #detectors
	 * @see #wheelCount
	 * @see LociDetector
	 */
	public LociConfig(String name) 
	{
		super(name);
		detectors = new LociDetector[maxDetectorCount];
		for(int i = 0; i < maxDetectorCount; i++)
			detectors[i] = new LociDetector();
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
			return (LociConfig)clone();
		}
		catch (CloneNotSupportedException ce) 
		{
			return null;
		}
	}

	/** 
	 * Compares this instance of LociConfig with another InstConfig to see if they are the same.
	 * @param other The comparison InstrumentConfig.
	 * @return The method returns true if the instrument configurations are the same, and false otherwise.
	 */
	public boolean sameAs(InstrumentConfig other)
	{
		if (! super.sameAs(other))
			return false;
		LociConfig cother = (LociConfig)other;
		// filter wheel
		if(filterName.equals(cother.getFilterName()) == false)
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
	
	// Formatted Text Output.
	/**
	 * Write formatted text output XML.
	 * @param out The PrintStream to write to.
	 * @param level The tab level to use for this output.
	 */
	public void writeXml(PrintStream out, int level)
	{
		out.println(tab(level)+"<LociConfig name = '"+name+"'>");
		out.println(tab(level+1)+"<filterName>"+filterName+"</filterName>");
		for(int i = 0; i < maxDetectorCount; i++)
		{
			detectors[i].writeXml(out,level+1);
		}
		out.println(tab(level)+"</LociConfig>");
	} // end (write).
	
	/**
	 * Get a String description of this object.
	 * @return A String description of this object.
	 */
	public String toString()
	{
		StringBuffer sb = null;
		sb = new StringBuffer();
		sb.append("LociConfig: ");
		sb.append(instrumentName);
		sb.append(" name = "+name);
		sb.append(" filter = "+filterName);
		for(int i = 0; i < maxDetectorCount; i++)
		{
			sb.append("Detector "+i);
			sb.append(" xbin = "+detectors[i].getXBin());
			sb.append(" ybin = "+detectors[i].getYBin());
			// TODO Window
		}
		return sb.toString();
	}
	
}
