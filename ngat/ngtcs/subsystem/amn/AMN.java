package ngat.ngtcs.subsystem.amn;

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
public class AMN extends ngat.ngtcs.subsystem.TTL_System
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


  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static AMN getInstance()
  {
    return new AMN();
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The non-public Constructor required by singleton classes.
   */
  protected AMN()
  {
    super( ngat.ngtcs.subsystem.TTL_Package.AMN );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_OMC );
    rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_OMS );
    initialiseCIL( np, "rx.", rxCIL );
    initialiseCIL( np, "tx.", txCIL );

    initialised = true;
  }


  /**
   *
   */
}
/*
 *    $Date: 2013-07-04 12:57:46 $
 * $RCSfile: AMN.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AMN.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:45  je
 *     Initial revision
 *
 */
