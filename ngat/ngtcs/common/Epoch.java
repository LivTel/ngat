package ngat.ngtcs.common;

import java.lang.Double;
import java.lang.String;

/**
 * This class represents a specific Epoch in time, with a date, timescale and
 * calendar.
 * <p>
 * @see ngat.ngtcs.common.TimescaleType
 * @see ngat.ngtcs.common.CalendarType
 * @version $Revision: 1.1 $
 * @author $Author: je $
 */
public class Epoch implements java.io.Serializable
{
    /**
     * Decimal year of this Epoch.
     */
    private double date = 2000.0;

    /**
     * Calendar system of this Epoch.
     * @see ngat.ngtcs.common.CalendarType
     */
    private CalendarType calendar = CalendarType.JULIAN;

    /**
     * Timescale of this Epoch.
     * @see ngat.ngtcs.common.TimescaleType
     */
    private TimescaleType timescale = TimescaleType.TDB;

    /**
     * Minimum acceptable date for this Epoch
     */
    private final double MIN_DATE = 0.0;

    /**
     * Maximum acceptable date for this Epoch
     */
    private final double MAX_DATE = 9999.0;


    /**
     * Epoch constructor takes Calendar, date and Timescale arguments.
     * @see #calendar
     * @see #date
     * @see #timescale
     * @param cal
     * @param date
     * @param tscale
     */
    public Epoch( CalendarType cal, double newDate, TimescaleType tscale )
    {
	if( !isValid( newDate ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newDate+"] out of range for Epoch date : "+
		      MIN_DATE+" .. "+MAX_DATE );
	    }
	date = newDate;
	this.calendar = cal;
	this.timescale = tscale;
    }

    /**
     * Epoch constructor takes Calendar and date arguments with the default
     * Timescale of ??????? applied.
     * @see #calendar
     * @see #date
     * @param cal
     * @param date
     */
    public Epoch( CalendarType cal, double newDate )
    {
	if( !isValid( newDate ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newDate+"] out of range for Epoch date : "+
		      MIN_DATE+" .. "+MAX_DATE );
	    }
	date = newDate;
	calendar = cal;
    }

    /**
     * Epoch constructor takes date argument with the default Calendar and
     * Timescale of ??????? and ??????? applied.
     * @see #date
     * @param date
     */
    public Epoch( double newDate )
    {
	if( !isValid( newDate ) )
	    {
		throw new IllegalArgumentException
		    ( "value ["+newDate+"] out of range for Epoch date : "+
		      MIN_DATE+" .. "+MAX_DATE );
	    }
	date = newDate;
    }

    /**
     * Return the Calendar system of this Epoch.
     * @return this Epoch's Calendar
     * @see #calendar
     */
    public CalendarType getCalendarType()
    {
	return calendar;
    }

    /**
     * Return the decimal date of this Epoch.
     * @return date
     * @see #date
     */
    public double getDate()
    {
	return date;
    }

    /**
     * Return the Timescale of this Epoch.
     * @return this Epoch's Timescale
     * @see #timescale
     */
    public TimescaleType getTimescaleType()
    {
	return timescale;
    }

    /**
     * Check the date is in range.
     * @return boolean describing if the date is in range
     * @see #date
     */
    private boolean isValid( double date )
    {
	if( ( date < MIN_DATE )||( date > MAX_DATE ) )
	{
	    return false;
	}
        return true;
    }


    /**
     *
     */
    public String toString()
    {
	return( calendar.getName()+" "+date+" "+timescale.getName() );
    }
}
/*
 *    $Date: 2003-07-01 10:13:04 $
 * $RCSfile: Epoch.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Epoch.java,v $
 *      $Id: Epoch.java,v 1.1 2003-07-01 10:13:04 je Exp $
 *     $Log: not supported by cvs2svn $
 */