package ngat.ngtcs.command;

/**
 * Super-class of ALL NGTCS commands.  This class holds the top-level values
 * used by every command.
 *
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public abstract class Command extends ngat.message.base.COMMAND
{
    /*=======================================================================*/
    /*                                                                       */
    /* CLASS FIELDS.                                                         */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: Command.java,v 1.2 2003-09-26 12:10:06 je Exp $" );

    /**
     * Total number of commands sent.
     */
    protected static long totalCommandNumber = 0;


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT FIELDS.                                                        */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * Number of this command.
     */
    protected long commandNumber = 0;


    /*=======================================================================*/
    /*                                                                       */
    /* CLASS METHODS.                                                        */
    /*                                                                       */
    /*=======================================================================*/


    /**
     * Return the total number of <code>Command</code>s.
     */
    public static long getTotalCommandNumber()
    {
	return totalCommandNumber;
    }


    /*=======================================================================*/
    /*                                                                       */
    /* OBJECT METHODS.                                                       */
    /*                                                                       */
    /*=======================================================================*/

    /**
     * The command constructor.  This constructor sets the id of the command.
     */
    public Command( String s )
    {
	super( s );
	commandNumber = ++totalCommandNumber;
    }


    /**
     * This method returns the class name and the arguments for logging.
     * @see #getArgString
     * @return the command name and arguments as a String
     */
    public String getArgumentString()
    {
	return( this.getClass().getName()+"\n"+getArgString() );
    }


    /**
     * This method must be implemented by <i>every</i> sub-class to display 
     * their command-specific arguments as a String.
     * @return the arguments as a String
     */
    //
    // THIS SHOULD BE ABSTRACT TO BE IMPLEMENTED BY EVERY COMMAND
    //
    //    protected abstract String getArgString();
    protected String getArgString()
    {
	return "args...";
    }


    /**
     * This method must be implemented by <i>every</i> sub-class to display
     * command-specific help, e.g. arguments and their allowable values.
     * @return a description of how to use this command
     */
    //
    // THIS SHOULD BE ABSTRACT TO BE IMPLEMENTED BY EVERY COMMAND
    //
    //    public abstract String getHelp();
    public String getHelp()
    {
	return "help string...";
    }

    /**
     * Return the number of this <code>Command</code>s.
     * @return commandNumber
     * @see #commandNumber
     */
    public long getCommandNumber()
    {
	return commandNumber;
    }


    /**
     * This method over-writes the ngat.ngtcs.command.Command.getId() method to
     * add the class name as part of the ID
     * @return "id-<i>command_name</i> where command_name is the subclass
     */
    public String getId()
    {
	return( super.getId()+"-"+ngat.util.StringUtilities.getLeaf
		( this.getClass().getName(), '.' ) );
    }
}
/*
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: Command.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/Command.java,v $
 *      $Id: Command.java,v 1.2 2003-09-26 12:10:06 je Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
