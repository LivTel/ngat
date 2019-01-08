package ngat.ngtcs.command;

/**
 * This command will reset the specified
 * <code><b><i>PluggableSubSystem</i></b></code>, or the whole Telescope
 * system, depending on the system name argument.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class RESET extends Command
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

    /**
     * The mechanism to be reset - null for all.
     */
    protected String systemName;

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
     * Create a RESET command with the specified ID, to reset the sub-system
     * with the specified name.
     * @param s1 the String ID of this command
     * @param s2 the name of the sub-system to reset
     */
    public RESET( String s1, String s2 )
    {
	super( s1 );
	systemName = s2;
    }


    /**
     * Return the String of the mechanism to be reset
     * @return systemName
     * @see #systemName
     */
    public String getSystemName()
    {
	return systemName;
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "RESET mechanism-name" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "\tresetting : "+systemName );
    }
}
/*
 *    $Date: 2013-07-04 10:07:20 $
 * $RCSfile: RESET.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/RESET.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
