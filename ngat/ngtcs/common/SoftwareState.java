package ngat.ngtcs.common;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SoftwareState.
 * <p>
 * The list of States is:
 * <ul>
 * <li>OKAY</li>
 * <li>INITIALISING</li>
 * <li>SAFE</li>
 * <li>ERROR</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class SoftwareState
  implements ngat.util.TypeSafeEnumeration, java.io.Serializable
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
    new String( "$Id: SoftwareState.java,v 1.3 2013-07-04 10:46:03 cjm Exp $" );

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

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Everything nominal.
   */
  public static final SoftwareState OKAY =
    new SoftwareState( "OKAY" );

  /**
   * Software initialising.
   */
  public static final SoftwareState INITIALISING =
    new SoftwareState( "INITIALISING" );

  /**
   * Software is ready to be shutdown.
   */
  public static final SoftwareState SAFE =
    new SoftwareState( "SAFE" );

  /**
   * A serious error has occurred.
   */
  public static final SoftwareState ERROR =
    new SoftwareState( "ERROR" );

  /**
   * Array to allow serialization.
   */
  protected final static SoftwareState[] array =
  {
    OKAY,
    INITIALISING,
    SAFE,
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
   * Return an object reference of the SoftwareState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SoftwareState
   * @return a reference to the SoftwareState specified by the argument
   */
  public static SoftwareState getReference( String s )
  {
    return( (SoftwareState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SoftwareState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SoftwareState
   * @return a reference to the SoftwareState specified by the argument
   */
  public static SoftwareState getReference( int i )
  {
    return( (SoftwareState)( intHash.get( new Integer( i ) ) ) );
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
  protected SoftwareState( String s )
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
  protected SoftwareState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SoftwareState.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SoftwareState.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
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
 *    $Date: 2013-07-04 10:46:03 $
 * $RCSfile: SoftwareState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SoftwareState.java,v $
 *      $Id: SoftwareState.java,v 1.3 2013-07-04 10:46:03 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:00:03  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *
 */
