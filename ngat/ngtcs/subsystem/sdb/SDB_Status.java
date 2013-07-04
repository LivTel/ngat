package ngat.ngtcs.subsystem.sdb;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SDB_Status.  These are:
 * <ul>
 * <li>E_SDB_GEN_ERR</li>
 * <li>E_SDB_SRC_UNKNOWN</li>
 * <li>E_SDB_WRONG_SRC</li>
 * <li>E_SDB_WRONG_DST</li>
 * <li>E_SDB_CLA_UNKNOWN</li>
 * <li>E_SDB_NOT_COMMAND</li>
 * <li>E_SDB_CLASS_ERR</li>
 * <li>E_SDB_TRUNCATED</li>
 * <li>E_SDB_MALLOC_FAIL</li>
 * <li>E_SDB_UNKNOWN_DEFN</li>
 * <li>E_SDB_NO_VALUES</li>
 * <li>E_SDB_LL_NOTEMPTY</li>
 * <li>E_SDB_LL_EMPTY</li>
 * <li>E_SDB_LL_NULLENTRY</li>
 * <li>E_SDB_LL_ENDOFLIST</li>
 * <li>E_SDB_UNKNOWN_SERVICE</li>
 * <li>E_SDB_BUFFER_OVERFLOW</li>
 * <li>E_SDB_FWRITE_FAIL</li>
 * <li>E_SDB_FOPEN_FAIL</li>
 * <li>E_SDB_HDR_MN_WRITE_ERR</li>
 * <li>E_SDB_HDR_TS_WRITE_ERR</li>
 * <li>E_SDB_ENCODE_FAILURE</li>
 * <li>E_SDB_SFR_SPAWN_FAIL</li>
 * <li>E_SDB_SFR_NOT_AVAIL</li>
 * <li>E_SDB_NO_UNITS_FILE</li>
 * <li>E_SDB_NO_SHELL</li>
 * <li>E_SDB_WRITE_ERR_LIMIT</li>
 * <li>E_SDB_HBEAT_FAIL</li>
 * <li>E_SDB_NOT_AUTH</li>
 * <li>E_SDB_EOERR_LIST</li>
 * <li>E_SDB_STATUS_MAX_VALUE</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class SDB_Status implements java.io.Serializable
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
    new String( "$Id: SDB_Status.java,v 1.2 2013-07-04 12:59:47 cjm Exp $" );

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
  public static SDB_Status E_SDB_GEN_ERR =
    new SDB_Status( "E_SDB_GEN_ERR", 0 );

  /**
   * Msg rx'd from unrecognised CIL src
   */
  public static SDB_Status E_SDB_SRC_UNKNOWN =
    new SDB_Status( "E_SDB_SRC_UNKNOWN", 1 );

  /**
   * Msg rx'd from inappropriate CIL src
   */
  public static SDB_Status E_SDB_WRONG_SRC =
    new SDB_Status( "E_SDB_WRONG_SRC", 2 );

  /**
   * Msg rx'd with non SDB destination
   */
  public static SDB_Status E_SDB_WRONG_DST =
    new SDB_Status( "E_SDB_WRONG_DST", 3 );

  /**
   * Unrecognised command line arg
   */
  public static SDB_Status E_SDB_CLA_UNKNOWN =
    new SDB_Status( "E_SDB_CLA_UNKNOWN", 4 );

  /**
   * The SDB has rejected a rx'd non command
   */
  public static SDB_Status E_SDB_NOT_COMMAND =
    new SDB_Status( "E_SDB_NOT_COMMAND", 5 );

  /**
   * Message received with incorrect CIL class
   */
  public static SDB_Status E_SDB_CLASS_ERR =
    new SDB_Status( "E_SDB_CLASS_ERR", 6 );

  /**
   * Insufficient data found in CIL message data
   */
  public static SDB_Status E_SDB_TRUNCATED =
    new SDB_Status( "E_SDB_TRUNCATED", 7 );

  /**
   * Failure to allocate dynamic memory
   */
  public static SDB_Status E_SDB_MALLOC_FAIL =
    new SDB_Status( "E_SDB_MALLOC_FAIL", 8 );

  /**
   * Data element definition not known
   */
  public static SDB_Status E_SDB_UNKNOWN_DEFN =
    new SDB_Status( "E_SDB_UNKNOWN_DEFN", 9 );

  /**
   * Data element has no values stored
   */
  public static SDB_Status E_SDB_NO_VALUES =
    new SDB_Status( "E_SDB_NO_VALUES", 10 );

  /**
   * Linked list not empty
   */
  public static SDB_Status E_SDB_LL_NOTEMPTY =
    new SDB_Status( "E_SDB_LL_NOTEMPTY", 11 );

  /**
   * Linked list empty
   */
  public static SDB_Status E_SDB_LL_EMPTY =
    new SDB_Status( "E_SDB_LL_EMPTY", 12 );

  /**
   * Attempted to insert null entry in linked list
   */
  public static SDB_Status E_SDB_LL_NULLENTRY =
    new SDB_Status( "E_SDB_LL_NULLENTRY", 13 );

  /**
   * Unexpected end of link list encountered
   */
  public static SDB_Status E_SDB_LL_ENDOFLIST =
    new SDB_Status( "E_SDB_LL_ENDOFLIST", 14 );

  /**
   * The SDB has rx'd an unrecognised command
   */
  public static SDB_Status E_SDB_UNKNOWN_SERVICE =
    new SDB_Status( "E_SDB_UNKNOWN_SERVICE", 15 );

  /**
   * Too much data generated for buffer capacity
   */
  public static SDB_Status E_SDB_BUFFER_OVERFLOW =
    new SDB_Status( "E_SDB_BUFFER_OVERFLOW", 16 );

  /**
   * Unable to write data to file
   */
  public static SDB_Status E_SDB_FWRITE_FAIL =
    new SDB_Status( "E_SDB_FWRITE_FAIL", 17 );

  /**
   * Unable to open file for storage of data
   */
  public static SDB_Status E_SDB_FOPEN_FAIL =
    new SDB_Status( "E_SDB_FOPEN_FAIL", 18 );

  /**
   * Error writing magic no. to storage file hdr
   */
  public static SDB_Status E_SDB_HDR_MN_WRITE_ERR =
    new SDB_Status( "E_SDB_HDR_MN_WRITE_ERR", 19 );

  /**
   * Error writing timestamp to storage file hdr
   */
  public static SDB_Status E_SDB_HDR_TS_WRITE_ERR =
    new SDB_Status( "E_SDB_HDR_TS_WRITE_ERR", 20 );

  /**
   * Unable to correctly generate a storage code
   */
  public static SDB_Status E_SDB_ENCODE_FAILURE =
    new SDB_Status( "E_SDB_ENCODE_FAILURE", 21 );

  /**
   * Unable to spawn the storage file reader
   */
  public static SDB_Status E_SDB_SFR_SPAWN_FAIL =
    new SDB_Status( "E_SDB_SFR_SPAWN_FAIL", 22 );

  /**
   * Storage file reading not available (not QNX)
   */
  public static SDB_Status E_SDB_SFR_NOT_AVAIL =
    new SDB_Status( "E_SDB_SFR_NOT_AVAIL", 23 );

  /**
   * Units file empty or non existant
   */
  public static SDB_Status E_SDB_NO_UNITS_FILE =
    new SDB_Status( "E_SDB_NO_UNITS_FILE", 24 );

  /**
   * There's no shell or command handler available
   */
  public static SDB_Status E_SDB_NO_SHELL =
    new SDB_Status( "E_SDB_NO_SHELL", 25 );

  /**
   * Max no. write to file failures exceeded
   */
  public static SDB_Status E_SDB_WRITE_ERR_LIMIT =
    new SDB_Status( "E_SDB_WRITE_ERR_LIMIT", 26 );

  /**
   * No heartbeats or error processing response
   */
  public static SDB_Status E_SDB_HBEAT_FAIL =
    new SDB_Status( "E_SDB_HBEAT_FAIL", 27 );

  /**
   * Not authorised to preform this command
   */
  public static SDB_Status E_SDB_NOT_AUTH =
    new SDB_Status( "E_SDB_NOT_AUTH", 28 );


  /**
   * Array allowing serialization.
   */
  protected static final SDB_Status[] array =
  {
    E_SDB_GEN_ERR,
    E_SDB_SRC_UNKNOWN,
    E_SDB_WRONG_SRC,
    E_SDB_WRONG_DST,
    E_SDB_CLA_UNKNOWN,
    E_SDB_NOT_COMMAND,
    E_SDB_CLASS_ERR,
    E_SDB_TRUNCATED,
    E_SDB_MALLOC_FAIL,
    E_SDB_UNKNOWN_DEFN,
    E_SDB_NO_VALUES,
    E_SDB_LL_NOTEMPTY,
    E_SDB_LL_EMPTY,
    E_SDB_LL_NULLENTRY,
    E_SDB_LL_ENDOFLIST,
    E_SDB_UNKNOWN_SERVICE,
    E_SDB_BUFFER_OVERFLOW,
    E_SDB_FWRITE_FAIL,
    E_SDB_FOPEN_FAIL,
    E_SDB_HDR_MN_WRITE_ERR,
    E_SDB_HDR_TS_WRITE_ERR,
    E_SDB_ENCODE_FAILURE,
    E_SDB_SFR_SPAWN_FAIL,
    E_SDB_SFR_NOT_AVAIL,
    E_SDB_NO_UNITS_FILE,
    E_SDB_NO_SHELL,
    E_SDB_WRITE_ERR_LIMIT,
    E_SDB_HBEAT_FAIL,
    E_SDB_NOT_AUTH
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
   * Return an object reference of the SDB_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SDB_Status
   * @return a reference to the SDB_Status specified by the argument
   */
  public static SDB_Status getReference( String s )
  {
    return( (SDB_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SDB_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SDB_Status
   * @return a reference to the SDB_Status specified by the argument
   */
  public static SDB_Status getReference( int i )
  {
    return( (SDB_Status)( intHash.get( new Integer( i ) ) ) );
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
  protected SDB_Status( String s )
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
  protected SDB_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SDB_Status.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SDB_Status.
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
 *    $Date: 2013-07-04 12:59:47 $
 * $RCSfile: SDB_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_Status.java,v $
 *      $Id: SDB_Status.java,v 1.2 2013-07-04 12:59:47 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:40  je
 *     Initial revision
 *
 *
 */
