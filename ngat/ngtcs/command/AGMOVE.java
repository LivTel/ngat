package ngat.ngtcs.command;

/**
 * This command moves the autoguider probe mirror to the radius specified in
 * the constructor.  The range of movement is from
 * <code><b>MIN_RADIUS</b></code> (0.00mm) to <code><b>MIN_RADIUS</b></code>
 * (110.00mm).  If the radius is outside this position an
 * IllegalArgumentException will be thrown.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGMOVE extends ngat.ngtcs.command.Command
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
    new String( "$Id: AGMOVE.java,v 1.2 2013-07-04 10:06:14 cjm Exp $" );

  /**
   * Minimum radius of the autoguider probe mirror.
   */
  public static final double MIN_RADIUS = 0.000;

  /**
   * Maximum radius of the autoguider probe mirror.
   */
  public static final double MAX_RADIUS = 110.000;

  /**
   * Minimum rotator angle in degrees.
   */
  public static final double MIN_ANGLE = -240.000;

  /**
   * Maximum rotator angle in degrees.
   */
  public static final double MAX_ANGLE = 240.000;

  /**
   * Minimum pixel position.
   */
  public static final double MIN_PIXEL = 0.500;

  /**
   * Maximum pixel position.
   */
  public static final double MAX_PIXEL = 1023.500;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Radial position in millimetres.
   */
  protected double position;

  /**
   * Rotator angle in degrees.
   */
  protected double angle;

  /**
   * Autoguider X pixel.
   */
  protected double xPixel;

  /**
   * Autoguider Y pixel.
   */
  protected double yPixel;

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
   * This constructor creates an AGMOVE command with the ID specified by
   * <b>s</b> and a radius specified by <b>d</b>.
   * @param s the String ID of this command
   * @param d the radius of the autoguider probe mirror
   */
  public AGMOVE( String s, double pos, double ang, double x, double y )
    throws IllegalArgumentException
  {
    super( s );

    if( ngat.ngtcs.common.Util.outOfRange( pos, MIN_RADIUS, MAX_RADIUS ) )
      throw new IllegalArgumentException
	( "radius "+pos+" is not in the range "+MIN_RADIUS+" - "+
	  MAX_RADIUS );
    position = pos;

    if( ngat.ngtcs.common.Util.outOfRange( ang, MIN_ANGLE, MAX_ANGLE ) )
      throw new IllegalArgumentException
	( "rotator angle "+ang+" is not in range "+MIN_ANGLE+" - "+
	  MAX_ANGLE );
    angle = ang;

    if( ngat.ngtcs.common.Util.outOfRange( x, MIN_PIXEL, MAX_PIXEL ) )
      throw new IllegalArgumentException
	( "X pixel "+x+" is not in range "+MIN_PIXEL+" - "+MAX_PIXEL );
    xPixel = x;

    if( ngat.ngtcs.common.Util.outOfRange( y, MIN_PIXEL, MAX_PIXEL ) )
      throw new IllegalArgumentException
	( "Y pixel "+y+" is not in range "+MIN_PIXEL+" - "+MAX_PIXEL );
    yPixel = y;
  }


  /**
   * Return the position to set the autoguider probe mirror to, in millimetres.
   * @return position
   * @see #position
   */
  public double getPosition()
  {
    return position;
  }


  /**
   * Return the rotator angle, in degrees.
   * @return angle
   * @see #angle
   */
  public double getRotatorAngle()
  {
    return angle;
  }


  /**
   * Return the X pixel position on which to guide.
   * @return xPixel
   * @see #xPixel
   */
  public double getXPixel()
  {
    return xPixel;
  }


  /**
   * Return the Y pixel position on which to guide.
   * @return yPixel
   * @see #yPixel
   */
  public double getYPixel()
  {
    return yPixel;
  }
}
/*
 *    $Date: 2013-07-04 10:06:14 $
 * $RCSfile: AGMOVE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AGMOVE.java,v $
 *      $Id: AGMOVE.java,v 1.2 2013-07-04 10:06:14 cjm Exp $
 *     $Log: not supported by cvs2svn $
 */
