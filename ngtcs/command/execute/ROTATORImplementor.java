package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Switch the rotator into different mode and/or move it to aposition.
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class ROTATORImplementor extends CommandImplementor
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
   * The timeout for the ROTATOR command (300 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 300000;

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


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public ROTATORImplementor( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    ROTATOR r = (ROTATOR)command;
    telescope.setRotatorMode( r.getRotatorMode() );
    telescope.setPositionAngle( r.getPositionAngle() );
    commandDone.setSuccessful( true );
    return;
  }


  /**
   * Return the default timeout for this command execution.
   * @return TIMEOUT
   * @see #TIMEOUT
   */
  public int calcAcknowledgeTime()
  {
    return( TIMEOUT );
  }
}
/*
 *    $Date: 2013-07-04 10:26:00 $
 * $RCSfile: ROTATORImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/ROTATORImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
