package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Set the time to be used by subsequent calls to the Timer mechanism used by
 * the <code>Telescope</code> object.  This can be used to implement a
 * simulation time for testing, either static or dynamic.  In the case of
 * dynamic simulation subsequent calls to the Timer object will have times
 * beginning at the simulation time specified in the <code>TIME</code> command
 * incremented by the increment specified in that command.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TIMEImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: TIMEImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );


    public TIMEImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	TIME timeCommand;
	Timer timer;
	long secs;
	long nanosecs;

	timeCommand = (TIME)command;
	timer = telescope.getTimer();

	secs = timeCommand.getSeconds();
	nanosecs = timeCommand.getNanoseconds();

	if( ( secs == 0 )&&( nanosecs == 0 ) )
	    {
		timer.setEmulate( false );
		commandDone.setSuccessful( true );
		return;
	    }

	timer.setEmulate( true );
	timer.setTime( secs, nanosecs );
	timer.setStatic( timeCommand.isStaticTime() );
	timer.setTimestep( timeCommand.getTimestep() );

	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: TIMEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/TIMEImplementor.java,v $
 *     $Log: not supported by cvs2svn $
*/
