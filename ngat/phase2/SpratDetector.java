// SpratDetector.java
// $HeadURL$
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the Sprat Detector.
 * This is an Andor iDus DU420A-BV USB camera with a 1024x255 pixel detector with 26um pixels.
 */
public class SpratDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * The name of the detector.
	 */
	public static final String name = "Andor iDus DU420A-BV";
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = ;
	/** 
	 * The maximum window count: 1.
	 */
	public static final int maxWindowCount = 1;
    
	/** 
	 * The X pixel count: 1024.
	 */
	public static final int xPixelCount = 1024;
    
	/** 
	 * The Y pixel count: 255.
	 */
	public static final int yPixelCount = 255;
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
	 * Create a SpratDetector.
	 */
	public SpratDetector()
	{
		super();
	}

	/**
	 * equals method, comparing the contents of this detector and the 'other' one. This compares contents
	 * rather than object references. This method should really be in the Detector class, and the Window comparison
	 * in the Window class.
	 * @param other The comparison object, should be of class SpratDetector.
	 * @return Returns true if the contents of the fields in the detector, and associated window fields, 
	 *         have the same values.
	 */
	public boolean equals(Object other)
	{
		SpratDetector otherDetector = null;
		Window thisWindow,otherWindow;

		if((other instanceof SpratDetector) == false)
			return false;
		otherDetector = (SpratDetector)other;
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
	 * @see #maxWindowCount
	 * @see #windows
	 * @see ngat.phase2.Window#isActive
	 * @see ngat.phase2.Window#getXs
	 * @see ngat.phase2.Window#getYs
	 * @see ngat.phase2.Window#getXe
	 * @see ngat.phase2.Window#getYe
	 */
	public String toString()
	{
		StringBuffer sb = null;

		sb = new StringBuffer();
		sb.append("SpratDetector:"+name+" Binning: [ "+getXBin()+", "+getYBin()+" ] ");
		for(int index = 0; index < maxWindowCount; index++)
		{
			sb.append(" Window: [ isActive = "+windows[index].isActive()+
				  ", Xs = "+windows[index].getXs()+", Ys = "+windows[index].getYs()+
				  ", Xe = "+windows[index].getXe()+", Ye = "+windows[index].getYe()+"]");
		}
		return sb.toString();
	}
}

