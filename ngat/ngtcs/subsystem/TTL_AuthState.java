package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_AuthState.
 * <p>
 * Class defining allowable references of TTL_AuthState.  These are:
 * <ul>
 * <li>E_MCP_AUTH_NONE</li>
 * <li>E_MCP_AUTH_REQUEST</li>
 * <li>E_MCP_AUTH_GRANTED</li>
 * <li>E_MCP_AUTH_REFUSED</li>
 * <li>E_MCP_AUTH_SYSREQ_ONLY</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class TTL_AuthState implements java.io.Serializable
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
    new String( "$Id: TTL_AuthState.java,v 1.2 2013-07-04 10:55:01 cjm Exp $" );

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
   * Authorisation is not requested
   */
  public static TTL_AuthState E_MCP_AUTH_NONE =
    new TTL_AuthState( "E_MCP_AUTH_NONE", 1 );

  /**
   * Application requests authorisation
   */
  public static TTL_AuthState E_MCP_AUTH_REQUEST =
    new TTL_AuthState( "E_MCP_AUTH_REQUEST", 2 );

  /**
   * Application granted authorisation
   */
  public static TTL_AuthState E_MCP_AUTH_GRANTED =
    new TTL_AuthState( "E_MCP_AUTH_GRANTED", 3 );

  /**
   * Application refused authorisation
   */
  public static TTL_AuthState E_MCP_AUTH_REFUSED =
    new TTL_AuthState( "E_MCP_AUTH_REFUSED", 4 );

  /**
   * Application can make system requests only
   */
  public static TTL_AuthState E_MCP_AUTH_SYSREQ_ONLY =
    new TTL_AuthState( "E_MCP_AUTH_SYSREQ_ONLY", 5 );


  /**
   * Array allowing serialization.
   */
  protected static final TTL_AuthState[] array =
  {
    E_MCP_AUTH_NONE,
    E_MCP_AUTH_REQUEST,
    E_MCP_AUTH_GRANTED,
    E_MCP_AUTH_REFUSED,
    E_MCP_AUTH_SYSREQ_ONLY
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
   * Return an object reference of the TTL_AuthState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_AuthState
   * @return a reference to the TTL_AuthState specified by the argument
   */
  public static TTL_AuthState getReference( String s )
  {
    return( (TTL_AuthState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_AuthState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_AuthState
   * @return a reference to the TTL_AuthState specified by the argument
   */
  public static TTL_AuthState getReference( int i )
  {
    return( (TTL_AuthState)( intHash.get( new Integer( i ) ) ) );
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
  protected TTL_AuthState( String s )
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
  protected TTL_AuthState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_AuthState.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_AuthState.
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
 *    $Date: 2013-07-04 10:55:01 $
 * $RCSfile: TTL_AuthState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_AuthState.java,v $
 *      $Id: TTL_AuthState.java,v 1.2 2013-07-04 10:55:01 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:01:09  je
 *     Initial revision
 *
 *
 */
