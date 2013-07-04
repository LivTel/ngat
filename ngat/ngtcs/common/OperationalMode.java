package ngat.ngtcs.common;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type OperationalMode.  These are:
 * <ul>
 * <li>PARKED</li>
 * <li>STOPPED</li>
 * <li>SLEWING</li>
 * <li>TRACKING</li>
 * <li>ERROR</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class OperationalMode
  implements java.io.Serializable
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
    new String( "$Id: OperationalMode.java,v 1.1 2013-07-04 10:38:08 cjm Exp $" );

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
   * The PARKED operational mode.
   */
  public final static OperationalMode PARKED =
    new OperationalMode( "PARKED" );

  /**
   * The STOPPED operational mode.
   */
  public final static OperationalMode STOPPED =
    new OperationalMode( "STOPPED" );

  /**
   * The SLEWING operational mode.
   */
  public final static OperationalMode SLEWING =
    new OperationalMode( "SLEWING" );

  /**
   * The TRACKING operational mode.
   */
  public final static OperationalMode TRACKING =
    new OperationalMode( "TRACKING" );

  /**
   * The ERROR operational mode.
   */
  public final static OperationalMode ERROR =
    new OperationalMode( "ERROR" );

  /**
   *
   */
  private static OperationalMode[] array =
  {
    PARKED,
    STOPPED,
    SLEWING,
    TRACKING,
    ERROR
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
   * Return an object reference of the OperationalMode with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the OperationalMode
   * @return a reference to the OperationalMode specified by the argument
   */
  public static OperationalMode getReference( String s )
  {
    return( (OperationalMode)( nameHash.get( s.trim() ) ) );
  }


  /**
   * Return an object reference of the OperationalMode with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the OperationalMode
   * @return a reference to the OperationalMode specified by the argument
   */
  public static OperationalMode getReference( int i )
  {
    return( (OperationalMode)( intHash.get( new Integer( i ) ) ) );
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
  private OperationalMode( String s )
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
  private OperationalMode( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intHash.put( new Integer( i ), this );
  }


  /**
   * Return the name of this OperationalMode.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this OperationalMode.
   * @return intValue
   * @see #intValue
   */
  public final int getInt()
  {
    return intValue;
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
 *    $Date: 2013-07-04 10:38:08 $
 * $RCSfile: OperationalMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/OperationalMode.java,v $
 *      $Id: OperationalMode.java,v 1.1 2013-07-04 10:38:08 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
