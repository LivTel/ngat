package ngat.ngtcs.common;

import ngat.ngtcs.subsystem.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class TelescopeStatus
{
  /**
   * String used to identify RCS revision details.
   */
  public static final String rcsid =
    new String( "$Id$" );

  /**
   *
   */
  protected SoftwareState softwareState = null;

  /**
   * Operational mode.
   */
  protected OperationalMode operationalMode = null;

  /**
   * Telescope for which this is the Status.
   */
  protected String telescopeName = null;

  /**
   * Current Target set on this Telescope.
   */
  protected Target target = null;

  /**
   * Current MountState.
   */
  protected MountState mountState = null;

  /**
   *
   */
  protected ReportedTarget reported = null;


  /**
   * Constructor.
   * @param t the Telescope for which this is the Status
   */
  public TelescopeStatus( String name, SoftwareState ss, OperationalMode om,
			  Target t, ReportedTarget rt, MountState ms )
  {
    telescopeName = name;
    softwareState = ss;
    operationalMode = om;
    target = t;
    reported = rt;
    mountState = ms;
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
   * Return the MountState of the specified Mount
   * @return mountState
   */
  public MountState getMountState()
  {
    return mountState;
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
 *    $Date: 2013-07-04 10:47:14 $
 * $RCSfile: TelescopeStatus.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/TelescopeStatus.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
