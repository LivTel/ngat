package ngat.ngtcs.command;

import java.util.Hashtable;

/**
 * Type-safe enumerations of the possible subjects for the SHOW command
 * <p>
 * The available enumerations currently only consist of
 * <ul>
 * <li>ASTROMETRY</li>
 * <li>AUTOGUIDER</li>
 * <li>CALIBRATE</li>
 * <li>FOCAL_STATION</li>
 * <li>LIMITS</li>
 * <li>MECHANISMS</li>
 * <li>METEOROLOGY</li>
 * <li>SOURCE</li>
 * <li>STATE</li>
 * <li>TIME</li>
 * <li>VERSION</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class ShowType implements java.io.Serializable
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
    new String( "$Id: ShowType.java,v 1.3 2013-07-04 10:08:16 cjm Exp $" );



  /**
   * The ASTROMETRY data type.
   */
  public static final ShowType ASTROMETRY = new ShowType( "ASTROMETRY" );

  /**
   * The CALIBRATE data type.
   */
  public static final ShowType CALIBRATE = new ShowType( "CALIBRATE" );

  /**
   * The FOCAL_STATION data type.
   */
  public static final ShowType FOCAL_STATION =
    new ShowType( "FOCAL_STATION" );

  /**
   * The LIMITS data type.
   */
  public static final ShowType LIMITS = new ShowType( "LIMITS" );

  /**
   * The MECHANISMS data type.
   */
  public static final ShowType MECHANISMS = new ShowType( "MECHANISMS" );

  /**
   * The METEOROLOGY data type.
   */
  public static final ShowType METEOROLOGY = new ShowType( "METEOROLOGY" );

  /**
   * The SOURCE data type.
   */
  public static final ShowType SOURCE = new ShowType( "SOURCE" );

  /**
   * The STATE data type.
   */
  public static final ShowType STATE = new ShowType( "STATE" );

  /**
   * The TIME data type.
   */
  public static final ShowType TIME = new ShowType( "TIME" );

  /**
   * The VERSION data type.
   */
  public static final ShowType VERSION = new ShowType( "VERSION" );


  /**
   * Hashtable to store the refernces on a key:value (name:reference) basis.
   */
  protected static Hashtable typeHash = new Hashtable();

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Name of this ShowType.
   */
  protected String name;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return the ShowType object reference from the name of the ShowType.
   * This method will return <code>null</code> if no such reference exists.
   * @param s the String name of the ShowType
   * @return the ShowType object reference called <i><b>name</b></i>
   */
  public static ShowType getReference( String s )
  {
    return (ShowType)( typeHash.get( s ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * The protected constructor of type-safe enumerations. This constructor
   * creates a ShowType with the name specified, and adds the reference
   * to a Hashtable for refence lookups.
   */
  protected ShowType( String s )
  {
    name = s;
    typeHash.put( s, this );
  }


  /**
   * Return the name of this ShowType.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }
}
/*
 *    $Date: 2013-07-04 10:08:16 $
 * $RCSfile: ShowType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/ShowType.java,v $
 *      $Id: ShowType.java,v 1.3 2013-07-04 10:08:16 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
