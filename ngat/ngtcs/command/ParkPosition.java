package ngat.ngtcs.command;

import java.util.Hashtable;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type ParkPosition.
 * <p>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class ParkPosition implements java.io.Serializable
{
  /*=======================================================================*/
  /*                                                                       */
  /* CLASS FIELDS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id: ParkPosition.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

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
   * The Zenith pointing park position.
   */
  public static final ParkPosition ZENITH = new ParkPosition( "ZENITH" );

  /**
   *
   */
  protected final static ParkPosition[] array =
  {
    ZENITH
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
   * Return an object reference of the ParkPosition with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the ParkPosition
   * @return a reference to the ParkPosition specified by the argument
   */
  public static ParkPosition getReference( String s )
  {
    return( (ParkPosition)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the ParkPosition with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the ParkPosition
   * @return a reference to the ParkPosition specified by the argument
   */
  public static ParkPosition getReference( int i )
  {
    return( (ParkPosition)( intHash.get( new Integer( i ) ) ) );
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
  protected ParkPosition( String s )
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
  protected ParkPosition( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this ParkPosition.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this ParkPosition.
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
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: ParkPosition.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/ParkPosition.java,v $
 *      $Id: ParkPosition.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 *
 */
