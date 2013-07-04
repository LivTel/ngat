package ngat.ngtcs.test;

import java.io.*;
import java.net.*;

import ngat.net.*;
import ngat.message.base.*;
import ngat.util.logging.Logger;

import ngat.ngtcs.*;
import ngat.ngtcs.command.*;
import ngat.ngtcs.command.execute.*;

/**
 * This class is a wrapper for the ngat.net.TCPServerConnectionThread to 
 * implement the JMS protocol for NGTCS command communication.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.4 $
 */
public class TestExecutionThread extends TCPServerConnectionThread
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
    new String( "$Id: TestExecutionThread.java,v 1.4 2013-07-04 13:07:05 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The NGTCS ExecutionThread for which this is a wrapper.
   */
  protected ExecutionThread executor;

  /**
   * The Telescope on which commands will be executed.
   */
  protected Telescope telescope;

  /**
   * The Communicator used to talk to the NGTCS.
   */
  protected Communicator communicator;

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
   * Create a TestExecutionThread wityh the specified Socket for the
   * super-class.  This thread will command the specified Telescope by using
   * the specified Communicator.
   * @param s the Socket over which to communicate
   * @param t the Telescope on which to execute commands
   * @param c the COmmunicator used to talk to the NGTCS
   */
  public TestExecutionThread( Socket s, Telescope t, Communicator c )
  {
    super( s );
    telescope = t;
    communicator = c;
    logger = telescope.getLogger( TestExecutionThread.class );
    logName = logger.getName();	
  }


  /**
   * Over-ride the super-class method to produce the
   * <code><b>ngat.net.ACK</b></code> object form the NGTCS ExecutionThread
   * as per the ngat.net protocol.
   * @return a generated <code><b>ngat.net.ACK</b></code>
   */
  protected ACK calculateAcknowledgeTime()
  {
    return( executor.generateAcknowledge() );
    //    Acknowledge a = executor.generateAcknowledge();
    //    return( (ACK)a );
  }


  /**
   *
   */
  public void run()
  {
    // if so socket stop
    if( socket == null )
      return;
    try
    {
      // attempt to get object input/output streams.
      getInputStream();
      getOutputStream();

      // try to get command from client
      getCommandFromClient();

      // create ExecutionThread to do the work
      executor = new ExecutionThread
	( telescope, communicator, (Command)command );

      // enable reply by Communicator
      ( (NGAT_NET_Communicator)( communicator ) ).
	setReplyPath( (Command)command, this );

      // copy of ExecutionThread's run() method
      try
      {
	executor.instantiateImplementor();
      }
      catch( Exception e )
      {
	processError( e.toString() );
	return;
      }

      Acknowledge ack = executor.generateAcknowledge();
      communicator.handleAcknowledge( ack );
      processCommand();
      communicator.handleDone( (CommandDone)done );

      // close the streams etc.
      close();
    }
    catch( IOException e )
    {
      socket = null;
      processError( e.toString() );
    }
    catch( ClassNotFoundException e )
    {
      socket = null;
      processError( e.toString() );
    }
  }


  /**
   * Over-ride the method in the super-class to call the NGTCS
   * ExecutionThread <code><b>execute</b></code> method.
   */
  public void processCommand()
  {
    done = executor.processCommand();
  }


  /**
   * Over-ride the method in the super-class to log the specified error, create
   * a CommandDone, set the error message in the CommandDone, and return it.
   * @param err the error message
   */
  protected void processError( String err )
  {
    logger.log( 1, logName, err );

    done = new CommandDone( (Command)command );
    ( (CommandDone)done ).setReturnMessage( err );

    communicator.handleDone( (CommandDone)done );
    return;
  }
}
/*
 *    $Date: 2013-07-04 13:07:05 $
 * $RCSfile: TestExecutionThread.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/TestExecutionThread.java,v $
 *      $Id: TestExecutionThread.java,v 1.4 2013-07-04 13:07:05 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/23 11:48:45  je
 *     Documentation change and replaced System.err reporting with logger.log
 *
 *     Revision 1.2  2003/09/19 16:10:27  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *     Revision 1.1  2003/07/01 10:13:54  je
 *     Initial revision
 *
 */
