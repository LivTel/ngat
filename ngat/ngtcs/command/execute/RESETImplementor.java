package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.subsystem.*;

/**
 *
 * Resets the specified mechanism, or the whole telescope if the specified
 * name is <code><i>the telescope system name</i></code>,
 * <code>all</code> or <code>telescope</code>.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class RESETImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: RESETImplementor.java,v 1.4 2013-07-04 10:25:58 cjm Exp $" );

  /**
   * The timeout for the RESET command (30 seconds), in milliseconds
   */
  public static final int TIMEOUT = 30000;


  public RESETImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   *
   */
  public void execute()
  {
    RESET reset = (RESET)command;

    telescope.setOperationalMode( OperationalMode.IDLE );

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
	logError( "Failed to reset "+systemName+" : "+nie.toString() );
	return;
      }
    }
    else
    {
      PluggableSubSystem mechanism =
	telescope.getPluggableSubSystem( systemName );

      if( mechanism == null )
      {
	logError
	  ( "No such mechanism ["+systemName+"] : execution terminated" );
	return;
      }

      try
      {
	mechanism = telescope.getPluggableSubSystem( systemName );
	mechanism.initialise( telescope );
	commandDone.setSuccessful( true );
	return;
      }
      catch( InitialisationException nie )
      {
	logError( "Failed to reset "+systemName+" : "+nie.toString() );
	return;
      }
    }
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
 *    $Date: 2013-07-04 10:25:58 $
 * $RCSfile: RESETImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/RESETImplementor.java,v $
 *      $Id: RESETImplementor.java,v 1.4 2013-07-04 10:25:58 cjm Exp $
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
