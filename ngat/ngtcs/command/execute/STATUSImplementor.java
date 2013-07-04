package ngat.ngtcs.command.execute;

import java.util.List;
import java.util.Vector;
import java.util.StringTokenizer;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;
 
/**
 *
 * The <code>STATUS</code> command obtains the status of the telescope and
 * all sub-systems and mechanisms.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class STATUSImplementor extends CommandImplementor 
{
    /**
     * String used to identify RCS revision details.
  */
    public static final String rcsid =
	new String( "$Id: STATUSImplementor.java,v 1.4 2013-07-04 10:28:24 cjm Exp $" );

  /**
   * The timeout for the STATUS command (10 seconds), in milliseconds
   */
  public static final int TIMEOUT = 10000;

    protected String mechanismName = null;

    protected ControllableSubSystem mechanism = null;

    protected TelescopeStatus telescopeStatus = null;

    public STATUSImplementor( Telescope ts, Command c )
    {
	super( ts, c );
    }


    /**
     *
  */
    public void execute()
    {
	commandDone = (CommandDone)( new STATUSDone( (STATUS)command ) );
	telescopeStatus = (TelescopeStatus)telescope.getStatus();
	( (STATUSDone)( commandDone ) ).addTelescopeStatus( telescopeStatus );

	List mechList = telescope.getControllableSubSystemList();

	for( int i = 0; i < mechList.size(); i++ )
	    {
		String mechName = (String)( mechList.get( i ) );
		mechanism = telescope.getControllableSubSystem( mechName );
		if( !( mechanism instanceof Mount ) )
		    {
			( (STATUSDone)( commandDone ) ).
			  addStatus( mechanism.getStatus() );
		    }
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
 *    $Date: 2013-07-04 10:28:24 $
 * $RCSfile: STATUSImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/STATUSImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
