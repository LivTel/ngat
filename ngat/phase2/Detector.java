package ngat.phase2;

import ngat.phase2.nonpersist.*;
import java.io.*;
import java.util.*;
import com.odi.*;
import com.odi.util.*;

/**
 * Encapsulates properties of the detector component of an instrument.
 * This class is a base class and should always be subclassed to provide
 * implementations of the accessor methods.
 * <br>
 * $Id: Detector.java,v 1.2 2001-02-23 18:45:20 snf Exp $
 *
 */
public class Detector implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 9195725272547947756L;
    
    /** Override to return the name of the Detector. */
    public String getName() { return "Detector";}
   
    /** Override to return the maximum number of windows allowed.*/
    public int getMaxWindowCount() {return 0;}
    
    /** Override to return the number of pixels in the X axis.*/
    public int getXPixelCount() { return 0;}
    
    /** Override to return the number of pixels in the Y axis.*/
    public int getYPixelCount() { return 0;}
    
    /** Override to return the maximum number of pixels which
     * can be binned in the X axis.*/
    public int getMaxXBins() { return 0;}
    
    /** Override to return the maximum number of pixels which
     * can be binned in the Y axis.*/
    public int getMaxYBins() { return 0;}

    /** Stores the set of window information for the detector.*/
    protected Window[] windows;
    
    /** Number of pixels to bin in X direction. */
    protected int xBin;
    
    /** Number of pixels to bin in Y direction. */
    protected int yBin;
    
    /** Create a generic detector.*/
    public Detector() {
	windows = new Window[getMaxWindowCount()];
    }

    /** Create a Detector from a nonpersistent NPDetector.*/
    public Detector(NPDetector npDetector) {
	this();
	setXBin(npDetector.getXBin());
	setYBin(npDetector.getYBin());
	// Copy all Windows across, ignore TooManyWindows.
	for (int i = 0; i < getMaxWindowCount(); i++) {
	    try {
		if (npDetector.getWindow(i) == null) {
		    setWindow(i, null);
		} else {
		    setWindow(i, new Window(npDetector.getWindow(i)));
		}
	    } catch(IllegalArgumentException twe) {
		System.out.println("Detector.Translation Error doing NPWindow to Window");
	    }
	}
    }
    
   
    /** Set the windows reference to the supplied array.
     * @param windows A predefined set of window settings.*/
    public void setWindows(Window[] windows) { this.windows = windows;}
    
    /** Return a reference to the windowing information.*/
    public Window[] getWindows() { return windows;}
    
    /** Add a window to the list. 
     * @param winNo The number of the window to set.
     * @param window The preset window information.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount().*/
    public void setWindow(int winNo, Window window) 
	throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new  IllegalArgumentException("Window number "+winNo);
	}
	windows[winNo] = window;
    }
    
    /** Make a Window and add to the list. 
     * @param winNo The number of the window to set.
     * @param xs The X coordinate of the top/left corner of the window.
     * @param ys The Y coordinate of the top/left corner of the window.
     * @param xe The X coordinate of the bottom/right corner of the window.
     * @param ye The Y coordinate of the bottom/right corner of the window.
     * @exception IllegalArgumentException  If the window number is < 0 or 
     * > getMaxWindowCount() or the X or Y start values are < 0 or the
     * X end value is > getXPixelCount() or the Y end value is > getYPixelCount().*/
    public void setWindow(int winNo, int xs, int ys, int xe, int ye)
	throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new  IllegalArgumentException("Window number "+winNo);
	}
	if (xs < 0 || ys < 0 || xs >= getXPixelCount() || ys >= getYPixelCount()) {
	    throw new IllegalArgumentException("Window bounds");
	}
	windows[winNo] = new Window(xs, ys, xe, ye);
    }

    /** Remove a window from the list -  is made null.
     * @param winNo The number of the window to clear.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount().*/
    public void clearWindow(int winNo) throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new IllegalArgumentException("Window number "+winNo);
	}
	windows[winNo] = null;;
    }
    
    /** Remove all windows i.e.clear them from the list and set to null.*/
    public void clearAllWindows() {
	for (int i = 0; i < getMaxWindowCount(); i++) {
	    windows[i] = null;
	}
    }
    
    /** Gets a reference to the nth window. 
     * @param winNo The number of the window.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount().
     * @return A reference to the nth window.*/
    public Window getWindow(int winNo) throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new IllegalArgumentException("Window number "+winNo);
	}
	return windows[winNo];
    }
    
    /** Sets the nominated window active. 
     * You should only access the window's active flag via this method. 
     * @param winNo The number of the window to activate.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount().*/
    public void activateWindow(int winNo) throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new IllegalArgumentException("Window number "+winNo);
	}
	windows[winNo].setActive(true);
    }
    
    /** Sets the nominated window inactive. 
     *  You should only access the window's active flag via this method. 
     * @param winNo The number of the window to deactivate.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount().*/
    public void deactivateWindow(int winNo) throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new IllegalArgumentException("Window number "+winNo);
	}
	windows[winNo].setActive(false);
    }
    
    /** Returns the activation status of the specified window. 
     * @param winNo The number of the window.
     * @exception IllegalArgumentException If the window number is < 0 or > getMaxWindowCount(). 
     * @return True if the specified window is active.*/
    public boolean isActiveWindow(int winNo) throws IllegalArgumentException {
	if (winNo < 0 || winNo >= getMaxWindowCount()){
	    throw new IllegalArgumentException("Window number "+winNo);
	}
	return windows[winNo].isActive();
	// OR return (windowFlags & (1<<i) > 0);
    }
    
    /** Returns a bitmask to indicate which windows are active. 
     * The ith window is active if the bit at position i (from LSB) is set
     * otherwise the window is inactive. <br>i.e. <br>
     * xxxx1101 indicates that window: 0=set, 1=clear, 2=set, 3=set.<br>
     * Bit positions > getMaxWindowCount() are undefined.*/
    public int getWindowFlags() { 
	int mask = 0;
	for (int i = 0; i < getMaxWindowCount(); i++) {	   	  
	    if (windows[i] != null) {
		mask |= (windows[i].isActive() ? (1 << i) : 0);
	    }
	}
	return mask;	
    }
   
  
    /** Sets the number of pixels to bin in X direction.*/
    public void setXBin(int in) { this.xBin = in;}
    
    /** Returns the number of pixels to bin in X direction. */
    public int getXBin() { return xBin;}
    
    /** Sets the number of pixels to bin in Y direction.*/
    public void setYBin(int in) { this.yBin = in;}    
    
    /** Returns the number of pixels to bin in Y direction . */
    public int getYBin() { return yBin;}

}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2000/11/23 12:15:56  snf
/** Initial revision
/** */
