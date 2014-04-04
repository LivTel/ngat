package ngat.ngtcs.net.cil;

import ngat.net.cil.*;



/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_CIL_Message extends CIL_Message
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
    new String( "$Id$" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  //protected TTL_Package ttlSystem;

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
  public TTL_CIL_Message( CIL_Node tx, CIL_Node rx, CIL_MessageClass msg,
			  CIL_ServiceClass srv, int sN, byte[] body )
  {
    super( tx, rx, msg, srv, sN, body );
  }


  /**
   *
   */
  public TTL_CIL_Message( CIL_Node tx, CIL_Node rx, CIL_MessageClass msg,
			  CIL_ServiceClass srv,
			  int sN, ngat.ngtcs.common.Timestamp time,
			  byte[] body )
  {
    super( tx, rx, msg, srv, sN, (int)time.getSeconds(),
	   (int)time.getNanoseconds(), body );
  }


  /**
   *
   */
  public TTL_CIL_Message( CIL_Node tx, CIL_Node rx, CIL_MessageClass msg,
			  CIL_ServiceClass srv,
			  int sN, int secs, int nsecs, byte[] body )
  {
    super( tx, rx, msg, srv, sN, secs, nsecs, body );
  }


  /**
   *
   */
  public String toString()
  {
    return
      ( "     Message source node: "+srcNode+
	"\nMessage destination node: "+destNode+
	"\n           Message class: "+msgClass+
	"\n           Service class: "+srvClass+
	//	      "\n              TTL System: "+ttlSystem+
	"\n Message sequence number: "+seqNo+
	"\n       Timestamp seconds: "+seconds+
	"\n   Timestamp nanoseconds: "+nanoseconds+
	"\n            Message body:"+
	"\n                         "+( new String( msgBody ) ) );
  }
}
/*
 *    $Date: 2003-09-19 16:00:50 $
 * $RCSfile: TTL_CIL_Message.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_Message.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 */
