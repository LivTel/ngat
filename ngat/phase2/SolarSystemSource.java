package ngat.phase2;

import ngat.astrometry.*;
import ngat.phase2.nonpersist.*;

import com.odi.*;
import com.odi.util.*;

import java.util.*;
import java.io.*;

/** Encapsulates information about Solar system objects.*/
public abstract class SolarSystemSource extends Source implements Serializable {

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

    /** Returns the source's current position. This is calculated using
     * the orbital elements and obtained via ngat.astrometry.Astrometry.
     * When the position is calculated the current value is cached along
     * with an estimate of the position at a time in the future. If the
     * expiry time has not been exceeded next time this method is called
     * an interpolation is made using the cached and projected values.
     * The following constants are used in this calculation:
     * <dl>
     * <dt>INITIAL_DELTA_TIME
     * <dd> Determine how far in the future to estimate the
     * projected position.
     * <dt>MAX_ANGLE_ERROR
     * <dd> The maximum error/movement permitted for interpolation.
     * </dl>
     **/
    public Position getPosition() { 
	long now = System.currentTimeMillis();
	if (now > exTime) {	    
	    Position position = Astrometry.getPlanetPosition(this);
	    cacheRA   = position.getRA();
	    cacheDec  = position.getDec();
	    cacheTime = now;
	    
	    double delta = 2*INITIAL_DELTA_TIME;
	    double diff  = 0.0;
	    Position offsetPosition = null;
	    do {
		delta = delta/2;
		offsetPosition = Astrometry.getPlanetPosition(this, now + (long)delta);
		diff =
		    Math.abs(cacheRA  - offsetPosition.getRA()) +
		    Math.abs(cacheDec - offsetPosition.getDec());
	    } while (diff > MAX_ANGLE_ERROR);
	    exRA   = offsetPosition.getRA();
	    exDec  = offsetPosition.getDec();
	    exTime = now + delta;
	    return new Position(cacheRA, cacheDec);
	} else {
	    double f = (now - cacheTime) / (exTime - cacheTime);
	    return new Position( cacheRA  + f*(exRA  - cacheRA),
				 cacheDec + f*(exDec - cacheDec) );
	}
    }

    /** Returns the current Non-sidereal tracking in RA.*/
    public abstract double getNsTrackRA();
    
    /** Returns the current Non-sidereal tracking in dec.*/
    public abstract double getNsTrackDec();

    // NP -> P Translator.   
    public SolarSystemSource(NPSolarSystemSource npSource) {
	super(npSource);
    } // end (NP -> P Translator).
    
    // P -> NP Translator.   
    public void stuff(NPSolarSystemSource npSource) {
	super.stuff(npSource);
    } // end (P -> NP Translator).
    
    // P -> NP Translator.  
    public NPDBObject makeNP() {
	NPSolarSystemSource npSource = new NPSolarSystemSource();
	stuff(npSource);
	return npSource;
    } // end (makeNp).
    

} // end class def [SolarSystemSource].
