package ngat.ngtcs.common;

/**
 * Type-safe enumerations of the different Calibrate Modes.
 * <p>
 * The list of Modes is:
 * <ul>
 * <li>DEFAULT</li>
 * <li>NEW</li>
 * <li>LAST</li>
 * </ul>
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class CalibrateMode extends ngat.util.TypeSafeEnumeration
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
	new String( "$Id: CalibrateMode.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     *
     */
    public static final CalibrateMode DEFAULT = new CalibrateMode( "DEFAULT" );

    /**
     *
     */
    public static final CalibrateMode NEW = new CalibrateMode( "NEW" );

    /**
     *
     */
    public static final CalibrateMode LAST = new CalibrateMode( "LAST" );

    /**
     * Array allowing serialization.
     */
    protected static final CalibrateMode[] array =
    {
	DEFAULT, NEW, LAST
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
     * The protected constructor of type-safe enumeration classes.  The
     * CalibrationMode has the name specified by the String s.
     * @param s the String name of the CalibrationMode
     */
    protected CalibrateMode( String s )
    {
	super( s );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: CalibrateMode.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/CalibrateMode.java,v $
 *      $Id: CalibrateMode.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
