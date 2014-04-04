package ngat.ngtcs.subsystem.spt;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SPT_Status.  These are:
 * <ul>
 * <li>E_SPT_GEN_ERR</li>
 * <li>E_SPT_ERR_SETUP</li>
 * <li>E_SPT_UNKNOWN_SERVICE</li>
 * <li>E_SPT_SERVICE_NOT_IMPL</li>
 * <li>E_SPT_NOT_IMPLEMENTED</li>
 * <li>E_SPT_MSG_LEN_ERR</li>
 * <li>E_SPT_UNEXPECTED_MSG</li>
 * <li>E_SPT_PARAM_RANGE_ERR</li>
 * <li>E_SPT_NO_HEARTBEATS</li>
 * <li>E_SPT_DMD_NOT_PERMITTED</li>
 * <li>E_SPT_PARAM_SIZE_ERR</li>
 * <li>E_SPT_NO_TIMED_DMDS</li>
 * <li>E_SPT_UNKNOWN_OID</li>
 * <li>E_SPT_DMD_PENDING</li>
 * <li>E_SPT_WRITE_FAILED</li>
 * <li>E_SPT_READ_FAILED</li>
 * <li>E_SPT_PLC_INVALID</li>
 * <li>E_SPT_CONFIG_FILE_ERROR</li>
 * <li>E_SPT_INVALID_DATUM</li>
 * <li>E_SPT_NO_TIMED_CMDS</li>
 * <li>E_SPT_UNKNOWN_DEMAND</li>
 * <li>E_SPT_PARSE_ERROR</li>
 * <li>E_SPT_UNKNOWN_STATE</li>
 * <li>E_SPT_IMMED_SDOWN</li>
 * <li>E_SPT_SW_SIMULATE</li>
 * <li>E_SPT_UNEXPECTED_SIM</li>
 * <li>E_SPT_ERR_SDB_SUBMIT</li>
 * <li>E_SPT_STATUS_EOL</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public final class SPT_Status
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
  public static final String RevisionString =
    new String( "$Id$" );

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

  /**
   * TTL package to which this data refers.
   */
  private static final TTL_Package ttlPackage = TTL_Package.SPT;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static SPT_Status E_SPT_GEN_ERR =
      new SPT_Status( "E_SPT_GEN_ERR", 0 );

  /**
   * Error performing setup
   */
  public final static SPT_Status E_SPT_ERR_SETUP =
      new SPT_Status( "E_SPT_ERR_SETUP", 1 );

  /**
   * Requested service not provided by this task
   */
  public final static SPT_Status E_SPT_UNKNOWN_SERVICE =
      new SPT_Status( "E_SPT_UNKNOWN_SERVICE", 2 );

  /**
   * Service not implemented in this version
   */
  public final static SPT_Status E_SPT_SERVICE_NOT_IMPL =
      new SPT_Status( "E_SPT_SERVICE_NOT_IMPL", 3 );

  /**
   * General functionality not implemented
   */
  public final static SPT_Status E_SPT_NOT_IMPLEMENTED =
      new SPT_Status( "E_SPT_NOT_IMPLEMENTED", 4 );

  /**
   * Incorrect no. bytes in CIL data block
   */
  public final static SPT_Status E_SPT_MSG_LEN_ERR =
      new SPT_Status( "E_SPT_MSG_LEN_ERR", 5 );

  /**
   * Unexpected CIL message received
   */
  public final static SPT_Status E_SPT_UNEXPECTED_MSG =
      new SPT_Status( "E_SPT_UNEXPECTED_MSG", 6 );

  /**
   * Specified parameter outside allowed range
   */
  public final static SPT_Status E_SPT_PARAM_RANGE_ERR =
      new SPT_Status( "E_SPT_PARAM_RANGE_ERR", 7 );

  /**
   * No heartbeats received recently
   */
  public final static SPT_Status E_SPT_NO_HEARTBEATS =
      new SPT_Status( "E_SPT_NO_HEARTBEATS", 8 );

  /**
   * Command not permitted because state not OKAY
   */
  public final static SPT_Status E_SPT_DMD_NOT_PERMITTED =
      new SPT_Status( "E_SPT_DMD_NOT_PERMITTED", 9 );

  /**
   * A value to be set is not of a supported size
   */
  public final static SPT_Status E_SPT_PARAM_SIZE_ERR =
      new SPT_Status( "E_SPT_PARAM_SIZE_ERR", 10 );

  /**
   * Timed set commands are not supported
   */
  public final static SPT_Status E_SPT_NO_TIMED_DMDS =
      new SPT_Status( "E_SPT_NO_TIMED_DMDS", 11 );

  /**
   * Object ID not recognised
   */
  public final static SPT_Status E_SPT_UNKNOWN_OID =
      new SPT_Status( "E_SPT_UNKNOWN_OID", 12 );

  /**
   * An operation is already pending
   */
  public final static SPT_Status E_SPT_DMD_PENDING =
      new SPT_Status( "E_SPT_DMD_PENDING", 13 );

  /**
   * Write to PLC failed
   */
  public final static SPT_Status E_SPT_WRITE_FAILED =
      new SPT_Status( "E_SPT_WRITE_FAILED", 14 );

  /**
   * Read to PLC failed
   */
  public final static SPT_Status E_SPT_READ_FAILED =
      new SPT_Status( "E_SPT_READ_FAILED", 15 );

  /**
   * More than one bit set in PLC invalid state.
   */
  public final static SPT_Status E_SPT_PLC_INVALID =
      new SPT_Status( "E_SPT_PLC_INVALID", 16 );

  /**
   * Error found in Config file format.
   */
  public final static SPT_Status E_SPT_CONFIG_FILE_ERROR =
      new SPT_Status( "E_SPT_CONFIG_FILE_ERROR", 17 );

  /**
   * Datum is not in rage of eSptDataId_t.
   */
  public final static SPT_Status E_SPT_INVALID_DATUM =
      new SPT_Status( "E_SPT_INVALID_DATUM", 18 );

  /**
   * Set 1 commands must have 0 timestamps.
   */
  public final static SPT_Status E_SPT_NO_TIMED_CMDS =
      new SPT_Status( "E_SPT_NO_TIMED_CMDS", 19 );

  /**
   * Received Demand Oid is unknown
   */
  public final static SPT_Status E_SPT_UNKNOWN_DEMAND =
      new SPT_Status( "E_SPT_UNKNOWN_DEMAND", 20 );

  /**
   * Parsing of string failed; incorrect format.
   */
  public final static SPT_Status E_SPT_PARSE_ERROR =
      new SPT_Status( "E_SPT_PARSE_ERROR", 21 );

  /**
   * Value not in range for this OID.
   */
  public final static SPT_Status E_SPT_UNKNOWN_STATE =
      new SPT_Status( "E_SPT_UNKNOWN_STATE", 22 );

  /**
   * Immediate shutdown requested
   */
  public final static SPT_Status E_SPT_IMMED_SDOWN =
      new SPT_Status( "E_SPT_IMMED_SDOWN", 23 );

  /**
   * Using software to simulate PLC operation
   */
  public final static SPT_Status E_SPT_SW_SIMULATE =
      new SPT_Status( "E_SPT_SW_SIMULATE", 24 );

  /**
   * Simulate fn. called without simulate selected
   */
  public final static SPT_Status E_SPT_UNEXPECTED_SIM =
      new SPT_Status( "E_SPT_UNEXPECTED_SIM", 25 );

  /**
   * Error performing SDB submission
   */
  public final static SPT_Status E_SPT_ERR_SDB_SUBMIT =
      new SPT_Status( "E_SPT_ERR_SDB_SUBMIT", 26 );

  /**
   * 
   */
  public final static SPT_Status E_SPT_STATUS_EOL =
      new SPT_Status( "E_SPT_STATUS_EOL", 27 );

  /**
   * Array allowing serialization.
   */
  protected static final SPT_Status[] array =
  {
    E_SPT_GEN_ERR,
    E_SPT_ERR_SETUP,
    E_SPT_UNKNOWN_SERVICE,
    E_SPT_SERVICE_NOT_IMPL,
    E_SPT_NOT_IMPLEMENTED,
    E_SPT_MSG_LEN_ERR,
    E_SPT_UNEXPECTED_MSG,
    E_SPT_PARAM_RANGE_ERR,
    E_SPT_NO_HEARTBEATS,
    E_SPT_DMD_NOT_PERMITTED,
    E_SPT_PARAM_SIZE_ERR,
    E_SPT_NO_TIMED_DMDS,
    E_SPT_UNKNOWN_OID,
    E_SPT_DMD_PENDING,
    E_SPT_WRITE_FAILED,
    E_SPT_READ_FAILED,
    E_SPT_PLC_INVALID,
    E_SPT_CONFIG_FILE_ERROR,
    E_SPT_INVALID_DATUM,
    E_SPT_NO_TIMED_CMDS,
    E_SPT_UNKNOWN_DEMAND,
    E_SPT_PARSE_ERROR,
    E_SPT_UNKNOWN_STATE,
    E_SPT_IMMED_SDOWN,
    E_SPT_SW_SIMULATE,
    E_SPT_UNEXPECTED_SIM,
    E_SPT_ERR_SDB_SUBMIT,
    E_SPT_STATUS_EOL
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
   * Return an object reference of the SPT_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SPT_Status
   * @return a reference to the SPT_Status specified by the argument
   */
  public static SPT_Status getReference( String s )
  {
    return( (SPT_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SPT_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SPT_Status
   * @return a reference to the SPT_Status specified by the argument
   */
  public static SPT_Status getReference( int i )
  {
    return( (SPT_Status)( intHash.get( new Integer( i ) ) ) );
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
  private SPT_Status( String s )
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
  private SPT_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this SPT_Status.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SPT_Status.
   * @return intValue
   * @see #intValue
   */
  public final int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package for which this data refers.
   * @return ttlPackage
   * @see #ttlPackage
   */
  public TTL_Package getTTL_Package()
  {
    return( ttlPackage );
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
 *    $Date: 2006-11-20 14:47:01 $
 * $RCSfile: SPT_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/spt/SPT_Status.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
