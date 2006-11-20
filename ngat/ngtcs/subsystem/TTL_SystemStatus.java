package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_SystemStatus.  These are:
 * <ul>
 * <li>SYS_NOMINAL</li>
 * <li>SYS_WARNING</li>
 * <li>SYS_ERROR_NON_FATAL</li>
 * <li>SYS_ERROR_FATAL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class TTL_SystemStatus
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
    new String( "$Id: TTL_SystemStatus.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

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

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static TTL_SystemStatus SYS_NOMINAL =
      new TTL_SystemStatus( "SYS_NOMINAL", 0, TTL_Package.SYS );

  /**
   * Not an error
   */
  public final static TTL_SystemStatus SYS_WARNING =
      new TTL_SystemStatus( "SYS_WARNING", 1, TTL_Package.SYS );

  /**
   * Operation failure that it recoverable
   */
  public final static TTL_SystemStatus SYS_ERROR_NON_FATAL =
      new TTL_SystemStatus( "SYS_ERROR_NON_FATAL", 2, TTL_Package.SYS );

  /**
   * 
   */
  public final static TTL_SystemStatus SYS_ERROR_FATAL =
      new TTL_SystemStatus( "SYS_ERROR_FATAL", 3, TTL_Package.SYS );

  /**
   * Array allowing serialization.
   */
  protected static final TTL_SystemStatus[] array =
  {
    SYS_NOMINAL,
    SYS_WARNING,
    SYS_ERROR_NON_FATAL,
    SYS_ERROR_FATAL
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

  /**
   * TTL Package of this type-safe enumeration.
   */
  private final TTL_Package ttlPackage;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the TTL_SystemStatus with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_SystemStatus
   * @return a reference to the TTL_SystemStatus specified by the argument
   */
  public static TTL_SystemStatus getReference( String s )
  {
    return( (TTL_SystemStatus)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_SystemStatus with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_SystemStatus
   * @return a reference to the TTL_SystemStatus specified by the argument
   */
  public static TTL_SystemStatus getReference( int i )
  {
    return( (TTL_SystemStatus)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  private TTL_SystemStatus( String s, int i, TTL_Package p )
  {
    name = s;
    nameHash.put( s, this );
    ttlPackage = p;
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_SystemStatus.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_SystemStatus.
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
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_SystemStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SystemStatus.java,v $
 *      $Id: TTL_SystemStatus.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
