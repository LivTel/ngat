package ngat.ngtcs.command.execute;

import java.util.List;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * The <code>STATE</code> command returns the current
 * <code>TelescopeState</code> <code>and SoftwareState</code> of the NGTCS
 * application.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class STATEImplementor extends CommandImplementor
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: STATEImplementor.java,v 1.1 2003-07-01 10:12:55 je Exp $" );


    public STATEImplementor( ExecutionThread eT, Telescope ts, Command c )
    {
	super( eT, ts, c );
    }


    /**
     *
     */
    public void execute()
    {
	TelescopeState telescopeState = telescope.getTelescopeState();
	SoftwareState softwareState = telescope.getSoftwareState();
	returnMessage = ( "\n software : "+softwareState+
			  "\ntelescope : "+telescopeState+"\n" );
	commandDone.setSuccessful( true );
    }


}
/*
 *    $Date: 2003-07-01 10:12:55 $
 * $RCSfile: STATEImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/STATEImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 */
