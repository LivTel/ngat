package ngat.phase2;

import java.io.*;
import java.util.*;
import jyd.storable.*;
import jyd.collection.*;
import ngat.phase2.nonpersist.*;

/** Subclass of Detector to represent the SITe SI-502AB Detector.
 *
 * $Id: LowResSpecDetector.java,v 1.2 2008-08-21 10:09:58 eng Exp $
 *
 */
public class LowResSpecDetector extends Detector implements Serializable {

    /** The name of the detector.*/
    public static final String name = "SITe SI-502AB";
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 2525571208702325099L;
   
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

    /** Create a LowResSpecDetector.*/
    public LowResSpecDetector() {
	super();
    }

    public void writeXml(PrintStream out, int level) {
        out.println(tab(level)+"<LowResSpecDetector name= '"+name+"'>");
        out.println(tab(level+1)+"<xBin>"+xBin+"</xBin>");
        out.println(tab(level+1)+"<yBin>"+yBin+"</yBin>");
        for (int i = 0; i < getMaxWindowCount(); i++) {
            if (windows[i] != null) {
                windows[i].writeXml(out,level+1);
            }
        }
        out.println(tab(level)+"</LowResSpecDetector>");
    }

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/11/20 14:51:23  cjm
/** Initial revision
/**
/** Revision 1.1  2000/11/23 12:31:23  snf
/** Initial revision
/** */
