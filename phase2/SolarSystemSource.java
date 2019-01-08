package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import jyd.storable.*;
import jyd.collection.*;

import java.util.*;
import java.io.*;

/** Encapsulates information about Solar system objects.*/
public abstract class SolarSystemSource extends Source implements Serializable {
    /** Serial version UID - used to maintain serialization compatibility
     * across modifications of the class's structure.*/
    private static final long serialVersionUID = -3393273544467255931L;

    // Cache Variables
    
    /** Stores the cached RA.*/
    protected double cacheRA;

    /** Stores the cached Dec.*/
    protected double cacheDec;

    /** Stores the cache time.*/
    protected double cacheTime;

    /** Stores the expiry RA. (initially = 0.0) .*/
    protected double exRA;

    /** Stores the expiry Dec. (initially = 0.0) .*/
    protected double exDec;

    /** Stores the expiry time. (initially = 0.0) .*/
    protected double exTime;

    /** The allowable error in RA or Dec (rads). - Used to decide on whether
     * to interpolate.*/
    public static final double MAX_ANGLE_ERROR = 0.005;

    /** Initial time offset.*/
    public static final long INITIAL_DELTA_TIME = 3600000L;

    /** Create a SolarSystemSource with the name 'untitled'.
     */
    public SolarSystemSource() {super();}
    
    /** Create a SolarSyatemSource with the specified name.
     * @param name The name of the source - unique within a Proposal's
     * list of sources.
     */
    public SolarSystemSource(String name) {
	super(name);
    }
    
    // Accessors.

    /** Returns the source's current position.*/
    public abstract Position getPosition();

    /** Returns the current Non-sidereal tracking in RA.*/
    public abstract double getNsTrackRA();
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public abstract double getNsTrackDec();

    // Formatted Text Output.   
    public void writeXml(PrintStream out, int level) {
	super.writeXml(out, level);
    } // end (write).
    
    // Clone Constructor.   
    public NPDBObject copy() {
	try {
	    return (SolarSystemSource)clone();
	} catch (CloneNotSupportedException ce) {return null;}
    } // end (copy).
    
    
} // end class def [SolarSystemSource].
