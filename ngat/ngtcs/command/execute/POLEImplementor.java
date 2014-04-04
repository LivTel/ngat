package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * This command alters the currently used values for the Earth's rotational
 * pole coordinates held in the Telescope's IERSData object.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class POLEImplementor extends CommandImplementor
{
  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS FIELDS.  /*                                                       */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the POLE command (3 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 3000;

  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT FIELDS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/


  /*=========================================================================*/
  /*  /*                                                                     */
  /* CLASS METHODS.  /*                                                      */
  /*  /*                                                                     */
  /*=========================================================================*/


  /*=========================================================================*/
  /*  /*                                                                     */
  /* OBJECT METHODS.  /*                                                     */
  /*  /*                                                                     */
  /*=========================================================================*/

  /**
   * Create the POLE command implementor.
   * @param ts the Telescope on which this CommandImplementor is executing
   * @param c the Command (ngat.ngtcs.command.POLE) to execute
   */
  public POLEImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   * This execute method sets the pole coordinates in the IERSData object
   * used by the AstrometryCalculator.
   */
  public void execute()
  {
    POLE p = (POLE)command;
    IERSData dat = telescope.getAstrometryCalculator().getIERSData();
    dat.setPolarMotionX( p.getX() );
    dat.setPolarMotionY( p.getY() );
    commandDone.setSuccessful( true );
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
 *    $Date: 2013-07-04 10:22:32 $
 * $RCSfile: POLEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/POLEImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
