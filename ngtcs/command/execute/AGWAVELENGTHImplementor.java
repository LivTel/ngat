package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.ags.*;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Enter the value of the effective wavelength of light used in
 * theatmospheric refraction calculation for the autoguider.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGWAVELENGTHImplementor
  extends CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   * The timeout for the AGWAVELENGTH command (3 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 3000;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                        */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                       */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public AGWAVELENGTHImplementor
    ( Telescope t, Command c )
  {
    super( t, c );
  }


  /**
   * Set the wavelength on the VirtualTelescope used by the autoguider to the
   * wavelength specified in the command argument.
   */
  public void execute()
  {
    TTL_Autoguider ag = (TTL_Autoguider)TTL_Autoguider.getInstance();

    ag.setWavelength( ( (AGWAVELENGTH)command ).getWavelength() );
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
 *    $Date: 2013-07-04 10:16:11 $
 * $RCSfile: AGWAVELENGTHImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/AGWAVELENGTHImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/09/29 12:04:32  je
 *     Added execute documentation.
 *
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
