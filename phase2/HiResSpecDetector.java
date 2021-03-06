package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** Subclass of Detector to represent the SITe SI-502AB Detector.
 *
 * $Id$
 *
 */
public class HiResSpecDetector extends Detector implements Serializable {

    /** The name of the detector.*/
    public static final String name = "SITe SI-502AB";
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 263475011226192197L;
   
    /** The maximum window count: 1.*/
    public static final int maxWindowCount = 1;
    
    /** The X pixel count: 512.*/
    public static final int xPixelCount = 512;
    
    /** The Y pixel count: 511.*/
    public static final int yPixelCount = 511;
    
    /** The maximum bin size in X axis: 8.*/
    public static final int maxXBins = 8;

    /** The maximum bin size in Y axis: 8.*/
    public static final int maxYBins = 8;

    public final String getName() {  return name ; }

    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() {  return 1;}

    public final int getMaxWindowCount() {  return maxWindowCount;}

    public final int getXPixelCount() {  return xPixelCount;}

    public final int getYPixelCount() {  return yPixelCount;}
    
    public final int getMaxXBins() {  return maxXBins ;}
    
    public final int getMaxYBins() {  return maxYBins;}

    /** Create a HiResSpecDetector.*/
    public HiResSpecDetector() {
	super();
    }
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 12:31:23  snf
/** Initial revision
/** */
