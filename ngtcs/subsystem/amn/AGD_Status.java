package ngat.ngtcs.subsystem.amn;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGD_Status.  These are:
 * <ul>
 * <li>E_AGD_GEN_ERROR</li>
 * <li>E_AGD_INIT_FAIL</li>
 * <li>E_AGD_SHUTDOWN_ERROR</li>
 * <li>E_AGD_TIMER_SETUP_ERR</li>
 * <li>E_AGD_TIMER_CLOSE_ERR</li>
 * <li>E_AGD_INVALID_STATE</li>
 * <li>E_AGD_SERVER_NOT_FOUND</li>
 * <li>E_AGD_SEND_FAIL</li>
 * <li>E_AGD_INVALID_COMMAND</li>
 * <li>E_AGD_BRAKE_FAIL</li>
 * <li>E_AGD_OID_CONFIG_ERR</li>
 * <li>E_AGD_RECEIVE_ERR</li>
 * <li>E_AGD_HOME_ERR</li>
 * <li>E_AGD_MOVE_ERR</li>
 * <li>E_AGD_STOPPED</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGD_Status implements java.io.Serializable
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
   * 
   */
  public static AGD_Status E_AGD_GEN_ERROR =
    new AGD_Status( "E_AGD_GEN_ERROR", 0 );

  /**
   * 
   */
  public static AGD_Status E_AGD_INIT_FAIL =
    new AGD_Status( "E_AGD_INIT_FAIL", 1 );

  /**
   * 
   */
  public static AGD_Status E_AGD_SHUTDOWN_ERROR =
    new AGD_Status( "E_AGD_SHUTDOWN_ERROR", 2 );

  /**
   * 
   */
  public static AGD_Status E_AGD_TIMER_SETUP_ERR =
    new AGD_Status( "E_AGD_TIMER_SETUP_ERR", 3 );

  /**
   * 
   */
  public static AGD_Status E_AGD_TIMER_CLOSE_ERR =
    new AGD_Status( "E_AGD_TIMER_CLOSE_ERR", 4 );

  /**
   * 
   */
  public static AGD_Status E_AGD_INVALID_STATE =
    new AGD_Status( "E_AGD_INVALID_STATE", 5 );

  /**
   * 
   */
  public static AGD_Status E_AGD_SERVER_NOT_FOUND =
    new AGD_Status( "E_AGD_SERVER_NOT_FOUND", 6 );

  /**
   * 
   */
  public static AGD_Status E_AGD_SEND_FAIL =
    new AGD_Status( "E_AGD_SEND_FAIL", 7 );

  /**
   * 
   */
  public static AGD_Status E_AGD_INVALID_COMMAND =
    new AGD_Status( "E_AGD_INVALID_COMMAND", 8 );

  /**
   * 
   */
  public static AGD_Status E_AGD_BRAKE_FAIL =
    new AGD_Status( "E_AGD_BRAKE_FAIL", 9 );

  /**
   * 
   */
  public static AGD_Status E_AGD_OID_CONFIG_ERR =
    new AGD_Status( "E_AGD_OID_CONFIG_ERR", 10 );

  /**
   * 
   */
  public static AGD_Status E_AGD_RECEIVE_ERR =
    new AGD_Status( "E_AGD_RECEIVE_ERR", 11 );

  /**
   * 
   */
  public static AGD_Status E_AGD_HOME_ERR =
    new AGD_Status( "E_AGD_HOME_ERR", 12 );

  /**
   * 
   */
  public static AGD_Status E_AGD_MOVE_ERR =
    new AGD_Status( "E_AGD_MOVE_ERR", 13 );

  /**
   * 
   */
  public static AGD_Status E_AGD_STOPPED =
    new AGD_Status( "E_AGD_STOPPED", 14 );


  /**
   * Array allowing serialization.
   */
  protected static final AGD_Status[] array =
  {
    E_AGD_GEN_ERROR,
    E_AGD_INIT_FAIL,
    E_AGD_SHUTDOWN_ERROR,
    E_AGD_TIMER_SETUP_ERR,
    E_AGD_TIMER_CLOSE_ERR,
    E_AGD_INVALID_STATE,
    E_AGD_SERVER_NOT_FOUND,
    E_AGD_SEND_FAIL,
    E_AGD_INVALID_COMMAND,
    E_AGD_BRAKE_FAIL,
    E_AGD_OID_CONFIG_ERR,
    E_AGD_RECEIVE_ERR,
    E_AGD_HOME_ERR,
    E_AGD_MOVE_ERR,
    E_AGD_STOPPED
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
   * Return an object reference of the AGD_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGD_Status
   * @return a reference to the AGD_Status specified by the argument
   */
  public static AGD_Status getReference( String s )
  {
    return( (AGD_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGD_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGD_Status
   * @return a reference to the AGD_Status specified by the argument
   */
  public static AGD_Status getReference( int i )
  {
    return( (AGD_Status)( intHash.get( new Integer( i ) ) ) );
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
  protected AGD_Status( String s )
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
  protected AGD_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGD_Status.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGD_Status.
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
 *    $Date: 2013-07-04 12:57:36 $
 * $RCSfile: AGD_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/amn/AGD_Status.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:45  je
 *     Initial revision
 *
 *
 */
