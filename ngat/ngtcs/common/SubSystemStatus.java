package ngat.ngtcs.common; 

/**
 * The generic SubSystemStatus class - used only for testing
 *
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class SubSystemStatus extends Status
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: SubSystemStatus.java,v 1.1 2003-07-01 10:13:04 je Exp $" );


    /**
     * Constructor.
     */
    public SubSystemStatus( State s )
    {
	super( s );
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
 * $RCSfile: SubSystemStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/SubSystemStatus.java,v $
 *     $Log: not supported by cvs2svn $
 */
