package ngat.ngtcs.subsystem;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.subsystem.acn.*;
import ngat.ngtcs.subsystem.sdb.*;

/**
 * This class is a singleton and represents the TTL Rotator System.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_Rotator
  extends BasicMechanism
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

  /**
   * The single instance of this class.
   */
  protected static TTL_Rotator instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL AGS subsystem used by this rotator.
   */
  protected ACN cas = null;

  /**
   * The TTL SDB subsystem used by this rotator.
   */
  protected SDB sdb = null;

  /**
   * The maximum acceptable position error that will return a successfully
   * acheived demand of the rotator angle.  This value is read from the
   * config file.
   */
  protected double positionTolerance = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the ONLY instance of this TTL_Rotator class.
   * <br>
   * If this class is previously uninstantiated a new object will be created,
   * otherwise the previous instantiation is returned.
   * @return the ONLY instance of this class.
   */
  public static PluggableSubSystem getInstance()
  {
    if( instance == null )
      instance = new TTL_Rotator();

    return instance;
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The non-public Constructor required by singleton classes.
   */
  protected TTL_Rotator()
  {
    cas = ACN.getInstance( ACN_NodeType.E_ACN_NODE_CSN );
    sdb = SDB.getInstance();
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    // get properties file
    getProperties();

    // read config file and get TTL_Rotator position tolerance
    try
    {
      positionTolerance = np.getDouble( "tolerance" );
    }
    catch( NGATPropertyException npe )
    {
      logger.log( 1, logName, npe );
      throw new InitialisationException( npe );
    }

    initialised = true;
  }


  /**
   *
   */
  public double getPositionTolerance()
  {
    return( positionTolerance );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* AMC Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the current position for the rotator, in degrees.
   * @return the current demand
   */
  public double getActualPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( AMC_DataType.D_AMC_AXIS_POSITION,
			  TTL_CIL_Node.E_CIL_CSR ) );
    return( (double)( val.getValue() ) / 3600000.0 );
  }


  /**
   * Set the demand position for the rotator, in degrees.
   * @param d the angle to set.
   */
  public void setDemandPosition( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( AMC_DataType.D_AMC_AXIS_POSITION_DEMAND, (int)( d * 3600000.0 ) );
    cas.setValue( val );
  }


  /**
   * Return the position-demand for the rotator, in degrees.
   * @return the current demand
   */
  public double getDemandPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val =sdb.retrieveValue
      ( new SDB_DataType( AMC_DataType.D_AMC_AXIS_POSITION_DEMAND,
			  TTL_CIL_Node.E_CIL_CSR ) );
    return( (double)( val.getValue() ) / 3600000.0 );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_Rotator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Rotator.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
