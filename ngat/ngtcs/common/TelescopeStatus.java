package ngat.ngtcs.common;

import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TelescopeStatus extends Status
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: TelescopeStatus.java,v 1.1 2003-07-01 10:13:04 je Exp $" );

    /**
     * Telescope state.
     */
    private TelescopeState telescopeState = null;

    /**
     * Telescope for which this is the Status.
     */
    private String telescopeName = null;

    /**
     * Current Target set on this Telescope.
     */
    private Target target = null;

    /**
     * Current MountStatus.
     */
    private MountStatus mountStatus = null;

    /**
     *
     */
    private ReportedTarget reported = null;


    /**
     * Constructor.
     * @param t the Telescope for which this is the Status
     */
    public TelescopeStatus( String name, SoftwareState ss, TelescopeState ts,
			    Target t, ReportedTarget rt, MountStatus ms )
    {
	super( ss );
	telescopeState = ts;
	telescopeName = name;
	target = t;
	reported = rt;
	mountStatus = ms;
    }


    /**
     * Return the current target for the specified Telescope.
     * @return target
     */
    public Target getTarget()
    {
	return target;
    }


    /**
     * Return the MountStatus of the specified Mount
     * @return mountStatus
     */
    public MountStatus getMountStatus()
    {
	return mountStatus;
    }


    /**
     * Return the ReportedTarget of the current mount position.
     * @return reported
     */
    public ReportedTarget getRportedTarget()
    {
	return reported;
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: TelescopeStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TelescopeStatus.java,v $
 *     $Log: not supported by cvs2svn $
 */
