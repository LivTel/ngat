package ngat.astrometry;

import ngat.phase2.*;
import ngat.phase2.nonpersist.*;

import java.io.*;
import java.util.*;
import java.text.*;
/**
 * Stores the location on the sky, of a target or of a pointing position.
 * Includes methods to translate between coordinate systems.
 * <br>
 * $Id$    
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
    public static  double phi = Math.toRadians(28.76);
    
    /** Longitude of the observatory in rads East of Greenwich.*/
    public static  double ll = Math.toRadians(-17.881);

    /** Convenience constant = sin(phi).*/
    static double sphi = Math.sin(phi);
    
    /** Convenience constant = cos(phi).*/
    static double cphi = Math.cos(phi);

    static NumberFormat nf;

    static {
	nf = NumberFormat.getInstance();
    }

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
    
    /** Sets the current Observatory viewpoint as lat and long.
     * @param lat The Observatory latitude (rads).
     * @param lon The Observatory longitude (rads).*/
    public static void setViewpoint(double lat, double lon) {
	phi = lat;
	ll  = lon;
	sphi = Math.sin(phi);
	cphi = Math.cos(phi);
    }

    /** Calculate the slew time to another Position. 
     * @param other The other position to slew to.
     * @param vAlt The tracking rate in Altitude axis (rad/sec ?).
     * @param vAz The tracking rate in Azimuth axis (rad/sec ?).
     * @return The slew time in msecs.*/
    public double getSlewTime(Position other, double vAlt, double vAz) { 
	double talt = Math.abs(other.getAltitude() - getAltitude())/vAlt;
	double taz  = Math.abs(other.getAzimuth()  - getAzimuth())/vAz;
	return Math.max(talt, taz)*1000.0;
    }
    
    /** Calculate the slew time to another Position. ## snf - uses vaz, valt = 2.0 deg/sec. ##
     * @param other The position to move to.
     * @return The slew time in msecs.*/
    public double getSlewTime(Position other) { 
	return getSlewTime(other, Math.toRadians(2.0), Math.toRadians(2.0));
    }
    
    /** Get the RA of the target in rads. */
    public double getRA() { return RA;}
    
    /** Get the declination of the target in rads.*/ 
    public double getDec() { return dec;}
    
    /** Get the current Hour Angle of the target in rads. */
    public double getHA() { 
	return correct(getLST() - getRA());} 

    /** Get the Hour Angle at a specified LST. 
     * @param epoch The epoch specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Hour Angle in rads.*/
    public double getHA(double lst) { 	
	//System.err.println("POS::GetHA lst = "+lst);
	return correct(lst - getRA());}
    
    /** Get the Hour Angle at a specified epoch. 
     * @param epoch The epoch specified in millis from 1970.
     * @return The Hour Angle in rads.*/
    public double getHA(long epoch) { 
	//System.err.println("POS::GetHA epoch");
	return correct(getLST(epoch) - getRA());}
    
    /** Get the Hour Angle at a specified epoch and longitude. 
     * @param epoch The epoch specified in millis from 1970.
     * @param longitude The site longitude (rads East of Greenwich).
     * @return The Hour Angle in rads.*/
    public double getHA(long epoch, double longitude) { 
	return correct(getLST(epoch, longitude) - getRA());
    }
    
    /** Get the current Altitude of the target in rads. 
     * @return The Altitude of the target at this time in rads.*/
    public double getAltitude() {
	return calculateAltitude(getHA());}
    
    /** Get the Altitude of the target in rads at a specified LST. 
     * @param lst The LST specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Altitude of the target at the specified LST in rads.*/
    public double getAltitude(double lst) { 
	return calculateAltitude(getHA(lst));}
    
    /** Get the Altitude of the target in rads at a specified epoch. 
     * @param epoch The epoch specified in millis from 1970.
     * @return The Altitude of the target at the specified LST in rads.*/
    public double getAltitude(long epoch) { 
	return calculateAltitude(getHA(epoch));}
    
    /** Get the Altitude of the target in rads at a specified epoch and longitude. 
     * @param epoch The epoch specified in millis from 1970.
     * @param longitude The site longitude (rads East of Greenwich).
     * @return The Altitude of the target at the specified LST in rads.*/
    public double getAltitude(long epoch, double longitude) {
	return calculateAltitude(getHA(epoch, longitude));}
       
    /** Get the Altitude of the target in rads at a specified hourAngle (rads). 
     * @param HA The Hour Angle of the calculation.
     */
    public double calculateAltitude(double HA) {
	double arg = sphi*Math.sin(dec)+
	    cphi*Math.cos(dec)*
	    Math.cos(HA);
	return Math.asin(arg);
    }

    /** Get the Altitude of the target in rads at a specified hourAngle (rads). 
     * @param HA The Hour Angle of the calculation.
     */
    public double calculateAltitude(double HA, Site site) {
	double arg = Math.sin(site.getLatitude())*Math.sin(dec)+
	    Math.cos(site.getLatitude())*Math.cos(dec)*
	    Math.cos(HA);
	return Math.asin(arg);
    }
    

    public double getAltitude(long epoch, Site site) {
	return calculateAltitude(getHA(epoch, site.getLongitude()), site);
    }
    
    /** Get the current Azimuth of the target in rads. 
     * @return The Azimuth of the target at this time in rads.*/
    public double getAzimuth() {
	return calculateAzimuth(getHA());}
    
    /** Get the Azimuth at a specified LST. 
     * @param lst The LST specified in rads 0.0 = 0h, 2xPI = 24h.
     * @return The Azimuth of the target at the specified LST in rads.*/
    public double getAzimuth(double lst) {
	return calculateAzimuth(getHA(lst));} 
    
    /** Get the Azimuth at a specified epoch. 
     * @param epoch The epoch specified in millis from 1970.
     * @return The Azimuth of the target at the specified epoch in rads.*/
    public double getAzimuth(long epoch) {
	//System.err.println("POS::GetAz at epoch...");
	double harg = getHA(epoch);
	//System.err.println("POS::GetAz at epoch HA="+harg);
	return calculateAzimuth(harg);}
    
    /** Get the Azimuth at a specified epoch and longitude. 
     * @param epoch The epoch specified in millis from 1970.
     * @param longitude The site longitude (rads East of Greenwich).
     * @return The Azimuth of the target at the specified epoch in rads.*/
    public double getAzimuth(long epoch, double longitude) {
	return calculateAzimuth(getHA(epoch, longitude));}
    
    /** Get the current Azimuth of the target in rads - BASE method
     * called by all other overides.
     * @param HA The Hour Angle for the calculation in  rads. 
     * @return The Azimuth of the target at the given HA in rads.*/
    public double calculateAzimuth(double HA) {
	//System.err.println("POS::CalcAz:HA="+HA);
	double alt = calculateAltitude(HA);
	//System.err.println("POS::CalcAz:Alt="+alt);	
	double az  = Math.acos((Math.sin(getDec())-sphi*Math.sin(alt))/
			       (cphi*Math.cos(alt)));	
	if (HA < PI) 
	    return 2.0*PI - az;
	return az;
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



    // ----------------------------------------------------------------
    // General rising and setting calculations for specified low limit. 
    // ----------------------------------------------------------------

    /** Calculate the Rise Time (above altitude 'low') as an Hour Angle in rads.
     * @param low The horizon limit. */
    public double getRiseTime(double low) { return 2.0*PI - hset(low);}
    
    /** Calculate the Setting time (above altitude 'low') as an Hour Angle in rads.
     * @param low The horizon limit. */
    public double getSetTime(double low) { return hset(low);}
    

    public long getNextSetTime() {
	if (isRisen() && (getHA() < hset()))
	    // Today.
	    return System.currentTimeMillis() + 
		(long)((hset() - getHA())*13750998.70);
	else
	    // Tomorrow.
	    return System.currentTimeMillis() + 86400000L + 
		(long)((hset() - getHA())*13750998.70);
    }
    
    public long getNextSetTime(double low) {
	if (isRisen(low) && (getHA() < hset(low))) 
	    // Today.
	    return System.currentTimeMillis() + 
		(long)((hset(low) - getHA())*13750998.70);
	else 
	    // Tomorrow.
	    return System.currentTimeMillis() + 86400000L + 
		(long)((hset(low) - getHA())*13750998.70);
    }

    /** Calculate the rising time during Today. This is probably
     * only valid for the sun.*/
    public long getRiseTimeToday() { return getRiseTimeToday(System.currentTimeMillis(), 0.0);}
    
    /** Calculate the rising time during Today. This is probably
     * only valid for the sun.*/
    public long getRiseTimeToday(double low) {
	return getRiseTimeToday(System.currentTimeMillis(), low);
    }
    
    /** Calculate the rising time during a day. This is probably
     * only valid for the sun.*/
    public long getRiseTimeToday(long epoch, double low) {
	if (isRising())
	    return epoch + (long)((2.0*Math.PI - hset(low) - getHA(epoch))* 13750998.70);
	else
	    return epoch - (long)((hset(low) + getHA(epoch))* 13750998.70);
    }

    /** Calculate the setting time during Today.This is probably
     * only valid for the sun.*/
    public long getSetTimeToday() { return getSetTimeToday(System.currentTimeMillis(), 0.0);}
    
    /** Calculate the setting time during Today.This is probably
     * only valid for the sun.*/
    public long getSetTimeToday(double low) {
	return getSetTimeToday(System.currentTimeMillis(), low);
    }

    /** Calculate the setting time during a day.This is probably
     * only valid for the sun.*/
    public long getSetTimeToday(long epoch, double low) {
	if (isRising())
	    return  epoch + (long)((2.0*Math.PI + hset(low) - getHA(epoch))* 13750998.70);
	else
	    return epoch + (long)((hset(low) - getHA(epoch))* 13750998.70);
    }

    
    /** Returns true if the target does not rise above low limit at this time. 
     * @param low The horizon limit.*/
    public boolean neverRises(double low) {
	if (phi < 0.0)
	    return (getDec() >= PI/2.0 + (phi - low));
	else
	    return (getDec() <= -PI/2.0 + (phi + low));
    }
    
    /** Returns true if the star rises above low limit at any point.
     * @param low The horizon limit.*/
    public boolean rises(double low) { return !neverRises(low);}
    
    /** Returns true if the target does not set below low limit at this time. 
     * @param low The horizon limit.*/
    public boolean neverSets(double low) { 
	if (phi < 0.0)
	    return (getDec() <= -PI/2.0 - (phi + low));
	else
	    return (getDec() >=  PI/2.0 - (phi - low));
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
	return getAltitude() > low ;}
    
    /** Returns true if the target is currently below the low limit.
     * @param low The horizon limit.*/
    public boolean isSet(double low) { 
	//	double ha = getHA();
	//return (ha > getSetTime(low) && ha < getRiseTime(low));
	return (getAltitude() < low);
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
	return getTransitHeight(phi);
    }
    
    /** Calculate the Transit height for this target from a given latitude.<br>
     * (a negative height means it never rises but is otherwise meaningless).<br>
     * The methods neverRises(), neverSets() should be used to test first.
     * @param latitude The latitude of the site (rads)*/
    public double getTransitHeight(double latitude) {	
	double dec = getDec();
	if (dec < latitude)
	    return (Math.PI/2.0 + dec - latitude);
	else
	    return (Math.PI/2.0 + latitude - dec);
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
    protected double getLST() { 
	return Astrometry.computeLST(System.currentTimeMillis(), ll);
    }
    
    /** Convenience method to give current LST as an angle offset in rads. 
     * @param longitude The longitude East of Greenwich of the site.**/
    protected double getLST(double longitude) { 
	return Astrometry.computeLST(System.currentTimeMillis(), longitude);
    }
    
    /** Convenience method to give current LST as an angle offset in rads. 
     * @param epoch The time (Since 1970) of the computation.**/
    protected double getLST(long epoch) { 
	//System.err.println("POS::GetLST epoch");
	return Astrometry.computeLST(epoch, ll);
    }
    

    /** Convenience method to give current LST as an angle offset in rads. 
     * @param longitude The longitude East of Greenwich of the site.
     * @param epoch The time (Since 1970) of the computation.**/
    protected double getLST(long epoch, double longitude) { 
	return Astrometry.computeLST(epoch, longitude);
    }
    

    /** Convenience method to return currentSystemTime. */
    protected long now() { return System.currentTimeMillis(); }

    /** Returns a String representation of the supplied angle in ddd:mm:ss.ss format.
     * @param angle The angle to format (rads).
     */
    public static String toDMSString(double angle) {
	return formatDMSString(angle, ":");
    }
    
    /** Returns a String representation of the supplied angle in hh:mm:ss.sss format.
     * @param angle The angle to format (rads).
     */
    public static String toHMSString(double angle) {
	return formatHMSString(angle, ":");
    }

    /** Returns a String representation of the supplied angle in ddd.sss format
     * to the supplied precision.
     * @param angle The angle to format (rads).
     * @param precision The number of digits after the decimal point.
     */
    public static String toDegrees(double angle, int precision) {
	nf.setMaximumFractionDigits(precision);
	return nf.format(Math.toDegrees(angle));
    }

    /** Returns this Position formatted as sextet string:-<br>
     *  <ul>
     *  RA: 12h 52m 16.43 Dec: 145 45' 21.25"
     *  </ul>
     */
    public String toString() {
	return "RA: "+toHMSString(getRA())+" Dec: "+toDMSString(getDec());
    }

    /** Parse a String containing a RA in the format hh:mm:ss.sss 
     * @param str The String to parse. 
     * @param delim The tokenizer delim to use.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double parseHMS(String str, String delim) throws ParseException {	
	String arg = str.trim();
	
	int z = 1;
		
	// Strip leading whitespace and look for a sign.
	if (arg.startsWith("+")) {
	    z = 1;
	    if (arg.length() > 1)
		arg = arg.substring(1).trim();
	} else if (arg.startsWith("-")) {
	    z = -1;
	    if (arg.length() > 1)
		arg = arg.substring(1).trim();
	}

	// Split into tokens.
	StringTokenizer tokenizer = new StringTokenizer(arg, delim+" ");
	if (tokenizer.countTokens() < 3)
	    throw new ParseException("Format ["+arg+"] (num tokens ?) - <hh>"+delim+"<mm>"+delim+"<ss.sss>", 0);
	
	try {
	    int hh = Integer.parseInt(tokenizer.nextToken());
	    if (hh < 0 || hh > 23)
		throw new ParseException("Illegal value (0 <= hours <= 23)",0);
	    int mm = Integer.parseInt(tokenizer.nextToken());
	    if (mm < 0 || mm > 59)
		throw new ParseException("Illegal value (0 <= minutes <= 59)",0);
	    double ss = Double.parseDouble(tokenizer.nextToken());
	    if ((int)ss < 0 || (int)ss > 60)
		throw new ParseException("Illegal value (0 <= seconds <= 60)",0);	
	    return (double)z * ((double)hh*3600.0 + (double)mm*60.0 + ss)/13750.98708;   
	} catch (NumberFormatException ne) {
	    throw new ParseException("Illegal format: "+ne,0);
	}
	
    }

    /** Parse a String containing a Dec in the format zdd:mm:ss.sss 
     * @param str The String to parse.
     * @param delim The tokenizer token to use.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double parseDMS(String str, String delim) throws ParseException {
	String arg = str.trim();
	
	int z = 1;
	arg = arg.trim();

	// Strip leading whitespace and look for a sign.
	if (arg.startsWith("+")) {
	    z = 1;
	    if (arg.length() > 1)
		arg = arg.substring(1).trim();
	} else if (arg.startsWith("-")) {
	    z = -1;
	    if (arg.length() > 1)
		arg = arg.substring(1).trim();
	}	

	double v = 0.0;

	// Split into tokens.
	StringTokenizer tokenizer = new StringTokenizer(arg, delim+" ");
	if (tokenizer.countTokens() < 3)
	    throw new ParseException("Format  ["+arg+"] (num tokens ?) - <dd>"+delim+"<mm>"+delim+"<ss.sss>", 0);
	
	try {
	    int dd = Integer.parseInt(tokenizer.nextToken());
	    if (dd < -359 || dd > 359 || dd < -359)
		throw new ParseException("Illegal value (-359 <= degrees <= +/-359)",0);
	    int mm = Integer.parseInt(tokenizer.nextToken());
	    if (mm < 0 || mm > 59)
		throw new ParseException("Illegal value (0 <= minutes <= 59)",0);
	    double ss = Double.parseDouble(tokenizer.nextToken());
	    if ((int)ss < 0 || ((int)ss > 60))
		throw new ParseException("Illegal value (0 <= seconds <= 60)",0);
	    v = (double)z * ((double)dd*3600.0+(double)mm*60.0+ss)/206264.8062;	    
	} catch (NumberFormatException ne) {
	    throw new ParseException("Illegal format: "+ne,0);
	}

	//System.err.println("Position::parseDMS: String ["+arg+"] -> "+v+" ("+Math.toDegrees(v)+")");
	return v;

    }

    /** Parse a String containing a RA in the format hh:mm:ss.sss 
     * @param str The String to parse.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double parseHMS(String str) throws ParseException {
	return parseHMS(str, ":");
    }

    /** Parse a String containing a Dec in the format zdd:mm:ss.sss 
     * @param str The String to parse.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double parseDMS(String str) throws ParseException {
	return parseDMS(str, ":");
    }

    /** Parses a field in generic HMS Format. 
     * <ul>
     *  <li>readHMS() using zxxd xxm xx.xx
     *  <li>parseHMS() with space as seperator
     *  <li>parseHMS() with : as seperator
     * </ul>
     */
    public static double parseGenericHMS(String str) throws ParseException {
	try {
	    // Try hhmmss.
	    return readHMS(str);
	} catch (ParseException px1) {
	    //System.err.println("HMS:: Failed to parse using (xxhhxxmmxxss.ss)");
	    // Try space.
	    try {		
		return parseHMS(str, " ");
	    } catch (ParseException px2) {}
	}
	//System.err.println("HMS:: Failed to parse using (space as seperator)");
	// Try :	
	return parseHMS(str, ":");	
	// Fall through and fail if this last one fails.
    }

    /** Parses a field in generic DMS Format. 
     * <ul>
     *  <li>readDMS() using zxxd xxm xx.xx
     *  <li>parseDMS() with space as seperator
     *  <li>parseDMS() with : as seperator
     * </ul>
     */
    public static double parseGenericDMS(String str) throws ParseException {
	try {
	    // Try ddmmss
	    return readDMS(str);
	} catch (ParseException px1) {
	    //System.err.println("DMS:: Failed to parse using (xxddxxmmxxss.ss)");
	    // try space.
	    try {		
		return parseDMS(str, " ");
	    } catch (ParseException px2) {}
	}
	//System.err.println("DMS:: Failed to parse using (space as seperator)");
	// Fall through and fail if this last one fails.
	return parseDMS(str, ":");	
    }

    /** Parse a String containing a RA in the format xxh xxm ss.sss 
     * @param str The String to parse.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double readHMS(String str) throws ParseException {
	int hindex = str.indexOf("h");
	int mindex = str.indexOf("m");

	if (hindex < 0 || mindex < 0 || mindex < hindex)
	    throw new ParseException("format ! - xxh xxm xx.xxx", 0);

	double value = 0.0;

	try {
	    int hrs  = Integer.parseInt(str.substring(0, hindex).trim());
	    int mins = Integer.parseInt(str.substring(hindex+1, mindex).trim());
	    double secs = Double.parseDouble(str.substring(mindex+1).trim());
	    value = (hrs*3600.0 + mins*60.0 + secs)/240.0;
	} catch (NumberFormatException ne) {
	    throw new ParseException("angle format",0);
	} catch (ClassCastException cce) {
	    throw new ParseException("angle format cast",0);
	}
	return Math.toRadians(value);
    }
    
    /** Parse a String containing a Dec in the format zxxd xxm ss.sss 
     * @param str The String to parse.
     * @exception ParseException if the String is incorrectly formatted.*/
    public static double readDMS(String str) throws ParseException {
	int dindex = str.indexOf("d");
	int mindex = str.indexOf("m");

	if (dindex < 0 || mindex < 0 || mindex < dindex)
	    throw new ParseException("format ! - sxxd xxm xx.xxx", 0);

	double value = 0.0;

	try {
	    int degs    = Integer.parseInt(str.substring(0, dindex).trim());
	    int mins    = Integer.parseInt(str.substring(dindex+1, mindex).trim());
	    double secs = Double.parseDouble(str.substring(mindex+1).trim());
	    if (degs < 0 || (str.indexOf("-") != -1)) {
		  value = -(-degs*3600.0 + mins*60.0 + secs)/3600.0;
	    } else {
		value = (degs*3600.0 + mins*60.0 + secs)/3600.0;
	    }
	} catch (NumberFormatException ne) {
	    throw new ParseException("angle format",0);
	} catch (ClassCastException cce) {
	    throw new ParseException("angle format cast",0);
	}
	return Math.toRadians(value);
    }

    /** Returns a String representation of the supplied angle in xxd xxm xx.xxx format.
     * @param angle The angle to format (rads).
     */
    public static String formatDMSString(double angle, String delim) {
	double f = Math.toDegrees(angle);	
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(2);
	nf.setMinimumFractionDigits(2);
	boolean neg = false;
	String sign = " ";
	if (f < 0.0f) neg = true;
	if (neg) f = -f;

	int deg = (int)f;
	f = (f-(double)deg)*60.0;
	
	int mins = (int)f;
	f = (f-(double)mins)*60.0;
	
	if (neg) sign = "-";	
	return new String(sign+deg+delim+mins+delim+nf.format(f));
    }
       

    /** Returns a String representation of the supplied angle in xxh xxm xx.xxx format.
     * @param angle The angle to format (rads).
     */
    public static String formatHMSString(double angle, String delim) {
	double f = Math.toDegrees(angle)/15;
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(3);
	nf.setMinimumFractionDigits(2);
	boolean neg = false;
	String sign = " ";
	if (f < 0.0f) neg = true;
	if (neg) f = -f;

	int hrs = (int)f;
	f = (f-(double)hrs)*60;

	int mins = (int)f;
	f = (f-(double)mins)*60;

	if (neg) sign = "-";
	return new String(sign+hrs+delim+mins+delim+nf.format(f));
    }


    /** Puts an angle (rads) into the correct (0 -> 2*PI) range.*/
    protected double correct(double angle) { 
	//System.err.println("POS::Correct angle: "+angle);
	double a = angle + 2.0*Math.PI;
	while (a >= 2*Math.PI) {
	    a -= 2.0*Math.PI;
	    //System.err.println("POS::Deduct 2PI");
	}
	return a;
    }
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.4  2007/10/18 11:00:50  snf
/** changed neverRises for southern
/**
/** Revision 1.3  2007/10/18 10:09:09  snf
/** changed impl for isSet() to use getAltitude() rather than weird HA stuff.
/**
/** Revision 1.2  2007/02/15 10:31:13  snf
/** Added getAltitude(epoch, site)
/**
/** Revision 1.1  2006/12/18 12:16:09  snf
/** Initial revision
/**
/** Revision 1.4  2006/12/05 12:48:59  snf
/** tagged.
/**
/** Revision 1.3  2001/03/02 18:27:12  snf
/** *** empty log message ***
/**
/** Revision 1.2  2001/02/23 18:47:47  snf
/** Added computeLST( current time).
/**
/** Revision 1.1  2001/01/09 14:03:54  snf
/** Initial revision
/**
/** Revision 1.3  2000/11/23 13:24:27  snf
/** Added getSlew(Posn, valt, vaz).
/**
/** Revision 1.2  2000/11/23 13:23:10  snf
/** Added some doc comments.
/**
/** Revision 1.1  2000/05/11 14:28:27  snf
/** Initial revision
/** */
