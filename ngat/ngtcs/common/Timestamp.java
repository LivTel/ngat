package ngat.ngtcs.common;

/**
 * 
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class Timestamp implements java.io.Serializable
{
    /**
     * String used to identify RCS revision details.
     */
    public static final String rcsid =
	new String( "$Id: Timestamp.java,v 1.2 2013-07-04 10:47:35 cjm Exp $" );

    /**
     * Integer number of whole seconds since 01/01/1970.
     */
    protected long intSeconds;

    /**
     * Integer number of nanoseconds through current second.
     */
    protected long intNanoseconds;

    /**
     * Timestamp as a double in seconds since 01/01/1970.
     */
    protected double doubleSeconds;

    /**
     * Calendar used for this Timestamp.
     */
    protected CalendarType calendar;

    /**
     * Timescale of this Timestamp.
     */
    protected TimescaleType timescale;

    /**
     * Constructor for Timestamp objects.
     */
    public Timestamp()
    {
      this( 0, 0, CalendarType.GREGORIAN, TimescaleType.UTC );
    }


    /**
     * Constructor for Timestamp objects.
     * @param secs the integer number of whole seconds since 01/01/1970
     * @param nanosecs the integer number of nanoseconds through current second
     */
    public Timestamp( long secs, long nanosecs, CalendarType cal, 
		      TimescaleType ts )
    {
	intSeconds = secs;
	intNanoseconds = nanosecs;
	doubleSeconds = (double)secs + ( (double)nanosecs / 1.0E+09 );
	calendar = cal;
	timescale = ts;
    }


    /**
     * Return the integer number of whole seconds since 01/01/1970 for this
     * timestamp.
     * @return integer number of whole seconds since 01/01/1970 for this 
     * timestamp
     */
    public long getSeconds()
    {
	return intSeconds;
    }


    /**
     * Return the integer number of nanoseconds through current second for this
     * timestamp.
     * @return integer number of nanoseconds through current second
     */
    public long getNanoseconds()
    {
	return intNanoseconds;
    }


    /**
     * Return the precise number of seconds since 01/01/1970.
     * @return number of seconds since 1970 as a double
     */
    public double getTime()
    {
	return doubleSeconds;
    }


    /**
     * Return the Calendar used by this Timestamp.
     * @return the Calendar used for this Timestamp.
     */
    public CalendarType getCalendarType()
    {
	return calendar;
    }


    /**
     * Return the Timescale used by this Timestamp.
     * @return the Timescale used for this Timestamp.
     */
    public TimescaleType getTimescaleType()
    {
	return timescale;
    }


    /**
     *
     *
     */
    public String toString()
    {
	return( intSeconds+" seconds,  "+intNanoseconds+" nanoseconds." );
    }
}
/*
 *    $Date: 2013-07-04 10:47:35 $
 * $RCSfile: Timestamp.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/Timestamp.java,v $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
