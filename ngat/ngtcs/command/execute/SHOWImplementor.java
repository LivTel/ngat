package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SHOWImplementor extends CommandImplementor
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
	new String( "$Id: SHOWImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The CommandDone sub-class that will be instantiated by this
     * implemenetor.
     */
    //    protected SHOWDone showDone;

    /**
     * Telescope Site data.
     */
    protected SiteData siteData = null;

    /**
     * TelescopeIERS  data.
     */
    protected IERSData iersData = null;

    /**
     * Telescope Meteorological data.
     */
    protected MeteorologicalData metData = null;



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
     * Create the SHOW command implementor using the specified thread, to
     * be executed on the specified telescope with the specified command.
     * @param eT the ExecutionThread executing this CommandImplementor
     * @param ts the Telescope on which this CommandImplementor is executing
     * @param c the Command (ngat.ngtcs.command.SHOW) to execute
     */
    public SHOWImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     * 
     */
    public void execute()
    {
	commandDone = (CommandDone)( new SHOWDone( (SHOW)command ) );
	ShowType type = ( (SHOW)command ).getType();

	if( type == ShowType.ASTROMETRY )
	{
	    
	}
	else if( type == ShowType.CALIBRATE )
	{

	}
	else if( type == ShowType.FOCAL_STATION )
	{

	}
	else if( type == ShowType.LIMITS )
	{

	}
	else if( type == ShowType.MECHANISMS )
	{

	}
	else if( type == ShowType.METEOROLOGY )
	{

	}
	else if( type == ShowType.SOURCE )
	{

	}
	else if( type == ShowType.STATE )
	{

	}
	else if( type == ShowType.TIME )
	{

	}
	else if( type == ShowType.VERSION )
	{

	}
	else
	{
	    commandDone.setReturnMessage
		( "This Implementor does NOT support ShowType ["+
		  type.getName()+"]" );
	    return;
	}
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: SHOWImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SHOWImplementor.java,v $
 *      $Id: SHOWImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $
 *     $Log: not supported by cvs2svn $
 */
