package ngat.ngtcs.subsystem;

import java.util.*;
import java.lang.reflect.*;

import ngat.util.*;
import ngat.util.logging.Logger;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * Basic super-class of Controllable subsystem used by the NGTCS commands.
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 */
public abstract class BasicMechanism
  implements ControllableSubSystem
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
   * Name of this PluggableSubSystem.
   */
  protected String name = null;

  /**
   * NGATProperties object, used for configuration in the
   * <code>initialise</code> method.
   */
  protected NGATProperties np = new NGATProperties();

  /**
   * Name of this PluggableSubSystem's logger.
   */
  protected String logName = null;

  /**
   * Telescope of which this PluggableSubSystem is a part.
   */
  protected Telescope telescope = null;

  /**
   * logger Object used by this mechanism.
   */
  protected Logger logger = null;

  /**
   * Boolean describing whether a shutdown has been initiated.
   */
  protected boolean shutdownInProgress = false;

  /**
   * Boolean describing whether this mechanism has been initialised.
   */
  protected boolean initialised = false;

  /**
   * Synchronisation lock for the shutdown process.
   */
  protected Object shutdownLock = new Object();

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
  public BasicMechanism()
  {
  }


  /**
   * Get the name of this PluggableSubSystem.
   * @return the name of this PluggableSubSystem.
   */
  public String getName()
  {
    return( telescope.getName()+"-"+logName );
  }


  /**
   * Initialise the mechanism implementing this interface.
   */
  public final void initialise( Telescope t ) throws InitialisationException
  {
    initialised = false;
    telescope = t;
    if( logger == null )
    {
      logger = telescope.getLogger( this.getClass() );
      logName = logger.getName();
    }
    logger.log( 2, logName, "Logging for "+logName+" on "+logger );

    // call mechanism-specific initialisation
    _initialise();

    initialised = true;
  }


  /**
   * Implemented by all sub-classes to perform system-specific intialisation.
   */
  protected abstract void _initialise() throws InitialisationException;


  /**
   *
   */
  public boolean isInitialised()
  {
    return( initialised );
  }


  /**
   * Create the NGATProperties and load from the standard filename.
   */
  protected void getProperties() throws InitialisationException
  {
    String configFilename = telescope.getName()+"-"+logName+".cfg";
    np = new NGATProperties();
    try
    {
      np.load( configFilename );
    }
    catch( Exception e )
    {
      throw new InitialisationException
	( "Could not get "+logName+
	  " properties for "+telescope.getName()+" : "+e );
    }

    logger.log( 2, logName, configFilename+" loaded" );
  }


  /**
   * Returns a boolean describing whether a SHUTDOWN has been initiated.
   * @return the state of the shutdown progress
   */
  public boolean shutdownInProgress()
  {
    boolean temp;
    synchronized( shutdownLock )
    {
      temp = shutdownInProgress;
    }
    return temp;
  }


  /**
   *
   */
  public abstract void makeSafe() throws SystemException;


  /**
   * Sets the state defining that a shutdown is in progress.
   */
  public final void shutdown() throws SystemException 
  {
    boolean temp;
    synchronized( shutdownLock )
    {
      temp = shutdownInProgress;

      if( shutdownInProgress == false )
	shutdownInProgress = true;
    }

    // system-specific shutdown
    if( temp == false )
      _shutdown();

    return;
  }


  /**
   * Implement the subsystem-specific shutdown.
   */
  protected abstract void _shutdown() throws SystemException;


  /**
   *
   */
  public void setLogLevel( int newLogLevel )
  {
    logger.setLogLevel( newLogLevel );
  }
}
/*
 *    $Date: 2013-07-04 10:53:08 $
 * $RCSfile: BasicMechanism.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/BasicMechanism.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:01:09  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
