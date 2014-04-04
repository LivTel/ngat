package ngat.ngtcs.command;
 
 
 
/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 *
 *    $Date: 2003-09-26 12:10:06 $
 * $RCSfile: TIME.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/command/TIME.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:12:39  je
 *     Initial revision
 *
 */
public class TIME extends Command
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id$" );


    protected long nanosecs;

    protected long secs;

    protected long timestep;

    protected boolean staticTime = false;


    public TIME( String commandPath, boolean newStaticTime,
		 long newSecs, long newNanosecs, long newTimestep )
    {
	super( commandPath );
	staticTime = newStaticTime;
	nanosecs = newNanosecs;
	secs = newSecs;
	timestep = newTimestep;
    }


    /**
     *
     */
    public long getSeconds()
    {
	return secs;
    }


    /**
     *
     */
    public long getNanoseconds()
    {
	return nanosecs;
    }


    /**
     *
     */
    public boolean isStaticTime()
    {
	return staticTime;
    }


    /**
     *
     */
    public long getTimestep()
    {
	return timestep;
    }


    /**
     * Return a decription of how to use this command.
     */
    public String getHelp()
    {
	return( "TIME emulate-boolean secs nanosecs incr" );
    }


    /**
     * Returns the arguments of this Command as a String.
     * @return the argument String
     */
    protected String getArgString()
    {
	return( "\t    seconds = "+secs+"\n"+
		"\tnanoseconds = "+nanosecs+"\n"+
		"\tstatic time = "+staticTime+"\n"+
		"timestep (ns) = "+timestep );
	
    }
}
