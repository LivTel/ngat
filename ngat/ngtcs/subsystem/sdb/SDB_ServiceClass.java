package ngat.ngtcs.subsystem.sdb;

import ngat.ngtcs.net.cil.*;
import ngat.ngtcs.subsystem.*;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SDB_ServiceClass.  These are:
 * <ul>
 * <li>E_SDB_PURGE</li>
 * <li>E_SDB_SUBMIT_1</li>
 * <li>E_SDB_RETRIEVE_1</li>
 * <li>E_SDB_SUBMIT_N</li>
 * <li>E_SDB_RETRIEVE_N</li>
 * <li>E_SDB_LISTSOURCES</li>
 * <li>E_SDB_LISTDATA</li>
 * <li>E_SDB_COUNTSOURCES</li>
 * <li>E_SDB_COUNTDATA</li>
 * <li>E_SDB_COUNTMSRMENTS</li>
 * <li>E_SDB_RETRIEVE_1R</li>
 * <li>E_SDB_RETRIEVE_F</li>
 * <li>E_SDB_SUBMIT_1P</li>
 * <li>E_SDB_CLEAR_S</li>
 * <li>E_SDB_CLEAR_1</li>
 * <li>E_SDB_RETRIEVE_L</li>
 * <li>E_SDB_COMMAND_EOL</li>
 * <li>E_SDB_COMMAND_MAX_VALUE</li>
 * </ul>
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SDB_ServiceClass
  extends ngat.ngtcs.net.cil.TTL_CIL_ServiceClass implements TTL_DataType
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

  /*=======================================================================*/
  /*                                                                       */
  /* ENUMERATIONS.                                                         */
  /*                                                                       */
  /*=======================================================================*/

  /**
   * 
   */
  public static SDB_ServiceClass E_SDB_HEARTBEAT =
    new SDB_ServiceClass( "E_SDB_HEARTBEAT",
			  TTL_CIL_ServiceClass.E_MCP_HEARTBEAT.getInt() );

  /**
   * 
   */
  public static SDB_ServiceClass E_SDB_SHUTDOWN =
    (SDB_ServiceClass)TTL_CIL_ServiceClass.E_MCP_SHUTDOWN;

  /**
   * 
   */
  public static SDB_ServiceClass E_SDB_SAFESTATE =
    (SDB_ServiceClass)TTL_CIL_ServiceClass.E_MCP_SAFESTATE;

  /**
   * 
   */
  public static SDB_ServiceClass E_SDB_ACTIVATE =
    (SDB_ServiceClass)TTL_CIL_ServiceClass.E_MCP_ACTIVATE;


  /**
   * 
   */
  public static SDB_ServiceClass E_SDB_PURGE =
    new SDB_ServiceClass( "E_SDB_PURGE", 0 );

  /**
   * Submit data for storage
   */
  public static SDB_ServiceClass E_SDB_SUBMIT_1 =
    new SDB_ServiceClass( "E_SDB_SUBMIT_1", 1 );

  /**
   * Request data from the database
   */
  public static SDB_ServiceClass E_SDB_RETRIEVE_1 =
    new SDB_ServiceClass( "E_SDB_RETRIEVE_1", 2 );

  /**
   * Submit block data for storage
   */
  public static SDB_ServiceClass E_SDB_SUBMIT_N =
    new SDB_ServiceClass( "E_SDB_SUBMIT_N", 3 );

  /**
   * Request block data from the database
   */
  public static SDB_ServiceClass E_SDB_RETRIEVE_N =
    new SDB_ServiceClass( "E_SDB_RETRIEVE_N", 4 );

  /**
   * Request source IDs for all submitted data
   */
  public static SDB_ServiceClass E_SDB_LISTSOURCES =
    new SDB_ServiceClass( "E_SDB_LISTSOURCES", 5 );

  /**
   * Request data IDs for a particular source
   */
  public static SDB_ServiceClass E_SDB_LISTDATA =
    new SDB_ServiceClass( "E_SDB_LISTDATA", 6 );

  /**
   * Reuqest number of different sources in SDB
   */
  public static SDB_ServiceClass E_SDB_COUNTSOURCES =
    new SDB_ServiceClass( "E_SDB_COUNTSOURCES", 7 );

  /**
   * Request number of data definitions for a Src
   */
  public static SDB_ServiceClass E_SDB_COUNTDATA =
    new SDB_ServiceClass( "E_SDB_COUNTDATA", 8 );

  /**
   * Request number of measurements for a Src:Dat
   */
  public static SDB_ServiceClass E_SDB_COUNTMSRMENTS =
    new SDB_ServiceClass( "E_SDB_COUNTMSRMENTS", 9 );

  /**
   * Request data from the database (robust mode)
   */
  public static SDB_ServiceClass E_SDB_RETRIEVE_1R =
    new SDB_ServiceClass( "E_SDB_RETRIEVE_1R", 10 );

  /**
   * Request data from storage file (robust mode)
   */
  public static SDB_ServiceClass E_SDB_RETRIEVE_F =
    new SDB_ServiceClass( "E_SDB_RETRIEVE_F", 11 );

  /**
   * Post data for submission no response
   */
  public static SDB_ServiceClass E_SDB_SUBMIT_1P =
    new SDB_ServiceClass( "E_SDB_SUBMIT_1P", 12 );

  /**
   * Clear data with a particular SourceID
   */
  public static SDB_ServiceClass E_SDB_CLEAR_S =
    new SDB_ServiceClass( "E_SDB_CLEAR_S", 13 );

  /**
   * Clear specific data submissions
   */
  public static SDB_ServiceClass E_SDB_CLEAR_1 =
    new SDB_ServiceClass( "E_SDB_CLEAR_1", 14 );

  /**
   * Request latest data from file (robust mode)
   */
  public static SDB_ServiceClass E_SDB_RETRIEVE_L =
    new SDB_ServiceClass( "E_SDB_RETRIEVE_L", 15 );

  /**
   * End of enumerated list of commands
   */
  public static SDB_ServiceClass E_SDB_COMMAND_EOL =
    new SDB_ServiceClass( "E_SDB_COMMAND_EOL", 16 );

  /**
   * Req'd to force size to 4 bytes
   */
  public static SDB_ServiceClass E_SDB_COMMAND_MAX_VALUE =
    new SDB_ServiceClass( "E_SDB_COMMAND_MAX_VALUE", 17 );

  /**
   * Array allowing serialization.
   */
  protected static final SDB_ServiceClass[] array =
  {
    E_SDB_HEARTBEAT,
    E_SDB_SHUTDOWN,
    E_SDB_SAFESTATE,
    E_SDB_ACTIVATE,
    E_SDB_PURGE,
    E_SDB_SUBMIT_1,
    E_SDB_RETRIEVE_1,
    E_SDB_SUBMIT_N,
    E_SDB_RETRIEVE_N,
    E_SDB_LISTSOURCES,
    E_SDB_LISTDATA,
    E_SDB_COUNTSOURCES,
    E_SDB_COUNTDATA,
    E_SDB_COUNTMSRMENTS,
    E_SDB_RETRIEVE_1R,
    E_SDB_RETRIEVE_F,
    E_SDB_SUBMIT_1P,
    E_SDB_CLEAR_S,
    E_SDB_CLEAR_1,
    E_SDB_RETRIEVE_L,
    E_SDB_COMMAND_EOL,
    E_SDB_COMMAND_MAX_VALUE
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
   * Return an object reference of the SDB_ServiceClass with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SDB_ServiceClass
   * @return a reference to the SDB_ServiceClass specified by the argument
   */
  public static TTL_CIL_ServiceClass getReference( String s )
  {
    return( (TTL_CIL_ServiceClass)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SDB_ServiceClass with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SDB_ServiceClass
   * @return a reference to the SDB_ServiceClass specified by the argument
   */
  public static TTL_CIL_ServiceClass getReference( int i )
  {
    return( (TTL_CIL_ServiceClass)( intHash.get( new Integer( i ) ) ) );
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
  protected SDB_ServiceClass( String s )
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
  protected SDB_ServiceClass( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.SDB
   */
  public TTL_Package getTTL_Package()
  {
    return( TTL_Package.SDB );
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
 *    $Date: 2003-09-19 16:09:40 $
 * $RCSfile: SDB_ServiceClass.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_ServiceClass.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *
 */
