package ngat.ngtcs.subsystem.ept;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type EPT_Status.  These are:
 * <ul>
 * <li>E_EPT_GEN_ERR</li>
 * <li>E_EPT_ERR_SETUP</li>
 * <li>E_EPT_ERR_CIL_RX</li>
 * <li>E_EPT_ERR_SDB_SUBMIT</li>
 * <li>E_EPT_SW_SIMULATE</li>
 * <li>E_EPT_UNKNOWN_SERVICE</li>
 * <li>E_EPT_SERVICE_NOT_IMPL</li>
 * <li>E_EPT_NOT_IMPLEMENTED</li>
 * <li>E_EPT_MSG_LEN_ERR</li>
 * <li>E_EPT_UNEXPECTED_SIM</li>
 * <li>E_EPT_UNEXPECTED_MSG</li>
 * <li>E_EPT_INVALID_SHUTTER</li>
 * <li>E_EPT_INVALID_DATUM</li>
 * <li>E_EPT_INVALID_COMMAND</li>
 * <li>E_EPT_PARSE_ERROR</li>
 * <li>E_EPT_PARAM_RANGE_ERR</li>
 * <li>E_EPT_NO_HEARTBEATS</li>
 * <li>E_EPT_CMD_NOT_PERMITTED</li>
 * <li>E_EPT_UNKNOWN_DEMAND</li>
 * <li>E_EPT_PARAM_SIZE_ERR</li>
 * <li>E_EPT_NO_TIMED_CMDS</li>
 * <li>E_EPT_UNKNOWN_OID</li>
 * <li>E_EPT_OID_READ_ONLY</li>
 * <li>E_EPT_ERR_READ_PKTS</li>
 * <li>E_EPT_ERR_READ_LENGTH</li>
 * <li>E_EPT_INVALID_LENGTH</li>
 * <li>E_EPT_CHKSUM_ERROR</li>
 * <li>E_EPT_SER_REPORTED_ERROR</li>
 * <li>E_EPT_SER_UNEXPECTED_RSP</li>
 * <li>E_EPT_DMD_PENDING</li>
 * <li>E_EPT_WRITE_FAILED</li>
 * <li>E_EPT_READ_FAILED</li>
 * <li>E_EPT_PLC_INVALID</li>
 * <li>E_EPT_CONFIG_FILE_ERROR</li>
 * <li>E_EPT_STATE_UNKNOWN</li>
 * <li>E_EPT_STATUS_EOL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public final class EPT_Status
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
    new String( "$Id: EPT_Status.java,v 1.2 2013-07-04 12:58:53 cjm Exp $" );

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
  private static final TTL_Package ttlPackage = TTL_Package.EPT;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public final static EPT_Status E_EPT_GEN_ERR =
      new EPT_Status( "E_EPT_GEN_ERR", 0 );

  /**
   * 1 Error performing setup
   */
  public final static EPT_Status E_EPT_ERR_SETUP =
      new EPT_Status( "E_EPT_ERR_SETUP", 1 );

  /**
   * 2 Unexpected CIL message received
   */
  public final static EPT_Status E_EPT_ERR_CIL_RX =
      new EPT_Status( "E_EPT_ERR_CIL_RX", 2 );

  /**
   * 3 Error performing SDB submission
   */
  public final static EPT_Status E_EPT_ERR_SDB_SUBMIT =
      new EPT_Status( "E_EPT_ERR_SDB_SUBMIT", 3 );

  /**
   * 4 Using software to simulate encl. operation
   */
  public final static EPT_Status E_EPT_SW_SIMULATE =
      new EPT_Status( "E_EPT_SW_SIMULATE", 4 );

  /**
   * 5 Requested service not provided by this task
   */
  public final static EPT_Status E_EPT_UNKNOWN_SERVICE =
      new EPT_Status( "E_EPT_UNKNOWN_SERVICE", 5 );

  /**
   * 6 Service not implemented in this version
   */
  public final static EPT_Status E_EPT_SERVICE_NOT_IMPL =
      new EPT_Status( "E_EPT_SERVICE_NOT_IMPL", 6 );

  /**
   * 7 General functionality not implemented
   */
  public final static EPT_Status E_EPT_NOT_IMPLEMENTED =
      new EPT_Status( "E_EPT_NOT_IMPLEMENTED", 7 );

  /**
   * 8 Incorrect no. bytes in CIL data block
   */
  public final static EPT_Status E_EPT_MSG_LEN_ERR =
      new EPT_Status( "E_EPT_MSG_LEN_ERR", 8 );

  /**
   * 9 Simulate fn. called without simulate selected
   */
  public final static EPT_Status E_EPT_UNEXPECTED_SIM =
      new EPT_Status( "E_EPT_UNEXPECTED_SIM", 9 );

  /**
   * a Unexpected CIL message received
   */
  public final static EPT_Status E_EPT_UNEXPECTED_MSG =
      new EPT_Status( "E_EPT_UNEXPECTED_MSG", 10 );

  /**
   * b Shutter ID number not recognised
   */
  public final static EPT_Status E_EPT_INVALID_SHUTTER =
      new EPT_Status( "E_EPT_INVALID_SHUTTER", 11 );

  /**
   * c Datum ID out of range
   */
  public final static EPT_Status E_EPT_INVALID_DATUM =
      new EPT_Status( "E_EPT_INVALID_DATUM", 12 );

  /**
   * d Command not recognised
   */
  public final static EPT_Status E_EPT_INVALID_COMMAND =
      new EPT_Status( "E_EPT_INVALID_COMMAND", 13 );

  /**
   * e Failed to read an ASCII argument token
   */
  public final static EPT_Status E_EPT_PARSE_ERROR =
      new EPT_Status( "E_EPT_PARSE_ERROR", 14 );

  /**
   * f Specified parameter outside allowed range
   */
  public final static EPT_Status E_EPT_PARAM_RANGE_ERR =
      new EPT_Status( "E_EPT_PARAM_RANGE_ERR", 15 );

  /**
   * 10 No heartbeats received recently
   */
  public final static EPT_Status E_EPT_NO_HEARTBEATS =
      new EPT_Status( "E_EPT_NO_HEARTBEATS", 16 );

  /**
   * 11 Command not permitted because state not OKAY
   */
  public final static EPT_Status E_EPT_CMD_NOT_PERMITTED =
      new EPT_Status( "E_EPT_CMD_NOT_PERMITTED", 17 );

  /**
   * 12 Demanded parameter not known
   */
  public final static EPT_Status E_EPT_UNKNOWN_DEMAND =
      new EPT_Status( "E_EPT_UNKNOWN_DEMAND", 18 );

  /**
   * 13 A value to be set is not of a supported size
   */
  public final static EPT_Status E_EPT_PARAM_SIZE_ERR =
      new EPT_Status( "E_EPT_PARAM_SIZE_ERR", 19 );

  /**
   * 14 Timed set commands are not supported
   */
  public final static EPT_Status E_EPT_NO_TIMED_CMDS =
      new EPT_Status( "E_EPT_NO_TIMED_CMDS", 20 );

  /**
   * 15 Object ID not recognised
   */
  public final static EPT_Status E_EPT_UNKNOWN_OID =
      new EPT_Status( "E_EPT_UNKNOWN_OID", 21 );

  /**
   * 16 Attempt made to change a read only OID value
   */
  public final static EPT_Status E_EPT_OID_READ_ONLY =
      new EPT_Status( "E_EPT_OID_READ_ONLY", 22 );

  /**
   * 17 Unable to read no. expected packets to follow
   */
  public final static EPT_Status E_EPT_ERR_READ_PKTS =
      new EPT_Status( "E_EPT_ERR_READ_PKTS", 23 );

  /**
   * 18 Failed to read the expected length of packet
   */
  public final static EPT_Status E_EPT_ERR_READ_LENGTH =
      new EPT_Status( "E_EPT_ERR_READ_LENGTH", 24 );

  /**
   * 19 Incorrect length of received packet
   */
  public final static EPT_Status E_EPT_INVALID_LENGTH =
      new EPT_Status( "E_EPT_INVALID_LENGTH", 25 );

  /**
   * 1a Calculated and reported checksums don't match
   */
  public final static EPT_Status E_EPT_CHKSUM_ERROR =
      new EPT_Status( "E_EPT_CHKSUM_ERROR", 26 );

  /**
   * 1b Error response received from serial device.
   */
  public final static EPT_Status E_EPT_SER_REPORTED_ERROR =
      new EPT_Status( "E_EPT_SER_REPORTED_ERROR", 27 );

  /**
   * 1c Response received not for command sent.
   */
  public final static EPT_Status E_EPT_SER_UNEXPECTED_RSP =
      new EPT_Status( "E_EPT_SER_UNEXPECTED_RSP", 28 );

  /**
   * 1d An operation is already pending
   */
  public final static EPT_Status E_EPT_DMD_PENDING =
      new EPT_Status( "E_EPT_DMD_PENDING", 29 );

  /**
   * 1e Write to PLC failed
   */
  public final static EPT_Status E_EPT_WRITE_FAILED =
      new EPT_Status( "E_EPT_WRITE_FAILED", 30 );

  /**
   * 1f Read to PLC failed
   */
  public final static EPT_Status E_EPT_READ_FAILED =
      new EPT_Status( "E_EPT_READ_FAILED", 31 );

  /**
   * More than one PLC bit is set for the same OID
   */
  public final static EPT_Status E_EPT_PLC_INVALID =
      new EPT_Status( "E_EPT_PLC_INVALID", 32 );

  /**
   * 20 Format of the config file is wrong.
   */
  public final static EPT_Status E_EPT_CONFIG_FILE_ERROR =
      new EPT_Status( "E_EPT_CONFIG_FILE_ERROR", 33 );

  /**
   * Value not in range for this OID.
   */
  public final static EPT_Status E_EPT_STATE_UNKNOWN =
      new EPT_Status( "E_EPT_STATE_UNKNOWN", 34 );

  /**
   * 
   */
  public final static EPT_Status E_EPT_STATUS_EOL =
      new EPT_Status( "E_EPT_STATUS_EOL", 35 );

  /**
   * Array allowing serialization.
   */
  protected static final EPT_Status[] array =
  {
    E_EPT_GEN_ERR,
    E_EPT_ERR_SETUP,
    E_EPT_ERR_CIL_RX,
    E_EPT_ERR_SDB_SUBMIT,
    E_EPT_SW_SIMULATE,
    E_EPT_UNKNOWN_SERVICE,
    E_EPT_SERVICE_NOT_IMPL,
    E_EPT_NOT_IMPLEMENTED,
    E_EPT_MSG_LEN_ERR,
    E_EPT_UNEXPECTED_SIM,
    E_EPT_UNEXPECTED_MSG,
    E_EPT_INVALID_SHUTTER,
    E_EPT_INVALID_DATUM,
    E_EPT_INVALID_COMMAND,
    E_EPT_PARSE_ERROR,
    E_EPT_PARAM_RANGE_ERR,
    E_EPT_NO_HEARTBEATS,
    E_EPT_CMD_NOT_PERMITTED,
    E_EPT_UNKNOWN_DEMAND,
    E_EPT_PARAM_SIZE_ERR,
    E_EPT_NO_TIMED_CMDS,
    E_EPT_UNKNOWN_OID,
    E_EPT_OID_READ_ONLY,
    E_EPT_ERR_READ_PKTS,
    E_EPT_ERR_READ_LENGTH,
    E_EPT_INVALID_LENGTH,
    E_EPT_CHKSUM_ERROR,
    E_EPT_SER_REPORTED_ERROR,
    E_EPT_SER_UNEXPECTED_RSP,
    E_EPT_DMD_PENDING,
    E_EPT_WRITE_FAILED,
    E_EPT_READ_FAILED,
    E_EPT_PLC_INVALID,
    E_EPT_CONFIG_FILE_ERROR,
    E_EPT_STATE_UNKNOWN,
    E_EPT_STATUS_EOL
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
   * Return an object reference of the EPT_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the EPT_Status
   * @return a reference to the EPT_Status specified by the argument
   */
  public static EPT_Status getReference( String s )
  {
    return( (EPT_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the EPT_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the EPT_Status
   * @return a reference to the EPT_Status specified by the argument
   */
  public static EPT_Status getReference( int i )
  {
    return( (EPT_Status)( intHash.get( new Integer( i ) ) ) );
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
  private EPT_Status( String s )
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
  private EPT_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this EPT_Status.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this EPT_Status.
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
 *    $Date: 2013-07-04 12:58:53 $
 * $RCSfile: EPT_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ept/EPT_Status.java,v $
 *      $Id: EPT_Status.java,v 1.2 2013-07-04 12:58:53 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:30  je
 *     Initial revision
 *
 *
 */
