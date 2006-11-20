package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_CIL_GenericService.  These are:
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
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class TTL_CIL_GenericService
  implements ngat.net.cil.CIL_ServiceClass, ngat.ngtcs.subsystem.TTL_DataType
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
    new String( "$Id: TTL_CIL_GenericService.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

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
   * Heartbeat message.
   */
  public final static TTL_CIL_GenericService E_MCP_HEARTBEAT =
    new TTL_CIL_GenericService( "E_MCP_HEARTBEAT", 255 );

  /**
   * Shutdown message.
   */
  public final static TTL_CIL_GenericService E_MCP_SHUTDOWN =
    new TTL_CIL_GenericService( "E_MCP_SHUTDOWN", 254 );

  /**
   * Safe-state message.
   */
  public final static TTL_CIL_GenericService E_MCP_SAFESTATE =
    new TTL_CIL_GenericService( "E_MCP_SAFESTATE", 253 );

  /**
   * Activate message from safe-state.
   */
  public final static TTL_CIL_GenericService E_MCP_ACTIVATE =
    new TTL_CIL_GenericService( "E_MCP_ACTIVATE", 252 );

  /**
   * Single set command.
   */
  public final static TTL_CIL_GenericService E_MCP_SET_1 =
    new TTL_CIL_GenericService( "E_MCP_SET_1", 251 );

  /**
   * Multiple set command.
   */
  public final static TTL_CIL_GenericService E_MCP_SET_N =
    new TTL_CIL_GenericService( "E_MCP_SET_N", 250 );

  /**
   * Single get command.
   */
  public final static TTL_CIL_GenericService E_MCP_GET_1 =
    new TTL_CIL_GenericService( " E_MCP_GET_1", 249 );

  /**
   * Multiple get command.
   */
  public final static TTL_CIL_GenericService E_MCP_GET_N =
    new TTL_CIL_GenericService( "E_MCP_GET_N", 248 );

  /**
   * Basic system command.
   */
  public final static TTL_CIL_GenericService E_SYS_CMD =
    new TTL_CIL_GenericService( "E_SYS_CMD", 0 );

  /**
   * Array to allow serialization.
   */
  protected final static TTL_CIL_GenericService[] array =
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
   * TTL Package for which these Generic Services apply - set to
   * <code>TTL_Package.GENERIC</code>
   */
  private transient TTL_Package ttlPackage;

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
   * Return an object reference of the TTL_CIL_GenericService with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_CIL_GenericService
   * @return a reference to the TTL_CIL_GenericService specified by the argument
   */
  public static TTL_CIL_GenericService getReference( String s )
  {
    return( (TTL_CIL_GenericService)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_CIL_GenericService with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_CIL_GenericService
   * @return a reference to the TTL_CIL_GenericService specified by the argument
   */
  public static TTL_CIL_GenericService getReference( int i )
  {
    return( (TTL_CIL_GenericService)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected TTL_CIL_GenericService( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    ttlPackage = TTL_Package.NO_SYS;
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_CIL_GenericService.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_CIL_GenericService.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package for this Generic Service.
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
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_CIL_GenericService.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_CIL_GenericService.java,v $
 *      $Id: TTL_CIL_GenericService.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 *
 */
