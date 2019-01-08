package ngat.ngtcs.command;

import java.util.*;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * The response to the SHOW command, containing the information requested by
 * the SHOW command specified in the constructor.  For information requested
 * from this object that was not specified by the original SHOW command, or not
 * applicabel due to the Telescope's current mode of operation, the returned
 * values will be zero or null, depending upon the return type.  This
 * <b>MUST</b> be checked for in the control process that requested the data
 * with the SHOW command.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SHOW_STATE_Done extends CommandDone
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
    new String( "$Id:" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  private boolean networkEnabled;

  /**
   *
   */
  private boolean engineeringOverrideEnabled;

  /**
   *
   */
  private TelescopeState telescopeState;

  /**
   *
   */
  private SoftwareState tcsState;

  /**
   *
   */
  private boolean restartImminent;

  /**
   *
   */
  private boolean shutdownImminent;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Initialise the reply to the specified SHOW command containing the
   * <code><b>true</b></code> successful flag and requested data or a
   * <code><b>false</b></code> successful flag.
   * @param s the SHOW command to which this is a reply
   */
  public SHOW_STATE_Done( SHOW s,
			  boolean network,
			  boolean engOver,
			  TelescopeState telStat,
			  SoftwareState swStat,
			  boolean restart,
			  boolean shutdown )
  {
    super( (Command)s );

    networkEnabled = network;
    engineeringOverrideEnabled = engOver;
    telescopeState = telStat;
    tcsState = swStat;
    restartImminent = restart;
    shutdownImminent = shutdown;
  }


  /**
   *
   */
  public boolean isNetworkInterfaceEnabled()
  {
    return( networkEnabled );
  }


  /**
   *
   */
  public boolean isEngineeringOverrideEnabled()
  {
    return( engineeringOverrideEnabled );
  }


  /**
   *
   */
  public TelescopeState getTelescopeState()
  {
    return( telescopeState );
  }


  /**
   *
   */
  public SoftwareState getTCSState()
  {
    return( tcsState );
  }


  /**
   *
   */
  public boolean isSystemRestartImminent()
  {
    return( restartImminent );
  }


  /**
   *
   */
  public boolean isSystemShutdownImminent()
  {
    return( shutdownImminent );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_STATE_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_STATE_Done.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
