package ngat.ngtcs.subsystem;

import ngat.util.logging.*;
import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.3 $
 */
public class Timer implements PluggableSubSystem
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
    new String( "$Id: Timer.java,v 1.3 2013-07-04 10:54:56 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Name of this PluggableSubSystem's logger.
   */
  protected String logName = null;

  /**
   * logger Object used by this mechanism.
   */
  protected Logger logger = null;

  /**
   * Trigger period in nanoseconds.
   */
  protected long period;

  /**
   * Trigger frequency in Hertz.
   */
  protected double frequency;

  /**
   *
   */
  protected long secs;

  /**
   *
   */
  protected long nanosecs;

  /**
   *
   */
  protected long secsOffset;

  /**
   *
   */
  protected long nanoOffset;

  /**
   *
   */
  protected boolean emulate = false;

  /**
   *
   */
  protected boolean staticTime = false;

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
   * Constructor for Timer objects.
   */
  public Timer()
  {
    super();
  }


  /**
   *
   */
  public void initialise( Telescope t ) throws InitialisationException
  {
    if( logger == null )
    {
      logger = t.getLogger( this.getClass() );
      logName = logger.getName();
    }
    logger.log( 2, logName, "Logging for "+logName+" on "+logger );
    return;
  }


  /**
   *
   */
  public void setFrequency( double newFrequency )
  {
    frequency = newFrequency;
    period    = (long)( 1000.0 / frequency );
  }


  /**
   *
   */
  public Timestamp trigger()
  {
    try
    {
      Thread.sleep( (int)period );
    }
    catch( InterruptedException ie )
    {
      logger.log( 1, logName, ie );
    }
    return getTime();
  }


  /**
   * Return the current time as a Timestamp
   */
  public Timestamp getTime()
  {
    if( staticTime )
      return new Timestamp
	( secs, nanosecs, CalendarType.GREGORIAN, TimescaleType.UTC );

    long millisecs = ( System.currentTimeMillis() );
    long _secs = (long)Math.floor( (double)millisecs / 1000.0 );
    long _nanosecs = ( millisecs - ( secs * 1000 ) ) * 1000000;

    if( emulate )
    {
      secs += secsOffset;
      nanosecs += nanoOffset;
    }

    return new Timestamp
      ( secs, nanosecs, CalendarType.GREGORIAN, TimescaleType.UTC );
  }


  /**
   * Return the trigger frequency of this Timer.
   * @return the trigger frequency of this Timer
   */
  public double getTriggerFrequency()
  {
    return frequency;
  }


  /**
   * Return the trigger period of this Timer in nanoseconds.
   * @return the trigger period of this Timer in nanoseconds
   */
  public long getTriggerPeriod()
  {
    return period;
  }


  /**
   *
   */
  public void setEmulate( boolean newEmulate )
  {
    emulate = newEmulate;
  }


  /**
   *
   */
  public void setStatic( boolean newStaticTime )
  {
    staticTime = newStaticTime;
  }


  /**
   *
   */
  public void offsetTimeBy( long newSecs, long newNanosecs )
  {
    emulate = true;
    secsOffset = newSecs;
    nanoOffset = newNanosecs;
  }


  /**
   *
   */
  public void offsetTimeTo( Timestamp t )
  {
    emulate = true;
    long millisecs = ( System.currentTimeMillis() );
    long _secs = (long)Math.floor( (double)millisecs / 1000.0 );
    long _nanosecs = ( millisecs - ( secs * 1000 ) ) * 1000000;
    secsOffset = secs - t.getSeconds();
    nanoOffset = nanosecs - t.getNanoseconds();
  }


  /**
   *
   */
  public SystemState getState()
  {
    return( (SystemState)( new TimerState( emulate, staticTime, secsOffset,
					   nanoOffset, frequency ) ) );
  }


  /**
   *
   */
  public void makeSafe()
    throws SystemException
  {
    return;
  }


  /**
   *
   */
  public void setLogLevel( int newLogLevel )
  {
    logger.setLogLevel( newLogLevel );
  }
}
/*
 *    $Date: 2013-07-04 10:54:56 $
 * $RCSfile: Timer.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Timer.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.2  2003/09/19 16:01:09  je
 *     Updated Command tx/rx and TTL subsystem interfaces.
 *
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
