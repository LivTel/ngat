package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * To tell the TCS the relative humidity levels to use in the
 * refractioncalculations.
 * This implementor sets the humidity in the MeteorologicalData object used by
 * the AstrometryCalculator on the specified telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class HUMIDITYImplementor extends CommandImplementor
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: HUMIDITYImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Create the HUMIDITY command implementor using the specified thread, to
     * be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.HUMIDITY) to execute
     */
    public HUMIDITYImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     * This execute method sets the humidity in the MeteorologicalData object
     * used by the AstrometryCalculator.
     */
    public void execute()
    {
	telescope.getAstrometryCalculator().getMeteorologicalData().
	    setHumidity( ( (HUMIDITY)command ).getHumidity() );
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: HUMIDITYImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/HUMIDITYImplementor.java,v $
 *      $Id: HUMIDITYImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
