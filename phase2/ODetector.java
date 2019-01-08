// ODetector.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/ODetector.java,v 1.1 2013-06-28 10:13:18 cjm Exp $

package ngat.phase2;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;


/**
 * Detector for the 'O' optical CCD camera. This is a Fairchild F486 4kx4k array.
 * Also used to represent the engineering chip, which is a Hamamatsu 512x60 CCD array.
 * @version $Revision$
 * @author cjm
 * @see Detector
 */
public class ODetector extends Detector implements Serializable 
{
	/**
	 * The detector name.
	 */
	public static final String name = "Fairchild F486";
	/**
	 * Serial Version UID.This is used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	private static final long serialVersionUID = -2422224619846454290L;
	/** 
	 * The maximum window count: 4.
	 */
	public static final int maxWindowCount = 4;
	/** 
	 * The X pixel count: 4096.
	 */
	public static final int xPixelCount = 4096;
        /** 
	 * The Y pixel count: 4096.
	 */
	public static final int yPixelCount = 4096;
	/** 
	 * The maximum bin size in X axis: 2.
	 */
	public static final int maxXBins = 2;
	/** 
	 * The maximum bin size in Y axis: 2.
	 */
	public static final int maxYBins = 2;

	/**
	 * Name accessor.
	 * @return The name of the detector as a string.
	 * @see #name
	 */
	public final String getName()
	{
		return name;
	}

	/** 
	 * The number of detectors: 1.
	 * @return An integer:1.
	 */
	public int getMaxDetectorCount()
	{
		return 1;
	}

	/**
	 * Return the number of windows.
	 * @return An integer, the number of windows.
	 * @see #maxWindowCount
	 */
	public final int getMaxWindowCount()
	{ 
		return maxWindowCount;
	}

	/**
	 * Return the number of pixels in X in the detector.
	 * @return An integer,the number of pixels in X.
	 * @see #xPixelCount
	 */
	public final int getXPixelCount()
	{ 
		return xPixelCount;
	}
    
	/**
	 * Return the number of pixels in Y in the detector.
	 * @return An integer,the number of pixels in Y.
	 * @see #yPixelCount
	 */
	public final int getYPixelCount()
	{
		return yPixelCount;
	}

	/**
	 * Return the maximum binning of pixels in the X direction.
	 * @return An integer,the maximum binning in X.
	 * @see #maxXBins
	 */
	public final int getMaxXBins()
	{
		return maxXBins;
	}

	/**
	 * Return the maximum binning of pixels in the Y direction.
	 * @return An integer,the maximum binning in Y.
	 * @see #maxYBins
	 */
	public final int getMaxYBins()
	{
		return maxYBins;
	}

	/** 
	 * Create an ODetector.
	 */
	public ODetector()
	{
		super();
	}

	/**
	 * Write an XML representation of this object's contents.
	 * @param out The printstream to write the XML to.
	 * @param level The number of tab's to write.
	 * @see #xBin
	 * @see #yBin
	 * @see #getMaxWindowCount
	 * @see #windows
	 * @see #tab
	 */
	public void writeXml(PrintStream out, int level)
	{
		out.println(tab(level)+"<oDetector name= '"+name+"'>");
		out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
		out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
		for (int i = 0; i < getMaxWindowCount(); i++) {
			if (windows[i] != null) {
				windows[i].writeXml(out,level+1);
			}
		}
		out.println(tab(level)+"</oDetector>");
	}
}
//
// $Log: not supported by cvs2svn $
//
