package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** Subclass of Detector to represent the  Detector.
 *
 * $Id: FixedFormatSpecDetector.java,v 1.1 2003-11-13 15:54:27 snf Exp $
 *
 */
public class FixedFormatSpecDetector extends Detector implements Serializable {

    /** The name of the detector.*/
    public static final String name = "AP32ME";
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.
     */
    private static final long serialVersionUID = 1197191368369317110L;
   
    /** The maximum window count: 1.*/
    public static final int maxWindowCount = 1;
    
    /** The X pixel count: 2184.*/
    public static final int xPixelCount = 2184;
    
    /** The Y pixel count: 1472.*/
    public static final int yPixelCount = 1472;
    
    /** The maximum bin size in X axis: 8.*/
    public static final int maxXBins = 8;

    /** The maximum bin size in Y axis: 50.*/
    public static final int maxYBins = 50;

    public final String getName() {  return name ; }

    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() {  return 1;}

    public final int getMaxWindowCount() {  return maxWindowCount;}

    public final int getXPixelCount() {  return xPixelCount;}

    public final int getYPixelCount() {  return yPixelCount;}
    
    public final int getMaxXBins() {  return maxXBins ;}
    
    public final int getMaxYBins() {  return maxYBins;}

    /** Create a FixedFormatSpecDetector.*/
    public FixedFormatSpecDetector() {
	super();
    }
    
}

/** $Log: not supported by cvs2svn $ */
