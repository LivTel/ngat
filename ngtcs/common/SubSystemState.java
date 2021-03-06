package ngat.ngtcs.common;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SubSystemState.
 * <p>
 * The list of States is:
 * <ul>
 * <li>ACTIVE</li>
 * <li>INACTIVE</li>
 * <li>SAFE</li>
 * <li>ERROR</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SubSystemState implements java.io.Serializable
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
    new String( "$Id$" );

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
   * Operational state.
   */
  public static final SubSystemState ACTIVE =
    new SubSystemState( "ACTIVE", 500002 );


  /**
   * Inactive state.
   */
  public static final SubSystemState INACTIVE =
    new SubSystemState( "INACTIVE", 500002 );


  /**
   * Safe state.
   */
  public static final SubSystemState SAFE =
    new SubSystemState( "SAFE", 500002 );


  /**
   * Error state.
   */
  public static final SubSystemState ERROR =
    new SubSystemState( "ERROR", 500002 );


  /**
   * Array to allow serialization.
   */
  protected final static SubSystemState[] array =
  {
    ACTIVE,
    INACTIVE,
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
   * Return an object reference of the SubSystemState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SubSystemState
   * @return a reference to the SubSystemState specified by the argument
   */
  public static SubSystemState getReference( String s )
  {
    return( (SubSystemState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SubSystemState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SubSystemState
   * @return a reference to the SubSystemState specified by the argument
   */
  public static SubSystemState getReference( int i )
  {
    return( (SubSystemState)( intHash.get( new Integer( i ) ) ) );
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
  protected SubSystemState( String s )
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
  protected SubSystemState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SubSystemState.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SubSystemState.
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
 *    $Date: 2013-07-04 10:46:24 $
 * $RCSfile: SubSystemState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SubSystemState.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:00:03  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *
 */
