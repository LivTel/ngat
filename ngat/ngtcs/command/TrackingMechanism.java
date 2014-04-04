package ngat.ngtcs.command;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type TrackingMechanism.
 * <p>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TrackingMechanism implements java.io.Serializable
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
   * All of the <code><b>TrackingMechanism</b></code>s.
   */
  public static final TrackingMechanism ALL =
    new TrackingMechanism( "ALL" );

  /**
   * The ALTITUDE TrackingMechanism.
   */
  public static final TrackingMechanism ALTITUDE =
    new TrackingMechanism( "ALTITUDE" );

  /**
   * The AZIMUTH TrackingMechanism.
   */
  public static final TrackingMechanism AZIMUTH =
    new TrackingMechanism( "AZIMUTH" );

  /**
   * The ROTATOR TrackingMechanism.
   */
  public static final TrackingMechanism ROTATOR =
    new TrackingMechanism( "ROTATOR" );

  /**
   * The FOCUS TrackingMechanism.
   */
  public static final TrackingMechanism FOCUS =
    new TrackingMechanism( "FOCUS" );


  /**
   * Array to allow serialization.
   */
  protected final static TrackingMechanism[] array =
  {
    ALL,
    ALTITUDE,
    AZIMUTH,
    ROTATOR,
    FOCUS
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
   * Return an object reference of the TrackingMechanism with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the TrackingMechanism
   * @return a reference to the TrackingMechanism specified by the argument
   */
  public static TrackingMechanism getReference( String s )
  {
    return( (TrackingMechanism)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the TrackingMechanism with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the TrackingMechanism
   * @return a reference to the TrackingMechanism specified by the argument
   */
  public static TrackingMechanism getReference( int i )
  {
    return( (TrackingMechanism)( intHash.get( new Integer( i ) ) ) );
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
  protected TrackingMechanism( String s )
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
  protected TrackingMechanism( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this TrackingMechanism.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this TrackingMechanism.
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
 *    $Date: 2013-07-04 10:08:32 $
 * $RCSfile: TrackingMechanism.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/TrackingMechanism.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 *
 */
