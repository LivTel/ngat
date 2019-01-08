package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_DataUnit.
 * <p>
 * List of type-safe enumerations of the Status Database data types
 * <p>
 * These are:
 * <ul>
 * <li>E_SDB_INVALID_UNITS</li>
 * <li>E_SDB_UNSUPPORTED_UNITS</li>
 * <li>E_SDB_NO_UNITS</li>
 * <li>E_SDB_MGAIN</li>
 * <li>E_SDB_MAS_UNITS</li>
 * <li>E_SDB_MKELVIN_UNITS</li>
 * <li>E_SDB_MCELSIUS_UNITS</li>
 * <li>E_SDB_MVOLT_UNITS</li>
 * <li>E_SDB_MAMP_UNITS</li>
 * <li>E_SDB_UVOLT_UNITS</li>
 * <li>E_SDB_UAMP_UNITS</li>
 * <li>E_SDB_MAMP_PER_VOLT_UNITS</li>
 * <li>E_SDB_BITS_PER_VOLT_UNITS</li>
 * <li>E_SDB_BITS_UNITS</li>
 * <li>E_SDB_MINUTES_UNITS</li>
 * <li>E_SDB_SEC_UNITS</li>
 * <li>E_SDB_MSEC_UNITS</li>
 * <li>E_SDB_USEC_UNITS</li>
 * <li>E_SDB_NSEC_UNITS</li>
 * <li>E_SDB_MASPERMS_UNITS</li>
 * <li>E_SDB_MASPERMSPERMS_UNITS</li>
 * <li>E_SDB_MNEWTONMETRES_UNITS</li>
 * <li>E_SDB_MNM_PER_AMP_UNITS</li>
 * <li>E_SDB_PROCSTATE_UNITS</li>
 * <li>E_SDB_TRUEFALSE_UNITS</li>
 * <li>E_SDB_ONOFF_UNITS</li>
 * <li>E_SDB_YESNO_UNITS</li>
 * <li>E_SDB_MPERCENT_UNITS</li>
 * <li>E_SDB_HERTZ_UNITS</li>
 * <li>E_SDB_SECPERYR_UNITS</li>
 * <li>E_SDB_MYR_UNITS</li>
 * <li>E_SDB_METREPERSEC_UNITS</li>
 * <li>E_SDB_MSEC_PER_YR</li>
 * <li>E_SDB_MAS_PER_YR</li>
 * <li>E_SDB_MSEC_PER_DAY</li>
 * <li>E_SDB_MAS_PER_DAY</li>
 * <li>E_SDB_SHUTTER_STATE_UNITS</li>
 * <li>E_SDB_CIL_ID_UNITS</li>
 * <li>E_SDB_AUTH_STATE_UNITS</li>
 * <li>E_SDB_BYTES_UNITS</li>
 * <li>E_SDB_KBYTES_UNITS</li>
 * <li>E_SDB_MBYTES_UNITS</li>
 * <li>E_SDB_RPM_UNITS</li>
 * <li>E_SDB_SYSREQ_UNITS</li>
 * <li>E_SDB_MM_PER_SEC_UNITS</li>
 * <li>E_SDB_UHERTZ_UNITS</li>
 * <li>E_SDB_MBAR_UNITS</li>
 * <li>E_SDB_OID_UNITS</li>
 * <li>E_SDB_INDEX_UNITS</li>
 * <li>E_SDB_BAR_UNITS</li>
 * <li>E_SDB_DCELSIUS_UNITS</li>
 * <li>E_SDB_LTR_PER_MIN_UNITS</li>
 * <li>E_SDB_MVERSION_UNITS</li>
 * <li>E_SDB_METRES_UNITS</li>
 * <li>E_SDB_MILLIMETRES_UNITS</li>
 * <li>E_SDB_MICRONS_UNITS</li>
 * <li>E_SDB_NANOMETRES_UNITS</li>
 * <li>E_SDB_HOURS_UNITS</li>
 * <li>E_SDB_MILLIDEGREES_UNITS</li>
 * <li>E_SDB_ARCSEC_UNITS</li>
 * <li>E_SDB_SSE_STRING_UNITS</li>
 * <li>E_SDB_NM_PER_CEL_UNITS</li>
 * <li>E_SDB_EPT_DATA_UNITS</li>
 * <li>E_SDB_SPT_DATA_UNITS</li>
 * <li>E_SDB_IET_DATA_UNITS</li>
 * <li>E_SDB_PACKAGE_ID_UNITS</li>
 * <li>E_SDB_TELESCOPE_UNITS</li>
 * <li>E_SDB_LITRES_UNITS</li>
 * <li>E_SDB_MILLILITRES_UNITS</li>
 * <li>E_SDB_MLTR_PER_MIN_UNITS</li>
 * <li>E_SDB_MPIXEL_UNITS</li>
 * <li>E_SDB_MSTARMAG_UNITS</li>
 * <li>E_SDB_UAS_UNITS</li>
 * <li>E_SDB_MPERCENT_RH_UNITS</li>
 * <li>E_SDB_PLC_BOL_UNITS</li>
 * <li>E_SDB_PLC_2_STATE_UNITS</li>
 * <li>E_SDB_PLC_4_STATE_UNITS</li>
 * <li>E_SDB_PLC_LIMIT_UNITS</li>
 * <li>E_SDB_PLC_SENSOR_UNITS</li>
 * <li>E_SDB_PLC_MOTOR_UNITS</li>
 * <li>E_SDB_PLC_ACTUATOR_UNITS</li>
 * <li>E_SDB_PLC_STATUS_UNITS</li>
 * <li>E_SDB_PLC_VERSION_UNITS</li>
 * <li>E_SDB_PLC_EOL_UNITS</li>
 * <li>E_SDB_MILLIDEG_PER_VOLT</li>
 * <li>E_SDB_MW_PERMPERM_UNITS</li>
 * <li>E_SDB_AGSTATE_UNITS</li>
 * <li>E_SDB_NOMORE_32BIT_UNITS</li>
 * <li>E_SDB_MSW_NO_UNITS</li>
 * <li>E_SDB_LSW_NO_UNITS</li>
 * <li>E_SDB_MSW_TTS_UNITS</li>
 * <li>E_SDB_LSW_TTS_UNITS</li>
 * <li>E_SDB_MSW_RAWENC_UNITS</li>
 * <li>E_SDB_LSW_RAWENC_UNITS</li>
 * <li>E_SDB_TTS_UNITS</li>
 * <li>E_SDB_RAWENC_UNITS</li>
 * <li>E_SDB_NOMORE_UNITS</li>
 * <li>E_SDB_UNITS_MAX_VALUE</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_DataUnit implements java.io.Serializable
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
   * Hashtable of instances for retrieval by the enumeration's String name.
   */
  protected static java.util.Hashtable nameHash = new java.util.Hashtable();

  /**
   * Hashtable of instances for retrieval by the enumeration's int value.
   */
  protected static java.util.Hashtable intHash = new java.util.Hashtable();

  /**
   * Index of the next enumeration to be added.
   */
  protected static int nextIndex = 0;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_INVALID_UNITS =
    new TTL_DataUnit( "E_SDB_INVALID_UNITS", 0 );

  /**
   * Indicate datum not supported at this time
   */
  public static TTL_DataUnit E_SDB_UNSUPPORTED_UNITS =
    new TTL_DataUnit( "E_SDB_UNSUPPORTED_UNITS", 1 );

  /**
   * Unit free measurement (counts
   */
  public static TTL_DataUnit E_SDB_NO_UNITS =
    new TTL_DataUnit( "E_SDB_NO_UNITS", 2 );

  /**
   * Gain factor x 1000
   */
  public static TTL_DataUnit E_SDB_MGAIN =
    new TTL_DataUnit( "E_SDB_MGAIN", 3 );

  /**
   * Milliarcseconds
   */
  public static TTL_DataUnit E_SDB_MAS_UNITS =
    new TTL_DataUnit( "E_SDB_MAS_UNITS", 4 );

  /**
   * Millikelvin
   */
  public static TTL_DataUnit E_SDB_MKELVIN_UNITS =
    new TTL_DataUnit( "E_SDB_MKELVIN_UNITS", 5 );

  /**
   * Millicelsius
   */
  public static TTL_DataUnit E_SDB_MCELSIUS_UNITS =
    new TTL_DataUnit( "E_SDB_MCELSIUS_UNITS", 6 );

  /**
   * Millivolts
   */
  public static TTL_DataUnit E_SDB_MVOLT_UNITS =
    new TTL_DataUnit( "E_SDB_MVOLT_UNITS", 7 );

  /**
   * Milliamperes
   */
  public static TTL_DataUnit E_SDB_MAMP_UNITS =
    new TTL_DataUnit( "E_SDB_MAMP_UNITS", 8 );

  /**
   * Microvolts
   */
  public static TTL_DataUnit E_SDB_UVOLT_UNITS =
    new TTL_DataUnit( "E_SDB_UVOLT_UNITS", 9 );

  /**
   * Microamperes
   */
  public static TTL_DataUnit E_SDB_UAMP_UNITS =
    new TTL_DataUnit( "E_SDB_UAMP_UNITS", 10 );

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_MAMP_PER_VOLT_UNITS =
    new TTL_DataUnit( "E_SDB_MAMP_PER_VOLT_UNITS", 11 );

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_BITS_PER_VOLT_UNITS =
    new TTL_DataUnit( "E_SDB_BITS_PER_VOLT_UNITS", 12 );

  /**
   * Bit field
   */
  public static TTL_DataUnit E_SDB_BITS_UNITS =
    new TTL_DataUnit( "E_SDB_BITS_UNITS", 13 );

  /**
   * Minutes
   */
  public static TTL_DataUnit E_SDB_MINUTES_UNITS =
    new TTL_DataUnit( "E_SDB_MINUTES_UNITS", 14 );

  /**
   * Seconds
   */
  public static TTL_DataUnit E_SDB_SEC_UNITS =
    new TTL_DataUnit( "E_SDB_SEC_UNITS", 15 );

  /**
   * Milliseconds
   */
  public static TTL_DataUnit E_SDB_MSEC_UNITS =
    new TTL_DataUnit( "E_SDB_MSEC_UNITS", 16 );

  /**
   * Microseconds
   */
  public static TTL_DataUnit E_SDB_USEC_UNITS =
    new TTL_DataUnit( "E_SDB_USEC_UNITS", 17 );

  /**
   * Nanoseconds
   */
  public static TTL_DataUnit E_SDB_NSEC_UNITS =
    new TTL_DataUnit( "E_SDB_NSEC_UNITS", 18 );

  /**
   * Milliarcseconds per millisecond
   */
  public static TTL_DataUnit E_SDB_MASPERMS_UNITS =
    new TTL_DataUnit( "E_SDB_MASPERMS_UNITS", 19 );

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_MASPERMSPERMS_UNITS =
    new TTL_DataUnit( "E_SDB_MASPERMSPERMS_UNITS", 20 );

  /**
   * Torque
   */
  public static TTL_DataUnit E_SDB_MNEWTONMETRES_UNITS =
    new TTL_DataUnit( "E_SDB_MNEWTONMETRES_UNITS", 21 );

  /**
   * Millinewton metres per ampere
   */
  public static TTL_DataUnit E_SDB_MNM_PER_AMP_UNITS =
    new TTL_DataUnit( "E_SDB_MNM_PER_AMP_UNITS", 22 );

  /**
   * TTL process state
   */
  public static TTL_DataUnit E_SDB_PROCSTATE_UNITS =
    new TTL_DataUnit( "E_SDB_PROCSTATE_UNITS", 23 );

  /**
   * Boolean True
   */
  public static TTL_DataUnit E_SDB_TRUEFALSE_UNITS =
    new TTL_DataUnit( "E_SDB_TRUEFALSE_UNITS", 24 );

  /**
   * Boolean On
   */
  public static TTL_DataUnit E_SDB_ONOFF_UNITS =
    new TTL_DataUnit( "E_SDB_ONOFF_UNITS", 25 );

  /**
   * Boolean Yes
   */
  public static TTL_DataUnit E_SDB_YESNO_UNITS =
    new TTL_DataUnit( "E_SDB_YESNO_UNITS", 26 );

  /**
   * Milli percentage (1000
   */
  public static TTL_DataUnit E_SDB_MPERCENT_UNITS =
    new TTL_DataUnit( "E_SDB_MPERCENT_UNITS", 27 );

  /**
   * Hertz for frequency
   */
  public static TTL_DataUnit E_SDB_HERTZ_UNITS =
    new TTL_DataUnit( "E_SDB_HERTZ_UNITS", 28 );

  /**
   * Seconds per year (eg proper motion of a star)
   */
  public static TTL_DataUnit E_SDB_SECPERYR_UNITS =
    new TTL_DataUnit( "E_SDB_SECPERYR_UNITS", 29 );

  /**
   * Milliyear
   */
  public static TTL_DataUnit E_SDB_MYR_UNITS =
    new TTL_DataUnit( "E_SDB_MYR_UNITS", 30 );

  /**
   * Metres per second
   */
  public static TTL_DataUnit E_SDB_METREPERSEC_UNITS =
    new TTL_DataUnit( "E_SDB_METREPERSEC_UNITS", 31 );

  /**
   * Milliseconds per year
   */
  public static TTL_DataUnit E_SDB_MSEC_PER_YR =
    new TTL_DataUnit( "E_SDB_MSEC_PER_YR", 32 );

  /**
   * Milliarcseconds per year
   */
  public static TTL_DataUnit E_SDB_MAS_PER_YR =
    new TTL_DataUnit( "E_SDB_MAS_PER_YR", 33 );

  /**
   * Milliseconds per day
   */
  public static TTL_DataUnit E_SDB_MSEC_PER_DAY =
    new TTL_DataUnit( "E_SDB_MSEC_PER_DAY", 34 );

  /**
   * Milliarcseconds per day
   */
  public static TTL_DataUnit E_SDB_MAS_PER_DAY =
    new TTL_DataUnit( "E_SDB_MAS_PER_DAY", 35 );

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_SHUTTER_STATE_UNITS =
    new TTL_DataUnit( "E_SDB_SHUTTER_STATE_UNITS", 36 );

  /**
   * CIL identifier
   */
  public static TTL_DataUnit E_SDB_CIL_ID_UNITS =
    new TTL_DataUnit( "E_SDB_CIL_ID_UNITS", 37 );

  /**
   * Requested
   */
  public static TTL_DataUnit E_SDB_AUTH_STATE_UNITS =
    new TTL_DataUnit( "E_SDB_AUTH_STATE_UNITS", 38 );

  /**
   * Bytes
   */
  public static TTL_DataUnit E_SDB_BYTES_UNITS =
    new TTL_DataUnit( "E_SDB_BYTES_UNITS", 39 );

  /**
   * Kilobytes (1024 bytes)
   */
  public static TTL_DataUnit E_SDB_KBYTES_UNITS =
    new TTL_DataUnit( "E_SDB_KBYTES_UNITS", 40 );

  /**
   * Megabytes (1024 kilobytes)
   */
  public static TTL_DataUnit E_SDB_MBYTES_UNITS =
    new TTL_DataUnit( "E_SDB_MBYTES_UNITS", 41 );

  /**
   * Revolutions per minute
   */
  public static TTL_DataUnit E_SDB_RPM_UNITS =
    new TTL_DataUnit( "E_SDB_RPM_UNITS", 42 );

  /**
   * System requests made to MCP
   */
  public static TTL_DataUnit E_SDB_SYSREQ_UNITS =
    new TTL_DataUnit( "E_SDB_SYSREQ_UNITS", 43 );

  /**
   * Milli metres per second
   */
  public static TTL_DataUnit E_SDB_MM_PER_SEC_UNITS =
    new TTL_DataUnit( "E_SDB_MM_PER_SEC_UNITS", 44 );

  /**
   * Micro hertz for frequency
   */
  public static TTL_DataUnit E_SDB_UHERTZ_UNITS =
    new TTL_DataUnit( "E_SDB_UHERTZ_UNITS", 45 );

  /**
   * Milli bar for (atmospheric) pressure
   */
  public static TTL_DataUnit E_SDB_MBAR_UNITS =
    new TTL_DataUnit( "E_SDB_MBAR_UNITS", 46 );

  /**
   * Oid contains an Oid as a value
   */
  public static TTL_DataUnit E_SDB_OID_UNITS =
    new TTL_DataUnit( "E_SDB_OID_UNITS", 47 );

  /**
   * Table or Array Offset
   */
  public static TTL_DataUnit E_SDB_INDEX_UNITS =
    new TTL_DataUnit( "E_SDB_INDEX_UNITS", 48 );

  /**
   * Bar for PLC fluid pressures
   */
  public static TTL_DataUnit E_SDB_BAR_UNITS =
    new TTL_DataUnit( "E_SDB_BAR_UNITS", 49 );

  /**
   * Deci celcius for PLC temperatures
   */
  public static TTL_DataUnit E_SDB_DCELSIUS_UNITS =
    new TTL_DataUnit( "E_SDB_DCELSIUS_UNITS", 50 );

  /**
   * Litres per minute for PLC flow rates
   */
  public static TTL_DataUnit E_SDB_LTR_PER_MIN_UNITS =
    new TTL_DataUnit( "E_SDB_LTR_PER_MIN_UNITS", 51 );

  /**
   * Milli version
   */
  public static TTL_DataUnit E_SDB_MVERSION_UNITS =
    new TTL_DataUnit( "E_SDB_MVERSION_UNITS", 52 );

  /**
   * Metres
   */
  public static TTL_DataUnit E_SDB_METRES_UNITS =
    new TTL_DataUnit( "E_SDB_METRES_UNITS", 53 );

  /**
   * Milli Metres
   */
  public static TTL_DataUnit E_SDB_MILLIMETRES_UNITS =
    new TTL_DataUnit( "E_SDB_MILLIMETRES_UNITS", 54 );

  /**
   * Microns
   */
  public static TTL_DataUnit E_SDB_MICRONS_UNITS =
    new TTL_DataUnit( "E_SDB_MICRONS_UNITS", 55 );

  /**
   * Nano metres
   */
  public static TTL_DataUnit E_SDB_NANOMETRES_UNITS =
    new TTL_DataUnit( "E_SDB_NANOMETRES_UNITS", 56 );

  /**
   * Hours
   */
  public static TTL_DataUnit E_SDB_HOURS_UNITS =
    new TTL_DataUnit( "E_SDB_HOURS_UNITS", 57 );

  /**
   * Milli degrees
   */
  public static TTL_DataUnit E_SDB_MILLIDEGREES_UNITS =
    new TTL_DataUnit( "E_SDB_MILLIDEGREES_UNITS", 58 );

  /**
   * Arc seconds
   */
  public static TTL_DataUnit E_SDB_ARCSEC_UNITS =
    new TTL_DataUnit( "E_SDB_ARCSEC_UNITS", 59 );

  /**
   * SSE encoded string
   */
  public static TTL_DataUnit E_SDB_SSE_STRING_UNITS =
    new TTL_DataUnit( "E_SDB_SSE_STRING_UNITS", 60 );

  /**
   * Nanometres per degree celsius
   */
  public static TTL_DataUnit E_SDB_NM_PER_CEL_UNITS =
    new TTL_DataUnit( "E_SDB_NM_PER_CEL_UNITS", 61 );

  /**
   * EPT Data State Units
   */
  public static TTL_DataUnit E_SDB_EPT_DATA_UNITS =
    new TTL_DataUnit( "E_SDB_EPT_DATA_UNITS", 62 );

  /**
   * SPT Data State Units
   */
  public static TTL_DataUnit E_SDB_SPT_DATA_UNITS =
    new TTL_DataUnit( "E_SDB_SPT_DATA_UNITS", 63 );

  /**
   * IET Data State Units
   */
  public static TTL_DataUnit E_SDB_IET_DATA_UNITS =
    new TTL_DataUnit( "E_SDB_IET_DATA_UNITS", 64 );

  /**
   * TTL Package ID Units
   */
  public static TTL_DataUnit E_SDB_PACKAGE_ID_UNITS =
    new TTL_DataUnit( "E_SDB_PACKAGE_ID_UNITS", 65 );

  /**
   * Identification of a telescope
   */
  public static TTL_DataUnit E_SDB_TELESCOPE_UNITS =
    new TTL_DataUnit( "E_SDB_TELESCOPE_UNITS", 66 );

  /**
   * Litres
   */
  public static TTL_DataUnit E_SDB_LITRES_UNITS =
    new TTL_DataUnit( "E_SDB_LITRES_UNITS", 67 );

  /**
   * Milli litres
   */
  public static TTL_DataUnit E_SDB_MILLILITRES_UNITS =
    new TTL_DataUnit( "E_SDB_MILLILITRES_UNITS", 68 );

  /**
   * Milli litres per minute
   */
  public static TTL_DataUnit E_SDB_MLTR_PER_MIN_UNITS =
    new TTL_DataUnit( "E_SDB_MLTR_PER_MIN_UNITS", 69 );

  /**
   * Milli pixels
   */
  public static TTL_DataUnit E_SDB_MPIXEL_UNITS =
    new TTL_DataUnit( "E_SDB_MPIXEL_UNITS", 70 );

  /**
   * Milli star magnitudes
   */
  public static TTL_DataUnit E_SDB_MSTARMAG_UNITS =
    new TTL_DataUnit( "E_SDB_MSTARMAG_UNITS", 71 );

  /**
   * Microarcseconds
   */
  public static TTL_DataUnit E_SDB_UAS_UNITS =
    new TTL_DataUnit( "E_SDB_UAS_UNITS", 72 );

  /**
   * Milli percent relative humidity
   */
  public static TTL_DataUnit E_SDB_MPERCENT_RH_UNITS =
    new TTL_DataUnit( "E_SDB_MPERCENT_RH_UNITS", 73 );

  /**
   * PLC device units beginning of list
   */
  public static TTL_DataUnit E_SDB_PLC_BOL_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_BOL_UNITS", 74 );

  /**
   * PLC 2 state device
   */
  public static TTL_DataUnit E_SDB_PLC_2_STATE_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_2_STATE_UNITS", 75 );

  /**
   * PLC 4 state device
   */
  public static TTL_DataUnit E_SDB_PLC_4_STATE_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_4_STATE_UNITS", 76 );

  /**
   * PLC Limit device
   */
  public static TTL_DataUnit E_SDB_PLC_LIMIT_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_LIMIT_UNITS", 77 );

  /**
   * PLC Sensor device
   */
  public static TTL_DataUnit E_SDB_PLC_SENSOR_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_SENSOR_UNITS", 78 );

  /**
   * PLC Motor device
   */
  public static TTL_DataUnit E_SDB_PLC_MOTOR_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_MOTOR_UNITS", 79 );

  /**
   * PLC Actuator device
   */
  public static TTL_DataUnit E_SDB_PLC_ACTUATOR_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_ACTUATOR_UNITS", 80 );

  /**
   * PLC status
   */
  public static TTL_DataUnit E_SDB_PLC_STATUS_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_STATUS_UNITS", 81 );

  /**
   * PLC version
   */
  public static TTL_DataUnit E_SDB_PLC_VERSION_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_VERSION_UNITS", 82 );

  /**
   * PLC device units end of list
   */
  public static TTL_DataUnit E_SDB_PLC_EOL_UNITS =
    new TTL_DataUnit( "E_SDB_PLC_EOL_UNITS", 83 );

  /**
   * Milli degrees per volt
   */
  public static TTL_DataUnit E_SDB_MILLIDEG_PER_VOLT =
    new TTL_DataUnit( "E_SDB_MILLIDEG_PER_VOLT", 84 );

  /**
   * Milli watts per metre squared
   */
  public static TTL_DataUnit E_SDB_MW_PERMPERM_UNITS =
    new TTL_DataUnit( "E_SDB_MW_PERMPERM_UNITS", 85 );

  /**
   * Autoguider System state units
   */
  public static TTL_DataUnit E_SDB_AGSTATE_UNITS =
    new TTL_DataUnit( "E_SDB_AGSTATE_UNITS", 86 );

  /**
   * No more 32 bit units in list
   */
  public static TTL_DataUnit E_SDB_NOMORE_32BIT_UNITS =
    new TTL_DataUnit( "E_SDB_NOMORE_32BIT_UNITS", 87 );

  /**
   * 
   */
  public static TTL_DataUnit E_SDB_MSW_NO_UNITS =
    new TTL_DataUnit( "E_SDB_MSW_NO_UNITS", 88 );

  /**
   * LSW of signed 64 bit unit free measurement
   */
  public static TTL_DataUnit E_SDB_LSW_NO_UNITS =
    new TTL_DataUnit( "E_SDB_LSW_NO_UNITS", 89 );

  /**
   * TTL TimeStamp Most Significant Word (sec)
   */
  public static TTL_DataUnit E_SDB_MSW_TTS_UNITS =
    new TTL_DataUnit( "E_SDB_MSW_TTS_UNITS", 90 );

  /**
   * TTL TimeStamp Least Significant Word (nsec)
   */
  public static TTL_DataUnit E_SDB_LSW_TTS_UNITS =
    new TTL_DataUnit( "E_SDB_LSW_TTS_UNITS", 91 );

  /**
   * Most Sig. Word of raw encoder counts
   */
  public static TTL_DataUnit E_SDB_MSW_RAWENC_UNITS =
    new TTL_DataUnit( "E_SDB_MSW_RAWENC_UNITS", 92 );

  /**
   * Least Sig. Word of raw encoder counts
   */
  public static TTL_DataUnit E_SDB_LSW_RAWENC_UNITS =
    new TTL_DataUnit( "E_SDB_LSW_RAWENC_UNITS", 93 );

  /**
   * TTL TimeStamp structure
   */
  public static TTL_DataUnit E_SDB_TTS_UNITS =
    new TTL_DataUnit( "E_SDB_TTS_UNITS", 94 );

  /**
   * Representation of 48 bit raw encoder counts
   */
  public static TTL_DataUnit E_SDB_RAWENC_UNITS =
    new TTL_DataUnit( "E_SDB_RAWENC_UNITS", 95 );

  /**
   * End of enumerated list of units
   */
  public static TTL_DataUnit E_SDB_NOMORE_UNITS =
    new TTL_DataUnit( "E_SDB_NOMORE_UNITS", 96 );

  /**
   * Req'd to force size to 4 bytes
   */
  public static TTL_DataUnit E_SDB_UNITS_MAX_VALUE =
    new TTL_DataUnit( "E_SDB_UNITS_MAX_VALUE", 97 );

  /**
   * Array allowing serialization.
   */
  protected static final TTL_DataUnit[] array =
  {
    E_SDB_INVALID_UNITS,
    E_SDB_UNSUPPORTED_UNITS,
    E_SDB_NO_UNITS,
    E_SDB_MGAIN,
    E_SDB_MAS_UNITS,
    E_SDB_MKELVIN_UNITS,
    E_SDB_MCELSIUS_UNITS,
    E_SDB_MVOLT_UNITS,
    E_SDB_MAMP_UNITS,
    E_SDB_UVOLT_UNITS,
    E_SDB_UAMP_UNITS,
    E_SDB_MAMP_PER_VOLT_UNITS,
    E_SDB_BITS_PER_VOLT_UNITS,
    E_SDB_BITS_UNITS,
    E_SDB_MINUTES_UNITS,
    E_SDB_SEC_UNITS,
    E_SDB_MSEC_UNITS,
    E_SDB_USEC_UNITS,
    E_SDB_NSEC_UNITS,
    E_SDB_MASPERMS_UNITS,
    E_SDB_MASPERMSPERMS_UNITS,
    E_SDB_MNEWTONMETRES_UNITS,
    E_SDB_MNM_PER_AMP_UNITS,
    E_SDB_PROCSTATE_UNITS,
    E_SDB_TRUEFALSE_UNITS,
    E_SDB_ONOFF_UNITS,
    E_SDB_YESNO_UNITS,
    E_SDB_MPERCENT_UNITS,
    E_SDB_HERTZ_UNITS,
    E_SDB_SECPERYR_UNITS,
    E_SDB_MYR_UNITS,
    E_SDB_METREPERSEC_UNITS,
    E_SDB_MSEC_PER_YR,
    E_SDB_MAS_PER_YR,
    E_SDB_MSEC_PER_DAY,
    E_SDB_MAS_PER_DAY,
    E_SDB_SHUTTER_STATE_UNITS,
    E_SDB_CIL_ID_UNITS,
    E_SDB_AUTH_STATE_UNITS,
    E_SDB_BYTES_UNITS,
    E_SDB_KBYTES_UNITS,
    E_SDB_MBYTES_UNITS,
    E_SDB_RPM_UNITS,
    E_SDB_SYSREQ_UNITS,
    E_SDB_MM_PER_SEC_UNITS,
    E_SDB_UHERTZ_UNITS,
    E_SDB_MBAR_UNITS,
    E_SDB_OID_UNITS,
    E_SDB_INDEX_UNITS,
    E_SDB_BAR_UNITS,
    E_SDB_DCELSIUS_UNITS,
    E_SDB_LTR_PER_MIN_UNITS,
    E_SDB_MVERSION_UNITS,
    E_SDB_METRES_UNITS,
    E_SDB_MILLIMETRES_UNITS,
    E_SDB_MICRONS_UNITS,
    E_SDB_NANOMETRES_UNITS,
    E_SDB_HOURS_UNITS,
    E_SDB_MILLIDEGREES_UNITS,
    E_SDB_ARCSEC_UNITS,
    E_SDB_SSE_STRING_UNITS,
    E_SDB_NM_PER_CEL_UNITS,
    E_SDB_EPT_DATA_UNITS,
    E_SDB_SPT_DATA_UNITS,
    E_SDB_IET_DATA_UNITS,
    E_SDB_PACKAGE_ID_UNITS,
    E_SDB_TELESCOPE_UNITS,
    E_SDB_LITRES_UNITS,
    E_SDB_MILLILITRES_UNITS,
    E_SDB_MLTR_PER_MIN_UNITS,
    E_SDB_MPIXEL_UNITS,
    E_SDB_MSTARMAG_UNITS,
    E_SDB_UAS_UNITS,
    E_SDB_MPERCENT_RH_UNITS,
    E_SDB_PLC_BOL_UNITS,
    E_SDB_PLC_2_STATE_UNITS,
    E_SDB_PLC_4_STATE_UNITS,
    E_SDB_PLC_LIMIT_UNITS,
    E_SDB_PLC_SENSOR_UNITS,
    E_SDB_PLC_MOTOR_UNITS,
    E_SDB_PLC_ACTUATOR_UNITS,
    E_SDB_PLC_STATUS_UNITS,
    E_SDB_PLC_VERSION_UNITS,
    E_SDB_PLC_EOL_UNITS,
    E_SDB_MILLIDEG_PER_VOLT,
    E_SDB_MW_PERMPERM_UNITS,
    E_SDB_AGSTATE_UNITS,
    E_SDB_NOMORE_32BIT_UNITS,
    E_SDB_MSW_NO_UNITS,
    E_SDB_LSW_NO_UNITS,
    E_SDB_MSW_TTS_UNITS,
    E_SDB_LSW_TTS_UNITS,
    E_SDB_MSW_RAWENC_UNITS,
    E_SDB_LSW_RAWENC_UNITS,
    E_SDB_TTS_UNITS,
    E_SDB_RAWENC_UNITS,
    E_SDB_NOMORE_UNITS,
    E_SDB_UNITS_MAX_VALUE
  };

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String name of this type-safe enumeration.
   */
  protected transient String name;

  /**
   * Optional integer for int representation of this Type-safe Enumeration.
   */
  protected transient int intValue;

  /**
   * Assign an index to this enumeration.
   */
  protected final int index = nextIndex++;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the TTL_DataUnit with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_DataUnit
   * @return a reference to the TTL_DataUnit specified by the argument
   */
  public static TTL_DataUnit getReference( String s )
  {
    return( (TTL_DataUnit)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_DataUnit with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_DataUnit
   * @return a reference to the TTL_DataUnit specified by the argument
   */
  public static TTL_DataUnit getReference( int i )
  {
    return( (TTL_DataUnit)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create an enumeration of the specified name.
   * <p>
   * <b>NOTE:</b> the <code><b>int</b></code> representation of this
   * enumeration is assigned to the index (index) of this enumeration in
   * the array.
   * @param s the name of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected TTL_DataUnit( String s )
  {
    name = s;
    nameHash.put( s, this );
    intValue = index;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected TTL_DataUnit( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_DataUnit.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_DataUnit.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Over-ride the Serializable method to ensure the same Object references
   * are returned after Serialization.
   */
  protected Object readResolve() throws java.io.ObjectStreamException
  {
    return( array[ index ] );
  }


  /**
   * Return the name of this enumeration.
   * @return name
   * @see #name
   */
  public String toString()
  {
    return name;
  }
}
/*
 *    $Date: 2013-07-04 10:55:29 $
 * $RCSfile: TTL_DataUnit.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_DataUnit.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 *
 */
