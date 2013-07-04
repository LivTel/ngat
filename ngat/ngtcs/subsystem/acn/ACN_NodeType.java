package ngat.ngtcs.subsystem.acn;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type ACN_NodeType.  These are:
 * <ul>
 * <li>E_ACN_NODE_AZN</li>
 * <li>E_ACN_NODE_ELN</li>
 * <li>E_ACN_NODE_CSN</li>
 * <li>E_ACN_NODE_AMN</li>
 * <li>E_ACN_NODE_PMN</li>
 * <li>E_ACN_NODE_EOL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class ACN_NodeType implements java.io.Serializable
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
    new String( "$Id: ACN_NodeType.java,v 1.2 2013-07-04 12:53:32 cjm Exp $" );

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
   * Azimuth
   */
  public static ACN_NodeType E_ACN_NODE_AZN =
    new ACN_NodeType( "E_ACN_NODE_AZN", 0 );

  /**
   * Altitude
   */
  public static ACN_NodeType E_ACN_NODE_ELN =
    new ACN_NodeType( "E_ACN_NODE_ELN", 1 );

  /**
   * Cassegrain
   */
  public static ACN_NodeType E_ACN_NODE_CSN =
    new ACN_NodeType( "E_ACN_NODE_CSN", 2 );

  /**
   * Auxiliary Mechanisms
   */
  public static ACN_NodeType E_ACN_NODE_AMN =
    new ACN_NodeType( "E_ACN_NODE_AMN", 3 );

  /**
   * Primary Mirror Support
   */
  public static ACN_NodeType E_ACN_NODE_PMN =
    new ACN_NodeType( "E_ACN_NODE_PMN", 4 );

  /**
   * 
   */
  public static ACN_NodeType E_ACN_NODE_EOL =
    new ACN_NodeType( "E_ACN_NODE_EOL", 5 );


  /**
   * Array allowing serialization.
   */
  protected static final ACN_NodeType[] array =
  {
    E_ACN_NODE_AZN,
    E_ACN_NODE_ELN,
    E_ACN_NODE_CSN,
    E_ACN_NODE_AMN,
    E_ACN_NODE_PMN,
    E_ACN_NODE_EOL
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
   * Return an object reference of the ACN_NodeType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the ACN_NodeType
   * @return a reference to the ACN_NodeType specified by the argument
   */
  public static ACN_NodeType getReference( String s )
  {
    return( (ACN_NodeType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the ACN_NodeType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the ACN_NodeType
   * @return a reference to the ACN_NodeType specified by the argument
   */
  public static ACN_NodeType getReference( int i )
  {
    return( (ACN_NodeType)( intHash.get( new Integer( i ) ) ) );
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
  protected ACN_NodeType( String s )
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
  protected ACN_NodeType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this ACN_NodeType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this ACN_NodeType.
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
 *    $Date: 2013-07-04 12:53:32 $
 * $RCSfile: ACN_NodeType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/acn/ACN_NodeType.java,v $
 *      $Id: ACN_NodeType.java,v 1.2 2013-07-04 12:53:32 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:30  je
 *     Initial revision
 *
 *
 */
