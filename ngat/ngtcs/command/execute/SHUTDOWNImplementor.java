package ngat.ngtcs.command.execute;
 
import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 *
 * Shutsdown the specified mechanism, or the whole telescope if the specified
 * name is <code><i>the telescope system name</i></code>,
 * <code>all</code> or <code>telescope</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class SHUTDOWNImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
        new String( "$Id: SHUTDOWNImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );


    public SHUTDOWNImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    public int calcAcknowledgeTime( Command command )
    {
	return 20000;
    }


    public void execute()
    {
	String name = ( (SHUTDOWN)command ).getSystemName();

	if( ( name.equals( telescope.getName() ) )||
	    ( name.equals( "all" ) )||
	    ( name.equals( "telescope" ) ) )
	    {
		if( !telescope.shutdownInProgress() )
		    {
			telescope.setTelescopeState( TelescopeState.IDLE );
			telescope.shutdown();
			commandDone.setSuccessful( true );
		    }
	    }
	else
	    {
		ControllableSubSystem mech = 
		    telescope.getControllableSubSystem( name );

		if( mech == null )
		    {
			returnMessage = name+": no such ControllableSubSystem";
			commandDone.setErrorMessage
			    ( name+": no such ControllableSubSystem" );
			return;
		    }

		if( !mech.shutdownInProgress() )
		    {
			if( mech.shutdown() != true )
			    {
				String errMsg = mech.getErrorMessage();
				logger.log( 1, logName, errMsg );
				commandDone.setErrorMessage( errMsg );
				return;
			    }
			commandDone.setSuccessful( true );
		    }
		else
		    {
			commandDone.setErrorMessage
			    ( "shutodwn in progress" );
		    }
	    }
	return;
    }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: SHUTDOWNImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SHUTDOWNImplementor.java,v $
 *      $Id: SHUTDOWNImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
