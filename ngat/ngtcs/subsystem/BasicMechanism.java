package ngat.ngtcs.subsystem;

import java.util.*;
import java.lang.reflect.*;

import ngat.util.*;
import ngat.util.logging.Logger;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 *
 */
public class BasicMechanism
  implements ControllableSubSystem
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: BasicMechanism.java,v 1.2 2003-09-19 16:01:09 je Exp $" );

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
   * State of the PluggableSubSystem.
   */
  protected SubSystemState subSystemState = SubSystemState.INACTIVE;

  /**
   * State of the PluggableSubSystem.
   */
  protected SoftwareState softwareState = null;

  /**
   * Error message - to be set when an error occurs, describing the error.
   */
  protected String errorMessage;

  /**
   * Status of the PluggableSubSystem.
   */
  protected Status status = null;

  /**
   * boolean Object representing whether this mechanism is active.
   */
  protected boolean active = false;

  /**
   * logger Object used by this mechanism.
   */
  protected Logger logger = null;

  /**
   *
   */
  protected boolean shutdownInProgress = false;

  /**
   * Synchronisation lock for the shutdown process.
   */
  protected Object shutdownLock = new Object();


  /**
   * PluggableSubSystem constructor.
   */
  public BasicMechanism()
  {
    subSystemState = SubSystemState.INACTIVE;
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
   * Method to return the State of this PluggableSubSystem.
   * @return the State of this PluggableSubSystem.
   */
  public SubSystemState getSubSystemState()
  {
    return subSystemState;
  }


  /**
   * Method to return the State of this PluggableSubSystem.
   * @return the State of this PluggableSubSystem.
   */
  public SoftwareState getSoftwareState()
  {
    return softwareState;
  }


  /**
   * Method to return the Status of this PluggableSubSystem.
   * @return the TelescopeStatus of this PluggableSubSystem.
   */
  public Status getStatus()
  {
    return status;
  }


  /**
   * Activate the mechanism implementing this interface.
   */
  public void activate()
  {

  }


  /**
   * Initialise the mechanism implementing this interface.
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    telescope = t;
    logger = telescope.getLogger( this.getClass() );
    logName = logger.getName();
  }


  /**
   * Create the NGATProperties and load from the standard filename.
   */
  protected void getProperties() throws InitialisationException
  {
    np = new NGATProperties();
    try
    {
      np.load( telescope.getName()+"-"+logName+".cfg" );
    }
    catch( Exception e )
    {
      throw new InitialisationException
	( "Could not get "+logName+
	  " properties for "+telescope.getName()+" : "+e );
    }
  }


  /**
   * The mechanism implementing this interface into a safe state in 
   * preparation for a shutdown or power cut.
   */
  public boolean makeSafe()
  {
    logger.log( 1, logName, "PluggableSubSystem ["+this+"] safe" );
    return true;
  }


  /**
   * De-activate the mechanism implementing this interface.
   */
  public void deActivate()
  {
    active = false;
    //state = SubSystemState.DEACTIVATED;
  }

  /**
   * Return a descriptor of whether the mechanism implementing this interface
   * is currently active.
   */
  public boolean isActive()
  {
    return active;
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
   * Sets the state defining that a shutdown is in progress.
   * This should be called FIRST by all concrete PluggableSubSystems.
   */
  public boolean shutdown()
  {
    synchronized( shutdownLock )
    {
      if( shutdownInProgress ) return false;

      shutdownInProgress = true;
    }
    return true;
  }


  /**
   *
   */
  public void setLogLevel( int newLogLevel )
  {
    logger.setLogLevel( newLogLevel );
  }


  /**
   *
   */
  public String getErrorMessage()
  {
    return( this+" has an error!" );
  }

}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: BasicMechanism.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/BasicMechanism.java,v $
 *      $Id: BasicMechanism.java,v 1.2 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
