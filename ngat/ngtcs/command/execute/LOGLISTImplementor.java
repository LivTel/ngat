package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 *
 * Returns a list of all the <code>Logger</code>s used on the telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class LOGLISTImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: LOGLISTImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );


    /**
     * List of PluggableSubSystems on Telescope.
     */
    private List logList = null;


    public LOGLISTImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	logList = telescope.getLoggerList();
	System.err.println( "Loggers on this telescope :" );
	for( int i = 0; i < logList.size(); i++ )
	    {
		System.err.println( logList.get( i ) );
	    }
	commandDone.setSuccessful( true );
    }


}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: LOGLISTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/LOGLISTImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
