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
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGG extends TTL_System
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
    new String( "$Id: AGG.java,v 1.2 2013-07-04 12:55:38 cjm Exp $" );

  /**
   *
   */
  protected static AGG instance = null;

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
  protected AGS ags;

  /**
   *
   */
  protected AGG_GuidePacketReader gpr;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static AGG getInstance()
  {
    if( instance == null )
      instance = new AGG();

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
  protected AGG()
  {
    super( TTL_Package.AGG );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_AGG );
    initialiseCIL( np, "", txCIL );
    rxCIL = txCIL;

    gpr = new AGG_GuidePacketReader();

    initialised = true;
  }


  /**
   *
   */
  public AGG_GuidePacket getGuidePacket()
    throws TTL_SystemException
  {
    return( gpr.getGuidePacket() );
  }
}
/*
 *    $Date: 2013-07-04 12:55:38 $
 * $RCSfile: AGG.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG.java,v $
 *      $Id: AGG.java,v 1.2 2013-07-04 12:55:38 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 */
