package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGD_DataType.  They are:
 * <p>
 * <ul>
 * <li>D_AGD_PROC_STATE</li>
 * <li>D_AGD_APP_VERSION</li>
 * <li>D_AGD_SHUTDOWN</li>
 * <li>D_AGD_STATE</li>
 * <li>D_AGD_ENABLE</li>
 * <li>D_AGD_HOME</li>
 * <li>D_AGD_DEMAND</li>
 * <li>D_AGD_ACTUAL</li>
 * <li>D_AGD_UNUSED_LIMIT_LO</li>
 * <li>D_AGD_UNUSED_LIMIT_HI</li>
 * <li>D_AGD_BRAKE_STATE</li>
 * <li>D_AGD_BRAKE_OFF</li>
 * <li>D_AGD_FILTER_STATE</li>
 * <li>D_AGD_FILTER_DEMAND</li>
 * <li>D_AGD_FILTER_ACTUAL</li>
 * <li>D_AGD_DARK_SLIDE_STATE</li>
 * <li>D_AGD_DARK_SLIDE_DEMAND</li>
 * <li>D_AGD_DARK_SLIDE_ACTUAL</li>
 * <li>D_AGD_AIR_STATE</li>
 * <li>D_AGD_STOP_DEMAND</li>
 * <li>D_AGD_AGD_RANGE_LO</li>
 * <li>D_AGD_AGD_RANGE_HI</li>
 * <li>D_AGD_AGD_HOME_TOLERANCE</li>
 * <li>D_AGD_DARK_SLIDE_FITTED</li>
 * <li>D_AGD_AGD_MAX_MOVING_TIME</li>
 * <li>D_AGD_STOPPED</li>
 * <li>D_AGD_AGD_AT_ZERO</li>
 * <li>D_AGD_HALT_DEMAND</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGD_DataType
  implements java.io.Serializable, ngat.ngtcs.subsystem.TTL_DataType
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static String RevisionString =
    new String( "$Id: AGD_DataType.java,v 1.1 2003-09-19 16:08:45 je Exp $" );

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

  /**
   * TTL package to which this data refers.
   */
  private static final TTL_Package ttlPackage = TTL_Package.AGD;

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Task process state
   */
  public static AGD_DataType D_AGD_PROC_STATE =
    new AGD_DataType( "D_AGD_PROC_STATE", 1 );

  /**
   * Package revision number
   */
  public static AGD_DataType D_AGD_APP_VERSION =
    new AGD_DataType( "D_AGD_APP_VERSION", 2 );

  /**
   * Task shutdown flag
   */
  public static AGD_DataType D_AGD_SHUTDOWN =
    new AGD_DataType( "D_AGD_SHUTDOWN", 3 );

  /**
   * Autoguider Focus State
   */
  public static AGD_DataType D_AGD_STATE =
    new AGD_DataType( "D_AGD_STATE", 4 );

  /**
   * Task enable flag
   */
  public static AGD_DataType D_AGD_ENABLE =
    new AGD_DataType( "D_AGD_ENABLE", 5 );

  /**
   * Home the main mechanism
   */
  public static AGD_DataType D_AGD_HOME =
    new AGD_DataType( "D_AGD_HOME", 6 );

  /**
   * Position demand
   */
  public static AGD_DataType D_AGD_DEMAND =
    new AGD_DataType( "D_AGD_DEMAND", 7 );

  /**
   * Actual linear position
   */
  public static AGD_DataType D_AGD_ACTUAL =
    new AGD_DataType( "D_AGD_ACTUAL", 8 );

  /**
   * Low limit switch state
   */
  public static AGD_DataType D_AGD_UNUSED_LIMIT_LO =
    new AGD_DataType( "D_AGD_UNUSED_LIMIT_LO", 9 );

  /**
   * High limit switch state
   */
  public static AGD_DataType D_AGD_UNUSED_LIMIT_HI =
    new AGD_DataType( "D_AGD_UNUSED_LIMIT_HI", 10 );

  /**
   * Brake operating state
   */
  public static AGD_DataType D_AGD_BRAKE_STATE =
    new AGD_DataType( "D_AGD_BRAKE_STATE", 11 );

  /**
   * Brake status
   */
  public static AGD_DataType D_AGD_BRAKE_OFF =
    new AGD_DataType( "D_AGD_BRAKE_OFF", 12 );

  /**
   * Filter operating state
   */
  public static AGD_DataType D_AGD_FILTER_STATE =
    new AGD_DataType( "D_AGD_FILTER_STATE", 13 );

  /**
   * Filter position demand
   */
  public static AGD_DataType D_AGD_FILTER_DEMAND =
    new AGD_DataType( "D_AGD_FILTER_DEMAND", 14 );

  /**
   * Actual filter position
   */
  public static AGD_DataType D_AGD_FILTER_ACTUAL =
    new AGD_DataType( "D_AGD_FILTER_ACTUAL", 15 );

  /**
   * Dark Slide operating state
   */
  public static AGD_DataType D_AGD_DARK_SLIDE_STATE =
    new AGD_DataType( "D_AGD_DARK_SLIDE_STATE", 16 );

  /**
   * Slide position demand
   */
  public static AGD_DataType D_AGD_DARK_SLIDE_DEMAND =
    new AGD_DataType( "D_AGD_DARK_SLIDE_DEMAND", 17 );

  /**
   * Actual dark slide position
   */
  public static AGD_DataType D_AGD_DARK_SLIDE_ACTUAL =
    new AGD_DataType( "D_AGD_DARK_SLIDE_ACTUAL", 18 );

  /**
   * Air pressure state
   */
  public static AGD_DataType D_AGD_AIR_STATE =
    new AGD_DataType( "D_AGD_AIR_STATE", 19 );

  /**
   * Task stop flag
   */
  public static AGD_DataType D_AGD_STOP_DEMAND =
    new AGD_DataType( "D_AGD_STOP_DEMAND", 20 );

  /**
   * Lower travel limit for axis
   */
  public static AGD_DataType D_AGD_AGD_RANGE_LO =
    new AGD_DataType( "D_AGD_AGD_RANGE_LO", 21 );

  /**
   * Upper travel limit for axis
   */
  public static AGD_DataType D_AGD_AGD_RANGE_HI =
    new AGD_DataType( "D_AGD_AGD_RANGE_HI", 22 );

  /**
   * Used in homing sequence
   */
  public static AGD_DataType D_AGD_AGD_HOME_TOLERANCE =
    new AGD_DataType( "D_AGD_AGD_HOME_TOLERANCE", 23 );

  /**
   * Flag for Dark Slide present
   */
  public static AGD_DataType D_AGD_DARK_SLIDE_FITTED =
    new AGD_DataType( "D_AGD_DARK_SLIDE_FITTED", 24 );

  /**
   * Maximum moving time (msec)
   */
  public static AGD_DataType D_AGD_AGD_MAX_MOVING_TIME =
    new AGD_DataType( "D_AGD_AGD_MAX_MOVING_TIME", 25 );

  /**
   * Denoting all movement stopped
   */
  public static AGD_DataType D_AGD_STOPPED =
    new AGD_DataType( "D_AGD_STOPPED", 26 );

  /**
   * Denoting current position /home/je 0
   */
  public static AGD_DataType D_AGD_AGD_AT_ZERO =
    new AGD_DataType( "D_AGD_AGD_AT_ZERO", 27 );

  /**
   * Task halt flag
   */
  public static AGD_DataType D_AGD_HALT_DEMAND =
    new AGD_DataType( "D_AGD_HALT_DEMAND", 28 );


  /**
   * Array allowing serialization.
   */
  protected static AGD_DataType[] array =
  {
    D_AGD_PROC_STATE,
    D_AGD_APP_VERSION,
    D_AGD_SHUTDOWN,
    D_AGD_STATE,
    D_AGD_ENABLE,
    D_AGD_HOME,
    D_AGD_DEMAND,
    D_AGD_ACTUAL,
    D_AGD_UNUSED_LIMIT_LO,
    D_AGD_UNUSED_LIMIT_HI,
    D_AGD_BRAKE_STATE,
    D_AGD_BRAKE_OFF,
    D_AGD_FILTER_STATE,
    D_AGD_FILTER_DEMAND,
    D_AGD_FILTER_ACTUAL,
    D_AGD_DARK_SLIDE_STATE,
    D_AGD_DARK_SLIDE_DEMAND,
    D_AGD_DARK_SLIDE_ACTUAL,
    D_AGD_AIR_STATE,
    D_AGD_STOP_DEMAND,
    D_AGD_AGD_RANGE_LO,
    D_AGD_AGD_RANGE_HI,
    D_AGD_AGD_HOME_TOLERANCE,
    D_AGD_DARK_SLIDE_FITTED,
    D_AGD_AGD_MAX_MOVING_TIME,
    D_AGD_STOPPED,
    D_AGD_AGD_AT_ZERO,
    D_AGD_HALT_DEMAND
  };

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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
  protected int index = nextIndex++;

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Return an object reference of the AGD_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGD_DataType
   * @return a reference to the AGD_DataType specified by the argument
   */
  public static AGD_DataType getReference( String s )
  {
    return( (AGD_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGD_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGD_DataType
   * @return a reference to the AGD_DataType specified by the argument
   */
  public static AGD_DataType getReference( int i )
  {
    return( (AGD_DataType)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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
  protected AGD_DataType( String s )
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
  protected AGD_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGD_DataType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGD_DataType.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGD
   */
  public TTL_Package getTTL_Package()
  {
    return( TTL_Package.AGD );
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
 *    $Date: 2003-09-19 16:08:45 $
 * $RCSfile: AGD_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AGD_DataType.java,v $
 *      $Id: AGD_DataType.java,v 1.1 2003-09-19 16:08:45 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
