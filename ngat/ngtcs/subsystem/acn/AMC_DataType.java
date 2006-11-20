package ngat.ngtcs.subsystem.acn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AMC_DataType.  These are:
 * <ul>
 * <li>D_AMC_DATAID_BOL</li>
 * <li>D_AMC_PROC_STATE</li>
 * <li>D_AMC_APP_VERSION</li>
 * <li>D_AMC_SERVER_SHUTDOWN</li>
 * <li>D_AMC_STATE</li>
 * <li>D_AMC_MOVE_ENABLE</li>
 * <li>D_AMC_AXIS_MOVE_FAILURE</li>
 * <li>D_AMC_MOVE_COMMAND</li>
 * <li>D_AMC_TRACK_COMMAND</li>
 * <li>D_AMC_HALT_COMMAND</li>
 * <li>D_AMC_TARGET_TIME</li>
 * <li>D_AMC_TARGET_TIME_DUMMY</li>
 * <li>D_AMC_TARGET_POSITION</li>
 * <li>D_AMC_WORKING_TARGET_TIME</li>
 * <li>D_AMC_WORKING_TARGET_TIME_DUMMY</li>
 * <li>D_AMC_WORKING_TARGET_POSITION</li>
 * <li>D_AMC_SINUSOID_COMMAND</li>
 * <li>D_AMC_SINUSOID_AMPLITUDE</li>
 * <li>D_AMC_SINUSOID_FREQUENCY</li>
 * <li>D_AMC_AXIS_POSITION_DEMAND</li>
 * <li>D_AMC_SIMULATION_ACTIVE</li>
 * <li>D_AMC_CLOCKWISE_LIMIT_ACTIVE</li>
 * <li>D_AMC_ACW_LIMIT_ACTIVE</li>
 * <li>D_AMC_DISTANCE_TO_CW_LIMIT</li>
 * <li>D_AMC_DISTANCE_TO_ACW_LIMIT</li>
 * <li>D_AMC_RECOVER_COMMAND</li>
 * <li>D_AMC_LIMIT_RECOVERY_DISTANCE</li>
 * <li>D_AMC_PRELOAD_APPLICATION_PERIOD</li>
 * <li>D_AMC_PEAK_TRACKING_ERROR</li>
 * <li>D_AMC_RMS_TRACKING_ERROR</li>
 * <li>D_AMC_AXIS_POSITION</li>
 * <li>D_AMC_PID_P1_GAIN</li>
 * <li>D_AMC_PID_P2_GAIN</li>
 * <li>D_AMC_PID_K1_GAIN</li>
 * <li>D_AMC_INTEGRATOR_WIND_UP_LIMIT</li>
 * <li>D_AMC_INTEGRATOR_LIMIT_REACHED</li>
 * <li>D_AMC_HOMING_VELOCITY</li>
 * <li>D_AMC_MAX_HOMING_DURATION</li>
 * <li>D_AMC_CURRENT_AXIS_VELOCITY</li>
 * <li>D_AMC_ENABLE_MOTION_TESTING</li>
 * <li>D_AMC_MOTION_CONTROL_TEST_MODE</li>
 * <li>D_AMC_START_LOGGING_COMMAND</li>
 * <li>D_AMC_VELOCITY_LIMIT</li>
 * <li>D_AMC_ACCELERATION_LIMIT</li>
 * <li>D_AMC_VELOCITY_CONTROL_DC_GAIN</li>
 * <li>D_AMC_MOTOR_1_VELOCITY</li>
 * <li>D_AMC_MOTOR_2_VELOCITY</li>
 * <li>D_AMC_MOTOR_VELOCITY_SUM</li>
 * <li>D_AMC_MOTOR_VELOCITY_DIFF</li>
 * <li>D_AMC_MTR_VEL_FEEDBACK_FRACTION</li>
 * <li>D_AMC_MTR_VEL_TRQ_CORRECTION</li>
 * <li>D_AMC_SERVO_CTRL_ALGORITHM_TYPE</li>
 * <li>D_AMC_SERVO_CTRL_ALGORITHM_VER</li>
 * <li>D_AMC_INTERRUPT_STUTTER_LIMIT</li>
 * <li>D_AMC_INTERRUPT_STUTTER_IGNORE</li>
 * <li>D_AMC_DATAID_EOL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class AMC_DataType
  implements java.io.Serializable, ngat.ngtcs.subsystem.TTL_DataType
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
    new String( "$Id: AMC_DataType.java,v 1.1 2006-11-20 14:46:32 cjm Exp $" );

  /**
   * Hashtable of instances for retrieval by the enumeration's String name.
   */
  private static java.util.Hashtable nameHash = new java.util.Hashtable();

  /**
   * Hashtable of instances for retrieval by the enumeration's int value.
   */
  private static java.util.Hashtable intHash = new java.util.Hashtable();

  /**
   * Index of the next enumeration to be added.
   */
  private static int nextIndex = 0;

  /**
   * TTL package to which this data refers.
   */
  private static final TTL_Package ttlPackage = TTL_Package.AMC;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static AMC_DataType D_AMC_DATAID_BOL =
      new AMC_DataType( "D_AMC_DATAID_BOL", 0 );

  /**
   * The AMC process state.
   */
  public final static AMC_DataType D_AMC_PROC_STATE =
      new AMC_DataType( "D_AMC_PROC_STATE", 1 );

  /**
   * TFP package revision.
   */
  public final static AMC_DataType D_AMC_APP_VERSION =
      new AMC_DataType( "D_AMC_APP_VERSION", 2 );

  /**
   * Server shutdown command flag.
   */
  public final static AMC_DataType D_AMC_SERVER_SHUTDOWN =
      new AMC_DataType( "D_AMC_SERVER_SHUTDOWN", 3 );

  /**
   * The current FSM state.
   */
  public final static AMC_DataType D_AMC_STATE =
      new AMC_DataType( "D_AMC_STATE", 4 );

  /**
   * The Enable this movement function.
   */
  public final static AMC_DataType D_AMC_MOVE_ENABLE =
      new AMC_DataType( "D_AMC_MOVE_ENABLE", 5 );

  /**
   * Failed while moving axis.
   */
  public final static AMC_DataType D_AMC_AXIS_MOVE_FAILURE =
      new AMC_DataType( "D_AMC_AXIS_MOVE_FAILURE", 6 );

  /**
   * Trigger a MOVE command.
   */
  public final static AMC_DataType D_AMC_MOVE_COMMAND =
      new AMC_DataType( "D_AMC_MOVE_COMMAND", 7 );

  /**
   * Trigger a TRACK command.
   */
  public final static AMC_DataType D_AMC_TRACK_COMMAND =
      new AMC_DataType( "D_AMC_TRACK_COMMAND", 8 );

  /**
   * Trigger a HALT command.
   */
  public final static AMC_DataType D_AMC_HALT_COMMAND =
      new AMC_DataType( "D_AMC_HALT_COMMAND", 9 );

  /**
   * The target time for a new TRACK command.
   */
  public final static AMC_DataType D_AMC_TARGET_TIME =
      new AMC_DataType( "D_AMC_TARGET_TIME", 10 );

  /**
   * The target time for a new TRACK command.
   */
  public final static AMC_DataType D_AMC_TARGET_TIME_DUMMY =
      new AMC_DataType( "D_AMC_TARGET_TIME_DUMMY", 11 );

  /**
   * The target position for a new TRK or MV.
   */
  public final static AMC_DataType D_AMC_TARGET_POSITION =
      new AMC_DataType( "D_AMC_TARGET_POSITION", 12 );

  /**
   * Snapshot of current target time (nS).
   */
  public final static AMC_DataType D_AMC_WORKING_TARGET_TIME =
      new AMC_DataType( "D_AMC_WORKING_TARGET_TIME", 13 );

  /**
   * Snapshot of current target time (Secs.
   */
  public final static AMC_DataType D_AMC_WORKING_TARGET_TIME_DUMMY =
      new AMC_DataType( "D_AMC_WORKING_TARGET_TIME_DUMMY", 14 );

  /**
   * Snapshot of current target position.
   */
  public final static AMC_DataType D_AMC_WORKING_TARGET_POSITION =
      new AMC_DataType( "D_AMC_WORKING_TARGET_POSITION", 15 );

  /**
   * Trigger a SINUSOID command.
   */
  public final static AMC_DataType D_AMC_SINUSOID_COMMAND =
      new AMC_DataType( "D_AMC_SINUSOID_COMMAND", 16 );

  /**
   * Amplitude for new SINE command.
   */
  public final static AMC_DataType D_AMC_SINUSOID_AMPLITUDE =
      new AMC_DataType( "D_AMC_SINUSOID_AMPLITUDE", 17 );

  /**
   * Frequency for new SINE command.
   */
  public final static AMC_DataType D_AMC_SINUSOID_FREQUENCY =
      new AMC_DataType( "D_AMC_SINUSOID_FREQUENCY", 18 );

  /**
   * Position demand to the axis.
   */
  public final static AMC_DataType D_AMC_AXIS_POSITION_DEMAND =
      new AMC_DataType( "D_AMC_AXIS_POSITION_DEMAND", 19 );

  /**
   * Enable SIMULATION mode.
   */
  public final static AMC_DataType D_AMC_SIMULATION_ACTIVE =
      new AMC_DataType( "D_AMC_SIMULATION_ACTIVE", 20 );

  /**
   * The clockwise limit status.
   */
  public final static AMC_DataType D_AMC_CLOCKWISE_LIMIT_ACTIVE =
      new AMC_DataType( "D_AMC_CLOCKWISE_LIMIT_ACTIVE", 21 );

  /**
   * The anti clockwise limit status.
   */
  public final static AMC_DataType D_AMC_ACW_LIMIT_ACTIVE =
      new AMC_DataType( "D_AMC_ACW_LIMIT_ACTIVE", 22 );

  /**
   * The distance to the clockwise L1 limit.
   */
  public final static AMC_DataType D_AMC_DISTANCE_TO_CW_LIMIT =
      new AMC_DataType( "D_AMC_DISTANCE_TO_CW_LIMIT", 23 );

  /**
   * The distance to the anti cw L1 limit.
   */
  public final static AMC_DataType D_AMC_DISTANCE_TO_ACW_LIMIT =
      new AMC_DataType( "D_AMC_DISTANCE_TO_ACW_LIMIT", 24 );

  /**
   * Trigger a recovery from a limit.
   */
  public final static AMC_DataType D_AMC_RECOVER_COMMAND =
      new AMC_DataType( "D_AMC_RECOVER_COMMAND", 25 );

  /**
   * Distance to recover beyond a limit.
   */
  public final static AMC_DataType D_AMC_LIMIT_RECOVERY_DISTANCE =
      new AMC_DataType( "D_AMC_LIMIT_RECOVERY_DISTANCE", 26 );

  /**
   * Duration to apply preload over.
   */
  public final static AMC_DataType D_AMC_PRELOAD_APPLICATION_PERIOD =
      new AMC_DataType( "D_AMC_PRELOAD_APPLICATION_PERIOD", 27 );

  /**
   * Control loop peak tracking error.
   */
  public final static AMC_DataType D_AMC_PEAK_TRACKING_ERROR =
      new AMC_DataType( "D_AMC_PEAK_TRACKING_ERROR", 28 );

  /**
   * Control loop RMS tracking error.
   */
  public final static AMC_DataType D_AMC_RMS_TRACKING_ERROR =
      new AMC_DataType( "D_AMC_RMS_TRACKING_ERROR", 29 );

  /**
   * The current axis position.
   */
  public final static AMC_DataType D_AMC_AXIS_POSITION =
      new AMC_DataType( "D_AMC_AXIS_POSITION", 30 );

  /**
   * PID controller P1 gain x 1e9.
   */
  public final static AMC_DataType D_AMC_PID_P1_GAIN =
      new AMC_DataType( "D_AMC_PID_P1_GAIN", 31 );

  /**
   * PID controller P2 gain x 1e9.
   */
  public final static AMC_DataType D_AMC_PID_P2_GAIN =
      new AMC_DataType( "D_AMC_PID_P2_GAIN", 32 );

  /**
   * PID controller K1 gain x 1e9.
   */
  public final static AMC_DataType D_AMC_PID_K1_GAIN =
      new AMC_DataType( "D_AMC_PID_K1_GAIN", 33 );

  /**
   * PID controller integrator limit value.
   */
  public final static AMC_DataType D_AMC_INTEGRATOR_WIND_UP_LIMIT =
      new AMC_DataType( "D_AMC_INTEGRATOR_WIND_UP_LIMIT", 34 );

  /**
   * PID controller integrator limit status.
   */
  public final static AMC_DataType D_AMC_INTEGRATOR_LIMIT_REACHED =
      new AMC_DataType( "D_AMC_INTEGRATOR_LIMIT_REACHED", 35 );

  /**
   * Homing velocity magnitude.
   */
  public final static AMC_DataType D_AMC_HOMING_VELOCITY =
      new AMC_DataType( "D_AMC_HOMING_VELOCITY", 36 );

  /**
   * Maximum homing duration before timeout.
   */
  public final static AMC_DataType D_AMC_MAX_HOMING_DURATION =
      new AMC_DataType( "D_AMC_MAX_HOMING_DURATION", 37 );

  /**
   * The current axis velocity in as
   */
  public final static AMC_DataType D_AMC_CURRENT_AXIS_VELOCITY =
      new AMC_DataType( "D_AMC_CURRENT_AXIS_VELOCITY", 38 );

  /**
   * Enable motion control test mode.
   */
  public final static AMC_DataType D_AMC_ENABLE_MOTION_TESTING =
      new AMC_DataType( "D_AMC_ENABLE_MOTION_TESTING", 39 );

  /**
   * The mode of operation for testing.
   */
  public final static AMC_DataType D_AMC_MOTION_CONTROL_TEST_MODE =
      new AMC_DataType( "D_AMC_MOTION_CONTROL_TEST_MODE", 40 );

  /**
   * Start logging data to log file.
   */
  public final static AMC_DataType D_AMC_START_LOGGING_COMMAND =
      new AMC_DataType( "D_AMC_START_LOGGING_COMMAND", 41 );

  /**
   * Axis velocity limit in as
   */
  public final static AMC_DataType D_AMC_VELOCITY_LIMIT =
      new AMC_DataType( "D_AMC_VELOCITY_LIMIT", 42 );

  /**
   * Acceleration limit in as
   */
  public final static AMC_DataType D_AMC_ACCELERATION_LIMIT =
      new AMC_DataType( "D_AMC_ACCELERATION_LIMIT", 43 );

  /**
   * DC loop gain in velocity control integer.
   */
  public final static AMC_DataType D_AMC_VELOCITY_CONTROL_DC_GAIN =
      new AMC_DataType( "D_AMC_VELOCITY_CONTROL_DC_GAIN", 44 );

  /**
   * Motor1 velocity in as
   */
  public final static AMC_DataType D_AMC_MOTOR_1_VELOCITY =
      new AMC_DataType( "D_AMC_MOTOR_1_VELOCITY", 45 );

  /**
   * Motor2 velocity in as
   */
  public final static AMC_DataType D_AMC_MOTOR_2_VELOCITY =
      new AMC_DataType( "D_AMC_MOTOR_2_VELOCITY", 46 );

  /**
   * Motor1
   */
  public final static AMC_DataType D_AMC_MOTOR_VELOCITY_SUM =
      new AMC_DataType( "D_AMC_MOTOR_VELOCITY_SUM", 47 );

  /**
   * Motor1 Motor2 velocity
   */
  public final static AMC_DataType D_AMC_MOTOR_VELOCITY_DIFF =
      new AMC_DataType( "D_AMC_MOTOR_VELOCITY_DIFF", 48 );

  /**
   * MtrVelDiff scaling for Trq correction
   */
  public final static AMC_DataType D_AMC_MTR_VEL_FEEDBACK_FRACTION =
      new AMC_DataType( "D_AMC_MTR_VEL_FEEDBACK_FRACTION", 49 );

  /**
   * Torque correction factor
   */
  public final static AMC_DataType D_AMC_MTR_VEL_TRQ_CORRECTION =
      new AMC_DataType( "D_AMC_MTR_VEL_TRQ_CORRECTION", 50 );

  /**
   * Servo algorithm used (PID
   */
  public final static AMC_DataType D_AMC_SERVO_CTRL_ALGORITHM_TYPE =
      new AMC_DataType( "D_AMC_SERVO_CTRL_ALGORITHM_TYPE", 51 );

  /**
   * Servo algorithm version number
   */
  public final static AMC_DataType D_AMC_SERVO_CTRL_ALGORITHM_VER =
      new AMC_DataType( "D_AMC_SERVO_CTRL_ALGORITHM_VER", 52 );

  /**
   * Threshold for detecting interrupt repeats
   */
  public final static AMC_DataType D_AMC_INTERRUPT_STUTTER_LIMIT =
      new AMC_DataType( "D_AMC_INTERRUPT_STUTTER_LIMIT", 53 );

  /**
   * Flag to ingoring stuttered interrupts
   */
  public final static AMC_DataType D_AMC_INTERRUPT_STUTTER_IGNORE =
      new AMC_DataType( "D_AMC_INTERRUPT_STUTTER_IGNORE", 54 );

  /**
   * 
   */
  public final static AMC_DataType D_AMC_DATAID_EOL =
      new AMC_DataType( "D_AMC_DATAID_EOL", 55 );

  /**
   * Array allowing serialization.
   */
  protected static final AMC_DataType[] array =
  {
    D_AMC_DATAID_BOL,
    D_AMC_PROC_STATE,
    D_AMC_APP_VERSION,
    D_AMC_SERVER_SHUTDOWN,
    D_AMC_STATE,
    D_AMC_MOVE_ENABLE,
    D_AMC_AXIS_MOVE_FAILURE,
    D_AMC_MOVE_COMMAND,
    D_AMC_TRACK_COMMAND,
    D_AMC_HALT_COMMAND,
    D_AMC_TARGET_TIME,
    D_AMC_TARGET_TIME_DUMMY,
    D_AMC_TARGET_POSITION,
    D_AMC_WORKING_TARGET_TIME,
    D_AMC_WORKING_TARGET_TIME_DUMMY,
    D_AMC_WORKING_TARGET_POSITION,
    D_AMC_SINUSOID_COMMAND,
    D_AMC_SINUSOID_AMPLITUDE,
    D_AMC_SINUSOID_FREQUENCY,
    D_AMC_AXIS_POSITION_DEMAND,
    D_AMC_SIMULATION_ACTIVE,
    D_AMC_CLOCKWISE_LIMIT_ACTIVE,
    D_AMC_ACW_LIMIT_ACTIVE,
    D_AMC_DISTANCE_TO_CW_LIMIT,
    D_AMC_DISTANCE_TO_ACW_LIMIT,
    D_AMC_RECOVER_COMMAND,
    D_AMC_LIMIT_RECOVERY_DISTANCE,
    D_AMC_PRELOAD_APPLICATION_PERIOD,
    D_AMC_PEAK_TRACKING_ERROR,
    D_AMC_RMS_TRACKING_ERROR,
    D_AMC_AXIS_POSITION,
    D_AMC_PID_P1_GAIN,
    D_AMC_PID_P2_GAIN,
    D_AMC_PID_K1_GAIN,
    D_AMC_INTEGRATOR_WIND_UP_LIMIT,
    D_AMC_INTEGRATOR_LIMIT_REACHED,
    D_AMC_HOMING_VELOCITY,
    D_AMC_MAX_HOMING_DURATION,
    D_AMC_CURRENT_AXIS_VELOCITY,
    D_AMC_ENABLE_MOTION_TESTING,
    D_AMC_MOTION_CONTROL_TEST_MODE,
    D_AMC_START_LOGGING_COMMAND,
    D_AMC_VELOCITY_LIMIT,
    D_AMC_ACCELERATION_LIMIT,
    D_AMC_VELOCITY_CONTROL_DC_GAIN,
    D_AMC_MOTOR_1_VELOCITY,
    D_AMC_MOTOR_2_VELOCITY,
    D_AMC_MOTOR_VELOCITY_SUM,
    D_AMC_MOTOR_VELOCITY_DIFF,
    D_AMC_MTR_VEL_FEEDBACK_FRACTION,
    D_AMC_MTR_VEL_TRQ_CORRECTION,
    D_AMC_SERVO_CTRL_ALGORITHM_TYPE,
    D_AMC_SERVO_CTRL_ALGORITHM_VER,
    D_AMC_INTERRUPT_STUTTER_LIMIT,
    D_AMC_INTERRUPT_STUTTER_IGNORE,
    D_AMC_DATAID_EOL
  };


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String name of this type-safe enumeration.
   */
  private transient String name;

  /**
   * Optional integer for int representation of this Type-safe Enumeration.
   */
  private transient int intValue;

  /**
   * Assign an index to this enumeration.
   */
  private final int index = nextIndex++;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the AMC_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AMC_DataType
   * @return a reference to the AMC_DataType specified by the argument
   */
  public static AMC_DataType getReference( String s )
  {
    return( (AMC_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AMC_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AMC_DataType
   * @return a reference to the AMC_DataType specified by the argument
   */
  public static AMC_DataType getReference( int i )
  {
    return( (AMC_DataType)( intHash.get( new Integer( i ) ) ) );
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
  private AMC_DataType( String s )
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
  private AMC_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AMC_DataType.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AMC_DataType.
   * @return intValue
   * @see #intValue
   */
  public final int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package for which this data refers.
   * @return ttlPackage
   * @see #ttlPackage
   */
  public TTL_Package getTTL_Package()
  {
    return( ttlPackage );
  }


  /**
   * Over-ride the Serializable method to ensure the same Object references
   * are returned after Serialization.
   */
  private Object readResolve() throws java.io.ObjectStreamException
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
 *    $Date: 2006-11-20 14:46:32 $
 * $RCSfile: AMC_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/acn/AMC_DataType.java,v $
 *      $Id: AMC_DataType.java,v 1.1 2006-11-20 14:46:32 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
