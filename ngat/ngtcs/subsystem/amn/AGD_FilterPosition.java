package ngat.ngtcs.subsystem.amn;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGD_FilterPosition.
 * <p>
 * The allowable references of AGD_FilterPosition are:
 * <ul>
 * <li>E_AGD_IR_UNSET</li>
 * <li>E_AGD_IR_RETRACT</li>
 * <li>E_AGD_IR_IN_LINE</li>
 * </ul>
  * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGD_FilterPosition implements java.io.Serializable
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
    new String( "$Id: AGD_FilterPosition.java,v 1.2 2013-07-04 12:57:27 cjm Exp $" );

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
   * No position is currently set or the position is unknown.
   */
  public static AGD_FilterPosition E_AGD_IR_UNSET =
    new AGD_FilterPosition( "E_AGD_IR_UNSET", 0 );

  /**
   * The poisition is OUT of the optical beam such that the filter is NOT
   * being used.
   */
  public static AGD_FilterPosition E_AGD_IR_RETRACT =
    new AGD_FilterPosition( "E_AGD_IR_RETRACT", 1 );

  /**
   * The poisition is IN the optical beam such that the filter IS
   * being used.
   */
  public static AGD_FilterPosition E_AGD_IR_IN_LINE =
    new AGD_FilterPosition( "E_AGD_IR_IN_LINE", 2 );

  /**
   * Array allowing serialization.
   */
  protected static final AGD_FilterPosition[] array =
  {
    E_AGD_IR_UNSET,
    E_AGD_IR_RETRACT,
    E_AGD_IR_IN_LINE
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
   * Return an object reference of the AGD_FilterPosition with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGD_FilterPosition
   * @return a reference to the AGD_FilterPosition specified by the argument
   */
  public static AGD_FilterPosition getReference( String s )
  {
    return( (AGD_FilterPosition)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGD_FilterPosition with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGD_FilterPosition
   * @return a reference to the AGD_FilterPosition specified by the argument
   */
  public static AGD_FilterPosition getReference( int i )
  {
    return( (AGD_FilterPosition)( intHash.get( new Integer( i ) ) ) );
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
  protected AGD_FilterPosition( String s )
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
  protected AGD_FilterPosition( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGD_FilterPosition.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGD_FilterPosition.
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
 *    $Date: 2013-07-04 12:57:27 $
 * $RCSfile: AGD_FilterPosition.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AGD_FilterPosition.java,v $
 *      $Id: AGD_FilterPosition.java,v 1.2 2013-07-04 12:57:27 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:45  je
 *     Initial revision
 *
 *
 */
