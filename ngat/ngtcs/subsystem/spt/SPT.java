package ngat.ngtcs.subsystem.spt;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.*;


/**
 * This class is a singleton and represents the TTL Services PLC Task system.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class SPT extends ngat.ngtcs.subsystem.TTL_System
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
    new String( "$Id: SPT.java,v 1.1 2006-11-20 14:47:01 cjm Exp $" );

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
  public static SPT getInstance()
  {
    return new SPT();
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The non-public Constructor required by singleton classes.
   */
  protected SPT()
  {
    super( ngat.ngtcs.subsystem.TTL_Package.SPT );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_SPT );
    rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_SPT );
    initialiseCIL( np, "tx.", txCIL );
    initialiseCIL( np, "rx.", rxCIL );

    initialised = true;
  }


  /**
   *
   */
  public void requestControl()
    throws TTL_SystemException
  {
    //sendCommand( SPT_Service. );
  }


  /**
   *
   */
  public void releaseControl()
  {

  }
}
/*
 *    $Date: 2006-11-20 14:47:01 $
 * $RCSfile: SPT.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/spt/SPT.java,v $
 *      $Id: SPT.java,v 1.1 2006-11-20 14:47:01 cjm Exp $
 *     $Log: not supported by cvs2svn $
 */
