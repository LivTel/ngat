package ngat.ngtcs.subsystem;

import java.util.List;
import java.util.Vector;

import ngat.util.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.sdb.*;
import ngat.ngtcs.subsystem.acn.*;

/**
 * This class encapsulates the TTL mechanisms that are required to provide the
 * functionality specified by the class
 * <code>ngat.ngtcs.subsystem.Mount</code>.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_Mount extends Mount implements ControllableSubSystem
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * Park altitude of a TTL_Mount, in degrees.
   */
  public final static double PARK_ALTITUDE = 90.000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Azimuth Axis Control Node.
   */
  protected ACN azimuth = null;

  /**
   * Altitude Axis Control Node.
   */
  protected ACN altitude = null;

  /**
   * The TTL SDB subsystem used by this TTL Mount.
   */
  protected SDB sdb = null;

  /**
   * Boolean describing whether the PARK command has been started.
   */
  protected boolean parking = false;

  /**
   * Positional tolerance of the parked state, in .
   */
  protected double parkingTolerance = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Basic constructor.
   * This constructor assigns the axis control node objects for the altitude
   * and azimuth axes.
   */
  public TTL_Mount()
  {
    azimuth  = ACN.getInstance( ACN_NodeType.E_ACN_NODE_AZN );
    altitude = ACN.getInstance( ACN_NodeType.E_ACN_NODE_ELN );
    sdb      = SDB.getInstance();
  }


  /**
   * Implement the PluggableSubSystem method.
   * This initialisation initialises the TTL Axis Control Nodes
   * (<code>ACN</code>) for each mount axis.  In this case, for an AltAz
   * TTL_Mount, the axes are altitude and azimuth.
   */
  public void initialise( ngat.ngtcs.Telescope t )
    throws ngat.ngtcs.InitialisationException
  {
    super.initialise( t );

    getProperties();
    try
    {
      parkingTolerance = np.getDouble( "parkingTolerance" );
    }
    catch( NGATPropertyException npe )
    {
      throw new ngat.ngtcs.InitialisationException( npe );
    }

    azimuth.initialise( t );
    altitude.initialise( t );

    parking = false;
  }


  /**
   * Return a List of the time to tracking limits, and what limits they are,
   * i.e.<br>
   * <code>TrackingLimit.ZENITH_BLIND_SPOT_ENTER</code> or
   * <code>TrackingLimit.CLOCKWISE_WRAP_LIMIT</code>.
   */
  public List calculateLimits( Target target )
  {
  /*
    {
    // RUBBISH !!!

    Vector limits = new Vector();

    limits.add( calcAltLimit( siteData, target, LOWER_LIMIT, enter ) );
    limits.add( calcAltLimit( siteData, target, LOWER_LIMIT, exit  ) );
    limits.add( calcAltLimit( siteData, target, UPPER_LIMIT, enter ) );
    limits.add( calcAltLimit( siteData, target, UPPER_LIMIT, exit  ) );

    return limits;
    }
  */

  /**
   * 
   */
  /*
    protected Limit calcAltLimit( SiteData siteData, Target target, 
    double altitudeLimit, boolean state )
    {		       
    Timestamp utcLimit, utcGuess;
    double    lst, lstNow, lstDiff, lstGuess, timeDiff;
    double    acosLST, cosZD, x, y, z;
    XYZMatrix appPos;
    double    lat    = siteData.getLatitude();
    double    cosLat = Math.cos( lat );
    double    sinLat = Math.sin( lat );

    utcLimit = timer.getTime();
    cosZD = Math.cos( LOWER_LIMIT );
    do
    {
    utcGuess = utcLimit;

    appPos = astroCalc.calcApparentPosition( utcGuess, target );

    x = appPos.getX();
    y = appPos.getY();
    z = appPos.getZ();

    acosLST = Math.sqrt( ( (cosZD - sinLat*z)*(cosZD - sinLat*z) -
    (y*y*cosLat*cosLat ) ) /
    ( x*x*cosLat*cosLat - 
    y*y*cosLat*cosLat ) );

    if( ( acosLST > 1.0 )||( acosLST < -1.0 ) )
    {		      
    return null;
    }

    lst = Math.acos( acosLST );

    //calculate UTC from LST
    lstGuess = astroCalc.getLST( utcGuess );

    if( state )
    {
    lstDiff = lst - lstGuess;
    }
    else
    {
    lstDiff = lstGuess - lst;
    }

    timeDiff = ( lstDiff * astroCalc.RADS2SECS * 
    astroCalc.SIDEREAL2ATOMIC );

    utcLimit = new Timestamp( utcGuess.getSeconds() - 
    (int)timeDiff, 0,
    CalendarType.GREGORIAN,
    TimescaleType.UTC );

    }
    while ( utcLimit.getSeconds() != utcGuess.getSeconds() );

    return new Limit( new Timestamp ( utcLimit.getSeconds(), 0,
    CalendarType.GREGORIAN,
    TimescaleType.UTC ), state );
    }
  */
    return( new Vector() );
  }


  public Status getStatus()
  {
    TimestampedPosition tp = getLatestTimestampedPosition();

    System.err.println( "TTL_Mount: getStatus NOT IMPLEMENTED" );

    return( ( Status )( new MountStatus( softwareState, 
					 mountState,
					 tp.getTimestamp(), 
					 tp.getPosition() ) ) );
  }


  /**
   * Return the type of mount that this is, i.e. AltAz.
   * @return the ONLY instance of MountType.ALTAZ as it is a type-safe
   * enumeration.
   */
  public MountType getType()
  {
    return MountType.ALTAZ;
  }


  /**
   *
   */
  protected void track( TimestampedPosition tp )
    throws TTL_SystemException
  {
    parking = false;

    XYZMatrix m = tp.getPosition();
    Timestamp t = tp.getTimestamp();
    double az = Math.atan2( -m.getY(), m.getX() );
    double el = Math.asin( m.getZ() );

    azimuth.trackDegreesPosition( Math.toDegrees( az ), t );
    altitude.trackDegreesPosition( Math.toDegrees( el ), t );
  }


  /**
   *
   */
  protected void move( XYZMatrix m )
    throws TTL_SystemException
  {
    parking = false;

    double az = Math.atan2( -m.getY(), m.getX() );
    double el = Math.asin( m.getZ() );

    moveToDegreesAzimuth( Math.toDegrees( az ) );
    moveToDegreesAltitude( Math.toDegrees( el ) );
  }


  /**
   *
   */
  public boolean stop()
    throws SystemException
  {
    stop = true;
    azimuth.halt();
    altitude.halt();
  }


  /**
   *
   */
  public void park()
    throws TTL_SystemException
  {
    parking = true;
    moveToDegreesAltitude( PARK_ALTITUDE );
  }


  /**
   *
   */
  public boolean isParked()
    throws TTL_SystemException
  {
    if( !parking ) return( false );

    TimestampedPosition tp = getLatestTimestampedPosition();
    double el = Math.toDegrees( Math.asin( tp.getPosition().getZ() ) );
    if( Math.abs( el - PARK_ALTITUDE ) < parkingTolerance )
      return( true );

    return( false );
  }


  /**
   *
   */
  public void moveToDegreesAltitude( double degrees )
    throws TTL_SystemException
  {
    parking = false;
    altitude.moveToDegreesPosition( degrees );
  }


  /**
   *
   */
  public void moveToRadiansAltitude( double rads )
    throws TTL_SystemException
  {
    moveToDegreesAltitude( Math.toDegrees( rads ) );
  }


  /**
   *
   */
  public void moveToDegreesAzimuth( double degrees )
    throws TTL_SystemException
  {
    parking = false;
    azimuth.moveToDegreesPosition( degrees );
  }


  /**
   *
   */
  public void moveToRadiansAzimuth( double rads )
    throws TTL_SystemException
  {
    moveToDegreesAzimuth( Math.toDegrees( rads ) );
  }


  /**
   *
   */
  protected TimestampedPosition getLatestTimestampedPosition()
  {
    TimestampedPosition tp = null;

    try
    {
      tp = ttl_getLatestTimestampedPosition();
    }
    catch( TTL_SystemException tse )
    {
      String err = new String
	( "TTL_Mount failed to getStatus : "+tse.toString() );
      System.err.println( err );
      logger.log( 1, logName, err );

      tp = new TimestampedPosition
	( new XYZMatrix(),
	  new Timestamp( 0L, 0L, CalendarType.GREGORIAN, TimescaleType.UTC ) );
    }

    return( tp );
  }


  /**
   *
   */
  protected TimestampedPosition ttl_getLatestTimestampedPosition()
    throws TTL_SystemException
  {
    TTL_DataValue az = azimuth.getValue( VEN_DataType.D_VEN_AXIS_POSITION );
    TTL_DataValue al = altitude.getValue( VEN_DataType.D_VEN_AXIS_POSITION );

    double azRads = Math.toRadians( az.getValue() / 3600000.0 );
    double alRads = Math.toRadians( al.getValue() / 3600000.0 );

    XYZMatrix posMat = new XYZMatrix
      ( ( Math.cos( alRads ) * Math.cos( azRads ) ),
	( Math.cos( alRads ) * Math.sin( azRads ) ),
	Math.sin( alRads ) );

    return( new TimestampedPosition( posMat, al.getTimestamp() ) );
  }
}
/*
 *    $Date: 2013-07-04 10:55:53 $
 * $RCSfile: TTL_Mount.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Mount.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 */
