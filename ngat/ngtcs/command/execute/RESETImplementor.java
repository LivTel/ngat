package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Resets the specified mechanism, or the whole telescope if the specified
 * name is <code><i>the telescope system name</i></code>,
 * <code>all</code> or <code>telescope</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class RESETImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: RESETImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );

    /**
     * RESET command this is to implement.
     */
    private RESET reset;


    public RESETImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	reset = (RESET)command;

	telescope.setTelescopeState( TelescopeState.IDLE );

	String systemName = reset.getSystemName();

	if( ( systemName.equals( telescope.getName() ) ) || 
	    ( systemName.equalsIgnoreCase( "all" ) ) ||
	    ( systemName.equalsIgnoreCase( "telescope" ) ) )
	    {
		try
		    {
			telescope.initialise();
			commandDone.setSuccessful( true );
			return;
		    }
		catch( InitialisationException nie )
		    {
			logger.log( 1, logName, nie );
			commandDone.setErrorMessage
			    ( nie.toString() );
			return;
		    }
	    }

	java.util.List mechList = telescope.getPluggableSubSystemList();
	PluggableSubSystem mechanism;

	for( int i = 0; i < mechList.size(); i++ )
	    {
		if( systemName.equals( (String)( mechList.get( i ) ) ) )
		    {
			try
			    {
				mechanism = telescope.getPluggableSubSystem
				    ( systemName );
				mechanism.initialise( telescope );
				commandDone.setSuccessful( true );
				return;
			    }
			catch( InitialisationException nie )
			    {
				logger.log( 1, logName, nie );
				commandDone.setErrorMessage
				    ( nie.toString() );
				return;
			    }
		    }
	    }
    }
}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: RESETImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/RESETImplementor.java,v $
 *      $Id: RESETImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $
 *     $Log: not supported by cvs2svn $
 */
