package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.sdb.*;
import ngat.ngtcs.subsystem.spt.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class TTL_PrimaryMirror
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
    new String( "$Id: TTL_PrimaryMirror.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

  /**
   * The single instance of this class.
   */
  protected static TTL_PrimaryMirror instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The TTL SPT subsystem used by this Primary Mirror.
   */
  protected SPT spt = null;

  /**
   * The TTL SDB subsystem used by this Primary Mirror.
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
  public static TTL_PrimaryMirror getInstance()
  {
    if( instance == null )
      instance = new TTL_PrimaryMirror();

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
  protected TTL_PrimaryMirror()
  {
    spt = SPT.getInstance();    
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
  /* SPT Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Request the operational state of the SMF process.
   * @return the operational state as an SMF_State object
   */
  public SPT_State get_SPT_State()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SPT_DataType.D_SPT_PROC_STATE,
			  TTL_CIL_Node.E_CIL_SPT ) );
    return( (SPT_State)( SPT_State.getReference( val.getValue() ) ) );
  }


  /**
   * Request that the SPT open the primary mirror cover.
   */
  public void openCover()
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( SPT_DataType.D_SPT_MCO, SPT_State.E_SPT_DMD_STS_OPN.getInt() );
    spt.setValue( val );
  }


  /**
   * Request that the SPT close the primary mirror cover.
   */
  public void closeCover()
    throws TTL_SystemException
  {
    TTL_DataValue val = new TTL_DataValue
      ( SPT_DataType.D_SPT_MCO, SPT_State.E_SPT_DMD_STS_CLS.getInt() );
    spt.setValue( val );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* SPT Commands                                                            */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the state of the Primary Mirror cover.
   * @return the SPT_State describing the current cover state
   * @see SPT_State
   */
  public SPT_State getCoverState()
    throws TTL_SystemException
  {
    TTL_DataValue val = sdb.retrieveValue
      ( new SDB_DataType( SPT_DataType.D_SPT_MCO,
			  TTL_CIL_Node.E_CIL_SPT ) );
    return( (SPT_State)( SPT_State.getReference( val.getValue() ) ) );
  }
}
/*
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_PrimaryMirror.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_PrimaryMirror.java,v $
 *      $Id: TTL_PrimaryMirror.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
