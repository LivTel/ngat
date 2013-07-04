package ngat.ngtcs.common;

import java.io.*;
import java.lang.*;

import ngat.util.*;

/**
 * This class is a subset of the Telescope Data used in the astrometric
 * transformations performed by the NGTCS.
 *
 * Some method overloading has been used to re-direct references to make access
 * from the Java Native Interface easier.
 * 
 * @version $Revision: 1.2 $
 * @author $Author: cjm $
 */
public class SiteData extends Data
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
        new String( "$Id: SiteData.java,v 1.2 2013-07-04 10:45:58 cjm Exp $" );

    /**
     * Mean observatory longitude in radians.
     */
    protected double longitude = 0.0;

    /**
     * Mean observatory latitude in radians.
     */
    protected double latitude = 0.0;

    /**
     * Mean observatory altitude in metres.
     */
    protected double altitude = 0.0;

    /**
     * Minimum acceptable value for Site longitude
     */
    protected final double MIN_LONGITUDE = -Math.PI;

    /**
     * Maximum acceptable value for Site longitude
     */
    protected final double MAX_LONGITUDE = Math.PI;

    /**
     * Minimum acceptable value for Site latitude
     */
    protected final double MIN_LATITUDE = -Math.PI/2.0;

    /**
     * Maximum acceptable value for Site latitude
     */
    protected final double MAX_LATITUDE = Math.PI/2.0;

    /**
     * Minimum acceptable value for Site altitude
     */
    protected final double MIN_ALTITUDE = -400.0;

    /**
     * Maximum acceptable value for Site altitude
     */
    protected final double MAX_ALTITUDE = 10000.0;


    /**
     * Empty constructor
     */
    public SiteData()
    {

    }


    /**
     * SiteData Constructor.
     * @param str the name of this site.
     * @param lon the longitude of this site.
     * @param lat the latitude of this site.
     * @param alt the altitude of this site.
     */
    public SiteData( double lon, double lat, double alt )
    throws IllegalArgumentException
    {
	if( !isValid( lon, MIN_LONGITUDE, MAX_LONGITUDE ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+lon+"] out of range for Site longitude : "+
		      MIN_LONGITUDE+" rads .. "+MAX_LONGITUDE+" rads." );
	    }
	longitude = lon;
	if( !isValid( lat, MIN_LATITUDE, MAX_LATITUDE ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+lat+"] out of range for Site longitude : "+
		      +MIN_LATITUDE+" .. "+MAX_LATITUDE );
	    }
	latitude  = lat;
	if( !isValid( alt, MIN_ALTITUDE, MAX_ALTITUDE ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+alt+"] out of range for Site longitude : "+
		      MIN_ALTITUDE+" .. "+MAX_ALTITUDE );
	    }
	altitude  = alt;
    }


    /**
     * Read data values from file.
     */
    /*
    public void loadData() throws InitialisationException
    {
	NGATProperties np = new NGTAProperties();
	try
	    {
		np.load( name+"_site_data" );
	    }
	catch( FileNotFoundException fnfe )
	    {
		throw new InitialisationException
		    ( "SiteData : "+fnfe.toString() );
	    }
	catch( IOException ioe )
	    {
		throw new InitialisationException
		    ( "SiteData : "+ioe.toString() );
	    }

	try
	    {
		longitude = np.getDouble( "longitude" );
		latitude  = np.getDouble( "latitude" );
		altitude  = np.getDouble( "altitude" );
	    }
	catch( NGATPropertyException npe )
	    {
		throw new InitialisationException( npe );
	    }
    }
    */

    /**
     * Retrieve this Site's longitude
     * @return longitude
     * @see #longitude
     */
    public double getLongitude()
    {
	return longitude;
    }

    /**
     * Retrieve this Site's latitude
     * @return latitude
     * @see #latitude
     */
    public double getLatitude()
    {
	return latitude;
    }

    /**
     * Retrieve this Site's altitude
     * @return altitude
     * @see #altitude
     */
    public double getAltitude()
    {
	return altitude;
    }
}
/*
 *    $Date: 2013-07-04 10:45:58 $
 * $RCSfile: SiteData.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SiteData.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
