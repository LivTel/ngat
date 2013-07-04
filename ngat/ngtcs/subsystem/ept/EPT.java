package ngat.ngtcs.subsystem.ept;

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
 * @version $Revision: 1.1 $
 */
public class EPT extends ngat.ngtcs.subsystem.TTL_System
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
    new String( "$Id: EPT.java,v 1.1 2013-07-04 12:58:40 cjm Exp $" );

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
  public static EPT getInstance()
  {
    return new EPT();
  }


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The non-public Constructor required by singleton classes.
   */
  protected EPT()
  {
    super( TTL_Package.EPT );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    txCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_EPT );
    rxCIL = new TTL_CIL( TTL_CIL_Node.E_CIL_EPT );

    initialiseCIL( np, "rx.", rxCIL );
    initialiseCIL( np, "tx.", txCIL );

    initialised = true;
  }
}
/*
 *    $Date: 2013-07-04 12:58:40 $
 * $RCSfile: EPT.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ept/EPT.java,v $
 *      $Id: EPT.java,v 1.1 2013-07-04 12:58:40 cjm Exp $
 *     $Log: not supported by cvs2svn $
 */
