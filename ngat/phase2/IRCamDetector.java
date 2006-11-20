package ngat.phase2;

import java.io.*;
import java.util.*;

import jyd.storable.*;
import jyd.collection.*;

import ngat.phase2.nonpersist.*;

/** Subclass of Detector to represent the SUPIR Cam  CCD Detector.
 *
 * $Id: IRCamDetector.java,v 1.1 2006-11-20 14:51:23 cjm Exp $
 *
 */
public class IRCamDetector extends Detector implements Serializable {

    /** The name of the detector.*/
    public static final String name = "SUPIRCAM-Detector";
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 325505489400146710L;
   
    /** The maximum window count: 2.*/
    public static final int maxWindowCount = 2;
    
    /** The X pixel count: 256.*/
    public static final int xPixelCount = 256;
    
    /** The Y pixel count: 256.*/
    public static final int yPixelCount = 256;
    
    /** The maximum bin size in X axis: 1.*/
    public static final int maxXBins = 1;

    /** The maximum bin size in Y axis: 1.*/
    public static final int maxYBins = 1;

    public final String getName() { return name ; }

    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() { return 1;}

    public final int getMaxWindowCount() { return maxWindowCount;}

    public final int getXPixelCount() { return xPixelCount;}

    public final int getYPixelCount() { return yPixelCount;}
    
    public final int getMaxXBins() { return maxXBins ;}
    
    public final int getMaxYBins() { return maxYBins;}

    /** Create a IRCamDetector.*/
    public IRCamDetector() {
	super();
    }
    
}

/** $Log: not supported by cvs2svn $ */
