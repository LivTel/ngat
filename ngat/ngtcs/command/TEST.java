package ngat.ngtcs.command;

import ngat.ngtcs.common.*;

/**
 * This command runs through the astrometry routines used by the track command
 * and prints the returned information to the screen.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TEST extends Command
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
	new String( "$Id: TEST.java,v 1.1 2003-07-01 10:12:39 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Target this TRACK Command is to use.
     */
    protected Target target;

    /**
     * Non-sidereal timestamp for non-sidereal tracking of this target.
     */
    protected Timestamp timestamp;

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
     * Create a TEST command with the specified ID, using the speicifed
     * target.
     * @param s the String ID of this command
     * @param t the target to use
     */
    public TEST( String s, Target t )
    {
	super( s );
	target = t;
    }


    /**
     * Return the Target to use for this command.
     * @return target
     * @see #target
     */
    public Target getTarget()
    {
	return target;
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "TEST target timestamp" );
    }


    /**
     * Returns the RotatorMode arguments as a String
     * @return the arguments as a String 
     */
    protected String getArgString()
    {
	return( target.toString() );
    }
}
/*
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: TEST.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/TEST.java,v $
 *      $Id: TEST.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */