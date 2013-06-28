package ngat.astrometry;

import ngat.phase2.*;

import java.io.*;
import java.util.*;
import java.text.*;
/** Methods for calculating various astrometric quantities and
 * carrying out transformations etc..
 */
public class TestCalculator implements AstrometricCalculator {

    /** The inclination/obliquity of the ecliptic in rads.*/
    public static final double ECLIPTIC_INCLINATION = Math.toRadians(23.5);

    /** Tracking delta time - we may need to use an adaptive scheme.*/
    public static final long TRACK_DELTA = 24 * 3600 *  1000L;

    /** Maximum ns track diff allowed in ns track calculation (rads) corresponds 1/2 deg/sec which is massive.*/
    public static final double MAX_DD = Math.toRadians(0.01); // 1/100 deg is about 40 asec
    
    Vector[] ephem;
    
    public TestCalculator() {
	ephem = new Vector[10];
	for (int i = 0; i < 10; i++) {
	    ephem[i] = new Vector();
	}
    }

    /** Calculate the apparent coordinates of an ExtraSolarSource at given date.
     * @param source The ExtraSolar Source.
     * @param time The date/time for the conversion.
     */
    public Position getApparentCoordinates(ExtraSolarSource source, long time) {
	// Convert values for slaMap.
	double ram   = source.getRA();
	double decm  = source.getDec();
	double pmra  = source.getPmRA(); // units???
	double pmdec = source.getPmDec(); // units???
	double para  = source.getParallax();
	double rv    = source.getRadialVelocity();
	double eq    = source.getEpoch();
	Position apparent = JSlalib.getApparentCoordinates(ram, decm, pmra, pmdec, para, rv, eq, time);
	return apparent;
    }
    
    /** Calculate the position of a SolarSystemSource at the current epoch.
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public Position getPlanetPosition(Source source) {
	return getPlanetPosition(source, System.currentTimeMillis());
    }
    
    /** Calculate the position of a Source at the specified epoch
     * from its elements. The format for the elements will depend on
     * the 'type' of source e.g. Comet, MajorPlanet, CatalogSource etc.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public Position getPlanetPosition(Source source, long epoch) {
	
	if (source instanceof CatalogSource) {	  
	    CatalogSource cs    = (CatalogSource)source;	  
	    int           catId = cs.getCatalogId();  
	    int iplanet = 0;
	    switch (catId) {
	    case CatalogSource.SUN:
		return getSolarPosition(epoch);
	    case CatalogSource.MOON:
		return getLunarPosition(epoch);
		
		
		// Deal with real planets
	    case CatalogSource.MERCURY:
		iplanet = 1;break;
	    case CatalogSource.VENUS:
		iplanet = 2;break;
	    case CatalogSource.MARS:
		iplanet = 4;break;
	    case CatalogSource.JUPITER:
		iplanet = 5;break;
	    case CatalogSource.SATURN:
		iplanet = 6;break;
	    case CatalogSource.URANUS:
		iplanet = 7;break;
	    case CatalogSource.NEPTUNE:
		iplanet = 8;break;
	    case CatalogSource.PLUTO:
		iplanet = 9;break;
	    default:
		System.err.println("ASTROMETRY:: Error unknown Cat Id: "+catId+" Returning (0,0)");
		return new Position(0.0, 0.0);
	    }
	    
	    if (iplanet != 0) {
		//System.err.println("TCalc:: Calc for "+iplanet);
		
		Position planet =
		    JSlalib.getPlanetCoordinates(epoch, iplanet);

// 		PositionVelocity sun = 
// 		    JSlalib.getSolarCoordinates(epoch);
		
// 		double x = planet.x + sun.x;
// 		double y = planet.y + sun.y;
// 		double z = planet.z + sun.z;
			
// 		double r = Math.sqrt(x*x + y*y);
// 		double n = Math.sqrt(x*x+y*y+z*z);
		
// 		double ra = Math.atan(y/x);
// 		if (x < 0.0)
// 		    ra += Math.PI;
// 		if (x > 0.0 && y < 0.0)
// 		    ra += 2.0*Math.PI;		

// 		double dec = Math.atan(z/r);
		
// 		System.err.println("TCalc:: RA: "+Math.toDegrees(ra)+" Dec: "+Math.toDegrees(dec));
		
// 		return new Position(ra, dec);

		return planet;

	    }
	   
	    //System.err.println("ASTROMETRY:: Error CatID is 0, Returning (0,0)");

	    return new Position(0.0, 0.0);

	}
	if (source instanceof Comet) {
	    Comet comet = (Comet)source;
	    //return JSlalib.getCometPosition(long time, double elong, double phi, 
	    //			    double epoch, double orbinc, double anode, 
	    //			    double perih, double aorq, double e) {
	    //System.err.println("TCalc::Exec comet: ");
	    return JSlalib.getCometPosition(epoch, 
					    Position.ll,
					    Position.phi,
					    comet.getElementEpoch(),
					    comet.getOrbitalInc(),
					    comet.getLongAscNode(),
					    comet.getArgPeri(),
					    comet.getPeriDist(),
					    comet.getEccentricity());
	    
	} else if (source instanceof MinorPlanet) {
	    // Call SlaPlante. HOW DOES THIS EVER WORK ?
	    MinorPlanet minor = (MinorPlanet)source;
	    // 	return JSlalib.getVeryCleverAsteroidPosition(epoch, 
		// 							     Position.ll,
		// 							     Position.phi,
		// 							     minor.getElementEpoch(),
		// 							     minor.getOrbitalInc(),
		// 							     minor.getLongAscNode(),
		// 							     minor.getArgPeri(),
		// 							     minor.getMeanDistance(),
		// 							     minor.getEccentricity(),
		// 							     minor.getMeanAnomaly());
	    return JSlalib.getAsteroidPosition(epoch, 
					       Position.ll,
					       Position.phi,
					       minor.getElementEpoch(),
					       minor.getOrbitalInc(),
					       minor.getLongAscNode(),
					       minor.getArgPeri(),
					       minor.getMeanDistance(),
					       minor.getEccentricity(),
					       minor.getMeanAnomaly());
	    
	    
	} else if (source instanceof MajorPlanet) {
	    // Call SlaPlante.
	    MajorPlanet planet = (MajorPlanet)source;
	    return JSlalib.getPlanetPosition(epoch, 
					     Position.ll,
					     Position.phi,
					     planet.getElementEpoch(),
					     planet.getOrbitalInc(),
					     planet.getLongAscNode(),
					     planet.getLongPeri(),
					     planet.getMeanDistance(),
					     planet.getEccentricity(),
					     planet.getMeanLongitude(),
					     planet.getDailyMotion());
	    
	} else if
	    (source instanceof EphemerisSource) {
	    
	    EphemerisSource ephem = (EphemerisSource)source;
	    
	    // Work out the position at epoch.
	    try {
		Vector track = ephem.getEphemeris();
		
		//System.err.println("Doing ephem with track"+track);

		if (track == null)
		    throw new Exception("No track available for ephemeris source");
		
		switch (track.size()) {	
		case 0:
		    throw new Exception("Ephem has no coordinates");
		case 1:
		    EphemerisSource.Coordinate point = (EphemerisSource.Coordinate)track.firstElement();
		    return new Position(point.getRA(), point.getDec());
		}
		
		
		EphemerisSource.Coordinate first = (EphemerisSource.Coordinate)track.firstElement();
		EphemerisSource.Coordinate last  = (EphemerisSource.Coordinate)track.lastElement();
		EphemerisSource.Coordinate a = null;
		EphemerisSource.Coordinate b = null;
		
		long ts = first.getTime();
		long te = last.getTime();
		if (epoch < ts) {
		    a = first;
		    b = (EphemerisSource.Coordinate)track.get(1);
		} else if
		    (epoch > te) { 
		    a = (EphemerisSource.Coordinate)track.get(track.size()-2);
		    b = last;			  
		} else {
		    
		    EphemerisSource.Coordinate current = first;
		    EphemerisSource.Coordinate next    = null;
		    int     nindex = 1;
		    boolean found  = false;
		    while (nindex < track.size() && !found) {
			next = (EphemerisSource.Coordinate)track.get(nindex);
			if (current.getTime() <= epoch && next.getTime() >= epoch) {
			    found = true;
			} else {
			    current = next;
			    nindex++;
			}
		    }
		    // should be between curr and next.
		    a = current;
		    b = next;
		}		
		// We have the 2 interpolation points
		
		double s   = (double)(b.getTime() - a.getTime());
		double dra = b.getRA()   - a.getRA();
		double ddc = b.getDec()  - a.getDec();
		double dt  = epoch          - a.getTime(); 

		//System.err.println("**Ephem Target interpolation between: "+
		//   " A="+a+" B="+b);

		double ra  = a.getRA()  + dra * dt / s;
		double dec = a.getDec() + ddc * dt / s;
		//System.err.println("**Ephem Target at: "+Position.toHMSString(ra)+","+Position.toDMSString(dec)); 
		return new Position(ra,dec);
		
	    } catch (Exception e) {
		e.printStackTrace();
		return new Position();
	    }
	    
	}
	
	// Here we should deal with anything else ???
	return new Position(0.0,0.0);	
    }

    /** Calculate the planetary non-sidereal tracking rate at the current epoch i.e. now.
     * @param source The source details - orbital elements etc.
     */ 
    public Tracking getPlanetTracking(Source source) {
	return getPlanetTracking(source, System.currentTimeMillis());
    }

    /** Calculate the planetary non-sidereal tracking rate.
     * @param source The source details - orbital elements etc.
     * @param epoch The epoch for the calculation millis from 1970.
     */ 
    public Tracking getPlanetTracking(Source source, long epoch) {
	double d_ra  = 999.0;
	double d_dec = 999.0;
	long   dt    = TRACK_DELTA;
	int ic = 0;
	while ((Math.abs(d_ra) + Math.abs(d_dec) > MAX_DD)) {
	    ic++;
	    dt /= 2;
	    Position p0 = getPlanetPosition(source, epoch);
	    Position p1 = getPlanetPosition(source, (long)(epoch+dt));
	    d_ra  = (p1.getRA()-p0.getRA());
	    d_dec = (p1.getDec()-p0.getDec());
	    System.err.println("NS Track iteration ["+ic+"] D_t = "+(dt/1000)+"s, D_ra = "+
			       (Math.toDegrees(d_ra)*3600.0)+"asec,  D_dec = "+
			       (Math.toDegrees(d_dec)*3600.0)+"asec");
	}
	System.err.println("NS Track calculated after ["+ic+"] iterations");
	// Ok we can use these they are small enough now... mult by 1k as want per second
	return new Tracking(1000.0*d_ra/(double)dt, 1000.0*d_dec/(double)dt);
    }

    /** Calculate the position of the MOON at the current epoch.
     * @return The ngat.astrometry.Position of the MOON.
     */
    public Position getLunarPosition() {
	return  getLunarPosition(System.currentTimeMillis());
    }
    
    /** Calculate the position of the MOON at the specified epoch.
     * This currently calls JSlalib which calls slaDMoon native method.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the MOON.
     */
    public Position getLunarPosition(long epoch) {	
// 	PositionVelocity moon = 
// 	    JSlalib.getLunarCoordinates(epoch);
	
// 	double x = moon.x;
// 	double y = moon.y;
// 	double z = moon.z;
	
// 	double r = Math.sqrt(x*x + y*y);
// 	double n = Math.sqrt(x*x+y*y+z*z);
		
// 	double ra = Math.atan(y/x);
// 	if (x < 0.0)
// 	    ra += Math.PI;
// 	if (x > 0.0 && y < 0.0)
// 	    ra += 2.0*Math.PI;
	
// 	double dec = Math.atan(z/r);
	
// 	return new Position(ra, dec);	

	return JSlalib.getLunarCoordinates(epoch);
    }
    
    /** Calculate the Lunar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public Tracking getLunarTracking() {
	return getLunarTracking(System.currentTimeMillis());
    }
    
    /** Calculate the Lunar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public Tracking getLunarTracking(long epoch) {
 // 	PositionVelocity moon = 
//  	    JSlalib.getLunarCoordinates(epoch);
	
//  	double x = moon.x;
//  	double y = moon.y;
//  	double z = moon.z;
	
//  	double r = Math.sqrt(x*x + y*y);
//  	double n = Math.sqrt(x*x+y*y+z*z);
	
//  	double ra = Math.atan(y/x);
//  	if (x < 0.0)
//  	    ra += Math.PI;
//  	if (x > 0.0 && y < 0.0)
//  	    ra += 2.0*Math.PI;
	
//  	double dec = Math.atan(z/r);
	
//  	double vx = moon.vx;
//  	double vy = moon.vy;
//  	double vz = moon.vz;
	
//  	double cosra  = Math.cos(ra);
//  	double cosdec = Math.cos(dec);
	
//  	double radot  = ( vy / x + ( y / ( x*x ) ) * vx ) * cosra * cosra;
//  	double decdot = ( r * vz - ( z * ( x * vx + y * vy ) / r ) ) * cosdec * cosdec / ( r * r );
	
//  	return new Tracking(radot, decdot);
		return new Tracking(0.0,0.0);
    }
    
    /** Calculate the position of the SUN at the current epoch. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public Position getSolarPosition() {
	return getSolarPosition(System.currentTimeMillis());
    }
    
    /** Calculate the position of the SUN at the specified epoch.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public Position getSolarPosition(long epoch) {
	PositionVelocity sun = 
	    JSlalib.getSolarCoordinates(epoch);
	
	double x = sun.x;
	double y = sun.y;
	double z = sun.z;
	
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
    
    /** Calculate the Solar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public Tracking getSolarTracking() {
	return getSolarTracking(System.currentTimeMillis());
    }
    
    /** Calculate the Solar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public Tracking getSolarTracking(long epoch) {
	PositionVelocity sun = 
	    JSlalib.getSolarCoordinates(epoch);
	
	double x = sun.x;
	double y = sun.y;
	double z = sun.z;
	
	double r = Math.sqrt(x*x + y*y);
	double n = Math.sqrt(x*x+y*y+z*z);
	
	double ra = Math.atan(y/x);
	if (x < 0.0)
	    ra += Math.PI;
	if (x > 0.0 && y < 0.0)
	    ra += 2.0*Math.PI;
	
	double dec = Math.atan(z/r);
	
	double vx = sun.vx;
	double vy = sun.vy;
	double vz = sun.vz;
	
	double cosra  = Math.cos(ra);
	double cosdec = Math.cos(dec);
	
	double radot  = ( vy / x + ( y / ( x*x ) ) * vx ) * cosra * cosra;
	double decdot = ( r * vz - ( z * ( x * vx + y * vy ) / r ) ) * cosdec * cosdec / ( r * r );
	
	return new Tracking(radot, decdot);
    }
    

   //  public Tracking getPlanetTracking(Source source, long epoch){

// 	if (source instanceof MajorPlanet) {
// 	    // Call SlaPlante.
// 	    MajorPlanet planet = (MajorPlanet)source;
// 	    return JSlalib.getPlanetPositionVelocity(epoch, 
// 						     Position.ll,
// 						     Position.phi,
// 						     planet.getElementEpoch(),
// 						     planet.getOrbitalInc(),
// 						     planet.getLongAscNode(),
// 						     planet.getLongPeri(),
// 						     planet.getMeanDistance(),
// 						     planet.getEccentricity(),
// 						     planet.getMeanLongitude(),
// 						     planet.getDailyMotion());
	    
// 	PositionVelocity planet = 
// 	    JSlalib.getPlanetCoordinates(epoch);
	
// 	double x = planet.x;
// 	double y = planet.y;
// 	double z = planet.z;
	
// 	double r = Math.sqrt(x*x + y*y);
// 	double n = Math.sqrt(x*x+y*y+z*z);
	
// 	double ra = Math.atan(y/x);
// 	if (x < 0.0)
// 	    ra += Math.PI;
// 	if (x > 0.0 && y < 0.0)
// 	    ra += 2.0*Math.PI;
	
// 	double dec = Math.atan(z/r);
	
// 	double vx = planet.vx;
// 	double vy = planet.vy;
// 	double vz = planet.vz;
	
// 	double cosra  = Math.cos(ra);
// 	double cosdec = Math.cos(dec);
	
// 	double radot  = ( vy / x + ( y / ( x*x ) ) * vx ) * cosra * cosra;
// 	double decdot = ( r * vz - ( z * ( x * vx + y * vy ) / r ) ) * cosdec * cosdec / ( r * r );
	
// 	return new Tracking(radot, decdot);

//     }
    
    

    /** Compute the LST at a given date/time (given as a long).
     * @param epoch The time in millis from 1/1/1970 AD 00:00:00 UT.
     * @param longitude The longitude (rads) East of Greenwitch.
     */
    public double computeLST(long epoch, double longitude) {
	double lst = JSlalib.getGMST(epoch, longitude);
	return lst;
    }
      

    /** Load the tabulated ephemeris for a CatalogSource. The file should be 
     * layed out in the following table format:-
     * <ul>
     * <li> Note (1) Do not leave extraneous spaces anywhere.
     * <li> Note (2) The 'z' signifies plus (+), minus (-) or space ( ).
     * </ul>
     * <br>
     * <date> (dd/MM/YYYY) <ra> (hh:mm:ss.sss) <dec> (+dd:mm:ss.ss) 
     * <br><br>
     *  E.g. 15/08/2001 12:22:43.123 +24:34:53.28
     * <br>
     * 
     * @param catID The ID of the CatalogSource as recorded in CatalogSource constants.
     * @param file The java.io.File containing the table.
     * @exception FileNotFoundException If the file does not exist.
     * @exception IOException If any problem occurs opening or reading from the file.
     * @exception IllegalArgumentException If there is no such CatalogSource.
     * @exception ParseException If any line cannot be parsed correctly.
     */
    public void loadEphemeris(int catId, File file) 
	throws IOException,
	       IllegalArgumentException,
	       ParseException {
	if (file == null) throw new IllegalArgumentException("No file set");
	if (catId < 0 || catId > 9) throw new IllegalArgumentException("CatId out of range 0 - 9");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	BufferedReader input = new BufferedReader(new FileReader(file));
	
	String line    = null;
	String strDate = null;
	String strRA   = null;
	String strDec  = null;
	double ra  = 0.0;
	double dec = 0.0;
	Date date = null;
	int i1 = 0;
	StringTokenizer tokenizer = null;
	while ((line = input.readLine()) != null) {
	    tokenizer = new StringTokenizer(line, " ");
	    if (tokenizer.countTokens() < 3)
		throw new ParseException("Dodgy data in line "+i1+" of file "+file.getPath(), 0);
	    strDate = tokenizer.nextToken();
	    strRA   = tokenizer.nextToken();
	    strDec  = tokenizer.nextToken();
	    date = sdf.parse(strDate);
	    ra   = Position.parseHMS(strRA);
	    dec  = Position.parseDMS(strDec);
	    //System.err.println("Parsed line with Date: "+strDate+" ra: "+ra+" dec: "+dec);
	    ephem[catId].add(new Ephemeroid(date, new Position(ra, dec)));
	    i1++;
	    //System.err.println("Parsed line: "+i1);
	}
	
	// Collections.sort((List)ephem[catId]);
	
    }
    
    /** Calculate the position from stored ephemeris.*/
    private Position interpolateFromEphemeris(int catId, long epoch) {	
	int first = 0;
	Vector v = ephem[catId];
	int ss    = v.size();
	Ephemeroid e = null;
	int index = -1;
	// Break as soon as we exceed the value.
	for (int i = 0; i < ss; i++) {
	    e = (Ephemeroid)v.elementAt(i);
	    if ( e.getDate().getTime() > epoch ) {
		index = i;
		break;
	    }
	}
	
	if 
	    (index == -1) {
	    // Extrapolate beyond latest ephemeris entry.
	    first = ss - 2;
	} else {
	    // Interpolate between 2 values.
	    first = index - 1;
	}
	
	int last = first + 1;
	double ra1  = ((Ephemeroid)ephem[catId].elementAt(first)).getPosition().getRA();
	double ra2  = ((Ephemeroid)ephem[catId].elementAt(last)).getPosition().getRA();
	double dec1 = ((Ephemeroid)ephem[catId].elementAt(first)).getPosition().getDec();
	double dec2 = ((Ephemeroid)ephem[catId].elementAt(last)).getPosition().getDec();
	long   t1   = ((Ephemeroid)ephem[catId].elementAt(first)).getDate().getTime();
	long   t2   = ((Ephemeroid)ephem[catId].elementAt(last)).getDate().getTime();
	
	double grad = (ra2 - ra1) / (t2 - t1);
	double ra   = grad*(epoch - t1) + ra1;
        grad = (dec2 - dec1) / (t2 - t1);
	double dec  = grad*(epoch - t1) + dec1;
	return new Position(ra, dec);
    }
    
    /** Container for storing Date/Position ephemeris data.*/
    class Ephemeroid implements Comparator {
	
	/** The Date for an ephemeris entry.*/
	private Date date;
	
	/** The Position at the ephemeris Date.*/
	private Position position;

	/** Create an ephemeris entry at the specified Date and Position.
	 * @param date The ephemeris Date.
	 * @param The position of the source at the ephemeris time.*/
	Ephemeroid(Date date, Position position) {
	    this.date = date;
	    this.position = position;
	}
	
	/** Returns the Date for this entry.*/
	public Date getDate() { return date; }

	/** Returns the Position at the ephemeris Date.*/
	public Position getPosition() { return position; }
	
	public int compare(Object o1, Object o2) {
	    long t1 = ((Ephemeroid)o1).getDate().getTime();
	    long t2 = ((Ephemeroid)o2).getDate().getTime();
	    
	    if (t1 < t2)
		return -1;
	    if (t1 == t2)
		return 0;
	    return 1;
	}

	public boolean equals(Object other) {
	    long t1 = date.getTime();
	    long t2 = ((Ephemeroid)other).getDate().getTime();
	    return (t1 == t2);
	}
	
    }


}

