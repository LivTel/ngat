package ngat.ngtcs.subsystem;

import ngat.ngtcs.*;
import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.2 $
 */
public class Timer extends BasicMechanism
  implements PluggableSubSystem
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Timer.java,v 1.2 2003-09-19 16:01:09 je Exp $" );


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
    protected long ticks = 0;

    /**
     *
     */
    protected long timestep;

    /**
     *
     */
    protected boolean emulate = false;

    /**
     *
     */
    protected boolean staticTime = false;


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
	super.initialise( t );

	active = true;
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
	long secOff = 0, nanOff = 0;

	if( emulate )
	    {
		long newNanosecs;

		if( staticTime )
		    {
			return new Timestamp( secs, nanosecs,
					      CalendarType.GREGORIAN,
					      TimescaleType.UTC  );
		    }
		else
		    {
			secOff = secsOffset;
			nanOff = nanoOffset;
		    }
	    }

	long millisecs;

	millisecs = ( System.currentTimeMillis() +
		      secOff * 1000 +
		      nanOff / 1000000 );

	secs = (long)Math.floor( (double)millisecs / 1000.0 );
	nanosecs = ( millisecs - ( secs * 1000 ) ) * 1000000;

	return new Timestamp( secs, nanosecs, CalendarType.GREGORIAN,
			      TimescaleType.UTC );
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
    public boolean makeSafe()
    {
	// do stuff
	return super.makeSafe();
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
    public void setTime( long newSecs, long newNanosecs )
    {
	secs = newSecs;
	nanosecs = newNanosecs;
	Timestamp t = getTime();
	secsOffset = secs - t.getSeconds();
	nanoOffset = nanosecs - t.getNanoseconds();
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
    public void setTimestep( long newTimestepNanoseconds )
    {
	timestep = newTimestepNanoseconds;
    }
}
/*
 *    $Date: 2003-09-19 16:01:09 $
 * $RCSfile: Timer.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/subsystem/Timer.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:46  je
 *     Initial revision
 *
 */
