package ngat.phase2;

import java.io.*;
import java.util.*;
import com.odi.*;
import com.odi.util.*;
import ngat.phase2.nonpersist.*;

/** Subclass of Detector to represent the RATCam EEV42-40 CCD Detector.
 *
 * $Id: CCDDetector.java,v 1.2 2001-02-23 18:45:20 snf Exp $
 *
 */
public class CCDDetector extends Detector implements Serializable {

    /** The name of the detector.*/
    public static final String name = "EEV42-40";
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 1834287133204619671L;
   
    /** The maximum window count: 4.*/
    public static final int maxWindowCount = 4;
    
    /** The X pixel count: 2048.*/
    public static final int xPixelCount = 2048;
    
    /** The Y pixel count: 2048.*/
    public static final int yPixelCount = 2048;
    
    /** The maximum bin size in X axis: 8.*/
    public static final int maxXBins = 8;

    /** The maximum bin size in Y axis: 8.*/
    public static final int maxYBins = 8;

    public final String getName() { return name ; }

    /** Subclasses should override to return the correct value. */
    public int getMaxDetectorCount() { return 1;}

    public final int getMaxWindowCount() { return maxWindowCount;}

    public final int getXPixelCount() { return xPixelCount;}

    public final int getYPixelCount() { return yPixelCount;}
    
    public final int getMaxXBins() { return maxXBins ;}
    
    public final int getMaxYBins() { return maxYBins;}

    /** Create a CCDDetector.*/
    public CCDDetector() {
	super();
    }
    
    /** Create a CCDDetector from a nonpersistent NPCCDDetector.*/
    public CCDDetector(NPCCDDetector npDetector) {
	super(npDetector);
    }
    
    /** Translate this CCDDetector into an NPCCDDetector.*/
    public NPDetector makeNP() {
	// #### This needs to be replaced by some reflect stuff in Detector
	// #### to get the correct subclass.
	NPCCDDetector npDetector = new NPCCDDetector();
	npDetector.setXBin(xBin);
	npDetector.setYBin(yBin); 	
	// Copy all Windows across, ignore TooManyWindows.
	for (int i = 0; i < getMaxWindowCount(); i++) {	    
	    try {
		if ( windows[i] != null) {
		    NPWindow npWindow = windows[i].makeNP();		   
		    npDetector.setNPWindow(i, npWindow);		    
		}
	    } catch(IllegalArgumentException twe) {
		System.out.println("CCDDetector.Translation Error doing Window to NPWindow::"+twe.getMessage());}
	}	
	return npDetector;
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 12:31:23  snf
/** Initial revision
/** */
