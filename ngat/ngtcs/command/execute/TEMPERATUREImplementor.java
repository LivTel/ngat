package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Set temperature used in atmospheric refraction calculations.
 * This implementor sets the temperature in the MeteorologicalData object used
 * by the AstrometryCalculator on the specified telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class TEMPERATUREImplementor extends CommandImplementor
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
	new String( "$Id: TEMPERATUREImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

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
     * Create the TEMPERATURE command implementor using the specified thread,
     * to be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.TEMPERATURE) to execute
     */
    public TEMPERATUREImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
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
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: TEMPERATUREImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/TEMPERATUREImplementor.java,v $
 *      $Id: TEMPERATUREImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
