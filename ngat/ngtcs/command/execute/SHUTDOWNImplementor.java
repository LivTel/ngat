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
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class SHUTDOWNImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: SHUTDOWNImplementor.java,v 1.4 2013-07-04 10:28:14 cjm Exp $" );

  /**
   * The timeout for the SHUTDOWN command (not applicable - set to
   * <code>Integer.MAX_VALUE</code> milliseconds).
   */
  public static final int TIMEOUT = Integer.MAX_VALUE;


  public SHUTDOWNImplementor( Telescope ts, Command c )
  {
    super( ts, c );
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
	telescope.setOperationalMode( OperationalMode.IDLE );
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
	String err = new String
	  ( "No such ControllableSubSystem ["+name+
	    "] : execution terminated" );
	logger.log( 1, logName, err );
	commandDone.setErrorMessage( err );
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
	commandDone.setReturnMessage( "shutodwn in progress" );
      }
    }
    return;
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
 *    $Date: 2013-07-04 10:28:14 $
 * $RCSfile: SHUTDOWNImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SHUTDOWNImplementor.java,v $
 *      $Id: SHUTDOWNImplementor.java,v 1.4 2013-07-04 10:28:14 cjm Exp $
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
