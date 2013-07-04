package ngat.ngtcs.common;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TimescaleType.
 * <p>
 * The list of Timescales is:
 * <ul>
 * <li>UT</li>
 * <li>UT1 ( = UT )</li>
 * <li>UTC</li>
 * <li>TAI</li>
 * <li>TDB</li>
 * <li>TDT</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class TimescaleType implements java.io.Serializable
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
    new String( "$Id: TimescaleType.java,v 1.3 2013-07-04 10:47:25 cjm Exp $" );

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
   * Type-safe Enumeration of the Universal Time timescale (UT).
   */
  public static final TimescaleType UT  = new TimescaleType( "UT", 3001 );

  /**
   * Type-safe Enumeration of the Universal Time timescale (UT1 = UT).
   */
  public static final TimescaleType UT1 = UT;

  /**
   * Type-safe Enumeration of the Coordinated Universal Time timescale
   * (UTC).
   */
  public static final TimescaleType UTC = new TimescaleType( "UTC", 3002 );

  /**
   * Type-safe Enumeration of the International Atomic Time (TAI).
   */
  public static final TimescaleType TAI = new TimescaleType( "TAI", 3003 );

  /**
   * Type-safe Enumeration of the Barycentric Dynamical timescale (TDB).
   */
  public static final TimescaleType TDB = new TimescaleType( "TDB", 3004 );

  /**
   * Type-safe Enumeration of the Terrestrial dynamical time (TDT).
   */
  public static final TimescaleType TDT = new TimescaleType( "TDT", 3005 );


  /**
   * Array to allow serialization.
   */
  protected final static TimescaleType[] array =
  {
    UT,
    UT1,
    UTC,
    TAI,
    TDB,
    TDT
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
   * Return an object reference of the TimescaleType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TimescaleType
   * @return a reference to the TimescaleType specified by the argument
   */
  public static TimescaleType getReference( String s )
  {
    return( (TimescaleType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TimescaleType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TimescaleType
   * @return a reference to the TimescaleType specified by the argument
   */
  public static TimescaleType getReference( int i )
  {
    return( (TimescaleType)( intHash.get( new Integer( i ) ) ) );
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
  protected TimescaleType( String s )
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
  protected TimescaleType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TimescaleType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TimescaleType.
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
 *    $Date: 2013-07-04 10:47:25 $
 * $RCSfile: TimescaleType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TimescaleType.java,v $
 *      $Id: TimescaleType.java,v 1.3 2013-07-04 10:47:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:00:03  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *
 */
