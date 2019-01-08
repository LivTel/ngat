package ngat.ngtcs;

import java.io.*;
import java.util.*;

import ngat.util.*;
import ngat.ngtcs.common.*;

/**
 * This class implements a pointing model for an Altitude-Azimuth mount.
 * Accordingly, the conversion from right-handed vector to left-handed is
 * handled by the relevant method, and swapped back to right-handed before
 * returning.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AltAzPointingModel extends PointingModel
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
   * Multiplicative conversion from arcseconds to radians.
   * Equal to <code>Math.PI / 648000.0</code>
   */
  public static final double ARCSECS2RADIANS = Math.PI / 648000.0;

  /**
   * Multiplicative conversion from radians to arcseconds.
   * Equal to <code>648000.0 / Math.PI</code>
   */
  public static final double RADIANS2ARCSECS = 648000.0 / Math.PI;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  public static void main( String args[] )
  {
    AltAzPointingModel aapm = new AltAzPointingModel();

    if( aapm instanceof ngat.ngtcs.subsystem.PluggableSubSystem )
      System.err.println( "yes" );
    else
      System.err.println( "no" );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/


  /**
   *
   */
  public AltAzPointingModel()
  {
  }


  /**
   *
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    permanent = (AltAzPointingModelCoefficients)
      ( new AltAzPointingModelCoefficients() );
    combined = (AltAzPointingModelCoefficients)
      ( new AltAzPointingModelCoefficients() );

    NGATProperties np = new NGATProperties();
    try
    {
      np.load( t.getName()+"-"+StringUtilities.getLeaf
	       ( this.getClass().getName(), '.' )+".cfg" );
    }
    catch( Exception e )
    {
      throw new InitialisationException
	( "AltAzPointingModel : "+e );
    }

    // Assign the pointing coefficients, or 0.0 if they are absent.
    ( (AltAzPointingModelCoefficients)permanent ).
      setIA( np.getDouble( "ia", 0.0 ) * ARCSECS2RADIANS );
    ( (AltAzPointingModelCoefficients)permanent ).
      setIE( np.getDouble( "ie", 0.0 ) * ARCSECS2RADIANS );
    ( (AltAzPointingModelCoefficients)permanent ).
      setTF( np.getDouble( "tf", 0.0 ) );
    ( (AltAzPointingModelCoefficients)permanent ).
      setCA( np.getDouble( "ca", 0.0 ) * ARCSECS2RADIANS );
    ( (AltAzPointingModelCoefficients)permanent ).
      setNPAE( np.getDouble( "npae", 0.0 ) * ARCSECS2RADIANS );
    ( (AltAzPointingModelCoefficients)permanent ).
      setAN( np.getDouble( "an", 0.0 ) * ARCSECS2RADIANS );
    ( (AltAzPointingModelCoefficients)permanent ).
      setAW( np.getDouble( "aw", 0.0 ) * ARCSECS2RADIANS );
  }


  /**
   *
   */
  public PointingModelCoefficients newCoefficientSet()
  {
    return( (PointingModelCoefficients)
	    ( new AltAzPointingModelCoefficients() ) );
  }


  /**
   * Apply this pointing model to the specified perfect [ x, y, z ] position.
   * The perfect position is first rotated about the E-W axis by AN 
   * arcseconds, then about the N-S axis by AW arcseconds.  The ia, ie, ca,
   * tf and npae corrections are then added.
   * @param observed the perfect position
   * @return the position as used by the mount to point to the observed
   */
  public XYZMatrix _applyToMountPosition( XYZMatrix observed )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    double az, el, plusAz = 0.0, plusEl = 0.0;
    XYZMatrix temp  = new XYZMatrix();

    temp.setX( cosAN * observed.getX() +
	       sinAN * observed.getZ() );

    temp.setY( -sinAN * sinAW * observed.getX() +
	       cosAW * observed.getY() +
	       cosAN * sinAW * observed.getZ() );

    temp.setZ( -sinAN * cosAW * observed.getX() +
	       -sinAW * observed.getY() +
	       cosAN * cosAW * observed.getZ() );

    az = Math.atan2( temp.getY(), temp.getX() );
    el = Math.asin( temp.getZ() );

    if( el < ( Math.PI * 0.5 ) )
    {
      plusAz = ( ia + 
		 ca / Math.cos( el ) + 
		 npae * Math.tan( el ) );
    }

    plusEl = ie - tf * Math.cos( el );

    return new XYZMatrix( ( Math.cos( el + plusEl ) *
			    Math.cos( az + plusAz ) ),
			  ( Math.cos( el + plusEl ) *
			    Math.sin( az + plusAz ) ),
			  Math.sin( el + plusEl ) );
  }


  /*
   * Remove this pointing model's corrections from the specified Mount 
   * position to create a perfect position.
   * First, the ia, ie, tf, ca and npae corrections are removed, then the
   * position is rotated about the N-S axis by -AW arcseconds, then about 
   * the E-W axis by AN arcseconds.
   * @param mountXYZ the mount position
   * @return the prefect position of the target
   */
  protected XYZMatrix _removeFromMountPosition( XYZMatrix mount )
  {
    XYZMatrix temp = new XYZMatrix();

    /*
      temp.setX( cosAN * mount.getX() +
      -sinAN * mount.getZ() );

      temp.setY( -sinAN * sinAW * mount.getX() +
      cosAW * mount.getY() +
      cosAN * -sinAW * mount.getZ() );

      temp.setZ( sinAN * cosAW * mount.getX() +
      sinAW * mount.getY() +
      cosAN * cosAW * mount.getZ() );

      az = Math.atan2( temp.getY(), temp.getX() );
      el = Math.asin( temp.getZ() );

      minusEl = ie - tf * Math.cos( el );

      if( el < ( Math.PI * 0.5 ) )
      {
      minusAz = ( ia +
      ca / Math.cos( el - minusEl ) +
      npae * Math.tan( el - minusEl ) );
      }

      return( new XYZMatrix( ( Math.cos( el - minusEl ) *
      Math.cos( az - minusAz ) ),
      ( Math.cos( el - minusEl ) *
      Math.sin( az - minusAz ) ),
      Math.sin( el - minusEl ) ) );
    */

    XYZMatrix temp1 = new XYZMatrix();
    XYZMatrix temp2 = new XYZMatrix();
    XYZMatrix temp3 = new XYZMatrix();
    XYZMatrix temp4 = new XYZMatrix();

    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );


    // Remove the tube flexure
    double z = ( mount.getZ() - -tf ) * ( 1.0 + -tf * mount.getZ() );
    temp1.setZ( ( mount.getZ() - -tf * ( 1.0 + z * z ) ) /
		( 1.0 - 2.0 * -tf * z - -tf * -tf ) );
    double a = 1.0 - -tf * temp1.getZ();
    temp1.setX( mount.getX() / a );
    temp1.setY( mount.getY() / a );

    // Remove collimation
    double xi = -( ca + npae * temp1.getZ() );
    double b  = Math.sqrt( 1.0 + xi * xi );
    double c  = Math.sqrt( temp1.getX() * temp1.getX() + 
			   temp1.getY() * temp1.getY() );

    temp2.setX( ( temp1.getX() - ( xi * temp1.getY() ) / c ) / b );
    temp2.setY( ( temp1.getY() + ( xi * temp1.getX() ) / c ) / b );
    temp2.setZ( temp1.getZ() / b );

    // Remove the index errors
    double el = Math.asin( temp2.getZ() );
    double az = Math.atan2( temp2.getY(), temp2.getX() );

    temp3.setX( Math.cos( az - ia ) * Math.cos( el - ie ) );
    temp3.setY( Math.sin( az - ia ) * Math.cos( el - ie ) );
    temp3.setZ( Math.sin( el - ie ) );

    // Remove North and West Azimuth tilts and return
    temp4.setX( cosAN * temp3.getX() +
		-sinAN * temp3.getZ() );
    temp4.setY( -sinAN * sinAW * temp3.getX() +
		cosAW * temp3.getY() +
		cosAN * -sinAW * temp3.getZ() );
    temp4.setZ( sinAN * cosAW * temp3.getX() +
		sinAW * temp3.getY() +
		cosAN * cosAW * temp3.getZ() );

    temp4.normalise();

    return( temp4 );
  }


  /**
   *
   */
  protected double _applyToRotatorPosition( double rotatorAngle )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );


    // need to determine z2, rxy, rxy2, xi, xi2
    // from page 23 TCS/PTW/3.17

    double z2 = 0.0;
    double rxy = 0.0;
    double rxy2 = 0.0;
    double xi = 0.0;
    double xi2 = 0.0;;

    return( rotatorAngle - z2 * Math.atan2( -xi, Math.sqrt( rxy2-xi2 ) ) +
	    npae * rxy );
  }


  /**
   * Apply the correction due to a 'non-normal' Z axis on the mount. The two
   * tilt coefficients AN and AW are used for this.
   * @param m the matrix to apply the correction to
   * @return the (new) attitude-corrected matrix
   */
  protected XYZMatrix _applyAttitudeCorrection( XYZMatrix m )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    XYZMatrix temp  = new XYZMatrix();

    temp.setX( cosAN * m.getX() + sinAN * m.getZ() );

    temp.setY( -sinAN * sinAW * m.getX() +
	       cosAW * m.getY() +
	       cosAN * sinAW * m.getZ() );

    temp.setZ( -sinAN * cosAW * m.getX() +
	       -sinAW * m.getY() +
	       cosAN * cosAW * m.getZ() );

    return temp;
  }



  /**
   * Apply the correction due to mechanical imperfections. These corrections
   * account for Azimuth Index Error (IA), Altitude Index Error (IE),
   * Collimation Error (CA), Non-perpendicularities of the Altitude and
   * Azimuth axes (NPAE) and Tube Flexure (TF)
   *
   */
  protected XYZMatrix _applyPositionCorrection( XYZMatrix m )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();

    double az, el, plusAz = 0.0, plusEl = 0.0;

    az = Math.atan2( m.getY(), m.getX() );
    el = Math.asin( m.getZ() );

    if( el < ( Math.PI * 0.5 ) )
    {
      plusAz = ( ia + 
		 ca / Math.cos( el ) + 
		 npae * Math.tan( el ) );
    }

    plusEl = ie - tf * Math.cos( el );

    return new XYZMatrix( ( Math.cos( el + plusEl ) *
			    Math.cos( az + plusAz ) ),
			  ( Math.cos( el + plusEl ) *
			    Math.sin( az + plusAz ) ),
			  Math.sin( el + plusEl ) );
  }


  /**
   *
   */
  protected double _applyRotatorCorrection( double d )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    // need to determine z2, rxy, rxy2, xi, xi2
    // from page 23 TCS/PTW/3.17

    double z2 = 0.0;
    double rxy = 0.0;
    double rxy2 = 0.0;
    double xi = 0.0;
    double xi2 = 0.0;;

    return( d - z2 * Math.atan2( -xi, Math.sqrt( rxy2-xi2 ) ) +
	    npae * rxy );
  }


  /**
   *
   *
   */
  protected XYZMatrix _removeAttitudeCorrection( XYZMatrix m )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    // remove stuff
    return m;
  }


  /**
   *
   *
   */
  protected XYZMatrix _removePositionCorrection( XYZMatrix m )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    // remove stuff
    return m;
  }


  /**
   *
   *
   */
  protected double _removeRotatorCorrection( double d )
  {
    double ia   = ( (AltAzPointingModelCoefficients)combined ).getIA();
    double ie   = ( (AltAzPointingModelCoefficients)combined ).getIE();
    double tf   = ( (AltAzPointingModelCoefficients)combined ).getTF();
    double ca   = ( (AltAzPointingModelCoefficients)combined ).getCA();
    double npae = ( (AltAzPointingModelCoefficients)combined ).getNPAE();
    double an   = ( (AltAzPointingModelCoefficients)combined ).getAN();
    double aw   = ( (AltAzPointingModelCoefficients)combined ).getAW();

    double cosAN = Math.cos( an );
    double cosAW = Math.cos( aw );
    double sinAN = Math.sin( an );
    double sinAW = Math.sin( aw );

    // remove NPAE correction to rotator turn
    return d;
  }


  /**
   *
   */
  public void calculateCoefficients( PointingModelCoefficients p,
				     XYZMatrix desired, XYZMatrix achieved )
  {
    // adjust the specified PointingModelCoefficients to make the
    // achieved match the desired vector
    //
    // This MAY require the use of a system call to TPOINT unless inline C-code
    // can be used?
  }
}
/*
 *    $Date: 2013-07-02 13:27:15 $
 * $RCSfile: AltAzPointingModel.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/AltAzPointingModel.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
