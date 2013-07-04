package ngat.ngtcs.common;

import java.text.*;

/**
 * Container class for an Degrees:Minutes:Seconds object as used by either time
 * variables or angle variables.
 * <br>
 * This class is non-mutable.
 * 
 * @author $Author: cjm $ 
 * @version $Revision: 1.2 $
 */
public class DegreesMinutesSeconds
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
    new String( "$Id: DegreesMinutesSeconds.java,v 1.2 2013-07-04 10:36:25 cjm Exp $" );

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT FIELDS.                                                          */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Number of degrees.
   */
  protected int degrees;

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
   * NumberFormatter for degrees.
   */
  protected DecimalFormat ddf = new DecimalFormat( "+#00;-#00" );

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
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified radians.
   * @param d the input angle in radians
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds radiansToDMS( double d )
  {
    return radiansToDMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified radians.
   * @param d the input angle in radians
   * @param b whether to range the degrees from 0.0 - 359.9
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds radiansToDMS( double d, boolean b )
  {
    double s = ( d * 180.0 * 3600.0 ) / Math.PI;
    return arcsecondsToDMS( s, b );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified timestamp.
   * @param t the input time as a Timestamp
   * @return a new DegreesMinutesSeconds object representation of t
   */
  public static DegreesMinutesSeconds timestampToDMS( Timestamp t )
  {
    return secondsToDMS( (double)( t.getTime() ), true );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified seconds.
   * @param d the input time or angle as seconds of time
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds secondsToDMS( double d )
  {
    return secondsToDMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified seconds.
   * @param d the input time or angle as seconds of time
   * @param b whether to range the degrees from 0.0 - 359.9
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds secondsToDMS( double d, boolean b )
  {
    return arcsecondsToDMS( ( d * 15.0 ), b );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified arcseconds.
   * @param d the input angle as arcseconds
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds arcsecondsToDMS( double d )
  {
    return arcsecondsToDMS( d, false );
  }


  /**
   * Create a new DegreesMinutesSeconds object, calculated from the
   * specified arcseconds.
   * @param d the input angle as arcseconds
   * @param b whether to range the degrees from 0.0 - 359.9
   * @return a new DegreesMinutesSeconds object representation of d
   */
  public static DegreesMinutesSeconds arcsecondsToDMS( double d, boolean b )
  {
    int sign = 1;
    double milliSecs = d * 1000.0;

    if( b )
    {
      while( milliSecs < 0.0 )
      {
	milliSecs += 1296000000;
      }
    }

    if( milliSecs < 0.0 )
    {
      sign = -1;
    }

    int absDecimalInt = (int)Math.abs( milliSecs );
    int millisInt = ( absDecimalInt ) % 1000;
    int allSeconds = ( absDecimalInt - millisInt ) / 1000;
    int secondsInt = allSeconds % 60;
    int allMinutes = ( allSeconds - secondsInt ) / 60;
    int minutesInt = allMinutes % 60;
    int degreesInt = ( ( allMinutes - minutesInt ) / 60 ) % 360;

    return new DegreesMinutesSeconds
      ( sign*degreesInt, minutesInt, secondsInt, millisInt );
  }

  /*=========================================================================*/
  /*                                                                         */
  /* OBJECT METHODS.                                                         */
  /*                                                                         */
  /*=========================================================================*/

  /**
   * Create a new DegreesMinutesSeconds object with the specified degrees,
   * minutes and seconds, with the milliseconds set to zero.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   */
  public DegreesMinutesSeconds( int d, int m, int s )
  {
    this( d, m, s, 0 );
  }


  /**
   * Create a new DegreesMinutesSeconds object with the specified degrees,
   * minutes and seconds.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   */
  public DegreesMinutesSeconds( int d, int m, double s )
  {
    this( d, m, (int)s, ( (int)( s * 1000.0 ) ) % 1000 );
  }


  /**
   * Create a new DegreesMinutesSeconds object with the specified degrees,
   * minutes, seconds and milliseconds.
   * @param d degrees
   * @param m minutes
   * @param s seconds
   * @param ms milliseconds
   */
  public DegreesMinutesSeconds( int d, int m, int s, int ms )
  {
    double sign = 1.00000000;

    degrees = d;
    minutes = m;
    seconds = s;
    millis = ms;

    if( degrees < 0 ) sign = -1.00000000;

    decimalDegrees =
      ( ( (double)millis / 1000.0 + (double)seconds ) / 60.0 +
      (double)minutes ) / 60.0 + (double)( Math.abs( degrees ) ) * sign;

    radians = Math.toRadians( decimalDegrees );
  }


  /**
   * Return the degrees.
   * @return degrees
   * @see #degrees
   */
  public int getDegrees()
  {
    return degrees;
  }


  /**
   * Return the degrees as a two-digit String, e.g. "-02"
   * @return the formatted String representing the degrees
   * @see #degrees
   */
  public String getDegreesString()
  {
    return ddf.format( (int)( degrees ) );
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
   * Return the minutes as a two-digit String, e.g. "02"
   * @return the formatted String representing the minutes
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
   * Return the seconds as a two-digit String, e.g. "02"
   * @return the formatted String representing the seconds
   * @see #seconds
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
   * Return the milliseconds as a two-digit String, e.g. "02"
   * @return the formatted String representing the milliseconds
   * @see #millis
   */
  public String getMillisecondsString()
  {
    return msdf.format( millis );
  }


  /**
   * Return the decimal degrees.
   * @return decimalDegrees
   * @see #decimalDegrees
   */
  public double getDecimalDegrees()
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
   * Return the string "#DDD&deg; MM' SS.SSSs''" where DDD are degrees, MM
   * are minutes SS.SSS are seconds to millisecond precision, and the # is a
   * negative sign or space.
   */
  public String toString()
  {
    return( getDegreesString()+( (char)176 )+" "+
	    getMinutesString()+"' "+
	    getSecondsString()+"."+
	    getMillisecondsString()+"''" );
  }
}
/*
 *    $Date: 2013-07-04 10:36:25 $
 * $RCSfile: DegreesMinutesSeconds.java,v $
 *  $Source: /space/home/eng/cjm/cvs/ngat/ngtcs/common/DegreesMinutesSeconds.java,v $
 *      $Id: DegreesMinutesSeconds.java,v 1.2 2013-07-04 10:36:25 cjm Exp $
 *     $Log: not supported by cvs2svn $
 *     Revision 1.1  2003/07/01 10:13:04  je
 *     Initial revision
 *
 */
