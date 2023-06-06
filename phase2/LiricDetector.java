// LiricDetector.java
// $Id$
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the Liric Raptor Ninox-640 IR Detector.
 * This is an InGaAS sensor with wavelength range 400-1700nm, 640 x 512 pixels (15um x 15um pixel size).
 */
public class LiricDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 

	/** 
	 * The name of the detector. 
	 */
	public static final String name = "Liric Raptor Ninox-640";
	/**
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = diddly;   
	/**
	 * The maximum window count: 0.
	 */
	public static final int maxWindowCount = 0;
    	/**
	 * The X pixel count: 640.
	 */
	public static final int xPixelCount = 640;
	/**
	 * The Y pixel count: 512.
	 */
	public static final int yPixelCount = 512;
	/**
	 * The maximum bin size in X axis: 1.
	 */
	public static final int maxXBins = 1;

	/**
	 * The maximum bin size in Y axis: 1.
	 */
	public static final int maxYBins = 1;

	public final String getName()
	{  
		return name; 
	}

	/**
	 * The number of detectors.
	 * @return The number of detectors (1).
	 */
	public int getMaxDetectorCount()
	{
		return 1;
	}

	/**
	 * Retrieve number of windowss.
	 * @return The number of windows (0).
	 * @see #maxWindowCount
	 */
	public final int getMaxWindowCount()
	{ 
		return maxWindowCount;
	}

	/**
	 * Retrieve the number of pixels of each detector in the X (horizontal) orientation.
	 * @return The number of pixels.
	 * @see #xPixelCount
	 */
	public final int getXPixelCount()
	{
		return xPixelCount;
	}

	/**
	 * Retrieve the number of pixels of each detector in the Y (vertical) orientation.
	 * @return The number of pixels.
	 * @see #yPixelCount
	 */
	public final int getYPixelCount()
	{
		return yPixelCount;
	}
    
	/**
	 * Retrieve the maximum amount of pixel binning allowed in the X (horizontal) direction.
	 * @return The maximum amount of binning allowed in the X direction.
	 * @see #maxXBins
	 */
	public final int getMaxXBins()
	{
		return maxXBins;
	}
    
	/**
	 * Retrieve the maximum amount of pixel binning allowed in the Y (vertical) direction.
	 * @return The maximum amount of binning allowed in the Y direction.
	 * @see #maxYBins
	 */
	public final int getMaxYBins()
	{
		return maxYBins;
	}

	/**
	 * Create a LiricDetector.
	 */
	public LiricDetector()
	{
		super();
	}
    
	public void writeXml(PrintStream out, int level)
	{
		out.println(tab(level)+"<LiricDetector name= '"+name+"'>");
		out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
		out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
		for (int i = 0; i < getMaxWindowCount(); i++) {
			if (windows[i] != null) {
				windows[i].writeXml(out,level+1);
			}
		}
		out.println(tab(level)+"</LiricDetector>");
	}
}
