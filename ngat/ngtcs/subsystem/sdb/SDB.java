package ngat.ngtcs.subsystem.sdb;

import java.io.*;
import java.net.*;

import ngat.util.*;
import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.net.cil.*;
import ngat.ngtcs.subsystem.*;


/**
 * This class is a singleton and represents the TTL status Database used for
 * storing all information about the whole telescope.
 * <p>
 * As with all singletons, the object reference is obtained by calling the
 * static method <code>getReference</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SDB extends TTL_System implements PluggableSubSystem
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
    new String( "$Id: SDB.java,v 1.1 2003-09-19 16:09:40 je Exp $" );

  /**
   *
   */
  protected static SDB instance = null;

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/


  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * 
   */
  public static SDB getInstance()
  {
    if( instance == null )
      instance = new SDB();

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

    initialiseCIL( np, "", cil );

    valueSetter = new TTL_CIL_ValueSetter( cil );
    valueGetter = new TTL_CIL_ValueGetter( cil );
    commandSender = new TTL_CIL_SendCommand( cil );

    initialised = true;
  }


  /**
   *
   */
  public TTL_DataValue retrieveValue( TTL_DataType t )
    throws TTL_SystemException
  {
    // need sendCommand to be used!!
    return getValue( t );
  }


  /**
   *
   *
   */
  public void submitValue( TTL_DataType t, int i )
    throws TTL_SystemException
  {
	
  }
}
/*
 *    $Date: 2003-09-19 16:09:40 $
 * $RCSfile: SDB.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB.java,v $
 *      $Id: SDB.java,v 1.1 2003-09-19 16:09:40 je Exp $
 *     $Log: not supported by cvs2svn $
 */
