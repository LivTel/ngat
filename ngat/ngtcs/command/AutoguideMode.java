package ngat.ngtcs.command;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AutoguideMode.
 * <p>
 * Class defining Autoguiding Modes as type-safe enumerations.
 * <p>
 * The list of modes is:
 * <ul>
 * <li>BRIGHTEST</li>
 * <li>RANGE</li>
 * <li>RANK</li>
 * <li>PIXEL</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class AutoguideMode implements java.io.Serializable
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
    new String( "$Id: AutoguideMode.java,v 1.3 2013-07-04 10:06:26 cjm Exp $" );

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
   * Guide on the Brightest star.
   */
  public static AutoguideMode BRIGHTEST = 
    new AutoguideMode( "BRIGHTEST" );

  /**
   * Guide on a star within a specified Range.
   */
  public static AutoguideMode RANGE = 
    new AutoguideMode( "RANGE" );

  /**
   * Guide on the Nth brightest star.
   */
  public static AutoguideMode RANK = 
    new AutoguideMode( "RANK" );

  /**
   * Guide on the star at the specified pixel coordinates.
   */
  public static AutoguideMode PIXEL = 
    new AutoguideMode( "PIXEL" );


  /**
   * Array to allow serialization.
   */
  protected final static AutoguideMode[] array =
  {
    BRIGHTEST,
    RANGE,
    RANK,
    PIXEL
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
   * Return an object reference of the AutoguideMode with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AutoguideMode
   * @return a reference to the AutoguideMode specified by the argument
   */
  public static AutoguideMode getReference( String s )
  {
    return( (AutoguideMode)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AutoguideMode with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AutoguideMode
   * @return a reference to the AutoguideMode specified by the argument
   */
  public static AutoguideMode getReference( int i )
  {
    return( (AutoguideMode)( intHash.get( new Integer( i ) ) ) );
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
  protected AutoguideMode( String s )
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
  protected AutoguideMode( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AutoguideMode.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AutoguideMode.
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
 *    $Date: 2013-07-04 10:06:26 $
 * $RCSfile: AutoguideMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/AutoguideMode.java,v $
 *      $Id: AutoguideMode.java,v 1.3 2013-07-04 10:06:26 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 *
 */
