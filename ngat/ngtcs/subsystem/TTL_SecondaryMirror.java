package ngat.ngtcs.subsystem;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.amn.*;
import ngat.ngtcs.subsystem.sdb.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_SecondaryMirror
  extends BasicSingletonMechanism
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
  public static final String RevisionString =
    new String( "$Id: TTL_SecondaryMirror.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL SDB subsystem used by this Secondary Mirror.
   */
  protected SDB sdb = null;

  /**
   * The TTL AMN subsystem used by this Secondary Mirror.
   */
  protected AMN amn = null;

  /**
   * Acceptable maximum position error for demanded focus positions,
   * in millimetres.  This parameter <i>should</i> be set during
   * initialisation.
   */
  protected double positionTolerance = 0.0;

  /**
   * The default focus position of this secondary mirror.  This field should be
   * updated for use when offsetting using <code>DFOCUS</code>.  This field
   * may be assigned during initialisation, but <b>MUST</b> be set (using the
   * accessor methods) to ensure continuity of focus after a
   * <code>DFOCUS</code> command.
   */
  protected double focusPosition = 0.0;

  /**
   * This field holds the current offset to enable the persistence of the
   * offset during an INSTRUMENT command.  This field <b>MUST</b> be assigned
   * (using the accessor methods) to ensure continuity of focus after a
   * <code>DFOCUS</code> command. It is the offset from
   * <code>focusPosition</code> in millimetres.
   * @see #focusPosition
   */
  protected double focusOffset = 0.0;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the ONLY instance of this TTL_Autoguider class.
   * <br>
   * If this class is previously uninstantiated a new object will be created,
   * otherwise the previous instantiation is returned.
   * @return the ONLY instance of this class.
   */
  public static PluggableSubSystem getInstance()
  {
    if( instance == null )
      instance = new TTL_SecondaryMirror();

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
  protected TTL_SecondaryMirror()
  {
    amn = AMN.getInstance();
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

    try
    {
      positionTolerance = np.getDouble( "positionTolerance" );
    }
    catch( NGATPropertyException npe )
    {
      logger.log( 1, logName, npe );
      throw new InitialisationException
	( "Need acceptable position error tolerance : "+npe );
    }

    initialised = true;
  }


  /**
   * Set the focus position used for offsetting from, in millimetres.
   * @param d the new focus position
   * @see #focusPosition
   */
  public void setFocusPosition( double d )
  {
    focusPosition = d;
  }


  /**
   * Return the focus position for use in offsetting, in millimetres.
   * @return focusPosition
   * @see #focusPosition
   */
  public double getFocusPosition()
  {
    return( focusPosition );
  }


  /**
   * Set the focus offset used for offsetting from, in millimetres.
   * @param d the new focus offset
   * @see #focusOffset
   */
  public void setFocusOffset( double d )
  {
    focusOffset = d;
  }


  /**
   * Return the focus offset for use in offsetting, in millimetres.
   * @return focusOffset
   * @see #focusOffset
   */
  public double getFocusOffset()
  {
    return( focusOffset );
  }


  /**
   * Return the position tolerance used in determining the success of focus
   * movement commands.
   * @return positionTolerance
   * @see #positionTolerance
   */
  public double getPositionTolerance()
  {
    return positionTolerance;
  }

  /*=========================================================================*/
  /*                                                                         */
  /* AMN Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Request the operational state of the SMF process.
   * @return the operational state as an SMF_State object
   */
  public SMF_State get_SMF_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = amn.getValue( SMF_DataType.D_SMF_PROC_STATE );
    return( (SMF_State)( SMF_State.getReference( val.getValue() ) ) );
  }


  /**
   * Request the current position of this Secondary Mirror.
   * @return the position in millimetres
   */
  public double getActualPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = amn.getValue( SMF_DataType.D_SMF_ACTUAL );
    return( ( (double)val.getValue() ) / 1000.000 );
  }


  /**
   * Request the demanded focus position of this Secondary Mirror.
   * @return the demand in millimetres
   */
  public double getDemandPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = amn.getValue( SMF_DataType.D_SMF_DEMAND );
    return( ( (double)val.getValue() ) / 1000.000 );
  }


  /**
   * Set the focus position demand of this Secondary Mirror.
   * @param d the position demand in millimetres
   */
  public void setDemandPosition( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( SMF_DataType.D_SMF_DEMAND, (int)( d * 1000.0 ) );
    amn.setValue( val );
  }


  /**
   * Request the minimum acceptable value for the position demand.
   * @return the minimum position in millimetres
   */
  public double getMinimumDemandPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = amn.getValue( SMF_DataType.D_SMF_SMF_RANGE_LO );
    return( ( (double)val.getValue() ) / 1000.000 );
  }


  /**
   * Request the maximum acceptable value for the position demand.
   * @return the maximum position in millimetres
   */
  public double getMaximumDemandPosition()
    throws TTL_SystemException
  {
    TTL_DataValue val = amn.getValue( SMF_DataType.D_SMF_SMF_RANGE_HI );
    return( ( (double)val.getValue() ) / 1000.000 );
  }


  /**
   *
   */
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_SecondaryMirror.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SecondaryMirror.java,v $
 *      $Id: TTL_SecondaryMirror.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
