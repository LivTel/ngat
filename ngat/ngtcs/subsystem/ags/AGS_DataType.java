package ngat.ngtcs.subsystem.ags;

import ngat.ngtcs.subsystem.TTL_Package;
import ngat.ngtcs.subsystem.TTL_DataType;
import ngat.ngtcs.subsystem.TTL_GenericData;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGS_DataType.
 * <p>
 * 
 * List of type-safe enumerations of the Autoguider Data Types held in the
 * Status Database.
 * <p>
 * The available Data Types are:
 * <ul>
 * <li>D_AGS_PROC_STATE</li>
 * <li>D_AGS_AUTH_STATE</li>
 * <li>D_AGS_SYS_REQUEST</li>
 * <li>D_AGS_APP_VERSION</li>
 * <li>D_AGS_AGSTATE</li>
 * <li>D_AGS_WINDOW_TLX</li>
 * <li>D_AGS_WINDOW_TLY</li>
 * <li>D_AGS_WINDOW_BRX</li>
 * <li>D_AGS_WINDOW_BRY</li>
 * <li>D_AGS_INTTIME</li>
 * <li>D_AGS_FRAMESKIP</li>
 * <li>D_AGS_GUIDEMAG</li>
 * <li>D_AGS_CENTROIDX</li>
 * <li>D_AGS_CENTROIDY</li>
 * <li>D_AGS_FWHM</li>
 * <li>D_AGS_PEAKPIXEL</li>
 * <li>D_AGS_AGTEMP</li>
 * </ul>
 * @author $Author$ 
 * @version $Revision$
 */
public class AGS_DataType
  implements java.io.Serializable, TTL_DataType
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

  /**
   * TTL package to which this data refers.
   */
  private static final TTL_Package ttlPackage = TTL_Package.AGS;

  /*=========================================================================*/
  /*                                                                         */
  /* ENUMERATIONS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * 
   */
  public static AGS_DataType D_AGS_PROC_STATE =
    new AGS_DataType( "D_AGS_PROC_STATE", 1 );

  /**
   * granted authorisation state
   */
  public static AGS_DataType D_AGS_AUTH_STATE =
    new AGS_DataType( "D_AGS_AUTH_STATE", 2 );

  /**
   * 
   */
  public static AGS_DataType D_AGS_SYS_REQUEST =
    new AGS_DataType( "D_AGS_SYS_REQUEST", 3 );

  /**
   * 
   */
  public static AGS_DataType D_AGS_APP_VERSION =
    new AGS_DataType( "D_AGS_APP_VERSION", 4 );

  /**
   * 
   */
  public static AGS_DataType D_AGS_AGSTATE =
    new AGS_DataType( "D_AGS_AGSTATE", 5 );

  /**
   * X pixel of top left guide win coord.
   */
  public static AGS_DataType D_AGS_WINDOW_TLX =
    new AGS_DataType( "D_AGS_WINDOW_TLX", 6 );

  /**
   * Y pixel of top left guide win coord.
   */
  public static AGS_DataType D_AGS_WINDOW_TLY =
    new AGS_DataType( "D_AGS_WINDOW_TLY", 7 );

  /**
   * X pixel of bottom right guide win coord.
   */
  public static AGS_DataType D_AGS_WINDOW_BRX =
    new AGS_DataType( "D_AGS_WINDOW_BRX", 8 );

  /**
   * Y pixel of bottom right guide win coord.
   */
  public static AGS_DataType D_AGS_WINDOW_BRY =
    new AGS_DataType( "D_AGS_WINDOW_BRY", 9 );

  /**
   * Int time for current
   */
  public static AGS_DataType D_AGS_INTTIME =
    new AGS_DataType( "D_AGS_INTTIME", 10 );

  /**
   * Frame skip for current
   */
  public static AGS_DataType D_AGS_FRAMESKIP =
    new AGS_DataType( "D_AGS_FRAMESKIP", 11 );

  /**
   * Star magnitude of guide star on loop start.
   */
  public static AGS_DataType D_AGS_GUIDEMAG =
    new AGS_DataType( "D_AGS_GUIDEMAG", 12 );

  /**
   * X pixel of current
   */
  public static AGS_DataType D_AGS_CENTROIDX =
    new AGS_DataType( "D_AGS_CENTROIDX", 13 );

  /**
   * Y pixel of current
   */
  public static AGS_DataType D_AGS_CENTROIDY =
    new AGS_DataType( "D_AGS_CENTROIDY", 14 );

  /**
   * Measured FWHM of current
   */
  public static AGS_DataType D_AGS_FWHM =
    new AGS_DataType( "D_AGS_FWHM", 15 );

  /**
   * Star magnitude of current
   */
  public static AGS_DataType D_AGS_PEAKPIXEL =
    new AGS_DataType( "D_AGS_PEAKPIXEL", 16 );

  /**
   * Chip temperature.
   */
  public static AGS_DataType D_AGS_AGTEMP =
    new AGS_DataType( "D_AGS_AGTEMP", 17 );

  /**
   * Array allowing serialization.
   */
  protected static final AGS_DataType[] array =
  {
    D_AGS_PROC_STATE,
    D_AGS_AUTH_STATE,
    D_AGS_SYS_REQUEST,
    D_AGS_APP_VERSION,
    D_AGS_AGSTATE,
    D_AGS_WINDOW_TLX,
    D_AGS_WINDOW_TLY,
    D_AGS_WINDOW_BRX,
    D_AGS_WINDOW_BRY,
    D_AGS_INTTIME,
    D_AGS_FRAMESKIP,
    D_AGS_GUIDEMAG,
    D_AGS_CENTROIDX,
    D_AGS_CENTROIDY,
    D_AGS_FWHM,
    D_AGS_PEAKPIXEL,
    D_AGS_AGTEMP
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
   * Return an object reference of the AGS_DataType with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGS_DataType
   * @return a reference to the AGS_DataType specified by the argument
   */
  public static AGS_DataType getReference( String s )
  {
    return( (AGS_DataType)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGS_DataType with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGS_DataType
   * @return a reference to the AGS_DataType specified by the argument
   */
  public static AGS_DataType getReference( int i )
  {
    return( (AGS_DataType)( intHash.get( new Integer( i ) ) ) );
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
  protected AGS_DataType( String s )
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
  protected AGS_DataType( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGS_DataType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGS_DataType.
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
 *    $Date: 2013-07-04 12:55:58 $
 * $RCSfile: AGS_DataType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS_DataType.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
