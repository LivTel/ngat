package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * This implementor sets the observing wavelength on EVERY VirtualTelescope
 * being used by a main instrument.  It will only set the wavelengths in these
 * VirtualTelescopes - any VirtualTelescopes created outside the scope of the
 * top-level Telescope object (i.e. VTs not specified in the top-level
 * configuration file, such as Autoguider VTs) will have to have their
 * wavelength set explicitly.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class WAVELENGTHImplementor extends CommandImplementor
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
	new String( "$Id: WAVELENGTHImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );

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
     * Create the WAVELENGTH command implementor using the specified thread, to
     * be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.WAVELENGTH) to execute
     */
    public WAVELENGTHImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     * Set the observing wavelength on every top-level VirtualTelescope.
     */
    public void execute()
    {
	telescope.setObservingWavelength( (WAVELENGTH)command );
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: WAVELENGTHImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/WAVELENGTHImplementor.java,v $
 *      $Id: WAVELENGTHImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $
 *     $Log: not supported by cvs2svn $
 */
