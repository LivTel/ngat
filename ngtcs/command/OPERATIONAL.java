package ngat.ngtcs.command;

/**
 * This command is effectively the ON/OFF command for the entire telescope
 * system.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class OPERATIONAL extends ngat.ngtcs.command.Command
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
     * <code>boolean</code> Of whether this OPERATIONAL command is to activate
     * or deactivate the telescope subsystem.
     */
    protected boolean on = false;

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
     * Construct an OPERATIONAL command with the specified ID, to either
     * activate or deactivate the telescope system. A boolean of
     * <code>true</code> implies system activation.
     * @param s the String ID of this command
     * @param b whether this command should <b>activate</b> the system
     * @see #on
     */
    public OPERATIONAL( String s, boolean b )
    {
	super( s );
	on = b;
    }


    /**
     * Return whether this command is to activate the telescope system or not.
     * @return on
     * @see #on
     */
    public boolean isOn()
    {
	return on;
    }
}
/*
 *    $Date: 2013-07-04 10:07:09 $
 * $RCSfile: OPERATIONAL.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/OPERATIONAL.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/09/19 16:09:49  je
 *     Initial revision
 *
 */
