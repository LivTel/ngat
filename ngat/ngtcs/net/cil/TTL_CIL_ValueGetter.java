package ngat.ngtcs.net.cil;

import java.io.*;
import java.net.*;

import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_CIL_ValueGetter
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: TTL_CIL_ValueGetter.java,v 1.1 2003-09-19 16:00:50 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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
  protected boolean two_CIL = false;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/


  /**
   *
   */
  public TTL_CIL_ValueGetter( TTL_CIL c )
  {
    cil = c;
    two_CIL = false;
  }


  /**
   *
   */
  public TTL_CIL_ValueGetter( TTL_CIL c1,  TTL_CIL c2 )
  {
    in_CIL = c1;
    out_CIL = c2;
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
    TTL_CIL_Message msg, reply;

    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream( baos );

      msg = new TTL_CIL_Message
	( TTL_CIL_Node.E_CIL_TCS, TTL_CIL_Node.E_CIL_AGS,
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS,
	  TTL_CIL_ServiceClass.E_MCP_GET_1,
	  0, baos.toByteArray() );

      if( two_CIL )
      {
	out_CIL.sendMessage( msg );
	reply = (TTL_CIL_Message)in_CIL.getReply( msg );
      }
      else
      {
	reply = (TTL_CIL_Message)
	  cil.sendMessageGetReply( msg );
      }
    }
    catch( Exception e )
    {
      throw new TTL_SystemException( e.toString() );
    }

    // parse reply - USE TTL_CIL_Message sub-classes

    return( new TTL_DataValue( t, value, units, timestamp ) );
  }
}
/*
 *    $Date: 2003-09-19 16:00:50 $
 * $RCSfile: TTL_CIL_ValueGetter.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_ValueGetter.java,v $
 *      $Id: TTL_CIL_ValueGetter.java,v 1.1 2003-09-19 16:00:50 je Exp $
 *     $Log: not supported by cvs2svn $
 */
