package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGG_Status.
 * <p>
 * Class defining allowable references of AGG_Status.  These are:
 * <ul>
 * <li>E_AGG_GEN_ERR</li>
 * <li>E_AGG_LOOP_STOPPING</li>
 * <li>E_AGG_LOOP_RUNNING</li>
 * <li>E_AGG_CMD_NOT_PERMITTED</li>
 * <li>E_AGG_CMD_BAD_FORMAT</li>
 * <li>E_AGG_MEMERR</li>
 * <li>E_AGG_SEM_ERR</li>
 * <li>E_AGG_SHMEM_ERR</li>
 * <li>E_AGG_HW_ERR</li>
 * <li>E_AGG_CMD_UNKNOWN</li>
 * <li>E_AGG_CMD_CONTEXT_1</li>
 * <li>E_AGG_CAMOK</li>
 * <li>E_AGG_PROXERR</li>
 * <li>E_AGG_FORKERR</li>
 * <li>E_AGG_BADPARAM</li>
 * <li>E_AGG_IMERR</li>
 * <li>E_AGG_NOSTAR</li>
 * <li>E_AGG_COMMSERR</li>
 * <li>E_AGG_DMERR</li>
 * <li>E_AGG_FILERR</li>
 * <li>E_AGG_INTCANCEL</li>
 * <li>E_AGG_CMDREPLIED</li>
 * </ul>
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class AGG_Status implements java.io.Serializable
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
    new String( "$Id: AGG_Status.java,v 1.1 2003-09-19 16:08:38 je Exp $" );

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
  public static AGG_Status E_AGG_GEN_ERR =
    new AGG_Status( "E_AGG_GEN_ERR", 0 );

  /**
   * Guide loop stopping.
   */
  public static AGG_Status E_AGG_LOOP_STOPPING =
    new AGG_Status( "E_AGG_LOOP_STOPPING", 1 );

  /**
   * Guide loop running.
   */
  public static AGG_Status E_AGG_LOOP_RUNNING =
    new AGG_Status( "E_AGG_LOOP_RUNNING", 2 );

  /**
   * Command not permitted.
   */
  public static AGG_Status E_AGG_CMD_NOT_PERMITTED =
    new AGG_Status( "E_AGG_CMD_NOT_PERMITTED", 3 );

  /**
   * Command has bad format.
   */
  public static AGG_Status E_AGG_CMD_BAD_FORMAT =
    new AGG_Status( "E_AGG_CMD_BAD_FORMAT", 4 );

  /**
   * Error allocating memory.
   */
  public static AGG_Status E_AGG_MEMERR =
    new AGG_Status( "E_AGG_MEMERR", 5 );

  /**
   * Semaphore error.
   */
  public static AGG_Status E_AGG_SEM_ERR =
    new AGG_Status( "E_AGG_SEM_ERR", 6 );

  /**
   * Shared memory error.
   */
  public static AGG_Status E_AGG_SHMEM_ERR =
    new AGG_Status( "E_AGG_SHMEM_ERR", 7 );

  /**
   * Hardware error.
   */
  public static AGG_Status E_AGG_HW_ERR =
    new AGG_Status( "E_AGG_HW_ERR", 8 );

  /**
   * Unknown command.
   */
  public static AGG_Status E_AGG_CMD_UNKNOWN =
    new AGG_Status( "E_AGG_CMD_UNKNOWN", 9 );

  /**
   * Last starlog entry.
   */
  public static AGG_Status E_AGG_CMD_CONTEXT_1 =
    new AGG_Status( "E_AGG_CMD_CONTEXT_1", 10 );

  /**
   * Camera OK.
   */
  public static AGG_Status E_AGG_CAMOK =
    new AGG_Status( "E_AGG_CAMOK", 11 );

  /**
   * Error creating proxy.
   */
  public static AGG_Status E_AGG_PROXERR =
    new AGG_Status( "E_AGG_PROXERR", 12 );

  /**
   * Error forking process.
   */
  public static AGG_Status E_AGG_FORKERR =
    new AGG_Status( "E_AGG_FORKERR", 13 );

  /**
   * Parameter in error.
   */
  public static AGG_Status E_AGG_BADPARAM =
    new AGG_Status( "E_AGG_BADPARAM", 14 );

  /**
   * Image error.
   */
  public static AGG_Status E_AGG_IMERR =
    new AGG_Status( "E_AGG_IMERR", 15 );

  /**
   * No suitable star found.
   */
  public static AGG_Status E_AGG_NOSTAR =
    new AGG_Status( "E_AGG_NOSTAR", 16 );

  /**
   * Comms error.
   */
  public static AGG_Status E_AGG_COMMSERR =
    new AGG_Status( "E_AGG_COMMSERR", 17 );

  /**
   * Error in defect map.
   */
  public static AGG_Status E_AGG_DMERR =
    new AGG_Status( "E_AGG_DMERR", 18 );

  /**
   * File error.
   */
  public static AGG_Status E_AGG_FILERR =
    new AGG_Status( "E_AGG_FILERR", 19 );

  /**
   * Integration cancelled by user.
   */
  public static AGG_Status E_AGG_INTCANCEL =
    new AGG_Status( "E_AGG_INTCANCEL", 20 );

  /**
   * Reply already sent in response to command.
   */
  public static AGG_Status E_AGG_CMDREPLIED =
    new AGG_Status( "E_AGG_CMDREPLIED", 21 );


  /**
   * Array allowing serialization.
   */
  protected static final AGG_Status[] array =
  {
    E_AGG_GEN_ERR,
    E_AGG_LOOP_STOPPING,
    E_AGG_LOOP_RUNNING,
    E_AGG_CMD_NOT_PERMITTED,
    E_AGG_CMD_BAD_FORMAT,
    E_AGG_MEMERR,
    E_AGG_SEM_ERR,
    E_AGG_SHMEM_ERR,
    E_AGG_HW_ERR,
    E_AGG_CMD_UNKNOWN,
    E_AGG_CMD_CONTEXT_1,
    E_AGG_CAMOK,
    E_AGG_PROXERR,
    E_AGG_FORKERR,
    E_AGG_BADPARAM,
    E_AGG_IMERR,
    E_AGG_NOSTAR,
    E_AGG_COMMSERR,
    E_AGG_DMERR,
    E_AGG_FILERR,
    E_AGG_INTCANCEL,
    E_AGG_CMDREPLIED
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
   * Return an object reference of the AGG_Status with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGG_Status
   * @return a reference to the AGG_Status specified by the argument
   */
  public static AGG_Status getReference( String s )
  {
    return( (AGG_Status)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGG_Status with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGG_Status
   * @return a reference to the AGG_Status specified by the argument
   */
  public static AGG_Status getReference( int i )
  {
    return( (AGG_Status)( intHash.get( new Integer( i ) ) ) );
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
  protected AGG_Status( String s )
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
  protected AGG_Status( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGG_Status.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGG_Status.
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
 *    $Date: 2003-09-19 16:08:38 $
 * $RCSfile: AGG_Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_Status.java,v $
 *      $Id: AGG_Status.java,v 1.1 2003-09-19 16:08:38 je Exp $
 *     $Log: not supported by cvs2svn $
 *
 */
