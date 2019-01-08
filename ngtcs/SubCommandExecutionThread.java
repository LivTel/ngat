package ngat.ngtcs;

import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

import ngat.util.logging.Logger;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

/**
 * This class is to look after execution of commands within commands to enable
 * the macro commands such as CALIBRATE, and also to utilise already-written
 * commands within larger, more complex commands such as AGMOVE compared to
 * AGRADIAL.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SubCommandExecutionThread extends ExecutionThread
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
   * Create a sub-command execution thread to perform the specified command, on
   * the specified telescope, reporting back using to the command-generator
   * using the specified communicator.
   * @param newTelescope the <code>Telescope</code> on which to execute
   * @param newCommunicator the <code>Communicator</code> to use
   * @param newCommand the <code>Command</code> to execute
   */
  public SubCommandExecutionThread( Telescope newTelescope,
				    Communicator newCommunicator,
				    Command newCommand )
  {
    super( newTelescope, newCommunicator, newCommand );
  }


  /**
   * The <code>Thread.run</code> method.
   * </p><p>
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
      done = new CommandDone( command );
      cd.setErrorMessage( e.toString() );
      return;
    }

    logger.log( 3, logName, "executing "+command.getClass().getName()+
		" command ["+command.getId()+"]" );

    comImp.execute();

    logger.log( 3, logName, "Command "+command.getClass().getName()+
		" ["+command.getId()+"] completed" );

    done = comImp.getDone();
  }


  /**
   * Return the CommandDone result of the execution.
   */
  public CommandDone getDone()
  {
    return( done );
  }
}
/*
 *    $Date: 2013-07-02 15:27:17 $
 * $RCSfile: SubCommandExecutionThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/SubCommandExecutionThread.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
