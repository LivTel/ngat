package ngat.ngtcs.common;

/**
 * The abstract Status class which is to be sub-classed by all Status objects
 * returned by either a telescope PluggableSubSystem, or the Telescope itself.
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class Status implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Status.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Current State of the Status-returner.
     */
    protected State state = null;


    /**
     * Constructor.
     */
    public Status( State s )
    {
	state = s;
    }


    /**
     * Constructor.
     */
    public Status()
    {
    }


    /**
     * Return the current State of the Status-returner.
     */
    public State getState()
    {
	return state;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Status.java,v $
 *     $Log: not supported by cvs2svn $
 */
