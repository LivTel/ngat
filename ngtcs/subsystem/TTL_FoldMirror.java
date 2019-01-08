package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.sdb.*;
import ngat.ngtcs.subsystem.amn.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_FoldMirror
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
  protected static TTL_FoldMirror instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL AMN subsystem used by this Fold Mirror.
   */
  protected AMN amn = null;

  /**
   * The TTL SDB subsystem used by this Fold Mirror.
   */
  protected SDB sdb = null;

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
  public static TTL_FoldMirror getInstance()
  {
    if( instance == null )
      instance = new TTL_FoldMirror();

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
  protected TTL_FoldMirror()
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

    initialised = true;
  }


  /*=========================================================================*/
  /*                                                                         */
  /* AMN Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Request the operational state of the SFD process.
   * @return the operational state as an SFD_State object
   */
  public SFD_State get_SFD_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SFD_DataType.D_SFD_PROC_STATE,
			  TTL_CIL_Node.E_CIL_OMR ) );
    return( (SFD_State)( SFD_State.getReference( val.getValue() ) ) );
  }


  /**
   * Request the operational state of the SFP process.
   * @return the operational state as an SFP_State object
   */
  public SFP_State get_SFP_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SFP_DataType.D_SFP_PROC_STATE,
			  TTL_CIL_Node.E_CIL_OMR ) );
    return( (SFP_State)( SFP_State.getReference( val.getValue() ) ) );
  }


  /**
   *
   */
  public void deploy()
    throws TTL_SystemException 
  {
    TTL_DataValue val = new TTL_DataValue
      ( SFD_DataType.D_SFD_DEMAND,
	SFD_DeployState.E_SFD_POS_DEPLOY.getInt() );
    amn.setValue( val );
  }


  /**
   *
   */
  public void retract()
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( SFD_DataType.D_SFD_DEMAND,
	SFD_DeployState.E_SFD_POS_STOW.getInt() );
    amn.setValue( val );
  }


  /**
   *
   */
  public SFD_DeployState getDeployState()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SFD_DataType.D_SFD_ACTUAL,
			  TTL_CIL_Node.E_CIL_OMR ) );
    return( (SFD_DeployState)( SFD_DeployState.getReference
			       ( val.getValue() ) ) );
  }


  /**
   *
   */
  public void setPortDemand( SFP_Port p )
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( SFP_DataType.D_SFP_DEMAND, p.getInt() );
    amn.setValue( val );
  }


  /**
   *
   */
  public SFP_Port getPortDemand( SFP_Port p )
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SFD_DataType.D_SFD_DEMAND,
			  TTL_CIL_Node.E_CIL_OMR ) );
    return( (SFP_Port)( SFP_Port.getReference( val.getValue() ) ) );
  }


  /**
   *
   */
  public SFP_Port getActualPort()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SFD_DataType.D_SFD_ACTUAL,
			  TTL_CIL_Node.E_CIL_OMR ) );
    return( (SFP_Port)( SFP_Port.getReference( val.getValue() ) ) );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_FoldMirror.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_FoldMirror.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
