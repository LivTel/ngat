package ngat.ngtcs.subsystem;

import java.util.List;
import java.util.Vector;

import ngat.ngtcs.common.Target;
import ngat.ngtcs.common.SiteData;
import ngat.ngtcs.subsystem.acn.ACN;
import ngat.ngtcs.subsystem.acn.ACN_NodeType;

/**
 * This class encapsulates the TTL mechanisms that are required to provide the
 * functionality specified by the class
 * <code>ngat.ngtcs.subsystem.Mount</code>.
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class TTL_Mount extends Mount implements PluggableSubSystem
{
  /*=========================================================================*/
  /*                                                                         */
  /* CLASS FIELDS.                                                           */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * String used to identify RCS revision details.
   */
  public static final String RevisionString =
    new String( "$Id: TTL_Mount.java,v 1.1 2003-09-19 16:01:09 je Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Azimuth Axis Control Node.
   */
  protected ACN azimuth = null;

  /**
   * Altitude Axis Control Node.
   */
  protected ACN altitude = null;

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
   * Basic constructor.
   * This constructor assigns the axis control node objects for the altitude
   * and azimuth axes.
   */
  public TTL_Mount()
  {
    azimuth  = new ACN( ACN_NodeType.E_ACN_NODE_AZN );
    altitude = new ACN( ACN_NodeType.E_ACN_NODE_ELN );
  }


  /**
   * Implement the PluggableSubSystem method.
   * This initialisation initialises the TTL Axis Control Nodes
   * (<code>ACN</code>) for each mount axis.  In this case, for an AltAz
   * TTL_Mount, the axes are altitude and azimuth.
   */
  public void initialise( ngat.ngtcs.Telescope t )
    throws ngat.ngtcs.InitialisationException
  {
    super.initialise( t );

    //azimuth.initialise( t );
    //altitude.initialise( t );
  }


  /**
   * Return a List of the time to tracking limits, and what limits they are,
   * i.e.<br>
   * <code>TrackingLimit.ZENITH_BLIND_SPOT_ENTER</code> or
   * <code>TrackingLimit.CLOCKWISE_WRAP_LIMIT</code>.
   */
  public List calculateLimits( SiteData siteData, Target target )
  {
    return( new Vector() );
  }


  /**
   * Return the type of mount that this is, i.e. AltAz.
   * @return the ONLY instance of MountType.ALTAZ as it is a type-safe
   * enumeration.
   */
  public MountType getType()
  {
    return MountType.ALTAZ;
  }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: TTL_Mount.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TTL_Mount.java,v $
 *      $Id: TTL_Mount.java,v 1.1 2003-09-19 16:01:09 je Exp $
 *     $Log: not supported by cvs2svn $
 */
