// LOTUSDetector.java
// $HeadURL$
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the LOTUS Detector.
 * This  is a Starlight Express Trius SX-35. The detector is a Grade 2  Kodak Truesense KAI11002M interline CCD,
 * with 4032 x 2688 9uM x 9uM pixels
 */
public class LOTUSDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * The name of the detector.
	 */
	public static final String name = "Kodak Truesense KAI11002M";
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	static final long serialVersionUID = -1116845668157893485L;
	/** 
	 * The maximum window count: 0.
	 */
	public static final int maxWindowCount = 0;
    
	/** 
	 * The X pixel count: 4032.
	 */
	public static final int xPixelCount = 4032;
	/** 
	 * The Y pixel count: 2688.
	 */
	public static final int yPixelCount = 2688;
	/**
	 * The maximum bin size in X axis: 4.
	 */
	public static final int maxXBins = 4;

	/**
	 * The maximum bin size in Y axis: 255.
	 */
	public static final int maxYBins = 255;

	public final String getName()
	{  
		return name; 
	}

	/**
	 * Overridden to return the correct number of detectors for this instrument:1. 
	 */
	public int getMaxDetectorCount()
	{ 
		return 1;
	}

	/**
	 * Overridden to return the correct number of windows for this instrument. 
	 * @see #maxWindowCount
	 */
	public final int getMaxWindowCount()
	{ 
		return maxWindowCount;
	}

	/**
	 * Overridden to return the correct number of pixels in the X axis.
	 * @see #xPixelCount
	 */
	public final int getXPixelCount()
	{
		return xPixelCount;
	}

	/**
	 * Overridden to return the correct number of pixels in the Y axis.
	 * @see #yPixelCount
	 */
	public final int getYPixelCount()
	{
		return yPixelCount;
	}
    
	/**
	 * Overridden to return the correct maximum X binning factor.
	 * @see #maxXBins
	 */
	public final int getMaxXBins()
	{
		return maxXBins;
	}
    
	/**
	 * Overridden to return the correct maximum Y binning factor.
	 * @see #maxYBins
	 */
	public final int getMaxYBins()
	{
		return maxYBins;
	}

	/** 
	 * Create a LOTUSDetector.
	 */
	public LOTUSDetector()
	{
		super();
	}

	/**
	 * equals method, comparing the contents of this detector and the 'other' one. This compares contents
	 * rather than object references. This method should really be in the Detector class, and the Window comparison
	 * in the Window class.
	 * @param other The comparison object, should be of class LOTUSDetector.
	 * @return Returns true if the contents of the fields in the detector, and associated window fields, 
	 *         have the same values.
	 */
	public boolean equals(Object other)
	{
		LOTUSDetector otherDetector = null;
		Window thisWindow,otherWindow;

		if((other instanceof LOTUSDetector) == false)
			return false;
		otherDetector = (LOTUSDetector)other;
		if(xBin != otherDetector.getXBin())
			return false;
		if(yBin != otherDetector.getYBin())
			return false;
		if(xPixelCount != otherDetector.getXPixelCount())
			return false;
		if(yPixelCount != otherDetector.getYPixelCount())
			return false;
		if(maxWindowCount != otherDetector.getMaxWindowCount())
			return false;
		for(int i = 0; i < maxWindowCount; i++)
		{
			thisWindow = windows[i];
			// we know this should work, as we have tested window counts are equal above
			otherWindow = otherDetector.getWindow(i);
			// windows can be null
			if((thisWindow != null)&&(otherWindow != null))
			{
				// The next bit should go in Windows equals method
				if(thisWindow.isActive() != otherWindow.isActive())
					return false;
				if(thisWindow.getXs() != otherWindow.getXs())
					return false;
				if(thisWindow.getYs() != otherWindow.getYs())
					return false;
				if(thisWindow.getXe() != otherWindow.getXe())
					return false;
				if(thisWindow.getYe() != otherWindow.getYe())
					return false;
			}
			else if(((thisWindow == null)&&(otherWindow != null))||
				((thisWindow != null)&&(otherWindow == null)))
			{
				// one window is null and one isn't, they arn't the same
				return false;
			}
			// else both windows are null - they are the same
		}// end for on windows
		return true;
	}

	/**
	 * Return a string representation of the detector.
	 * @see #name
	 * @see #getXBin
	 * @see #getYBin
	 */
	public String toString()
	{
		StringBuffer sb = null;

		sb = new StringBuffer();
		sb.append("LOTUSDetector:"+name+" Binning: [ "+getXBin()+", "+getYBin()+" ] ");
		return sb.toString();
	}
}

