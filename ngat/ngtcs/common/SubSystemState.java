package ngat.ngtcs.common;
  
/**
 * Class defining States as type-safe enumerations.
 * <p>
 * The list of States is:
 * <ul>
 * <li>ACTIVE</li>
 * <li>INACTIVE</li>
 * <li>SAFE</li>
 * <li>ERROR</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SubSystemState extends State
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
	new String( "$Id: SubSystemState.java,v 1.1 2003-07-01 10:13:04 je Exp $" );



    /**
     * Operational state.
     */
    public static final SubSystemState ACTIVE =
	new SubSystemState( "ACTIVE", 500002 );


    /**
     * Inactive state.
     */
    public static final SubSystemState INACTIVE =
	new SubSystemState( "INACTIVE", 500002 );


    /**
     * Operational state.
     */
    public static final SubSystemState SAFE =
	new SubSystemState( "SAFE", 500002 );


    /**
     * Operational state.
     */
    public static final SubSystemState ERROR =
	new SubSystemState( "ERROR", 500002 );

    /**
     * Array allowing serialization.
     */
    protected static final SubSystemState[] array =
    {
	ACTIVE, INACTIVE, SAFE, ERROR
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
     * Creates a SubSystemState.  The type-safe enumeration of SubSystemState
     * will have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe State enumeration
     * @param i the int representation of this type-safe State enumeration
     */
    protected SubSystemState( String s, int i )
    {
	super( s, i );
    }


    /**
     * Creates a SubSystemState.  The type-safe enumeration of SubSystemState
     * will have the specified String name (s).
     * @param s the String name of this type-safe State enumeration
     */
    protected SubSystemState( String s )
    {
	super( s );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: SubSystemState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SubSystemState.java,v $
 *      $Id: SubSystemState.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
