package ngat.ngtcs.test;

import java.io.*;
import java.net.*;
import java.util.*;

import ngat.net.*;
import ngat.util.*;
import ngat.util.logging.*;
import ngat.message.base.*;
import ngat.ngtcs.*;
import ngat.ngtcs.command.*;

/**
 * This class implements the <code>ngat.message</code> and
 * <code>ngat.net</code> method of client<->server communication for the NGTCS.
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 */
public class NGAT_NET_Communicator implements Communicator
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
   * The shutdown flag.
   */
  protected boolean shutdown = false;

  protected Logger logger;

  protected String logName;

  protected Telescope telescope;

  protected TCPServerExtension tcp;

  protected PrintWriter pw = new PrintWriter( System.err, true );

  protected Hashtable commandReplyHash = new Hashtable();

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create the <code>ngat.net</code> implementing NGTCS
   * <code>Communicator</code>
   */
  public NGAT_NET_Communicator()
  {
  }


  /**
   * Initialise this Communicator.  This reads properties from a config file,
   * falling back on default values
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    telescope = t;

    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();

    int timeout, port;

    NGATProperties np = new NGATProperties();

    try
    {
      np.load( telescope.getName()+"-"+
	       StringUtilities.getLeaf( this.getClass().getName(), '.' )
	       +".cfg" );
    }
    catch( Exception e )
    {
      logger.log( 1, logName, e.toString() );
      logger.log( 2, logName, this.getClass().getName()+
		  " : using default values" );
    }

    timeout = np.getInt( "timeout", 10000 );
    port = np.getInt( "port", 29574 );

    tcp = new TCPServerExtension( telescope.getName(), port, timeout );
    tcp.setErrorStream( pw );
    tcp.start();

    return;
  }


  /**
   * This adds the Command:TestExecutionThread key:value pair to a Hashtable
   * to allow routing of all relevant replies.
   * @param c the initial command
   * @param tet the TestExecutionThread replying to the Command-issuer
   */
  public void setReplyPath( Command c, TestExecutionThread tet )
  {
    commandReplyHash.put( c, tet );
  }


  /**
   * Handle the <code>Acknowledge</code> returned from
   * <code>telescope</code>.
   * @param ack the <code>Acknowledge</code> to handle
   */
  public void handleAcknowledge( Acknowledge ack )
  {
    Command c = ack.getCommand();
    TestExecutionThread t =
      (TestExecutionThread)( commandReplyHash.get( c ) );
    try
    {
      logger.log( 3, logName, "Sending Acknowledge" );
      t.sendAcknowledge( ack );
    }
    catch( IOException ioe )
    {
      logger.log( 1, logName, ioe );
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
    Command c = done.getCommand();
    TestExecutionThread t =
      (TestExecutionThread)( commandReplyHash.get( c ) );
    try
    {
      logger.log( 3, logName, "Sending CommandDone "+
		  done.getClass().getName() );
      t.sendDone();
    }
    catch( IOException ioe )
    {
      logger.log( 1, logName, ioe );
    }
    return;
  }


  /**
   * Return whether this Communicator is active
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
    tcp.close();
  }


  /**
   *
   */
  protected class TCPServerExtension extends TCPServer
  {
    /**
     *
     */
    protected TCPServerExtension( String name, int portNumber, int timeout )
    {
      super( name, portNumber, timeout );

      System.err.println( "TCPServer ["+name+"] listening on port "+
			  portNumber+", with timeout = "+timeout );
    }


    /**
     *
     */
    public void startConnectionThread( Socket s )
    {
      TestExecutionThread thread = new TestExecutionThread
	( s, telescope, NGAT_NET_Communicator.this );
      thread.setErrorStream( pw );
      thread.start();
    }
  }
}
/*
 * $Date: 2013-07-04 13:05:55 $
 * $RCSfile: NGAT_NET_Communicator.java,v $
 * $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/test/NGAT_NET_Communicator.java,v $
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2003/09/23 12:29:17  je
 * Changed init routine to use defualt if no config file is found
 *
 * Revision 1.2  2003/09/23 11:58:09  je
 * Added documentation.
 *
 * Revision 1.1  2003/07/01 10:13:54  je
 * Initial revision
 *
 */
