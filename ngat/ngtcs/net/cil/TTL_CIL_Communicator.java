package ngat.ngtcs.net.cil;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;



/**
 * Provides the command connection from TTL's CIL implementation to the NGTCS.
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 *
 */
public class TTL_CIL_Communicator implements Communicator
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: TTL_CIL_Communicator.java,v 1.1 2003-09-19 16:00:50 je Exp $" );

  /**
   * The telescope for which this is the Communicator.
   */
  protected Telescope telescope;

  /**
   * The shutdown flag.
   */
  protected boolean shutdown = false;

  /**
   * Logger to log to.
   */
  protected Logger logger = null;

  /**
   * Name of this Communicator's Logger.
   */
  protected String logName = null;

  /**
   * The TTL_CIL communication interface.
   */
  protected TTL_CIL cil = new TTL_CIL();

  /**
   * The initialisation flag.
   */
  protected boolean initialised = false;


  /**
   *
   */
  //    protected CIL_Translator translator;

  /**
   * Constructor.
   * Set the <code>Telescope</code> on which this Communicator is to be used.
   * The specified <code>Translator</code> will be used to translate all
   * incoming messages into NGTCS <code>Commands</code>.
   */
  public TTL_CIL_Communicator()
  {
  }


  /**
   * Initialise the Communicator.
   *
   * The TTL_CIL_Communicator reads the remote host address and both local
   * and remote ports needed for all communication from the configuration
   * file, <code>TTL_CIL_Communicator.cfg</code>
   * @param t the telescope for which this is the Communicator
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    if( initialised )
    {
      System.err.println( "TTL_CIL ["+this+"] already initialised" );
      return;
    }

    telescope = t;

    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();

    NGATProperties np = new NGATProperties();

    try
    {
      np.load( telescope.getName()+"-"+StringUtilities.getLeaf
	       ( this.getClass().getName(), '.' )+".cfg" );

      int localPort = np.getInt( "localPort" );
      int remotePort = np.getInt( "remotePort" );
      InetAddress remoteHost = np.getInetAddress( "remoteHost" );

      cil.initialise( localPort, remoteHost, remotePort );

      new Thread( cil ).start();
    }
    catch( Exception e )
    {
      logger.log( 1, logName, e );
      throw new InitialisationException( e );
    }
  }


  /**
   * This <code>Runnable</code> implementation is started to listen for
   * any incoming commands.
   *
   * Upon receiving a command an <code>ExecutionThread</code> is instantiated
   * to execute and monitor the command.
   *
   * This method should be devloped further to implement a protocol that
   * can be implemented using the <code>process<i>Message</i></code>
   * below, and made final in the super class.
   */
  public void run()
  {
    TTL_CIL_Message msg;
    Command cmd;

    while( shutdown == false )
    {
      msg = null;
      cmd = null;
      try
      {
	msg = (TTL_CIL_Message)cil.receiveMessage();

	// add Mesasge to a Hashtable(?) of mesages being dealt
	// with for reply with correct headers
      }
      catch( java.io.IOException ioe )
      {
	logger.log( 1, logName, ioe );
      }

      if( msg == null )
      {
	logger.log( 1, logName, "null CIL_Message received" );
	// reply with error
      }
      else if( msg.getMessageClass() ==
	       TTL_CIL_MessageClass.E_CIL_ERR_CLASS )
      {
	logger.log( 1, logName,
		    "ERROR message class received" );
	// reply with error
      }
      //else if ( ( cmd = translator.translate( msg ) ) == null ) 
      else if( cmd == null )
      {
	logger.log( 1, logName,
		    "no translation available for message" );
	// translate failed - send correct TTL_CIL_Message
      }

      new ExecutionThread( telescope, this, cmd ).start();
    }
  }


  /**
   * Handle the <code>Acknowledge</code> returned from
   * <code>telescope</code>.
   * @param ack the <code>Acknowledge</code> to handle
   */
  public void handleAcknowledge( Acknowledge ack )
  {
    TTL_CIL_Message msg = null;
    //	TTL_CIL_Message msg = translator.translate( ack );

    try
    {
      cil.sendMessage( msg );
    }
    catch( IOException ioe )
    {

    }
    return;
  }


  /**
   * Handle the <code>Done</code> returned from
   * <code>telescope</code>.
   * @param done the <code>Done</code> to handle
   */
  public void handleDone( CommandDone done )
  {
    TTL_CIL_Message msg = null;
    //	TTL_CIL_Message msg = translator.translate( done );

    try
    {
      cil.sendMessage( msg );
    }
    catch( IOException ioe )
    {

    }
    // remove msg from Hashtable(?) after dealt with
    return;
  }


  /**
   *
   */
  public boolean isActive()
  {
    return( !shutdown );
  }


  /**
   * Set the shutdown flag and close the TTL_CIL.
   */
  public void shutdown()
  {
    shutdown = true;
    cil.shutdown();
  }
}
/*
 *    $Date: 2003-09-19 16:00:50 $
 * $RCSfile: TTL_CIL_Communicator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_Communicator.java,v $
 *      $Id: TTL_CIL_Communicator.java,v 1.1 2003-09-19 16:00:50 je Exp $
 *     $Log: not supported by cvs2svn $
 */
