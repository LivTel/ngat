package ngat.ngtcs.command.execute;

import ngat.util.logging.Logger;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 *
 * Super-class for all executives of <code>Command</code> objects that are to
 * be implemented by the telescope control system.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.5 $
 */
public abstract class CommandImplementor
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: CommandImplementor.java,v 1.5 2003-09-29 13:12:18 je Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Logger for each CommandImplementor.
   */
  protected Logger logger;

  /**
   * The CommandImplementor sub-class name used for logging.  All object from
   * the same CommandImplementor sub-class will log to the same logger.
   */
  protected String logName;

  /**
   * The telescope on which the command is executed
   */
  protected Telescope telescope;

  /**
   * The command sub-class to execute.
   */
  protected Command command;

  /**
   * A generic CommandDone object for returning the command success/failure and
   * details.  If specific commands require a CommandDone sub-class they should
   * be assigned in either the constructor or execute method.
   */
  protected CommandDone commandDone;

  /**
   * Number of milliseconds slept (<code>Thread.sleep()</code>) during
   * execution of this command
   */
  protected int slept = 0;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The top-level CommandImplementor constructor assigns the command to
   * execute as well as the telescope for which it is being executed.  Also,
   * the logging details for the concrete sub-class are assigned and a
   * generic CommandDone is created.
   * @param ts the telescope on which this command is to be executed
   * @param c the command to implement execution of
   */
  public CommandImplementor( Telescope ts, Command c )
  {
    telescope = ts;
    command = c;
    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();
    commandDone = new CommandDone( command );
  }


  /**
   * Return the CommandDone (or sub-class) that resulted from this
   * command implementation.
   * @return commandDone
   * @see #commandDone
   */
  public CommandDone getDone()
  {
    return (CommandDone)( commandDone );
  }


  /**
   * Return the Telescope on which this CommandImplementor is executing.
   * @return telescope
   */
  public Telescope getTelescope()
  {
    return telescope;
  }


  /**
   * Return the Command for which this CommandImplementor is the executive.
   * @return command
   */
  public Command getCommand()
  {
    return command;
  }


  /**
   * Returns the timeout for the specific command implementation, in ms.
   */
  public abstract int calcAcknowledgeTime();


  /**
   * The command-specific execution method.
   */
  public abstract void execute();


  /**
   * Sleep the current thread for the specified time (in milliseconds),
   * increment the slept variable accordingly and log any InterruptedExceptions
   * to the logger.
   * @param ms the time to sleep, in ms
   */
  protected void sleep( int ms )
  {
    try
    {
      Thread.sleep( ms );
    }
    catch( InterruptedException ie )
    {
      logger.log( 1, logName, ie.toString() );
    }
    slept += ms;
  }
}
/*
 *    $Date: 2003-09-29 13:12:18 $
 * $RCSfile: CommandImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/CommandImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.4  2003/09/29 11:50:28  je
 *     Added documentation.
 *
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
