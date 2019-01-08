package ngat.ngtcs.subsystem;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_Boolean.  These are:
 * <ul>
 * <li>TRUE</li>
 * <li>FALSE</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public final class TTL_Boolean
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

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   *
   */
  public final static TTL_Boolean TRUE =
    new TTL_Boolean( "TRUE", 1, TTL_Package.NO_SYS );

  /**
   *
   */
  public final static TTL_Boolean FALSE =
    new TTL_Boolean( "FALSE", 0, TTL_Package.NO_SYS );

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
   * Optional package for modifying the int representation of this Type-safe
   * Enumeration.
   */
  private transient TTL_Package ttlPackage;

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
   * Return an object reference of the TTL_Boolean with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_Boolean
   * @return a reference to the TTL_Boolean specified by the argument
   */
  public static TTL_Boolean getReference( String s )
  {
    return( (TTL_Boolean)( nameHash.get( s.trim() ) ) );
  }


  /**
   * Return an object reference of the TTL_Boolean with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_Boolean
   * @return a reference to the TTL_Boolean specified by the argument
   */
  public static TTL_Boolean getReference( int i )
  {
    return( (TTL_Boolean)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  private TTL_Boolean( String s, int i, TTL_Package p )
  {
    name = s;
    nameHash.put( s, this );
    ttlPackage = p;
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_Boolean.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_Boolean.
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
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_Boolean.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Boolean.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
