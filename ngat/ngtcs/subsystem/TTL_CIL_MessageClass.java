package ngat.ngtcs.subsystem;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TTL_CIL_MessageClass.  These are:
 * <ul>
 * <li>E_CIL_CMD_CLASS</li>
 * <li>E_CIL_RSP_CLASS</li>
 * <li>E_CIL_ACK_CLASS</li>
 * <li>E_CIL_ACT_CLASS</li>
 * <li>E_CIL_COM_CLASS</li>
 * <li>E_CIL_ERR_CLASS</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class TTL_CIL_MessageClass
  implements ngat.net.cil.CIL_MessageClass
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public final static String rcsid =
    new String( "$Id: TTL_CIL_MessageClass.java,v 1.1 2006-11-20 14:42:25 cjm Exp $" );

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
   * Command message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_CMD_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_CMD_CLASS", 1 );

  /**
   * Response message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_RSP_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_RSP_CLASS", 2 );

  /**
   * Acknowledgment message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_ACK_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_ACK_CLASS", 3 );

  /**
   * Action message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_ACT_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_ACT_CLASS", 4 );

  /**
   * Completion message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_COM_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_COM_CLASS", 5 );

  /**
   * Error message class.
   */
  public final static TTL_CIL_MessageClass E_CIL_ERR_CLASS =
    new TTL_CIL_MessageClass( "E_CIL_ERR_CLASS", 6 );

  /**
   * Array to allow serialization.
   */
  protected final static TTL_CIL_MessageClass[] array =
  {
    E_CIL_CMD_CLASS,
    E_CIL_RSP_CLASS,
    E_CIL_ACK_CLASS,
    E_CIL_ACT_CLASS,
    E_CIL_COM_CLASS,
    E_CIL_ERR_CLASS
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
   * Return an object reference of the TTL_CIL_MessageClass with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TTL_CIL_MessageClass
   * @return a reference to the TTL_CIL_MessageClass specified by the argument
   */
  public final static TTL_CIL_MessageClass getReference( String s )
  {
    return( (TTL_CIL_MessageClass)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TTL_CIL_MessageClass with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TTL_CIL_MessageClass
   * @return a reference to the TTL_CIL_MessageClass specified by the argument
   */
  public final static TTL_CIL_MessageClass getReference( int i )
  {
    return( (TTL_CIL_MessageClass)( intHash.get( new Integer( i ) ) ) );
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
  protected TTL_CIL_MessageClass( String s )
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
  protected TTL_CIL_MessageClass( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TTL_CIL_MessageClass.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TTL_CIL_MessageClass.
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
 *    $Date: 2006-11-20 14:42:25 $
 * $RCSfile: TTL_CIL_MessageClass.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_CIL_MessageClass.java,v $
 *      $Id: TTL_CIL_MessageClass.java,v 1.1 2006-11-20 14:42:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:00:50  je
 *     Initial revision
 *
 *
 */
