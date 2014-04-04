package ngat.ngtcs.command;

/**
 * This command sets the position of the Earth's pole for calculations in the
 * astrometry routines.  The pole coordinates are given as X and Y with X being
 * along the 0 (zero) longitude, and Y along the 270 longitude.
 * The position is set in arcseconds, and this command
 * will throw an IllegalArgumentException if the coordinates are outside
 * the range <code><b>MIN_POLE</b></code> (-1.00") to 
 * <code><b>MAX_POLE</b></code> (1.00")
 * 
 * @author $Author$
 * @version $Revision$
 */
public class POLE extends Command
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
   * The minimum allowable value for the pressure (0.00)
   */
  public static final double MIN_POLE = -1.00;

  /**
   * The maximum allowable value for the pressure (1030.00)
   */
  public static final double MAX_POLE = 1.00;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The X coordinate of the pole, in arcseconds.
   */
  protected double x;

  /**
   * The Y coordinate of the pole, in arcseconds.
   */
  protected double y;

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
   * Create a POLE command with the specified ID and pressure to set
   * in millibars.
   * @param s the String ID of this command
   * @param x the X coordinate fof the pole
   * @param y the Y coordinate fof the pole
   */
  public POLE( String s, double x, double y )
    throws IllegalArgumentException
  {
    super( s );

    if( ngat.ngtcs.common.Util.outOfRange
	( x, MIN_POLE, MAX_POLE ) )
      throw new IllegalArgumentException
	( "X coordinate "+x+" is outside the range "+MIN_POLE+" - "+
	  MAX_POLE );

    if( ngat.ngtcs.common.Util.outOfRange
	( y, MIN_POLE, MAX_POLE ) )
      throw new IllegalArgumentException
	( "Y coordinate "+y+" is outside the range "+MIN_POLE+" - "+
	  MAX_POLE );

    this.x = x;
    this.y = y;
  }


  /**
   * Return the X coordinate of the pole.
   * @return x
   * @see #x
   */
  public double getX()
  {
    return x;
  }


  /**
   * Return the Y coordinate of the pole.
   * @return y
   * @see #y
   */
  public double getY()
  {
    return y;
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: POLE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/POLE.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */


