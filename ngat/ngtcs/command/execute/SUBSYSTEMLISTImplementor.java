package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * Returns a list of all <code>PluggableSubSystem</code> implementing objects used on
 * the telescope.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class SUBSYSTEMLISTImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: SUBSYSTEMLISTImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );


    /**
     * List of PluggableSubSystems on Telescope.
     */
    private List subSysList = null;


    public SUBSYSTEMLISTImplementor( ExecutionThread eT, Telescope ts,
				     Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	subSysList = telescope.getPluggableSubSystemList();
	System.err.println( "Mechansims on this telescope :" );
	for( int i = 0; i < subSysList.size(); i++ )
	    {
		System.err.println( subSysList.get( i ) );
	    }
	commandDone.setSuccessful( true );
    }


}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: SUBSYSTEMLISTImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SUBSYSTEMLISTImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 */
