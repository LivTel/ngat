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
public class SHOW_AUTOGUIDER_Done extends CommandDone
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
  private String name;

  /**
   *
   */
  private String status;

  /**
   *
   */
  private String softwareState;

  /**
   *
   */
  private double guideStarMagnitude;

  /**
   *
   */
  private double guideStarFWHM;

  /**
   *
   */
  private double mirrorDemandPosition;

  /**
   *
   */
  private double mirrorActualPosition;

  /**
   *
   */
  private String mirrorStatus;

  /**
   *
   */
  private double focusDemandPosition;

  /**
   *
   */
  private double focusActualPosition;

  /**
   *
   */
  private String focusStatus;

  /**
   *
   */
  private String filterDemandPosition;

  /**
   *
   */
  private String filterActualPosition;

  /**
   *
   */
  private String filterStatus;

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
  public SHOW_AUTOGUIDER_Done( SHOW s,
			       String name,
			       String status,
			       String swStatus,
			       double mag,
			       double fwhm,
			       double mDem,
			       double mAct,
			       String mStatus,
			       double focDem,
			       double focAct,
			       String focStatus,
			       String filDem,
			       String filAct,
			       String filStatus )
  {
    super( (Command)s );

    name = name;
    status = status;
    softwareState = swStatus;
    guideStarMagnitude = mag;
    guideStarFWHM = fwhm;
    mirrorDemandPosition = mDem;
    mirrorActualPosition = mAct;
    mirrorStatus = mStatus;
    focusDemandPosition = focDem;
    focusActualPosition = focAct;
    focusStatus = focStatus;
    filterDemandPosition = filDem;
    filterActualPosition = filAct;
    filterStatus = filStatus;
  }


  /**
   *
   */
  public String getName()
  {
    return( name );
  }


  /**
   *
   */
  public String getStatus()
  {
    return( status );
  }


  /**
   *
   */
  public String getSoftwareState()
  {
    return( softwareState );
  }


  /**
   *
   */
  public double getGuideStarMagnitude()
  {
    return( guideStarMagnitude );
  }


  /**
   *
   */
  public double getGuideStarFWHM()
  {
    return( guideStarFWHM );
  }


  /**
   *
   */
  public double getMirrorDemandPosition()
  {
    return( mirrorDemandPosition );
  }


  /**
   *
   */
  public double getMirrorActualPosition()
  {
    return( mirrorActualPosition );
  }


  /**
   *
   */
  public String getMirrorStatus()
  {
    return( mirrorStatus );
  }


  /**
   *
   */
  public double getFocusDemandPosition()
  {
    return( focusDemandPosition );
  }


  /**
   *
   */
  public double getFocusActualPosition()
  {
    return( focusActualPosition );
  }


  /**
   *
   */
  public String getFocusStatus()
  {
    return( focusStatus );
  }


  /**
   *
   */
  public String getFilterDemandPosition()
  { 
    return( filterDemandPosition );
  }


  /**
   *
   */
  public String getFilterActualPosition()
  {
    return( filterActualPosition );
  }


  /**
   *
   */
  public String getFilterStatus()
  {
    return( filterStatus );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_AUTOGUIDER_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_AUTOGUIDER_Done.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
