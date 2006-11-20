package ngat.ngtcs.subsystem.spt;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SPT_State.  These are:
 * <ul>
 * <li>E_SPT_DISABLE</li>
 * <li>E_SPT_ENABLE</li>
 * <li>E_SPT_DMD_ANALOG</li>
 * <li>E_SPT_DMD_STOP</li>
 * <li>E_SPT_DMD_STS_INVALID</li>
 * <li>E_SPT_DMD_STS_ACTD</li>
 * <li>E_SPT_DMD_STS_SAFE</li>
 * <li>E_SPT_DMD_STS_ON</li>
 * <li>E_SPT_DMD_STS_OFF</li>
 * <li>E_SPT_DMD_STS_START</li>
 * <li>E_SPT_DMD_STS_STOP</li>
 * <li>E_SPT_DMD_STS_OPN</li>
 * <li>E_SPT_DMD_STS_CLS</li>
 * <li>E_SPT_DMD_STS_RESET</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class SPT_State
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
    new String( "$Id: SPT_State.java,v 1.1 2006-11-20 14:47:01 cjm Exp $" );

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
  private static final TTL_Package ttlPackage = TTL_Package.SPT;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static SPT_State E_SPT_DISABLE =
      new SPT_State( "E_SPT_DISABLE", 0 );

  /**
   * 
   */
  public final static SPT_State E_SPT_ENABLE =
      new SPT_State( "E_SPT_ENABLE", 1 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_ANALOG =
      new SPT_State( "E_SPT_DMD_ANALOG", 2 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STOP =
      new SPT_State( "E_SPT_DMD_STOP", 3 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_INVALID =
      new SPT_State( "E_SPT_DMD_STS_INVALID", 4 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_ACTD =
      new SPT_State( "E_SPT_DMD_STS_ACTD", 5 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_SAFE =
      new SPT_State( "E_SPT_DMD_STS_SAFE", 6 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_ON =
      new SPT_State( "E_SPT_DMD_STS_ON", 7 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_OFF =
      new SPT_State( "E_SPT_DMD_STS_OFF", 8 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_START =
      new SPT_State( "E_SPT_DMD_STS_START", 9 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_STOP =
      new SPT_State( "E_SPT_DMD_STS_STOP", 10 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_OPN =
      new SPT_State( "E_SPT_DMD_STS_OPN", 11 );

  /**
   * 
   */
  public final static SPT_State E_SPT_DMD_STS_CLS =
      new SPT_State( "E_SPT_DMD_STS_CLS", 12 );

  /**
   * Reset
   */
  public final static SPT_State E_SPT_DMD_STS_RESET =
      new SPT_State( "E_SPT_DMD_STS_RESET", 13 );

  /**
   * Array allowing serialization.
   */
  protected static final SPT_State[] array =
  {
    E_SPT_DISABLE,
    E_SPT_ENABLE,
    E_SPT_DMD_ANALOG,
    E_SPT_DMD_STOP,
    E_SPT_DMD_STS_INVALID,
    E_SPT_DMD_STS_ACTD,
    E_SPT_DMD_STS_SAFE,
    E_SPT_DMD_STS_ON,
    E_SPT_DMD_STS_OFF,
    E_SPT_DMD_STS_START,
    E_SPT_DMD_STS_STOP,
    E_SPT_DMD_STS_OPN,
    E_SPT_DMD_STS_CLS,
    E_SPT_DMD_STS_RESET
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
   * Return an object reference of the SPT_State with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SPT_State
   * @return a reference to the SPT_State specified by the argument
   */
  public static SPT_State getReference( String s )
  {
    return( (SPT_State)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SPT_State with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SPT_State
   * @return a reference to the SPT_State specified by the argument
   */
  public static SPT_State getReference( int i )
  {
    return( (SPT_State)( intHash.get( new Integer( i ) ) ) );
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
  private SPT_State( String s )
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
  private SPT_State( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SPT_State.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SPT_State.
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
 *    $Date: 2006-11-20 14:47:01 $
 * $RCSfile: SPT_State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/spt/SPT_State.java,v $
 *      $Id: SPT_State.java,v 1.1 2006-11-20 14:47:01 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
