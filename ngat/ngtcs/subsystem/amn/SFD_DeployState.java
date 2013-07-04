package ngat.ngtcs.subsystem.amn;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SFD_DeployState.  These are:
 * <ul>
 * <li>E_SFD_POS_UNSET</li>
 * <li>E_SFD_POS_STOW</li>
 * <li>E_SFD_POS_DEPLOY</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public final class SFD_DeployState
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
    new String( "$Id: SFD_DeployState.java,v 1.2 2013-07-04 12:57:53 cjm Exp $" );

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

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static SFD_DeployState E_SFD_POS_UNSET =
      new SFD_DeployState( "E_SFD_POS_UNSET", 0 );

  /**
   * 
   */
  public final static SFD_DeployState E_SFD_POS_STOW =
      new SFD_DeployState( "E_SFD_POS_STOW", 1 );

  /**
   * 
   */
  public final static SFD_DeployState E_SFD_POS_DEPLOY =
      new SFD_DeployState( "E_SFD_POS_DEPLOY", 2 );

  /**
   * Array allowing serialization.
   */
  protected static final SFD_DeployState[] array =
  {
    E_SFD_POS_UNSET,
    E_SFD_POS_STOW,
    E_SFD_POS_DEPLOY
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
   * Return an object reference of the SFD_DeployState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SFD_DeployState
   * @return a reference to the SFD_DeployState specified by the argument
   */
  public static SFD_DeployState getReference( String s )
  {
    return( (SFD_DeployState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SFD_DeployState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SFD_DeployState
   * @return a reference to the SFD_DeployState specified by the argument
   */
  public static SFD_DeployState getReference( int i )
  {
    return( (SFD_DeployState)( intHash.get( new Integer( i ) ) ) );
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
  private SFD_DeployState( String s )
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
  private SFD_DeployState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SFD_DeployState.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SFD_DeployState.
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
 *    $Date: 2013-07-04 12:57:53 $
 * $RCSfile: SFD_DeployState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/SFD_DeployState.java,v $
 *      $Id: SFD_DeployState.java,v 1.2 2013-07-04 12:57:53 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/10/16 15:31:40  je
 *     Initial revision
 *
 *
 */
