package ngat.ngtcs.command;

/**
 * Return a list of the names of ALL the loggers registered with the Telescope
 * Control System. The name of the logger is used in (re)setting the log-level
 * for that logger.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class LOGLIST extends Command
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: LOGLIST.java,v 1.1 2003-07-01 10:12:39 je Exp $" );



    /**
     * Initialise a LOGLIST Command with the specified String ID.
     * @param s the String ID of this command
     */
    public LOGLIST( String s )
    {
	super( s );
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "LOGLIST : returns all Loggers on this system" );
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
 *    $Date: 2003-07-01 10:12:39 $
 * $RCSfile: LOGLIST.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/LOGLIST.java,v $
 *      $Id: LOGLIST.java,v 1.1 2003-07-01 10:12:39 je Exp $
 *     $Log: not supported by cvs2svn $
 */
