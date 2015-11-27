package ngat.astrometry;

import java.util.*;

public class JSlalib {
    
    /** The GMT timezone.*/
    static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    static final TimeZone UTC = new SimpleTimeZone(0, "UTC");

    // This information is updated weekly

    /*
**********************************************************************   
*                                                                    *   
*                   I E R S   B U L L E T I N - A                    *   
*                                                                    *   
*           Rapid Service/Prediction of Earth Orientation            *   
**********************************************************************   
4 September 2014                                    Vol. XXVII No. 036   
______________________________________________________________________   
GENERAL INFORMATION:                                                     
To receive this information electronically, contact:                  
ser7@maia.usno.navy.mil or use                                     
<http://maia.usno.navy.mil/docrequest.html>                        
MJD = Julian Date - 2 400 000.5 days                                  
UT2-UT1 = 0.022 sin(2*pi*T) - 0.012 cos(2*pi*T)                       
- 0.006 sin(4*pi*T) + 0.007 cos(4*pi*T)   
where pi = 3.14159265... and T is the date in Besselian years.     
TT = TAI + 32.184 seconds                                             
DUT1= (UT1-UTC) transmitted with time signals                         
=  -0.3 seconds beginning 8 May 2014 at 0000 UTC                  
=  -0.4 seconds beginning 25 Sep 2014 at 0000 UTC   
=  -0.5 seconds beginning 25 Dec 2014 at 0000 UTC
=  -0.6 seconds beginning 19 Mar 2015 at 0000 UTC  
=  +0.2 seconds beginning 17 Sep 2015 at 0000 UTC
=  +0.1 seconds beginning 26 Nov 2015 at 0000 UTC
Beginning 1 July 2012:                                                
TAI-UTC = 35.000 000 seconds                                       
*  A positive leap second WILL be introduced in UTC on 30 June 2015.  *
Beginning 1 July 2015:
TAI-UTC = 36.000 000 seconds 
***********************************************************************
*/

    /** TAI-UTC correction = no of leapseconds since datum.*/
    static final double TAIUTC = 36; 

    /** UT1-UTC correction from USNO Bulletin-A - this is never more than +-1.0 sec.*/
    static final double  UT1UTC = +0.1;          
  
    /** Calls slaPlante with the specified parameters.
     * @param date The date of observation of planet as MJD.
     * @param elong Longitude of the observatory (East = +ve).
     * @param phi Latitude of the observatory.
     * @param jform A controlling parameter to decide the element set.
     * @param epoch The epoch of the elements as MJD.
     * @param orbinc Inclination of the orbit (rads).
     * @param anode Longitude of ascending node (rads).
     * @param perih Longitude or argument of perihelion (rads).
     * @param aorq Mean distance or perihelion distance.
     * @param e Eccentricity of the orbit.
     * @param aorl Mean anomaly or mean longitude (rads).
     * @param dm Daily motion (rads/day).
     * @return The topocentric apparent position of the planet derived from the elements.
     */
    private static native Position callSlaPlante(double date, double elong, double phi, int jform,
						 double epoch, double orbinc, double anode, 
						 double perih, double aorq, double e, double aorl,
						 double dm);
    
    /** Calls slaPlanet with specified parameters.
     * @param date The date of the observation as MJD.
     * @param iplanet The planet code (1 = MERC, 9 = PLUTO).
     */
    private static native PositionVelocity callSlaPlanet(double date, int iplanet);

    private static native PositionVelocity callSlaPlanel(double date, int jform,  double epoch,  double orbinc, double anode, 
							 double perih, double aorq, double e,  double aorl, double dm);


    /** Calls slaMap with specified parameters.
     * @param ram Mean RA of epoch (rads).
     * @param decm Mean Dec of epoch (rads).
     * @param pmra PM in RA.
     * @param pmdec PM in Dec.
     * @param para Parallax.
     * @param rv RadialVelocity.
     * @param eq Equinox and epoch.
     * @param mjd MJD date for calculation of apparent coords.
     */
    private static native Position callSlaMap(double ram, double decm, double pmra, double pmdec,
					      double para, double rv, double eq, double mjd); 

    /** Calls slaDtp2s with specified parameters.
     * @param xi Tangent plane X offset (rads).
     * @param eta Tangent plane Y offset (rads).
     * @param raz RA off centre of field (rads).
     * @param decz Dec of centre of field (rads).
     */
    private static native Position callSlaDtp2s(double xi, double eta, double raz, double decz);

    /** Calls slaCldj with the specified parameters.
     * @param year The year part of the date.
     * @param month The month part of the date.
     * @param date The day-in-month part of the date.
     * @return The MJD of the specified date.
     */
    private static native double callSlaCldj(int year, int month, int date);
    
    /** Calls slaDbear with specified parameters.
     * @param az1 Azimuth of source.
     * @param alt1 Elevation of source.
     * @param az2 Azimuth of target.
     * @param alt2 Elevation of target.
     */
    private static native double callSlaDbear(double az1, double alt1, double az2, double alt2);
    
    /** Calls slaGmsta with the specified parameters.
     * @param mjd The date as MJD.
     * @param ut1 The time in day UT1 as fraction in [0,1].
     * @return The GMST of the time.
     */
    private static native double callSlaGmsta(double mjd, double ut1);
    
    /** Calls slaDtt with the specified parameters.
     * @param dju The date as MJD.
     * @return The difference between ET and UT in secs.
     */
    private static native double callSlaDtt(double dju);

    /** Calls slaDmoon with the specified MJD date.
     * @param mjd The date as MJD.
     * @return The lunar position.
     */
    private static native PositionVelocity callSlaDmoon(double mjd);

    /** Calls slaEqeqex with the spcified MJD Date.
     * @param mjd The date as MJD.
     */
    private static native double callSlaEqeqx(double mjd);

    /** Calls slaPvobs with specified parameters.
     * @param latt   Latitude (rads).
     * @param height Height (geodetic altitude).
     * @param last   Local Apparent Sidereal Time.
     */
    private static native PositionVelocity callSlaPvobs(double lat, double height, double last);

    /** Calls slaEvp with specified parameters.
     * @param mjd The date of observation (mjd).
     * @retuan The Solar position relative to Earth.
     */
    private static native PositionVelocity callSlaEvp(double mjd);
    
    /** Calls slaRdplan with specified parameters.
     * @param mjd     The date of observation (mjd) in ET not UT.
     * @param iplanet The planet code.
     * @param elong   Longitude East +ve.
     * @param phi     Latitiude.
     */
    private static native Position callSlaRdplan(double mjd, int iplanet, double elong, double phi);

    /**
     * @param time The current epoch (time) as millis sicne 1970.
     * @param elong East longitude of observer (West is negative) (rads).
     * @param phi Latitude of observer (rads).
     * @param epoch Epoch of the elements as MJDs.
     * @param orbinc Inclination of the orbit from ecliptic (rads).
     * @param anode Longitude of ascending node (rads).
     * @param perih Longitude of perihelion (rads).
     * @param aorq Mean distance (au).
     * @param e Eccentricity of orbit.
     * @param aorl Mean longitude (rads).
     * @param dm Daily motion (rad/day).
     * @return The current (at time) ngat.astrometry.Position of the Planet.
     */
    public static Position getPlanetPosition(long   time,  double elong,  double phi, 
					     double epoch, double orbinc, double anode, 
					     double perih, double aorq,   double e,
					     double aorl,  double dm) {
      
	return callSlaPlante(getMJD(time)+getUT(time)+getEtUt2(time)/86400.0, elong, phi, 1, epoch, orbinc, anode, perih,
			     aorq, e, aorl, dm);
    }
       
    /** Wrapper method. Returns the Position of Planet specified.*/
    public static Position getPlanetCoordinates(long time, int iplanet) {

	// Changed method 13-jan-05 due to not taking account of obs location
	
	// OLD STUFF using SlaPLANET
	
	//return callSlaPlanet(getMJD(time)+getUT(time), iplanet);

	// END OLD STUFF

	// New stuff usinf Sla_RDPLAN

	double mjd = getMJD(time);	
	double ut1 =  getUT(time);
	   
	double mjdut = mjd+ut1;

	//double etut  = callSlaDtt(mjdut);
	double etut  = getEtUt2(time);
	double mjdet = mjdut + (etut/86400.0);

	//System.err.println("J:Calling rdplan with mjdut = "+mjdut+" mjdet = "+mjdet);
	
	double elong = Position.ll;
	double phi   = Position.phi;

	Position posn = callSlaRdplan(mjdet, iplanet, elong, phi);
	//System.err.println("J:Called rdplan");

	return posn;

	// END NEW STUFF

    }


    /**
     * @param time The current epoch (time) as millis sicne 1970.
     * @param elong East longitude of observer (West is negative) (rads).
     * @param phi Latitude of observer (rads).
     * @param epoch Epoch of the elements as MJDs.
     * @param orbinc Inclination of the orbit from ecliptic (rads).
     * @param anode Longitude of ascending node (rads).
     * @param perih Argument of perihelion (rads).
     * @param aorq perihelion distance (au). 
     * @param e Eccentricity of orbit.
     * @param aorl Mean longitude (rads).NOTUSED
     * @param dm Daily motion (rad/day).NOTUSED
     * @return The current (at time) ngat.astrometry.Position of the Planet.
     */
    public static Position getCometPosition(long   time,  double elong,  double phi, 
					    double epoch, double orbinc, double anode, 
					    double perih, double aorq,   double e) {
	// 	System.err.println("JSlalib:: GetCometPosition: I will be using these comet parameters in degrees:"+
	// 			   "\nfor obs time:  "+time+
	// 			   "\nelong: "+Position.toDegrees(elong,3)+" / "+elong+
	// 			   "\nphi:   "+Position.toDegrees(phi,3)+" / "+phi+
	// 			   "\nepoch: "+epoch+
	// 			   "\norbinc:"+Position.toDegrees(orbinc,3)+" / "+orbinc+
	// 			   "\nanode: "+Position.toDegrees(anode,3)+" / "+anode+
	// 			   "\nperih: "+Position.toDegrees(perih,3)+" / "+perih+
	// 			   "\naorq:  "+aorq+" AU"+
	// 			   "\necc:   "+e);
	
	return callSlaPlante(getMJD(time)+getUT(time)+getEtUt2(time)/86400.0, elong, phi, 3, epoch, orbinc, anode, perih,
			     aorq, e, 0.0, 0.0);
    }


    /**
     * @param time The current epoch (time) as millis sicne 1970.
     * @param elong East longitude of observer (West is negative) (rads).
     * @param phi Latitude of observer (rads).
     * @param epoch Epoch of the elements as MJDs.
     * @param orbinc Inclination of the orbit from ecliptic (rads).
     * @param anode Longitude of ascending node (rads).
     * @param perih Argument of perihelion (rads).
     * @param aorq perihelion distance (au). 
     * @param e Eccentricity of orbit.
     * @param aorl Mean longitude (rads).
     * @param dm Daily motion (rad/day).NOTUSED
     * @return The current (at time) ngat.astrometry.Position of the Planet.
     */
    public static Position getAsteroidPosition(long   time,  double elong,  double phi, 
					       double epoch, double orbinc, double anode, 
					       double perih, double aorq,   double e, 
					       double aorl) {

	double mjdut = getMJD(time)+getUT(time);
	//double etut  = callSlaDtt(mjdut);
	double etut  = getEtUt2(time);
        double mjdet = mjdut + (etut/86400.0);

	return callSlaPlante(mjdet, elong, phi, 2, epoch, orbinc, anode, perih,
			     aorq, e, aorl, 0.0);
    }


    /** This is an attempt to get apparent geocentric coordinates back.*/
    public static Position getAsteroidPosition2(long   time,  double elong,  double phi,
						double epoch, double orbinc, double anode,
						double perih, double aorq,   double e,
						double aorl) {
	
	// calc time as tt
	double mjdut = getMJD(time)+getUT(time);
        //double etut  = callSlaDtt(mjdut);
	double etut  = getEtUt2(time);
        double mjdet = mjdut + (etut/86400.0);

	// use planel rather than plante (get heliocentric coords)
	PositionVelocity planet = callSlaPlanel(mjdet, 2, epoch, orbinc, anode, perih,
						aorq, e, aorl, 0.0);
	
	PositionVelocity sun = callSlaEvp(mjdet);

	// correct to geocentric
	double x = planet.x + sun.x;
	double y = planet.y + sun.y;
	double z = planet.z + sun.z;
	
	double r = Math.sqrt(x*x + y*y);
	double n = Math.sqrt(x*x+y*y+z*z);

	double ra = Math.atan(y/x);
	if (x < 0.0)
	    ra += Math.PI;
	if (x > 0.0 && y < 0.0)
	    ra += 2.0*Math.PI;
	double dec = Math.atan(z/r);

	//System.err.println("Target dist: "+r+" Sun dist: "+Math.sqrt(sun.x*sun.x+sun.y*sun.y+sun.z*sun.z));

	return new Position(ra, dec);


    }

    /** This is an attempt to get apparent topocentric coordinates back.*/
    public static Position getAsteroidPosition3(long   time,  double elong,  double phi,
						double epoch, double orbinc, double anode,
						double perih, double aorq,   double e,
						double aorl) {
	// calc time as tt
	double mjdut = getMJD(time)+getUT(time);
	//double etut  = callSlaDtt(mjdut);
	double etut  = getEtUt2(time);
	double mjdet = mjdut + (etut/86400.0);
	
	// use planel rather than plante (get heliocentric coords)
	PositionVelocity planet = callSlaPlanel(mjdet, 2, epoch, orbinc, anode, perih,
						aorq, e, aorl, 0.0);
	
	// sun from earth
	PositionVelocity sun = callSlaEvp(mjdet);

	// this is for the LT hight will vary
	PositionVelocity obs = getObservatoryCoordinates(phi, elong, 2400, time);

	// correct to topocentric
	double x = planet.x + sun.x - obs.x;
	double y = planet.y + sun.y - obs.y;
	double z = planet.z + sun.z - obs.z;
	
	double r = Math.sqrt(x*x + y*y);
	double n = Math.sqrt(x*x+y*y+z*z);

	double ra = Math.atan(y/x);
	if (x < 0.0)
	    ra += Math.PI;
	if (x > 0.0 && y < 0.0)
	    ra += 2.0*Math.PI;
	double dec = Math.atan(z/r);

	return new Position(ra, dec);

    }


    /** Wrapper method. Calculates the ET-UT difference for epoch.
     * @param time The time (millis since 1970).
     * return ET-UT (secs).
     */
    public static double getEtUt(long time) {
	double mjd = getMJD(time);	
	double ut1 =  getUT(time);
	return callSlaDtt(mjd+ut1);
    }

    /** Local method for determining ET-UT using coded values - avoids updating slalib each LS.*/
    public static double getEtUt2(long time) {
	// we are only using this for "now" so we know the time 
	return 32.184 + TAIUTC - UT1UTC;
    }
	
    /** Wrapper method. Calculates the MJD at 00.00h UT of the date specified by time.
     * @param time The time (millis since 1970).
     * @return The MJD at 00.00h UT of the date specified by time.
     */
    public static double getMJD(long time) {
	GregorianCalendar calendar = (GregorianCalendar)Calendar.getInstance();
	calendar.setTimeZone(UTC);
	calendar.setTime(new Date(time));

	return callSlaCldj(calendar.get(Calendar.YEAR), 
			   calendar.get(Calendar.MONTH)+1, 
			   calendar.get(Calendar.DATE));
    }
    
    /** Wrapper method. Calculates the UT day fraction at time, on the date specified.
     * @param time The time (millis since 1970).
     * @return The UT day fraction at time.
     */
    public static double getUT(long time) {
	GregorianCalendar calendar = (GregorianCalendar)Calendar.getInstance();	
	calendar.setTimeZone(UTC);
	calendar.setTime(new Date(time));

	return
	    (calendar.get(Calendar.HOUR_OF_DAY)*3600000.0 + 
	     calendar.get(Calendar.MINUTE)*60000.0 + 
	     calendar.get(Calendar.SECOND)*1000.0+
	     calendar.get(Calendar.MILLISECOND))/86400000.0;
    }
    
    /** Wrapper method. Calculates the local sidereal time.
     * @param time The time (millis since 1970).
     * @param longitude The observer's longitude (East = +ve).
     * @return The LST at the observer's longitude at time.
     */
    public static double getGMST(long time, double longitude) {
	GregorianCalendar calendar = (GregorianCalendar)Calendar.getInstance();	
	calendar.setTimeZone(UTC);
	calendar.setTime(new Date(time));

	double mjd = callSlaCldj(calendar.get(Calendar.YEAR), 
				 calendar.get(Calendar.MONTH)+1, 
				 calendar.get(Calendar.DATE));
	double ut1 = 
	    (calendar.get(Calendar.HOUR_OF_DAY)*3600000.0 +
	     calendar.get(Calendar.MINUTE)*60000.0 + 
	     calendar.get(Calendar.SECOND)*1000.0+
	     calendar.get(Calendar.MILLISECOND))/86400000.0 ;
	// -(double)calendar.get(Calendar.DST_OFFSET))/86400000.0 ;
	
	double gmst = callSlaGmsta(mjd, ut1) + longitude;
	double PI2 = Math.PI*2.0;
	if (gmst > PI2)
	    gmst -= PI2;
	if (gmst < 0.0)
	    gmst += PI2;
	return gmst;
    }

    /** Wrapper method. Works out the Equation of the Equinoxes at specified time.
     * @param time The date/time of required calculation.
     * @return The Equation of the Equinoxes in radians.
     */
    public static double getEquationOfEquinoxes(long time) {
	double mjd = getMJD(time);	
	double ut1 =  getUT(time);
	return callSlaEqeqx(mjd+ut1);
    }

    /** Wrapper method. Calculates the Local Apparent Sidereal Time.
     * @param time The time (millis since 1970).
     * @param longitude The observer's longitude (East = +ve).
     * @return The LAST at the observer's longitude at time.
     */
    public static double getLAST(long time, double longitude) {
	double lst = getGMST(time, longitude);
	double eq  = getEquationOfEquinoxes(time);
	return lst+eq;
    }

    /** Wrapper method. Calculates the coordinates of the given site.
     * @param latitude The latitude of the Observatory (rads).
     * @param longitude The longitude (E+ / W-) rads.
     * @param height Observatory altitude (m).
     * @param time The time for which position is required (millis).
     * @return The Observatory coordinates (RA, dec) vector.
     */    
    public static PositionVelocity getObservatoryCoordinates(double latitude, double longitude, double height, long time) {
	double last = getLAST(time, longitude);
	return callSlaPvobs(latitude, height, last);
    }
    
    /** Wrapper method. Calculates the geocentric equatorial coordinates of the Moon.
     * @param time The date/time of observation.
     * @return The geocentric equatorial coordinates of the Moon as an ngat.astrometry.Position.
     */
    public static Position getLunarCoordinates(long time) {
	double mjd = getMJD(time);	
	double ut1 =  getUT(time);
	   
	double mjdut = mjd+ut1;

	double etut  = callSlaDtt(mjdut);
	double mjdet = mjdut + (etut/86400.0);

	//System.err.println("J:Calling rdplan with mjdut = "+mjdut+" mjdet = "+mjdet);
	
	double elong = Position.ll;
	double phi   = Position.phi;

	Position posn = callSlaRdplan(mjdet, 3, elong, phi);
	//System.err.println("J:Called rdplan");
	return posn;
    }
	
    /** Wrapper method. Claculates the geocentric coordinates of the Sun.
     * @param time The date/time of observation.
     * @return The geocentric equatorial coordinates of the Sun as an ngat.astrometry.Position.
     */
    public static PositionVelocity getSolarCoordinates(long time) {
	double mjd = getMJD(time);
	double ut1 = getUT(time);

	PositionVelocity pv = callSlaEvp(mjd+ut1);
	return pv;
    }
  
    /** Calculates the position angle between 2 points on a sphere.*/
    public static double calcBearing(double az1, double alt1, double az2, double alt2) {
	return callSlaDbear(az1, alt1, az2, alt2);
    }
    
    /** Calculates the spherical coordinates of a tangent plane offset rotated by position angle.
     * @param raz  RA of field centre (rads).
     * @param decz Dec of field centre (rads).
     * @param pa   Position angle of tangent plane rotation (rads a/cwise from NORTH).
     * @param x    X offset (rotated) (rads).
     * @param y    Y offset (rotated) (rads).
     * @return Position of the offset point.*/
    public static Position getTangentPlaneOffset(double raz, double decz, double pa, double x, double y) {	
	double xi  = x*Math.cos(pa)-y*Math.sin(pa);
	double eta = x*Math.sin(pa)+y*Math.cos(pa);
	//System.err.println("Calling sladtp2s with: xi: "+xi+" eta: "+eta);
	//System.err.println("Calling sladtp2s with: raz: "+raz +" and decz: "+decz);
	Position offset = callSlaDtp2s(xi, eta, raz, decz);
	return offset;
    }
    
    /** Wrapper method. Calculates the apprent coordinates given mean coord of epoch.
     * @param ram Mean RA of epoch (rads).
     * @param decm Mean Dec of epoch (rads).
     * @param pmra PM in RA.
     * @param pmdec PM in Dec.
     * @param para Parallax.
     * @param rv RadialVelocity.
     * @param eq Equinox and epoch.
     * @param mjd Date/time for calculation of apparent coords.
     */
    public static Position getApparentCoordinates(double ram, double decm, double pmra, double pmdec,
						  double para, double rv, double eq, long time) {
	
	double mjd = getMJD(time);
	double ut1 =  getUT(time);

	Position apparent = callSlaMap(ram, decm, pmra, pmdec, para, rv, eq, mjd+ut1);
	return apparent;
    }

    /** Crap approximate method for calulating MJDs.*/
    public static double calcMJD(long time) {
	GregorianCalendar calendar = (GregorianCalendar)Calendar.getInstance();	
	calendar.setTimeZone(UTC);
	calendar.setTime(new Date(time));
	return 2451544.0 + (calendar.get(Calendar.YEAR)-2000)*365.0 + calendar.get(Calendar.DAY_OF_YEAR)+
	    (calendar.get(Calendar.HOUR_OF_DAY)*3600.0 + 
	     calendar.get(Calendar.MINUTE)*60.0 + 
	     calendar.get(Calendar.SECOND)-
	     (double)calendar.get(Calendar.DST_OFFSET)/1000.0)/86400.0;
    }
    
    static {
	//System.err.println("The java.library.path:\n"+System.getProperty("java.library.path"));
	System.loadLibrary("jslalib");
	//TimeZone utc = new SimpleTimeZone(0, "UTC");
	//TimeZone.setDefault(utc);
	//System.err.println("Set default timezone to UTC");
	//System.err.println("Loaded jslalib");
    }

}

