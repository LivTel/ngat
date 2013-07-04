package ngat.ngtcs.subsystem.acn;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type ACN_Command.  These are:
 * <ul>
 * <li>E_ACN_MIN_CMD</li>
 * <li>E_ACN_DIAGNOSE_CMD</li>
 * <li>E_ACN_GET_CMD</li>
 * <li>E_ACN_SET_CMD</li>
 * <li>E_ACN_STOP_CMD</li>
 * <li>E_ACN_START_CMD</li>
 * <li>E_ACN_HALT_CMD</li>
 * <li>E_ACN_MOVEUNHOMED_CMD</li>
 * <li>E_ACN_TRACK_CMD</li>
 * <li>E_ACN_MOVE_CMD</li>
 * <li>E_ACN_UNHOME_CMD</li>
 * <li>E_ACN_SINUSOID_CMD</li>
 * <li>E_ACN_GETSINUSOID_CMD</li>
 * <li>E_ACN_GETLOG_CMD</li>
 * <li>E_ACN_GETLOGLIST_CMD</li>
 * <li>E_ACN_SETLOGLIST_CMD</li>
 * <li>E_ACN_SETLOGDEPTH_CMD</li>
 * <li>E_ACN_CLEARLOG_CMD</li>
 * <li>E_ACN_GETFASTLOG_CMD</li>
 * <li>E_ACN_GETNEXTFASTLOG_CMD</li>
 * <li>E_ACN_GETFASTLOGLIST_CMD</li>
 * <li>E_ACN_SETFASTLOGLIST_CMD</li>
 * <li>E_ACN_SETFASTLOGDEPTH_CMD</li>
 * <li>E_ACN_SETFASTLOGFREQUENCY_CMD</li>
 * <li>E_ACN_STARTFASTLOG_CMD</li>
 * <li>E_ACN_STOPFASTLOG_CMD</li>
 * <li>E_ACN_SETNOFASTLOGS_CMD</li>
 * <li>E_ACN_HOME_CMD</li>
 * <li>E_ACN_SELECTMOTIONALGORITHM_CMD</li>
 * <li>E_ACN_CONTACTORRESET_CMD</li>
 * <li>E_ACN_RESETHEAD_CMD</li>
 * <li>E_ACN_SIMULATE_CMD</li>
 * <li>E_ACN_WARMBOOT_CMD</li>
 * <li>E_ACN_RECOVER_CMD</li>
 * <li>E_ACN_KEEPUNHOMED_CMD</li>
 * <li>E_ACN_SHUTDOWN_CMD</li>
 * <li>E_ACN_GET_ATTR_CMD</li>
 * <li>E_ACN_SET_ATTR_CMD</li>
 * <li>E_ACN_RESET_NODE_CMD</li>
 * <li>E_ACN_ACTIVATE_CMD</li>
 * <li>E_ACN_SAFESTATE_CMD</li>
 * <li>E_ACN_HEARTBEAT_CMD</li>
 * <li>E_ACN_MAX_CMD</li>
 * <li>E_ACN_CMD_LIST_MAX_VALUE</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class ACN_Command implements java.io.Serializable
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
    new String( "$Id: ACN_Command.java,v 1.2 2013-07-04 12:52:30 cjm Exp $" );

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
  public static ACN_Command E_ACN_MIN_CMD =
    new ACN_Command( "E_ACN_MIN_CMD", 0 );

  /**
   * 
   */
  public static ACN_Command E_ACN_DIAGNOSE_CMD =
    new ACN_Command( "E_ACN_DIAGNOSE_CMD", 1 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GET_CMD =
    new ACN_Command( "E_ACN_GET_CMD", 2 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SET_CMD =
    new ACN_Command( "E_ACN_SET_CMD", 3 );

  /**
   * 
   */
  public static ACN_Command E_ACN_STOP_CMD =
    new ACN_Command( "E_ACN_STOP_CMD", 4 );

  /**
   * 
   */
  public static ACN_Command E_ACN_START_CMD =
    new ACN_Command( "E_ACN_START_CMD", 5 );

  /**
   * 
   */
  public static ACN_Command E_ACN_HALT_CMD =
    new ACN_Command( "E_ACN_HALT_CMD", 6 );

  /**
   * 
   */
  public static ACN_Command E_ACN_MOVEUNHOMED_CMD =
    new ACN_Command( "E_ACN_MOVEUNHOMED_CMD", 7 );

  /**
   * 
   */
  public static ACN_Command E_ACN_TRACK_CMD =
    new ACN_Command( "E_ACN_TRACK_CMD", 8 );

  /**
   * 
   */
  public static ACN_Command E_ACN_MOVE_CMD =
    new ACN_Command( "E_ACN_MOVE_CMD", 9 );

  /**
   * 
   */
  public static ACN_Command E_ACN_UNHOME_CMD =
    new ACN_Command( "E_ACN_UNHOME_CMD", 10 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SINUSOID_CMD =
    new ACN_Command( "E_ACN_SINUSOID_CMD", 11 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETSINUSOID_CMD =
    new ACN_Command( "E_ACN_GETSINUSOID_CMD", 12 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETLOG_CMD =
    new ACN_Command( "E_ACN_GETLOG_CMD", 13 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETLOGLIST_CMD =
    new ACN_Command( "E_ACN_GETLOGLIST_CMD", 14 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETLOGLIST_CMD =
    new ACN_Command( "E_ACN_SETLOGLIST_CMD", 15 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETLOGDEPTH_CMD =
    new ACN_Command( "E_ACN_SETLOGDEPTH_CMD", 16 );

  /**
   * 
   */
  public static ACN_Command E_ACN_CLEARLOG_CMD =
    new ACN_Command( "E_ACN_CLEARLOG_CMD", 17 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETFASTLOG_CMD =
    new ACN_Command( "E_ACN_GETFASTLOG_CMD", 18 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETNEXTFASTLOG_CMD =
    new ACN_Command( "E_ACN_GETNEXTFASTLOG_CMD", 19 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GETFASTLOGLIST_CMD =
    new ACN_Command( "E_ACN_GETFASTLOGLIST_CMD", 20 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETFASTLOGLIST_CMD =
    new ACN_Command( "E_ACN_SETFASTLOGLIST_CMD", 21 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETFASTLOGDEPTH_CMD =
    new ACN_Command( "E_ACN_SETFASTLOGDEPTH_CMD", 22 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETFASTLOGFREQUENCY_CMD =
    new ACN_Command( "E_ACN_SETFASTLOGFREQUENCY_CMD", 23 );

  /**
   * 
   */
  public static ACN_Command E_ACN_STARTFASTLOG_CMD =
    new ACN_Command( "E_ACN_STARTFASTLOG_CMD", 24 );

  /**
   * 
   */
  public static ACN_Command E_ACN_STOPFASTLOG_CMD =
    new ACN_Command( "E_ACN_STOPFASTLOG_CMD", 25 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SETNOFASTLOGS_CMD =
    new ACN_Command( "E_ACN_SETNOFASTLOGS_CMD", 26 );

  /**
   * 
   */
  public static ACN_Command E_ACN_HOME_CMD =
    new ACN_Command( "E_ACN_HOME_CMD", 27 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SELECTMOTIONALGORITHM_CMD =
    new ACN_Command( "E_ACN_SELECTMOTIONALGORITHM_CMD", 28 );

  /**
   * 
   */
  public static ACN_Command E_ACN_CONTACTORRESET_CMD =
    new ACN_Command( "E_ACN_CONTACTORRESET_CMD", 29 );

  /**
   * 
   */
  public static ACN_Command E_ACN_RESETHEAD_CMD =
    new ACN_Command( "E_ACN_RESETHEAD_CMD", 30 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SIMULATE_CMD =
    new ACN_Command( "E_ACN_SIMULATE_CMD", 31 );

  /**
   * 
   */
  public static ACN_Command E_ACN_WARMBOOT_CMD =
    new ACN_Command( "E_ACN_WARMBOOT_CMD", 32 );

  /**
   * 
   */
  public static ACN_Command E_ACN_RECOVER_CMD =
    new ACN_Command( "E_ACN_RECOVER_CMD", 33 );

  /**
   * 
   */
  public static ACN_Command E_ACN_KEEPUNHOMED_CMD =
    new ACN_Command( "E_ACN_KEEPUNHOMED_CMD", 34 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SHUTDOWN_CMD =
    new ACN_Command( "E_ACN_SHUTDOWN_CMD", 35 );

  /**
   * 
   */
  public static ACN_Command E_ACN_GET_ATTR_CMD =
    new ACN_Command( "E_ACN_GET_ATTR_CMD", 36 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SET_ATTR_CMD =
    new ACN_Command( "E_ACN_SET_ATTR_CMD", 37 );

  /**
   * 
   */
  public static ACN_Command E_ACN_RESET_NODE_CMD =
    new ACN_Command( "E_ACN_RESET_NODE_CMD", 38 );

  /**
   * 
   */
  public static ACN_Command E_ACN_ACTIVATE_CMD =
    new ACN_Command( "E_ACN_ACTIVATE_CMD", 39 );

  /**
   * 
   */
  public static ACN_Command E_ACN_SAFESTATE_CMD =
    new ACN_Command( "E_ACN_SAFESTATE_CMD", 40 );

  /**
   * 
   */
  public static ACN_Command E_ACN_HEARTBEAT_CMD =
    new ACN_Command( "E_ACN_HEARTBEAT_CMD", 41 );

  /**
   * 
   */
  public static ACN_Command E_ACN_MAX_CMD =
    new ACN_Command( "E_ACN_MAX_CMD", 42 );

  /**
   * Req'd to force size to 4 bytes
   */
  public static ACN_Command E_ACN_CMD_LIST_MAX_VALUE =
    new ACN_Command( "E_ACN_CMD_LIST_MAX_VALUE", 43 );


  /**
   * Array allowing serialization.
   */
  protected static final ACN_Command[] array =
  {
    E_ACN_MIN_CMD,
    E_ACN_DIAGNOSE_CMD,
    E_ACN_GET_CMD,
    E_ACN_SET_CMD,
    E_ACN_STOP_CMD,
    E_ACN_START_CMD,
    E_ACN_HALT_CMD,
    E_ACN_MOVEUNHOMED_CMD,
    E_ACN_TRACK_CMD,
    E_ACN_MOVE_CMD,
    E_ACN_UNHOME_CMD,
    E_ACN_SINUSOID_CMD,
    E_ACN_GETSINUSOID_CMD,
    E_ACN_GETLOG_CMD,
    E_ACN_GETLOGLIST_CMD,
    E_ACN_SETLOGLIST_CMD,
    E_ACN_SETLOGDEPTH_CMD,
    E_ACN_CLEARLOG_CMD,
    E_ACN_GETFASTLOG_CMD,
    E_ACN_GETNEXTFASTLOG_CMD,
    E_ACN_GETFASTLOGLIST_CMD,
    E_ACN_SETFASTLOGLIST_CMD,
    E_ACN_SETFASTLOGDEPTH_CMD,
    E_ACN_SETFASTLOGFREQUENCY_CMD,
    E_ACN_STARTFASTLOG_CMD,
    E_ACN_STOPFASTLOG_CMD,
    E_ACN_SETNOFASTLOGS_CMD,
    E_ACN_HOME_CMD,
    E_ACN_SELECTMOTIONALGORITHM_CMD,
    E_ACN_CONTACTORRESET_CMD,
    E_ACN_RESETHEAD_CMD,
    E_ACN_SIMULATE_CMD,
    E_ACN_WARMBOOT_CMD,
    E_ACN_RECOVER_CMD,
    E_ACN_KEEPUNHOMED_CMD,
    E_ACN_SHUTDOWN_CMD,
    E_ACN_GET_ATTR_CMD,
    E_ACN_SET_ATTR_CMD,
    E_ACN_RESET_NODE_CMD,
    E_ACN_ACTIVATE_CMD,
    E_ACN_SAFESTATE_CMD,
    E_ACN_HEARTBEAT_CMD,
    E_ACN_MAX_CMD,
    E_ACN_CMD_LIST_MAX_VALUE
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
   * Return an object reference of the ACN_Command with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the ACN_Command
   * @return a reference to the ACN_Command specified by the argument
   */
  public static ACN_Command getReference( String s )
  {
    return( (ACN_Command)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the ACN_Command with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the ACN_Command
   * @return a reference to the ACN_Command specified by the argument
   */
  public static ACN_Command getReference( int i )
  {
    return( (ACN_Command)( intHash.get( new Integer( i ) ) ) );
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
  protected ACN_Command( String s )
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
  protected ACN_Command( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this ACN_Command.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this ACN_Command.
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
 *    $Date: 2013-07-04 12:52:30 $
 * $RCSfile: ACN_Command.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/acn/ACN_Command.java,v $
 *      $Id: ACN_Command.java,v 1.2 2013-07-04 12:52:30 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:30  je
 *     Initial revision
 *
 *
 */
