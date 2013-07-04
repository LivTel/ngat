package ngat.ngtcs.command;

/**
 * The TTL ENCLOSURE command to control the Cammell Laird Enclosure.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class ENCLOSURE extends Command
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
    new String( "$Id: ENCLOSURE.java,v 1.3 2013-07-04 10:06:45 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Description of whether this command refers to shutter1.
   */
  boolean shutter1 = false;

  /**
   * Description of whether this command refers to shutter2.
   */
  boolean shutter2 = false;

  /**
   * Description of whether this command is to close the specified
   * shutter(s).
   */
  boolean close = false;

  /**
   * Description of whether this command is to open the specified
   * shutter(s).
   */
  boolean open = false;

  /**
   * The angle at which the specified shutter(s) should be moved to.
   */
  double angle = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * This constructor takes all the arguments necessary for the
   * fully-specified command.
   * @param s the String identifier for this command
   * @param b1 boolean specifying shutter 1
   * @param b2 boolean specifying shutter 2
   * @param b3 boolean specifying whether to close the specified shutter(s)
   * @param d the angle at which to move the specified shutter(s) to
   * <p>
   * <b>NOTE:</b> if the close boolean AND the angle double are both
   * specified the actual operation that will be performed is documented in
   * the <code><b>ngat.ngtcs.command.execute.ENCLOSUREImplementor</b></code>
   * class.
   */
  public ENCLOSURE( String s, boolean b1, boolean b2, boolean b3, boolean b4,
		    double d )
  {
    super( s );
    shutter1 = b1;
    shutter2 = b2;
    open     = b3;
    close    = b4;
    angle    = d;
  }


  /**
   * Returns a boolean describing whether shutter 1 is included in this
   * command.
   * @return shutter1
   * @see #shutter1
   */
  public boolean includesShutter1()
  {
    return shutter1;
  }


  /**
   * Returns a boolean describing whether shutter 2 is included in this
   * command.
   * @return shutter2
   * @see #shutter2
   */
  public boolean includesShutter2()
  {
    return shutter2;
  }


  /**
   * Returns a boolean describing whether the <b>close</b> flag was set.
   * @return close
   * @see #close
   */
  public boolean getClose()
  {
    return close;
  }


  /**
   * Returns a boolean describing whether the <b>open</b> flag was set.
   * @return open
   * @see #open
   */
  public boolean getOpen()
  {
    return open;
  }


  /**
   * Return the angle that was set for the specified shutter(s).
   * @return angle
   * @see #angle
   */
  public double getAngle()
  {
    return angle;
  }
}
/*
 *    $Date: 2013-07-04 10:06:45 $
 * $RCSfile: ENCLOSURE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/ENCLOSURE.java,v $
 *      $Id: ENCLOSURE.java,v 1.3 2013-07-04 10:06:45 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
