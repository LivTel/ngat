// RISEDetector.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/RISEDetector.java,v 1.1 2007-12-11 16:00:31 cjm Exp $
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent the RISE Detector. This is a simple CCD with binning configuration only.
 */
public class RISEDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 

	/** The name of the detector.*/
	public static final String name = "Andor-DV435";
    
	/**
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	private static final long serialVersionUID = -941156126210593922L;   
	
	/**
	 * The maximum window count: 1.
	 */
	public static final int maxWindowCount = 1;
    
	/**
	 * The X pixel count: 1048.
	 * Actually 1024x1024 active imaging area, but allow for bias strip.
	 */
	public static final int xPixelCount = 1048;
    
	/**
	 * The Y pixel count: 1048.
	 * Actually 1024x1024 active imaging area, but allow for bias strip.
	 */
	public static final int yPixelCount = 1048;
	
	/**
	 * The maximum bin size in X axis: 8.
	 */
	public static final int maxXBins = 8;

	/**
	 * The maximum bin size in Y axis: 8.
	 */
	public static final int maxYBins = 8;

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
	 * Create a RISEDetector.
	 */
	public RISEDetector() {
		super();
	}
    
    public void writeXml(PrintStream out, int level) {
	out.println(tab(level)+"<RISEDetector name= '"+name+"'>");
	out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
	out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
	for (int i = 0; i < getMaxWindowCount(); i++) {
	    if (windows[i] != null) {
		windows[i].writeXml(out,level+1);
	    }
	}
	out.println(tab(level)+"</RISEDetector>");
    } 

}
/*
** $Log: not supported by cvs2svn $
*/
