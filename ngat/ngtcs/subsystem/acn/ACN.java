package ngat.ngtcs.subsystem.acn;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.net.cil.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.*;

/**
 * This class is a singleton and represents the TTL Autoguider System.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class ACN extends TTL_System implements PluggableSubSystem
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
   *
   */
  protected static ACN azn = null;

  /**
   *
   */
  protected static ACN eln = null;

  /**
   *
   */
  protected static ACN csn = null;

  /**
   *
   */
  protected static ACN amn = null;

  /**
   *
   */
  protected static ACN pmn = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The type of axis for which this is a control node.
   */
  protected ACN_NodeType nodeType = null;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static ACN getInstance( ACN_NodeType t )
  {
    if( t == ACN_NodeType.E_ACN_NODE_AZN )
    {
	if( azn == null ) azn = new ACN( ACN_NodeType.E_ACN_NODE_AZN );
	return azn;
    }
    else if( t == ACN_NodeType.E_ACN_NODE_ELN )
    {
	if( eln == null ) eln = new ACN( ACN_NodeType.E_ACN_NODE_ELN );
	return eln;
    }
    else if( t == ACN_NodeType.E_ACN_NODE_CSN )
    {
	if( csn == null ) csn = new ACN( ACN_NodeType.E_ACN_NODE_CSN );
	return csn;
    }
    else if( t == ACN_NodeType.E_ACN_NODE_AMN )
    {
	if( amn == null ) amn = new ACN( ACN_NodeType.E_ACN_NODE_AMN );
	return amn;
    }
    else if( t == ACN_NodeType.E_ACN_NODE_PMN )
    {
	if( pmn == null ) pmn = new ACN( ACN_NodeType.E_ACN_NODE_PMN );
	return pmn;
    }
    else
    {
	return null;
    }
  }

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
   * The non-public Constructor required by singleton classes.
   */
  protected ACN( ACN_NodeType type )
  {
    super( TTL_Package.ACN );
    nodeType = type;
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    if( nodeType == ACN_NodeType.E_ACN_NODE_AZN )
    {
      rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_AZS );
      txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_AZC );
      initialiseCIL( np, "AZN.rx.", rxCIL );
      initialiseCIL( np, "AZN.tx.", txCIL );
    }
    else if( nodeType == ACN_NodeType.E_ACN_NODE_ELN )
    {
      rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_ELS );
      txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_ELC );
      initialiseCIL( np, "ELN.rx.", rxCIL );
      initialiseCIL( np, "ELN.tx.", txCIL );
    }
    else if( nodeType == ACN_NodeType.E_ACN_NODE_CSN )
    {
      rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_CSS );
      txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_CSC );
      initialiseCIL( np, "CSN.rx.", rxCIL );
      initialiseCIL( np, "CSN.tx.", txCIL );
    }
    /*
    else if( nodeType == ACN_NodeType.E_ACN_NODE_AMN )
    {
      rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_OMS );
      txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_OMC );
      initialiseCIL( np, "AMN.rx.", rxCIL );
      initialiseCIL( np, "AMN.tx.", txCIL );
    }
    */
    else if( nodeType == ACN_NodeType.E_ACN_NODE_PMN )
    {
      rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_MSS );
      txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_MSC );
      initialiseCIL( np, "PMM.rx.", rxCIL );
      initialiseCIL( np, "PMM.tx.", txCIL );
    }

    initialised = true;
  }


  /**
   * Return the type of axis for which this is a control node.
   * @return nodeType
   * @see #nodeType
   */
  public ACN_NodeType getNodeType()
  {
    return( nodeType );
  }


  /**
   * This method sends a command to this ACN to track the specified position in
   * degrees, at the specified Timestamp.
   * @param d the position in degrees
   * @param t the Timestamp for the track demand
   */
  public void trackDegreesPosition( double d, Timestamp t )
    throws TTL_SystemException
  {
    int milliArcseconds = (int)( Math.rint( d * 3600.0 * 1000.0 ) );
    CIL_Message msg, reply;

    try
    {
      TTL_CIL_ObjectValue val = new TTL_CIL_ObjectValue
	( AMC_DataType.D_AMC_TRACK_COMMAND, milliArcseconds, t );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  ACN_Service.E_ACN_TRACK_CMD.getInt(),
	  0, val.asByteArray() );

      reply = sendCIL_Message( msg );
    }
    catch( IOException e )
    {
      throw new TTL_SystemException( e.toString() );
    }
  }


  /**
   * Move this axis to the specified position in degrees.
   * @param d the desired position, in degrees.
   */
  public void moveToDegreesPosition( double d )
    throws TTL_SystemException
  {
    int milliArcseconds = (int)( Math.rint( d * 3600.0 * 1000.0 ) );
    CIL_Message msg, reply;

    try
    {
      TTL_CIL_ObjectValue val = new TTL_CIL_ObjectValue
	( AMC_DataType.D_AMC_MOVE_COMMAND,  milliArcseconds,
	  new Timescale() );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  ACN_Service.E_ACN_MOVE_CMD.getInt(),
	  0, val.asByteArray() );

      reply = sendCIL_Message( msg );
    }
    catch( IOException e )
    {
      throw new TTL_SystemException( e.toString() );
    }
  }


  /**
   * Stop (halt, i.e. brakes NOT applied) this Axis.
   */
  public void halt()
    throws TTL_SystemException
  {
    CIL_Message msg, reply;
    try
    {
      TTL_CIL_ObjectValue val = new TTL_CIL_ObjectValue
	( AMC_DataType.D_AMC_HALT_COMMAND,  1, new Timescale() );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  ACN_Service.E_ACN_SET_CMD.getInt(),
	  0, val.asByteArray() );

      reply = sendCIL_Message( msg );
    }
    catch( IOException e )
    {
      throw new TTL_SystemException( e.toString() );
    }
  }
}
/*
 *    $Date: 2013-07-04 12:53:26 $
 * $RCSfile: ACN.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/acn/ACN.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:30  je
 *     Initial revision
 *
 */
