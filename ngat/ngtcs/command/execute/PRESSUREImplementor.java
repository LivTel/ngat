package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * This implementor sets the pressure in the MeteorologicalData object used by
 * the AstrometryCalculator on the specified telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class PRESSUREImplementor extends CommandImplementor
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
	new String( "$Id: PRESSUREImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );

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
     * Create the PRESSURE command implementor using the specified thread, to
     * be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.PRESSURE) to execute
     */
    public PRESSUREImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     * This execute method sets the pressure in the MeteorologicalData object
     * used by the AstrometryCalculator.
     */
    public void execute()
    {
	telescope.getAstrometryCalculator().getMeteorologicalData().
	    setPressure( ( (PRESSURE)command ).getPressure() );
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: PRESSUREImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/PRESSUREImplementor.java,v $
 *      $Id: PRESSUREImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $
 *     $Log: not supported by cvs2svn $
 */
