package ngat.ngtcs.subsystem.ags;

import ngat.ngtcs.subsystem.TTL_Package;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGS_Service.  These are:
 * <ul>
 * <li>E_AGS_CMD</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class AGS_Service implements ngat.net.cil.CIL_ServiceClass
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
   *
   */
  public final static AGS_Service E_AGS_CMD =
    new AGS_Service( "E_AGS_CMD", 0, TTL_Package.AGS );

  /**
   * Array allowing serialization.
   */
  protected static final AGS_Service[] array =
  {
    E_AGS_CMD
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
   * Return an object reference of the AGS_Service with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGS_Service
   * @return a reference to the AGS_Service specified by the argument
   */
  public static AGS_Service getReference( String s )
  {
    return( (AGS_Service)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGS_Service with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGS_Service
   * @return a reference to the AGS_Service specified by the argument
   */
  public static AGS_Service getReference( int i )
  {
    return( (AGS_Service)( intHash.get( new Integer( i ) ) ) );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create an enumeration of the specified name and int representation.
   * @param s the name of this enumeration
   * @param i the int representation of this enumeration
   * @param p the TTL_Package to which this service belongs
   * @see #name
   * @see #intValue
   * @see #ttlPackage
   * @see #array
   */
  protected AGS_Service( String s, int i, TTL_Package p )
  {
    name = s;
    nameHash.put( s, this );
    ttlPackage = p;
    intValue = i + ( ttlPackage.getInt() << 16 );
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGS_Service.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGS_Service.
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
    return( ttlPackage );
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
 *    $Date: 2006-11-20 14:46:39 $
 * $RCSfile: AGS_Service.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGS_Service.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
