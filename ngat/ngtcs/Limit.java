package ngat.ngtcs;

import ngat.ngtcs.common.*;

/**
 * 
 * 
 * @author $Author: je $ 
 * @version $Revision: 1.1 $
 */
public class Limit
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String RevisionString =
	new String( "$Id: Limit.java,v 1.1 2003-07-01 10:11:30 je Exp $" );

    /**
     * Timestamp of this Limit.
     */
    private Timestamp timestamp;

    /**
     * Whether this Limit defines a boundary where the target becomes
     * UNTRACKABLE.
     */
    private boolean trackable;

    /**
     * Usual constructor.
     * @param tstamp the Timestamp of this Limit
     * @param pos the position of this Limit
     */
    public Limit( Timestamp tstamp, boolean track )
    {
	timestamp = tstamp;
	trackable = track;
    }


    /**
     * Constructor used from Java Native Interface.
     * @param secs @see Timestamp#intSeconds
     * @param nanosecs @see Timestamp#intNanoseconds
     * @param x @see XYZMatrix#setX
     * @param y @see XYZMatrix#setY
     * @param z @see XYZMatrix#setZ
     */
    public Limit( long secs, long nanosecs, CalendarType calendar,
		  TimescaleType timescale, boolean track )
    {
	timestamp = new Timestamp( secs, nanosecs, calendar, timescale );
	trackable = track;
    }


    /**
     * Return the Timestamp for this Limit.
     * @return this Limit's Timestamp
     */
    public Timestamp getTimestamp()
    {
	return timestamp;
    }


    /**
     *
     */
    public boolean isTrackable()
    {
	return trackable;
    }
}
/*
 *    $Date: 2003-07-01 10:11:30 $
 * $RCSfile: Limit.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/Limit.java,v $
 *     $Log: not supported by cvs2svn $
 */
