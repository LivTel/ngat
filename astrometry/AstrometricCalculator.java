package ngat.astrometry;

import ngat.phase2.*;

/** Methods for calculating various astrometric quantities and
 * carrying out transformations etc..
 * <br><br>
 * $Id$
 */
public interface AstrometricCalculator {

    /** Calculate the position of a SolarSystemSource at the current epoch.
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public Position getPlanetPosition(Source source);
    
    /** Calculate the position of a Source at the specified epoch
     * from its elements. The format of the elements will depend on the
     * 'type' of Source e.g. Comet, Planet, CatalogSource. Implementors
     * may choose any suitable method for calculating the Position e.g.
     * it is quite permissable to load or consult a tabulation of 
     * positions and use interpolation.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @param source The source details - orbital elements etc.
     * @return The ngat.astrometry.Position of the source calculated
     * from its supplied elements.
     */
    public Position getPlanetPosition(Source source, long epoch);

    /** Calculate the planetary non-sidereal tracking rate at the current epoch i.e. now.
     * @param source The source details - orbital elements etc.
     */ 
    public Tracking getPlanetTracking(Source source);

    /** Calculate the planetary non-sidereal tracking rate.
     * @param source The source details - orbital elements etc.
     * @param epoch The epoch for the calculation millis from 1970.
     */ 
    public Tracking getPlanetTracking(Source source, long epoch);

    /** Calculate the position of the MOON at the current epoch.
     * @return The ngat.astrometry.Position of the MOON.
     */
    public Position getLunarPosition();
	
    /** Calculate the position of the MOON at the specified epoch.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the MOON.
     */
    public Position getLunarPosition(long epoch);

	 /** Calculate the Lunar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public Tracking getLunarTracking();

    /** Calculate the Lunar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the moon.
     */
    public Tracking getLunarTracking(long epoch);

    /** Calculate the position of the SUN at the current epoch. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public Position getSolarPosition();

    /** Calculate the position of the SUN at the specified epoch.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Position of the SUN.
     */
    public Position getSolarPosition(long epoch);

     /** Calculate the Solar non-sidereal tracking rate.
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public Tracking getSolarTracking();

    /** Calculate the Solar non-sidereal tracking rate.
     * @param epoch The epoch for the calculation millis from 1970. 
     * @return The ngat.astrometry.Tracking of the sun.
     */
    public Tracking getSolarTracking(long epoch);

    /** Compute the LST at a given date/time (given as a long).
     * @param epoch The time in millis from 1/1/1970 AD 00:00:00 UT.
     * @param longitude The longitude (rads) East of Greenwitch.
     */
    public double computeLST(long epoch, double longitude);
    
}

/** $Log: not supported by cvs2svn $
/** Revision 1.1  2006/12/18 12:16:09  snf
/** Initial revision
/**
/** Revision 1.3  2001/02/23 18:47:47  snf
/** Added computeLST( current time).
/**
/** Revision 1.2  2001/01/09 14:34:11  snf
/** *** empty log message ***
/**
/** Revision 1.1  2001/01/09 14:33:36  snf
/** Initial revision
/** */
