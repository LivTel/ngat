package ngat.ngtcs.command;
 
/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class SUBSYSTEMLIST extends Command
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );



    /**
     *
     */
    public SUBSYSTEMLIST( String commandPath )
    {
	super( commandPath );
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "SUBNSYSTEMLIST : "+
		"returns all PluggableSubSystems on this system" );
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
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: SUBSYSTEMLIST.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/SUBSYSTEMLIST.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
