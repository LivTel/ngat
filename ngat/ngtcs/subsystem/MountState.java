package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

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
public class MountState extends State
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
	new String( "$Id: MountState.java,v 1.1 2003-07-01 10:13:46 je Exp $" );


    /**
     * Everything nominal.
     */
    public static final MountState OKAY =
	new MountState( "OKAY", 500002 );

    /**
     * Mount initialising.
     */
    public static final MountState INITIALISING =
	new MountState( "INITIALISING", 500002 );

    /**
     * Mount is ready to be shutdown.
     */
    public static final MountState SAFE =
	new MountState( "SAFE", 500002 );

    /**
     * A serious error has occurred.
     */
    public static final MountState ERROR =
	new MountState( "ERROR", 500002 );

    /**
     * A non-serious error has occurred.
     */
    public static final MountState WARNING =
	new MountState( "WARNING", 500002 );

    /**
     * Array allowing serialization.
     */
    protected static final MountState[] array =
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
     * Creates a MountState.  The type-safe enumeration of State will
     * have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe State enumeration
     * @param i the int representation of this type-safe State enumeration
     */
    protected MountState( String s, int i )
    {
	super( s, i );
    }
}
/*
 *    $Date: 2003-07-01 10:13:46 $
 * $RCSfile: MountState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/MountState.java,v $
 *      $Id: MountState.java,v 1.1 2003-07-01 10:13:46 je Exp $
 *     $Log: not supported by cvs2svn $
 */
