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
 * @version $Revision: 1.2 $
 */
public abstract class CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: CommandImplementor.java,v 1.2 2003-09-22 13:24:36 je Exp $" );

  /**
   * Default timeout of 10 seconds for each command, in milliseconds.
   */
  public final int TIMEOUT = 10000;

  /**
   * Logger for each CommandImplementor.
   */
  protected Logger logger;

  /**
   *
   */
  protected String logName;

  /**
   *
   */
  protected String name;

  /**
   *
   */
  protected Telescope telescope;

  /**
   *
   */
  protected Command command;

  /**
   *
   */
  protected CommandDone commandDone;

  /**
   *
   */
  protected ExecutionThread executionThread;

  /**
   * Number of milliseconds slept (<code>Thread.sleep()</code>) during
   * execution of this command
   */
  protected int slept = 0;

  /**
   *
   */
  protected String returnMessage = "";

  /**
   *
   */
  public CommandImplementor( ExecutionThread eT, Telescope ts, Command c )
  {
    telescope = ts;
    command = c;
    executionThread = eT;
    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();
    commandDone = new CommandDone( command );
  }


  /**
   *
   */
  public abstract void execute();


  /**
   * Returns the default timeout of 10 seconds.
   * </p><p>
   * This method should be over-ridden by the actual CommandImplementor.
   */
  public int calcAcknowledgeTime()
  {
    return 10000;
  }


  /**
   *
   */
  public CommandDone getDone()
  {
    return (CommandDone)( commandDone );
  }


  /**
   * Return the Telescope on which this CommandImplementor is operating.
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
}
/*
 *    $Date: 2003-09-22 13:24:36 $
 * $RCSfile: CommandImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/CommandImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
