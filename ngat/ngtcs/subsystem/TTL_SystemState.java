package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_SystemState.
 * <p>
 * List of type-safe enumerations of the Status Database data types
 * <p>
 * The available states are:
 * <ul>
 * <li>SYS_INVALID_STATE</li>
 * <li>SYS_OKAY_STATE</li>
 * <li>SYS_INIT_STATE</li>
 * <li>SYS_STANDBY_STATE</li>
 * <li>SYS_WARN_STATE</li>
 * <li>SYS_FAILED_STATE</li>
 * <li>SYS_SAFE_STATE</li>
 * <li>SYS_OFF_STATE</li>
 * <li>SYS_TIMEOUT_STATE</li>
 * <li>SYS_SUSPEND_STATE</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class TTL_SystemState implements java.io.Serializable
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
    new String( "$Id: TTL_SystemState.java,v 1.2 2013-07-04 10:57:11 cjm Exp $" );

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
   * No state has been set (uninitialised).
   */
  public static TTL_SystemState SYS_INVALID_STATE =
    new TTL_SystemState( "SYS_INVALID_STATE", 0 );

  /**
   * The process is operating normally.
   */
  public static TTL_SystemState SYS_OKAY_STATE =
    new TTL_SystemState( "SYS_OKAY_STATE", 1 );

  /**
   * The process is performing its initial start up
   * or awaiting initialisation commands to take the 
   * process to SYS_OKAY_STATE (if appropriate).     
   */
  public static TTL_SystemState SYS_INIT_STATE =
    new TTL_SystemState( "SYS_INIT_STATE", 2 );

  /**
   * This state is used for a process or the system
   * when software is running, but not yet ready for 
   * full operation. To move from this state to OKAY 
   * will require some action to be taken. For       
   * example, axis software will attain this state   
   * until the axis is homed.                        
   */
  public static TTL_SystemState SYS_STANDBY_STATE =
    new TTL_SystemState( "SYS_STANDBY_STATE", 3 );

  /**
   * A problem has occurred, but no automatic        
   * intervention is required from the MCP. The      
   * telescope may still be used in this state, but  
   * there is a possibility that operational         
   * performance may be degraded.
   */
  public static TTL_SystemState SYS_WARN_STATE =
    new TTL_SystemState( "SYS_WARN_STATE", 4 );

  /**
   * A problem has occurred with the process that
   * requires intervention by the MCP.               
   */
  public static TTL_SystemState SYS_FAILED_STATE =
    new TTL_SystemState( "SYS_FAILED_STATE", 5 );

  /**
   * The process has ceased normal operation (maybe
   * only temporarily) and is either about to        
   * terminate itself or be able to be terminated by 
   * the MCP/system without risk of hardware damage  
   * or serious data loss.                           
   */
  public static TTL_SystemState SYS_SAFE_STATE =
    new TTL_SystemState( "SYS_SAFE_STATE", 6 );

  /**
   * The process is not running. THIS IS AN INFERRED
   * STATE which is set by the MCP only.             
   */
  public static TTL_SystemState SYS_OFF_STATE =
    new TTL_SystemState( "SYS_OFF_STATE", 7 );

  /**
   * The process is not responding to the MCP heart
   * beat messages. THIS IS AN INFERRED STATE which  
   * is set by the MCP only.                         
   */
  public static TTL_SystemState SYS_TIMEOUT_STATE =
    new TTL_SystemState( "SYS_TIMEOUT_STATE", 8 );

  /**
   * system when it is awaiting the clearing of an   
   * external condition which prevents normal        
   * operation. The process is performing monitoring 
   * and reporting duties, but it will not accept    
   * operating instructions (e.g. motion commands).  
   */
  public static TTL_SystemState SYS_SUSPEND_STATE =
    new TTL_SystemState( "SYS_SUSPEND_STATE", 9 );


  /**
   * Array allowing serialization.
   */
  protected static final TTL_SystemState[] array =
  {
    SYS_OKAY_STATE,
    SYS_INIT_STATE,
    SYS_STANDBY_STATE,
    SYS_WARN_STATE,
    SYS_FAILED_STATE,
    SYS_SAFE_STATE,
    SYS_OFF_STATE,
    SYS_TIMEOUT_STATE,
    SYS_SUSPEND_STATE
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
   * Return an object reference of the TTL_SystemState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_SystemState
   * @return a reference to the TTL_SystemState specified by the argument
   */
  public static TTL_SystemState getReference( String s )
  {
    return( (TTL_SystemState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_SystemState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_SystemState
   * @return a reference to the TTL_SystemState specified by the argument
   */
  public static TTL_SystemState getReference( int i )
  {
    return( (TTL_SystemState)( intHash.get( new Integer( i ) ) ) );
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
  protected TTL_SystemState( String s )
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
  protected TTL_SystemState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_SystemState.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_SystemState.
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
 *    $Date: 2013-07-04 10:57:11 $
 * $RCSfile: TTL_SystemState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_SystemState.java,v $
 *      $Id: TTL_SystemState.java,v 1.2 2013-07-04 10:57:11 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 *
 */
