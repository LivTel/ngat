package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;
import java.util.*;
import java.io.*;


/** Encapsulates data about a general Source / target - either a 'fixed'
 * extra-solar object or a planet, comet, moon etc.
 */
public abstract class Source extends DBObject implements Serializable {

    public static final int FK4 = 4;

    public static final int FK5 = 5;

    // Variables.
    
    /**  Coordinate system for epoch measurment. */
    protected float equinox;
    
    /**The calendar designator for the coordinate system:- B = Besselian, J = Julian.*/
    protected char equinoxLetter;
    
    /**  Year and fraction of pm measurement. */
    protected float epoch;

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
    public float getEquinox() { return equinox;}
    
    /** Sets the  calendar designator for the coordinate system.*/
    public void setEquinoxLetter(char in) { this.equinoxLetter = in;}
    
    /** Returns the calendar designator for the coordinate system.*/
    public char getEquinoxLetter() { return equinoxLetter;}
    
    /** Sets the  year and fraction of RA/Dec  measurement .*/
    public void setEpoch(float in) { this.epoch = in;}
    
    /** Returns the  year and fraction of RA/Dec  measurement. */
    public float getEpoch() { return epoch;}

    /** Set the coordinate frame FK4 or FK5.*/
    public void setFrame(int in) { this.frame = in; }

    /** Returns the coordinate frame.*/
    public int getFrame() { return frame; }

    /** Returns the current position. */
    public abstract Position getPosition();   
    
    // Descendant Mutators.
    
    // NP -> P Translator.
    
    public Source(NPSource npSource) {
	super(npSource);
	equinox = npSource.getEquinox();
	equinoxLetter = npSource.getEquinoxLetter();
	epoch = npSource.getEpoch();
	frame = npSource.getFrame();
    } // end (NP -> P Translator).
    
    // P -> NP Translator.
    
    public void stuff(NPSource npSource) {
	super.stuff(npSource);
	npSource.setEquinox(getEquinox());
	npSource.setEquinoxLetter(getEquinoxLetter());
	npSource.setEpoch(getEpoch());
	npSource.setFrame(getFrame());
    } // end (P -> NP Translator).
    
    // P -> NP Translator.
    
    public NPDBObject makeNP() {
	NPSource npSource = new NPSource();
	stuff(npSource);
	return npSource;
    } // end (makeNp).
    

} // end class def [Source].
