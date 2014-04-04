package ngat.ngtcs.test;

import ngat.ngtcs.common.*;

/**
 * Simple class to test the addition of offset vectors for the OFFBYImplementor
 * class.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class OffsetTest
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

  /**
   * Main method.  The arguments should be:
   * <ul>
   * <li>RA in HH:MM:SS format</li>
   * <li>Dec in DD:MM:SS format</li>
   * <li>RA Offset in arcseconds</li>
   * <li>Dec Offset in arcseconds</li>
   * </ul>
   */
  public static void main( String args[] )
  {
    if( args.length != 8 )
    {
      System.err.println
	( "args: HH MM SS DD MM SS offset-RA offset-Dec" );
      System.exit( 1 );
    }

    HoursMinutesSeconds hms = null;
    DegreesMinutesSeconds dms = null;
    double offsetRA = 0.0;
    double offsetDec = 0.0;
	
    try
    {
      hms = new HoursMinutesSeconds
	( Integer.parseInt( args [ 0 ] ),
	  Integer.parseInt( args [ 1 ] ),
	  Integer.parseInt( args [ 2 ] ) );

      dms = new DegreesMinutesSeconds
	( Integer.parseInt( args[ 3 ] ),
	  Integer.parseInt( args[ 4 ] ),
	  Integer.parseInt( args[ 5 ] ) );

      offsetRA = ( ( Double.parseDouble( args[ 6 ] ) )
		   * Math.PI / ( 3600.0 * 180.0 ) );
      offsetDec = ( ( Double.parseDouble( args[ 7 ] ) )
		    * Math.PI / ( 3600.0 * 180.0 ) );
    }
    catch( Exception e )
    {
      System.err.println( e.toString() );
      System.exit( 2 );
    }

    System.out.println( "HMS = "+hms.toString()+"  = "+hms.getRadians() );
    System.out.println( "DMS = "+dms.toString()+"  = "+dms.getRadians() );

    double x = Math.cos( dms.getRadians() ) * Math.cos( hms.getRadians() );
    double y = Math.cos( dms.getRadians() ) * Math.sin( hms.getRadians() );
    double z = Math.sin( dms.getRadians() );

    XYZMatrix matrix = new XYZMatrix( x, y, z );
    System.out.println( "matrix = \n"+matrix );

    matrix.normalise();
    System.out.println( "matrix = \n"+matrix );

    Matrix xRotated = null;
    try
    {
      Matrix yRotation = new Matrix( 3, 3 );
      yRotation.setElement( 1, 1, Math.cos( offsetDec ) );
      yRotation.setElement( 1, 2, 0.0 );
      yRotation.setElement( 1, 3, -Math.sin( offsetDec ) );
      yRotation.setElement( 2, 1, 0.0 );
      yRotation.setElement( 2, 2, 1.0 );
      yRotation.setElement( 2, 3, 0.0 );
      yRotation.setElement( 3, 1, Math.sin( offsetDec ) );
      yRotation.setElement( 3, 2, 0.0 );
      yRotation.setElement( 3, 3, Math.cos( offsetDec ) );

      System.out.println( "yRotation = \n"+yRotation );

      Matrix yRotated = matrix.multiplyBy( yRotation );

      System.out.println( "Y rotated matrix = \n"+yRotated );

      Matrix xRotation = new Matrix( 3, 3 );
      xRotation.setElement( 1, 1, Math.cos( offsetRA ) );
      xRotation.setElement( 1, 2, Math.sin( offsetRA ) );
      xRotation.setElement( 1, 3, 0.0 );
      xRotation.setElement( 2, 1, -Math.sin( offsetRA ) );
      xRotation.setElement( 2, 2, Math.cos( offsetRA ) );
      xRotation.setElement( 2, 3, 0.0 );
      xRotation.setElement( 3, 1, 0.0 );
      xRotation.setElement( 3, 2, 0.0 );
      xRotation.setElement( 3, 3, 1.0 );

      System.out.println( "xRotation = \n"+xRotation );

      xRotated = yRotated.multiplyBy( xRotation );

      System.out.println( "X rotated matrix = \n"+xRotated );
    }
    catch( ArithmeticException ae )
    {
      System.err.println( ae.toString() );
      System.exit( 3 );
    }

    double newX = xRotated.getElement( 1, 1 );
    double newY = xRotated.getElement( 2, 1 );
    double newZ = xRotated.getElement( 3, 1 );


    System.out.println( " RA = "+HoursMinutesSeconds.radiansToHMS
			( Math.atan2( newY, newX ) ).toString() );
    System.out.println( "Dec = "+DegreesMinutesSeconds.radiansToDMS
			( Math.asin( newZ ) ).toString() );


    System.err.println( "Math.IEEEremainder( 3.14159265..., Math.PI ) = "+
			Math.IEEEremainder( 9.0, Math.PI ) );

    System.exit( 0 );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/
}
/*
 *    $Date: 2006-11-20 14:48:31 $
 * $RCSfile: OffsetTest.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/OffsetTest.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 */
