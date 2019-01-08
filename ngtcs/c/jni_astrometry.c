/**
 * Library using slalib functions to implement the C layer of the 
 * SlalibAstrometricKernel class.  The SlalibAstrometricKernel class
 * is an implementation of the AstrometryCalculator interface used by
 * the NGTCS.
 *
 * All Java Class references end with `_cls', and all objects with `_obj'.
 * All Field IDs begin with the class name and then the field name.
 * All Method IDs begin with the class name then the method name.
 * Both Field and Method IDs and with _ID.
 * e.g. ThisClassName_thisFieldName_ID or ThisClassName_thisMethodName_ID.
 * 
 *  @author $Author$
 * @version $Revision$
 */

/*
 *     $Date: 2013-07-04 09:58:36 $
 *  $RCSfile: jni_astrometry.c,v $
 *   $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/c/jni_astrometry.c,v $
 *       $Id$
 *      $Log: not supported by cvs2svn $
*/
#include<string.h>

#include"ngat_ngtcs_JNIAstrometryCalculator.h"

#include"astro_structs.h"
#include"jni_astrometry.h"
#include"ngtcs_astrometry.h"


/*****************************************************************************/
/* These values hold the Java Environment variables                          */
/*****************************************************************************/
static jclass xyzmatrix_cls;
static jclass reportedtarget_cls;
static jclass timestampedposition_cls;
static jclass time_cls;

static jfieldID  AstrometryCalculator_outputEquinox_ID;
static jfieldID  AstrometryCalculator_rotatorMode_ID;

static jmethodID Target_getRA_ID;
static jmethodID Target_getDec_ID;
static jmethodID Target_getProperMotionRA_ID;
static jmethodID Target_getProperMotionDec_ID;
static jmethodID Target_getParallax_ID;
static jmethodID Target_getRadialVelocity_ID;
static jmethodID Target_getEquinox_ID;
static jmethodID Target_getEpoch_ID;
static jmethodID Target_getOffsetRA_ID;
static jmethodID Target_getOffsetDec_ID;
static jmethodID Target_getNonSiderealTrackingStart_ID;
static jmethodID Target_getNonSiderealTrackingRateRA_ID;
static jmethodID Target_getNonSiderealTrackingRateDec_ID;

static jmethodID Equinox_getInt_ID;
static jmethodID Equinox_getEpoch_ID;

static jmethodID Epoch_getDate_ID;
static jmethodID Epoch_getCalendarType_ID;
static jmethodID Epoch_getTimescaleType_ID;

static jmethodID CalendarType_getInt_ID;
static jmethodID TimescaleType_getInt_ID;

static jmethodID Timestamp_getSeconds_ID;
static jmethodID Timestamp_getNanoseconds_ID;
static jmethodID Timestamp_getCalendarType_ID;
static jmethodID Timestamp_getTimescaleType_ID;

static jmethodID MetData_getTemperatureKelvin_ID;
static jmethodID MetData_getPressure_ID;
static jmethodID MetData_getHumidity_ID;
static jmethodID MetData_getTroposphericLapseRate_ID;

static jmethodID IERSData_getUT1_UTC_ID;
static jmethodID IERSData_getTT_UT1_ID;
static jmethodID IERSData_getPolarMotionXRadians_ID;
static jmethodID IERSData_getPolarMotionYRadians_ID;
static jmethodID IERSData_getLeapseconds_ID;

static jmethodID SiteData_getLongitude_ID;
static jmethodID SiteData_getLatitude_ID;
static jmethodID SiteData_getAltitude_ID;

static jmethodID XYZMatrix_constructor_ID;
static jmethodID XYZMatrix_getX_ID;
static jmethodID XYZMatrix_getY_ID;
static jmethodID XYZMatrix_getZ_ID;

static jmethodID ReportedTarget_constructor_ID;

static jmethodID TimestampedPosition_constructor_ID;

static jmethodID Time_constructor_ID;

static jmethodID RotatorMode_getInt_ID;
static jmethodID RotatorMode_getTrackingFrame_ID;



void jni_initialisation_error( char *msg );



JNIEXPORT void JNICALL 
Java_ngat_ngtcs_JNIAstrometryCalculator_jniInitialise
( JNIEnv *env, jclass this_cls )
{
  /*************************************************************************/
  /* Class, Object and Method reference holders                            */
  /*************************************************************************/
  jclass equinox_cls, epoch_cls, calendar_cls, timescale_cls;
  jclass timestamp_cls, met_cls, site_cls, iers_cls, rotatormode_cls;
  jclass astrometrycalculator_cls, target_cls, _xyzmatrix_cls;
  jclass _reportedtarget_cls, _time_cls, _timestampedposition_cls;
  /*************************************************************************/
  /* Class references                                                      */
  /*************************************************************************/
  site_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/SiteData" );
  if( site_cls == NULL )
    {
      jni_initialisation_error( "cannot get SiteData"
				" class reference" );
      return;
    }

  iers_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/IERSData" );
  if( iers_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get IERSData"
				" class reference" );
      return;
    }

  met_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/MeteorologicalData" );
  if( met_cls == NULL ) 
    {
      jni_initialisation_error( "cannot MeteorologicalData"
				" class reference" );
      return;
    }

  target_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/Target" );
  if( target_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get Target"
				" class reference" );
      return;
    }

  equinox_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/Equinox" );
  if( equinox_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get Equinox"
				" class reference" );
      return;
    }

  epoch_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/Epoch" );
  if( epoch_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get Epoch"
				" class reference" );
      return;
    }

  calendar_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/CalendarType" );
  if( calendar_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get CalendarType"
				" class reference" );
      return;
    }

  timescale_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/TimescaleType" );
  if( timescale_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get TimescaleType"
				" class reference" );
      return;
    }

  timestamp_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/Timestamp" );
  if( timestamp_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get Timestamp"
				" class reference" );
      return;
    }

  astrometrycalculator_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/AstrometryCalculator" );
  if( astrometrycalculator_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get "
				"AstrometryCalculator class reference" );
      return;
    }

  rotatormode_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/RotatorMode" );
  if( rotatormode_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get "
				"RotatorMode class reference" );
      return;
    }

  _timestampedposition_cls = (*env)->FindClass
      ( env, "ngat/ngtcs/common/TimestampedPosition" );
  if( _timestampedposition_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get "
				"TimestampedPosition class reference" );
      return;
    }

  _xyzmatrix_cls = (*env)->FindClass
  ( env, "ngat/ngtcs/common/XYZMatrix" );
  if( _xyzmatrix_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get XYZMatrix"
				" class reference" );
      return;
    }

  _reportedtarget_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/ReportedTarget" );
  if( _reportedtarget_cls == NULL ) 
    {
      jni_initialisation_error( "cannot get ReportedTarget"
				" class reference" );
      return;
    }

  _time_cls = (*env)->FindClass
    ( env, "ngat/ngtcs/common/Time" );
  if( _time_cls == NULL )
    {
      jni_initialisation_error( "cannot get Time class reference" );
      return;
    }
  /*************************************************************************/
  /* Global references                                                     */
  /*************************************************************************/
  xyzmatrix_cls = (*env)->NewGlobalRef( env, _xyzmatrix_cls );
  if( xyzmatrix_cls == NULL ) 
    {
      jni_initialisation_error( "cannot set Global Reference"
				" xyzmatrix_cls" );
      return;
    }

  reportedtarget_cls = (*env)->NewGlobalRef( env, _reportedtarget_cls );
  if( reportedtarget_cls == NULL ) 
    {
      jni_initialisation_error( "cannot set Global Reference"
				" reportedtarget_cls" );
      return;
    }

  timestampedposition_cls = (*env)->NewGlobalRef
    ( env, _timestampedposition_cls );
  if( timestampedposition_cls == NULL ) 
    {
      jni_initialisation_error( "cannot set Global Reference"
				" timestampedposition_cls" );
      return;
    }

  time_cls = (*env)->NewGlobalRef( env, _time_cls );
  if( time_cls == NULL ) 
    {
      jni_initialisation_error( "cannot set Global Reference"
				" time_cls" );
      return;
    }
  /***************************************************************************/
  /* AstrometryCalculator field IDs                                          */
  /***************************************************************************/
  AstrometryCalculator_outputEquinox_ID = (*env)->GetFieldID
    ( env, astrometrycalculator_cls, "outputEquinox",
      "Lngat/ngtcs/common/Equinox;" );
  if( AstrometryCalculator_outputEquinox_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set fieldID"
				" AstrometryCalculator_outputEquinox_ID" );
      return;
    }

  AstrometryCalculator_rotatorMode_ID = (*env)->GetFieldID
    ( env, astrometrycalculator_cls, "rotatorMode",
      "Lngat/ngtcs/common/RotatorMode;" );
  if( AstrometryCalculator_rotatorMode_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set fieldID"
				" AstrometryCalculator_rotatorMode_ID" );
      return;
    }
  /***************************************************************************/
  /* SiteData method IDs                                                     */
  /***************************************************************************/
  SiteData_getLongitude_ID = (*env)->GetMethodID
      ( env, site_cls, "getLongitude", "()D" );
  if( SiteData_getLongitude_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"SiteData_getLongitude_ID" );
      return;
    }

  SiteData_getLatitude_ID = (*env)->GetMethodID
      ( env, site_cls, "getLatitude", "()D" );
  if( SiteData_getLatitude_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"SiteData_getLatitude_ID" );
      return;
    }

  SiteData_getAltitude_ID = (*env)->GetMethodID
      ( env, site_cls, "getAltitude", "()D" );
  if( SiteData_getAltitude_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"SiteData_getAltitude_ID" );
      return;
    }
  /***************************************************************************/
  /* IERSData method IDs                                                     */
  /***************************************************************************/
  IERSData_getUT1_UTC_ID = (*env)->GetMethodID
      ( env, iers_cls, "getUT1_UTC", "()D" );
  if( IERSData_getUT1_UTC_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"IERSData_getUT1_UTC_ID" );
      return;
    }

  IERSData_getTT_UT1_ID = (*env)->GetMethodID
      ( env, iers_cls, "getTT_UT1", "()D" );
  if( IERSData_getTT_UT1_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"IERSData_getTT_UT1_ID" );
      return;
    }

  IERSData_getPolarMotionXRadians_ID = (*env)->GetMethodID
      ( env, iers_cls, "getPolarMotionXRadians", "()D" );
  if( IERSData_getPolarMotionXRadians_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"IERSData_getPolarMotionXRadians_ID" );
      return;
    }

  IERSData_getPolarMotionYRadians_ID = (*env)->GetMethodID
      ( env, iers_cls, "getPolarMotionYRadians", "()D" );
  if( IERSData_getPolarMotionYRadians_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"IERSData_getPolarMotionYRadians_ID" );
      return;
    }

  IERSData_getLeapseconds_ID = (*env)->GetMethodID
      ( env, iers_cls, "getLeapseconds", "()I" );
  if( IERSData_getLeapseconds_ID == NULL )
    {
      jni_initialisation_error( "cannot set methodID " 
				"IERSData_getLeapseconds_ID" );
      return;
    }
  /***************************************************************************/
  /* Target method IDs                                                       */
  /***************************************************************************/
  Target_getRA_ID = (*env)->GetMethodID
      ( env, target_cls, "getRA", "()D" );
  if( Target_getRA_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getRA_ID" );
      return;
    }

  Target_getDec_ID = (*env)->GetMethodID
      ( env, target_cls, "getDec", "()D" );
  if( Target_getDec_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getDec_ID" );
      return;
    }

  Target_getProperMotionRA_ID = (*env)->GetMethodID
      ( env, target_cls, "getProperMotionRA", "()D" );
  if( Target_getProperMotionRA_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getProperMotionRA_ID" );
      return;
    }

  Target_getProperMotionDec_ID = (*env)->GetMethodID
      ( env, target_cls, "getProperMotionDec", "()D" );
  if( Target_getProperMotionDec_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getProperMotionDec_ID" );
      return;
    }

  Target_getParallax_ID = (*env)->GetMethodID
      ( env, target_cls, "getParallax", "()D" );
  if( Target_getParallax_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getParallax_ID" );
      return;
    }

  Target_getRadialVelocity_ID = (*env)->GetMethodID
      ( env, target_cls, "getRadialVelocity", "()D" );
  if( Target_getRadialVelocity_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getRadialVelocity_ID" );
      return;
    }

  Target_getEquinox_ID = (*env)->GetMethodID
      ( env, target_cls, "getEquinox", "()Lngat/ngtcs/common/Equinox;" );
  if( Target_getEquinox_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getEquinox_ID" );
      return;
    }

  Target_getEpoch_ID = (*env)->GetMethodID
      ( env, target_cls, "getEpoch", "()Lngat/ngtcs/common/Epoch;" );
  if( Target_getEpoch_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getEpoch_ID" );
      return;
    }

  Target_getOffsetRA_ID = (*env)->GetMethodID
      ( env, target_cls, "getOffsetRA", "()D" );
  if( Target_getOffsetRA_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getOffsetRA_ID" );
      return;
    }

  Target_getOffsetDec_ID = (*env)->GetMethodID
      ( env, target_cls, "getOffsetDec", "()D" );
  if( Target_getOffsetDec_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getOffsetDec_ID" );
      return;
    }

  Target_getNonSiderealTrackingStart_ID = (*env)->GetMethodID
      ( env, target_cls, "getNonSiderealTrackingStart",
	"()Lngat/ngtcs/common/Timestamp;" );
  if( Target_getNonSiderealTrackingStart_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getNonSiderealTrackingStart_ID" );
      return;
    }

  Target_getNonSiderealTrackingRateRA_ID = (*env)->GetMethodID
      ( env, target_cls, "getNonSiderealTrackingRateRA", "()D" );
  if( Target_getNonSiderealTrackingRateRA_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getNonSiderealTrackingRateRA_ID" );
      return;
    }

  Target_getNonSiderealTrackingRateDec_ID = (*env)->GetMethodID
      ( env, target_cls, "getNonSiderealTrackingRateDec", "()D" );
  if( Target_getNonSiderealTrackingRateDec_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Target_getNonSiderealTrackingRateDec_ID" );
      return;
    }
  /***************************************************************************/
  /* Equinox method IDs                                                      */
  /***************************************************************************/
  Equinox_getInt_ID = (*env)->GetMethodID
    ( env, equinox_cls, "getInt", "()I" );
  if( Equinox_getInt_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Equinox_getInt_ID" );
      return;
    }

  Equinox_getEpoch_ID = (*env)->GetMethodID
    ( env, equinox_cls, "getEpoch", "()Lngat/ngtcs/common/Epoch;" );
  if( Equinox_getEpoch_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Equinox_getEpoch_ID" );
      return;
    }
  /***************************************************************************/
  /* Epoch method IDs                                                        */
  /***************************************************************************/
  Epoch_getDate_ID = (*env)->GetMethodID
      ( env, epoch_cls, "getDate", "()D" );
  if( Epoch_getDate_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Epoch_getDate_ID" );
      return;
    }

  Epoch_getCalendarType_ID = (*env)->GetMethodID
      ( env, epoch_cls, "getCalendarType",
	"()Lngat/ngtcs/common/CalendarType;" );
  if( Epoch_getCalendarType_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Epoch_getCalendarType_ID" );
      return;
    }

  Epoch_getTimescaleType_ID = (*env)->GetMethodID
      ( env, epoch_cls, "getTimescaleType",
	"()Lngat/ngtcs/common/TimescaleType;" );
  if( Epoch_getTimescaleType_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Epoch_getTimescaleType_ID" );
      return;
    }
  /***************************************************************************/
  /* Calendar method IDs                                                     */
  /***************************************************************************/
  CalendarType_getInt_ID = (*env)->GetMethodID
      ( env, calendar_cls, "getInt", "()I" );
  if( CalendarType_getInt_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"CalendarType_getInt_ID" );
      return;
    }
  /***************************************************************************/
  /* Timescale method IDs                                                    */
  /***************************************************************************/
  TimescaleType_getInt_ID = (*env)->GetMethodID
      ( env, timescale_cls, "getInt", "()I" );
  if( TimescaleType_getInt_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"TimescaleType_getInt_ID" );
      return;
    }
  /***************************************************************************/
  /* Timestamp method IDs                                                    */
  /***************************************************************************/
  Timestamp_getSeconds_ID = (*env)->GetMethodID
      ( env, timestamp_cls, "getSeconds", "()J" );
  if( Timestamp_getSeconds_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Timestamp_getSeconds_ID" );
      return;
    }

  Timestamp_getNanoseconds_ID = (*env)->GetMethodID
      ( env, timestamp_cls, "getNanoseconds", "()J" ); 
  if( Timestamp_getNanoseconds_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Timestamp_getNanoseconds_ID" );
      return;
    }

  Timestamp_getCalendarType_ID = (*env)->GetMethodID
      ( env, timestamp_cls, "getCalendarType",
	"()Lngat/ngtcs/common/CalendarType;" );
  if( Timestamp_getCalendarType_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Timestamp_getCalendarType_ID" );
      return;
    }

  Timestamp_getTimescaleType_ID = (*env)->GetMethodID
      ( env, timestamp_cls, "getTimescaleType", 
	"()Lngat/ngtcs/common/TimescaleType;" );
  if( Timestamp_getTimescaleType_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Timestamp_getTimescaleType_ID" );
      return;
    }
  /***************************************************************************/
  /* MeteorologicalData method IDs                                           */
  /***************************************************************************/
  MetData_getTemperatureKelvin_ID = (*env)->GetMethodID
      ( env, met_cls, "getTemperatureKelvin", "()D" );
  if( MetData_getTemperatureKelvin_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"MetData_getTemperatureKelvin_ID" );
      return;
    }

  MetData_getPressure_ID = (*env)->GetMethodID
      ( env, met_cls, "getPressure", "()D" );
  if( MetData_getPressure_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"MetData_getPressure_ID" );
      return;
    }

  MetData_getHumidity_ID = (*env)->GetMethodID
      ( env, met_cls, "getHumidity", "()D" );
  if( MetData_getHumidity_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"MetData_getHumidity_ID" );
      return;
    }

  MetData_getTroposphericLapseRate_ID = (*env)->GetMethodID
      ( env, met_cls, "getTroposphericLapseRate", "()D" );
  if( MetData_getTroposphericLapseRate_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"MetData_getTroposphericLapseRate_ID" );
      return;
    }
  /***************************************************************************/
  /* ReportedTarget constructor ID                                           */
  /***************************************************************************/
  ReportedTarget_constructor_ID = (*env)->GetMethodID
      ( env, reportedtarget_cls, "<init>", 
	"(Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/XYZMatrix;"
	"Lngat/ngtcs/common/Timestamp;"
	"Lngat/ngtcs/common/Equinox;"
	"DDDD)V" );
  if( ReportedTarget_constructor_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"ReportedTarget_constructor_ID" );
      return;
    }
  /***************************************************************************/
  /* XYZMatrix constructor and method IDs                                    */
  /***************************************************************************/
  XYZMatrix_constructor_ID = (*env)->GetMethodID
    ( env, xyzmatrix_cls, "<init>", "(DDD)V" );
  if( XYZMatrix_constructor_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"XYZMatrix_constructor_ID" );
      return;
    }

  XYZMatrix_getX_ID = (*env)->GetMethodID
    ( env, xyzmatrix_cls, "getX", "()D" );
  if( XYZMatrix_getX_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"XYZMatrix_getX_ID" );
      return;
    }

  XYZMatrix_getY_ID = (*env)->GetMethodID
    ( env, xyzmatrix_cls, "getY", "()D" );
  if( XYZMatrix_getY_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"XYZMatrix_getY_ID" );
      return;
    }

  XYZMatrix_getZ_ID = (*env)->GetMethodID
    ( env, xyzmatrix_cls, "getZ", "()D" );
  if( XYZMatrix_getZ_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"XYZMatrix_getZ_ID" );
      return;
    }
  /***************************************************************************/
  /* RotatorMode method IDs                                                  */
  /***************************************************************************/
  RotatorMode_getInt_ID = (*env)->GetMethodID
    ( env, rotatormode_cls, "getInt", "()I" );
  if( RotatorMode_getInt_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"RotatorMode_getInt_ID" );
      return;
    }

  RotatorMode_getTrackingFrame_ID = (*env)->GetMethodID
    ( env, rotatormode_cls, "getTrackingFrame", 
      "()Lngat/ngtcs/common/Equinox;" );
  if( RotatorMode_getTrackingFrame_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"RotatorMode_getTrackingFrame_ID" );
      return;
    }
  /***************************************************************************/
  /* TimestampedPosition method IDs                                          */
  /***************************************************************************/
  TimestampedPosition_constructor_ID = (*env)->GetMethodID
    ( env, timestampedposition_cls, "<init>",
      "(Lngat/ngtcs/common/XYZMatrix;Lngat/ngtcs/common/Timestamp;)V" );
  if( TimestampedPosition_constructor_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"TimestampedPosition_constructor_ID" );
      return;
    }
  /***************************************************************************/
  /* Time method IDs                                                         */
  /***************************************************************************/
  Time_constructor_ID = (*env)->GetMethodID
    ( env, time_cls, "<init>",
      "(Lngat/ngtcs/common/Timestamp;DDDDDDDDDDDDD)V" );
  if( Time_constructor_ID == NULL ) 
    {
      jni_initialisation_error( "cannot set methodID " 
				"Time_constructor_ID" );
      return;
    }
  /***************************************************************************/
  return;
  /***************************************************************************/
}


JNIEXPORT jobject JNICALL 
Java_ngat_ngtcs_JNIAstrometryCalculator_jniCalcApparentPosition
( JNIEnv *env, jobject this, jobject timestamp_obj, jobject target_obj,
  jdouble j_wavelength )
{
  /***************************************************************************/
  /* Data holder                                                             */
  /***************************************************************************/
  struct all_data_struct all_data;
  /***************************************************************************/
  /* Java object reference                                                   */
  /***************************************************************************/
  jobject new_xyzmatrix_obj;
  /***************************************************************************/
  /* Parse Java objects                                                      */
  /***************************************************************************/
  if( jni_parse_target( env, target_obj, &(all_data.target ) ) == FAILED ) 
      return NULL;
  if( jni_parse_timestamp( env, timestamp_obj, 
			   &(all_data.timestamp) ) == FAILED ) return NULL;
  all_data.lambda = (double)j_wavelength;
  /***************************************************************************/
  /* Perform astrometry                                                      */
  /***************************************************************************/
  ast_init_generic_data( &all_data );
  ast_mean_2_apparent( &all_data );
  /***************************************************************************/
  /* Return new XYZMatrix                                                    */
  /***************************************************************************/
  new_xyzmatrix_obj = (*env)->NewObject( env, xyzmatrix_cls, 
					 XYZMatrix_constructor_ID, 
					 (jdouble)all_data.app_radec[0], 
					 (jdouble)all_data.app_radec[1], 
					 (jdouble)all_data.app_radec[2] );
  if( (*env)->ExceptionCheck( env ) ) return NULL;

  return new_xyzmatrix_obj;
  /**************************************************************************/
}


JNIEXPORT jobject JNICALL 
Java_ngat_ngtcs_JNIAstrometryCalculator_jniGeneratePositionDemand
( JNIEnv *env, jobject this, jobject timestamp_obj, jobject target_obj, 
  jdouble j_wavelength, jobject site_obj, jobject iers_obj, jobject met_obj )
{
  /***************************************************************************/
  /* Data holders                                                            */
  /***************************************************************************/
  struct all_data_struct all_data;
  /***************************************************************************/
  /* Java object reference                                                   */
  /***************************************************************************/
  jobject new_xyzmatrix_obj;
  /***************************************************************************/
  /* Parse Java objects                                                      */
  /***************************************************************************/
  if( jni_parse_timestamp( env, timestamp_obj, 
			   &(all_data.timestamp) ) == FAILED ) 
      return NULL;

  if( jni_parse_target( env, target_obj, &(all_data.target) ) == FAILED )
      return NULL;

  if( jni_parse_site_data( env, site_obj, &(all_data.site) ) == FAILED )
      return NULL;

  if( jni_parse_iers_data( env, iers_obj, &(all_data.iers) ) == FAILED )
    return NULL;

  if( jni_parse_met_data( env, met_obj, &(all_data.met) ) == FAILED )
    return NULL;
  all_data.lambda = (double)j_wavelength;
  /***************************************************************************/
  /* perform astrometry                                                      */
  /***************************************************************************/
  ast_init_generic_data( &all_data );
  ast_mean_2_apparent( &all_data );
  ast_LST_rotation( &all_data );
  ast_apparent_2_observed( &all_data );
  /***************************************************************************/
  /* Return new TimestampedPosition                                          */
  /***************************************************************************/
  new_xyzmatrix_obj = (*env)->NewObject( env, xyzmatrix_cls, 
					 XYZMatrix_constructor_ID,
					 (jdouble)all_data.observed[ 0 ], 
					 (jdouble)all_data.observed[ 1 ], 
					 (jdouble)all_data.observed[ 2 ] );
  if( (*env)->ExceptionCheck( env ) ) return NULL;

  return new_xyzmatrix_obj;
  /***************************************************************************/
}


JNIEXPORT jobject JNICALL 
Java_ngat_ngtcs_JNIAstrometryCalculator_jniGeneratePositionCoordinates
( JNIEnv *env, jobject this, jobject timestamp_obj, jobject mount_pos_obj,
  jdouble j_wavelength, jobject site_obj, jobject iers_obj, jobject met_obj )
{
  struct all_data_struct all_data;
  /***************************************************************************/
  /* Java object reference                                                   */
  /***************************************************************************/
  jobject output_eqx_obj, reported_obj, obs_radec_obj, unref_obj;
  jobject topo_hadec_obj, app_hadec_obj, app_radec_obj, sys_radec_obj;
  jobject output_obj;

  output_eqx_obj = (*env)->GetObjectField
      ( env, this, AstrometryCalculator_outputEquinox_ID );
  if( output_eqx_obj == NULL ) return NULL;
  /***************************************************************************/
  /* Parse Java objects                                                      */
  /***************************************************************************/
  if( jni_parse_timestamp( env, timestamp_obj, 
			   &(all_data.timestamp) ) == FAILED ) 
      return NULL;

  if( jni_parse_site_data( env, site_obj, &(all_data.site) ) == FAILED )
      return NULL;

  if( jni_parse_iers_data( env, iers_obj, &(all_data.iers) ) == FAILED )
      return NULL;

  if( jni_parse_met_data( env, met_obj, &(all_data.met) ) == FAILED )
      return NULL;

  if( jni_parse_timestamp( env, timestamp_obj, 
			   &(all_data.timestamp) ) == FAILED ) 
      return NULL;

  if( jni_parse_equinox
      ( env, output_eqx_obj, &(all_data.reported.output_eqx) ) == FAILED )
      return NULL;

  if( jni_parse_xyzmatrix( env, mount_pos_obj, 
			   all_data.reported.obs ) == FAILED )
      return NULL;

  all_data.lambda = (double)j_wavelength;
  /***************************************************************************/
  /* Perform the astrometry                                                  */
  /***************************************************************************/
  ast_init_generic_data( &all_data );
  ast_observed_2_apparent( &all_data );
  ast_LST_derotation( &all_data );
  ast_apparent_2_mean( &all_data );
  /***************************************************************************/
  /* Create a new ReportedTarget object and return it                        */
  /***************************************************************************/
  unref_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.unref[ 0 ], 
	all_data.reported.unref[ 1 ], 
	all_data.reported.unref[ 2 ] );
  if( unref_obj == NULL ) return NULL;

  obs_radec_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID,
	all_data.reported.obs_radec[ 0 ], 
	all_data.reported.obs_radec[ 1 ], 
	all_data.reported.obs_radec[ 2 ] );
  if( obs_radec_obj == NULL ) return NULL;

  topo_hadec_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.topo_hadec[ 0 ], 
	all_data.reported.topo_hadec[ 1 ], 
	all_data.reported.topo_hadec[ 2 ] );
  if( topo_hadec_obj == NULL ) return NULL;

  app_hadec_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.app_hadec[ 0 ], 
	all_data.reported.app_hadec[ 1 ], 
	all_data.reported.app_hadec[ 2 ] );
  if( app_hadec_obj == NULL ) return NULL;

  app_radec_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.app_radec[ 0 ],
	all_data.reported.app_radec[ 1 ], 
	all_data.reported.app_radec[ 2 ] );
  if( app_radec_obj == NULL ) return NULL;

  sys_radec_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.sys_radec[ 0 ],
	all_data.reported.sys_radec[ 1 ], 
	all_data.reported.sys_radec[ 2 ] );
  if( sys_radec_obj == NULL ) return NULL;

  output_obj = (*env)->NewObject
      ( env, xyzmatrix_cls, XYZMatrix_constructor_ID, 
	all_data.reported.output_radec[ 0 ], 
	all_data.reported.output_radec[ 1 ], 
	all_data.reported.output_radec[ 2 ] );
  if( output_obj == NULL ) return NULL;

  reported_obj = (*env)->NewObject
      ( env, reportedtarget_cls, ReportedTarget_constructor_ID,
	mount_pos_obj, obs_radec_obj, unref_obj, topo_hadec_obj,
	app_hadec_obj, app_radec_obj, sys_radec_obj, output_obj, 
	timestamp_obj, output_eqx_obj, 
	(jdouble)all_data.reported.airmass, 
	(jdouble)all_data.timestamp.lst, 
	(jdouble)all_data.timestamp.mjd_ut1, 
	(jdouble)all_data.timestamp.jep_tdb );

  return reported_obj;
  /***************************************************************************/
}


JNIEXPORT jobject JNICALL 
Java_ngat_ngtcs_JNIAstrometryCalculator_jniParseTimestamp
( JNIEnv *env, jobject this, jobject timestamp_obj,
  jobject site_obj, jobject iers_obj )
{
  struct all_data_struct all_data;
  jobject time_obj;
  /***************************************************************************/
  /* Parse Java Timestamp object                                             */
  /***************************************************************************/
  if( jni_parse_timestamp( env, timestamp_obj, 
			   &(all_data.timestamp) ) == FAILED ) 
    return NULL;
  /***************************************************************************/
  /* Parse the Timestamp values                                              */
  /***************************************************************************/
  ast_parse_timestamp( &all_data );
  /***************************************************************************/
  /* Create a new Time object and return it                                  */
  /***************************************************************************/
  time_obj = (*env)->NewObject
    ( env, time_cls, Time_constructor_ID, timestamp_obj,
      (jdouble)all_data.timestamp.lst,
      (jdouble)all_data.timestamp.mjd_ut1,
      (jdouble)all_data.timestamp.jep_ut1,
      (jdouble)all_data.timestamp.bep_ut1,
      (jdouble)all_data.timestamp.mjd_tai,
      (jdouble)all_data.timestamp.jep_tai,
      (jdouble)all_data.timestamp.bep_tai,
      (jdouble)all_data.timestamp.mjd_tdt,
      (jdouble)all_data.timestamp.jep_tdt,
      (jdouble)all_data.timestamp.bep_tdt,
      (jdouble)all_data.timestamp.mjd_tdb,
      (jdouble)all_data.timestamp.jep_tdb,
      (jdouble)all_data.timestamp.bep_tdb );

  return time_obj;
  /***************************************************************************/
}


/**
 *
 */
int jni_parse_timestamp( JNIEnv *env, jobject timestamp_obj,
			 struct timestamp_struct *timestamp )
{
  jobject cal_obj, tscale_obj;
  /***************************************************************************/
  /* Read Timestamp values                                                   */
  /***************************************************************************/
  cal_obj = (*env)->CallObjectMethod
      ( env, timestamp_obj, Timestamp_getCalendarType_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  tscale_obj = (*env)->CallObjectMethod
      ( env, timestamp_obj, Timestamp_getTimescaleType_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  timestamp->secs = (long)(*env)->CallLongMethod
    ( env, timestamp_obj, Timestamp_getSeconds_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  timestamp->nanosecs = (long)(*env)->CallLongMethod
    ( env, timestamp_obj, Timestamp_getNanoseconds_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  timestamp->cal_int = (Calendar)(*env)->CallIntMethod
      ( env, cal_obj, CalendarType_getInt_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  timestamp->ts_int = (Timescale)(*env)->CallIntMethod
      ( env, tscale_obj, TimescaleType_getInt_ID );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;
  /***************************************************************************/
  return SUCCEEDED;
  /***************************************************************************/
}


/**
 * Assign the variables from jobject `target' to the [astrometry_data] module.
 */
int jni_parse_target( JNIEnv *env, jobject target_obj, 
		      struct target_struct *target )
{
  /***************************************************************************/
  /* Data holders                                                            */
  /***************************************************************************/
  jobject   eqx_obj, eqx_epo_obj, eqx_epo_cal_obj, eqx_epo_ts_obj;
  jobject   epo_obj, epo_cal_obj, epo_ts_obj;
  /***************************************************************************/
  /* Read Target objects                                                     */
  /***************************************************************************/
  eqx_obj = (*env)->CallObjectMethod( env, target_obj, Target_getEquinox_ID );
  if( eqx_obj == NULL ) return FAILED;

  eqx_epo_obj = (*env)->CallObjectMethod( env, eqx_obj, Equinox_getEpoch_ID );
  if( eqx_epo_obj == NULL ) return FAILED;

  eqx_epo_cal_obj = (*env)->CallObjectMethod( env, eqx_epo_obj, 
					      Epoch_getCalendarType_ID );
  if( eqx_epo_cal_obj == NULL ) return FAILED;

  eqx_epo_ts_obj = (*env)->CallObjectMethod( env, eqx_epo_obj,
					     Epoch_getTimescaleType_ID );
  if( eqx_epo_ts_obj == NULL ) return FAILED;

  epo_obj = (*env)->CallObjectMethod( env, target_obj, Target_getEpoch_ID );
  if( epo_obj == NULL ) return FAILED;

  epo_cal_obj = (*env)->CallObjectMethod( env, epo_obj, 
					  Epoch_getCalendarType_ID );
  if( epo_cal_obj == NULL ) return FAILED;

  epo_ts_obj = (*env)->CallObjectMethod
      ( env, epo_obj, Epoch_getTimescaleType_ID );
  if( epo_ts_obj == NULL ) return FAILED;
  /***************************************************************************/
  /* Read objects values                                                     */
  /***************************************************************************/
  target->ra = (double)( (*env)->CallDoubleMethod
			 ( env, target_obj, Target_getRA_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->dec = (double)( (*env)->CallDoubleMethod
			  ( env, target_obj, Target_getDec_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->pm_ra = (double)( (*env)->CallDoubleMethod
			    ( env, target_obj, Target_getProperMotionRA_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->pm_dec = (double)( (*env)->CallDoubleMethod
			     ( env, target_obj, 
			       Target_getProperMotionDec_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->plx = (double)( (*env)->CallDoubleMethod
			  ( env, target_obj, Target_getParallax_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->rad_vel = (double)( (*env)->CallDoubleMethod
			      ( env, target_obj, 
				Target_getRadialVelocity_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  if( jni_parse_equinox( env, eqx_obj, &( target->eqx ) ) == FAILED )
      return FAILED;

  if( jni_parse_epoch( env, epo_obj, &( target->epo ) ) == FAILED )
      return FAILED;

  target->off_ra = (double)( (*env)->CallDoubleMethod
			     ( env, target_obj, Target_getOffsetRA_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->off_dec = (double)( (*env)->CallDoubleMethod
			      ( env, target_obj, Target_getOffsetDec_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->ns_ra  = (double)( (*env)->CallDoubleMethod
			     ( env, target_obj, 
			       Target_getNonSiderealTrackingRateRA_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  target->ns_dec = (double)( (*env)->CallDoubleMethod
			     ( env, target_obj, 
			       Target_getNonSiderealTrackingRateDec_ID ));
  if( (*env)->ExceptionCheck( env ) ) return FAILED;
  /***************************************************************************/
  return SUCCEEDED;
  /***************************************************************************/
}


/**
 *
 */
int jni_parse_iers_data( JNIEnv *env, jobject iers_obj,
			 struct iers_struct *iers )
{
    iers->ut1_utc = (double)( (*env)->CallDoubleMethod
			      ( env, iers_obj, IERSData_getUT1_UTC_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    iers->tt_ut1  = (double)( (*env)->CallDoubleMethod
			      ( env, iers_obj, IERSData_getTT_UT1_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    iers->polmo_x = (double)( (*env)->CallDoubleMethod
			      ( env, iers_obj, 
				IERSData_getPolarMotionXRadians_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    iers->polmo_y = (double)( (*env)->CallDoubleMethod
			      ( env, iers_obj, 
				IERSData_getPolarMotionYRadians_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    iers->tai_utc = (int)( (*env)->CallIntMethod
			   ( env, iers_obj, 
			     IERSData_getLeapseconds_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    return SUCCEEDED;
}


/**
 *
 */
int jni_parse_site_data( JNIEnv *env, jobject site_obj,
			 struct site_struct *site )
{
    site->longitude = (double)( (*env)->CallDoubleMethod
				( env, site_obj, 
				  SiteData_getLongitude_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    site->latitude  = (double)( (*env)->CallDoubleMethod
				( env, site_obj, 
				  SiteData_getLatitude_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    site->altitude  = (double)( (*env)->CallDoubleMethod
				( env, site_obj, 
				  SiteData_getAltitude_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    return SUCCEEDED;
}


/**
 *
 */
int jni_parse_met_data( JNIEnv *env, jobject met_obj,
			 struct met_struct *met )
{
    /*************************************************************************/
    /* Read MeteorologicalData values                                        */
    /*************************************************************************/
    met->temp_k = (double)( (*env)->CallDoubleMethod
			    ( env, met_obj, 
			      MetData_getTemperatureKelvin_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    met->pres = (double)( (*env)->CallDoubleMethod
			      ( env, met_obj, MetData_getPressure_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    met->hum = (double)( (*env)->CallDoubleMethod
			      ( env, met_obj, MetData_getHumidity_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    met->tlr = (double)( (*env)->CallDoubleMethod
			 ( env, met_obj, 
			   MetData_getTroposphericLapseRate_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    return SUCCEEDED;
}


/**
 *
 */
int jni_parse_equinox( JNIEnv *env, jobject eqx_obj,
		       struct equinox_struct *eqx )
{
    /*************************************************************************/
    jobject eqx_epo_obj;
    /*************************************************************************/
    eqx_epo_obj = (*env)->CallObjectMethod
	( env, eqx_obj, Equinox_getEpoch_ID );
    if( eqx_epo_obj == NULL ) return FAILED;
    /*************************************************************************/
    eqx->eqx_int = (Equinox)( (*env)->CallIntMethod
			      ( env, eqx_obj, Equinox_getInt_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;
    /*************************************************************************/
    if( jni_parse_epoch( env, eqx_epo_obj, &( eqx->epo ) ) == FAILED )
	return FAILED;
    /*************************************************************************/
    return SUCCEEDED;
    /*************************************************************************/
}


/**
 *
 */
int jni_parse_epoch( JNIEnv *env, jobject epo_obj,
		     struct epoch_struct *epo )
{
    /*************************************************************************/
    jobject cal_obj, ts_obj;
    /*************************************************************************/
    cal_obj = (*env)->CallObjectMethod( env, epo_obj, 
					Epoch_getCalendarType_ID );
    if( cal_obj == NULL ) return FAILED;

    ts_obj = (*env)->CallObjectMethod( env, epo_obj, Epoch_getTimescaleType_ID );
    if( ts_obj == NULL ) return FAILED;
    /*************************************************************************/
    epo->date = (double)( (*env)->CallDoubleMethod
			  ( env, epo_obj, Epoch_getDate_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    epo->cal_int = (Calendar)( (*env)->CallIntMethod
			       ( env, cal_obj, CalendarType_getInt_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;

    epo->ts_int = (Timescale)( (*env)->CallIntMethod
			       ( env, ts_obj, TimescaleType_getInt_ID ) );
    if( (*env)->ExceptionCheck( env ) ) return FAILED;
    /*************************************************************************/
    return SUCCEEDED;
    /*************************************************************************/
}


/**
 *
 */
int jni_parse_xyzmatrix( JNIEnv *env, jobject matrix_obj, double mat[] )
{
  mat[ 0 ] = (double)( (*env)->CallDoubleMethod
		       ( env, matrix_obj, XYZMatrix_getX_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  mat[ 1 ] = (double)( (*env)->CallDoubleMethod
		       ( env, matrix_obj, XYZMatrix_getY_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  mat[ 2 ] = (double)( (*env)->CallDoubleMethod
		       ( env, matrix_obj, XYZMatrix_getZ_ID ) );
  if( (*env)->ExceptionCheck( env ) ) return FAILED;

  return SUCCEEDED;
}


/**
 *
 */
void jni_initialisation_error( char *msg )
{
  fprintf( stderr, "\n%s\n\n", msg );
}
