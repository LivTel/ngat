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
 * @version $Revision: 1.1 $
 *
 */
public class BasicPluggableSubSystem implements PluggableSubSystem
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: BasicPluggableSubSystem.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

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
   * Error message - to be set when an error occurs, describing the error.
   */
  protected String errorMessage;

  /**
   * logger Object used by this mechanism.
   */
  protected Logger logger = null;



  /**
   * PluggableSubSystem constructor.
   */
  public BasicPluggableSubSystem()
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
 * $RCSfile: BasicPluggableSubSystem.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/BasicPluggableSubSystem.java,v $
 *      $Id: BasicPluggableSubSystem.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
