package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_GenericData.
 * <p>
 * List of type-safe enumerations of the Status Database data types
 * <p>
 * The available states are:
 * <ul>
 * <li>D_MCP_PROC_STATE</li>
 * <li>D_MCP_AUTH_STATE</li>
 * <li>D_MCP_SYS_REQUEST</li>
 * <li>D_MCP_APP_VERSION</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TTL_GenericData
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
   *
   */
  public static final TTL_GenericData D_MCP_PROC_STATE = new TTL_GenericData
    ( "D_MCP_PROC_STATE", 1 );

  /**
   *
   */
  public static final TTL_GenericData D_MCP_AUTH_STATE = new TTL_GenericData
    ( "D_MCP_AUTH_STATE", 2 );

  /**
   *
   */
  public static final TTL_GenericData D_MCP_SYS_REQUEST = new TTL_GenericData
    ( "D_MCP_SYS_REQUEST", 3 );

  /**
   *
   */
  public static final TTL_GenericData D_MCP_APP_VERSION = new TTL_GenericData
    ( "D_MCP_APP_VERSION", 4 );

  /**
   * Array to allow serialization.
   */
  protected static TTL_GenericData[] array =
  {
    D_MCP_PROC_STATE,
    D_MCP_AUTH_STATE,
    D_MCP_SYS_REQUEST,
    D_MCP_APP_VERSION
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
   * Return an object reference of the TTL_GenericData with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_GenericData
   * @return a reference to the TTL_GenericData specified by the argument
   */
  public static TTL_GenericData getReference( String s )
  {
    return( (TTL_GenericData)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_GenericData with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_GenericData
   * @return a reference to the TTL_GenericData specified by the argument
   */
  public static TTL_GenericData getReference( int i )
  {
    return( (TTL_GenericData)( intHash.get( new Integer( i ) ) ) );
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
  protected TTL_GenericData( String s )
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
  protected TTL_GenericData( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_GenericData.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_GenericData.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.SYS
   */
  public ngat.ngtcs.subsystem.TTL_Package getTTL_Package()
  {
    return( ngat.ngtcs.subsystem.TTL_Package.SYS );
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
 *    $Date: 2013-07-04 10:55:46 $
 * $RCSfile: TTL_GenericData.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_GenericData.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 *
 */
