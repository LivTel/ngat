package ngat.ngtcs.subsystem.sdb;

import ngat.ngtcs.subsystem.*;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type SDB_Service.  These are:
 * <ul>
 * <li>E_SDB_HEARTBEAT</li>
 * <li>E_SDB_SHUTDOWN</li>
 * <li>E_SDB_SAFESTATE</li>
 * <li>E_SDB_ACTIVATE</li>
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
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public final class SDB_Service
  implements java.io.Serializable, ngat.ngtcs.subsystem.TTL_DataType,
	     ngat.net.cil.CIL_ServiceClass
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: SDB_Service.java,v 1.1 2006-11-20 14:46:54 cjm Exp $" );

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
  public final static SDB_Service E_SDB_HEARTBEAT =
    new SDB_Service( "E_SDB_HEARTBEAT",
		     TTL_CIL_GenericService.E_MCP_HEARTBEAT.getInt(),
		     TTL_Package.NO_SYS );
      
  /**
   *
   */
  public final static SDB_Service E_SDB_SHUTDOWN =
    new SDB_Service( "E_SDB_SHUTDOWN",
		     TTL_CIL_GenericService.E_MCP_SHUTDOWN.getInt(),
		     TTL_Package.NO_SYS );
      
  /**
   *
   */
  public final static SDB_Service E_SDB_SAFESTATE =
    new SDB_Service( "E_SDB_SAFESTATE",
		     TTL_CIL_GenericService.E_MCP_SAFESTATE.getInt(),
		     TTL_Package.NO_SYS );
      
  /**
   *
   */
  public final static SDB_Service E_SDB_ACTIVATE =
    new SDB_Service( "E_SDB_ACTIVATE",
		     TTL_CIL_GenericService.E_MCP_ACTIVATE.getInt(),
		     TTL_Package.NO_SYS );
      
  /**
   * 
   */
  public final static SDB_Service E_SDB_PURGE =
      new SDB_Service( "E_SDB_PURGE", 0, TTL_Package.SDB );

  /**
   * Submit data for storage
   */
  public final static SDB_Service E_SDB_SUBMIT_1 =
      new SDB_Service( "E_SDB_SUBMIT_1", 1, TTL_Package.SDB );

  /**
   * Request data from the database
   */
  public final static SDB_Service E_SDB_RETRIEVE_1 =
      new SDB_Service( "E_SDB_RETRIEVE_1", 2, TTL_Package.SDB );

  /**
   * Submit block data for storage
   */
  public final static SDB_Service E_SDB_SUBMIT_N =
      new SDB_Service( "E_SDB_SUBMIT_N", 3, TTL_Package.SDB );

  /**
   * Request block data from the database
   */
  public final static SDB_Service E_SDB_RETRIEVE_N =
      new SDB_Service( "E_SDB_RETRIEVE_N", 4, TTL_Package.SDB );

  /**
   * Request source IDs for all submitted data
   */
  public final static SDB_Service E_SDB_LISTSOURCES =
      new SDB_Service( "E_SDB_LISTSOURCES", 5, TTL_Package.SDB );

  /**
   * Request data IDs for a particular source
   */
  public final static SDB_Service E_SDB_LISTDATA =
      new SDB_Service( "E_SDB_LISTDATA", 6, TTL_Package.SDB );

  /**
   * Reuqest number of different sources in SDB
   */
  public final static SDB_Service E_SDB_COUNTSOURCES =
      new SDB_Service( "E_SDB_COUNTSOURCES", 7, TTL_Package.SDB );

  /**
   * Request number of data definitions for a Src
   */
  public final static SDB_Service E_SDB_COUNTDATA =
      new SDB_Service( "E_SDB_COUNTDATA", 8, TTL_Package.SDB );

  /**
   * Request number of measurements for a Src:Dat
   */
  public final static SDB_Service E_SDB_COUNTMSRMENTS =
      new SDB_Service( "E_SDB_COUNTMSRMENTS", 9, TTL_Package.SDB );

  /**
   * Request data from the database (robust mode)
   */
  public final static SDB_Service E_SDB_RETRIEVE_1R =
      new SDB_Service( "E_SDB_RETRIEVE_1R", 10, TTL_Package.SDB );

  /**
   * Request data from storage file (robust mode)
   */
  public final static SDB_Service E_SDB_RETRIEVE_F =
      new SDB_Service( "E_SDB_RETRIEVE_F", 11, TTL_Package.SDB );

  /**
   * Post data for submission no response
   */
  public final static SDB_Service E_SDB_SUBMIT_1P =
      new SDB_Service( "E_SDB_SUBMIT_1P", 12, TTL_Package.SDB );

  /**
   * Clear data with a particular SourceID
   */
  public final static SDB_Service E_SDB_CLEAR_S =
      new SDB_Service( "E_SDB_CLEAR_S", 13, TTL_Package.SDB );

  /**
   * Clear specific data submissions
   */
  public final static SDB_Service E_SDB_CLEAR_1 =
      new SDB_Service( "E_SDB_CLEAR_1", 14, TTL_Package.SDB );

  /**
   * Request latest data from file (robust mode)
   */
  public final static SDB_Service E_SDB_RETRIEVE_L =
      new SDB_Service( "E_SDB_RETRIEVE_L", 15, TTL_Package.SDB );

  /**
   * End of enumerated list of commands
   */
  public final static SDB_Service E_SDB_COMMAND_EOL =
      new SDB_Service( "E_SDB_COMMAND_EOL", 16, TTL_Package.SDB );

  /**
   * Array allowing serialization.
   */
  protected static final SDB_Service[] array =
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
    E_SDB_COMMAND_EOL
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

  /**
   *
   */
  protected TTL_Package ttlPackage;

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Return an object reference of the SDB_Service with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the SDB_Service
   * @return a reference to the SDB_Service specified by the argument
   */
  public static SDB_Service getReference( String s )
  {
    return( (SDB_Service)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the SDB_Service with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the SDB_Service
   * @return a reference to the SDB_Service specified by the argument
   */
  public static SDB_Service getReference( int i )
  {
    return( (SDB_Service)( intHash.get( new Integer( i ) ) ) );
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
   * @param p the TTL_Package of this TTL_DataType
   * @see #name
   * @see #intValue
   * @see #array
   */
  protected SDB_Service( String s, int i, TTL_Package p )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
    ttlPackage = p;
  }


  /**
   * Return the name of this SDB_Service.
   * @return name
   * @see #name
   */
  public final String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this SDB_Service.
   * @return intValue
   * @see #intValue
   */
  public final int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package for which this data refers.
   * @return ttlPackage
   * @see #ttlPackage
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
 *    $Date: 2006-11-20 14:46:54 $
 * $RCSfile: SDB_Service.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/sdb/SDB_Service.java,v $
 *      $Id: SDB_Service.java,v 1.1 2006-11-20 14:46:54 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
