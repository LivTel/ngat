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
public class SHOW_MECHANISMS_Done extends CommandDone
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
  private double azimuthDemandPosition;

  /**
   *
   */
  private double azimuthActualPosition;

  /**
   *
   */
  private String azimuthStatus;

  /**
   *
   */
  private double altitudeDemandPosition;

  /**
   *
   */
  private double altitudeActualPosition;

  /**
   *
   */
  private String altitudeStatus;

  /**
   *
   */
  private double rotatorDemandPosition;

  /**
   *
   */
  private double rotatorActualPosition;

  /**
   *
   */
  private String rotatorStatus;

  /**
   *
   */
  private String rotatorMode;

  /**
   *
   */
  private double rotatorPositionAngle;

  /**
   *
   */
  private double enclosure1DemandPosition;

  /**
   *
   */
  private double enclosure1ActualPosition;

  /**
   *
   */
  private String enclosure1Status;

  /**
   *
   */
  private double enclosure2DemandPosition;

  /**
   *
   */
  private double enclosure2ActualPosition;

  /**
   *
   */
  private String enclosure2Status;

  /**
   *
   */
  private double foldMirrorDemandPosition;

  /**
   *
   */
  private double foldMirrorActualPosition;

  /**
   *
   */
  private String foldMirrorStatus;

  /**
   *
   */
  private String primaryMirrorStatus;

  /**
   *
   */
  private double primaryMirrorCoverDemandPosition;

  /**
   *
   */
  private double primaryMirrorCoverActualPosition;

  /**
   *
   */
  private double secondaryMirrorDemandPosition;

  /**
   *
   */
  private double secondaryMirrorActualPosition;

  /**
   *
   */
  private String secondaryMirrorStatus;

  /**
   *
   */
  private double focusOffset;

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
  public SHOW_MECHANISMS_Done( SHOW s,
			       double azDem,
			       double azAct,
			       String azStat,
			       double altDem,
			       double altAct,
			       String altStat,
			       double rotDem,
			       double rotAct,
			       String rotMod,
			       double rotPA,
			       String rotStat,
			       double enc1Dem,
			       double enc1Act,
			       String enc1Stat,
			       double enc2Dem,
			       double enc2Act,
			       String enc2Stat,
			       double fmDem,
			       double fmAct,
			       String fmStat,
			       double pmCovDem,
			       double pmCovAct,
			       String pmStat,
			       double smDem,
			       double smAct,
			       String smStat,
			       double focOff )
  {
    super( (Command)s );

    azimuthDemandPosition = azDem;
    azimuthActualPosition = azAct;
    azimuthStatus = azStat;
    altitudeDemandPosition = altDem;
    altitudeActualPosition = altAct;
    altitudeStatus = altStat;
    rotatorDemandPosition = rotDem;
    rotatorActualPosition = rotAct;
    rotatorStatus = rotStat;
    rotatorMode = rotMod;
    rotatorPositionAngle = rotPA;
    enclosure1DemandPosition = enc1Dem;
    enclosure1ActualPosition = enc1Act;
    enclosure1Status = enc1Stat;
    enclosure2DemandPosition = enc2Dem;
    enclosure2ActualPosition = enc2Act;
    enclosure2Status = enc2Stat;
    foldMirrorDemandPosition = fmDem;
    foldMirrorActualPosition = fmAct;
    foldMirrorStatus = fmStat;
    primaryMirrorCoverDemandPosition = pmCovDem;
    primaryMirrorCoverActualPosition = pmCovAct;
    primaryMirrorStatus = pmStat;
    secondaryMirrorDemandPosition = smDem;
    secondaryMirrorActualPosition = smAct;
    secondaryMirrorStatus = smStat;
    focusOffset = focOff;
  }


  /**
   *
   */
  public double getAzimuthDemandPosition()
  {
    return( azimuthDemandPosition );
  }


  /**
   *
   */
  public double getAzimuthActualPosition()
  {
    return( azimuthActualPosition );
  }


  /**
   *
   */
  public String getAzimuthStatus()
  {
    return( azimuthStatus );
  }


  /**
   *
   */
  public double getAltitudeDemandPosition()
  {
    return( altitudeDemandPosition );
  }


  /**
   *
   */
  public double getAltitudeActualPosition()
  {
    return( altitudeActualPosition );
  }


  /**
   *
   */
  public String getAltitudeStatus()
  {
    return( altitudeStatus );
  }


  /**
   *
   */
  public double getRotatorDemandPosition()
  { 
    return( rotatorDemandPosition );
  }


  /**
   *
   */
  public double getRotatorActualPosition()
  {
    return( rotatorActualPosition );
  }


  /**
   *
   */
  public String getRotatorStatus()
  {
    return( rotatorStatus );
  }


  /**
   *
   */
  public String getRotatorMode()
  {
    return( rotatorMode );
  }


  /**
   *
   */
  public double getRotatorPositionAngle()
  {
    return( rotatorPositionAngle );
  }


  /**
   *
   */
  public double getEnclosure1DemandPosition()
  {
    return( enclosure1DemandPosition );
  }


  /**
   *
   */
  public double getEnclosure1ActualPosition()
  {
    return( enclosure1ActualPosition );
  }


  /**
   *
   */
  public String getEnclosure1Status()
  {
    return( enclosure1Status );
  }


  /**
   *
   */
  public double getEnclosure2DemandPosition()
  {
    return( enclosure2DemandPosition );
  }


  /**
   *
   */
  public double getEnclosure2ActualPosition()
  {
    return( enclosure2ActualPosition );
  }


  /**
   *
   */
  public String getEnclosure2Status()
  {
    return( enclosure2Status );
  }


  /**
   *
   */
  public double getFoldMirrorDemandPosition()
  {
    return( foldMirrorDemandPosition );
  }


  /**
   *
   */
  public double getFoldMirrorActualPosition()
  {
    return( foldMirrorActualPosition );
  }


  /**
   *
   */
  public String getFoldMirrorStatus()
  {
    return( foldMirrorStatus );
  }


  /**
   *
   */
  public String getPrimaryMirrorStatus()
  {
    return( primaryMirrorStatus );
  }


  /**
   *
   */
  public double getPrimaryMirrorCoverDemandPosition()
  {
    return( primaryMirrorCoverDemandPosition );
  }


  /**
   *
   */
  public double getPrimaryMirrorCoverActualPosition()
  {
    return( primaryMirrorCoverActualPosition );
  }


  /**
   *
   */
  public double getSecondaryMirrorDemandPosition()
  {
    return( secondaryMirrorDemandPosition );
  }


  /**
   *
   */
  public double getSecondaryMirrorActualPosition()
  {
    return( secondaryMirrorActualPosition );
  }


  /**
   *
   */
  public String getSecondaryMirrorStatus()
  {
    return( secondaryMirrorStatus );
  }


  /**
   *
   */
  public double getFocusOffset()
  {
    return( focusOffset );
  }
}
/*
 *    $Date: 2006-11-20 14:47:33 $
 * $RCSfile: SHOW_MECHANISMS_Done.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOW_MECHANISMS_Done.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
