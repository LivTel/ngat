// FrodoSpecDetector.java
// $Header: /space/home/eng/cjm/cvs/ngat/phase2/FrodoSpecDetector.java,v 1.1 2007-05-22 09:07:31 cjm Exp $
package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** 
 * Subclass of Detector to represent a FrodoSpec Detector.
 * These are E2V CCD44-82 4kx2k chips. 15um pixel scale.
 * $Id$
 */
public class FrodoSpecDetector extends Detector implements Serializable
{
	/**
	 * Revision Control System id string, showing the version of the Class.
	 */
	public final static String RCSID = new String("$Id$"); 
	/** 
	 * The name of the detector.
	 */
	public static final String name = "E2V CCD44-82";
    
	/** 
	 * Serial version UID - used to maintain serialization compatibility
	 * across modifications of the class's structure.
	 */
	//private static final long serialVersionUID = 2525571208702325099L;
   
	/** 
	 * The maximum window count: 1.
	 */
	public static final int maxWindowCount = 1;
    
	/** 
	 * The X pixel count: 2048.
	 */
	public static final int xPixelCount = 2048;
    
	/** 
	 * The Y pixel count: 4096 (+6 according to the data sheet).
	 */
	public static final int yPixelCount = 4096;
    
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
		return name ; 
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
		return maxXBins ;
	}
    
	public final int getMaxYBins()
	{  
		return maxYBins;
	}

	/** 
	 * Create aFrodoSpecDetector.
	 */
	public FrodoSpecDetector()
	{
		super();
	}
}

/* 
** $Log: not supported by cvs2svn $
*/
