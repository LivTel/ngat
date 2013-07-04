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
 * @version $Revision: 1.3 $
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

  public double calibrateDefaultAzimuthIndexError();
  public double calibrateDefaultAltitudeIndexError();
  public double calibrateDefaultRotatorIndexError();
  public double calibrateCurrentAzimuthIndexError();
  public double calibrateCurrentAltitudeIndexError();
  public double calibrateCurrentRotatorIndexError();
  public double calibrateLastCalibrationAzimuthIndexError();
  public double calibrateLastCalibrationAzimuthRMS();
  public double calibrateLastCalibrationAltitudeIndexError();
  public double calibrateLastCalibrationAltitudeRMS();
  public double calibrateLastCalibrationCollimationError();
  public double calibrateLastCalibrationCollimationRMS();
  public double calibrateLastCalibrationSkyRMS();

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
  public SHOWDone( SHOW s )
  {
    super( (Command)s );

    calibrateDefaultAzimuthIndexError();
    calibrateDefaultAltitudeIndexError();
    calibrateDefaultRotatorIndexError();
    calibrateCurrentAzimuthIndexError();
    calibrateCurrentAltitudeIndexError();
    calibrateCurrentRotatorIndexError();
    calibrateLastCalibrationAzimuthIndexError();
    calibrateLastCalibrationAzimuthRMS();
    calibrateLastCalibrationAltitudeIndexError();
    calibrateLastCalibrationAltitudeRMS();
    calibrateLastCalibrationCollimationError();
    calibrateLastCalibrationCollimationRMS();
    calibrateLastCalibrationSkyRMS();
  }


  public void setCalibrateDefaultAzimuthIndexError()
  {

  }


  public void setCalibrateDefaultAltitudeIndexError();
  {

  }


  public void setCalibrateDefaultRotatorIndexError();
  {

  }


  public void setCalibrateCurrentAzimuthIndexError();
  {

  }


  public void setCalibrateCurrentAltitudeIndexError();
  {

  }


  public void setCalibrateCurrentRotatorIndexError();
  {

  }


  public void setCalibrateLastCalibrationAzimuthIndexError();
  {

  }


  public void setCalibrateLastCalibrationAzimuthRMS();
  {

  }


  public void setCalibrateLastCalibrationAltitudeIndexError();
  {

  }


  public void setCalibrateLastCalibrationAltitudeRMS();
  {

  }


  public void setCalibrateLastCalibrationCollimationError();
  {

  }


  public void setCalibrateLastCalibrationCollimationRMS();
  {

  }


  public void setCalibrateLastCalibrationSkyRMS();
  {

  }
}
/*
 *    $Date: 2013-07-04 10:08:05 $
 * $RCSfile: SHOWDone.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHOWDone.java,v $
 *      $Id: SHOWDone.java,v 1.3 2013-07-04 10:08:05 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
