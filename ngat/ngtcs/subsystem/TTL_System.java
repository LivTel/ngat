package ngat.ngtcs.subsystem;

import java.net.*;

import ngat.util.logging.*;
import ngat.util.*;
import ngat.ngtcs.*;
import ngat.ngtcs.net.cil.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public abstract class TTL_System implements PluggableSubSystem
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: TTL_System.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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
  protected TTL_CIL cil;

  /**
   *
   */
  protected TTL_CIL_ValueSetter valueSetter;

  /**
   *
   */
  protected TTL_CIL_ValueGetter valueGetter;

  /**
   *
   */
  protected TTL_CIL_SendCommand commandSender;

  /**
   * Boolean describing the initialisation state of this object.
   */
  protected boolean initialised = false;

  /**
   *
   */
  protected String errMsg = "Error!";

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/


  /**
   *
   */
  protected TTL_System()
  {
	
  }


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
  }


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

      cil.initialise( localPort, remoteHost, remotePort );

      new Thread( cil ).start();
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
  public TTL_DataValue getValue( TTL_DataType t )
    throws TTL_SystemException
  {
    return valueGetter.getValue( t );
  }


  /**
   *
   */
  public void setValue( TTL_DataValue v )
    throws TTL_SystemException
  {
    valueSetter.setValue( v );
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
  public void sendCommand( TTL_DataType c, int i1, int i2 )
    throws TTL_SystemException
  {
    //commandSender.send( c, i1, i2 );
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
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_System.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_System.java,v $
 *      $Id: TTL_System.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
