package ngat.ngtcs.subsystem.ags;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
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
public class AGS extends TTL_System
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
  protected static AGS instance = null;

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  protected AGG agg;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static AGS getInstance()
  {
    if( instance == null )
      instance = new AGS();

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
  protected AGS()
  {
    super( TTL_Package.AGS );
    agg = AGG.getInstance();
  }


  /**
   *
   */
  protected void _initialise( Telescope t )
    throws InitialisationException
  {
    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_AGS );
    initialiseCIL( np, "", txCIL );
    rxCIL = txCIL;

    agg.initialise( telescope );
  }


  /**
   *
   */
  public void sendCommand( CIL_ServiceClass s, int i1, int i2 )
    throws TTL_SystemException
  {
    CIL_Message msg, reply;

    try
    {


      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream( baos );

      // write command ID
      dos.writeInt( s.getInt() );
      // write status
      dos.writeInt( TTL_SystemStatus.SYS_NOMINAL.getInt() );
      // write arg 1
      dos.writeInt( i1 );
      // write arg 2
      dos.writeInt( i2 );

      msg = new CIL_Message
	( TTL_CIL_Node.E_CIL_TCS.getInt(), txCIL.getNode().getInt(),
	  TTL_CIL_MessageClass.E_CIL_CMD_CLASS.getInt(),
	  TTL_CIL_GenericService.E_MCP_SET_1.getInt(),
	  0, baos.toByteArray() );

      reply = sendCIL_Message( msg );
    }
    catch( IOException e )
    {
      throw new TTL_SystemException( e.toString() );
    }
  }
}
/*
 *    $Date: 2013-07-04 12:56:01 $
 * $RCSfile: AGS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 */
