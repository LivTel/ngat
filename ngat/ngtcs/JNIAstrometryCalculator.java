package ngat.ngtcs;

import ngat.ngtcs.common.*;

/**
 * This class is the JNI layer interacting with a C slalib library for the 
 * astrometric transformations.
 *
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public class JNIAstrometryCalculator extends AstrometryCalculator
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString = new String( "$Id: JNIAstrometryCalculator.java,v 1.1 2003-09-19 15:58:40 je Exp $" );

  /**
   * Name of the C slalib library to use.
   */
  protected final static String libName = "JNIAstrometryCalculator";


  /**
   * Load the slalib-based C astrometry library.
   */
  static
  {
    System.loadLibrary( libName );
    jniInitialise();
  }


  /**
   * Create and new slalib-based Astrometric Kernel.
   */
  public JNIAstrometryCalculator()
  {
    super();
  }


  /**
   * Return an array of Limits defining where the specified Target becomes
   * impossible to track.
   */
  public XYZMatrix calcApparentPosition( Timestamp timestamp,
					 Target target,
					 double wavelength )
  {
    return jniCalcApparentPosition( timestamp, target, wavelength );
  }


  /**
   * Generate the downstream (coords to mount) timestamped position demand.
   *
   * 
   *
   * @see AstrometryCalculator#generatePositionDemand
   */
  public XYZMatrix generatePositionDemand( Timestamp timestamp,
					   Target target,
					   double wavelength )
  {
    return jniGeneratePositionDemand
      ( timestamp, target, wavelength, siteData, iersData, metData );
  }


  /**
   * Generate the upstream (mount to coords) timestamped reported position.
   * @see AstrometryCalculator#generatePositionCoordinates
   */
  public ReportedTarget generatePositionCoordinates
    ( Timestamp timestamp, XYZMatrix mountPosition, double wavelength )
  {
    return jniGeneratePositionCoordinates
      ( timestamp, mountPosition, wavelength,
	siteData, iersData, metData );
  }


  /**
   *
   */
  public Time parseTimestamp( Timestamp time )
  {
    return jniParseTimestamp( time, siteData, iersData );
  }


  /**
   * Initialisation of the C library.
   * @see c/jni_astrometry.html#jniInitialise
   */
  protected static native void jniInitialise();

  /**
   * Calculate the Apparent position of the specified Target at the
   * specified Timestamp.
   */
  protected native XYZMatrix jniCalcApparentPosition
    ( Timestamp ts, Target tgt, double wl );

  /**
   * Generation of the coords-to-mount timestamped position 
   * demand in the C library.
   * @see c/slalib_astrometry.html#jniGeneratePositionDemand
   */
  protected native XYZMatrix jniGeneratePositionDemand
    ( Timestamp t, Target tgt, double wl,
      SiteData sD, IERSData iD, MeteorologicalData mD );

  /**
   * Generation of the mount-to-coords timestamped reported 
   * position in the C library.
   * @see c/slalib_astrometry.html#jniGeneratePositionCoordinates
   */
  protected native ReportedTarget jniGeneratePositionCoordinates
    ( Timestamp t, XYZMatrix mntPos, double wl,
      SiteData sD, IERSData iD, MeteorologicalData mD );
    

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
  protected native Time jniParseTimestamp
    ( Timestamp t, SiteData sd, IERSData id );

}
/*
 *    $Date: 2003-09-19 15:58:40 $
 * $RCSfile: JNIAstrometryCalculator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/JNIAstrometryCalculator.java,v $
 *      $Id: JNIAstrometryCalculator.java,v 1.1 2003-09-19 15:58:40 je Exp $
 *     $Log: not supported by cvs2svn $
 */
