package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * Deliver Telescope and TCS status information to an RCS.Includes
 * information necessary to build a FITS header.
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
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
    public static final String rcsid =
	new String( "$Id: SHOWImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the SHOW command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

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
    public SHOWImplementor( Telescope ts, Command c )
    {
	super( ts, c );
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
 * $RCSfile: SHOWImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SHOWImplementor.java,v $
 *      $Id: SHOWImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
