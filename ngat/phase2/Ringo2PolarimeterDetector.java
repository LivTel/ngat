// PolarimeterDetector.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/Ringo2PolarimeterDetector.java,v 1.2 2013-07-04 13:35:42 eng Exp $
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the Polarimter Detector.
 */
public class Ringo2PolarimeterDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id: Ringo2PolarimeterDetector.java,v 1.2 2013-07-04 13:35:42 eng Exp $"); 

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
	 * The maximum bin size in X axis: 2.
	 */
	public static final int maxXBins = 2;

	/**
	 * The maximum bin size in Y axis: 2.
	 */
	public static final int maxYBins = 2;

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
	 * Create a Ringo2PolarimeterDetector.
	 */
	public Ringo2PolarimeterDetector() {
		super();
	}
    
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<Ringo2PolarimeterDetector name= '"+name+"'>");
	out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
	out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
	for (int i = 0; i < getMaxWindowCount(); i++) {
	    if (windows[i] != null) {
		windows[i].writeXml(out,level+1);
	    }
	}
	out.println(tab(level)+"</Ringo2PolarimeterDetector>");
    } 

}
