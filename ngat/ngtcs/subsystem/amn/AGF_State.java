package ngat.ngtcs.subsystem.amn;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGF_State.  These are:
 * <ul>
 * <li>E_AGF_STATE_DISABLED</li>
 * <li>E_AGF_STATE_ENABLED</li>
 * <li>E_AGF_STATE_HOMING</li>
 * <li>E_AGF_STATE_READY</li>
 * <li>E_AGF_STATE_MOVING</li>
 * <li>E_AGF_STATE_ERROR</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGF_State
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
  public static final String rcsid =
    new String( "$Id: AGF_State.java,v 1.2 2013-07-04 12:57:41 cjm Exp $" );

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
   * 
   */
  public static AGF_State E_AGF_STATE_DISABLED =
    new AGF_State( "E_AGF_STATE_DISABLED", 0 );

  /**
   * 
   */
  public static AGF_State E_AGF_STATE_ENABLED =
    new AGF_State( "E_AGF_STATE_ENABLED", 1 );

  /**
   * 
   */
  public static AGF_State E_AGF_STATE_HOMING =
    new AGF_State( "E_AGF_STATE_HOMING", 2 );

  /**
   * 
   */
  public static AGF_State E_AGF_STATE_READY =
    new AGF_State( "E_AGF_STATE_READY", 3 );

  /**
   * 
   */
  public static AGF_State E_AGF_STATE_MOVING =
    new AGF_State( "E_AGF_STATE_MOVING", 4 );

  /**
   * 
   */
  public static AGF_State E_AGF_STATE_ERROR =
    new AGF_State( "E_AGF_STATE_ERROR", 5 );


  /**
   * Array allowing serialization.
   */
  protected static final AGF_State[] array =
  {
    E_AGF_STATE_DISABLED,
    E_AGF_STATE_ENABLED,
    E_AGF_STATE_HOMING,
    E_AGF_STATE_READY,
    E_AGF_STATE_MOVING,
    E_AGF_STATE_ERROR
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
   * Return an object reference of the AGF_State with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGF_State
   * @return a reference to the AGF_State specified by the argument
   */
  public static AGF_State getReference( String s )
  {
    return( (AGF_State)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGF_State with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGF_State
   * @return a reference to the AGF_State specified by the argument
   */
  public static AGF_State getReference( int i )
  {
    return( (AGF_State)( intHash.get( new Integer( i ) ) ) );
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
  protected AGF_State( String s )
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
  protected AGF_State( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGF_State.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGF_State.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGD
   */
  public ngat.ngtcs.subsystem.TTL_Package getTTL_Package()
  {
    return( ngat.ngtcs.subsystem.TTL_Package.AGD );
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
 *    $Date: 2013-07-04 12:57:41 $
 * $RCSfile: AGF_State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AGF_State.java,v $
 *      $Id: AGF_State.java,v 1.2 2013-07-04 12:57:41 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:45  je
 *     Initial revision
 *
 *
 */
