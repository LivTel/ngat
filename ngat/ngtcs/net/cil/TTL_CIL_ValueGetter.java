package ngat.ngtcs.net.cil;

import java.io.*;
import java.net.*;

import ngat.net.cil.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_CIL_ValueGetter
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
   *
   */
  protected TTL_CIL cil;

  /**
   *
   */
  protected TTL_CIL in_CIL = null;

  /**
   *
   */
  protected TTL_CIL out_CIL = null;

  /**
   *
   */
  protected TTL_CIL_Node cilNode = null;

  /**
   *
   */
  protected boolean two_CIL = false;

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
  public TTL_CIL_ValueGetter( TTL_CIL c, TTL_CIL_Node n )
  {
    cil = c;
    cilNode = n;
    two_CIL = false;
  }


  /**
   *
   */
  public TTL_CIL_ValueGetter( TTL_CIL c1,  TTL_CIL c2, TTL_CIL_Node n )
  {
    in_CIL = c1;
    out_CIL = c2;
    cilNode = n;
    two_CIL = true;
  }


  /**
   *
   */
  public TTL_DataValue getValue( TTL_DataType t )
    throws TTL_SystemException
  {

    TTL_DataUnit units = null;
    Timestamp timestamp = null;
    int value = 0;
    String errMsg;
    CIL_Message msg, reply;

    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream( baos );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), cilNode.getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  TTL_CIL_GenericService.E_MCP_GET_1.getInt(),
	  0, baos.toByteArray() );

      if( two_CIL )
      {
	out_CIL.sendMessage( msg );
	reply = (CIL_Message)in_CIL.getReply( msg );
      }
      else
      {
	reply = (CIL_Message)
	  cil.sendMessageGetReply( msg );
      }
    }
    catch( Exception e )
    {
      throw new TTL_SystemException( e.toString() );
    }

    // parse reply - USE CIL_Message sub-classes

    return( new TTL_DataValue( t, value, units, timestamp ) );
  }
}
/*
 *    $Date: 2013-07-04 10:48:56 $
 * $RCSfile: TTL_CIL_ValueGetter.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_ValueGetter.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 */
