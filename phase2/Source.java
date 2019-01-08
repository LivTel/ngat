package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;
import java.util.*;
import java.io.*;


/** Encapsulates data about a general Source / target - either a 'fixed'
 * extra-solar object or a planet, comet, moon etc.
 */
public abstract class Source extends NPDBObject implements Serializable {
 
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = 7467445046143920914L;
    
    public static final int FK4 = 4;

    public static final int FK5 = 5;

    public static final char BESSELIAN = 'B';

    public static final char JULIAN = 'J';

    public static final char APPARENT = 'A';

    public static final int EPOCH_B1950 = 1950;

    public static final int EPOCH_J2000 = 2000;

    public static final int EPOCH_CURRENT = 1;

    // Variables.
    
    /**  Coordinate system for epoch measurment. */
    protected float equinox;
    
    /**The calendar designator for the coordinate system:- B = Besselian, J = Julian.*/
    protected char equinoxLetter;
    
    /**  Year and fraction of pm measurement. */
    protected float epoch;

    /** Equinox designator. - This is the preferred mechanism for setting equinox.*/
    protected int equinoxDesignator;

    /** Coordinate Frame FK4 or FK5.*/
    protected int frame;

    // Constructor.
    
    public Source() {this("untitled");}
    
    public Source(String name) {
	super(name);
    }
    
    // Accessors.
    
    /** Sets the  coordinate system for epoch measurment .*/
    public void setEquinox(float in) { this.equinox = in;}
    
    /** Returns the  coordinate system for epoch measurment. */
    public float getEquinox() {  return equinox;}
    
    /** Sets the  calendar designator for the coordinate system.*/
    public void setEquinoxLetter(char in) {  this.equinoxLetter = in;}
    
    /** Returns the calendar designator for the coordinate system.*/
    public char getEquinoxLetter() {  return equinoxLetter;}
    
    /** Sets the  year and fraction of RA/Dec  measurement .*/
    public void setEpoch(float in) {  this.epoch = in;}
    
    /** Returns the  year and fraction of RA/Dec  measurement. */
    public float getEpoch() {  return epoch;}
    

    /** Sets the epoch  designator.*/
    public void setEquinox(int in) {  this.equinoxDesignator = in;}
  
    /** Returns the epoch designator.*/
    public int getEpochDesignator() {  return equinoxDesignator; }    


    /** Set the coordinate frame FK4 or FK5.*/
    public void setFrame(int in) {  this.frame = in; }
    
    /** Returns the coordinate frame.*/
    public int getFrame() {  return frame; }
    
    /** Returns the current position. */
    public abstract Position getPosition();   

    public void writeXml(PrintStream out, int level) {
	super.writeXml(out, level);
	out.println(tab(level+1)+"<equinox>"+equinoxLetter+equinox+"</equinox>");
	out.println(tab(level+1)+"<epoch>"+epoch+"</epoch>");
	out.println(tab(level+1)+"<frame>FK"+frame+"</frame>");
    } // end (write).
    
    // Clone Constructor.    
    public NPDBObject copy() {
	try {
	    return (Source)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
} // end class def [Source].
