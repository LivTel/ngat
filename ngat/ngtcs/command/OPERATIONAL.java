package ngat.ngtcs.command;

/**
 * This command is effectively the ON/OFF command for the entire telescope
 * system.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class OPERATIONAL extends ngat.ngtcs.command.Command
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
	new String( "$Id: OPERATIONAL.java,v 1.1 2003-09-19 16:09:49 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * <code>boolean</code> Of whether this OPERATIONAL command is to activate
     * or deactivate the telescope subsystem.
     */
    protected boolean on = false;

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
 *    $Date: 2003-09-19 16:09:49 $
 * $RCSfile: OPERATIONAL.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/OPERATIONAL.java,v $
 *      $Id: OPERATIONAL.java,v 1.1 2003-09-19 16:09:49 je Exp $
 *     $Log: not supported by cvs2svn $
 */
