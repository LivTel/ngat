package ngat.ngtcs;

import java.lang.String;

import ngat.ngtcs.common.*;

/**
 * Interface to the NGTCS through which different astrometric transformations
 * can be implemented.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class AstrometryCalculator
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   *
   */
  protected String name;

  /**
   * Data used form all transformations.
   */
  protected SiteData siteData;

  /**
   * Data used form all transformations.
   */
  protected IERSData iersData;

  /**
   * Data used form all transformations.
   */
  protected MeteorologicalData metData;

  /**
   * Equinox of astrometry kernel coordinates.
   */
  protected Equinox outputEquinox = Equinox.J2000;

  /**
   * Wavelength of current target in metres.
   */
  //    protected double wavelength = 5.5E-07;

  /**
   * Rotator tracking mode.
   */
  protected RotatorMode rotatorMode = RotatorMode.SKY_POSITION;

  /**
   * Rotator tracking position angle.
   */
  protected double positionAngle = 0.0;

  /**
   * Conversion factor from radians of angle to seconds of time.
   */
  public double RADS2SECS = ( 43200.0 / Math.PI );

  /**
   * Conversion factor from sidereal seconds to atomic seconds.
   */
  public double SIDEREAL2ATOMIC = 1.00273790935;


  /**
   * Constructor.
   */
  public AstrometryCalculator()
  {

  }


  /**
   * Assign the wavelength of the current target in metres.
   * @param newWavelength
   */
  /*
    public void setWavelength( double newWavelength )
    {
    wavelength = newWavelength;
    }
  */

  /**
   * Assign the output Equinox.
   * @param newOutputEquinox
   */
  public void setOutputEquinox( Equinox newOutputEquinox )
  {
    outputEquinox = newOutputEquinox;
  }


  /**
   *
   */
  public void setRotatorMode( RotatorMode newRotatorMode )
  {
    rotatorMode = newRotatorMode;
  }


  /**
   *
   */
  public RotatorMode getRotatorMode()
  {
    return rotatorMode;
  }


  /**
   *
   */
  public double getPositionAngle()
  {
    return positionAngle;
  }


  /**
   *
   */
  public void setPositionAngle( double newPositionAngle )
  {
    positionAngle = newPositionAngle;
  }


  /**
   * Assign the SiteData object containing all Site information.
   * @param newSiteData the new Data object
   */
  public void setSiteData( SiteData newSiteData )
  {
    siteData = newSiteData;
  }


  /**
   * Return the SiteData object containing all Site information.
   * @return siteData the SiteData object
   */
  public SiteData getSiteData()
  {
    return siteData;
  }


  /**
   * Assign the IERSData object containing all position/time information.
   * @param newIERSData the new Data object
   */
  public void setIERSData( IERSData newIERSData )
  {
    iersData = newIERSData;
  }


  /**
   * Return the IERSData object containing all position/time information.
   * @return the IERSData object
   */
  public IERSData getIERSData()
  {
    return iersData;
  }


  /**
   * Assign the MeteorologicalData object containing all weather information.
   * @param newMetData the new Data object
   */
  public void setMeteorologicalData( MeteorologicalData newMetData )
  {
    metData = newMetData;
  }


  /**
   * Return the MeteorologicalData object containing all weather information.
   * @return the MeteorologicalData object
   */
  public MeteorologicalData getMeteorologicalData()
  {
    return metData;
  }


  /**
   *
   *
   */
  public XYZMatrix calcApparentPosition( Timestamp timestamp,
					 Target target,
					 double wavelength )
						     
  {
    return new XYZMatrix( 0.0, 0.0, 0.0 );
  }


  /**
   * Generate the downstream (coords to mount) timestamped position demand.
   */
  public XYZMatrix generatePositionDemand( Timestamp timestamp,
					   Target target,
					   double wavelength )
  {
    return new XYZMatrix( 0.0, 0.0, 0.0 );
  }


  /**
   * Generate the upstream (mount to coords) timestamped reported position.
   * @see AstrometryCalculator#generatePositionCoordinates
   */
  public ReportedTarget generatePositionCoordinates( Timestamp timestamp,
						     XYZMatrix mntPosition,
						     double wavelength )
  {
    return new ReportedTarget();
  }


  /**
   * Convert an Alt,Az vector (N=0, E=90) to RA,Dec.
   * @param altAz the Alz,Az XYZmatrix
   * @return the RADec of specified Alt,Az position.
   */
  public XYZMatrix altAzToRADec( XYZMatrix altAz )
  {
    double sinlat = Math.sin( siteData.getLatitude() );
    double coslat = Math.cos( siteData.getLatitude() );

    return new XYZMatrix( ( coslat * altAz.getX() -
			    sinlat * altAz.getZ() ), 
			  ( -altAz.getY() ), 
			  ( sinlat * altAz.getX() +
			    coslat * altAz.getZ() ) );
  }


  /**
   * Convert an RA,Dec vector to Alt,Az vector (N=0, E=90).
   * @param raDec the RA,Dec XYZmatrix
   * @return the Alt,Az of specified RADec position.
   */
  public XYZMatrix raDecToAltAz( XYZMatrix raDec )
  {
    double sinlat = Math.sin( siteData.getLatitude() );
    double coslat = Math.cos( siteData.getLatitude() );

    return new XYZMatrix( ( coslat * raDec.getX() +
			    sinlat * raDec.getZ() ),
			  ( -raDec.getY() ),
			  ( coslat * raDec.getZ() -
			    sinlat * raDec.getX() ) );
  }


  /**
   * Calculate and return the bearing of the second point with respect to the
   * first point.
   * The points are defined by the two vectors, and the coordinate system
   * <b>must</b> be the same for both.
   * @param m1 the `center' point
   * @param m2 the point defining the direction of the bearing
   * @return the bearing of m2 from m1
   */
  public double calcBearing( XYZMatrix m1, XYZMatrix m2 )
  {
    double x1, y1, z1, x2, y2, z2, radius, sine, cosine;

    x1 = m1.getX();
    y1 = m1.getY();
    z1 = m1.getZ();
    x2 = m2.getX();
    y2 = m2.getY();
    z2 = m2.getZ();

    radius = Math.sqrt( x1 * x1 + y1 * y1 + z1 * z1 );
    if( radius != 0.0 )
    {
      x1 /= radius;
      y1 /= radius;
      z1 /= radius;
    }

    sine = y2 * x1 - x2 * y1;
    cosine = z2 * ( x1 * x1 + y1 * y1 ) - z1 * ( x2 * x1 + y2 * y1 );

    if( ( sine != 0.0 )||( cosine != 0.0 ) )
      return Math.atan2( sine, cosine );
	
    return 0.0;
  }


  /**
   * Parse the current specified Timestamp into the following calendars:
   * <ul>
   * <li>JULIAN</li>
   * <li>BESSELIAN</li>
   * <li>GREGORIAN</li>
   * </ul><br>
   * and the following timescales:
   * <ul>
   * <li>UTC</li>
   * <li>UT1</li>
   * <li>TDB</li>
   * <li>TDT</li>
   * <li>TAI</li>
   * </ul>
   */
  public Time parseTimestamp( Timestamp time )
  {
    return new Time();
  }
}
/*
 *    $Date: 2013-07-02 13:28:18 $
 * $RCSfile: AstrometryCalculator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/AstrometryCalculator.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
