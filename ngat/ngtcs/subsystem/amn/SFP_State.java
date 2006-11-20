package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SFP_State.  These are:
 * <ul>
 * <li>E_SFP_STATE_DISABLED</li>
 * <li>E_SFP_STATE_ENABLED</li>
 * <li>E_SFP_STATE_HOMING</li>
 * <li>E_SFP_STATE_READY</li>
 * <li>E_SFP_STATE_MOVING</li>
 * <li>E_SFP_STATE_ERROR</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class SFP_State
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
    new String( "$Id: SFP_State.java,v 1.1 2006-11-20 14:46:44 cjm Exp $" );

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
  public final static SFP_State E_SFP_STATE_DISABLED =
      new SFP_State( "E_SFP_STATE_DISABLED", 0 );

  /**
   * 
   */
  public final static SFP_State E_SFP_STATE_ENABLED =
      new SFP_State( "E_SFP_STATE_ENABLED", 1 );

  /**
   * 
   */
  public final static SFP_State E_SFP_STATE_HOMING =
      new SFP_State( "E_SFP_STATE_HOMING", 2 );

  /**
   * 
   */
  public final static SFP_State E_SFP_STATE_READY =
      new SFP_State( "E_SFP_STATE_READY", 3 );

  /**
   * 
   */
  public final static SFP_State E_SFP_STATE_MOVING =
      new SFP_State( "E_SFP_STATE_MOVING", 4 );

  /**
   * 
   */
  public final static SFP_State E_SFP_STATE_ERROR =
      new SFP_State( "E_SFP_STATE_ERROR", 5 );

  /**
   * Array allowing serialization.
   */
  protected static final SFP_State[] array =
  {
    E_SFP_STATE_DISABLED,
    E_SFP_STATE_ENABLED,
    E_SFP_STATE_HOMING,
    E_SFP_STATE_READY,
    E_SFP_STATE_MOVING,
    E_SFP_STATE_ERROR
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
   * Return an object reference of the SFP_State with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SFP_State
   * @return a reference to the SFP_State specified by the argument
   */
  public static SFP_State getReference( String s )
  {
    return( (SFP_State)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SFP_State with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SFP_State
   * @return a reference to the SFP_State specified by the argument
   */
  public static SFP_State getReference( int i )
  {
    return( (SFP_State)( intHash.get( new Integer( i ) ) ) );
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
  private SFP_State( String s )
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
  private SFP_State( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SFP_State.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SFP_State.
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
 * $RCSfile: SFP_State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/SFP_State.java,v $
 *      $Id: SFP_State.java,v 1.1 2006-11-20 14:46:44 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
