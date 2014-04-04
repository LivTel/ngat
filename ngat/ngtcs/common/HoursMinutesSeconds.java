package ngat.ngtcs.common;

import java.text.*;

/**
 * Container class for an Hours:Minutes:Seconds object as used by either time
 * variables or angle variables.
 * <br>
 * This class is non-mutable.
 * 
 * @author $Author$ 
 * @version $Revision$
 */
public class HoursMinutesSeconds
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
   * Number of hours.
   */
  protected int hours;

  /**
   * Number of minutes.
   */
  protected int minutes;

  /**
   * Number of seconds.
   */
  protected int seconds;

  /**
   * Number of milliseconds.
   */
  protected int millis;

  /**
   * This Right Ascension in decimal degrees.
   */
  protected double decimalDegrees;

  /**
   * This Right Ascension is radians.
   */
  protected double radians;

  /**
   * NumberFormatter for hours.
   */
  protected DecimalFormat hdf = new DecimalFormat( " 00;-00" );

  /**
   * NumberFormatter for minutes and seconds.
   */
  protected DecimalFormat df = new DecimalFormat( "00" );

  /**
   * NumberFormatter for milliseconds.
   */
  protected DecimalFormat msdf = new DecimalFormat( "000" );

  /*=========================================================================*/
  /*                                                                         */
  /* CLASS METHODS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create a new HoursMinutesSeconds object, calculated from the
   * specified radians.
   * @param d the input angle in radians
   * @return a new HoursMinutesSeconds object representation of d
   */
  public static HoursMinutesSeconds radiansToHMS( double d )
  {
    return radiansToHMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified radians.
   * @param d the input angle in radians
   * @param b whether to range the hours from 0.0 - 23.9
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static HoursMinutesSeconds radiansToHMS( double d, boolean b )
  {
    return arcsecondsToHMS( ( ( d * 180.0 * 3600.0 ) / Math.PI ), b );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified timestamp.
   * @param d the input angle in radians
   * @return a new DegreesMinutesSeconds object representation of d
   *
   */
  public static HoursMinutesSeconds timestampToHMS( Timestamp t )
  {
    return secondsToHMS( t.getTime(), true );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified arcseconds.
   * @param d the input angle in radians
   * @return a new DegreesMinutesSeconds object representation of d
   *
   */
  public static HoursMinutesSeconds arcsecondsToHMS( double d )
  {
    return arcsecondsToHMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified arcseconds.
   * @param d the input angle in radians
   * @param b whether to range the hours from 0.0 - 23.9
   * @return a new DegreesMinutesSeconds object representation of d
   *
   */
  public static HoursMinutesSeconds arcsecondsToHMS( double d, boolean b )
  {
    return secondsToHMS( ( d / 15.0 ), b );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified seconds.
   * @param d the input angle in radians
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static HoursMinutesSeconds secondsToHMS( double d )
  {
    return secondsToHMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified seconds.
   * @param d the input angle in radians
   * @param b whether to range the hours from 0.0 - 23.9
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static HoursMinutesSeconds secondsToHMS( double d, boolean b )
  {
    int sign = 1;
    double milliSecs = d * 1000.0;

    if( b )
    {
      while( milliSecs < 0.0 )
      {
	milliSecs += 86400000.0;
      }
    }

    if( milliSecs < 0.0 )
    {
      sign = -1;
    }

    int absDecimalInt = (int)( Math.abs( Math.rint( milliSecs ) ) );
    int millisInt = ( absDecimalInt ) % 1000;
    int allSeconds = ( absDecimalInt - millisInt ) / 1000;
    int secondsInt = allSeconds % 60;
    int allMinutes = ( allSeconds - secondsInt ) / 60;
    int minutesInt = allMinutes % 60;
    int hoursInt = ( ( allMinutes - minutesInt ) / 60 ) % 24;

    return new HoursMinutesSeconds
      ( sign*hoursInt, minutesInt, secondsInt, millisInt );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create a new HoursMinutesSeconds object with the specified hours,
   * minutes and seconds, with the milliseconds set to zero.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   */
  public HoursMinutesSeconds( int h, int m, int s )
  {
    this( h, m, s, 0 );
  }


  /**
   * Create a new HoursMinutesSeconds object with the specified hours,
   * minutes and seconds.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   */
  public HoursMinutesSeconds( int h, int m, double s )
  {
    this( h, m, (int)s, ( (int)( s * 1000.0 ) ) % 1000 );
  }


  /**
   * Create a new HoursMinutesSeconds object with the specified hours,
   * minutes, seconds, and milliseconds.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   * @param ms milliseconds
   */
  public HoursMinutesSeconds( int h, int m, int s, int ms )
  {
    double sign = 1.0000000;

    hours = h;
    minutes = m;
    seconds = s;
    millis = ms;

    if( hours < 0 ) sign = -1.0000000;

    decimalDegrees =
      ( ( (double)millis / 1000.0 + (double)seconds ) / 60.0 +
	(double)minutes ) / 60.0 + (double)( Math.abs( hours ) ) * sign * 15.0;

    radians = Math.toRadians( decimalDegrees );
  }


  /**
   * Return the hours.
   * @return hours
   * @see #hours
   */
  public int getHours()
  {
    return hours;
  }


  /**
   * Return the hours as a double digit String.
   * @return a two-character String describing the hours, e.g. "-02"
   */
  public String getHoursString()
  {
    return hdf.format( (int)( hours ) );
  }


  /**
   * Return the minutes.
   * @return minutes
   * @see #minutes
   */
  public int getMinutes()
  {
    return minutes;
  }


  /**
   * Return the minutes as a double digit String.
   * @return a two-character String describing the minutes, e.g. "39" 
   */
  public String getMinutesString()
  {
    return df.format( (int)( minutes ) );
  }


  /**
   * Return the seconds.
   * @return seconds
   * @see #seconds
   */
  public int getSeconds()
  {
    return seconds;
  }


  /**
   * Return the seconds as a double digit String.
   * @return a two-character String describing the seconds, e.g. "05" 
   */
  public String getSecondsString()
  {
    return df.format( (int)( seconds ) );
  }


  /**
   * Return the milliseconds.
   * @return millis
   * @see #millis
   */
  public int getMillis()
  {
    return millis;
  }


  /**
   * Return the milliseconds.
   * @return millis
   * @see #millis
   */
  public int getMilliSeconds()
  {
    return millis;
  }


  /**
   * Return the seconds as a double digit String.
   * @return a two-character String describing the milliseconds, e.g. "059" 
   */
  public String getMillisecondsString()
  {
    return msdf.format( millis );
  }


  /**
   * Return this HoursMinutesSeconds as decimal degrees.
   * @return decimalDegrees
   * @see #decimalDegrees
   */
  public double getDegrees()
  {
    return decimalDegrees;
  }


  /**
   * Return this HoursMinutesSeconds as radians.
   * @return radians
   * @see #radians
   */
  public double getRadians()
  {
    return radians;
  }


  /**
   * Return the string "#HHh MMm SS.SSSs" where HH are hours, MM are minutes
   * SS.SSS are seconds to millisecond precision, and the # is a negative
   * sign or space.
   */
  public String toString()
  {
    return( getHoursString()+"h "+
	    getMinutesString()+"m "+
	    getSecondsString()+"."+
	    getMillisecondsString()+"s" );
  }
}
/*
 *    $Date: 2013-07-04 10:37:32 $
 * $RCSfile: HoursMinutesSeconds.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/HoursMinutesSeconds.java,v $
 *      $Id$
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
