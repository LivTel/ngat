package ngat.ngtcs.common;

/**
 * Class defining Epoch Calendar systems as type-safe enumerations.
 * <p>
 * The list of Calendars is:
 * <ul>
 * <li>BESSELIAN</li>
 * <li>JULIAN</li>
 * <li>GREGORIAN</li>
 * </ul>
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class CalendarType extends ngat.util.TypeSafeEnumeration
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
	new String( "$Id: CalendarType.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Type-safe Enumeration of the Besselian Calendar.
     */
    final public static CalendarType BESSELIAN = 
	new CalendarType( "Besselian", 2021 );

    /**
     * Type-safe Enumeration of the Julian Calendar.
     */
    final public static CalendarType JULIAN = 
	new CalendarType( "Julian", 2022 );

    /**
     * Type-safe Enumeration of the Gregorian Calendar.
     */
    final public static CalendarType GREGORIAN = 
	new CalendarType( "Gregorian", 2023 );

    /**
     *
     */
    protected static final CalendarType[] array =
    {
	BESSELIAN, JULIAN, GREGORIAN
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
     * Creates a CalendarType.  The type-safe enumeration of Calendar will
     * have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe Calendar enumeration
     * @param i the int representation of this type-safe Calendar enumeration
     */
    private CalendarType( String s, int i )
    {
	super( s, i );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: CalendarType.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/CalendarType.java,v $
 *      $Id: CalendarType.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
