package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGS_Status.
 * <p>
 * The available status are:
 * <ul>
 * <li>E_AGS_GEN_ERR</li>
 * <li>E_AGS_UNEXPECTED_MSG</li>
 * <li>E_AGS_UNKNOWN_SOURCE</li>
 * <li>E_AGS_NO_HEARTBEATS</li>
 * <li>E_AGS_INVREPLY</li>
 * <li>E_AGS_CMDQ_EMPTY</li>
 * <li>E_AGS_CONFIG_FILE_ERROR</li>
 * <li>E_AGS_INVALID_DATUM</li>
 * <li>E_AGS_INV_CMD</li>
 * <li>E_AGS_BAD_CMD</li>
 * <li>E_AGS_CMDQ_ERR</li>
 * <li>E_AGS_INV_STATE</li>
 * <li>E_AGS_UNKNOWN_OID</li>
 * <li>E_AGS_CMDQ_FULL</li>
 * <li>E_AGS_CMD_TIMEOUT</li>
 * <li>E_AGS_CMD_NOT_PERMITTED</li>
 * <li>E_AGS_LOOP_STOPPING</li>
 * <li>E_AGS_LOOP_RUNNING</li>
 * <li>E_AGS_LOOP_ERROR</li>
 * <li>E_AGS_BAD_FORMAT</li>
 * <li>E_AGS_HW_ERR</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGS_Status implements java.io.Serializable
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: AGS_Status.java,v 1.1 2003-09-19 16:08:38 je Exp $" );

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

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Miscellaneous error.
   */
  public static AGS_Status E_AGS_GEN_ERR =
    new AGS_Status( "E_AGS_GEN_ERR", 0 );

  /**
   * Unexpected message class.
   */
  public static AGS_Status E_AGS_UNEXPECTED_MSG =
    new AGS_Status( "E_AGS_UNEXPECTED_MSG", 1 );

  /**
   * Unexpected message source.
   */
  public static AGS_Status E_AGS_UNKNOWN_SOURCE =
    new AGS_Status( "E_AGS_UNKNOWN_SOURCE", 2 );

  /**
   * Heartbeats have been missed.
   */
  public static AGS_Status E_AGS_NO_HEARTBEATS =
    new AGS_Status( "E_AGS_NO_HEARTBEATS", 3 );

  /**
   * Invalid reply to an AGS command.
   */
  public static AGS_Status E_AGS_INVREPLY =
    new AGS_Status( "E_AGS_INVREPLY", 4 );

  /**
   * Command queue empty.
   */
  public static AGS_Status E_AGS_CMDQ_EMPTY =
    new AGS_Status( "E_AGS_CMDQ_EMPTY", 5 );

  /**
   * Error reading config file.
   */
  public static AGS_Status E_AGS_CONFIG_FILE_ERROR =
    new AGS_Status( "E_AGS_CONFIG_FILE_ERROR", 6 );

  /**
   * Datum not in range of eAgsDataId_t.
   */
  public static AGS_Status E_AGS_INVALID_DATUM =
    new AGS_Status( "E_AGS_INVALID_DATUM", 7 );

  /**
   * Invalid command.
   */
  public static AGS_Status E_AGS_INV_CMD =
    new AGS_Status( "E_AGS_INV_CMD", 8 );

  /**
   * Badly formed command.
   */
  public static AGS_Status E_AGS_BAD_CMD =
    new AGS_Status( "E_AGS_BAD_CMD", 9 );

  /**
   * Error with command queue to Agg.
   */
  public static AGS_Status E_AGS_CMDQ_ERR =
    new AGS_Status( "E_AGS_CMDQ_ERR", 10 );

  /**
   * Invalid state requested.
   */
  public static AGS_Status E_AGS_INV_STATE =
    new AGS_Status( "E_AGS_INV_STATE", 11 );

  /**
   * Request to set unknown OID.
   */
  public static AGS_Status E_AGS_UNKNOWN_OID =
    new AGS_Status( "E_AGS_UNKNOWN_OID", 12 );

  /**
   * Command queue full.
   */
  public static AGS_Status E_AGS_CMDQ_FULL =
    new AGS_Status( "E_AGS_CMDQ_FULL", 13 );

  /**
   * Command to Agg timed out.
   */
  public static AGS_Status E_AGS_CMD_TIMEOUT =
    new AGS_Status( "E_AGS_CMD_TIMEOUT", 14 );

  /**
   * Command to Agg not permitted.
   */
  public static AGS_Status E_AGS_CMD_NOT_PERMITTED =
    new AGS_Status( "E_AGS_CMD_NOT_PERMITTED", 15 );

  /**
   * Command rejected already stopping.
   */
  public static AGS_Status E_AGS_LOOP_STOPPING =
    new AGS_Status( "E_AGS_LOOP_STOPPING", 16 );

  /**
   * Command rejected already running.
   */
  public static AGS_Status E_AGS_LOOP_RUNNING =
    new AGS_Status( "E_AGS_LOOP_RUNNING", 17 );

  /**
   * Error starting guide loop.
   */
  public static AGS_Status E_AGS_LOOP_ERROR =
    new AGS_Status( "E_AGS_LOOP_ERROR", 18 );

  /**
   * Badly formatted command sent to Agg.
   */
  public static AGS_Status E_AGS_BAD_FORMAT =
    new AGS_Status( "E_AGS_BAD_FORMAT", 19 );

  /**
   * Error reported by camera hardware.
   */
  public static AGS_Status E_AGS_HW_ERR =
    new AGS_Status( "E_AGS_HW_ERR", 20 );

  /**
   * Array allowing serialization.
   */
  protected static final AGS_Status[] array =
  {
    E_AGS_GEN_ERR,
    E_AGS_UNEXPECTED_MSG,
    E_AGS_UNKNOWN_SOURCE,
    E_AGS_NO_HEARTBEATS,
    E_AGS_INVREPLY,
    E_AGS_CMDQ_EMPTY,
    E_AGS_CONFIG_FILE_ERROR,
    E_AGS_INVALID_DATUM,
    E_AGS_INV_CMD,
    E_AGS_BAD_CMD,
    E_AGS_CMDQ_ERR,
    E_AGS_INV_STATE,
    E_AGS_UNKNOWN_OID,
    E_AGS_CMDQ_FULL,
    E_AGS_CMD_TIMEOUT,
    E_AGS_CMD_NOT_PERMITTED,
    E_AGS_LOOP_STOPPING,
    E_AGS_LOOP_RUNNING,
    E_AGS_LOOP_ERROR,
    E_AGS_BAD_FORMAT,
    E_AGS_HW_ERR,
  };

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Return an object reference of the AGS_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGS_Status
   * @return a reference to the AGS_Status specified by the argument
   */
  public static AGS_Status getReference( String s )
  {
    return( (AGS_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGS_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGS_Status
   * @return a reference to the AGS_Status specified by the argument
   */
  public static AGS_Status getReference( int i )
  {
    return( (AGS_Status)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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
  protected AGS_Status( String s )
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
  protected AGS_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGS_Status.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGS_Status.
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
 *    $Date: 2003-09-19 16:08:38 $
 * $RCSfile: AGS_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS_Status.java,v $
 *      $Id: AGS_Status.java,v 1.1 2003-09-19 16:08:38 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
