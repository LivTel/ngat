package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.subsystem.ept.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_Enclosure
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
    new String( "$Id: TTL_Enclosure.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL EPT subsystem used by this TTL_Enclosure.
   */
  protected EPT ept;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the ONLY instance of this TTL_Enclosure class.
   * <br>
   * If this class is previously uninstantiated a new object will be created,
   * otherwise the previous instantiation is returned.
   * @return the ONLY instance of this class.
   */
  public static PluggableSubSystem getInstance()
  {
    if( instance == null )
      instance = new TTL_Enclosure();

    return instance;
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  protected TTL_Enclosure()
  {
    ept = EPT.getInstance();
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );
    initialised = true;
  }


  /**
   *
   */
  public void openShutter1()
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S1_STATE,
			 EPT_State.E_EPT_OPEN_STATE.getInt() );
    ept.setValue( val );
  }


  /**
   *
   */
  public void openShutter2()
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S2_STATE,
			 EPT_State.E_EPT_OPEN_STATE.getInt() );
    ept.setValue( val );
  }


  /**
   *
   */
  public void closeShutter1()
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S1_STATE,
			 EPT_State.E_EPT_CLOSED_STATE.getInt() );
    ept.setValue( val );
  }


  /**
   *
   */
  public void closeShutter2()
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S2_STATE,
			 EPT_State.E_EPT_CLOSED_STATE.getInt() );
    ept.setValue( val );
  }


  /**
   *
   */
  public void moveShutter1( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S1_POS, 
			 (int)( d * 3600000.0 ) );
    ept.setValue( val );
  }


  /**
   *
   */
  public void moveShutter2( double d )
    throws TTL_SystemException
  {
    TTL_DataValue val =
      new TTL_DataValue( EPT_DataType.D_EPT_DMD_S2_POS, 
			 (int)( d * 3600000.0 ) );
    ept.setValue( val );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* EPT Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public EPT_State get_EPT_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = ept.getValue( EPT_DataType.D_EPT_PROC_STATE );
    return( (EPT_State)( EPT_State.getReference( val.getValue() ) ) );
  }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_Enclosure.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Enclosure.java,v $
 *      $Id: TTL_Enclosure.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
