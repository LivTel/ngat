package ngat.ngtcs.command;

/**
 * This command will shutdown the sub-system with the specified name.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SHUTDOWN extends Command
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
	new String( "$Id: SHUTDOWN.java,v 1.1 2003-07-01 10:12:39 je Exp $" );

    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Name of the Telescope or PluggableSubSystem to shut down.
     */
    private String systemName = null;

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
     * Create a SHUTDOWN command with the specifed ID, to shutdown the
     * sub-system with the specified name.
     * @param s1 the String ID of this command
     * @param s2 the name of the sub-system to shutdown
     */
    public SHUTDOWN( String s1, String s2 )
    {
	super( s1 );
	systemName = s2;
    }


    /**
     * Get the name of the mechanism/telescope to be shut down
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
	return( "SHUTDOWN <telescope|mechanism-name>" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "\tshutting down : "+systemName );
    }
}
/*
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: SHUTDOWN.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SHUTDOWN.java,v $
 *      $Id: SHUTDOWN.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */
