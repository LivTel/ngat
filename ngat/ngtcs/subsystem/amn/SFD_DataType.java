package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SFD_DataType.  These are:
 * <ul>
 * <li>D_SFD_DATAID_BOL</li>
 * <li>D_SFD_PROC_STATE</li>
 * <li>D_SFD_APP_VERSION</li>
 * <li>D_SFD_SHUTDOWN</li>
 * <li>D_SFD_STATE</li>
 * <li>D_SFD_ENABLE</li>
 * <li>D_SFD_HOME</li>
 * <li>D_SFD_DEMAND</li>
 * <li>D_SFD_ACTUAL</li>
 * <li>D_SFD_ACTUAL_LINEAR</li>
 * <li>D_SFD_DEPLOY_POSITION</li>
 * <li>D_SFD_STOW_POSITION</li>
 * <li>D_SFD_STOW_DEPLOY_TOLERANCE</li>
 * <li>D_SFD_UNUSED_LIMIT_LO</li>
 * <li>D_SFD_UNUSED_LIMIT_HI</li>
 * <li>D_SFD_STOP_DEMAND</li>
 * <li>D_SFD_SFD_HOME_TOLERANCE</li>
 * <li>D_SFD_SFD_MAX_MOVING_TIME</li>
 * <li>D_SFD_STOPPED</li>
 * <li>D_SFD_SFD_AT_ZERO</li>
 * <li>D_SFD_HALT_DEMAND</li>
 * <li>D_SFD_DATAID_EOL</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public final class SFD_DataType
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
  public static final String RevisionString =
    new String( "$Id: SFD_DataType.java,v 1.1 2003-09-19 16:08:45 je Exp $" );

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
  private static final TTL_Package ttlPackage = TTL_Package.SFD;

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * 
   */
  public final static SFD_DataType D_SFD_DATAID_BOL =
      new SFD_DataType( "D_SFD_DATAID_BOL", 0 );

  /**
   * Task process state
   */
  public final static SFD_DataType D_SFD_PROC_STATE =
      new SFD_DataType( "D_SFD_PROC_STATE", 1 );

  /**
   * Task shutdown flag
   */
  public final static SFD_DataType D_SFD_APP_VERSION =
      new SFD_DataType( "D_SFD_APP_VERSION", 2 );

  /**
   * Task shutdown flag
   */
  public final static SFD_DataType D_SFD_SHUTDOWN =
      new SFD_DataType( "D_SFD_SHUTDOWN", 3 );

  /**
   * Sci. Fold Deployment State
   */
  public final static SFD_DataType D_SFD_STATE =
      new SFD_DataType( "D_SFD_STATE", 4 );

  /**
   * Task Enable Flag
   */
  public final static SFD_DataType D_SFD_ENABLE =
      new SFD_DataType( "D_SFD_ENABLE", 5 );

  /**
   * Home the main mechanism
   */
  public final static SFD_DataType D_SFD_HOME =
      new SFD_DataType( "D_SFD_HOME", 6 );

  /**
   * Requested mechanism position
   */
  public final static SFD_DataType D_SFD_DEMAND =
      new SFD_DataType( "D_SFD_DEMAND", 7 );

  /**
   * Actual mechanism position
   */
  public final static SFD_DataType D_SFD_ACTUAL =
      new SFD_DataType( "D_SFD_ACTUAL", 8 );

  /**
   * Actual linear position
   */
  public final static SFD_DataType D_SFD_ACTUAL_LINEAR =
      new SFD_DataType( "D_SFD_ACTUAL_LINEAR", 9 );

  /**
   * Deploy Position
   */
  public final static SFD_DataType D_SFD_DEPLOY_POSITION =
      new SFD_DataType( "D_SFD_DEPLOY_POSITION", 10 );

  /**
   * Stow Position
   */
  public final static SFD_DataType D_SFD_STOW_POSITION =
      new SFD_DataType( "D_SFD_STOW_POSITION", 11 );

  /**
   * Used in position calculation
   */
  public final static SFD_DataType D_SFD_STOW_DEPLOY_TOLERANCE =
      new SFD_DataType( "D_SFD_STOW_DEPLOY_TOLERANCE", 12 );

  /**
   * Low limit switch status
   */
  public final static SFD_DataType D_SFD_UNUSED_LIMIT_LO =
      new SFD_DataType( "D_SFD_UNUSED_LIMIT_LO", 13 );

  /**
   * High limit switch status
   */
  public final static SFD_DataType D_SFD_UNUSED_LIMIT_HI =
      new SFD_DataType( "D_SFD_UNUSED_LIMIT_HI", 14 );

  /**
   * Task stop Flag
   */
  public final static SFD_DataType D_SFD_STOP_DEMAND =
      new SFD_DataType( "D_SFD_STOP_DEMAND", 15 );

  /**
   * Used in homing sequence
   */
  public final static SFD_DataType D_SFD_SFD_HOME_TOLERANCE =
      new SFD_DataType( "D_SFD_SFD_HOME_TOLERANCE", 16 );

  /**
   * Maximum moving time (msec)
   */
  public final static SFD_DataType D_SFD_SFD_MAX_MOVING_TIME =
      new SFD_DataType( "D_SFD_SFD_MAX_MOVING_TIME", 17 );

  /**
   * Denoting all movement stopped
   */
  public final static SFD_DataType D_SFD_STOPPED =
      new SFD_DataType( "D_SFD_STOPPED", 18 );

  /**
   * Denoting current position /home/je 0
   */
  public final static SFD_DataType D_SFD_SFD_AT_ZERO =
      new SFD_DataType( "D_SFD_SFD_AT_ZERO", 19 );

  /**
   * Task halt flag
   */
  public final static SFD_DataType D_SFD_HALT_DEMAND =
      new SFD_DataType( "D_SFD_HALT_DEMAND", 20 );

  /**
   * 
   */
  public final static SFD_DataType D_SFD_DATAID_EOL =
      new SFD_DataType( "D_SFD_DATAID_EOL", 21 );

  /**
   * Array allowing serialization.
   */
  protected static final SFD_DataType[] array =
  {
    D_SFD_DATAID_BOL,
    D_SFD_PROC_STATE,
    D_SFD_APP_VERSION,
    D_SFD_SHUTDOWN,
    D_SFD_STATE,
    D_SFD_ENABLE,
    D_SFD_HOME,
    D_SFD_DEMAND,
    D_SFD_ACTUAL,
    D_SFD_ACTUAL_LINEAR,
    D_SFD_DEPLOY_POSITION,
    D_SFD_STOW_POSITION,
    D_SFD_STOW_DEPLOY_TOLERANCE,
    D_SFD_UNUSED_LIMIT_LO,
    D_SFD_UNUSED_LIMIT_HI,
    D_SFD_STOP_DEMAND,
    D_SFD_SFD_HOME_TOLERANCE,
    D_SFD_SFD_MAX_MOVING_TIME,
    D_SFD_STOPPED,
    D_SFD_SFD_AT_ZERO,
    D_SFD_HALT_DEMAND,
    D_SFD_DATAID_EOL
  };

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Return an object reference of the SFD_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SFD_DataType
   * @return a reference to the SFD_DataType specified by the argument
   */
  public static SFD_DataType getReference( String s )
  {
    return( (SFD_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SFD_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SFD_DataType
   * @return a reference to the SFD_DataType specified by the argument
   */
  public static SFD_DataType getReference( int i )
  {
    return( (SFD_DataType)( intHash.get( new Integer( i ) ) ) );
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
  private SFD_DataType( String s )
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
  private SFD_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SFD_DataType.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SFD_DataType.
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
 *    $Date: 2003-09-19 16:08:45 $
 * $RCSfile: SFD_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/SFD_DataType.java,v $
 *      $Id: SFD_DataType.java,v 1.1 2003-09-19 16:08:45 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
