package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_SystemRequest.
 * <p>
 * The allowable references of TTL_SystemRequest are:
 * <ul>
 * <li>E_MCP_SYSREQ_NULL</li>
 * <li>E_MCP_SYSREQ_SAFE_STATE</li>
 * <li>E_MCP_SYSREQ_ACTIVATE</li>
 * <li>E_MCP_SYSREQ_FULL_SHUTDOWN</li>
 * <li>E_MCP_SYSREQ_FULL_STARTUP</li>
 * <li>E_MCP_SYSREQ_PARTIAL_SHUTDOWN</li>
 * <li>E_MCP_SYSREQ_PARTIAL_STARTUP</li>
 * <li>E_MCP_SYSREQ_START_OBSERVATION</li>
 * <li>E_MCP_SYSREQ_STOP_OBSERVATION</li>
 * <li>E_MCP_SYSREQ_IMMED_SHUTDOWN</li>
 * <li>E_MCP_SYSREQ_FULL_RESTART</li>
 * <li>E_MCP_SYSREQ_FAIL_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_FAIL_RESTORE</li>
 * <li>E_MCP_SYSREQ_TIME_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_TIME_RESTORE</li>
 * <li>E_MCP_SYSREQ_WMS_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_WMS_RESTORE</li>
 * <li>E_MCP_SYSREQ_EPT_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_EPT_RESTORE</li>
 * <li>E_MCP_SYSREQ_SPT_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_SPT_RESTORE</li>
 * <li>E_MCP_SYSREQ_NODE_OVERRIDE</li>
 * <li>E_MCP_SYSREQ_NODE_RESTORE</li>
 * <li>E_MCP_SYSREQ_CANCEL_START_OBS</li>
 * <li>E_MCP_SYSREQ_CANCEL_STOP_OBS</li>
 * <li>E_MCP_SYSREQ_REREAD_OBS</li>
 * <li>E_MCP_SYSREQ_SOFT_SHUTDOWN</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_SystemRequest implements java.io.Serializable
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
   * NULL/no request.
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_NULL =
    new TTL_SystemRequest( "E_MCP_SYSREQ_NULL", 0 );

  /**
   * Request for safe state
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_SAFE_STATE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_SAFE_STATE", 1 );

  /**
   * Request for activate following safe state
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_ACTIVATE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_ACTIVATE", 2 );

  /**
   * Request for full shutdown
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_FULL_SHUTDOWN =
    new TTL_SystemRequest( "E_MCP_SYSREQ_FULL_SHUTDOWN", 3 );

  /**
   * Request for full start up (unused)
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_FULL_STARTUP =
    new TTL_SystemRequest( "E_MCP_SYSREQ_FULL_STARTUP", 4 );

  /**
   * Request for partial shutdown
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_PARTIAL_SHUTDOWN =
    new TTL_SystemRequest( "E_MCP_SYSREQ_PARTIAL_SHUTDOWN", 5 );

  /**
   * Request for partial start up
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_PARTIAL_STARTUP =
    new TTL_SystemRequest( "E_MCP_SYSREQ_PARTIAL_STARTUP", 6 );

  /**
   * Request for system observation start
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_START_OBSERVATION =
    new TTL_SystemRequest( "E_MCP_SYSREQ_START_OBSERVATION", 7 );

  /**
   * Request for system observation stop
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_STOP_OBSERVATION =
    new TTL_SystemRequest( "E_MCP_SYSREQ_STOP_OBSERVATION", 8 );

  /**
   * Request for immediate full shutdown
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_IMMED_SHUTDOWN =
    new TTL_SystemRequest( "E_MCP_SYSREQ_IMMED_SHUTDOWN", 9 );

  /**
   * Request for full system restart
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_FULL_RESTART =
    new TTL_SystemRequest( "E_MCP_SYSREQ_FULL_RESTART", 10 );

  /**
   * Request for shutdown on failure override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_FAIL_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_FAIL_OVERRIDE", 11 );

  /**
   * Request to cancel shutdown on failure override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_FAIL_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_FAIL_RESTORE", 12 );

  /**
   * Request for observation time override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_TIME_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_TIME_OVERRIDE", 13 );

  /**
   * Request to cancel observation time override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_TIME_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_TIME_RESTORE", 14 );

  /**
   * Request for bad weather override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_WMS_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_WMS_OVERRIDE", 15 );

  /**
   * Request to cancel bad weather override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_WMS_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_WMS_RESTORE", 16 );

  /**
   * Request for EPT problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_EPT_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_EPT_OVERRIDE", 17 );

  /**
   * Request to cancel EPT problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_EPT_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_EPT_RESTORE", 18 );

  /**
   * Request for SPT problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_SPT_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_SPT_OVERRIDE", 19 );

  /**
   * Request to cancel SPT problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_SPT_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_SPT_RESTORE", 20 );

  /**
   * Request for node problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_NODE_OVERRIDE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_NODE_OVERRIDE", 21 );

  /**
   * Request to cancel node problem override
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_NODE_RESTORE =
    new TTL_SystemRequest( "E_MCP_SYSREQ_NODE_RESTORE", 22 );

  /**
   * Request to cancel a start observation
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_CANCEL_START_OBS =
    new TTL_SystemRequest( "E_MCP_SYSREQ_CANCEL_START_OBS", 23 );

  /**
   * Request to cancel a stop observation
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_CANCEL_STOP_OBS =
    new TTL_SystemRequest( "E_MCP_SYSREQ_CANCEL_STOP_OBS", 24 );

  /**
   * Request to re read sections of config file
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_REREAD_OBS =
    new TTL_SystemRequest( "E_MCP_SYSREQ_REREAD_OBS", 25 );

  /**
   * Request for software only shutdown
   */
  public static TTL_SystemRequest E_MCP_SYSREQ_SOFT_SHUTDOWN =
    new TTL_SystemRequest( "E_MCP_SYSREQ_SOFT_SHUTDOWN", 26 );


  /**
   * Array allowing serialization.
   */
  protected static final TTL_SystemRequest[] array =
  {
    E_MCP_SYSREQ_NULL,
    E_MCP_SYSREQ_SAFE_STATE,
    E_MCP_SYSREQ_ACTIVATE,
    E_MCP_SYSREQ_FULL_SHUTDOWN,
    E_MCP_SYSREQ_FULL_STARTUP,
    E_MCP_SYSREQ_PARTIAL_SHUTDOWN,
    E_MCP_SYSREQ_PARTIAL_STARTUP,
    E_MCP_SYSREQ_START_OBSERVATION,
    E_MCP_SYSREQ_STOP_OBSERVATION,
    E_MCP_SYSREQ_IMMED_SHUTDOWN,
    E_MCP_SYSREQ_FULL_RESTART,
    E_MCP_SYSREQ_FAIL_OVERRIDE,
    E_MCP_SYSREQ_FAIL_RESTORE,
    E_MCP_SYSREQ_TIME_OVERRIDE,
    E_MCP_SYSREQ_TIME_RESTORE,
    E_MCP_SYSREQ_WMS_OVERRIDE,
    E_MCP_SYSREQ_WMS_RESTORE,
    E_MCP_SYSREQ_EPT_OVERRIDE,
    E_MCP_SYSREQ_EPT_RESTORE,
    E_MCP_SYSREQ_SPT_OVERRIDE,
    E_MCP_SYSREQ_SPT_RESTORE,
    E_MCP_SYSREQ_NODE_OVERRIDE,
    E_MCP_SYSREQ_NODE_RESTORE,
    E_MCP_SYSREQ_CANCEL_START_OBS,
    E_MCP_SYSREQ_CANCEL_STOP_OBS,
    E_MCP_SYSREQ_REREAD_OBS,
    E_MCP_SYSREQ_SOFT_SHUTDOWN
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
   * Return an object reference of the TTL_SystemRequest with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_SystemRequest
   * @return a reference to the TTL_SystemRequest specified by the argument
   */
  public static TTL_SystemRequest getReference( String s )
  {
    return( (TTL_SystemRequest)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_SystemRequest with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_SystemRequest
   * @return a reference to the TTL_SystemRequest specified by the argument
   */
  public static TTL_SystemRequest getReference( int i )
  {
    return( (TTL_SystemRequest)( intHash.get( new Integer( i ) ) ) );
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
  protected TTL_SystemRequest( String s )
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
  protected TTL_SystemRequest( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_SystemRequest.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_SystemRequest.
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
 *    $Date: 2013-07-04 10:57:09 $
 * $RCSfile: TTL_SystemRequest.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SystemRequest.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 *
 */
