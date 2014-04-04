package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGS_State.
 * <p>
 * These are:
 * <ul>
 * <li>E_AGS_OFF</li>
 * <li>E_AGS_ON_BRIGHTEST</li>
 * <li>E_AGS_ON_RANGE</li>
 * <li>E_AGS_ON_RANK</li>
 * <li>E_AGS_ON_PIXEL</li>
 * <li>E_AGS_IDLE</li>
 * <li>E_AGS_WORKING</li>
 * <li>E_AGS_INITIALISING</li>
 * <li>E_AGS_FAILED</li>
 * <li>E_AGS_INTERACTIVE_WORKING</li>
 * <li>E_AGS_INTERACTIVE_ON</li>
 * <li>E_AGS_ERROR</li>
 * <li>E_AGS_NON_RECOVERABLE_ERROR</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGS_State
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
   * Not ready to accept observing commands.
   */
  public static AGS_State E_AGS_OFF =
    new AGS_State( "E_AGS_OFF", 0 );

  /**
   * Supplying guiding corrections on brightest non-saturated source.
   */
  public static AGS_State E_AGS_ON_BRIGHTEST =
    new AGS_State( "E_AGS_ON_BRIGHTEST", 1 );

  /**
   * Supplying guiding corrections on brightest non-saturated source in the
   * specified range.
   */
  public static AGS_State E_AGS_ON_RANGE =
    new AGS_State( "E_AGS_ON_RANGE", 2 );

  /**
   * Supplying guiding corrections on nth brightest non-saturated object.
   */
  public static AGS_State E_AGS_ON_RANK =
    new AGS_State( "E_AGS_ON_RANK", 3 );

  /**
   * Supplying guiding corrections on object closest to supplied pixel.
   */
  public static AGS_State E_AGS_ON_PIXEL =
    new AGS_State( "E_AGS_ON_PIXEL", 4 );

  /**
   * Ready to accept observing commands.
   */
  public static AGS_State E_AGS_IDLE =
    new AGS_State( "E_AGS_IDLE", 5 );

  /**
   * Currently acquiring guide source.
   */
  public static AGS_State E_AGS_WORKING =
    new AGS_State( "E_AGS_WORKING", 6 );

  /**
   * Not ready for operational use.
   */
  public static AGS_State E_AGS_INITIALISING =
    new AGS_State( "E_AGS_INITIALISING", 7 );

  /**
   * Failed to find a guide source.
   */
  public static AGS_State E_AGS_FAILED =
    new AGS_State( "E_AGS_FAILED", 8 );

  /**
   * Currently being used interactively but not supplying guiding data.
   */
  public static AGS_State E_AGS_INTERACTIVE_WORKING =
    new AGS_State( "E_AGS_INTERACTIVE_WORKING", 9 );

  /**
   * Currently being used interactively and suppliying guiding data.
   */
  public static AGS_State E_AGS_INTERACTIVE_ON =
    new AGS_State( "E_AGS_INTERACTIVE_ON", 10 );

  /**
   * Recoverable error.
   */
  public static AGS_State E_AGS_ERROR =
    new AGS_State( "E_AGS_ERROR", 11 );

  /**
   * Non recoverable error.
   */
  public static AGS_State E_AGS_NON_RECOVERABLE_ERROR =
    new AGS_State( "E_AGS_NON_RECOVERABLE_ERROR", 12 );


  /**
   * Array allowing serialization.
   */
  protected static final AGS_State[] array =
  {
    E_AGS_OFF,
    E_AGS_ON_BRIGHTEST,
    E_AGS_ON_RANGE,
    E_AGS_ON_RANK,
    E_AGS_ON_PIXEL,
    E_AGS_IDLE,
    E_AGS_WORKING,
    E_AGS_INITIALISING,
    E_AGS_FAILED,
    E_AGS_INTERACTIVE_WORKING,
    E_AGS_INTERACTIVE_ON,
    E_AGS_ERROR,
    E_AGS_NON_RECOVERABLE_ERROR
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
   * Return an object reference of the AGS_State with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGS_State
   * @return a reference to the AGS_State specified by the argument
   */
  public static AGS_State getReference( String s )
  {
    return( (AGS_State)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGS_State with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGS_State
   * @return a reference to the AGS_State specified by the argument
   */
  public static AGS_State getReference( int i )
  {
    return( (AGS_State)( intHash.get( new Integer( i ) ) ) );
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
  protected AGS_State( String s )
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
  protected AGS_State( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGS_State.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGS_State.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGS
   */
  public ngat.ngtcs.subsystem.TTL_Package getTTL_Package()
  {
    return( ngat.ngtcs.subsystem.TTL_Package.AGS );
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
 *    $Date: 2013-07-04 12:56:08 $
 * $RCSfile: AGS_State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS_State.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
