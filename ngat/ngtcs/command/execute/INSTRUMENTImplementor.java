package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Configure the telescope and TCS for a particular instrument. Thiscommand
 * will set the focal station, rotator, default focus, fold mirror position
 * and initialise the autoguider.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class INSTRUMENTImplementor extends CommandImplementor
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
    new String( "$Id: INSTRUMENTImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the INSTRUMENT command (300 seconds), in milliseconds.
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
  public INSTRUMENTImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   *
   */
  public void execute()
  {
    INSTRUMENT i = (INSTRUMENT)command;
    String port = i.getPortName();

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
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: INSTRUMENTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/INSTRUMENTImplementor.java,v $
 *      $Id: INSTRUMENTImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/09/19 16:10:15  je
 *     Initial revision
 *
 */
