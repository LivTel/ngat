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
 * @version $Revision: 1.3 $
 */
public abstract class CommandImplementor
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: CommandImplementor.java,v 1.3 2003-09-26 09:58:41 je Exp $" );

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
   * Number of milliseconds slept (<code>Thread.sleep()</code>) during
   * execution of this command
   */
  protected int slept = 0;

  /**
   *
   */
  //  protected String returnMessage = "";

  /**
   *
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
   *
   */
  public abstract void execute();


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

  /**
   * Returns the timeout for the specific command implementation, in ms.
   */
  public abstract int calcAcknowledgeTime();
}
/*
 *    $Date: 2003-09-26 09:58:41 $
 * $RCSfile: CommandImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/CommandImplementor.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
