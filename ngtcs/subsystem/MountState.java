package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class MountState extends SystemState
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
    new String( "$Id$" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Mount for which this is the State.
   */
  protected String mount = null;

  /**
   * Current MountState.
   */
  protected MountState mountState = null;

  /**
   * Current position of this Mount.
   */
  protected XYZMatrix currentPos = null;

  /**
   * The Rotator object used on the specified Mount.
   */
  protected double rotatorAngle = 0.0;

  /**
   * Timestamp of the `current' positions.
   */
  protected Timestamp timestamp = null;


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Constructor.
   */
  public MountState( MountState ms, Timestamp t, XYZMatrix pos )
  {
    super();
    mountState = ms;
    currentPos = pos;
    timestamp = t;
  }


  /**
   * Return the current position of the Mount as an XYZMatrix.
   * @return currentPos
   */
  public XYZMatrix getPosition()
  {
    return currentPos;
  }


  /**
   * Return the current rotator angle.
   * @return rotatorAngle
   */
  public double getRotatorAngle()
  {
    return rotatorAngle;
  }


  /**
   * Return the Timestamp
   * @return timestamp
   */
  public Timestamp getTimestamp()
  {
    return timestamp;
  }
}
/*
 *    $Date: 2013-07-04 10:54:26 $
 * $RCSfile: MountState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/MountState.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
