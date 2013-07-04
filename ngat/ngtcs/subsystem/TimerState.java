package ngat.ngtcs.subsystem;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.1 $
 */
public class TimerState extends SystemState
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
    new String( "$Id: TimerState.java,v 1.1 2013-07-04 10:54:58 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   *
   */
  private boolean emulatedTime;;

  /**
   *
   */
  private boolean staticTime;

  /**
   *
   */
  private long offsetSeconds;

  /**
   *
   */
  private long offsetNanoseconds;

  /**
   *
   */
  private double frequency;

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
   *
   */
  public TimerState( boolean _emulatedTime, boolean _staticTime,
		     long _offsetSeconds, long _offsetNanoseconds,
		     double _frequency )
  {
    staticTime = _staticTime;
    emulatedTime = _emulatedTime;
    offsetSeconds = _offsetSeconds;
    offsetNanoseconds = _offsetNanoseconds;
    frequency = _frequency;
  }


  /**
   *
   */
  public boolean getEmulated()
  {
    return( emulatedTime );
  }


  /**
   *
   */
  public boolean getStatic()
  {
    return( staticTime );
  }


  /**
   *
   */
  public long getOffsetSeconds()
  {
    return( offsetSeconds );
  }


  /**
   *
   */
  public long getOffsetNanoseconds()
  {
    return( offsetNanoseconds );
  }


  /**
   *
   */
  public double getTriggerFrequency()
  {
    return( frequency );
  }
}
/*
 *    $Date: 2013-07-04 10:54:58 $
 * $RCSfile: TimerState.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/TimerState.java,v $
 *      $Id: TimerState.java,v 1.1 2013-07-04 10:54:58 cjm Exp $
 *     $Log: not supported by cvs2svn $
 */
