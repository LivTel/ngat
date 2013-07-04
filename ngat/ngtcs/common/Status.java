package ngat.ngtcs.common;

/**
 * The abstract Status class which is to be sub-classed by all Status objects
 * returned by either a telescope PluggableSubSystem, or the Telescope itself.
 *
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class Status implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: Status.java,v 1.2 2013-07-04 10:46:13 cjm Exp $" );


    /**
     * Current State of the Status-returner.
     */
    protected SubSystemState subSystemState = null;


    /**
     * Current Software State of the Status-returner.
     */
    protected SoftwareState softwareState = null;


    /**
     * Constructor.
     */
    public Status( SubSystemState s )
    {
	subSystemState = s;
    }


    /**
     * Constructor.
     */
    public Status( SoftwareState s )
    {
	softwareState = s;
    }


    /**
     * Constructor.
     */
    public Status()
    {
    }


    /**
     * Constructor.
     */
    public Status( SubSystemState sss, SoftwareState ss )
    {
      subSystemState = sss;
      softwareState = ss;
    }


    /**
     * Return the current State of the Status-returner.
     */
    public SoftwareState getSoftwareState()
    {
	return softwareState;
    }


    /**
     * Return the current State of the Status-returner.
     */
    public SubSystemState getSubSystemState()
    {
	return subSystemState;
    }
}
/*
 *    $Date: 2013-07-04 10:46:13 $
 * $RCSfile: Status.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Status.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
