package ngat.ngtcs.common;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type RotatorMode.
 * <p>
 * The operational modes available are:
 * <ul>
 * <li> RotatorMode.SKY_POSITION
 * <li> RotatorMode.FLOATING_SKY_POSITION
 * <li> RotatorMode.MOUNT_POSITION
 * <li> RotatorMode.VERTICAL_POSITION
 * <li> RotatorMode.FLOATING_VERTICAL_POSITION
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class RotatorMode implements java.io.Serializable
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


  public static RotatorMode SKY_POSITION = 
    new RotatorMode( "SKY_POSITION", 4001 );

  public static RotatorMode MOUNT_POSITION = 
    new RotatorMode( "MOUNT_POSITION", 4002 );

  public static RotatorMode FLOATING_SKY_POSITION = 
    new RotatorMode( "FLOATING_SKY_POSITION", 4003 );

  public static RotatorMode VERTICAL_POSITION = 
    new RotatorMode( "VERTICAL_POSITION", 4004 );

  public static RotatorMode FLOATING_VERTICAL_POSITION = 
    new RotatorMode( "FLOATING_VERTICAL_POSITION", 4005 );

  /**
   * Array to allow serialization.
   */
  protected final static RotatorMode[] array =
  {
    SKY_POSITION,
    MOUNT_POSITION,
    FLOATING_SKY_POSITION,
    VERTICAL_POSITION,
    FLOATING_VERTICAL_POSITION
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
   * The tracking angle used in the relevent mode of operation, in radians.
   */
  protected double trackingAngle = 0.0;

  /**
   * The Equinox of the tracking frame used to track the rotator.
   */
  protected Equinox trackingEquinox = Equinox.J2000;

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
   * Return an object reference of the RotatorMode with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the RotatorMode
   * @return a reference to the RotatorMode specified by the argument
   */
  public static RotatorMode getReference( String s )
  {
    return( (RotatorMode)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the RotatorMode with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the RotatorMode
   * @return a reference to the RotatorMode specified by the argument
   */
  public static RotatorMode getReference( int i )
  {
    return( (RotatorMode)( intHash.get( new Integer( i ) ) ) );
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
  protected RotatorMode( String s )
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
  protected RotatorMode( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this RotatorMode.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this RotatorMode.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Sets the tracking angle used in this operational mode.  The specified 
   * angle should be in radians, in the range: 0 - 2*PI.
   * @param newTrackingAngle
   * @see #trackingAngle
   */
  public void setTrackingAngle( double newTrackingAngle )
  {
    trackingAngle = newTrackingAngle;
  }


  /**
   * Return the current tracking angle being used in this operational mode.
   * @return trackingAngle
   * @see #trackingAngle
   */
  public double getTrackingAngle()
  {
    return trackingAngle;
  }


  /**
   * Set the tracking frame to be used while tracking the rotator.
   * @param newTrackingEquinox
   */
  public void setTrackingFrame( Equinox newTrackingEquinox )
  {
    trackingEquinox = newTrackingEquinox;
  }


  /**
   * Get the tracking frame used to track the rotator.
   * @return trackingEquinox
   */
  public Equinox getTrackingFrame()
  {
    return trackingEquinox;
  }


  /**
   * Reset the tracking angle and tracking equinox to the defaults
   */
  public void resetValues()
  {
    trackingAngle = 0.0;
    trackingEquinox = Equinox.J2000;
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
 *    $Date: 2013-07-04 10:38:36 $
 * $RCSfile: RotatorMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/RotatorMode.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:00:03  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *
 */
