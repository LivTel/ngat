package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;


// Generated by O3J 


public class Mosaic extends NPDBObject implements Serializable {
    
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 8500970155285516661L;

    // Variables.

    
    /** Constant. Indicates Mosaic pattern ARRAY. */
    public static final int ARRAY  = 2;
   
    
    /** Constant. Indicates Mosaic position (N).*/
    public static final int NORTH = 2;

    /** Constant. Indicates Mosaic position (S).*/
    public static final int SOUTH = 128 ;
    
    /** Constant. Indicates Mosaic position (E).*/
    public static final int EAST = 32;
    
    /** Constant. Indicates Mosaic position (W).*/
    public static final int WEST = 8 ;
    
    /** Constant. Indicates Mosaic position (NW).*/
    public static final int NORTH_WEST = 1;

    /** Constant. Indicates Mosaic position (NE).*/
    public static final int NORTH_EAST = 4;
    
    /** Constant. Indicates Mosaic position (SW).*/
    public static final int SOUTH_WEST = 64;

    /** Constant. Indicates Mosaic position (SE).*/
    public static final int SOUTH_EAST = 256;

    /** Constant. Indicates Mosaic position (C).*/
    public static final int CENTER = 16;


    /** Constant. Indicates Mosaic pattern (V).*/
    public static final int VERTICAL = NORTH + CENTER + SOUTH;

    /** Constant. Indicates Mosaic pattern (H).*/
    public static final int HORIZONTAL = WEST + CENTER + EAST;
    
    /** Constant. Indicates Mosaic pattern (+).*/
    public static final int CROSS = VERTICAL + HORIZONTAL;
    
    /** Constant. Indicates Mosaic pattern (X).*/
    public static final int X = NORTH_WEST + SOUTH_WEST + CENTER + NORTH_EAST + SOUTH_EAST;
    
    /** Constant. Indicates Mosaic pattern (G).*/
    public static final int GRID = 511;
    
    /** Constant. Indicates Mosaic pattern (G-C).*/
    public static final int HOLE = 511 - 16;
    
    /** Constant. Indicates Mosaic pattern (S).*/
    public static final int SINGLE = CENTER;
    
    /** Constant. Indicates Mosaic pattern (SU).*/
    public static final int SLOPE_UP = SOUTH_WEST + CENTER + NORTH_EAST;
    
    /** Constant. Indicates Mosaic pattern (SD).*/
    public static final int SLOPE_DOWN = NORTH_WEST + CENTER + SOUTH_EAST;

    /** The mosaic pattern (from SINGLE | CROSS | ARRAY + others).*/
    protected int pattern;

    /**  gap between mosaic RA offsets (rads). */
    protected double offsetRA;
    
    /**  gap between mosaic Dec offsets (rads). */
    protected double offsetDec;
    
    /**  number of RA offsets in array/cross. */
    protected int cellsRA;
    
    /**  number of Dec offsets in array/cross. */
    protected int cellsDec;

    /** Indicates whether offsets should be scaled to the nearest full pixel.*/
    protected boolean scaleToPixel;


    public Mosaic() {
	super();
    }

    public Mosaic(String name) {
	super(name);
    }


    /** Sets the pattern.*/
    public void setPattern(int in) { this.pattern = in;}
      
    /** Returns the mosaic pattern. */
    public int getPattern() { return pattern;}
    
    /** Sets the  gap between mosaic RA offsets (rads) .*/
    public void setOffsetRA(double in) { this.offsetRA = in;}
    
    /** Returns the  gap between mosaic RA offsets (rads). */
    public double getOffsetRA() { return offsetRA;}
    
    /** Sets the  gap between mosaic Dec offsets (rads) .*/
    public void setOffsetDec(double in) { this.offsetDec = in;}
    
    /** Returns the  gap between mosaic Dec offsets (rads). */
    public double getOffsetDec() { return offsetDec;}
    
    /** Sets the  number of RA offsets in array/cross .*/
    public void setCellsRA(int in) { this.cellsRA = in;}
    
    /** Returns the  number of RA offsets in array/cross. */
    public int getCellsRA() { return cellsRA;}
    
    /** Sets the  number of Dec offsets in array/cross .*/
    public void setCellsDec(int in) { this.cellsDec = in;}
    
    /** Returns the  number of Dec offsets in array/cross. */
    public int getCellsDec() { return cellsDec;}   
    
    /** Set to indicate whether offsets should be scaled to the nearest full pixel.*/
    public void setScaleToPixel(boolean in) { this.scaleToPixel = in; }
    
    /** Get indication of whether offsets should be scaled to the nearest full pixel.*/
    public boolean getScaleToPixel() { return scaleToPixel; }
 
    // Clone Constructor.
    public NPDBObject copy() {
	Mosaic newMosaic = new Mosaic(name);
	newMosaic.setPath(path);
	newMosaic.setPattern(pattern);
	newMosaic.setOffsetRA(offsetRA);
	newMosaic.setOffsetDec(offsetDec);
	newMosaic.setCellsRA(cellsRA);
	newMosaic.setCellsDec(cellsDec);
	newMosaic.setScaleToPixel(scaleToPixel);
	return newMosaic;
    }
    
    /**Formatted Text Output as XML.*/
    public void writeXml(PrintStream out, int level) { 
	//super.writeXml(out, level);

	out.println(tab(level)+"<mosaic pattern = '"+toMosaicString(pattern)+
		    "' scale = '"+scaleToPixel+
		    "'>");
	out.println(tab(level+1)+"<raOffset>"+offsetRA+"</raOffset>");
	out.println(tab(level+1)+"<decOffset>"+offsetDec+"</decOffset>");
	out.println(tab(level+1)+"<raCells>"+cellsRA+"</raCells>");
	out.println(tab(level+1)+"<decCells>"+cellsDec+"</decCells>");
	out.println(tab(level)+"</mosaic>");
    } // end (write).

    public String toString() {
	return "[Mosaic: Pattern="+toMosaicString(pattern)+
	    " ("+cellsRA+"x"+cellsDec+")"+	    
	    ", GridSize=(dx="+offsetRA+", dy="+offsetDec+") rads"+"]";
	
	// e.g. [Mosaic: Pattern=CROSS (3x5), GridSize=(dx=2.3, dy=3.4) rads]
    }

    /** Returns a description of the Pattern.*/
    public static String toMosaicString(int pattern) {
	switch (pattern) {
	case VERTICAL:
	    return "VERTICAL";
	case HORIZONTAL:
	    return "HORIZONTAL";
	case CROSS:
	    return "CROSS";
	case  X:
	    return "X";
	case GRID:
	    return "GRID";
	case HOLE:
	    return "HOLE";
	case SINGLE:
	    return "SINGLE";
	case SLOPE_UP:
	    return "SLOPE_UP";
	case SLOPE_DOWN:
	    return "SLOPE_DOWN";
	case ARRAY:
	    return "ARRAY-DEFUNCT";
	default:
	    return "UNKNOWN-("+pattern+")";
	}
    }

	
}
