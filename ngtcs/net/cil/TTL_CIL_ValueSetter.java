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
public class TTL_CIL_ValueSetter
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
  protected TTL_CIL cil = null;

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

  /**
   *
   */
  protected TTL_CIL_Node cilNode = null;

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
  public TTL_CIL_ValueSetter( TTL_CIL c, TTL_CIL_Node n )
  {
    cil = c;
    cilNode = n;
    two_CIL = false;
  }


  /**
   *
   */
  public TTL_CIL_ValueSetter( TTL_CIL c1,  TTL_CIL c2, TTL_CIL_Node n )
  {
    in_CIL = c1;
    out_CIL = c2;
    cilNode = n;
    two_CIL = true;
  }


  /**
   *
   */
  public void setValue( TTL_DataValue v )
    throws TTL_SystemException
  {
    CIL_Message msg, reply;
    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream( baos );

      // write OID
      dos.writeInt( v.getType().getInt() );
      // write error
      dos.writeInt( 0 );
      // write data type - 4 or 8 byte data
      //
      // NEEDS CHANGING!
      //
      dos.writeInt( 0 );
      // write high bytes (5-8)
      dos.writeInt( 0 );
      // write low bytes (1-4)
      dos.writeInt( v.getValue() );
      // write timestamp seconds
      dos.writeInt( 0 );
      // write timestamp nanoseconds
      dos.writeInt( 0 );
      // write units
      dos.writeInt( 0 );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), cilNode.getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  TTL_CIL_GenericService.E_MCP_SET_1.getInt(),
	  0, baos.toByteArray() );

      if( two_CIL )
      {
	out_CIL.sendMessage( msg );
	reply = in_CIL.getReply( msg );
      }
      else
      {
	reply = cil.sendMessageGetReply( msg );
      }
    }
    catch( Exception e )
    {
      throw new TTL_SystemException( e.toString() );
    }

    // parse message and check for error.
  }
}
/*
 *    $Date: 2013-07-04 10:48:59 $
 * $RCSfile: TTL_CIL_ValueSetter.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_ValueSetter.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 */
