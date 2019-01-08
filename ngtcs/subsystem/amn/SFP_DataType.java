package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SFP_DataType.  These are:
 * <ul>
 * <li>D_SFP_DATAID_BOL</li>
 * <li>D_SFP_PROC_STATE</li>
 * <li>D_SFP_APP_VERSION</li>
 * <li>D_SFP_SHUTDOWN</li>
 * <li>D_SFP_STATE</li>
 * <li>D_SFP_ENABLE</li>
 * <li>D_SFP_HOME</li>
 * <li>D_SFP_DEMAND</li>
 * <li>D_SFP_ACTUAL</li>
 * <li>D_SFP_DEMAND_ANGULAR</li>
 * <li>D_SFP_ACTUAL_ANGULAR</li>
 * <li>D_SFP_UNUSED_HOME_SWITCH</li>
 * <li>D_SFP_STOP_DEMAND</li>
 * <li>D_SFP_SFP_TOLERANCE</li>
 * <li>D_SFP_PORT_1_POSITION</li>
 * <li>D_SFP_PORT_2_POSITION</li>
 * <li>D_SFP_PORT_3_POSITION</li>
 * <li>D_SFP_PORT_4_POSITION</li>
 * <li>D_SFP_PORT_5_POSITION</li>
 * <li>D_SFP_PORT_6_POSITION</li>
 * <li>D_SFP_PORT_7_POSITION</li>
 * <li>D_SFP_PORT_8_POSITION</li>
 * <li>D_SFP_NO_PORTS</li>
 * <li>D_SFP_SFP_MAX_MOVING_TIME</li>
 * <li>D_SFP_STOPPED</li>
 * <li>D_SFP_SFP_AT_ZERO</li>
 * <li>D_SFP_HALT_DEMAND</li>
 * <li>D_SFP_DATAID_EOL</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public final class SFP_DataType
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
  public static final String RevisionString =
    new String( "$Id$" );

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
  private static final TTL_Package ttlPackage = TTL_Package.SFP;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static SFP_DataType D_SFP_DATAID_BOL =
      new SFP_DataType( "D_SFP_DATAID_BOL", 0 );

  /**
   * Task process state
   */
  public final static SFP_DataType D_SFP_PROC_STATE =
      new SFP_DataType( "D_SFP_PROC_STATE", 1 );

  /**
   * Package Version Number
   */
  public final static SFP_DataType D_SFP_APP_VERSION =
      new SFP_DataType( "D_SFP_APP_VERSION", 2 );

  /**
   * Task Shutdown flag
   */
  public final static SFP_DataType D_SFP_SHUTDOWN =
      new SFP_DataType( "D_SFP_SHUTDOWN", 3 );

  /**
   * Autoguider Focus State
   */
  public final static SFP_DataType D_SFP_STATE =
      new SFP_DataType( "D_SFP_STATE", 4 );

  /**
   * Task Enable flag
   */
  public final static SFP_DataType D_SFP_ENABLE =
      new SFP_DataType( "D_SFP_ENABLE", 5 );

  /**
   * Home the main mechanism
   */
  public final static SFP_DataType D_SFP_HOME =
      new SFP_DataType( "D_SFP_HOME", 6 );

  /**
   * Requested side port position
   */
  public final static SFP_DataType D_SFP_DEMAND =
      new SFP_DataType( "D_SFP_DEMAND", 7 );

  /**
   * Actual side port position
   */
  public final static SFP_DataType D_SFP_ACTUAL =
      new SFP_DataType( "D_SFP_ACTUAL", 8 );

  /**
   * Requested angular position
   */
  public final static SFP_DataType D_SFP_DEMAND_ANGULAR =
      new SFP_DataType( "D_SFP_DEMAND_ANGULAR", 9 );

  /**
   * Actual angular position
   */
  public final static SFP_DataType D_SFP_ACTUAL_ANGULAR =
      new SFP_DataType( "D_SFP_ACTUAL_ANGULAR", 10 );

  /**
   * Home switch status
   */
  public final static SFP_DataType D_SFP_UNUSED_HOME_SWITCH =
      new SFP_DataType( "D_SFP_UNUSED_HOME_SWITCH", 11 );

  /**
   * Task stop flag
   */
  public final static SFP_DataType D_SFP_STOP_DEMAND =
      new SFP_DataType( "D_SFP_STOP_DEMAND", 12 );

  /**
   * Tolerance for homing
   */
  public final static SFP_DataType D_SFP_SFP_TOLERANCE =
      new SFP_DataType( "D_SFP_SFP_TOLERANCE", 13 );

  /**
   * Port 1 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_1_POSITION =
      new SFP_DataType( "D_SFP_PORT_1_POSITION", 14 );

  /**
   * Port 2 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_2_POSITION =
      new SFP_DataType( "D_SFP_PORT_2_POSITION", 15 );

  /**
   * Port 3 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_3_POSITION =
      new SFP_DataType( "D_SFP_PORT_3_POSITION", 16 );

  /**
   * Port 4 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_4_POSITION =
      new SFP_DataType( "D_SFP_PORT_4_POSITION", 17 );

  /**
   * Port 5 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_5_POSITION =
      new SFP_DataType( "D_SFP_PORT_5_POSITION", 18 );

  /**
   * Port 6 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_6_POSITION =
      new SFP_DataType( "D_SFP_PORT_6_POSITION", 19 );

  /**
   * Port 7 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_7_POSITION =
      new SFP_DataType( "D_SFP_PORT_7_POSITION", 20 );

  /**
   * Port 8 offset from home
   */
  public final static SFP_DataType D_SFP_PORT_8_POSITION =
      new SFP_DataType( "D_SFP_PORT_8_POSITION", 21 );

  /**
   * No. of available side ports
   */
  public final static SFP_DataType D_SFP_NO_PORTS =
      new SFP_DataType( "D_SFP_NO_PORTS", 22 );

  /**
   * Maximum moving time (msec)
   */
  public final static SFP_DataType D_SFP_SFP_MAX_MOVING_TIME =
      new SFP_DataType( "D_SFP_SFP_MAX_MOVING_TIME", 23 );

  /**
   * Denoting all movement stopped
   */
  public final static SFP_DataType D_SFP_STOPPED =
      new SFP_DataType( "D_SFP_STOPPED", 24 );

  /**
   * Denoting current position /home/je 0
   */
  public final static SFP_DataType D_SFP_SFP_AT_ZERO =
      new SFP_DataType( "D_SFP_SFP_AT_ZERO", 25 );

  /**
   * Task halt flag
   */
  public final static SFP_DataType D_SFP_HALT_DEMAND =
      new SFP_DataType( "D_SFP_HALT_DEMAND", 26 );

  /**
   * 
   */
  public final static SFP_DataType D_SFP_DATAID_EOL =
      new SFP_DataType( "D_SFP_DATAID_EOL", 27 );

  /**
   * Array allowing serialization.
   */
  protected static final SFP_DataType[] array =
  {
    D_SFP_DATAID_BOL,
    D_SFP_PROC_STATE,
    D_SFP_APP_VERSION,
    D_SFP_SHUTDOWN,
    D_SFP_STATE,
    D_SFP_ENABLE,
    D_SFP_HOME,
    D_SFP_DEMAND,
    D_SFP_ACTUAL,
    D_SFP_DEMAND_ANGULAR,
    D_SFP_ACTUAL_ANGULAR,
    D_SFP_UNUSED_HOME_SWITCH,
    D_SFP_STOP_DEMAND,
    D_SFP_SFP_TOLERANCE,
    D_SFP_PORT_1_POSITION,
    D_SFP_PORT_2_POSITION,
    D_SFP_PORT_3_POSITION,
    D_SFP_PORT_4_POSITION,
    D_SFP_PORT_5_POSITION,
    D_SFP_PORT_6_POSITION,
    D_SFP_PORT_7_POSITION,
    D_SFP_PORT_8_POSITION,
    D_SFP_NO_PORTS,
    D_SFP_SFP_MAX_MOVING_TIME,
    D_SFP_STOPPED,
    D_SFP_SFP_AT_ZERO,
    D_SFP_HALT_DEMAND,
    D_SFP_DATAID_EOL
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
   * Return an object reference of the SFP_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SFP_DataType
   * @return a reference to the SFP_DataType specified by the argument
   */
  public static SFP_DataType getReference( String s )
  {
    return( (SFP_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SFP_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SFP_DataType
   * @return a reference to the SFP_DataType specified by the argument
   */
  public static SFP_DataType getReference( int i )
  {
    return( (SFP_DataType)( intHash.get( new Integer( i ) ) ) );
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
  private SFP_DataType( String s )
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
  private SFP_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SFP_DataType.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SFP_DataType.
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
 *    $Date: 2006-11-20 14:46:44 $
 * $RCSfile: SFP_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/SFP_DataType.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
