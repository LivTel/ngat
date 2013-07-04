package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public abstract class Rotator extends BasicMechanism
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
    new String( "$Id: Rotator.java,v 1.2 2013-07-04 10:54:46 cjm Exp $" );


  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Rotator angle on the mount.
   */
  protected double currentPositionAngle = 0.0;

  /**
   * Previous rotator angle on the mount.
   */
  protected double oldPositionAngle = 0.0;

  /**
   * Previous Timestamp of previous rotator position.
   */
  protected Timestamp currentRotatorTimestamp =
    new Timestamp( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );

  /**
   * Previous Timestamp of previous rotator position.
   */
  protected Timestamp oldRotatorTimestamp =
    new Timestamp( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );

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
   * Constructor for Rotators.
   */
  public Rotator()
  {
    super();
  }


  /**
   * Returns the rotator position angle.
   * @return the rotator position angle
   */
  public double getPositionAngle()
  {
    return currentPositionAngle;
  }


  /**
   *
   */
  public void sendPositionDemand( double demand )
  {
    oldPositionAngle = currentPositionAngle;
    currentPositionAngle = demand;
  }

  /**
   * Send the specified position demand to the Mount Control System.
   * @param timePos the Timestamped position demand
   */
  public void sendRotatorDemand( double currentPA, Timestamp rotTimestamp )
  {
	
    oldPositionAngle = currentPositionAngle;
    oldRotatorTimestamp = currentRotatorTimestamp;
    currentPositionAngle = currentPA;
    currentRotatorTimestamp = rotTimestamp;
  }


  /**
   *
   */
  public double predictPositionAngle( Timestamp tStamp )
  {
    // if current timestamp older than X
    // get update for pos rot and T

    double oldTime = oldRotatorTimestamp.getTime();
    double timeDiff = currentRotatorTimestamp.getTime() - oldTime;
    double angleDiff = currentPositionAngle - oldPositionAngle;

    double predicted = ( oldPositionAngle + 
			 ( ( tStamp.getTime() - oldTime ) / timeDiff )
			 * angleDiff );

    return predicted;
  }


}
/*
 *    $Date: 2013-07-04 10:54:46 $
 * $RCSfile: Rotator.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Rotator.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
