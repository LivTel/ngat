package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type MountState.
 * <p>
 * The list of States is:
 * <ul>
 * <li>OKAY</li>
 * <li>INITIALISING</li>
 * <li>SAFE</li>
 * <li>ERROR</li>
 * <li>WARNING</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class MountState implements java.io.Serializable
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
    new String( "$Id: MountState.java,v 1.2 2003-09-19 16:01:09 je Exp $" );

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
   * Everything nominal.
   */
  public static final MountState OKAY =
    new MountState( "OKAY", 500002 );

  /**
   * Mount initialising.
   */
  public static final MountState INITIALISING =
    new MountState( "INITIALISING", 500002 );

  /**
   * Mount is ready to be shutdown.
   */
  public static final MountState SAFE =
    new MountState( "SAFE", 500002 );

  /**
   * A serious error has occurred.
   */
  public static final MountState ERROR =
    new MountState( "ERROR", 500002 );

  /**
   * A non-serious error has occurred.
   */
  public static final MountState WARNING =
    new MountState( "WARNING", 500002 );


  /**
   * Array to allow serialization.
   */
  protected final static MountState[] array =
  {
    OKAY,
    INITIALISING,
    SAFE,
    ERROR,
    WARNING
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
   * Return an object reference of the MountState with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the MountState
   * @return a reference to the MountState specified by the argument
   */
  public static MountState getReference( String s )
  {
    return( (MountState)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the MountState with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the MountState
   * @return a reference to the MountState specified by the argument
   */
  public static MountState getReference( int i )
  {
    return( (MountState)( intHash.get( new Integer( i ) ) ) );
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
  protected MountState( String s )
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
  protected MountState( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this MountState.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this MountState.
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
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: MountState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/MountState.java,v $
 *      $Id: MountState.java,v 1.2 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
