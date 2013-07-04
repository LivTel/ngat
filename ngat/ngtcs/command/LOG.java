package ngat.ngtcs.command;
 
/**
 * The LOG Command is used to change the log-level of a specified logger.
 * <br>
 * Use of this command can localise loging to a high degree so that specific
 * sub-systems can log all possible information while other systems log brief
 * messages only, or none at all.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class LOG extends Command
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
	new String( "$Id: LOG.java,v 1.3 2013-07-04 10:06:57 cjm Exp $" );

    /*=========================================================================*/
    /*                                                                         */
    /* OBJECT FIELDS.                                                          */
    /*                                                                         */
    /*=========================================================================*/

    /**
     * The name of the logger to adjust.
     */
    protected String logName;

    /**
     * The log level to be set.
     */
    protected int logLevel;

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
     * Create a LOG command with the specified ID, for the Logger with the
     * specified name, at the specified log-level
     * @param s1 the String ID of this command
     * @param s2 the name of the logger to adjust
     * @param i the new log-level to set
     */
    public LOG( String s1, String s2, int i )
    {
	super( s1 );
	logName = s2;
	logLevel = i;
    }


    /**
     * Return the String of logger name. 
     * @return logName
     * @see #logName
     */
    public String getLogName()
    {
	return logName;
    }


    /**
     * Return the log level to be set.
     * @return logLevel
     * @see #logLevel
     */
    public int getLogLevel()
    {
	return logLevel;
    }
    

    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "LOG logger logLevel" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( " logName = "+logName+"\nlogLevel = "+logLevel );
    }
}
/*
 *    $Date: 2013-07-04 10:06:57 $
 * $RCSfile: LOG.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/LOG.java,v $
 *      $Id: LOG.java,v 1.3 2013-07-04 10:06:57 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/26 12:10:06  je
 *     Changed 'RevisionString' to 'rcsid' for ident command.
 *
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
