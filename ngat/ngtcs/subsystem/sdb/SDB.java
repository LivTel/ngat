package ngat.ngtcs.subsystem.sdb;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.net.cil.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.subsystem.*;


/**
 * This class is a singleton and represents the TTL status Database used for
 * storing all information about the whole telescope.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SDB extends TTL_System implements PluggableSubSystem
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
  protected static SDB instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static SDB getInstance()
  {
    if( instance == null )
      instance = new SDB();

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
  protected SDB()
  {
    super( TTL_Package.SDB );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_SDB );
    initialiseCIL( np, "", txCIL );
    rxCIL = txCIL;

    initialised = true;
  }


  /**
   * Retrive one datum from the Status Database.  This method calls
   * <code>retrieveMultipleValues</code> with an array of length 1.
   */
  public TTL_DataValue retrieveValue( SDB_DataType d )
    throws TTL_SystemException
  {
    SDB_DataType types[] = new SDB_DataType[ 1 ];
    types[ 0 ] = d;

    TTL_DataValue vals[] = retrieveMultipleValues( types );
    return( vals[ 0 ] );
  }


  /**
   *
   */
  public TTL_DataValue[] retrieveMultipleValues( SDB_DataType[] d )
    throws TTL_SystemException
  {
    TTL_DataValue[] vals = null;

    try
    {
      int i;
      CIL_Message msg, reply;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream body = new DataOutputStream( baos );

      // write number of requests
      body.writeInt( d.length );

      for( i = 0; i < d.length; i++ )
      {
	// dataum src ID
	body.writeInt( d[ i ].getReportingNode().getInt() );
	// datum ID
	body.writeInt( d[ i ].getDataType().getInt() );
      }

      byte[] sendBody = baos.toByteArray();

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), TTL_CIL_Node.E_CIL_SDB.getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  SDB_Service.E_SDB_RETRIEVE_1.getInt(),
	  sendBody.length, sendBody );

      reply = sendCIL_Message( msg );

      ByteArrayInputStream bais =
	new ByteArrayInputStream( reply.getMessageBody() );
      DataInputStream dis = new DataInputStream( bais );

      if( reply.getMessageClass() ==
	  TTL_CIL_MessageClass.E_CIL_ERR_CLASS.getInt() )
      {
	int err = dis.readInt();
	throw new TTL_SystemException
	  ( "SDB returned an error : "+
	    "SDB_TTL_CIL_Error.getInstance( err ).getName()" );
      }
      else if( reply.getMessageClass() !=
	       TTL_CIL_MessageClass.E_CIL_RSP_CLASS.getInt() )
      {
	throw new TTL_SystemException
	  ( "SDB did not return a valid message class ["+
	    reply.getMessageClass()+"]" );
      }

      int rcvd = dis.readInt();
      if( rcvd != d.length )
      {
	throw new TTL_SystemException
	  ( "Requested "+d.length+" data, but only received "+rcvd );
      }

      vals = new TTL_DataValue[ d.length ];

      for( i = 0; i < d.length; i++ )
      {
	// read source ID
	int src = dis.readInt();
	// read datum ID
	int dat = dis.readInt();
	// read units
	int uni = dis.readInt();
	// read secs
	int sec = dis.readInt();
	// read nanosecs
	int nsc = dis.readInt();
	// read value
	int val = dis.readInt();

	vals[ i ] = new TTL_DataValue
	  ( d[ i ].getDataType(), val, TTL_DataUnit.getReference( uni ),
	    new Timestamp( (long)sec, (long)nsc, CalendarType.GREGORIAN,
			   TimescaleType.UTC ) );
      }
    }
    catch( Exception e )
    {
      throw new TTL_SystemException
	( "Failed to retrieve data from SDB : "+e.toString() );
    }

    return( vals );
  }


  /**
   *
   *
   */
  public void submitValue( TTL_DataValue v )
    throws TTL_SystemException
  {
    System.err.println( "NOT YET IMPLEMENTED!" );
  }
}
/*
 *    $Date: 2013-07-04 12:59:43 $
 * $RCSfile: SDB.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:40  je
 *     Initial revision
 *
 */
