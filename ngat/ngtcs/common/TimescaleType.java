package ngat.ngtcs.common;

/**
 * Class defining Epoch Timescales as type-safe enumerations.
 * <p>
 * The list of Timescales is:
 * <ul>
 * <li>UT</li>
 * <li>UT1 ( = UT )</li>
 * <li>UTC</li>
 * <li>TAI</li>
 * <li>TDB</li>
 * <li>TDT</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TimescaleType extends ngat.util.TypeSafeEnumeration
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
	new String( "$Id: TimescaleType.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Type-safe Enumeration of the Universal Time timescale (UT).
     */
    public static final TimescaleType UT  = new TimescaleType( "UT", 3001 );

    /**
     * Type-safe Enumeration of the Universal Time timescale (UT1 = UT).
     */
    public static final TimescaleType UT1 = UT;

    /**
     * Type-safe Enumeration of the Coordinated Universal Time timescale
     * (UTC).
     */
    public static final TimescaleType UTC = new TimescaleType( "UTC", 3002 );

    /**
     * Type-safe Enumeration of the International Atomic Time (TAI).
     */
    public static final TimescaleType TAI = new TimescaleType( "TAI", 3003 );

    /**
     * Type-safe Enumeration of the Barycentric Dynamical timescale (TDB).
     */
    public static final TimescaleType TDB = new TimescaleType( "TDB", 3004 );

    /**
     * Type-safe Enumeration of the Terrestrial dynamical time (TDT).
     */
    public static final TimescaleType TDT = new TimescaleType( "TDT", 3005 );

    /**
     * Array allowing serialization.
     */
    protected static final TimescaleType[] array =
    {
	UT, UT1, UTC, TAI, TDB, TDT
    };

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Creates a TimescaleType.  The type-safe enumeration of Timescale will
     * have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe Timescale enumeration
     * @param i the int representation of this type-safe Timescale enumeration
     */
    protected TimescaleType( String s, int i )
    {
	super( s, i );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: TimescaleType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TimescaleType.java,v $
 *      $Id: TimescaleType.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
