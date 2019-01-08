package ngat.astrometry;

import ngat.phase2.*;

import java.util.*;
import java.io.*;

/** Provides global access to standard astrometry functions. The calculations
 * are performed by a concrete implementation of ngat.astrometry.AstrometricCalculator
 * which is resolved via a static initializer when this class is loaded at runtime.
 * The location of the implementor is specified via a system property astrometry.impl
 * in the java command line via the -D switch e.g. <br><br>
 * 
 * java -Dastrometry.impl=JNI_SLALIB &lt;other-options&gt; &lt;main-class&gt;
 * <br><br>
 * The location of the class file for this class (here JNI_SLALIB.class) must have
 * previously been included in the CLASSPATH environment variable in order for it to
 * be found.
 * <br><br>
 * $Id$
 */
public class Astrometry {

    /** A set of catalogs.*/
    protected static Map catalogs;
    
    /** Singleton instance of an AstrometricCalculator to carry out the calculations.*/
    protected static AstrometricCalculator astroCalculator;
    
    static {
	try {
	    /* Get rid of this we only ever use the TestCalculator and only ever will....
	       astroCalculator = (AstrometricCalculator)
		(Class.forName(System.getProperty("astrometry.impl")).newInstance());*/
	    astroCalculator = new TestCalculator();
	    catalogs = new HashMap();
	} catch (Exception e) {
	    System.err.println("FATAL: Error loading AstrometricCalculator: ");
	    e.printStackTrace();
	    System.exit(1);
	}
    }


    /** Calculate the position of a SolarSystemSource at this epoch.
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public static Position getPlanetPosition(Source source) {
	return astroCalculator.getPlanetPosition(source);
    }

    /** Calculate the position of a Source at the specified epoch
     * from its elements.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public static Position getPlanetPosition(Source source, long epoch) {
	return astroCalculator.getPlanetPosition(source, epoch);
    }

    /** Calculate the source non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the source.
     */
    public static Tracking getPlanetTracking(Source source) {
	return astroCalculator.getPlanetTracking(source);	
	//return new Tracking(0.0, 0.0);
    }
    
    /** Calculate the source non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the source.
     */
    public static Tracking getPlanetTracking(Source source, long epoch) {
	return astroCalculator.getPlanetTracking(source, epoch);
	//	return new Tracking(0.0, 0.0);
    }
    
    /** Calculate the position of the MOON at this epoch. 
     * @return The ngat.astrometry.Position of the MOON.
     */
    public static Position getLunarPosition() {
	return astroCalculator.getLunarPosition();
    }
    
    /** Calculate the position of the MOON at the specified epoch.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the MOON.
     */
    public static Position getLunarPosition(long epoch) {
	return astroCalculator.getLunarPosition(epoch);	
    }
    
    /** Calculate the Lunar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public static Tracking getLunarTracking() {
	return astroCalculator.getLunarTracking();
    }

    /** Calculate the Lunar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public static Tracking getLunarTracking(long epoch) {
	return astroCalculator.getLunarTracking(epoch);
    }

    /** Calculate the position of the SUN at this epoch. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public static Position getSolarPosition() {
	return astroCalculator.getSolarPosition();
    }

    /** Calculate the position of the SUN at the specified epoch.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public static Position getSolarPosition(long epoch) {
	return astroCalculator.getSolarPosition(epoch);
    }

    /** Calculate the Solar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public static Tracking getSolarTracking() {
	return astroCalculator.getSolarTracking();
    }

    /** Calculate the Solar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public static Tracking getSolarTracking(long epoch) {
	return astroCalculator.getSolarTracking(epoch);
    }

    /** Compute the LST at a given date/time (given as a long).
     * @param time The time in millis from 1/1/1970 AD 00:00:00 UT.
     * @param longitude The longitude (rads) East of Greenwitch.
     */
    public static double computeLST(long time, double longitude) {
	return astroCalculator.computeLST(time, longitude);
    }

    /** Compute the LST at this moment (given as a long).
     * @param longitude The longitude (rads) East of Greenwitch.
     */
    public static double computeLST(double longitude) {
	return astroCalculator.computeLST(System.currentTimeMillis(), longitude);
    }

    /** Returns the reference to the instance of AstrometricCalculator in use.*/
    public static AstrometricCalculator getAstroCalculator() { return astroCalculator; }

    /** Sets the current AstrometricCalculator.*/
    public static void setAstroCalculator(AstrometricCalculator a) {
	astroCalculator = a;
    }
    
    /** Load a named catalog.*/
    public static Catalog loadCatalog(String name, File file) throws Exception {

	Catalog cat = new Catalog(name);
	
	BufferedReader bin = new BufferedReader(new FileReader(file));
	
	int il = 0;
	String line = null;
	while ( (line = bin.readLine()) != null) {
	    il++;
	    // skip blanks or comments
	    if (line.trim().startsWith("#") ||
		line.trim().equals(""))
		continue;

	    StringTokenizer st = new StringTokenizer(line);
	    if (st.countTokens() < 4)
		throw new IllegalArgumentException("Catalog ["+name+"] Missing tokens in line: "+il);

	    String tgtname = st.nextToken();
	    String stra    = st.nextToken();
	    String stdec   = st.nextToken();
	    
	    double ra  = Position.parseHMS(stra, ":");
	    double dec = Position.parseDMS(stdec, ":");
	    
	    ExtraSolarSource src = new ExtraSolarSource(tgtname);
	    src.setRA(ra);
	    src.setDec(dec);
	    src.setFrame(Source.FK5);
	    src.setEquinox(2000.0f);
	    src.setEpoch(2000.0f);
	    src.setEquinoxLetter('J');
	    src.setPmRA(0.0);
	    src.setPmDec(0.0);
	    src.setParallax(0.0);
	    src.setRadialVelocity(0.0);

	    cat.addTarget(src);
	    System.err.println("Astrometry:LoadCat("+name+"): Adding target: "+src); 
	    
	}

	catalogs.put(name, cat);
	
	return cat;
    }

    /** Returns a named catalog.*/
    public static Catalog getCatalog(String name) {
	return (Catalog)catalogs.get(name);
    }



}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2009/02/27 09:23:58  eng
/** Initial revision
/**
/** Revision 1.3  2008/08/21 10:21:17  eng
/** *** empty log message ***
/**
/** Revision 1.2  2007/09/05 12:09:33  snf
/** added catlog accessors.
/**
/** Revision 1.1  2006/12/18 12:16:09  snf
/** Initial revision
/**
/** Revision 1.3  2006/12/05 12:48:09  snf
/** tagged.
/** ,
/**
/** Revision 1.2  2001/02/23 18:47:47  snf
/** Added computeLST( current time).
/**
/** Revision 1.1  2001/01/09 14:34:23  snf
/** Initial revision
/** */
