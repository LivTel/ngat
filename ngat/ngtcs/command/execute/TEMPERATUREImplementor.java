package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Set temperature used in atmospheric refraction calculations.
 * This implementor sets the temperature in the MeteorologicalData object used
 * by the AstrometryCalculator on the specified telescope.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TEMPERATUREImplementor extends CommandImplementor
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
   * The timeout for the TEMPERATURE command (3 seconds), in milliseconds.
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
     * Create the TEMPERATURE command implementor using the specified thread,
     * to be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.TEMPERATURE) to execute
  */
    public TEMPERATUREImplementor( Telescope ts, Command c )
    {
	super( ts, c );
    }


    /**
     * This execute method sets the temperature in the MeteorologicalData
     * object used by the AstrometryCalculator.
  */
    public void execute()
    {
	telescope.getAstrometryCalculator().getMeteorologicalData().
	    setTemperatureKelvin( ( (TEMPERATURE)command ).getKelvin() );
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
 *    $Date: 2013-07-04 10:29:33 $
 * $RCSfile: TEMPERATUREImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/TEMPERATUREImplementor.java,v $
 *      $Id$
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
