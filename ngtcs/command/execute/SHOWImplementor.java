package ngat.ngtcs.command.execute;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;
import ngat.ngtcs.command.*;

/**
 * Deliver Telescope and TCS status information to an RCS.  Includes
 * information necessary to build a FITS header.
 * 
 * 
 * @author $Author$
 * @version $Revision$
 */
public class SHOWImplementor extends CommandImplementor
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

  /**
   * The timeout for the SHOW command (60 seconds), in milliseconds.
   */
  public static final int TIMEOUT = 60000;

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


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create the SHOW command implementor using the specified thread, to
   * be executed on the specified telescope with the specified command.
   * @param eT the ExecutionThread executing this CommandImplementor
   * @param ts the Telescope on which this CommandImplementor is executing
   * @param c the Command (ngat.ngtcs.command.SHOW) to execute
   */
  public SHOWImplementor( Telescope ts, Command c )
  {
    super( ts, c );
  }


  /**
   * 
   */
  public void execute()
  {
    ShowType type = ( (SHOW)command ).getType();

    if( type == ShowType.ASTROMETRY )
    {
      sd.setAstrometryWavelength();
      sd.setAstrometryTemperature();
      sd.setAstrometryWavelength();
      sd.setAstrometryPressure();
      sd.setAstrometryHumidity();
      sd.setAstrometryUT1_UTC();
      sd.setAstrometryPole();
      sd.setAstrometryTDT_UTC();
      sd.setAstrometryAirmass();
      sd.setAstrometryAutoguiderWavelength();

      commandDone = (CommandDone)
	( new SHOW_ASTROMETRY_Done
	  () );
    }
    else if( type == ShowType.AUTOGUIDER )
    {
      sd.setAutoguiderName();
      sd.setAutoguiderStatus();
      sd.setAutoguiderSoftwareState();
      sd.setAutoguiderGuideStarMagnitude();
      sd.setAutoguiderGuideStarFWHM();
      sd.setAutoguiderMirrorDemandPosition();
      sd.setAutoguiderMirrorActualPosition();
      sd.setAutoguiderMirrorStatus();
      sd.setAutoguiderFocusDemandPosition();
      sd.setAutoguiderFocusActualPosition();
      sd.setAutoguiderFocusStatus();
      sd.setAutoguiderFilterDemandPosition();
      sd.setAutoguiderFilterDemandPosition();
      sd.setAutoguiderFilterDemandPosition();

      commandDone = (CommandDone)
	( new SHOW_AUTOGUIDER_Done
	  () );
    }
    else if( type == ShowType.CALIBRATE )
    {
      sd.setCalibrateDefaultAzimuthIndexError();
      sd.setCalibrateDefaultAltitudeIndexError();
      sd.setCalibrateDefaultRotatorIndexError();
      sd.setCalibrateCurrentAzimuthIndexError();
      sd.setCalibrateCurrentAltitudeIndexError();
      sd.setCalibrateCurrentRotatorIndexError();
      sd.setCalibrateLastCalibrationAzimuthIndexError();
      sd.setCalibrateLastCalibrationAzimuthRMS();
      sd.setCalibrateLastCalibrationAltitudeIndexError();
      sd.setCalibrateLastCalibrationAltitudeRMS();
      sd.setCalibrateLastCalibrationCollimationError();
      sd.setCalibrateLastCalibrationCollimationRMS();
      sd.setCalibrateLastCalibrationSkyRMS();

      commandDone = (CommandDone)
	( new SHOW_CALIBRATE_Done
	  () );
    }
    else if( type == ShowType.FOCAL_STATION )
    {
      sd.setFocalStation();
      sd.setFocalStationAutoguider();
    }
    else if( type == ShowType.LIMITS )
    {
      sd.setLimitsPositiveAzimuth();
      sd.setLimitsNegativeAzimuth();
      sd.setLimitsPositiveAltitude();
      sd.setLimitsNegativeAltitude();
      sd.setLimitsPositiveRotator();
      sd.setLimitsNegativeRotator();
      sd.setLimitsTimetoAzimuthLimit();
      sd.setLimitsTimeToAltitudeLimit();
      sd.setLimitsTimeToRotatorLimit();
      sd.setLimitsAzimtuhLimitSense();
      sd.setLimitsAltitudeLimitSense();
      sd.setLimitsRotatorLimitSense();

      commandDone = (CommandDone)
	( new SHOW_FOCAL_STATION_Done
	  () );
    }
    else if( type == ShowType.MECHANISMS )
    {
      sd.setMechanismsLatestMountDemandPosition();
      sd.setMechansimsLatestMountActualPosition();
      sd.setMechansimsMountStatus();
      sd.setMechansimsLatestRotatorDemandPosition();
      sd.setMechansimsLatestRotatorActualPosition();
      sd.setMechansimsRotatorStatus();
      sd.setMechansimsRotatorMode();
      sd.setMechansimsRotatorPositionAngle();
      sd.setMechansimsEnclosureDemandPosition();
      sd.setMechansimsEnclosureActualPosition();
      sd.setMechansimsEnclosureStatus();
      sd.setMechansimsFoldMirrorDemandPosition();
      sd.setMechansimsFoldMirrorActualPosition();
      sd.setMechansimsFoldMirrorStatus();
      sd.setMechansimsPrimaryMirrorStatus();
      sd.setMechansimsPrimaryMirrorCoverDemandPosition();
      sd.setMechansimsPrimaryMirrorCoverActualPosition();
      sd.setMechansimsSecondaryMirrorDemandPosition();
      sd.setMechansimsSecondaryMirrorActualPosition();
      sd.setMechansimsSecondaryMirrorStatus();
      sd.setMechansimsFocusOffset();

      commandDone = (CommandDone)
	( new SHOW_MECHANISMS_Done
	  () );
    }
    else if( type == ShowType.METEOROLOGY )
    {
      sd.setMeteorologyWMSStatus();
      sd.setMeteorologyRainAlarm();
      sd.setMeteorologyTrussTemperature();
      sd.setMeteorologyOilTemperature();
      sd.setMeteorologyPrimaryMirrorTemperature();
      sd.setMeteorologySecondaryMirrorTemperature();
      sd.setMeteorologyAirTemperature();
      sd.setMeteorologyWindSpeed();
      sd.setMeteorologyPressure();
      sd.setMeteorologyHumidity();
      sd.setMeteorologyWindDirection();
      sd.setMeteorologyMoistureLevel();
      sd.setMeteorologyDewPointTemperature();

      commandDone = (CommandDone)
	( new SHOW_METEOROLOGY_Done
	  () );
    }
    else if( type == ShowType.SOURCE )
    {
      sd.setSourceTargetName();
      sd.setSourceTargetRightAscension();
      sd.setSourceTargetDeclination();
      sd.setSourceTargetEquinox();
      sd.setSourceTargetEpoch();
      sd.setSourceTargetProperMotionRA();
      sd.setSourceTargetProperMotionDec();
      sd.setSourceTargetDifferentialRateRA();
      sd.setSourceTargetDifferentialRateDec();
      sd.setSourceTargetParallax();
      sd.setSourceTargetRadialVelocity();

      commandDone = (CommandDone)
	( new SHOW_SOURCE_Done
	  () );
    }
    else if( type == ShowType.STATE )
    {
      sd.setStateNetworkInterface();
      sd.setStateEngineeringOverride();
      sd.setStateTelescopeState();
      sd.setStateTCSState();
      // MCC-specified restarts/shutdowns - check MCC data
      sd.setStateSystemRestartImminent();
      sd.setStateSystemShutdownImminent();

      commandDone = (CommandDone)
	( new SHOW_STATE_Done
	  () );
    }
    else if( type == ShowType.TIME )
    {
      sd.setTimeMJD(); 
      sd.setTimeUT1();
      sd.setTimeLST();

      commandDone = (CommandDone)
	( new SHOW_TIME_Done
	  () );
   }
    else if( type == ShowType.VERSION )
    {
      sd.setVersion();

      commandDone = (CommandDone)
	( new SHOW_VERSION_Done
	  () );
    }
    else
    {
      commandDone.setReturnMessage
	( "This Implementor does NOT support ShowType ["+type.getName()+"]" );
      return;
    }
    commandDone.setSuccessful( true );
  }


  /**
   * Return the default timeout for this command execution.
   * @return TIMEOUT
   * @see #TIMEOUT
   */
  public int calcAcknowledgeTime()
  {
    return( TIMEOUT );
  }
}
/*
 *    $Date: 2013-07-04 10:26:08 $
 * $RCSfile: SHOWImplementor.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/execute/SHOWImplementor.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.3  2003/09/26 09:58:41  je
 *     Implemented public final static TIMEOUT and public abstract int calcAcknowledgeTime()
 *
 *     Revision 1.2  2003/09/22 13:24:36  je
 *     Added TTL TCS-Network-ICD documentation.
 *
 *     Revision 1.1  2003/07/01 10:12:55  je
 *     Initial revision
 *
 */
