package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGF_DataType.  These are:
 * <ul>
 * <li>D_AGF_PROC_STATE</li>
 * <li>D_AGF_APP_VERSION</li>
 * <li>D_AGF_SHUTDOWN</li>
 * <li>D_AGF_STATE</li>
 * <li>D_AGF_ENABLE</li>
 * <li>D_AGF_HOME</li>
 * <li>D_AGF_DEMAND</li>
 * <li>D_AGF_ACTUAL</li>
 * <li>D_AGF_UNUSED_LIMIT_LO</li>
 * <li>D_AGF_UNUSED_LIMIT_HI</li>
 * <li>D_AGF_STOP_DEMAND</li>
 * <li>D_AGF_AGF_RANGE_LO</li>
 * <li>D_AGF_AGF_RANGE_HI</li>
 * <li>D_AGF_AGF_HOME_TOLERANCE</li>
 * <li>D_AGF_AGF_MAX_MOVING_TIME</li>
 * <li>D_AGF_STOPPED</li>
 * <li>D_AGF_AGF_AT_ZERO</li>
 * <li>D_AGF_HALT_DEMAND</li>
 * </ul>
 * <p>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGF_DataType
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
    new String( "$Id: AGF_DataType.java,v 1.2 2013-07-04 12:57:39 cjm Exp $" );

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
  protected static final TTL_Package ttlPackage = TTL_Package.AGF;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  public static AGF_DataType D_AGF_DATAID_BOL = 
    new AGF_DataType( "D_AGF_DATAID_BOL", 0 );

  /**
   * Task process state
   */
  public static AGF_DataType D_AGF_PROC_STATE =
    new AGF_DataType( "D_AGF_PROC_STATE", 1 );

  /**
   * Package revision number
   */
  public static AGF_DataType D_AGF_APP_VERSION =
    new AGF_DataType( "D_AGF_APP_VERSION", 2 );

  /**
   * Task shutdown flag
   */
  public static AGF_DataType D_AGF_SHUTDOWN =
    new AGF_DataType( "D_AGF_SHUTDOWN", 3 );

  /**
   * Autoguider Focus State
   */
  public static AGF_DataType D_AGF_STATE =
    new AGF_DataType( "D_AGF_STATE", 4 );

  /**
   * Task enable flag
   */
  public static AGF_DataType D_AGF_ENABLE =
    new AGF_DataType( "D_AGF_ENABLE", 5 );

  /**
   * Home the main mechanism
   */
  public static AGF_DataType D_AGF_HOME =
    new AGF_DataType( "D_AGF_HOME", 6 );

  /**
   * Position demand
   */
  public static AGF_DataType D_AGF_DEMAND =
    new AGF_DataType( "D_AGF_DEMAND", 7 );

  /**
   * Actual linear position
   */
  public static AGF_DataType D_AGF_ACTUAL =
    new AGF_DataType( "D_AGF_ACTUAL", 8 );

  /**
   * Low limit switch state
   */
  public static AGF_DataType D_AGF_UNUSED_LIMIT_LO =
    new AGF_DataType( "D_AGF_UNUSED_LIMIT_LO", 9 );

  /**
   * High limit switch state
   */
  public static AGF_DataType D_AGF_UNUSED_LIMIT_HI =
    new AGF_DataType( "D_AGF_UNUSED_LIMIT_HI", 10 );

  /**
   * Task stop flag
   */
  public static AGF_DataType D_AGF_STOP_DEMAND =
    new AGF_DataType( "D_AGF_STOP_DEMAND", 11 );

  /**
   * Lower travel limit for axis
   */
  public static AGF_DataType D_AGF_AGF_RANGE_LO =
    new AGF_DataType( "D_AGF_AGF_RANGE_LO", 12 );

  /**
   * Upper travel limit for axis
   */
  public static AGF_DataType D_AGF_AGF_RANGE_HI =
    new AGF_DataType( "D_AGF_AGF_RANGE_HI", 13 );

  /**
   * Used in homing sequence
   */
  public static AGF_DataType D_AGF_AGF_HOME_TOLERANCE =
    new AGF_DataType( "D_AGF_AGF_HOME_TOLERANCE", 14 );

  /**
   * Maximum moving time (msec)
   */
  public static AGF_DataType D_AGF_AGF_MAX_MOVING_TIME =
    new AGF_DataType( "D_AGF_AGF_MAX_MOVING_TIME", 15 );

  /**
   * Denoting all movement stopped
   */
  public static AGF_DataType D_AGF_STOPPED =
    new AGF_DataType( "D_AGF_STOPPED", 16 );

  /**
   * Denoting current position /home/je 0
   */
  public static AGF_DataType D_AGF_AGF_AT_ZERO =
    new AGF_DataType( "D_AGF_AGF_AT_ZERO", 17 );

  /**
   * Task halt flag
   */
  public static AGF_DataType D_AGF_HALT_DEMAND =
    new AGF_DataType( "D_AGF_HALT_DEMAND", 18 );



  /**
   * Array allowing serialization.
   */
  protected final static AGF_DataType[] array = 
  {
    D_AGF_PROC_STATE,
    D_AGF_APP_VERSION,
    D_AGF_SHUTDOWN,
    D_AGF_STATE,
    D_AGF_ENABLE,
    D_AGF_HOME,
    D_AGF_DEMAND,
    D_AGF_ACTUAL,
    D_AGF_UNUSED_LIMIT_LO,
    D_AGF_UNUSED_LIMIT_HI,
    D_AGF_STOP_DEMAND,
    D_AGF_AGF_RANGE_LO,
    D_AGF_AGF_RANGE_HI,
    D_AGF_AGF_HOME_TOLERANCE,
    D_AGF_AGF_MAX_MOVING_TIME,
    D_AGF_STOPPED,
    D_AGF_AGF_AT_ZERO,
    D_AGF_HALT_DEMAND
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
   * Return an object reference of the AGF_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGF_DataType
   * @return a reference to the AGF_DataType specified by the argument
   */
  public static AGF_DataType getReference( String s )
  {
    return( (AGF_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGF_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGF_DataType
   * @return a reference to the AGF_DataType specified by the argument
   */
  public static AGF_DataType getReference( int i )
  {
    return( (AGF_DataType)( intHash.get( new Integer( i ) ) ) );
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
  protected AGF_DataType( String s )
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
  protected AGF_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGF_DataType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGF_DataType.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGF
   */
  public TTL_Package getTTL_Package()
  {
    return( ttlPackage );
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
 *    $Date: 2013-07-04 12:57:39 $
 * $RCSfile: AGF_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AGF_DataType.java,v $
 *      $Id: AGF_DataType.java,v 1.2 2013-07-04 12:57:39 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:45  je
 *     Initial revision
 *
 *
 */
