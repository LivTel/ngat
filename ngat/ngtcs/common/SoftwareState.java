package ngat.ngtcs.common;
  
/**
 * Class defining States as type-safe enumerations.
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
 * @version $Revision: 1.1 $
 */
public class SoftwareState extends State
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
	new String( "$Id: SoftwareState.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Everything nominal.
     */
    public static final SoftwareState OKAY =
	new SoftwareState( "OKAY", 500002 );

    /**
     * Software initialising.
     */
    public static final SoftwareState INITIALISING =
	new SoftwareState( "INITIALISING", 500002 );

    /**
     * Software is ready to be shutdown.
     */
    public static final SoftwareState SAFE =
	new SoftwareState( "SAFE", 500002 );

    /**
     * A serious error has occurred.
     */
    public static final SoftwareState ERROR =
	new SoftwareState( "ERROR", 500002 );

    /**
     * A non-serious error has occurred.
     */
    public static final SoftwareState WARNING =
	new SoftwareState( "WARNING", 500002 );

    /**
     * Array allowing serialization.
     */
    protected static final SoftwareState[] array =
    {
	OKAY, INITIALISING, SAFE, ERROR, WARNING
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
     * Creates a SoftwareState.  The type-safe enumeration of State will
     * have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe State enumeration
     * @param i the int representation of this type-safe State enumeration
     */
    protected SoftwareState( String s, int i )
    {
	super( s, i );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: SoftwareState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SoftwareState.java,v $
 *      $Id: SoftwareState.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
