package ngat.ngtcs.common;
  
/**
 * Class defining Telescope states as type-safe enumerations.
 * <p>
 * The list of states is:
 * <ul>
 * <li>IDLE</li>
 * <li>SAFE</li>
 * <li>SLEWING</li>
 * <li>TRACKING</li>
 * </ul>
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public final class TelescopeState extends State
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
	new String( "$Id: TelescopeState.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /*
     * Idle telescope State
     */
    public static final TelescopeState IDLE = 
	new TelescopeState( "IDLE", 500011 );

    /*
     * Safe telescope State
     */
    public static final TelescopeState SAFE = 
	new TelescopeState( "SAFE", 500012 );

    /**
     * Slewing telescope State
     */
    public static final TelescopeState SLEWING = 
	new TelescopeState( "SLEWING", 500013 );

    /**
     * Tracking telescope State
     */
    public static final TelescopeState TRACKING = 
	new TelescopeState( "TRACKING", 500014 );

    /**
     * Array allowing serialization.
     */
    protected static final TelescopeState[] array =
    {
	IDLE, SAFE, SLEWING, TRACKING
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
     * Creates a TelescopeState.  The type-safe enumeration of TelescopeState
     * will have the specified String name (s) and int representation (i).
     * @param s the String name of this type-safe state enumeration
     * @param i the int representation of this type-safe state enumeration
     */
    protected TelescopeState( String s, int i )
    {
	super( s, i );	
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: TelescopeState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TelescopeState.java,v $
 *      $Id: TelescopeState.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */
