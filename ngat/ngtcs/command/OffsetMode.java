package ngat.ngtcs.command;

import java.util.Hashtable;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type OffsetMode.
 * <p>
 * The only references are <code>Offset.TIME</code> and
 * <code>Offset.ARC</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class OffsetMode implements java.io.Serializable
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: OffsetMode.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

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

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * The time form of offsets (seconds).
   */
  public static final OffsetMode TIME = new OffsetMode( "TIME" );

  /**
   * The degree form of offsets (arcseconds).
   */
  public static final OffsetMode ARC = new OffsetMode( "ARC" );

  /**
   * Array to allow serialization.
   */
  protected final static OffsetMode[] array =
  {
    TIME,
    ARC
  };

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT FIELDS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

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

  /*=======================================================================*/
  /*                                                                       */
  /* CLASS METHODS.                                                        */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * Return an object reference of the OffsetMode with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the OffsetMode
   * @return a reference to the OffsetMode specified by the argument
   */
  public static OffsetMode getReference( String s )
  {
    return( (OffsetMode)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the OffsetMode with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the OffsetMode
   * @return a reference to the OffsetMode specified by the argument
   */
  public static OffsetMode getReference( int i )
  {
    return( (OffsetMode)( intHash.get( new Integer( i ) ) ) );
  }

  /*=======================================================================*/
  /*                                                                       */
  /* OBJECT METHODS.                                                       */
  /*                                                                       */
  /*=======================================================================*/

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
  protected OffsetMode( String s )
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
  protected OffsetMode( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this OffsetMode.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this OffsetMode.
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
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: OffsetMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/OffsetMode.java,v $
 *      $Id: OffsetMode.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
