package ngat.ngtcs.command;

/**
 * This command drives the focus mechanism to an absolute position.  The
 * constructor for this command will throw an IllegalArgumentException if the 
 * position is outside the range <code><b>MIN_POSITION</b></code> -
 * <code><b>MAX_POSITION</b></code>.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class FOCUS extends ngat.ngtcs.command.Command
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
    new String( "$Id: FOCUS.java,v 1.3 2013-07-04 10:06:47 cjm Exp $" );

  /**
   * The minimum (0.00) absolute position of the focus mechanism in
   * millimetres.
   */
  public static final double MIN_POSITION = 0.000;

  /**
   * The maximum (60.00) absolute position of the focus mechanism in
   * millimetres.
   */
  public static final double MAX_POSITION = 60.000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The absolute position of the focus mechanism in millimetres.
   */
  protected double position;

  /**
   * Description of whether the default focus position should be used.
   */
  protected boolean def = false;

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
   * This is the complete constructor, setting all possible values in this
   * object.
   * @param s the String ID of this command
   * @param d the absolute position of the focus mechanism in millimetres
   * @param b the boolean describing whether to use the def position
   */
  public FOCUS( String s, double d, boolean b )
    throws IllegalArgumentException
  {
    super( s );

    if( ngat.ngtcs.common.Util.outOfRange( d, MIN_POSITION,
					   MAX_POSITION ) )
      throw new IllegalArgumentException
	( "Position "+d+" is outside the range "+MIN_POSITION+" - "+
	  MAX_POSITION );

    position = d;
    def = b;
  }

  /**
   * This constructor is used to set the focus position (in millimetres)
   * only.
   * @param s the String ID of this command
   * @param d the absolute position of the focus mechanism in millimetres
   */
  public FOCUS( String s, double d )
  {
    super( s );
	
    if( ngat.ngtcs.common.Util.outOfRange( d, MIN_POSITION,
					   MAX_POSITION ) )
      throw new IllegalArgumentException
	( "Position "+d+" is outside the range "+MIN_POSITION+" - "+
	  MAX_POSITION );

    position = d;
  }


  /**
   * This constructor is used to set the def focus position only.
   * @param s the String ID of this command
   * @param b the boolean describing whether to use the def position
   */
  public FOCUS( String s )
  {
    super( s );
    def = true;
  }


  /**
   * Return the absolute position of the focus mechanism in millimetres.
   * @return position
   * @see #position
   */
  public double getPosition()
  {
    return position;
  }


  /**
   * Return the boolean describing whether to use the def focus position.
   * @return def
   * @see #def
   */
  public boolean isDefaultPosition()
  {
    return def;
  }
}
/*
 *    $Date: 2013-07-04 10:06:47 $
 * $RCSfile: FOCUS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/FOCUS.java,v $
 *      $Id: FOCUS.java,v 1.3 2013-07-04 10:06:47 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
