package ngat.phase2;

import ngat.phase2.nonpersist.*;
import ngat.OSS.server.environment.*;

import java.io.*;
import java.util.*;

/**
 * Stores the location on the sky, of an object or of the Telescope's pointing position.
 * Includes methods to translate between coordinate systems. For non-fixed sources the
 * current position is either calculated using elements at this time or retrieved from
 * either an internal or external cache.
 *
 * $Id: Position.java,v 1.1 2000-05-11 14:28:27 snf Exp $    
 *
 */
public class Position implements Serializable {

    // Fixed Source parameters.
    /** The Right Ascension of the object in rads. */
    protected float RA;

    /** The Declination of the object in rads. (-pi <= dec <= pi) */
    protected float dec;
    protected float pmRA;
    protected float pmDec;
    protected float equinox;
    protected float epoch;

    // Orbital elements for non-fixed sources.
    protected float elementEpoch;
    protected float longAscNode;
    protected float argPeri;
    protected float periDist;
    protected float orbitalInc;
    protected float eccentricity;
    protected float meanAnomaly;
    protected float meanDistance;
    protected float dailyMotion;
    protected float longPeri;


    /** ####Latitude of the observatory in rads.#####UK Southish. ~  52.5N */
    float phi = 0.907f;
    // ##TelescopeParameters.getSiteLatitude();

    /** Temporarily save LST locally. */
    private float cacheLST;

    /** Temporarily save HA locally. */
    private float cacheHA;

    /** Temporarily save Altitude locally. */
    private float cacheAltitude;

    /** Temporarily save Azimuth locally. */
    private float cacheAzimuth;

    /** Cache RA locally. */
    private float cacheRA;

    /** Cache Dec locally. */
    private float cacheDec;

    /** records whether this source is fixed (i.e. its RA/Dec do not change rapidly)
     * as would be the case for a Planet etc. */
    protected boolean fixed;
    
    /** The last time at which currently cached values can be relied on.*/
    protected long cacheExpiry;

    static final float PIx2 = (float)Math.PI*2;

    /** contruct a 'fixed' Position object. at specified RA/Dec
     *@param RA - Right Ascension of the object in rads.
     *@param dec - declination of the object in rads. */
    public Position(float RA, float dec) {
	this.RA= RA;
	this.dec = dec;
	pmRA =0.0f;
	pmDec = 0.0f;
	equinox = 0.0f;
	epoch = 0.0f;
	fixed = true;
	cacheExpiry = 0L; // first time try to access RA/dec will need to calc.
    }

    /** construct a 'non-fixed' Position object using supplied elements.
     * @param argPeri argument of the perihelion
     * @param longAscNode position of ascending node
     * @param orbitalInc inclination of the orbit
     * @param dailyMotion rate of movement
     * @param eccentricity orbital eccentricity
     * @param elementEpoch epoch of the elements 
     * ## calls SLA_PLANTE(date, elong, phi, jform=1, epoch, orbinc, 
     * ##                  anode, perih, aorq, eccen, aorl, dm, ra, dec, r, jstat)
     * ## CHECK THESE ELEMENTS !!! ##*/
    public Position(float argPeri, float longAscNode, float orbitalInc,
		    float dailyMotion, float eccentricity, float elementEpoch,float meanDistance) {
	this.argPeri = argPeri;
	this.longAscNode = longAscNode;
	this.orbitalInc = orbitalInc;
	this.dailyMotion = dailyMotion;
	this.eccentricity = eccentricity;
	this.elementEpoch  = elementEpoch;
	this.meanDistance = meanDistance;
	fixed = false;
    }

    public Position(NPPosition npPosition) {
	if (npPosition.isFixed()) {
	    this.RA = npPosition.getRA();
	    this.dec = npPosition.getDec();
	    fixed = true;
	} else {
	    this.argPeri = npPosition.getArgPeri();
	    this.longAscNode = npPosition.getLongAscNode();
	    this.orbitalInc = npPosition.getOrbitalInc();
	    this.dailyMotion = npPosition.getDailyMotion();
	    this.eccentricity = npPosition.getEccentricity();
	    this.elementEpoch  = npPosition.getElementEpoch();
	    this.meanDistance = npPosition.getMeanDistance();
	    fixed = false;
	}
    }

    public NPPosition makeNP() {
	NPPosition npPosition = null;new NPPosition();
	if (fixed) {
	    npPosition = new NPPosition(RA,dec);
	} else {
	    npPosition = new NPPosition(argPeri, longAscNode, orbitalInc,
		    dailyMotion, eccentricity, elementEpoch, meanDistance);
	}
	return npPosition;
    }

    /** Returns true if the source is fixed. */
    public boolean isFixed() { return fixed; }

    public float getElementEpoch() { return elementEpoch;}
    public float getLongAscNode() { return longAscNode;}
    public float getArgPeri() { return argPeri;}
    public float getPeriDist() { return periDist;}
    public float getOrbitalInc() { return orbitalInc;}
    public float getEccentricity() { return eccentricity;}
    public float getMeanAnomaly() { return meanAnomaly;}
    public float getMeanDistance() { return meanDistance;}
    public float getDailyMotion() { return dailyMotion;}
    public float getLongPeri() { return longPeri;}




    /** Efficiency method. Carries out all the Position element calculations, setting
     * temporary internal variables to be returned by getCurrentXXElem() methods.
     * These methods will have to be called within a shortish period of the call to
     * calculatePosition() as they will become stale */
    public void calculatePosition(float lst) {
	cacheLST = lst;
	cacheHA = getHA(cacheLST);
	cacheAltitude = getAltitude(cacheLST);
	cacheAzimuth = getAzimuth(cacheLST);
	// cacheRA = ..
	// cahceDec = ...
    }

    /** Get the RA of the object in rads. For non-fixed objects the cached value is
     * examined first. If this has expired a new value is calculated from elements.*/
    public float getRA() {
	if (fixed) return RA;
	if (now() > cacheExpiry) {
	    return calculateRA();
	}
	return cacheRA;
    }

    /** Get the declination of the object in rads. For non-fixed objects the cached value is
     * examined first. If this has expired a new value is calculated from elements.*/ 
    public float getDec() { 
	if (fixed) return dec; 
	if (now() > cacheExpiry) {
	    return calculateDec();
	}
	return cacheDec;
    }

    /** Calculates the RA of this Position using orbital elements at this time.*/
    public float calculateRA() {
	// cacheExpiry = now()+.... depends on rate of change of posn and desired accuracy
	return 0.0f;
    } //#### TEMP ####

    /** Calculates the Dec of this object using orbital elements at this time. */
    public float calculateDec() { 
	// cacheExpiry = now()+.... depends on rate of change of posn and desired accuracy
	return 0.0f;
    } //#### TEMP ####

    /** Reference to external cache. */
    private float getExternallyCachedRA() { return cacheRA; } // #### TEMP

    /** Reference to external cache. */
    private float getExternallyCachedDec() { return cacheDec; } // #### TEMP

    /** Get the current Hour Angle of the object in rads. */
    public float getHA() { return (float)(((PIx2+getLST()-RA)*100000)%628318)/100000.0f;} 

    /** Get the Hour Angle at a specified LST. */
    public float getHA(float lst) { return (float)(((PIx2+lst-RA)*100000)%628318)/100000.0f;}

    /** Get the current Altitude of the object in rads. */
    public float getAltitude() { return (float)(Math.asin(Math.sin(phi)*Math.sin(dec)+
						  Math.cos(phi)*Math.cos(dec)*Math.cos(getHA())));}
    /** Get the Altitude at a specified LST. */
    public float getAltitude(float lst) { return (float)(Math.asin(Math.sin(phi)*Math.sin(dec)+
						  Math.cos(phi)*Math.cos(dec)*Math.cos(getHA(lst))));}

    /** Get the current Azimuth of the object in rads. */
    public float getAzimuth() {
	float az = (float)Math.acos((Math.sin(dec)-Math.sin(phi)*Math.sin(getAltitude()))/
				    (Math.cos(phi)*Math.cos(getAltitude())));
	if (Math.sin(getHA()) > 0.0f) az = PIx2 - az;
	return az;
    }

    /** Get the Azimuth at a specified LST. */
    public float getAzimuth(float lst) {
	float az = (float)Math.acos((Math.sin(dec)-Math.sin(phi)*Math.sin(getAltitude(lst)))/
				    (Math.cos(phi)*Math.cos(getAltitude(lst))));
	if (Math.sin(getHA(lst)) > 0.0f) az = PIx2 - az;
	return az;
    }

    /** Calculate the Rise Time (above altitude 'low') as an Hour Angle in rads. */
    public float getRiseTime(float low) { return (((-hset(low)+RA+PIx2)*100000)%628318)/100000.0f;}

    /** Calculate the Setting time (above altitude 'low') as an Hour Angle in rads. */
    public float getSetTime(float low) { return (((hset(low)+RA+PIx2)*100000)%628318)/100000.0f;}

    /** Calculate the Transit height for this source. (a negative height means it never rises)*/
    public float getTransitHeight() {
	float zt = 0.0f;
	if (0.0f <= dec && dec <= phi) zt = phi - dec;
	if (dec > phi) zt = dec - phi;
	if ((phi - (float)Math.PI/2.0f) <= dec && dec < 0.0f) zt =  phi - dec;
	if (dec < (phi - (float)Math.PI/2.0f)) return -1.0f; // Never Rises.
	return (float)Math.PI/2.0f - zt;
    }

    /** Calculate the Rising Azimuth point. */
    public float getRisingAzimuth(float low) { return (float)(Math.acos(Math.sin(dec)-Math.sin(phi)*Math.sin(low))/(Math.cos(phi)*Math.cos(low)));}

    /** Calculate the Setting Azimuth.*/
    public float getSettingAzimuth(float low) { return -getRisingAzimuth(low);}

    public float getCurrentHA() { return cacheHA ;}
    public float getCurrentAltitude() { return cacheAltitude ;}
    public float getCurrentAzimuth() { return cacheAzimuth ;}
    
    /** Returns intermediate value in Rising/setting calculation. */
    private float hset(float low) { return (float)Math.acos(((float)Math.cos(1.57079f-low)-Math.sin(phi)*Math.sin(dec))/(Math.cos(phi)*Math.cos(dec)));}
    
    /** Convenience method to give current LST as an angle offset in rads. */
    private float getLST() { return Scheduling.getLST(); }

    /** Convenience method to return currentSystemTime. */
    private long now() { return Scheduling.getCurrentTime(); }

    public static String toDMSString(float angle) {
	float f = (float)Math.toDegrees((double)angle);
	boolean neg = false;
	String sign = " ";
	if (f < 0.0f) neg = true;
	if (neg) f = -f;
	int deg = (int)f;
	f = (f-deg)*60;
	int mins = (int)f;
	f = (f-mins)*60;
	int secs = (int)f;
	f = (f-secs)*100;
	int frac = (int)Math.rint(f);
	if (neg) sign = "-";
	return new String(sign+deg+"  "+mins+"' "+secs+"\""+"."+frac);
    }
    
    public static String toHMSString(float angle) {
	float f = (float)Math.toDegrees((double)angle)/15;
	boolean neg = false;
	String sign = " ";
	if (f < 0.0f) neg = true;
	if (neg) f = -f;
	int hrs = (int)f;
	f = (f-hrs)*60;
	int mins = (int)f;
	f = (f-mins)*60;
	int secs = (int)f;
	f = (f-secs)*100;
	int frac = (int)Math.rint(f);
	if (neg) sign = "-";
	return new String(sign+hrs+"h "+mins+"m "+secs+"."+frac);
    }
}

/** $Log: not supported by cvs2svn $ */
