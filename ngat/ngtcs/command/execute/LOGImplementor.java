package ngat.ngtcs.command.execute;

import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 *
 * Sets the logging level of the specified <code>Logger</code> to the level
 * specified in the command.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class LOGImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: LOGImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );



    public LOGImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	LOG log = (LOG)command;

	String logname = log.getLogName();
	Logger logga = telescope.getLogger( logname );

	if( logga == null )
	    {
		logger.log( 1, logName,
			    "No such logger as [ "+logname+" ]" );
		commandDone.setErrorMessage
		    ( "No such logger as [ "+logname+" ]" );
		return;
	    }

	logga.setLogLevel( log.getLogLevel() );

	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: LOGImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/LOGImplementor.java,v $
 *      $Id: LOGImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
