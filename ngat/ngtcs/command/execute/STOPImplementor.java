package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 * Stops the named mechanism.
 * The STOP command is implemented by setting the telescope state to IDLE.
 * This is because all telescope operation is (or <i>should</i> be) performed
 * under conditional testing of this state.
 *
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class STOPImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: STOPImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );


    /**
     * Create the STOPImplementor.
     */
    public STOPImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     * Set the telescope state to IDLE.
     */
    public void execute()
    {
	telescope.setTelescopeState( TelescopeState.IDLE );
	commandDone.setSuccessful( true );
    }
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: STOPImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/STOPImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
