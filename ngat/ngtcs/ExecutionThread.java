package ngat.ngtcs;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

import ngat.util.logging.Logger;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class ExecutionThread extends Thread
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
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Telescope on which this ExecutionThread will be executing commands.
   */
  protected Telescope telescope;

  /**
   * The <code>Command</code> to be executed in this thread.
   */
  protected Command command;

  /**
   * The <code>CommandImplementor</code> used to execute the
   * <code>Command</code> used to instantiate this thread.
   */
  protected CommandImplementor comImp;

  /**
   * The communicator over which the command was issued, and used to report
   * execution status.
   */
  protected Communicator communicator;

  /**
   * The execution status of the command.
   */
  protected CommandDone done = null;

  /**
   * Logger used for all <code><b>ExecutionThread</b></code>s
   */
  protected Logger logger = null;

  /**
   * Name of the Class logger.
   */
  protected String logName = null;

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
   * Constructor.
   * </p><p>
   * Create a command execution thread to perform the specified command, on
   * the specified telescope, reporting back using to the command-generator
   * using the specified communicator.
   * @param newTelescope the <code>Telescope</code> on which to execute
   * @param newCommunicator the <code>Communicator</code> to use
   * @param newCommand the <code>Command</code> to execute
   */
  public ExecutionThread( Telescope newTelescope,
			  Communicator newCommunicator,
			  Command newCommand )
  {
    telescope = newTelescope;
    communicator = newCommunicator;
    command = newCommand;
    logger = telescope.getLogger( ExecutionThread.class );
    logName = logger.getName();	
  }


  /**
   * The <code>Thread.run</code> method.
   * </p><p>
   * This method is final as it is the NGTCS protocol for handling commands.
   * If, for some reason this exact protocol is not being used, it should
   * be dealt with in the <code>Communicator</code>-implementing protocol
   * handler.
   * </p><p>
   * This (the base NGTCS command-handling protocol) is the order of
   * command execution:
   * <ul>
   * <li>
   * the relevent <code>CommandImplementor</code> is instantiated
   * </li><li>
   * the completion time for command execution is calculated and sent to the
   * communicator in an acknowledgement
   * </li><li>
   * the <code>CommandImplementor.execute()</code> method is called
   */
  public void run()
  {
    try
    {
      instantiateImplementor();
    }
    catch( Exception e )
    {
      logger.log( 1, logName, e );
      CommandDone cd = new CommandDone( command );
      cd.setReturnMessage( e.toString() );
      communicator.handleDone( cd );
      return;
    }

    Acknowledge ack = generateAcknowledge();
    communicator.handleAcknowledge( ack );
    done = processCommand();
    communicator.handleDone( done );
  }


  /**
   *
   */
  public Acknowledge generateAcknowledge()
  {
    int time;
    Acknowledge ack = new Acknowledge( command.getId(), command );

    time = comImp.calcAcknowledgeTime();
    ack.setTimeToComplete( time );

    return ack;
  }


  /**
   * Send the Acknowledgement to the Communicator to deal with.
   * </p><p>
   * This method instantiates an Acknowledge object using the reference of
   * the command to be executed. The completion time is then set after being
   * caluclated by the CommandImplementor. The Acknowledge is then sent to
   * the Communicator to deal with accordingly.
   */
  public void sendAcknowledge( Acknowledge ack )
  {
    communicator.handleAcknowledge( ack );
  }


  /**
   *
   */
  public CommandDone processCommand()
  {
    logger.log( 3, logName, "executing "+command.getClass().getName()+
		" command ["+command.getId()+"]" );
    comImp.execute();
    logger.log( 3, logName, "Command "+command.getClass().getName()+
		" ["+command.getId()+"] completed" );
    return( comImp.getDone() );
  }


  /**
   * Instantiate the implementor to perform this command.
   * </p><p>
   * The CommandImplementor is instantiated using the class name:
   * <code>command.getClass().getName()+"Implementor"</code>.
   */
  public void instantiateImplementor()
    throws Exception
  {
    Class clazz = null;
    Constructor constructor = null;

    Class argClasses[] = 
      {
	Telescope.class, Command.class
      };

    Object args[] =
      {
	telescope, command
      };

    Package p = command.getClass().getPackage();

    String commandPath = command.getClass().getName();
    String root = ngat.util.StringUtilities.getRoot( commandPath, '.' );
    String leaf = ngat.util.StringUtilities.getLeaf( commandPath, '.' );
    String impName = new String( root+".execute."+leaf+"Implementor" );
    clazz = Class.forName( impName );

    logger.log( 3, logName, "Creating "+impName );
    constructor = clazz.getConstructor( argClasses );
    comImp = (CommandImplementor)constructor.newInstance( args );
  }
}
/*
 *    $Date: 2013-07-02 13:30:06 $
 * $RCSfile: ExecutionThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/ExecutionThread.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:11:30  je
 *     Initial revision
 *
 */
