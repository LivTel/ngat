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
 * @author $Author: cjm $
 * @version $Revision: 1.1 $
 */
public class SHOW_CALIBRATE_Done extends CommandDone
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
  public double defaultAzimuthIndexError;

  /**
   *
   */
  public double defaultAltitudeIndexError;

  /**
   *
   */
  public double defaultRotatorIndexError;

  /**
   *
   */
  public double currentAzimuthIndexError;

  /**
   *
   */
  public double currentAltitudeIndexError;

  /**
   *
   */
  public double currentRotatorIndexError;

  /**
   *
   */
  public double lastCalibrationAzimuthIndexError;
  /**
   *
   */

  public double lastCalibrationAzimuthRMS;

  /**
   *
   */
  public double lastCalibrationAltitudeIndexError;

  /**
   *
   */
  public double lastCalibrationAltitudeRMS;

  /**
   *
   */
  public double lastCalibrationCollimationError;

  /**
   *
   */
  public double lastCalibrationCollimationRMS;

  /**
   *
   */
  public double lastCalibrationSkyRMS;

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
  public SHOW_CALIBRATE_Done( SHOW s,
			      double defIA,
			      double defIE,
			      double defRotE,
			      double curIA,
			      double curIE,
			      double curRotE,
			      double lastIAerr,
			      double lastAzrms,
			      double lastIEerr,
			      double lastElrms,
			      double lastColerr,
			      double lastColrms,
			      double lastSkyrms )
  {
    super( (Command)s );

    defaultAzimuthIndexError = defIA;
    defaultAltitudeIndexError = defIE;
    defaultRotatorIndexError = defRotE;
    currentAzimuthIndexError = curIA;
    currentAltitudeIndexError = curIE;
    currentRotatorIndexError = curRotE;
    lastCalibrationAzimuthIndexError = lastIAerr;
    lastCalibrationAzimuthRMS = lastAzrms;
    lastCalibrationAltitudeIndexError = lastIEerr;
    lastCalibrationAltitudeRMS = lastElrms;
    lastCalibrationCollimationError = lastColerr;
    lastCalibrationCollimationRMS = lastColrms;
    lastCalibrationSkyRMS = lastSkyrms;
  }


  /**
   *
   */
  public double getDefaultAzimuthIndexError()
  {
    return( defaultAzimuthIndexError );
  }


  /**
   *
   */
  public double getDefaultAltitudeIndexError()
  {
    return( defaultAltitudeIndexError );
  }


  /**
   *
   */
  public double getDefaultRotatorIndexError()
  {
    return( defaultRotatorIndexError );
  }


  /**
   *
   */
  public double getCurrentAzimuthIndexError()
  {
    return( currentAzimuthIndexError );
  }


  /**
   *
   */
  public double getCurrentAltitudeIndexError()
  {
    return( currentAltitudeIndexError );
  }


  /**
   *
   */
  public double getCurrentRotatorIndexError()
  {
    return( currentRotatorIndexError );
  }


  /**
   *
   */
  public double getLastCalibrationAzimuthIndexError()
  {
    return( lastCalibrationAzimuthIndexError );
  }


  /**
   *
   */
  public double getLastCalibrationAzimuthRMS()
  {
    return( lastCalibrationAzimuthRMS );
  }


  /**
   *
   */
  public double getLastCalibrationAltitudeIndexError()
  {
    return( lastCalibrationAltitudeIndexError );
  }


  /**
   *
   */
  public double getLastCalibrationAltitudeRMS()
  {
    return( lastCalibrationAltitudeRMS );
  }


  /**
   *
   */
  public double getLastCalibrationCollimationError()
  {
    return( lastCalibrationCollimationError );
  }


  /**
   *
   */
  public double getLastCalibrationCollimationRMS()
  {
    return( lastCalibrationCollimationRMS );
  }


  /**
   *
   */
  public double getLastCalibrationSkyRMS()
  {
    return( lastCalibrationSkyRMS );
  }
}
/*
 *    $Date:
 * $RCSfile:
 *  $Source:
 *      $Id:
 *     $Log:
 *
 */
