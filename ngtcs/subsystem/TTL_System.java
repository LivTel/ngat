package ngat.ngtcs.subsystem;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.net.cil.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public abstract class TTL_System implements PluggableSubSystem
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
   *
   */
  protected Telescope telescope;

  /**
   *
   */
  protected Logger logger;

  /**
   *
   */
  protected String logName;

  /**
   * The TTL_Package enumeration of this TTL_System.  All TTL_Package
   * enumerations are listed in ngat.ngtcs.common.TTL_Package.
   * @see ngat.ngtcs.subsystem.TTL_Package
   */
  protected TTL_Package ttlPackage = null;

  /**
   *
   */
  protected TTL_CIL txCIL = null;

  /**
   *
   */
  protected TTL_CIL rxCIL = null;

  /**
   * Boolean describing the initialisation state of this object.
   */
  protected boolean initialised = false;

  /**
   *
   */
  protected String errMsg = "Error!";

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
   *
   */
  protected TTL_System( TTL_Package p )
  {
    ttlPackage = p;
  }


  /**
   * Return the TTL_Package ID for this TTL_System.
   * @return ttlPackage
   * @see #ttlPackage
   */
  public TTL_Package getPackage()
  {
    return ttlPackage;
  }


  /**
   *
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    initialised = false;
    telescope = t;
    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();

    // system specific initialisation
    _initialise( t );

    initialised = true;
  }


  /**
   *
   */
  protected abstract void _initialise() throws InitialisationException;


  /**
   *
   */
  protected NGATProperties getProperties() throws InitialisationException
  {
    NGATProperties np = new NGATProperties();
    try
    {
      np.load( telescope.getName()+"-"+logName+".cfg" );
    }
    catch( Exception e )
    {
      error( "Could not get "+logName+" properties because: "+e );
      throw new InitialisationException( errMsg );
    }
    return np;
  }


  /**
   *
   */
  public void initialiseCIL( NGATProperties np, String s, TTL_CIL c )
    throws InitialisationException
  {
    try
    {
      int localPort = np.getInt( s+"localPort" );
      int remotePort = np.getInt( s+"remotePort" );
      InetAddress remoteHost = np.getInetAddress( s+"remoteHost" );

      c.initialise( localPort, remoteHost, remotePort );

      new Thread( c ).start();
    }
    catch( Exception e )
    {
      error( e.toString() );
      throw new InitialisationException( errMsg );
    }
  }


  /**
   *
   */
  public boolean isInitialised()
  {
    return initialised;
  }


  /**
   *
   */
  public void setLogLevel( int i )
  {
    logger.setLogLevel( i );
  }


  /**
   *
   */
  public TTL_SystemState getProcState()
    throws TTL_SystemException
  {
    return( (TTL_SystemState)
	    ( TTL_SystemState.getReference
	      ( getValue( TTL_GenericData.D_MCP_PROC_STATE ).getValue() ) ) );
  }


  /**
   *
   */
  public TTL_AuthState getAuthState()
    throws TTL_SystemException
  {
    return( (TTL_AuthState)
	    ( TTL_AuthState.getReference
	      ( getValue( TTL_GenericData.D_MCP_AUTH_STATE ).getValue() ) ) );
  }


  /**
   *
   */
  public double getAppVersion()
    throws TTL_SystemException
  {
    return( (double)
	    ( getValue( TTL_GenericData.D_MCP_APP_VERSION ).getValue() )
	    / 1000.0 );
  }


  /**
   *
   */
  public void setSysRequest( TTL_SystemRequest r )
    throws TTL_SystemException
  {
    TTL_DataValue v =
      new TTL_DataValue( TTL_GenericData.D_MCP_SYS_REQUEST, r.getInt() );
    setValue( v );
  }


  /**
   *
   */
  public TTL_DataValue getValue( TTL_DataType t )
    throws TTL_SystemException
  {
    TTL_DataUnit units = null;
    Timestamp timestamp = null;
    int value = 0;
    String errMsg;
    CIL_Message msg, reply;


      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  TTL_CIL_GenericService.E_MCP_GET_1.getInt(),
	  0, baos.toByteArray() );

      reply = sendCIL_Message( msg );

    return( new TTL_DataValue( t, value, units, timestamp ) );
  }


  /**
   *
   */
  public void setValue( TTL_DataValue v )
    throws TTL_SystemException
  {
    CIL_Message msg, reply;
    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream( baos );

      // write OID
      dos.writeInt( v.getType().getInt() );
      // write error
      dos.writeInt( 0 );
      // write data type - 4 or 8 byte data
      //
      // NEEDS CHANGING!
      //
      dos.writeInt( 0 );
      // write high bytes (5-8)
      dos.writeInt( 0 );
      // write low bytes (1-4)
      dos.writeInt( v.getValue() );
      // write timestamp seconds
      dos.writeInt( 0 );
      // write timestamp nanoseconds
      dos.writeInt( 0 );
      // write units
      dos.writeInt( 0 );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  TTL_CIL_GenericService.E_MCP_SET_1.getInt(),
	  0, baos.toByteArray() );

      reply = sendCIL_Message( msg );
    }
    catch( IOException e )
    {
      throw new TTL_SystemException( e.toString() );
    }
  }


  /**
   *
   */


  /**
   *
   */
  protected CIL_Message sendCIL_Message( CIL_Message msg )
    throws TTL_SystemException    
  {
    try
    {
      txCIL.sendMessage( msg );
      return( rxCIL.getReply( msg ) );
    }
    catch( java.io.IOException e )
    {
      String err = new String
	( ttlPackage.getName()+": Failed to send CIL message: "+e.toString() );
      logger.log( 1, logName, err );
      throw new TTL_SystemException( err );
    }

    // parse message and check for error.
  }


  /**
   *
   */
  protected void error( String s )
  {
    errMsg = new String( s );
    logger.log( 1, logName, errMsg );
  }

  /**
   *
   */
  public String getErrorMessage()
  {
    return errMsg;
  }
}
/*
 *    $Date: 2013-07-04 10:56:55 $
 * $RCSfile: TTL_System.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_System.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 */
