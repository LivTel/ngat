package ngat.ngtcs.subsystem.ags;

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
public class AGS extends TTL_System
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
    new String( "$Id: AGS.java,v 1.1 2003-09-19 16:08:38 je Exp $" );

  /**
   *
   */
  protected static AGS instance = null;

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  protected AGG agg;

  /**
   *
   */
  protected TTL_CIL cil = null;

  /**
   * VirtualTelescope object used to perform the astrometry.
   */
  protected VirtualTelescope vt;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * 
   */
  public static AGS getInstance()
  {
    if( instance == null )
      instance = new AGS();

    return instance;
  }


  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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

    agg.initialise( telescope );

    //vt = new VirtualTelescope( telescope );

    initialised = true;
  }
}
/*
 *    $Date: 2003-09-19 16:08:38 $
 * $RCSfile: AGS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS.java,v $
 *      $Id: AGS.java,v 1.1 2003-09-19 16:08:38 je Exp $
 *     $Log: not supported by cvs2svn $
 */
