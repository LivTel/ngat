package ngat.ngtcs.subsystem.ags;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGS_Command.  These are:
 * <ul>
 * <li>E_AGS_AUTOGUIDE_ON_BRIGHTEST</li>
 * <li>E_AGS_AUTOGUIDE_ON_RANK</li>
 * <li>E_AGS_AUTOGUIDE_ON_RANGE</li>
 * <li>E_AGS_AUTOGUIDE_ON_PIXEL</li>
 * <li>E_AGS_AUTOGUIDE_OFF</li>
 * <li>E_AGS_CONF_EXP_TIME</li>
 * <li>E_AGS_CONF_FRAME_RATE</li>
 * <li>E_AGS_CONF_FRAME_AVERAGE</li>
 * <li>E_AGS_CONF_CALIB</li>
 * <li>E_AGS_START_SESSION</li>
 * <li>E_AGS_END_SESSION</li>
 * <li>E_AGS_LOGGING</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGS_Command implements ngat.net.cil.CIL_ServiceClass
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
   * Guide on brightest object.
   */
  public static AGS_Command E_AGS_AUTOGUIDE_ON_BRIGHTEST =
    new AGS_Command( "E_AGS_AUTOGUIDE_ON_BRIGHTEST", 1 );

  /**
   * Guide on n'th brightest object.
   */
  public static AGS_Command E_AGS_AUTOGUIDE_ON_RANK =
    new AGS_Command( "E_AGS_AUTOGUIDE_ON_RANK", 2 );

  /**
   * Guide on object in range of star mags.
   */
  public static AGS_Command E_AGS_AUTOGUIDE_ON_RANGE =
    new AGS_Command( "E_AGS_AUTOGUIDE_ON_RANGE", 3 );

  /**
   * Guide on object nearest to pixel.
   */
  public static AGS_Command E_AGS_AUTOGUIDE_ON_PIXEL =
    new AGS_Command( "E_AGS_AUTOGUIDE_ON_PIXEL", 4 );

  /**
   * Stop guide loop.
   */
  public static AGS_Command E_AGS_AUTOGUIDE_OFF =
    new AGS_Command( "E_AGS_AUTOGUIDE_OFF", 5 );

  /**
   * Configure exposure (integration) time.
   */
  public static AGS_Command E_AGS_CONF_EXP_TIME =
    new AGS_Command( "E_AGS_CONF_EXP_TIME", 6 );

  /**
   * Configure frame rate.
   */
  public static AGS_Command E_AGS_CONF_FRAME_RATE =
    new AGS_Command( "E_AGS_CONF_FRAME_RATE", 7 );

  /**
   * Configure frame average.
   */
  public static AGS_Command E_AGS_CONF_FRAME_AVERAGE =
    new AGS_Command( "E_AGS_CONF_FRAME_AVERAGE", 8 );

  /**
   * Configure grey level >star mag calcs.
   */
  public static AGS_Command E_AGS_CONF_CALIB =
    new AGS_Command( "E_AGS_CONF_CALIB", 9 );

  /**
   * Start session (turn on cooling).
   */
  public static AGS_Command E_AGS_START_SESSION =
    new AGS_Command( "E_AGS_START_SESSION", 10 );

  /**
   * End session (turn off cooling).
   */
  public static AGS_Command E_AGS_END_SESSION =
    new AGS_Command( "E_AGS_END_SESSION", 11 );

  /**
   * Turn logging on or off for Agg.
   */
  public static AGS_Command E_AGS_LOGGING =
    new AGS_Command( "E_AGS_LOGGING", 12 );

  /**
   * Array allowing serialization.
   */
  protected static final AGS_Command[] array =
  {
    E_AGS_AUTOGUIDE_ON_BRIGHTEST,
    E_AGS_AUTOGUIDE_ON_RANK,
    E_AGS_AUTOGUIDE_ON_RANGE,
    E_AGS_AUTOGUIDE_ON_PIXEL,
    E_AGS_AUTOGUIDE_OFF,
    E_AGS_CONF_EXP_TIME,
    E_AGS_CONF_FRAME_RATE,
    E_AGS_CONF_FRAME_AVERAGE,
    E_AGS_CONF_CALIB,
    E_AGS_START_SESSION,
    E_AGS_END_SESSION,
    E_AGS_LOGGING
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
   * Return an object reference of the AGS_Command with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGS_Command
   * @return a reference to the AGS_Command specified by the argument
   */
  public static AGS_Command getReference( String s )
  {
    return( (AGS_Command)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGS_Command with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGS_Command
   * @return a reference to the AGS_Command specified by the argument
   */
  public static AGS_Command getReference( int i )
  {
    return( (AGS_Command)( intHash.get( new Integer( i ) ) ) );
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
  protected AGS_Command( String s )
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
  protected AGS_Command( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGS_Command.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGS_Command.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGS
   */
  public TTL_Package getTTL_Package()
  {
    return( TTL_Package.AGS );
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
 *    $Date: 2013-07-04 12:55:56 $
 * $RCSfile: AGS_Command.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS_Command.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
