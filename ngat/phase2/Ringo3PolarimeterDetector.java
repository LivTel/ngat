// PolarimeterDetector.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/Ringo3PolarimeterDetector.java,v 1.1 2013-06-28 10:13:18 cjm Exp $
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the Polarimter Detector.
 */
public class Ringo3PolarimeterDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: Ringo3PolarimeterDetector.java,v 1.1 2013-06-28 10:13:18 cjm Exp $"); 

	/** The name of the detector.*/
	public static final String name = "EMCCD";
    
	/**
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = -7113226725676098949L;   
	
    /**
	 * The maximum window count: 1.
	 */
	public static final int maxWindowCount = 1;
    
	/**
	 * The X pixel count: 512.
	 */
	public static final int xPixelCount = 512;
    
	/**
	 * The Y pixel count: 512.
	 */
	public static final int yPixelCount = 512;
	
	/**
	 * The maximum bin size in X axis: 4.
	 */
	public static final int maxXBins = 4;

	/**
	 * The maximum bin size in Y axis: 4.
	 */
	public static final int maxYBins = 4;

	public final String getName()
	{  
		return name; 
	}

	/**
	 * Subclasses should override to return the correct value. 
	 */
	public int getMaxDetectorCount()
	{ 
		return 1;
	}

	public final int getMaxWindowCount()
	{ 
		return maxWindowCount;
	}

	public final int getXPixelCount()
	{
		return xPixelCount;
	}

	public final int getYPixelCount()
	{
		return yPixelCount;
	}
    
	public final int getMaxXBins()
	{
		return maxXBins;
	}
    
	public final int getMaxYBins()
	{
		return maxYBins;
	}

	/**
	 * Create a Ringo3PolarimeterDetector.
	 */
	public Ringo3PolarimeterDetector() {
		super();
	}
    
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<Ringo3PolarimeterDetector name= '"+name+"'>");
	out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
	out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
	for (int i = 0; i < getMaxWindowCount(); i++) {
	    if (windows[i] != null) {
		windows[i].writeXml(out,level+1);
	    }
	}
	out.println(tab(level)+"</Ringo3PolarimeterDetector>");
    } 

	/**
	 * equals method, comparing the contents of this detector and the 'other' one. This compares contents
	 * rather than object references. This method should really be in the Detector class, and the Window comparison
	 * in the Window class.
	 * @param other The comparison object, should be of class Ringo3PolarimeterDetector.
	 * @return Returns true if the contents of the fields in the detector, and associated window fields, 
	 *         have the same values.
	 */
	public boolean equals(Object other)
	{
		Ringo3PolarimeterDetector otherDetector = null;
		Window thisWindow,otherWindow;

		if((other instanceof Ringo3PolarimeterDetector) == false)
			return false;
		otherDetector = (Ringo3PolarimeterDetector)other;
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
	 * toString method.
	 * @see #getXBin
	 * @see #getYBin
	 * @see #getMaxWindowCount
	 * @see #getWindow
	 * @see ngat.phase2.Window#getXs
	 * @see ngat.phase2.Window#getYs
	 * @see ngat.phase2.Window#getXe
	 * @see ngat.phase2.Window#getYe
	 */
	public String toString()
	{
		StringBuffer returnString = null;

		returnString = new StringBuffer("Ringo3PolarimeterDetector: (Bin ["+getXBin()+", "+getYBin()+"] ,");
		for(int i = 0; i < getMaxWindowCount(); i++)
		{
			Window w = null;
			
			w = getWindow(i);
			if(w != null)
			{
				returnString = returnString.append("Window [xs="+w.getXs()+", ys="+w.getYs()+
								   ", xe="+w.getXe()+", ye="+w.getYe()+
								   ", isActive="+w.isActive()+"]");
			}
		}
		returnString = returnString.append(")");
		return returnString.toString();
	}
}
