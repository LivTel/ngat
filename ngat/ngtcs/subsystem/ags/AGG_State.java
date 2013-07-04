package ngat.ngtcs.subsystem.ags;

/**
 * Class to define Type-safe Enumerations.  This class defines <b>ALL</b>
 * possible references of type AGG_State.  These are:
 * <ul>
 * <li>E_AGG_STATE_UNKNOWN</li>
 * <li>E_AGG_STATE_OK</li>
 * <li>E_AGG_STATE_OFF</li>
 * <li>E_AGG_STATE_STANDBY</li>
 * <li>E_AGG_STATE_IDLE</li>
 * <li>E_AGG_STATE_WORKING</li>
 * <li>E_AGG_STATE_INIT</li>
 * <li>E_AGG_STATE_FAILED</li>
 * <li>E_AGG_STATE_INTWORK</li>
 * <li>E_AGG_STATE_ERROR</li>
 * <li>E_AGG_STATE_NONRECERR</li>
 * <li>E_AGG_STATE_LOOP_RUNNING</li>
 * <li>E_AGG_STATE_GUIDEONBRIGHT</li>
 * <li>E_AGG_STATE_GUIDEONRANGE</li>
 * <li>E_AGG_STATE_GUIDEONRANK</li>
 * <li>E_AGG_STATE_GUIDEONPIXEL</li>
 * <li>E_AGG_STATE_INTON</li>
 * <li>E_AGG_TSTATE_AT_TEMP</li>
 * <li>E_AGG_TSTATE_ABOVE_TEMP</li>
 * <li>E_AGG_TSTATE_BELOW_TEMP</li>
 * <li>E_AGG_TSTATE_ERROR</li>
 * </ul>
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class AGG_State
  implements java.io.Serializable, ngat.ngtcs.subsystem.TTL_DataType
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
    new String( "$Id: AGG_State.java,v 1.2 2013-07-04 12:55:46 cjm Exp $" );

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
  public static AGG_State E_AGG_STATE_UNKNOWN =
    new AGG_State( "E_AGG_STATE_UNKNOWN", 0x0 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_OK =
    new AGG_State( "E_AGG_STATE_OK", 0x10 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_OFF =
    new AGG_State( "E_AGG_STATE_OFF", 0x11 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_STANDBY =
    new AGG_State( "E_AGG_STATE_STANDBY", 0x12 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_IDLE =
    new AGG_State( "E_AGG_STATE_IDLE", 0x13 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_WORKING =
    new AGG_State( "E_AGG_STATE_WORKING", 0x14 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_INIT =
    new AGG_State( "E_AGG_STATE_INIT", 6 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_FAILED =
    new AGG_State( "E_AGG_STATE_FAILED", 7 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_INTWORK =
    new AGG_State( "E_AGG_STATE_INTWORK", 8 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_ERROR =
    new AGG_State( "E_AGG_STATE_ERROR", 0x100 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_NONRECERR =
    new AGG_State( "E_AGG_STATE_NONRECERR", 10 );

  /**
   * 
   */
  public static AGG_State E_AGG_STATE_LOOP_RUNNING =
    new AGG_State( "E_AGG_STATE_LOOP_RUNNING", 0x1000 );

  /**
   * E_AGG_BRIGHTEST
   */
  public static AGG_State E_AGG_STATE_GUIDEONBRIGHT =
    new AGG_State( "E_AGG_STATE_GUIDEONBRIGHT",
		   E_AGG_STATE_LOOP_RUNNING.getInt() +
		   AGG_Keyword.E_AGG_BRIGHTEST.getInt() );

  /**
   * E_AGG_RANGE
   */
  public static AGG_State E_AGG_STATE_GUIDEONRANGE =
    new AGG_State( "E_AGG_STATE_GUIDEONRANGE", 
		   E_AGG_STATE_LOOP_RUNNING.getInt() +
		   AGG_Keyword.E_AGG_RANGE.getInt() );

  /**
   * E_AGG_RANK
   */
  public static AGG_State E_AGG_STATE_GUIDEONRANK =
    new AGG_State( "E_AGG_STATE_GUIDEONRANK",
		   E_AGG_STATE_LOOP_RUNNING.getInt() +
		   AGG_Keyword.E_AGG_RANK.getInt() );

  /**
   * E_AGG_PIXEL
   */
  public static AGG_State E_AGG_STATE_GUIDEONPIXEL =
    new AGG_State( "E_AGG_STATE_GUIDEONPIXEL",
		   E_AGG_STATE_LOOP_RUNNING.getInt() +
		   AGG_Keyword.E_AGG_PIXEL.getInt() );

  /**
     * 
     */
    public static AGG_State E_AGG_STATE_INTON =
        new AGG_State( "E_AGG_STATE_INTON",
		       E_AGG_STATE_GUIDEONPIXEL.getInt() + 1 );

    /**
     * 
     */
    public static AGG_State E_AGG_TSTATE_AT_TEMP =
        new AGG_State( "E_AGG_TSTATE_AT_TEMP", 0x10000 );

    /**
     * 
     */
    public static AGG_State E_AGG_TSTATE_ABOVE_TEMP =
        new AGG_State( "E_AGG_TSTATE_ABOVE_TEMP", 0x10001 );

    /**
     * 
     */
    public static AGG_State E_AGG_TSTATE_BELOW_TEMP =
        new AGG_State( "E_AGG_TSTATE_BELOW_TEMP", 0x10002 );

    /**
     * 
     */
    public static AGG_State E_AGG_TSTATE_ERROR =
        new AGG_State( "E_AGG_TSTATE_ERROR", 0x10003 );


    /**
     * Array allowing serialization.
     */
    protected static final AGG_State[] array =
    {
        E_AGG_STATE_UNKNOWN,
        E_AGG_STATE_OK,
        E_AGG_STATE_OFF,
        E_AGG_STATE_STANDBY,
        E_AGG_STATE_IDLE,
        E_AGG_STATE_WORKING,
        E_AGG_STATE_INIT,
        E_AGG_STATE_FAILED,
        E_AGG_STATE_INTWORK,
        E_AGG_STATE_ERROR,
        E_AGG_STATE_NONRECERR,
        E_AGG_STATE_LOOP_RUNNING,
        E_AGG_STATE_GUIDEONBRIGHT,
        E_AGG_STATE_GUIDEONRANGE,
        E_AGG_STATE_GUIDEONRANK,
        E_AGG_STATE_GUIDEONPIXEL,
        E_AGG_STATE_INTON,
        E_AGG_TSTATE_AT_TEMP,
        E_AGG_TSTATE_ABOVE_TEMP,
        E_AGG_TSTATE_BELOW_TEMP,
        E_AGG_TSTATE_ERROR
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
   * Return an object reference of the AGG_State with the String
   * name specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param s the name of the AGG_State
   * @return a reference to the AGG_State specified by the argument
   */
  public static AGG_State getReference( String s )
  {
    return( (AGG_State)( nameHash.get( s ) ) );
  }


  /**
   * Return an object reference of the AGG_State with the int value
   * specified.
   * <p>
   * <b>NOTE:</b> if there is no matching reference <code>null</code> will be
   * returned.
   * @param i the int representation of the AGG_State
   * @return a reference to the AGG_State specified by the argument
   */
  public static AGG_State getReference( int i )
  {
    return( (AGG_State)( intHash.get( new Integer( i ) ) ) );
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
  protected AGG_State( String s )
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
  protected AGG_State( String s, int i )
  {
    name = s;
    nameHash.put( s, this );
    intValue = i;
    intHash.put( new Integer( intValue ), this );
  }


  /**
   * Return the name of this AGG_State.
   * @return name
   * @see #name
   */
  public String getName()
  {
    return name;
  }


  /**
   * Return the int representation of this AGG_State.
   * @return intValue
   * @see #intValue
   */
  public int getInt()
  {
    return intValue;
  }


  /**
   * Return the TTL_Package from which this data was read.
   * @return TTL_Package.AGG
   */
  public ngat.ngtcs.subsystem.TTL_Package getTTL_Package()
  {
    return( ngat.ngtcs.subsystem.TTL_Package.AGG );
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
 *    $Date: 2013-07-04 12:55:46 $
 * $RCSfile: AGG_State.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/ags/AGG_State.java,v $
 *      $Id: AGG_State.java,v 1.2 2013-07-04 12:55:46 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/09/19 16:08:38  je
 *     Initial revision
 *
 *
 */
