package ngat.ngtcs.net.cil;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_CIL_ServiceClass.  These are:
 * <ul>
 * <li>E_MCP_SHUTDOWN</li>
 * <li>E_MCP_SAFESTATE</li>
 * <li>E_MCP_ACTIVATE</li>
 * <li>E_MCP_SET_1</li>
 * <li>E_MCP_SET_N</li>
 * <li>E_MCP_GET_1</li>
 * <li>E_MCP_GET_N</li>
 * <li>E_SYS_CMD</li>
 * </ul>
 * The objects in this class have a 32 bit int to identify them to the
 * communications broker, and a human-readable name in String format.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_CIL_ServiceClass extends ngat.net.cil.CIL_ServiceClass
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
    new String( "$Id: TTL_CIL_ServiceClass.java,v 1.1 2003-09-19 16:00:50 je Exp $" );

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
   * Heartbeat message.
   */
  public static TTL_CIL_ServiceClass E_MCP_HEARTBEAT =
    new TTL_CIL_ServiceClass( "E_MCP_HEARTBEAT", 255 );

  /**
   * Shutdown message.
   */
  public static TTL_CIL_ServiceClass E_MCP_SHUTDOWN =
    new TTL_CIL_ServiceClass( "E_MCP_SHUTDOWN", 254 );

  /**
   * Safe-state message.
   */
  public static TTL_CIL_ServiceClass E_MCP_SAFESTATE =
    new TTL_CIL_ServiceClass( "E_MCP_SAFESTATE", 253 );

  /**
   * Activate message from safe-state.
   */
  public static TTL_CIL_ServiceClass E_MCP_ACTIVATE =
    new TTL_CIL_ServiceClass( "E_MCP_ACTIVATE", 252 );

  /**
   * Single set command.
   */
  public static TTL_CIL_ServiceClass E_MCP_SET_1 =
    new TTL_CIL_ServiceClass( "E_MCP_SET_1", 251 );

  /**
   * Multiple set command.
   */
  public static TTL_CIL_ServiceClass E_MCP_SET_N =
    new TTL_CIL_ServiceClass( "E_MCP_SET_N", 250 );

  /**
   * Single get command.
   */
  public static TTL_CIL_ServiceClass E_MCP_GET_1 =
    new TTL_CIL_ServiceClass( " E_MCP_GET_1", 249 );

  /**
   * Multiple get command.
   */
  public static TTL_CIL_ServiceClass E_MCP_GET_N =
    new TTL_CIL_ServiceClass( "E_MCP_GET_N", 248 );

  /**
   * Basic system command.
   */
  public static TTL_CIL_ServiceClass E_SYS_CMD =
    new TTL_CIL_ServiceClass( "E_SYS_CMD", 0 );

  /**
   * Array to allow serialization.
   */
  protected final static TTL_CIL_ServiceClass[] array =
  {
    E_MCP_SHUTDOWN,
    E_MCP_SAFESTATE,
    E_MCP_ACTIVATE,
    E_MCP_SET_1,
    E_MCP_SET_N,
    E_MCP_GET_1,
    E_MCP_GET_N,
    E_SYS_CMD
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
   * Return an object reference of the TTL_CIL_ServiceClass with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_CIL_ServiceClass
   * @return a reference to the TTL_CIL_ServiceClass specified by the argument
   */
  public static TTL_CIL_ServiceClass getReference( String s )
  {
    return( (TTL_CIL_ServiceClass)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_CIL_ServiceClass with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_CIL_ServiceClass
   * @return a reference to the TTL_CIL_ServiceClass specified by the argument
   */
  public static TTL_CIL_ServiceClass getReference( int i )
  {
    return( (TTL_CIL_ServiceClass)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Implemented to provide inheritability.
   */
  protected TTL_CIL_ServiceClass()
  {
  }


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
  protected TTL_CIL_ServiceClass( String s )
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
  protected TTL_CIL_ServiceClass( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_CIL_ServiceClass.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_CIL_ServiceClass.
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
 *    $Date: 2003-09-19 16:00:50 $
 * $RCSfile: TTL_CIL_ServiceClass.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/net/cil/TTL_CIL_ServiceClass.java,v $
 *      $Id: TTL_CIL_ServiceClass.java,v 1.1 2003-09-19 16:00:50 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
