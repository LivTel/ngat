package ngat.phase2;

import ngat.phase2.util.*;
import ngat.phase2.nonpersist.*;
import java.io.*;

/**
 * Stores the location on the sky, of a target or of a pointing position.
 * Includes methods to translate between coordinate systems.
 * <br>
 * $Id: Position.java,v 1.3 2000-11-23 13:24:27 snf Exp $    
 *
 */
public class Position implements Serializable {

    /** The Right Ascension of the target in rads. (0 <= RA <= PI) */
    protected double RA;
    
    /** The Declination of the target in rads. (-PI <= dec <= PI) */
    protected double dec;
    
    /** Convenience constant = 3.14159...*/
    public static final double PI = Math.PI;

    /** Convenience constant = 2xPI.*/
    public static final double PIx2 = Math.PI*2;

    /** Latitude of the observatory in rads.*/
    public static final double phi = Math.toRadians(28.76);
    
    /** Convenience constant = sin(phi).*/
    public static final double sphi = Math.sin(phi);
    
    /** Convenience constant = cos(phi).*/
    public static final double cphi = Math.cos(phi);

    // ##TelescopeParameters.getSiteLatitude();
  
    /** Contruct a fixed target Position at specified RA and Dec
     *@param RA  - Right Ascension of the target in rads.
     *@param dec - declination of the target in rads. */
    public Position(double RA, double dec) {
	this.RA= RA;
	this.dec = dec;	
    }

    /** Contruct a fixed target Position at specified RA = 0.0 and Dec = 0.0.*/
    public Position() {
	this(0.0, 0.0);
    }
   
    /** Translate an NPPosition into a Position.*/
    public Position(NPPosition npPosition) {
	this.RA  = npPosition.getRA();
	this.dec = npPosition.getDec();
    }
    
    /** Translate this Position into an NPPOsition.*/
    public NPPosition makeNP() {
	NPPosition npPosition = new NPPosition(RA, dec);	
	return npPosition;
    }
    
    /** Calculate the slew time to another Position. ## TBD snf -just returns 15 secs ##
     * @return The slew time in msecs.*/
    public double getSlewTime(Position other, double vAlt, double vAz) { return 15000.0;}
    
    /** Calculate the slew time to another Position. ## TBD snf -just returns 15 secs ##
     * @param other The position to move to.
     * @param vAlt The altitude slew rate rad/sec.
     * @param vAz The azimuth slew rate rad/sec.
     * @return The slew time in msecs.*/
    public double getSlewTime(Position other) { return 15000.0;}
       
    /** Get the RA of the target in rads. */
    public double getRA() { return RA;}
    
    /** Get the declination of the target in rads.*/ 
    public double getDec() { return dec;}
    
    /** Get the current Hour Angle of the target in rads. */
    public double getHA() { return correct(getLST() - getRA());} 

    /** Get the Hour Angle at a specified LST. ## TBD needs fixing for getRA(lst) 
     * @param lst The LST specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Hour Angle in rads.*/
    public double getHA(double lst) { return correct(lst - getRA());}
    
    /** Get the current Altitude of the target in rads. 
     * @return The Altitude of the target at this time in rads.*/
    public double getAltitude() { return (Math.asin(sphi*Math.sin(dec)+
						    cphi*Math.cos(dec)*Math.cos(getHA())));}
    
    /** Get the Altitude of the target in rads at a specified LST. 
     * @param lst The LST specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Altitude of the target at the specified LST in rads.*/
    public double getAltitude(double lst) { return Math.asin(sphi*Math.sin(dec)+
							     cphi*Math.cos(dec)*
							     Math.cos(getHA(lst)));}
    
    /** Get the current Azimuth of the target in rads. 
     * @return The Azimuth of the target at this time in rads.*/
    public double getAzimuth() {
	double alt = getAltitude();
	double az = Math.acos((Math.sin(getDec())-sphi*Math.sin(alt))/
			      (cphi*Math.cos(alt)));	
	if (getHA() < PI) 
	    return 2.0*PI - az;
	return az;
    }
    
    /** Get the Azimuth at a specified LST. 
     * @param lst The LST specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Azimuth of the target at the specified LST in rads.*/
    public double getAzimuth(double lst) {
	double alt = getAltitude(lst);
	double az = Math.acos((Math.sin(getDec())-Math.sin(phi)*Math.sin(alt))/
			      (Math.cos(phi)*Math.cos(alt)));
	return correct(az);
    }
    
    /** Returns the angle in rads between this Position and the specified other Position.
     * Uses vector dot product in spherical polar coordinates.
     * @param other The position to which the angular seperation is required.*/
    public double getAngularDistance(Position other) {
	double dec = getDec();
	return Math.acos(Math.cos(dec)*Math.cos(other.getDec())*Math.cos(getRA() - other.getRA())
			 + Math.sin(dec)*Math.sin(other.getDec()));
    }

    // Overrides of rising setting calculations for Horizon.
    // ----------------------------------------------------

    /** Calculates Rise time (above Horizon) as an Hour angle in rads. */
    public double getRiseTime() { return getRiseTime(0.0);}

    /**  Calculate the Setting time (above Horizon) as an Hour Angle in rads. */
    public double getSetTime() { return getSetTime(0.0);}
 
    /** Returns true if the target does not rise above Horizon at this time. */
    public boolean neverRises() { return neverRises(0.0);}
 
    /** Returns true if the target does not set below Horizon at this time. */
    public boolean neverSets() { return neverSets(0.0);}
        
    /** Returns true if the target is above the Horizon at this time.*/
    public boolean isRisen() { return isRisen(0.0);}

    /** Returns true if the target is below the Horizon at this time.*/
    public boolean isSet() { return isSet(0.0);}   
    
    /** Returns intermediate value in Rising setting calculations for Horizon. */
    protected double hset() { return hset(0.0);}
 
    /** Calculates the time (in rads) the star will remain above the Horizon.*/
    public double getUpTime() { return getUpTime(0.0);} 
 
    /** Calculates the time (in msecs) the star will remain above the Horizon.*/
    public double getUpTimeMillis() { return getUpTimeMillis(0.0);}

    /** Calculates the time (in rads) the star will remain below the Horizon.*/
    public double getDownTime() { return getDownTime(0.0);} 
 
    /** Calculates the time (in msecs) the star will remain below the Horizon.*/
    public double getDownTimeMillis() { return getDownTimeMillis(0.0);}




    // General rising and setting calculations for specified low limit. 
    // ---------------------------------------------------------------
    /** Calculate the Rise Time (above altitude 'low') as an Hour Angle in rads.
     * @param low The horizon limit. */
    public double getRiseTime(double low) { return 2.0*PI - hset(low);}
    
    /** Calculate the Setting time (above altitude 'low') as an Hour Angle in rads.
     * @param low The horizon limit. */
    public double getSetTime(double low) { return hset(low);}
    
    /** Returns true if the target does not rise above low limit at this time. 
     * @param low The horizon limit.*/
    public boolean neverRises(double low) {
	return (getDec() <= -PI/2.0 + (phi + low));
    }
    
    /** Returns true if the star rises above low limit at any point.
     * @param low The horizon limit.*/
    public boolean rises(double low) { return !neverRises(low);}
    
    /** Returns true if the target does not set below low limit at this time. 
     * @param low The horizon limit.*/
    public boolean neverSets(double low) { 
	return (getDec() >= PI/2.0 - (phi - low));
    }

    /** Returns true if the star sets below low limit at any point.
     * @param low The horizon limit.*/
    public boolean sets(double low) { return !neverSets(low);}

    /** Returns true if the target is currently rising. */
    public boolean isRising() { return getHA() > PI; }

    /** returns true if the target is currently setting.*/
    public boolean isSetting() { return getHA() < PI;}

    /** Returns true if the target is currently above the low limit.
     * @param low The horizon limit.*/
    public boolean isRisen(double low) { 
	double ha = getHA();
	return (ha > getRiseTime(low) || ha < getSetTime(low));}
    
    /** Returns true if the target is currently below the low limit.
     * @param low The horizon limit.*/
    public boolean isSet(double low) { 
	double ha = getHA();
	return (ha >  getSetTime(low) && ha < getRiseTime(low));
    }

    /** Calculates the time (in rads) the star will remain above the low limit.
     * @param low The horizon limit.*/
    public double getUpTime(double low) { 
	if (isRisen(low))
	    return correct( 2.0*PI + hset(low) - getHA() );
	else 
	    return 0.0;
    }
       
    /** Calculates the time (in msecs) the star will remain above the low limit.
     * @param low The horizon limit.*/
    public double getUpTimeMillis(double low) {return getUpTime(low)*13750987.08;}
    

    /** Calculates the time (in rads) the star will remain below the low limit.
     * @param low The horizon limit.*/
    public double getDownTime(double low) {
	if (isSet(low))
	    return correct( 2.0*PI - hset(low) - getHA() );
	else
	    return 0.0;
    }

    /** Calculates the time (in msecs) the star will remain below the low limit.
     * @param low The horizon limit.*/
    public double getDownTimeMillis(double low) {return getDownTime(low)*13750987.08;}
    
    

    /** Calculate the Transit height for this target.<br>
     * (a negative height means it never rises but is otherwise meaningless).<br>
     * The methods neverRises(), neverSets() should be used to test first.*/
    public double getTransitHeight() {
	double zt = 0.0;
	double dec = getDec();
	if (0.0 <= dec && dec <= phi) zt = phi - dec;
	if (dec > phi) zt = dec - phi;
	if ((phi - Math.PI/2.0) <= dec && dec < 0.0) zt =  phi - dec;
	if (dec < (phi - Math.PI/2.0)) return -1.0; // Never Rises.
	return Math.PI/2.0 - zt;
    }
    
    /** Calculate the Rising Azimuth point. ##TBD snf CHECK THESE## 
     * @param low The horizon limit.*/
    public double getRisingAzimuth(double low) { 
	return (Math.acos(Math.sin(getDec())-sphi*Math.sin(low))/(cphi*Math.cos(low)));
    }
    
    /** Calculate the Setting Azimuth. ##TBD snf CHECK THESE## */
    public double getSettingAzimuth(double low) { return 2.0*Math.PI-getRisingAzimuth(low);}
    
    /** Returns intermediate value in Rising/setting calculation for specified low limit.  
     * @param low The horizon limit.*/
    private double hset(double low) { 
	return Math.acos((Math.sin(low) - Math.sin(getDec())*sphi)/(Math.cos(getDec())*cphi));
    }  
    
    /** Convenience method to give current LST as an angle offset in rads. */
    protected double getLST() { return Scheduling.getLST(); }
    
    /** Convenience method to return currentSystemTime. */
    protected long now() { return Scheduling.getCurrentTime(); }
    
    public static String toDMSString(double angle) {
	double f = Math.toDegrees(angle);
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
    
    public static String toHMSString(double angle) {
	double f = Math.toDegrees(angle)/15;
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

    /** Puts an angle (rads) into the correct (0 -> 2*PI) range.*/
    protected double correct(double angle) { 
	double a = angle + 2.0*PI;
	while (a >= 2*PI) a -= 2.0*PI;
	return a;
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.2  2000/11/23 13:23:10  snf
/** Added some doc comments.
/**
/** Revision 1.1  2000/05/11 14:28:27  snf
/** Initial revision
/** */
