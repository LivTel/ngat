package ngat.ngtcs;

import java.util.*;

import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.*;

/**
 * The VirtualTelescope class contains all the necessary methods to allow the
 * position transformations required to point a telescope.
 * <br>
 * The three main transformations are:
 * <ol>
 * <li>Target coords and focal plane X,Y -> Mount position</li>
 * <li>Target coords and mount position -> Focal plane X,Y</li>
 * <li>Focal plane X,Y and Mount position -> Target coords</li>
 * </ol>
 * <p>
 * There are also various transformations that are used within some/all of the
 * above transformations such as the calculating of the tangent plane
 * coordinates from the focal station X,Y and the rotator position angle.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class VirtualTelescope
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: VirtualTelescope.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

  /**
   * AstrometryCalculator used to perform all astrometric transformations.
   */
  protected static AstrometryCalculator astroCalc;

  /**
   * Defined limit.
   */
  protected static double TINY = 1.0E-06;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Telescope system for which this is a VirtualTelescope..
   */
  protected Telescope telescope;

  /**
   * Mount for the Telescope.
   */
  protected Mount mount;

  /**
   * The Rotator used on the Telescope.
   */
  protected Rotator rotator;

  /**
   * FocalStation object used to hold all focus data.
   */
  protected FocalStation focalStation;

  /**
   * Focal length of this Telescope, in metres.
   */
  protected double focalLength = 1.0;

  /**
   *
   */
  protected PointingOrigin pointingOrigin;

  /**
   * Name of this VirtualTelescope.
   */
  protected String name;

  /**
   * Wavelgnth of light this VirtualTelescope is looking at.
   */
  protected double lambda = 5.5E-07;

  /**
   * Pointing model to be used on this VirtualTelescope.
   */
  protected PointingModel pointingModel;

  /**
   * Define the offset for the PA calculation points.
   */
  private double offsetRadians = 0.000005;

  /**
   * Define the sine of the offset angle for the PA calculation points.
   */
  private double sineOffset = Math.sin( offsetRadians );

  /**
   * The astronomical target used in the transformations.
   */
  protected Target target;



  /**
   * Virtual Telescope constructor.
   * @param telescope telescope for which this is a virtual representation
   */
  public VirtualTelescope( Telescope t,
			   FocalStation fs,
			   AstrometryCalculator ac,
			   double d )
  {
    telescope     = t;
    focalStation  = fs;
    astroCalc     = ac;
    focalLength   = d;
    name          = focalStation.getName();
    pointingModel = telescope.getMount().getPointingModel();

    // read relevant config scripts and initiallise
  }


  /**
   * Virtual Telescope constructor.
   * @param telescope telescope for which this is a virtual representation
   */
  public VirtualTelescope( FocalStation fs,
			   AstrometryCalculator ac,
			   double d,
			   PointingModel pm )
  {
    focalStation  = fs;
    astroCalc     = ac;
    focalLength   = d;
    name          = focalStation.getName();
    pointingModel = pm;

    // read relevant config scripts and initiallise
  }


  /**
   * Return the FocalStation defining the pointing for this VT.
   * @return focalStation
   * @see focalStation
   */
  public FocalStation getFocalStation()
  {
    return focalStation;
  }


  /**
   * Method to set the observing wavelength.
   * @param d the wavelength in metres
   * @see #lambda
   */
  public void setWavelength( double d )
  {
    lambda = d;
  }


  /**
   * Method to return observing wavelength.
   * @return the current observing wavelength in metres
   * @see #lambda
   */
  public double getWavelength()
  {
    return lambda;
  }


  /**
   * Set the target to be subsequently used by this VirtualTelescope.
   * @param t the new target
   * @see #target
   */
  public void setTarget( Target t )
  {
    target = t;
  }


  /**
   * Return the current target being used with this VirtualTelescope.
   * @return target
   * @see #target
   */
  public Target getTarget()
  {
    return target;
  }


  /**
   * This method calls <code>calcMountPosition</code> with the extra values
   * taken from the predicted rotator position, the (X,Y) pointing origin
   * defined by this VirtualTelescope's FocalStation and lambda, the current
   * observing wavelength.
   * @param timestamp   Timestamp for the transformations
   * @param target to observe
   * @return the mount position as an XYZMatrix
   */
  public XYZMatrix calcMountPosition( Timestamp timestamp,
				      Target target )
  {
    return calcMountPosition( timestamp, target, focalStation.getTargetXY(),
			      rotator.predictPositionAngle( timestamp ),
			      lambda );
  }


  /**
   * Calculate the Mount position required to observe the specifed target,
   * on the specified pointing origin at the specified rotation angle, at the
   * specified time.
   * <br>
   * First the theoretical position is calculated for the target, then the
   * pointing origin is transformed into tangent plane coordinates before the
   * theoretical vector is adjusted for the tangent plane coordinates.
   * @param timestamp   Timestamp for the transformations
   * @param target to observe
   * @param pointingOriginXY the coordinates of the image in the focal plane
   * @param rotatorPA the orientation of the focal plane
   * @param wavelength the observing wavelength, in metres
   * @return the mount position as an XYZMatrix
   */
  public static XYZMatrix calcMountPosition( Timestamp timestamp,
					     Target target,
					     PointingOrigin pointingOriginXY,
					     double rotatorPA,
					     double wavelength )
  {
    // calculate the 'perfect' vector for the specified target
    XYZMatrix matrix = astroCalc.generatePositionDemand
      ( timestamp, target, wavelength );

    // move the target in the focal plane from (0,0) to (X,Y)
    return moveTargetToXY( matrix, pointingOriginXY, rotatorPA );
  }


  /**
   * Adjusts the target vector to account for the desired image
   * location in the focal plane by adjusting the specified target vector to
   * originate from the specified pointing origin, oriented at the specified
   * rotator position angle.
   * @param target the (0,0) target vector
   * @param pointingOriginXY the (X,Y) coordinates in the focal plane
   * @param rotatorPA the orientation of the focal plane
   */
  // slaDtpv2c.
  public static XYZMatrix moveTargetToXY( XYZMatrix target,
					  PointingOrigin pointingOriginXY,
					  double rotatorPA )
  {
    double sinPA = Math.sin( rotatorPA );
    double cosPA = Math.cos( rotatorPA );
    double x = pointingOriginXY.getX();
    double y = pointingOriginXY.getY();
    
    double xi     = x * cosPA + y * sinPA;
    double eta    = x * sinPA + y * cosPA;
    
    double rSq    = ( target.getX() * target.getX() + 
		      target.getY() * target.getY() );
    double xiSq   = xi * xi;
    double etaSq  = eta * eta;
    double sdf    = target.getZ() * Math.sqrt( xiSq + ( etaSq + 1.0 ) );
    double f      = ( rSq * ( etaSq + 1.0 ) - 
		      target.getZ() * target.getZ() * xiSq );
    double c = 0.0, r = 0.0;
    XYZMatrix returnXYZ;

    if ( f > 0.0 )
    {
      r = Math.sqrt( f );
      c = ( sdf * eta + r ) / 
	( ( etaSq + 1.0 ) * Math.sqrt ( rSq * ( f + xiSq ) ) );

      returnXYZ = new XYZMatrix
	( ( c * ( target.getX() * r + target.getY() * xi ) ),
	  ( c * ( target.getY() * r - target.getX() * xi ) ),
	  ( ( sdf - eta * r ) / ( etaSq + 1.0 ) ) );
    }
    else 
    {
      // Arbitrarily places the tangent point so that increasing eta
      // matches increasing declination, i.e. North is up.
      double _x, _y, _z;
	
      _x = target.getX() - eta;
      _y = 0.0;
      _z = Math.sqrt( 1.0 - _x * _x );
	
      returnXYZ = new XYZMatrix( _x, _y, _z );
    }

    //returnXYZ = target;

    return returnXYZ;
  }
  

  /**
   * This method calls <code>calcObservedTarget</code> with the extra values
   * taken from the predicted rotator position, the (X,Y) pointing origin
   * defined by this VirtualTelescope's FocalStation and lambda, the current
   * observing wavelength.
   */
  public ReportedTarget calcObservedTarget( Timestamp timestamp,
					    XYZMatrix mountPosition )
  {
    return calcObservedTarget( timestamp, mountPosition,
			       focalStation.getTargetXY(),
			       rotator.predictPositionAngle( timestamp ),
			       lambda );
  }


  /**
   * Calculate the target coordinates given the current telescope position and
   * pointing origin.
   * In this method the Mount position and rotator angle are used to
   * determine the position the Mount <it>would</it> be at, were the target
   * at the pointing origin of (0,0) in the xi-eta plane.
   *
   * This star position is then used to calculate the current
   * telescope coordinates.
   *
   * @param timestamp   Timestamp for the transformations
   * @param mountPosition the mount position of the target
   * @param pointingOriginXY the coordinates of the image in the focal plane
   * @param rotatorPA the orientation of the focal plane
   * @param wavelength the observing wavelength, in metres
   * @return the mount position's target coordinates
   */
  public static ReportedTarget calcObservedTarget( Timestamp timestamp,
						   XYZMatrix mountPosition,
						   PointingOrigin pOriginXY,
						   double rotatorPA,
						   double wavelength )
  {
    // move the target in the focal plane from (X,Y) to (0,0)
    XYZMatrix target = moveTargetFromXY( mountPosition, pOriginXY );

    // calculate the target being observed
    return astroCalc.generatePositionCoordinates
      ( timestamp, target, wavelength );
  }


  /** 
   * Given the current telescope position calculate the coordinates
   * defined by the current pointing origin.
   */
  // slaDtp2v
  public static XYZMatrix moveTargetFromXY( XYZMatrix observed,
					    PointingOrigin po )
  {
    // not sure if this needs checking...
    double xi = po.getX();
    double eta = po.getY();
    XYZMatrix returnXYZ;

    double f = Math.sqrt( 1.0 + eta * eta + xi * xi );
    double r = Math.sqrt( observed.getX() * observed.getX() + 
			  observed.getY() * observed.getY() );

    if( r < Double.MIN_VALUE )
    {
      r = Double.MIN_VALUE;
      f = Double.MIN_VALUE;
    }
    returnXYZ = new XYZMatrix
      ( ( ( observed.getX() - 
	    ( xi * observed.getY() + 
	      eta * observed.getX() * observed.getZ() ) / r ) / f ),
	( ( observed.getY() + 
	    ( xi * observed.getX() - 
	      eta * observed.getY() * observed.getZ() ) / r ) / f ),
	( ( observed.getZ() + eta * r ) / f ) );

    return returnXYZ;
  }





  public PointingOrigin calcTargetXY( Timestamp timestamp,
				      XYZMatrix mountPosition,
				      Target target )
  {
    // calculate the target vector
    XYZMatrix m1 = astroCalc.generatePositionDemand
      ( timestamp, target, lambda );

    // calculate the XY of the target vector at the current mount position
    // slaDv2tp
    return calculateXY( mountPosition, m1 );
  }


  /**
   * Given the current telescope position calculate the pointing origin
   * required for the current target.
   */
  // slaDv2tp
  public static PointingOrigin calculateXY( XYZMatrix target, 
					    XYZMatrix mount )
  {
    double xi = 0.0;
    double eta = 0.0;

    double xTarget = target.getX();
    double yTarget = target.getY();
    double zTarget = target.getZ();
    double xMount = mount.getX();
    double yMount = mount.getY();
    double zMount = mount.getZ();
    double rSq     = xMount * xMount + yMount * yMount;
    double r       = Math.sqrt ( rSq );

    if ( r == 0.0 )
    {
      r = 1e-20;
      xMount = r;
    }

    double w = xTarget * xMount + yTarget * yMount;
    double d = w + zTarget * zMount;

    if ( d > TINY )
    {
      // OK
    } 
    else  if ( d == TINY ) 
    {
      // star too far from axis
      d = TINY;
    } 
    else if ( d > -TINY ) 
    {
      // antistar on tangent plane
      d = -TINY;
    } 
    else 
    {
      // antistar too far from axis
    }
    d *= r;

    xi = ( yTarget * xMount - xTarget * yMount ) / d;
    eta = ( zTarget * rSq - zMount * w ) / d;

    return new PointingOrigin( xi, eta );
  }


  /**
   *
   */
  public double calcRotatorDemand( Timestamp timestamp, Target target )
  {
    RotatorMode rotatorMode = telescope.getRotatorMode();
    XYZMatrix lowerPos, upperPos;
    double positionAngle, instrumentAngle, rotatorDemand;
    XYZMatrix lowerCorrectedAttitudeTarget, upperCorrectedAttitudeTarget;

    // calc upper and lower points from dec +- delta dec
    if( ( rotatorMode == RotatorMode.SKY_POSITION )||
	( rotatorMode == RotatorMode.FLOATING_SKY_POSITION )||
	( rotatorMode == RotatorMode.FLOATING_VERTICAL_POSITION ) )
    {
      Target upperTarget, lowerTarget;
      double dec;

      // create a target above and below the user-target
      dec = target.getDec();

      upperTarget = (Target)target.clone();
      lowerTarget = (Target)target.clone();

      upperTarget.setDec( dec + offsetRadians );
      lowerTarget.setDec( dec - offsetRadians );

      upperPos = astroCalc.generatePositionDemand
	( timestamp, upperTarget, lambda );

      lowerPos = astroCalc.generatePositionDemand
	( timestamp, lowerTarget, lambda );
    }
    // or calc upper and lower points from el +- delta el
    else
    {
      double x, y, z, r;
      XYZMatrix observedPos = astroCalc.generatePositionDemand
	( timestamp, target, lambda );

      x = observedPos.getX();
      y = observedPos.getY();
      z = observedPos.getZ();

      r = Math.sqrt( x*x + y*y +
		     ( z + sineOffset )*( z + sineOffset ) );
      upperPos = new XYZMatrix( x/r, y/r, ( z + sineOffset )/r );

      r = Math.sqrt( x*x + y*y +
		     ( z - sineOffset )*( z - sineOffset ) );
      lowerPos = new XYZMatrix( x/r, y/r, ( z - sineOffset )/r );
    }

    //convert from Horizon to Equatorial
    if( mount.getType() == MountType.EQUATORIAL )
    {
      XYZMatrix temp;
      temp = astroCalc.altAzToRADec( upperPos );
      upperPos = temp;

      temp = astroCalc.altAzToRADec( lowerPos );
      lowerPos = temp;
    }

    // apply Attitude PointingModel corrections
    upperCorrectedAttitudeTarget =
      pointingModel.applyAttitudeCorrection( upperPos );
    lowerCorrectedAttitudeTarget =
      pointingModel.applyAttitudeCorrection( lowerPos );

    // calculate the rotator bearing for UP (Zenith or North) in
    // the specified rotator tracking coordinate system.
    positionAngle = astroCalc.calcBearing
      ( lowerCorrectedAttitudeTarget, upperCorrectedAttitudeTarget );

    // add the angle defining the Instrument Principal Direction
    instrumentAngle = ( positionAngle +
			focalStation.getInstrumentAlignmentAngle() );

    // add the user defined position angle
    rotatorDemand = telescope.getPositionAngle() - instrumentAngle; 


    return rotatorDemand;
  }


  /**
   *
   *
   */
  public double calcPositionAngle( Timestamp timestamp, XYZMatrix mountPos )
  {
    XYZMatrix upperPos, lowerPos;
    ReportedTarget upperRT, lowerRT;
    double skyPA, positionAngle, x, y, z, r;
    RotatorMode rotatorMode = telescope.getRotatorMode();

    x = mountPos.getX();
    y = mountPos.getY();
    z = mountPos.getZ();

    positionAngle = pointingModel.removeRotatorCorrection
      ( rotator.getPositionAngle() );

    /*
      System.err.println( "current corrected rotPos = "+
      Math.toDegrees( positionAngle ) );
    */

    if( ( rotatorMode == RotatorMode.SKY_POSITION )||
	( rotatorMode == RotatorMode.FLOATING_SKY_POSITION )||
	( rotatorMode == RotatorMode.FLOATING_VERTICAL_POSITION ) )
    {
      // use upper and lower points
      r = Math.sqrt( x*x + y*y +
		     ( z + sineOffset )*( z + sineOffset ) );
      upperPos = new XYZMatrix( x/r, y/r, ( z + sineOffset )/r );

      r = Math.sqrt( x*x + y*y +
		     ( z - sineOffset )*( z - sineOffset ) );
      lowerPos = new XYZMatrix( x/r, y/r, ( z - sineOffset )/r );

      //convert from Horizon to Equatorial
      if( mount.getType() == MountType.EQUATORIAL )
      {
	XYZMatrix temp;
	temp = astroCalc.raDecToAltAz( upperPos );
	upperPos = temp;

	temp = astroCalc.raDecToAltAz( lowerPos );
	lowerPos = temp;
      }

      // send upper and lower points through VT to find coords
      upperRT = astroCalc.generatePositionCoordinates
	( timestamp, upperPos, lambda );
      lowerRT = astroCalc.generatePositionCoordinates
	( timestamp, lowerPos, lambda );

      // find what PA a rotPos of 0.0 gives at Az,El
      skyPA = astroCalc.calcBearing
	( lowerRT.getOutputRADec(), upperRT.getOutputRADec() );

      /*
	System.err.println( "skyPA = "+Math.toDegrees( skyPA ) );
      */

      positionAngle -= skyPA;
    }

    /*
      System.err.println( "positionAngle = "+
      Math.toDegrees( positionAngle ) );
    */

    positionAngle += focalStation.getInstrumentAlignmentAngle();

    /*
      System.err.println( "positionAngle = "+
      Math.toDegrees( positionAngle ) );
    */

    return positionAngle;
  }


  /**
   * Calculate the current pointing origin given the chosen RA and Dec, and
   * the current telescope position.
   */
  public PointingOrigin calcPointingOrigin( Timestamp timestamp,
					    Target target, 
					    XYZMatrix observed,
					    double rotatorAngle )
  {
    XYZMatrix position  = astroCalc.generatePositionDemand
      ( timestamp, target, lambda );

    PointingOrigin po =  calculateXY(  observed, position );

    double x = ( po.getX() * Math.cos( -rotatorAngle ) +
		 po.getY() * Math.sin( -rotatorAngle ) );
    double y = ( po.getX() * Math.sin( -rotatorAngle ) +
		 po.getY() * Math.cos( -rotatorAngle ) );

    return( new PointingOrigin( x, y ) );
  }

  /**
   * Calculate Xi and Eta, the two coordinates of the pointing origin
   * in the focal plane.
   */
  public PointingOrigin calcPointingXiEta( double rotatorAngle )
  {
    // obtain the XY on the rotator of where the target is/should be
    return( calcPointingXiEta( rotatorAngle,
			       focalStation.getTargetXY(),
			       focalLength ) );
  }


  /**
   * Calculate Xi and Eta, the two coordinates of the pointing origin
   * in the focal plane.
   */
  public static PointingOrigin calcPointingXiEta( double rotatorAngle,
						  PointingOrigin po,
						  double fLength )
  {
    double xi = ( po.getX() * Math.cos( rotatorAngle ) +
		  po.getY() * Math.sin( rotatorAngle ) );
    double eta = ( po.getX() * Math.sin( rotatorAngle ) +
		   po.getY() * Math.cos( rotatorAngle ) );

    if( xi  != 0.0 ) xi  /= fLength;
    if( eta != 0.0 ) eta /= fLength;

    return new PointingOrigin( xi, eta );
  }
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: VirtualTelescope.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/VirtualTelescope.java,v $
 *      $Id: VirtualTelescope.java,v 1.1 2003-07-01 10:11:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
