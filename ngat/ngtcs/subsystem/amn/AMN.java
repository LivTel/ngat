package ngat.ngtcs.subsystem.amn;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

import ngat.ngtcs.subsystem.*;
import ngat.ngtcs.net.cil.*;

/**
 * This class is a singleton and represents the TTL Autoguider System.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AMN extends ngat.ngtcs.subsystem.TTL_System
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
    new String( "$Id: AMN.java,v 1.1 2003-09-19 16:08:45 je Exp $" );

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected TTL_CIL incomingCIL = null;

  /**
   *
   */
  protected TTL_CIL outgoingCIL = null;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * 
   */
  public static AMN getInstance()
  {
    return new AMN();
  }


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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

    initialiseCIL( np, "in", incomingCIL );
    initialiseCIL( np, "out", outgoingCIL );

    valueSetter = new TTL_CIL_ValueSetter( incomingCIL, outgoingCIL );
    valueGetter = new TTL_CIL_ValueGetter( incomingCIL, outgoingCIL );
    commandSender = new TTL_CIL_SendCommand( incomingCIL, outgoingCIL );

    initialised = true;
  }


  /**
   *
   */
}
/*
 *    $Date: 2003-09-19 16:08:45 $
 * $RCSfile: AMN.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AMN.java,v $
 *      $Id: AMN.java,v 1.1 2003-09-19 16:08:45 je Exp $
 *     $Log: not supported by cvs2svn $
 */
