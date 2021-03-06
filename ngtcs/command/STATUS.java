package ngat.ngtcs.command;

/**
 * Request status information from the telescope system.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class STATUS extends Command
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
	new String( "$Id$" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/


    /*=========================================================================*/
    /*                                                                         */
    /* CLASS METHODS.                                                          */
    /*                                                                         */
    /*=========================================================================*/


    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT METHODS.                                                         */
    /*                                                                         */
    /*=========================================================================*/


    /**
     * Create a STATUS command with the specified ID.
     * @param s the String ID of this command
     */
    public STATUS( String s )
    {
	super( s );
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "STATUS : returns the status of the Telescope" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "\thas no arguments" );
    }
}
/*
 *    $Date: 2013-07-04 10:08:24 $
 * $RCSfile: STATUS.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/STATUS.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
