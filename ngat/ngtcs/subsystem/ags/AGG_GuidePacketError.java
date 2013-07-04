package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGG_GuidePacketError.
 * <p>
 * The available enumerations are:
 * <ul>
 * <lu>FAILED_TO_FIND</li>
 * <lu>TOO_NEAR_EDGE</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGG_GuidePacketError implements java.io.Serializable
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
    new String( "$Id: AGG_GuidePacketError.java,v 1.2 2013-07-04 12:54:47 cjm Exp $" );

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
   * The enumeration describing the error that the AGG failed to find a
   * guide star.
   */
  public static AGG_GuidePacketError FAILED_TO_FIND =
    new AGG_GuidePacketError( "FAILED_TO_FIND" );

  /**
   * The enumeration describing the error that the guide star is too close to
   * edge of the guide window.
   */
  public static AGG_GuidePacketError TOO_NEAR_EDGE =
    new AGG_GuidePacketError( "TOO_NEAR_EDGE" );

  /**
   * Array allowing serialization.
   */
  protected static final AGG_GuidePacketError[] array =
  {
    FAILED_TO_FIND,
    TOO_NEAR_EDGE
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
   * Return an object reference of the AGG_GuidePacketError with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGG_GuidePacketError
   * @return a reference to the AGG_GuidePacketError specified by the argument
   */
  public static AGG_GuidePacketError getReference( String s )
  {
    return( (AGG_GuidePacketError)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGG_GuidePacketError with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGG_GuidePacketError
   * @return a reference to the AGG_GuidePacketError specified by the argument
   */
  public static AGG_GuidePacketError getReference( int i )
  {
    return( (AGG_GuidePacketError)( intHash.get( new Integer( i ) ) ) );
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
  protected AGG_GuidePacketError( String s )
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
  protected AGG_GuidePacketError( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGG_GuidePacketError.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGG_GuidePacketError.
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
 *    $Date: 2013-07-04 12:54:47 $
 * $RCSfile: AGG_GuidePacketError.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_GuidePacketError.java,v $
 *      $Id: AGG_GuidePacketError.java,v 1.2 2013-07-04 12:54:47 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
