package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Enter the value of the wavelength of light used in the calculationof
 * atmospheric refraction.
 * Enter the value of the wavelength of light used in the calculationof
 * atmospheric refraction.
 * This implementor sets the observing wavelength on EVERY VirtualTelescope
 * being used by a main instrument.  It will only set the wavelengths in these
 * VirtualTelescopes - any VirtualTelescopes created outside the scope of the
 * top-level Telescope object (i.e. VTs not specified in the top-level
 * configuration file, such as Autoguider VTs) will have to have their
 * wavelength set explicitly.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class WAVELENGTHImplementor extends CommandImplementor
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
	new String( "$Id: WAVELENGTHImplementor.java,v 1.4 2013-07-04 10:30:23 cjm Exp $" );

  /**
   * The timeout for the WAVELENGTH command (3 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 3000;

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
     * Create the WAVELENGTH command implementor using the specified thread, to
     * be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.WAVELENGTH) to execute
  */
    public WAVELENGTHImplementor( Telescope ts, Command c )
    {
	super( ts, c );
    }


    /**
     * Set the observing wavelength on every top-level VirtualTelescope.
  */
    public void execute()
    {
	telescope.setObservingWavelength( (WAVELENGTH)command );
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
 *    $Date: 2013-07-04 10:30:23 $
 * $RCSfile: WAVELENGTHImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/WAVELENGTHImplementor.java,v $
 *      $Id: WAVELENGTHImplementor.java,v 1.4 2013-07-04 10:30:23 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
