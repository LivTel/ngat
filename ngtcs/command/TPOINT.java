package ngat.ngtcs.command;
 
 
 
/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */

/*
    $Date: 2003-09-26 12:10:06 $
 $RCSfile: TPOINT.java,v $
  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/TPOINT.java,v $
     $Log: not supported by cvs2svn $
     Revision 1.1  2003/07/01 10:12:39  je
     Initial revision

*/

public class TPOINT extends Command
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );

    /**
     *
     */
    protected String file;

    /**
     *
     */
    public TPOINT( String commandPath, String inFile )
    {
	super( commandPath );
	file = inFile;
    }

    /**
     *
     */
    public String getInputFile()
    {
	return file;
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "TPOINT input-file-name" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "\tinput file : "+file );
    }
}
