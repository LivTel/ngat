package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGG_Keyword.
 * <p>
 * The allowable references of AGG_Keyword are:
 * <ul>
 * <li>E_AGG_ON</li>
 * <li>E_AGG_OFF</li>
 * <li>E_AGG_BRIGHTEST</li>
 * <li>E_AGG_RANGE</li>
 * <li>E_AGG_RANK</li>
 * <li>E_AGG_PIXEL</li>
 * <li>E_AGG_CONF_EXPTIME</li>
 * <li>E_AGG_CONF_FRATE</li>
 * <li>E_AGG_CONF_FAVERAGE</li>
 * <li>E_AGG_CONF_CALIB</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGG_Keyword implements java.io.Serializable
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
    new String( "$Id: AGG_Keyword.java,v 1.2 2013-07-04 12:55:42 cjm Exp $" );

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
   * Turn on (ie guiding, loop)
   */
  public static AGG_Keyword E_AGG_ON =
    new AGG_Keyword( "E_AGG_ON", 0 );

  /**
   * Turn off (ie guiding, loop)
   */
  public static AGG_Keyword E_AGG_OFF =
    new AGG_Keyword( "E_AGG_OFF", 1 );

  /**
   * Guide on brightest source.
   */
  public static AGG_Keyword E_AGG_BRIGHTEST =
    new AGG_Keyword( "E_AGG_BRIGHTEST", 2 );

  /**
   * Guide on source within magnitude range.
   */
  public static AGG_Keyword E_AGG_RANGE =
    new AGG_Keyword( "E_AGG_RANGE", 3 );

  /**
   * Guide on source of given rank.
   */
  public static AGG_Keyword E_AGG_RANK =
    new AGG_Keyword( "E_AGG_RANK", 4 );

  /**
   * Guide on source closest to supplied pixel.
   */
  public static AGG_Keyword E_AGG_PIXEL =
    new AGG_Keyword( "E_AGG_PIXEL", 5 );

  /**
   * Configure exposure time.
   */
  public static AGG_Keyword E_AGG_CONF_EXPTIME =
    new AGG_Keyword( "E_AGG_CONF_EXPTIME", 6 );

  /**
   * Configure frame rate.
   */
  public static AGG_Keyword E_AGG_CONF_FRATE =
    new AGG_Keyword( "E_AGG_CONF_FRATE", 7 );

  /**
   * Configure frame average.
   */
  public static AGG_Keyword E_AGG_CONF_FAVERAGE =
    new AGG_Keyword( "E_AGG_CONF_FAVERAGE", 8 );

  /**
   * 
   */
  public static AGG_Keyword E_AGG_CONF_CALIB =
    new AGG_Keyword( "E_AGG_CONF_CALIB", 9 );


  /**
   * Array allowing serialization.
   */
  protected static final AGG_Keyword[] array =
  {
    E_AGG_ON,
    E_AGG_OFF,
    E_AGG_BRIGHTEST,
    E_AGG_RANGE,
    E_AGG_RANK,
    E_AGG_PIXEL,
    E_AGG_CONF_EXPTIME,
    E_AGG_CONF_FRATE,
    E_AGG_CONF_FAVERAGE,
    E_AGG_CONF_CALIB
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
   * Return an object reference of the AGG_Keyword with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGG_Keyword
   * @return a reference to the AGG_Keyword specified by the argument
   */
  public static AGG_Keyword getReference( String s )
  {
    return( (AGG_Keyword)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGG_Keyword with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGG_Keyword
   * @return a reference to the AGG_Keyword specified by the argument
   */
  public static AGG_Keyword getReference( int i )
  {
    return( (AGG_Keyword)( intHash.get( new Integer( i ) ) ) );
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
  protected AGG_Keyword( String s )
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
  protected AGG_Keyword( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGG_Keyword.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGG_Keyword.
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
 *    $Date: 2013-07-04 12:55:42 $
 * $RCSfile: AGG_Keyword.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_Keyword.java,v $
 *      $Id: AGG_Keyword.java,v 1.2 2013-07-04 12:55:42 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
