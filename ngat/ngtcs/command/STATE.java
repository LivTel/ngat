package ngat.ngtcs.command;

/**
 * This command returns the state of the telescope system.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class STATE extends Command
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
	new String( "$Id: STATE.java,v 1.3 2013-07-04 10:08:20 cjm Exp $" );

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
     * Create a STATE command with the specified ID.
     * @param s the String ID of this command
     */
    public STATE( String s )
    {
	super( s );
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "STATE : returns the state of the telescope" );
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
 *    $Date: 2013-07-04 10:08:20 $
 * $RCSfile: STATE.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/STATE.java,v $
 *      $Id: STATE.java,v 1.3 2013-07-04 10:08:20 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
