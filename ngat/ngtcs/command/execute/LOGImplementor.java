package ngat.ngtcs.command.execute;

import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 *
 * Sets the logging level of the specified <code>Logger</code> to the level
 * specified in the command.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.3 $
 */
public class LOGImplementor extends CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: LOGImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

  /**
   * The timeout for the LOG command (3 seconds), in milliseconds
   */
  public static final int TIMEOUT = 3000;

  public LOGImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   *
   */
  public void execute()
  {
    LOG log = (LOG)command;

    String logname = log.getLogName();
    Logger logga = telescope.getLogger( logname );

    if( logga == null )
    {
      logger.log( 1, logName,
		  "No such logger as ["+logname+"]" );
      commandDone.setErrorMessage
	( "No such logger as ["+logname+"]" );
      return;
    }

    logga.setLogLevel( log.getLogLevel() );

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
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: LOGImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/LOGImplementor.java,v $
 *      $Id: LOGImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
