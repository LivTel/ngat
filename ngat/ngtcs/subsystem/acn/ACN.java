package ngat.ngtcs.subsystem.acn;

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
public class ACN extends ngat.ngtcs.subsystem.TTL_System
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
    new String( "$Id: ACN.java,v 1.1 2003-09-19 16:08:30 je Exp $" );


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected TTL_CIL cil = null;

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
   * The non-public Constructor required by singleton classes.
   */
  public ACN( ACN_NodeType type )
  {
    super( TTL_Package.ACN );
  }


  /**
   *
   */
  public void initialise( Telescope t )
    throws InitialisationException
  {
    super.initialise( t );

    NGATProperties np = getProperties();

    cil = new TTL_CIL();
    initialiseCIL( np, "", cil );

    valueSetter = new TTL_CIL_ValueSetter( cil );
    valueGetter = new TTL_CIL_ValueGetter( cil );
    commandSender = new TTL_CIL_SendCommand( cil );

    initialised = true;
  }
}
/*
 *    $Date: 2003-09-19 16:08:30 $
 * $RCSfile: ACN.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/acn/ACN.java,v $
 *      $Id: ACN.java,v 1.1 2003-09-19 16:08:30 je Exp $
 *     $Log: not supported by cvs2svn $
 */
